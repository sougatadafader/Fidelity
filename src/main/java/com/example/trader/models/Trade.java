package com.example.trader.models;

import java.util.ArrayList;
import java.util.List;

public class Trade {
	private long id;
	private String side;
	private String security;
	private String fundName;
	private long quantity;
	private long price;
	private List<Trade> trades = new ArrayList<Trade>();
	
	
	
	
	
	public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public String getSide() {
		return side;
	}





	public void setSide(String side) {
		this.side = side;
	}





	public String getSecurity() {
		return security;
	}





	public void setSecurity(String security) {
		this.security = security;
	}





	public String getFundName() {
		return fundName;
	}





	public void setFundName(String fundName) {
		this.fundName = fundName;
	}





	public long getQuantity() {
		return quantity;
	}





	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}





	public long getPrice() {
		return price;
	}





	public void setPrice(long price) {
		this.price = price;
	}





	public List<Trade> getTrades(){
		return trades;
	}
}
