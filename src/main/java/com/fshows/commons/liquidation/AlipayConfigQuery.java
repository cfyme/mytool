package com.fshows.commons.liquidation;

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
public class AlipayConfigQuery {
	private static final Log log = LogFactory.getLog(AlipayConfigQuery.class);
	static int pageSize = 1000;
	
	static AtomicInteger counter = new AtomicInteger();

	static final int DEFAULT_IO_THREADS = 25;
	public static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
	public static ThreadPoolExecutor iphoneExecutor = new ThreadPoolExecutor(DEFAULT_IO_THREADS,
			DEFAULT_IO_THREADS*2, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static String access_token = "";

	public static void execute() {
		log.error("=========start===========");
		System.out.println(getList());
		log.error("=========end===========");

	}

	public static List<Map<String, Object>> getList() {
		String sql="select * from lp_alipay_config";
		List<Map<String, Object>> list = new DBLiquidationUtilsTemplate().find(sql);
		return list;
	}


}
