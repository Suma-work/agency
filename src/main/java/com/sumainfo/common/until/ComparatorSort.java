package com.sumainfo.common.until;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComparatorSort {
	static Logger log = LoggerFactory.getLogger(ComparatorSort.class);
	/**
	 * 对List<Map<String,Object>>进行排序，暂时是比较Double类型，可自己扩张
	 * 第一个参数是要排序的对象，第二个参数的根据那个字段排序,第三个字段排序方式 desc升序，反之降序
	 * @author:zhlu
	 * @date: 2018年3月1日
	 * @param params
	 * @param sort
	 * @return
	 */
	public static List<Map<String,Object>> MyCompartor(List<Map<String,Object>> list,String sort,String order){
		//排序前 
		log.info("排序前----");
        for (Map<String, Object> map : list) {
        	log.info(map.get("distance").toString()+"---------------"+map.get("rate").toString());
        }
        
        Map<String, Object> mapPai=new HashMap<String,Object>();
        for(int i=0;i<list.size()-1;i++){
            for(int j=0;j<list.size()-1-i;j++){
                if("1".equals(order)){
                    if(Double.parseDouble(list.get(j).get(sort).toString())>Double.parseDouble(list.get(j+1).get(sort).toString()))
                    {
                        mapPai=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,mapPai);
                    }
                 }
                else{
                    if(Double.parseDouble(list.get(j).get(sort).toString())<Double.parseDouble(list.get(j+1).get(sort).toString()))
                    {
                        mapPai=list.get(j);
                        list.set(j,list.get(j+1));
                        list.set(j+1,mapPai);
                    }
                }
	    }
     }
      //排序前 
        log.info("-----------------");
      	log.info("排序后----");
        for (Map<String, Object> map : list) {
        	log.info(map.get("distance").toString()+"---------------"+map.get("rate").toString());
        }
		return list;
   }
	
//	public static void main(String[] args) {
//        // TODO Auto-generated method stub 
//		List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
//		Map<String,Object>map1=new HashMap<String,Object>();
//		map1.put("distance", "0.00");
//		map1.put("log", 121.329643);
//		map1.put("rate", 0);
//		map1.put("shopName","上海绅亚起亚");
//		map1.put("address","闵行区（七宝）中春路9998号（近沪青平路）");
//		map1.put("lat",31.166567);
//		
//		Map<String,Object>map2=new HashMap<String,Object>();
//		map2.put("distance", "1077.30");
//		map2.put("log", 116.272793);
//		map2.put("rate", 0);
//		map2.put("shopName","海淀保时捷中心");
//		map2.put("address","北京市海淀区西四环北路143号（定慧北桥");
//		map2.put("lat",39.930923);
//		
//		Map<String,Object>map3=new HashMap<String,Object>();
//		map3.put("distance", "12.21");
//		map3.put("log", 121.453917);
//		map3.put("rate", 3);
//		map3.put("shopName","上海安吉荣威");
//		map3.put("address","徐汇区东安路239号5幢1楼");
//		map3.put("lat",31.193597);
//		list.add(map1);
//		list.add(map2);
//		list.add(map3);
//		
//		ComparatorSort.MyCompartor(list, "distance","1");
//		
//    }
}


