package com.sumainfo.common.until;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.Pager;

public class PageUtils {
	/**
	 * 处理参数名称不一致
	 * @param params
	 */
	public void getPageParam(Map<String, Object> params){
		Pager pager = new Pager();
		if(params.get("rows") != null ){
			pager.setPagesize(Integer.valueOf(params.get("rows").toString()));
		}else{
			pager.setPagesize(20);
		}
		if(params.get("page") != null){
			pager.setPageno(Integer.valueOf(params.get("page").toString()));
		}else{
			pager.setPageno(1);
		}
		
		pager.setPager(params, pager);
	}
	
	public JsonResult getJsonResult(List<Map<String, Object>> userList,Map<String, Object> params,Integer cout){
		JsonResult jr = new JsonResult();
		Map<String,Object> mapres = new HashMap<>();
		mapres.put("dataList", userList);
		if(params.get("page") != null){
			mapres.put("currPage", params.get("page").toString());
		}
		Integer totalPage;//总页数
		Integer total;
		//计算总页数
		if(params.get("rows") != null){
			total = cout%Integer.valueOf(params.get("rows").toString());
			if(total == 0){
				//总记录整除每页记录数，
				//总页数等于总记录除每页记录数
				totalPage = cout/Integer.valueOf(params.get("rows").toString());
			}else{
				//总记录不整除每页记录数，
				//总页数等于总记录除每页记录数+1
				totalPage = cout/Integer.valueOf(params.get("rows").toString())+1;
			}
		}else{
			total = cout%20;
			if(total == 0){
				//总记录整除每页记录数，
				//总页数等于总记录除每页记录数
				totalPage = cout/20;
			}else{
				//总记录不整除每页记录数，
				//总页数等于总记录除每页记录数+1
				totalPage = cout/20+1;
			}
		}
		
		mapres.put("totalPage", totalPage);
		mapres.put("totalCount", cout);
		jr.put(mapres);
		return jr;
	}
}
