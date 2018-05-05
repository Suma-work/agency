package com.sumainfo.common.area;

import java.util.ArrayList;

import com.sumainfo.common.entity.AreaTable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 通过极速的api 获取全国省市的信息
 * @author Administrator
 *
 */

public class AreaUntil {
	 public static final String APPKEY = "0cf833e290074463";// 你的appkey
	 public static final String URL = "http://api.jisuapi.com/area/all";
	
	public static ArrayList<AreaTable> provinceMessage(){
		        ArrayList<AreaTable> list = new ArrayList<AreaTable>();
		        AreaTable areaTable = null;
		        String result = null;
		        String url = URL + "?appkey=" + APPKEY; 
		        try {
		            result = HttpUntil.sendGet(url, "utf-8");
		            JSONObject json = JSONObject.fromObject(result);
		            if (json.getInt("status") != 0) {
		                System.out.println(json.getString("msg"));
		            } else {
		                JSONArray resultarr = json.optJSONArray("result");
		                for (int i = 0; i < resultarr.size(); i++) {
		                    JSONObject obj = (JSONObject) resultarr.opt(i);
		                    String id = obj.getString("id");
		                    String name = obj.getString("name");
		                    String parentname = obj.getString("parentname");
		                    String areacode = obj.getString("areacode");
		                    String zipcode = obj.getString("zipcode");
		                    String depth = obj.getString("depth");
		                    System.out.println("主键id:"+id + " " +"名称:"+ name + " " + "父级名称:"+parentname + " " + "编码:"+areacode + " " + "其他:"+zipcode + " " + "冗余:"+depth);
		                    areaTable = new AreaTable(id, name, parentname, zipcode, areacode);
		                    list.add(areaTable);
		                }
		                
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		  
		return list;
	}
	
	public void cityMessage(){
			
    }
	
	public void regionMessage(){
		
	}

}
