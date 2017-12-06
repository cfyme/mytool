package com.fshows.commons.liquidation;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * group=143
 */
public class NetTradeUpdate {
    private static final Log log = LogFactory.getLog(NetTradeUpdate.class);
    static int pageSize = 1000;


    public static void execute() {
        log.error("=========start===========");
        update();
        log.error("=========end===========");

    }

    public static void update() {

        for(int i=50280;  i<=100316; i++){
            String sql = "delete from lp_net_bank_store  where id="+i;
            DBLiquidationUtilsTemplate.update(sql);
            log.info("sql="+sql);
        }

    }


}
