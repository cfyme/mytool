package com.fshows.commons.liquidation;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import com.fshows.commons.util.MyFileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * group=143
 */
public class StoreInit {
    private static final Log log = LogFactory.getLog(StoreInit.class);
    static int pageSize = 1000;


    public static void execute() {
        log.error("=========start===========");
        update();
        log.error("=========end===========");

    }

    public static void update() {

        List<String> list = MyFileUtil.getListFromFile("/tmp/store.txt");

        log.info("list.size==" + list.size());
        DBLiquidationUtilsTemplate.update("delete from lp_net_bank_store where id>=100317");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 1;
        StringBuffer buffer = new StringBuffer("insert into lp_net_bank_store (store_id,status) values");

        for (String storeId : list) {
            long startTime = System.currentTimeMillis() ;
            buffer.append("(");
            buffer.append("'" + storeId + "','1'");
            buffer.append("),");
            if (i % 100 == 0) {
                buffer.deleteCharAt(buffer.length() - 1);
                log.info("sql=" + buffer.toString());
                DBLiquidationUtilsTemplate.update(buffer.toString());
                long end = System.currentTimeMillis() - startTime;
                log.info("costTime=" + end + ", i=" + i);
                buffer = new StringBuffer("insert into lp_net_bank_store (store_id,status) values");
            }
            i++;

        }

        buffer.deleteCharAt(buffer.length() - 1);
        log.info("sql=" + buffer.toString());
        DBLiquidationUtilsTemplate.update(buffer.toString());
    }

}
