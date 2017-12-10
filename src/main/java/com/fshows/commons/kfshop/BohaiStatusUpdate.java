package com.fshows.commons.kfshop;

import com.alibaba.fastjson.JSON;
import com.fshows.commons.dao.DBKfShopUtilsTemplate;
import com.fshows.commons.util.OkHttpUtils;
import com.fshows.commons.util.SignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BohaiStatusUpdate {

	private static final String secret = "7be46ca25a1611e7907ba6006ad3dba0";


	private static final Log log = LogFactory.getLog(BohaiStatusUpdate.class);

	public static void execute(){
		log.error("=========start===========");

		List<Map<String, Object>> list =  getList();

		try {
			executeList(list);
		}catch ( Exception e){

		}
		log.error("=========end===========");

	}

	public static List<Map<String, Object>> getList() {
		String sql= "SELECT * FROM fbs_life_cycle_withdraw_history WHERE withdraw_type=3 AND withdraw_status=2 ORDER BY create_time desc";
		List<Map<String, Object>> list = DBKfShopUtilsTemplate.find(sql);

		log.info("list.size="+list.size());
		return list;
	}


	private static void executeList(List<Map<String, Object>> list) throws  Exception{

		HashMap<String, Object> map = new HashMap<>();
		map.put("outTradeNo","cfymeTest15096306433160002");
		String sign = SignUtil.sign(map,secret);
		map.putIfAbsent("sign",sign);
		System.err.println("query请求参数：" + JSON.toJSONString(map));

		System.err.println("请求结果：" + OkHttpUtils.post("https://kfshopapi.51youdian.com/kfshopapi/openapiDf/dfQuery", map));
	}

}
