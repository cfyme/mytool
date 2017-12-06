package com.fshows.commons.df;

import com.fshows.commons.util.Consts;
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
public class KfshopDf {

    private static final Log logger = LogFactory.getLog(KfshopDf.class);

    static String ip = "127.0.0.1";
    static int port = 7072;
    static  String acctNo = "11015798771005";
    static  String yqdm = "00901027000000021000";

    public static void execute() throws Exception  {
        logger.error("=========start===========");
       download();
        //query();
        logger.error("=========end===========");

    }


    public static void download()  throws  Exception{
        File03 obj = new File03();
       // obj.setFileName("CHK_KHKF01_11015798771005_20170817.txt");
        //obj.setRandomPwd("sLb18B0NV9FN62SA9UzvU041OGe3ng05");
        obj.setTradeSn(createThirdLogNo());
        obj.setFilePath("/home/kfshopapinew/files/upload");

        obj.setFileName("CHK_KHKF01_11015798771005_20170816.txt");
        obj.setRandomPwd("drMUbzMm2lsbAiJJyaOslDFdq3e5mIKz");

        String xml = XmlStream.toXML(obj, Consts.GBK);
        logger.info("=====xml===="+xml);
        logger.info("11111");

        String packets = YQUtil.asemblyPackets(yqdm, "FILE03",  createThirdLogNo(), xml);
        logger.info("22222");


        try {
            //发起请求
            Packets packetsRP = YQUtil.send2server(ip, port, packets);
            logger.info("==packageRet==" + packetsRP);
        }catch ( Exception e){
            logger.error("00000000000000");
            logger.error(e.getMessage(), e);
        }





    }


    public static void query()  throws  Exception{
        KHKF05 khkf05 = new KHKF05(acctNo, "20170816", "KHKF01");

        String xml = XmlStream.toXML(khkf05, Consts.GBK);
        logger.info("=====xml===="+xml);
        logger.info("11111");

        String packets = YQUtil.asemblyPackets(yqdm, "KHKF05",  createThirdLogNo(), xml);
        logger.info("22222");

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
            mapRet = YQUtil.parsingPacketsHeaderString(rcvMsgH.toString());
            logger.info("3333333333333333"+mapRet);
        }
        if (bodyRpLen > 0 && bodyRP != null) {
            rcvMsgB.append(new String(bodyRP, YQUtil.CHARSET_DEFAULT));
            logger.info("4444444444444444 = "+ rcvMsgB.toString());
            Map<String, String> mapB = XmlStream.xml2map(rcvMsgB.toString());
            mapRet.put("TranFlowNo", mapB.get("TranFlowNo"));
            logger.info("===9999999999=mapRet===="+mapB);
        }


    }



    public static void df()  throws  Exception{
        KHKF04 khkf04 = new KHKF04(
                acctNo,
                null,
                "8043431708187206624279"
        );



        String xml = XmlStream.toXML(khkf04, Consts.GBK);
//            xml = xml.replace(" standalone=\"yes\"", "");


        logger.info("=====xml===="+xml);
        logger.info("11111");

        String packets = YQUtil.asemblyPackets(yqdm, "KHKF04",  createThirdLogNo(), xml);
        logger.info("22222");

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
