package com.lin.myzone.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESTools 
{
	private DESTools()
	{
		
	}
	
	//mode 0为加密，1为解密
	public static byte[] des(byte[] btInput,byte[] btKeyValue,int mode)
	{
		 try
		 {
			 // 从原始密匙数据创建一个DESKeySpec对象
			 DESKeySpec dks = new DESKeySpec( btKeyValue );
	
			 // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 一个SecretKey对象
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			 SecretKey key = keyFactory.generateSecret( dks );
	
			 // Cipher对象实际完成解密操作
			 Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			 // 用密匙初始化Cipher对象
			 if(mode==0) //加密
			 {
				 cipher.init( Cipher.ENCRYPT_MODE, key );
			 }
			 else //解密
			 {
				 cipher.init( Cipher.DECRYPT_MODE, key );
			 }
			 // 正式执行加或解密操作
			 byte[] output = cipher.doFinal( btInput );
			 return output;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return null;
		 }
	}

	//mode 0为加密，1为解密
	public static byte[] trides(byte[] btInput,byte[] btKeyValue,int mode)
	{
		byte[] in=new byte[8];
		byte[] out=new byte[8];
		byte[] key=new byte[8];
		
		for(int i=0;i<8;i++)
		{
			in[i]=btInput[i];
			key[i]=btKeyValue[i];
		}
		out=des(in,key,mode);
		
		for(int i=0;i<8;i++)
		{
			in[i]=out[i];
			key[i]=btKeyValue[8+i];
		}
		if(mode==0){
			out=des(in,key,1);
		}else{
			out=des(in,key,0);			
		}
		
		for(int i=0;i<8;i++)
		{
			in[i]=out[i];
			key[i]=btKeyValue[i];
		}
		out=des(in,key,mode);
		return out;
		
	}

	/**
	 * 输出16 byte 3DES密钥结果
	 * mode 0为加密，1为解密
	 */
	public static byte[] tridesEx(byte[] btInput,byte[] btKeyValue,int mode)
	{
		byte[] out=new byte[16];
		
		byte[] out1 = trides(btInput,btKeyValue,mode);
		if(btInput.length>=16){
			byte[] tmpInput=new byte[8];
			for(int i=0;i<8;i++)
			{
				tmpInput[i]=btInput[i+8];
			}
			byte[] out2 = trides(tmpInput,btKeyValue,mode);
			for(int i=0;i<8;i++)
			{
				out[i]=out1[i];
				out[i+8]=out2[i];
			}
			return out;
		}else{
			return out1;
		}
	}
	
	public static byte[] ecbMac(byte[] input,byte[] bttMackey)
	{
		int blockNumber=input.length/8;
		byte[] macValue=null;
		if(input.length%8!=0)
		{
			blockNumber+=1;
		}
		macValue=new byte[blockNumber*8];
		for(int i=0;i<macValue.length;i++)
		{
			macValue[i]=0;
		}
		for(int i=0;i<input.length;i++)
		{
			macValue[i]=input[i];
		}
		byte[] iv=new byte[8];
		for(int i=0;i<8;i++)
			iv[i]=0;
		byte[] in=new byte[8];
		for(int i=0;i<blockNumber;i++)
		{
			for(int j=0;j<8;j++)
				in[j]=macValue[8*i+j];
			for(int j=0;j<8;j++)
				iv[j]=(byte)(iv[j]^in[j]);
			
		}
		byte[] out=des(iv,bttMackey,0);
		return out;
	}
	
	public static byte[] AscToBcd(String hex)
	{
		
		//A null string returns an empty array
		if (hex == null || hex.length() == 0) {
			return new byte[0];
		} else if (hex.length() < 3) {
			return new byte[]{ (byte)(Integer.parseInt(hex, 16) & 0xff) };
		}
		//Adjust accordingly for odd-length strings
		int count = hex.length();
		int nibble = 0;
		if (count % 2 != 0) {
			count++;
			nibble = 1;
		}
		byte[] buf = new byte[count / 2];
		char c = 0;
		int holder = 0;
		int pos = 0;
		for (int i = 0; i < buf.length; i++) {
		    for (int z = 0; z < 2 && pos<hex.length(); z++) {
		        c = hex.charAt(pos++);
		        if (c >= 'A' && c <= 'F') {
		            c -= 55;
		        } else if (c >= '0' && c <= '9') {
		            c -= 48;
		        } else if (c >= 'a' && c <= 'f') {
		            c -= 87;
		        }
		        if (nibble == 0) {
		            holder = c << 4;
		        } else {
		            holder |= c;
		            buf[i] = (byte)holder;
		        }
		        nibble = 1 - nibble;
		    }
		}
		return buf;
	}
	public static String BcdToAsc(byte[] buffer)
	{
		char[] HEX = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };		
		if (buffer.length == 0) {
			return "";
		}
		int holder = 0;
		char[] chars = new char[buffer.length * 2];
		for (int i = 0; i < buffer.length; i++) {
			holder = (buffer[i] & 0xf0) >> 4;
			chars[i * 2] = HEX[holder];
			holder = buffer[i] & 0x0f;
			chars[(i * 2) + 1] = HEX[holder];
		}
		return new String(chars);
		
	}
	
	public static byte[] sessionKeyGen(String masterKey,String sn)
	{
		byte[] masterkey=AscToBcd(masterKey);
		String rightsn=sn.substring(sn.length()-16);
		byte[] intran=AscToBcd(rightsn);
		byte[] random=new byte[8];
		for(int i=0;i<8;i++)
			random[i]=intran[i];
		
		byte[] randomReverse=new byte[8];
		for(int i=0;i<8;i++)
			randomReverse[i]=  (byte)~random[i];
		
		byte[] masterkeyLeft=new byte[8];
		byte[] masterkeyRight=new byte[8];
		for(int i=0;i<8;i++)
			masterkeyLeft[i]=masterkey[i];
		for(int i=0;i<8;i++)
			masterkeyRight[i]=masterkey[8+i];
		
		byte[] resultA=des(random,masterkeyLeft,0);
		byte[] resultB=des(resultA,masterkeyRight,1);
		byte[] resultC=des(resultB,masterkeyLeft,0);
		
		byte[] resultD=des(randomReverse,masterkeyLeft,0);
		byte[] resultE=des(resultD,masterkeyRight,1);
		byte[] resultF=des(resultE,masterkeyLeft,0);
		
		byte[] resultG=new byte[16];
		for(int i=0;i<8;i++)
			resultG[i]=resultC[i];
		for(int i=0;i<8;i++)
			resultG[8+i]=resultF[i];
		return resultG;
		
	}
	
	public static byte[] smsMac(byte[] input,byte[] key)
	{		
		
		byte[] rawData=null;
		int length=input.length;
		if(length%8==0)
		{
			rawData=new byte[length+8];
			for(int i=0;i<length;i++)
				rawData[i]=input[i];
			rawData[length]=(byte)0x80;rawData[length+1]=0x00;
			rawData[length+2]=0x00;rawData[length+3]=0x00;
			rawData[length+4]=0x00;rawData[length+5]=0x00;
			rawData[length+6]=0x00;rawData[length+7]=0x00;
		}
		else
		{
			int left=length%8;
			rawData=new byte[length+8-left];
			for(int i=0;i<length;i++)
				rawData[i]=input[i];
			
			rawData[length]=(byte)0x80;
			for(int i=0;i<7-left;i++)
				rawData[length+1+i]=0x00;
			
		}
		byte[] iv=new byte[8];
		for(int i=0;i<8;i++)
			iv[i]=0x00;
		byte[] value=new byte[8];
		for(int i=0;i<rawData.length/8;i++)
		{
			for(int j=0;j<8;j++)
				value[j]=(byte)(iv[j]^rawData[i*8+j]);
			byte[] result=trides(value,key,0);
			for(int j=0;j<8;j++)
				iv[j]=result[j];
		}
		return iv;
	}
	public static byte[] MacCal(byte[] input,byte[] key)
	{
				
		byte[] rawData=null;
		int length=input.length;
		if(length%8==0)
		{
			rawData=new byte[length];
			System.arraycopy(input, 0, rawData, 0, length);
		}
		else
		{
			int left=length%8;
			rawData=new byte[length+8-left];
			System.arraycopy(input, 0, rawData, 0, length);
			for(int i=0;i<8-left;i++)
				rawData[length+i]=0x00;
		}
		byte[] iv=new byte[8];
		for(int i=0;i<8;i++)
			iv[i]=0x00;
		byte[] value=new byte[8];
		for(int i=0;i<rawData.length/8;i++)
		{
			for(int j=0;j<8;j++)
				value[j]=(byte)(iv[j]^rawData[i*8+j]);
			for(int j=0;j<8;j++)
				iv[j]=value[j];
		}
		String resultA=BcdToAsc(value);
		resultA=resultA.toUpperCase();
		byte[] leftByte=new byte[8];
		for(int i=0;i<8;i++)
		{
			char ch=resultA.charAt(i);
			leftByte[i]=(byte)ch;
		}
		byte[] rightByte=new byte[8];
		for(int i=0;i<8;i++)
		{
			char ch=resultA.charAt(8+i);
			rightByte[i]=(byte)ch;
		}	
		byte[] leftResult=des(leftByte,key,0);
		for(int j=0;j<8;j++)
			value[j]=(byte)(leftResult[j]^rightByte[j]);
		
		byte[] rightResult=des(value,key,0);
		resultA=BcdToAsc(rightResult);
		resultA=resultA.toUpperCase();
		for(int i=0;i<8;i++)
		{
			char ch=resultA.charAt(i);
			leftByte[i]=(byte)(ch);
	        
		}
		return leftByte;
	}
	/**
	* 计算MAC
	* @param bdata 待处理的数据     bkey 加密Key
	* @return byte[]  
	* @throws Exception
	*/
	public static byte[] doMac(byte[] bdata,byte[] bkey){
		byte[] tmp=new byte[8];
		byte[] result=new byte[8];
		int index=0;

		for(int i=0;i<8;i++)
			result[i]=0;
		while(index<bdata.length){
			for(int j=0;j<8;j++){
				if(index<bdata.length){
					tmp[j]=bdata[index++];
				}else{
					tmp[j]=0;
				}
			}
			for (int j = 0; j < 8; j++) 
				   tmp[j] =(byte)( tmp[j] ^ result[j]);
			result=des(tmp,bkey,0);
		}
		return result;
	}

	 public static void main(String[] args)
	 {
		 /*byte[] intMac = DESTools.AscToBcd("bb8c6fd12e962a99");
		 byte[] intMasterkey = DESTools.AscToBcd("0102030405060708090a0b0c0d0e0f01");
		 byte[] enc_data = {(byte)1,2,(byte)3,(byte)4,(byte)5,(byte)6,(byte)7,8};
		    	    
		 byte[] encMac=DESTools.trides(intMac,intMasterkey,0);
	 
	    StringBuilder sb3=new StringBuilder();
		sb3.append("====trides data is: ");
		for(int i=0;i<encMac.length;i++)
		{
			String inTmp=String.format("%02x",encMac[i]);
			sb3.append(inTmp);
		}
		sb3.append(",");
		for(int i=0;i<enc_data.length;i++)
		{
			String inTmp=String.format("%02x",enc_data[i]);
			sb3.append(inTmp);
		}
		System.out.println(sb3.toString());
	        
		byte[] responseField64 = DESTools.doMac(enc_data,intMac);
		byte[] responseField64_2 = DESTools.MacCal(enc_data,intMac);
		
	    StringBuilder sb1=new StringBuilder();
		sb1.append("====MacCal data is: ");
		String inTmp="";
		for(int i=0;i<responseField64.length;i++)
		{
			inTmp=String.format("%02x",responseField64[i]);
			sb1.append(inTmp);
		} 
		sb1.append("\t");
		for(int i=0;i<responseField64.length;i++)
		{
			inTmp=String.format("%02x",responseField64_2[i]);
			sb1.append(inTmp);
		}
		System.out.println(sb1.toString());
		
		responseField64 = DESTools.MacCal(AscToBcd("12345678"),intMac);
		responseField64_2 = DESTools.MacCal(AscToBcd("12345678"),intMac);
		StringBuilder sb2=new StringBuilder();
		sb2.append("====MacCal data is: ");
		String inTmp2="";
		for(int i=0;i<responseField64.length;i++)
		{
			inTmp2=String.format("%02x",responseField64[i]);
			sb2.append(inTmp2);
		}
		sb2.append("\t");
		for(int i=0;i<responseField64.length;i++)
		{
			inTmp=String.format("%02x",responseField64_2[i]);
			sb2.append(inTmp);
		}
		System.out.println(sb2.toString());*/
		 
		 byte[] intMac = DESTools.AscToBcd("f9f90ef7fddafa73f9f90ef7fddafa73");
		 byte[] desKey = DESTools.AscToBcd("35353535353535353838383838383837"); 
		 //System.out.println(Util.printMsg(intMac) + "," + Util.printMsg(desKey));
		 byte[] encMac=DESTools.tridesEx(intMac,desKey,0);
		 //System.out.println("trides:"+Util.printMsg(encMac));
		 //System.out.println(Util.printMsg(Util.printMsg(encMac).getBytes()));
	 }
}
