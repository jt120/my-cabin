package com.jt.test.service;

import java.util.Date;

/**
 * @className:Currency.java
 * @classDescription:
 * @author:xing.zhao
 * @createTime:2013-8-9 下午2:08:20
 */
public class Currency {

	private int id;
	private String main_cur;
	private String change_cur;
	private float  rate;
	private int uprole_price;
	private int uprole_tax;
	private Date update_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMain_cur() {
		return main_cur;
	}
	public void setMain_cur(String main_cur) {
		this.main_cur = main_cur;
	}
	public String getChange_cur() {
		return change_cur;
	}
	public void setChange_cur(String change_cur) {
		this.change_cur = change_cur;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getUprole_price() {
		return uprole_price;
	}
	public void setUprole_price(int uprole_price) {
		this.uprole_price = uprole_price;
	}
	public int getUprole_tax() {
		return uprole_tax;
	}
	public void setUprole_tax(int uprole_tax) {
		this.uprole_tax = uprole_tax;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
