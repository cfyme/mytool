package com.fshows.commons.yq;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.common.yq
 * 功能：
 * 时间：2016-10-17
 * 作者：呱牛
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KHKF03 implements Serializable {

    @XmlElement(name = "OrderNumber")
    private String OrderNumber;

    @XmlElement(name = "AcctNo")
    private String AcctNo;

    @XmlElement(name = "BusiType")
    private String BusiType;

    @XmlElement(name = "CorpId")
    private String CorpId;

    @XmlElement(name = "CcyCode")
    private String CcyCode;

    @XmlElement(name = "TranAmount")
    private BigDecimal TranAmount;

    @XmlElement(name = "InAcctNo")
    private String InAcctNo;

    @XmlElement(name = "InAcctName")
    private String InAcctName;

    @XmlElement(name = "InAcctBankName")
    private String InAcctBankName;

    @XmlElement(name = "InAcctBankNode")
    private String InAcctBankNode;

    @XmlElement(name = "Mobile")
    private String Mobile;

    @XmlElement(name = "Remark")
    private String Remark;

    public KHKF03() {
        super();
    }

    public KHKF03(String orderNumber,
                  String acctNo,
                  String busiType,
                  String corpId,
                  String ccyCode,
                  BigDecimal tranAmount,
                  String inAcctNo,
                  String inAcctName,
                  String inAcctBankName,
                  String inAcctBankNode,
                  String mobile,
                  String remark) {
        OrderNumber = orderNumber;
        AcctNo = acctNo;
        BusiType = busiType;
        CorpId = corpId;
        CcyCode = ccyCode;
        TranAmount = tranAmount;
        InAcctNo = inAcctNo;
        InAcctName = inAcctName;
        InAcctBankName = inAcctBankName;
        InAcctBankNode = inAcctBankNode;
        Mobile = mobile;
        Remark = remark;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getAcctNo() {
        return AcctNo;
    }

    public void setAcctNo(String acctNo) {
        AcctNo = acctNo;
    }

    public String getBusiType() {
        return BusiType;
    }

    public void setBusiType(String busiType) {
        BusiType = busiType;
    }

    public String getCorpId() {
        return CorpId;
    }

    public void setCorpId(String corpId) {
        CorpId = corpId;
    }

    public String getCcyCode() {
        return CcyCode;
    }

    public void setCcyCode(String ccyCode) {
        CcyCode = ccyCode;
    }

    public BigDecimal getTranAmount() {
        return TranAmount;
    }

    public void setTranAmount(BigDecimal tranAmount) {
        TranAmount = tranAmount;
    }

    public String getInAcctNo() {
        return InAcctNo;
    }

    public void setInAcctNo(String inAcctNo) {
        InAcctNo = inAcctNo;
    }

    public String getInAcctName() {
        return InAcctName;
    }

    public void setInAcctName(String inAcctName) {
        InAcctName = inAcctName;
    }

    public String getInAcctBankName() {
        return InAcctBankName;
    }

    public void setInAcctBankName(String inAcctBankName) {
        InAcctBankName = inAcctBankName;
    }

    public String getInAcctBankNode() {
        return InAcctBankNode;
    }

    public void setInAcctBankNode(String inAcctBankNode) {
        InAcctBankNode = inAcctBankNode;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KHKF03{");
        sb.append("OrderNumber='").append(OrderNumber).append('\'');
        sb.append(", AcctNo='").append(AcctNo).append('\'');
        sb.append(", BusiType='").append(BusiType).append('\'');
        sb.append(", CorpId='").append(CorpId).append('\'');
        sb.append(", CcyCode='").append(CcyCode).append('\'');
        sb.append(", TranAmount=").append(TranAmount);
        sb.append(", InAcctNo='").append(InAcctNo).append('\'');
        sb.append(", InAcctName='").append(InAcctName).append('\'');
        sb.append(", InAcctBankName='").append(InAcctBankName).append('\'');
        sb.append(", InAcctBankNode='").append(InAcctBankNode).append('\'');
        sb.append(", Mobile='").append(Mobile).append('\'');
        sb.append(", Remark='").append(Remark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
