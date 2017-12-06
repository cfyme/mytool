package com.fshows.commons.kfshop;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * group=143
 *
 */
public class BalanceDeal {
	private static final Log log = LogFactory.getLog(BalanceDeal.class);
	static int pageSize = 1000;
	
	static AtomicInteger counter = new AtomicInteger();

	static final int DEFAULT_IO_THREADS = 25;
	public static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
	public static ThreadPoolExecutor iphoneExecutor = new ThreadPoolExecutor(DEFAULT_IO_THREADS,
			DEFAULT_IO_THREADS*2, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static String access_token = "";
	static Double  total = 0.0;
	public static void execute() {
		log.error("=========start===========");

		List<Map<String, Object>> list =  getList();
		executeList(list);
		log.error("=========end===========");

	}

	public static List<Map<String, Object>> getList() {
		String sql=" SELECT      \n" +
				"        t1.merchant_id merchantId\n" +
				"        FROM\n" +
				"        fbs_merchant t1, fbs_pay_company t2\n" +
				"        WHERE\n" +
				"        t1.pay_company_id = t2.pay_company_id\n" +
				"        AND\n" +
				"        t2.is_bank_witness != 0\n" +
				"        AND\n" +
				"        t1.status != 0\n" +
				"        AND\n" +
				"        CAST(LEFT(t1.merchant_id, 8) AS signed) <= 20170713";
		List<Map<String, Object>> list = new DBLiquidationUtilsTemplate().find(sql);

		log.info("list.size="+list.size());
		return list;
	}


	public static void executeList ( List<Map<String, Object>>  list){
		int idx = 0;
		for(Map<String, Object> map : list){
			idx++;
			String merchantId = map.get("merchantId")+"";

			String sql = " SELECT\n" +
					"        SUM(calculation_money) as money\n" +
					"        FROM\n" +
					"        fbs_merchant_balance_calculation\n" +
					"        WHERE\n" +
					"        merchant_id = '"+merchantId+"'\n" +
					"      \n" +
					"            AND source_day <= 20170713\n" +
					"    ";


			Map<String, Object> obj = DBLiquidationUtilsTemplate.findFirst(sql);

			String money = obj.get("money")+"";

			if(money==null || "null".equalsIgnoreCase(money)){
				money="0.0";
			}

			double balance = Double.valueOf(money);

			total+=balance;
			log.info("merchantId,"+merchantId+", total="+total+",money="+money+",idx="+idx);

		}

	}

}
