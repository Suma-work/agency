package com.sumainfo.common.until;

public class SqlUntil {
	public static String getNearShop(Double lat,Double lon,String classify){
		String sql = "SELECT"+" "+
	                  "shopId,"+
	                  "shopName,"+
	                  "shopPicAdress,"+
	                  "lon,"+
	                  "lat,"+
	"round(6378.138*2*asin(sqrt(pow(sin(("+lat+"*pi()/180-lat*pi()/180)/2),2)+cos("+lat+"*pi()/180)*cos(lat*pi()/180)* pow(sin(("+lon+"*pi()/180-lon*pi()/180)/2),2)))*1000) AS distance"+
" "+"FROM"+" "+
	"shop"+" "+
"WHERE"+" "+
	"classify ="+classify+" "+
"GROUP BY distance ASC"+" "+
"LIMIT 0,5";
		return sql;
	}
	
	public static String getpt4SShopMs(String shopName,String citynm,Double lat,Double lon,int minPageNum,int maxPageNum){
		
		return null;
		
	}

}
