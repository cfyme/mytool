package com.fshows.commons.sftp;

import com.jcraft.jsch.ChannelSftp;

import java.util.HashMap;
import java.util.Map;

public class SFTPToAlipay {

    public SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    public static void execute(){
        System.out.println("start sftp");
        try {
            SFTPToAlipay test = new SFTPToAlipay();
            Map<String, String> sftpDetails = new HashMap<String, String>();
            // 设置主机ip，端口，用户名，密码
            sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "dwsftp.alipay.com");
            sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "SJSNSJLRPAWZ0808");
            sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "DRkeO0AnZmzZNFNc");
            sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "22");

            String src = "/tmp/ods_out_ext_pawz_cust_info_dd_20170831.txt"; // 本地文件名
            String dst = "/upload/20170831/"; // 目标文件名

            SFTPChannel channel = test.getSFTPChannel();
            ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);

             chSftp.put(src, dst, ChannelSftp.OVERWRITE); // 代码段2

            // chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 代码段3

           // chSftp.mkdir("/upload/20170831");
            chSftp.quit();
            channel.closeChannel();
            System.out.println("end");
        }catch (Exception e){
            e.printStackTrace();;
        }
       
    }
}