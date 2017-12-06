package com.fshows.commons.liquidation;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * group=143
 */
public class DML {
    private static final Log log = LogFactory.getLog(DML.class);
    static int pageSize = 1000;


    public static void execute(String a) {
        log.error("=========start==========="+a);
        DBLiquidationUtilsTemplate.update(a);
        log.error("=========end===========");

    }



}
