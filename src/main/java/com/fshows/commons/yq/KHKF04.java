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
public class KHKF04 implements Serializable {

    @XmlElement(name = "AcctNo")
    private String AcctNo;
    @XmlElement(name = "OrderNumber")
    private String OrderNumber;
    @XmlElement(name = "BussFlowNo")
    private String BussFlowNo;

    public KHKF04() {
    }

    public KHKF04(String acctNo, String orderNumber, String bussFlowNo) {
        AcctNo = acctNo;
        OrderNumber = orderNumber;
        BussFlowNo = bussFlowNo;
    }

    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getBussFlowNo() {
        return BussFlowNo;
    }

    public void setBussFlowNo(String bussFlowNo) {
        BussFlowNo = bussFlowNo;
    }
}
