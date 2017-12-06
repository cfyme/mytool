package com.fshows.commons.liquidation;

import com.fshows.commons.dao.DBLiquidationUtilsTemplate;
import com.fshows.commons.util.MyFileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * group=143
 */
public class NetTradeFTP {
    private static final Log log = LogFactory.getLog(NetTradeFTP.class);
    static int pageSize = 1000;


    public static void execute() {
        log.error("=========start11111===========");
        update();
        log.error("=========end11111===========");

    }

    public static void update() {

        List<String> storeList = MyFileUtil.getListFromFile("/home/caofy/store.txt");

        log.info("storeList.size = " + storeList.size());

        for (String storeId : storeList) {
            buildTradeTxt(storeId);
        }
        log.info("storeList buile end ");


    }

    private static void buildTradeTxt(String storeId) {
        String sql = "select * from lp_net_bank_trade_record where uid='" + storeId + "'";
        List<Map<String, Object>> list = DBLiquidationUtilsTemplate.find(sql);


        for (Map<String, Object> map : list) {
            StringBuffer buffer = new StringBuffer();
            String bank_name = map.get("bank_name") + "";
            String person_name = map.get("person_name") + "";
            String cert_no = map.get("cert_no") + "";
            String card_no = map.get("card_no") + "";
            String uid = map.get("uid") + "";
            String smid = map.get("smid") + "";
            String company_name = map.get("company_name") + "";
            String shop_name = map.get("shop_name") + "";
            String industry = map.get("industry") + "";
            String city = map.get("city") + "";
            String address = map.get("address") + "";
            String sign_date = map.get("sign_date") + "";

            address = address.replaceAll("\\|", "");
            company_name = company_name.replaceAll("\\|", "");
            shop_name = shop_name.replaceAll("\\|", "");
            bank_name = bank_name.replaceAll("\\|", "");
            person_name = person_name.replaceAll("\\|", "");


            if (sign_date.length() < 15) {
                sign_date = sign_date.substring(0, 10) + " 00:00:00";
                System.out.println("1=" + sign_date);
            } else {
                sign_date = sign_date.substring(0, 19);
                System.out.println("2=" + sign_date);
            }

            String prevent_spam_flag = map.get("prevent_spam_flag") + "";
            String vintage = map.get("vintage") + "";
            String alipay_total_amt = map.get("alipay_total_amt") + "";
            String alipay_total_cnt = map.get("alipay_total_cnt") + "";
            String alipay_debit_amt = map.get("alipay_debit_amt") + "";
            String alipay_debit_cnt = map.get("alipay_debit_cnt") + "";
            String alipay_credit_amt = map.get("alipay_credit_amt") + "";
            String alipay_credit_cnt = map.get("alipay_credit_cnt") + "";
            String wx_total_amt = map.get("wx_total_amt") + "";
            String wx_total_cnt = map.get("wx_total_cnt") + "";
            String wx_debit_amt = map.get("wx_debit_amt") + "";
            String wx_debit_cnt = map.get("wx_debit_cnt") + "";
            String wx_credit_amt = map.get("wx_credit_amt") + "";
            String wx_credit_cnt = map.get("wx_credit_cnt") + "";
            String other_amt = map.get("other_amt") + "";
            String other_cnt = map.get("other_cnt") + "";
            String debit_card_amt = map.get("debit_card_amt") + "";
            String debit_card_cnt = map.get("debit_card_cnt") + "";
            String credit_card_amt = map.get("credit_card_amt") + "";
            String credit_card_cnt = map.get("credit_card_cnt") + "";
            String cash_amt = map.get("cash_amt") + "";
            String cash_cnt = map.get("cash_cnt") + "";
            String total_amt = map.get("total_amt") + "";
            String total_cnt = map.get("total_cnt") + "";
            String fraud_amt = map.get("fraud_amt") + "";
            String fraud_cnt = map.get("fraud_cnt") + "";
            String gmt_update = map.get("gmt_update") + "";

            if (gmt_update != null && gmt_update.length() > 19) {
                gmt_update = gmt_update.substring(0, 19);
            }

            buffer.append(bank_name).append("|").append(person_name).append("|").append(cert_no).append("|").append(card_no).append("|");
            buffer.append(uid).append("|").append(smid).append("|").append(company_name).append("|").append(shop_name).append("|");
            buffer.append(industry).append("|").append(city).append("|").append(address).append("|").append(sign_date).append("|");
            buffer.append(prevent_spam_flag).append("|").append(vintage).append("|").append(alipay_total_amt).append("|").append(alipay_total_cnt).append("|");
            buffer.append(alipay_debit_amt).append("|").append(alipay_debit_cnt).append("|").append(alipay_credit_amt).append("|").append(alipay_credit_cnt).append("|");
            buffer.append(wx_total_amt).append("|").append(wx_total_cnt).append("|").append(wx_debit_amt).append("|").append(wx_debit_cnt).append("|");
            buffer.append(wx_credit_amt).append("|").append(wx_credit_cnt).append("|").append(other_amt).append("|").append(other_cnt).append("|");
            buffer.append(debit_card_amt).append("|").append(debit_card_cnt).append("|").append(credit_card_amt).append("|").append(credit_card_cnt).append("|");
            buffer.append(cash_amt).append("|").append(cash_cnt).append("|").append(total_amt).append("|").append(total_cnt).append("|");
            buffer.append(fraud_amt).append("|").append(fraud_cnt).append("|").append(gmt_update);


            String str = buffer.toString().replaceAll("null", "");
            str = str.replaceAll("\\,", "");

            System.out.println("address=" + address);
            System.out.println(sign_date);

            writeTxt(str);


        }


    }


    private static void writeTxt(String str) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("/home/caofy/trade.txt", true));
            bw.write(str);
            bw.newLine();
        } catch (Exception e) {
            log.info("writeTxt error,str=" + str, e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(1);
        String address = "中华人民|共和国|3434";
        address = address.replaceAll("\\|", "");
        System.out.println(address);
    }


}
