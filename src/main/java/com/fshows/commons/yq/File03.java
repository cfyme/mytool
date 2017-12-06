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
public class File03 implements Serializable {

    @XmlElement(name = "FilePath")
    private String FilePath;

    @XmlElement(name = "FileName")
    private String FileName;

    @XmlElement(name = "TradeSn")
    private String TradeSn;

    @XmlElement(name = "RandomPwd")
    private String RandomPwd;


    public File03() {
    }


    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getTradeSn() {
        return TradeSn;
    }

    public void setTradeSn(String tradeSn) {
        TradeSn = tradeSn;
    }

    public String getRandomPwd() {
        return RandomPwd;
    }

    public void setRandomPwd(String randomPwd) {
        RandomPwd = randomPwd;
    }
}
