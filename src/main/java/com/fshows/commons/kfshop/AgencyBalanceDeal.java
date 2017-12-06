package com.fshows.commons.kfshop;

import com.fshows.commons.dao.DBKfShopUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * group=143
 *
 */
public class AgencyBalanceDeal {
	private static final Log log = LogFactory.getLog(AgencyBalanceDeal.class);
	static int pageSize = 1000;
	
	static AtomicInteger counter = new AtomicInteger();


	static Double  total = 0.0;
	public static void execute() {
		log.error("=========start===========");

		List<Map<String, Object>> list =  getList();
		executeList(list);
		log.error("=========end===========");

	}

	public static List<Map<String, Object>> getList() {
		String sql= "SELECT\n" +
				"        t1.agency_id\n" +
				"        FROM\n" +
				"        fbs_agency t1, fbs_pay_company t2\n" +
				"        WHERE\n" +
				"        t1.pay_company_id = t2.pay_company_id\n" +
				"        AND\n" +
				"        t2.is_bank_witness != 0\n" +
				"        AND\n" +
				"        t1.status = 1\n" +
				"        AND\n" +
				"        CAST(LEFT(t1.agency_id, 8) AS signed) <=20171102";
		List<Map<String, Object>> list = DBKfShopUtilsTemplate.find(sql);

		log.info("list.size="+list.size());
		return list;
	}


	public static void executeList ( List<Map<String, Object>>  list){
		int idx = 0;
		for(Map<String, Object> map : list){
			idx++;
			String agencyId = map.get("agency_id")+"";

			if("20170524153346386450".equalsIgnoreCase(agencyId)){
				continue;
			}

			log.info("agencyId="+agencyId);

			String sql = " SELECT\n" +
					"        SUM(calculation_money) as money\n" +
					"        FROM\n" +
					"        fbs_agency_commission_balance_calculation\n" +
					"        WHERE\n" +
					"        agency_id = '"+agencyId+"'\n" +
					"      \n" +
					"            AND source_day <= 20170830\n" +
					"    ";


			Map<String, Object> obj = DBKfShopUtilsTemplate.findFirst(sql);

			String money = obj.get("money")+"";

			if(money==null || "null".equalsIgnoreCase(money)){
				money="0.0";
			}

			double balance = Double.valueOf(money);

			total+=balance;
			log.info("agencyId,"+agencyId+", total="+total+",money="+money+",idx="+idx);

		}

	}

}
