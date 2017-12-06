package com.fshows.commons.yq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.model.yq
 * 功能：
 * 时间：2016-10-17
 * 作者：呱牛
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KHKF05 implements Serializable {

    @XmlElement(name = "AcctNo")
    private String AcctNo;
    @XmlElement(name = "Date")
    private String date;

    @XmlElement(name = "FileType")
    private String FileType;

    public KHKF05() {
    }

    public KHKF05(String acctNo, String date, String FileType) {
        this.AcctNo = acctNo;
        this.date = date;
        this.FileType=FileType;
    }

    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }
}
