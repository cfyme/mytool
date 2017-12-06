package com.fshows.commons;

import com.fshows.commons.df.KfshopDf;
import com.fshows.commons.df.KfshopDfTest;
import com.fshows.commons.df.KfshopQuery;
import com.fshows.commons.df.WithdrawTest;
import com.fshows.commons.kfshop.AgencyBalanceDeal;
import com.fshows.commons.kfshop.BalanceDeal;
import com.fshows.commons.kfshop.BalanceDealToday;
import com.fshows.commons.kfshop.BalanceUnionDealToday;
import com.fshows.commons.liquidation.*;
import com.fshows.commons.sftp.SFTPDownload;
import com.fshows.commons.sftp.SFTPToAlipay;
import com.fshows.commons.sftp.TradeToAlipay;

public class MainExecute {

	public static void main(String[] args) throws  Exception {
		String a = "", b = "", c = "", d = "";
		try {
			a = args[0];
		} catch (Exception e) {
		}
		try {
			b = args[1];
		} catch (Exception e) {
		}
		try {
			c = args[2];
		} catch (Exception e) {
		}
		try {
			d = args[3];
		} catch (Exception e) {
		}

		System.out.println(String.format("a=%s,b=%s,c=%s,d=%s", a, b, c, d));


		if ("Push221".equals(a)) {
			AlipayConfigQuery obj = new AlipayConfigQuery();
			obj.execute();
		}
		if ("LpDiffResult".equals(a)) {
			LpDiffResult obj = new LpDiffResult();
			obj.execute(Integer.valueOf(b));
		}
		if ("BalanceDeal".equals(a)) {
			BalanceDeal obj = new BalanceDeal();
			obj.execute();
		}
		if ("AgencyBalanceDeal".equals(a)) {
			AgencyBalanceDeal obj = new AgencyBalanceDeal();
			obj.execute();
		}


		if ("BalanceDealToday".equals(a)) {
			BalanceDealToday obj = new BalanceDealToday();
			obj.execute();
		}

		if ("KfshopDf".equals(a)) {
			KfshopDf obj = new KfshopDf();
			obj.execute();
		}
		if ("KfshopDfTest".equals(a)) {
			KfshopDfTest obj = new KfshopDfTest();
			obj.execute(b, c);
		}
		if ("WithdrawTest".equals(a)) {
			WithdrawTest obj = new WithdrawTest();
			obj.execute(b);
		}
		if ("KfshopQuery".equals(a)) {
			KfshopQuery obj = new KfshopQuery();
			obj.execute(b,c);
		}

		if ("NetTradeUpdate".equals(a)) {
			NetTradeUpdate obj = new NetTradeUpdate();
			obj.execute();
		}

		if ("StoreInit".equals(a)) {
			StoreInit obj = new StoreInit();
			obj.execute();
		}

		if ("DML".equals(a)) {
			DML obj = new DML();
			obj.execute(b);
		}
		if ("NetTradeFTP".equals(a)) {
			NetTradeFTP obj = new NetTradeFTP();
			obj.execute();
		}
		if ("SFTPToAlipay".equals(a)) {
			SFTPToAlipay obj = new SFTPToAlipay();
			obj.execute();
		}
		if ("SFTPDownload".equals(a)) {
			SFTPDownload obj = new SFTPDownload();
			obj.execute();
		}
		if ("TradeToAlipay".equals(a)) {
			TradeToAlipay obj = new TradeToAlipay();
			obj.execute();
		}
		if ("BalanceUnionDealToday".equals(a)) {
			BalanceUnionDealToday obj = new BalanceUnionDealToday();
			obj.execute();
		}


	}
}
