package com.lin.myzone.utils;

public class Const {
	

	//用户号
	public final static String USER_NO="userNo";
	
	//渠道商路径前缀
	public final static String PRE_CHANNEL_NAME="prechannel";
	
	//用户登录系统标识
	public final static String LOGIN_USER="loginUser";
	
	public static final int STATUS = 19;
	public static final int TYPE = 22;
	
	//用户名字
	public final static String USER_NAME = "userName";
	
	//版权信息
	public final static String COPYRIGHTINFO = "copyrightInfo";
	
	//报表存储前缀路径
	public final static String REPORTPATH = "reportPath";
	
	//图片路径
	//public final static String SHOW_PATH = "http://www.vvjoin.com:8090/images";
	//public final static String SHOW_PATH_MIN = "http://211.162.73.85:9010/images/min";
	//public final static String SHOW_PATH_MAX = "http://211.162.73.85:9010/images/max";
	//public final static String SHOW_PATH = "http://192.168.1.190:8090/images";
	public final static String SHOW_PATH = "http://192.168.1.190:3333/file";
	//public final static String SHOW_PATH = "http://localhost:8080/CRM/images";
	public final static String SHOW_PATH_MIN = "http://192.168.1.190:3333/file/min";
	public final static String SHOW_PATH_MAX = "http://192.168.1.190:3333/file/max";
	
    //权限名称
	public final static String ROLE = "role";
	//权限ID
	public final static String IS_ADMIN = "is_admin";
	
	//用户所拥有权限菜单id集合
	public final static String ROLE_MENUIDS = "roleMenuIds";
	
	
	//登录时间
	public final static String LOGIN_TIME = "loginTime";
	
	//root管理员菜单json
	public final static String MENU_JSON = "menuJson";
	
	//非root菜单json
	public final static String MENU_JSON2 = "menuJson2";
	
	//用户所拥有的菜单列表
	public final static String MENU_LIST = "menuList";
	
	//商户号
	public final static String MERCHANTNO ="merchantNo";
	
	//商店号
	public final static String SHOPNO = "shopNo";
	
	//登录的商户
	public final static String LOGIN_MERCHANT = "merchant";
	
	//登录的商店
	public final static String LOGIN_SHOP = "shop";
	
	
	//商户头像
	public final static String LOGINLOGO_PATH = "/upload/merchant/logo/";
	public final static int loginLogoHeight=200;
	public final static int loginLogoWidth=200;   
	
	//用户头像
	public final static String HEADPHOTO = "/user/headPhoto/";
	//会员头像
	public final static String MEMBERPHOTO = "/member/memberPhoto/";
	
	//图片路径
/*//	public final static String FILEPATH = "/home/file-tomcat/webapps/images";
	public final static String UPLOAD_MIN_FILEPATH="/home/file-tomcat/webapps/images/min";
	public final static String UPLOAD_MAX_FILEPATH="/home/file-tomcat/webapps/images/max";*/
	//二维码图片路径
	public final static String CODEPATH = "/home/file-tomcat/webapps/images/code/";
	//public final static String CODEPATH = "d://test/";
	//public final static String FILEPATH = "C:\\Program Files\\apache-tomcat-6.0.39\\webapps\\CRM\\images";
	public final static String FILEPATH = "D:/file-tomcat/webapps/file";
	public final static String UPLOAD_MIN_FILEPATH="D:/file-tomcat/webapps/file/min";
	public final static String UPLOAD_MAX_FILEPATH="D:/file-tomcat/webapps/file/max";
	
	/*
	//商户头像
		public final static String LOGINLOGO_PATH = "";
		public final static int loginLogoHeight=200;
		public final static int loginLogoWidth=200;   
		
		//图片路径
		public final static String FILEPATH = "C:/Program Files/apache-tomcat-6.0.39/webapps/CRMOrderNew/images";
		
		public final static String DBA_FILEPATH = "d:/sss/var/www/html/dba/images";
		*/
		
		//首次关注回复图片信息
		public final static String FIRST_MATERIAL="firstMaterial";
		
		public final static String FIRST_MATERIAL_0 = "firstMaterial0";
		
		//中图文保存路径
		
		public final static String GRAPHIC_PATH = "/upload/graphic/";				
		//小图保存路径
		public final static String GRAPHIC_PATH_MIN = "/min/upload/graphic/";
		//大图保存路径
		public final static String GRAPHIC_PATH_MAX = "/max/upload/graphic/";
		
		
		//优惠券图片背景保存路径
		public final static String COUPON_BG_PATH = "/upload/coupon/merchant/";
		
		//系统使用图片高度
		public final static int imageHeight=400;
		
		//系统使用图片宽度
		public final static int imageWidth=720;
		
		
		//在素材库里上传图片为空的时候的默认图片
		public final static String LINE_PNG = "/line.png";
		
		
		//当日时间
		public final static String TODAY = "today";
		
		//首页顶部广告图片设置高度
		public final static int indxAdsImageHeight=300;
		
		
		//首页顶部广告图片设置宽度
		public final static int indxAdsImageWidth=900;      
		
		//内容编辑栏目和文章封面图片设置高度
		public final static int contentEditImageHeight=640;
		//内容编辑栏目和文章封面图片设置宽度
		public final static int contentEditImageWidth=640;
		
		
		//压缩上传图片的高度和宽度（大图）
		public final static int  uploadMaxImageHeight=640;
		public final static int  uploadMaxImageWidth=640;
		
		
		//压缩上传图片的高度和宽度（中图）
		public final static int  uploadMiddleImageHeight=400;
		public final static int  uploadMiddleImageWidth=400;
		
		//压缩上传图片的高度和宽度（小图）
		public final static int  uploadMinImageHeight=220;
		public final static int  uploadMinImageWidth=220;
		//设置商品类型显示图片
		public final static int  wemallsTypeImageHeight=100;
		public final static int  wemallsTypeImageWidth=70;
		
		//小图高度和宽度
		public final static int  wemallsGoodsImageHeight=160;
		public final static int  wemallsGoodsImageWidth=160;
		
		
		
		//卡片图片背景保存路径
		public final static String CARD_BG_PATH = "/upload/card/merchant/";
		
		public static final int MERCHANTPOINTSETTING = 1;
		public static final int MERCHANTPOINTTIMES = 4;
		public static final int MERCHANTPOINTTYPE = 7;
		public static final int MERCHANTPOINTVERTY = 12;
		
		
		/*//网站域名
		public final static String PRE_APACHE_PATH="http://www.vvjoin.com:8090";*/
		public final static String PRE_APACHE_PATH="http://211.162.73.85:9010";
		//public final static String PRE_APACHE_PATH="http://192.168.1.190:8090";
		//public final static String PRE_APACHE_PATH="http://localhost:8080/CRM";
		public final static String IPADDRESS="ipaddress";


		
	
}
