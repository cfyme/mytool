package com.fshows.commons.sftp;

import com.jcraft.jsch.ChannelSftp;

import java.util.HashMap;
import java.util.Map;

public class TradeToAlipay {

    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    public static void execute(){
        System.out.println("start sftp");
        try {
            TradeToAlipay test = new TradeToAlipay();
            Map<String, String> sftpDetails = new HashMap<String, String>();
            // 设置主机ip，端口，用户名，密码
            sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "dwsftp.alipay.com");
            sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "SJSNSJLRPAWZ0808");
            sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "DRkeO0AnZmzZNFNc");
            sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");

            String src = "/home/caofy/ods_out_ext_pawz_trade_statis_dd_20170930.txt"; // 本地文件名
            String dst = "/upload/20170930/"; // 目标文件名

            SFTPChannel channel = test.getSFTPChannel();
            ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);

             chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

            src = "/home/caofy/ods_out_ext_pawz_trade_statis_dd_20170930.txt.ok"; // 本地文件名
            chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

            chSftp.quit();
            channel.closeChannel();
            System.out.println("end");
        }catch (Exception e){
            e.printStackTrace();;
        }
       
    }
}