package com.fshows.commons.yq;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.common.yq
 * 功能：
 * 时间：2016-10-14
 * 作者：呱牛
 */
public class Packets {

    private byte[] head;
    private byte[] body;

    private int len;

    public byte[] getHead() {
        return head;
    }

    public void setHead(byte[] head) {
        this.head = head;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
