package com.lin.myzone.utils;

import java.util.Iterator;

public class CRC16 
{
    private int[] crcTable = new int[256];
    
    public CRC16() 
    {
        computeCrcTable();
    }
    /*********************************************************************
    Initcrc16table
  
   Create CRC-16 look-up table for polynomial x^16+x^15+x^2+1
 *********************************************************************/
    /*
     * 生成0 - 255对应的CRC16校验码
     */
    private void computeCrcTable() 
    {
    	int i, j;
        int crc, c;

        for (i=0; i<256; i++) 
        {

            crc = 0;
            c   = i;

            for (j=0; j<8; j++) {

                if ( ((crc ^ c) & 0x0001)!=0 ) 
                	crc = ( crc >> 1 ) ^ 0xA001;
                else                      
                	crc =   crc >> 1;

                c = c >> 1;
            }

            crcTable[i] = crc;
        }

    }

    public int getCrc(int[] data) 
    {
        int crc = 0;
        
        for (int i = 0; i < data.length; i++) 
        {
        	int short_c=0xff & data[i];
        	int tmp= crc ^ short_c ;
            crc = (crc  >> 8) ^ crcTable[tmp & 0xFF];
        }
        return crc;
    }
    

    /*
     * 获取crc校验的short形式
     */
    public static int crc16Short(int[] data) 
    {
        return new CRC16().getCrc(data);
    }

    
    /*************************************************************************
     *  Compilation:  javac CRC16CCITT.java
     *  Execution:    java CRC16CCITT s
     *  Dependencies: 
     *  
     *  Reads in a sequence of bytes and prints out its 16 bit
     *  Cylcic Redundancy Check (CRC-CCIIT 0xFFFF).
     *
     *  1 + x + x^5 + x^12 + x^16 is irreducible polynomial.
     *
     *  % java CRC16-CCITT 123456789
     *  CRC16-CCITT = 29b1
     *
     *************************************************************************/
        public static int crc16CCITT(byte[] srcData) 
        { 
            int crc = 0xFFFF;          // initial value
            int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) 

            for (int b : srcData) 
            {
                for (int i = 0; i < 8; i++) 
                {
                    boolean bit = ((b   >> (7-i) & 1) == 1);
                    boolean c15 = ((crc >> 15    & 1) == 1);
                    crc <<= 1;
                    if (c15 ^ bit) crc ^= polynomial;
                 }
            }

            crc &= 0xffff;
            return crc;
        }
        /*
         * nationz版本
         */
        public static int crc16CCITTNZ(byte[] srcData) 
        { 
        	int data;
        	//crc-ccitt
        	int poly=0x8408;  //1000 0100 0000 1000  == x12+x5+1
        	int crc=0xffff;
        	int Len=srcData.length;
        	if (Len == 0)
        		return 0;
        	
        	for (int i=0;i < srcData.length;i++)
        	{
        		data=(int)0xff & srcData[i];
        		for(int j=0;j<8;j++)
        		{
        			if ( ((crc & 0x0001) ^ (data & 0x0001))>0)
        				crc = (crc >> 1) ^ poly;
        			else  
        				crc >>= 1;
                    data >>= 1;
        		}
    			
        	} 
        	
        	return (~crc)&0x0000ffff ;
            
            
        }
        public static void main(String[] args)
	   	 {
	   		 /*String str="0100004ea10006504331303030a300083131313131313131a5000a31303030303030303033a80003000035a90003000000ab000420121128ac0003210942c400020100c500020100c600020100c700020100";
	   		 byte[] src=DESTools.AscToBcd(str);
	   		
	   		 int crcCalculate=CRC16.crc16CCITTNZ(src);
	   		 byte[] btCrc=new byte[2];
	   		 btCrc[0]=(byte)((crcCalculate&0xff00)>>8);
	   		 btCrc[1]=(byte)(crcCalculate&0x00ff);
	   		 
	   		 int crcCalculate2 = CRC16.crc16CCITT(src);
	   		 byte[] btCrc2=new byte[2];
	   		 btCrc2[0]=(byte)((crcCalculate2&0xff00)>>8);
	   		 btCrc2[1]=(byte)(crcCalculate2&0x00ff);
	   		 System.out.println(crcCalculate + ";" + Util.printMsg(btCrc) + ";" + crcCalculate2 + "; " + Util.printMsg(btCrc2));*/
        	
        	 CRC16 crc16 = new CRC16();
        	 int index = 0;
        	 for (int i = 0; i < 256; i++) {
        		 int crcData = crc16.crcTable[i];
        		 System.out.print("0x" + String.format("%04x", crcData) + ", ");
        		 if (++index == 16) {
 					System.out.println();
 					index = 0;
 				}
			 }
        	 System.out.println();
        	 for (int i : crc16.crcTable) {
				System.out.print("0x" + String.format("%04x", i) + ", ");
				if (++index == 16) {
					System.out.println();
					index = 0;
				}
			 }
	   	 }
}