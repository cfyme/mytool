package com.fshows.commons.util;

import java.math.BigDecimal;

/**
 * Created by caofangyi on 2017/7/19.
 */
public class RefundTest {

    public static void main(String[] args){
         test1();
         test2();
    }

    private static void test1() {
        BigDecimal totalAmount = new BigDecimal(2);
        BigDecimal bankCommissionRate = new BigDecimal(0.00200);
        BigDecimal bankCommissionFee = BigDecimalUtil.multiply(totalAmount, bankCommissionRate);
      //  System.out.println(bankCommissionFee);
        System.out.println(bankCommissionFee.setScale(2, BigDecimal.ROUND_HALF_UP));  // 2位小数，第3位四舍五入

    }

    private static void test2() {
        BigDecimal totalAmount = new BigDecimal(50);
        BigDecimal bankCommissionRate = new BigDecimal(0.00200);
        BigDecimal bankCommissionFee = BigDecimalUtil.multiply(totalAmount, bankCommissionRate);
       // System.out.println(bankCommissionFee);
        System.out.println(bankCommissionFee.setScale(2, BigDecimal.ROUND_HALF_UP));  // 2位小数，第3位四舍五入
    }

}
