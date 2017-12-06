package com.fshows.commons.df;

import com.fshows.commons.util.Consts;
import com.fshows.commons.util.StringUtil;
import com.fshows.commons.util.XmlStream;
import com.fshows.commons.yq.*;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caofangyi on 2017/8/18.
 */
public class KfshopQuery {

    private static final Log logger = LogFactory.getLog(KfshopQuery.class);

    static String ip = "120.26.100.13";
    static int port = 7072;
    static  String acctNo = "11014609385000";
    static  String yqdm = "00901020000000011000";

    public static void execute(String b, String c) throws Exception  {
        logger.error("=========start========yqdm==="+yqdm);
        logger.error("=========acctNo==========="+acctNo);
        logger.error("=========b==========="+b);
        logger.error("=========c==========="+c);
        dfQuery(b);
        logger.error("=========end===========");

    }




    public static void dfQuery(String trade)  throws  Exception{
        if(StringUtil.isBlank(trade)){
            trade = "8043431708226116868080";
        }
        KHKF04 khkf04 = new KHKF04(
                acctNo,
                null,
                trade
        );


        String xml = XmlStream.toXML(khkf04, Consts.GBK);
//            xml = xml.replace(" standalone=\"yes\"", "");


        logger.info("=====xml===="+xml);
        logger.info("111test11");

        String packets = YQUtil.asemblyPackets(yqdm, "KHKF04",  createThirdLogNo(), xml);
        logger.info("22test222");

        logger.info("单笔付款结果查询 KHKF04 >> 生成报文 >> packets = "+packets);

        //发起请求
        Packets packetsRP = YQUtil.send2server(ip, port, packets);
        HashMap<String, String> mapRet = Maps.newHashMap();
        if (packetsRP == null) {
            logger.info("单笔付款结果查询 KHKF04 >> 返回报文为空");
        }

        byte[] headRP = packetsRP.getHead();
        int bodyRpLen = packetsRP.getLen();
        byte[] bodyRP = packetsRP.getBody();
        StringBuffer rcvMsgH = new StringBuffer();
        StringBuffer rcvMsgB = new StringBuffer();
        if (headRP != null) {
            rcvMsgH.append(new String(headRP, YQUtil.CHARSET_DEFAULT));
            logger.info("单笔付款结果查询 KHKF04 >> 返回报文 >> head >> head = "+rcvMsgH.toString());
            mapRet = YQUtil.parsingPacketsHeaderString(rcvMsgH.toString());
        }
        if (bodyRpLen > 0 && bodyRP != null) {
            rcvMsgB.append(new String(bodyRP, YQUtil.CHARSET_DEFAULT));
            logger.info("单笔付款结果查询 KHKF04 >> 返回报文 >> body >> body = "+ rcvMsgB.toString());
            Map<String, String> mapB = XmlStream.xml2map(rcvMsgB.toString());
            mapRet.put("OrderNumber", mapB.get("OrderNumber"));
            mapRet.put("BussFlowNo", mapB.get("BussFlowNo"));
            mapRet.put("TranFlowNo", mapB.get("TranFlowNo"));
            mapRet.put("Status", mapB.get("Status"));
            mapRet.put("RetCode", mapB.get("RetCode"));
            mapRet.put("RetMsg", mapB.get("RetMsg"));
            mapRet.put("SettleDate", mapB.get("SettleDate"));
            mapRet.put("InAcctNo", mapB.get("InAcctNo"));
            mapRet.put("InAcctName", mapB.get("InAcctName"));
            mapRet.put("CcyCode", mapB.get("CcyCode"));
            mapRet.put("TranAmount", mapB.get("TranAmount"));
            mapRet.put("Mobile", mapB.get("Mobile"));
            mapRet.put("Remark", mapB.get("Remark"));
            logger.info("====mapRet===="+mapRet);

            logger.info("======================");

            logger.info("====mapB===="+mapB);
        }

    }

    public static String createThirdLogNo() {
        StringBuffer sb = new StringBuffer();
        // 获得时间字符串
        sb.append(DateTime.now().toString("yyyyMMddHHmmss"));
        // 获得6位随机数字
        sb.append(RandomStringUtils.randomNumeric(6));

        return sb.toString();
    }

}
