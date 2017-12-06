package com.fshows.commons.liquidation;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * group=143
 */
public class LpDiffResult {
    private static final Log log = LogFactory.getLog(LpDiffResult.class);
    static int pageSize = 1000;


    public static void execute(int day) {
        log.error("=========start===========");
        add(day);
        log.error("=========end===========");

    }

    public static void add(int day) {
        String sql1 = "insert into lp_diff_result (pay_platform_type,create_time,create_day) values (?,?,?)";
        String sql2 = "insert into lp_diff_result (pay_platform_type,create_time,create_day) values (?,?,?)";

        DBLiquidationUtilsTemplate.update(sql1, new Object[]{1, System.currentTimeMillis(), day});
        DBLiquidationUtilsTemplate.update(sql1, new Object[]{2, System.currentTimeMillis(), day});
    }


}
