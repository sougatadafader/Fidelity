package com.example.trader.models;

/**
 * Represents a trade 
 * @author Sougata Dafader
 *
 */

public class Trade {
	/**
	 *  unique id of the Trade.
	 */
	private long id;
	/**
	 *  type of trade either BUY or SELL.
	 */
	private String side;
	/**
	 *  ticker symbol of the company.
	 */
	private String security;
	/**
	 *  name of the Investment fund.
	 */
	private String fundName;
	/**
	 *  number of shares involved in the Trade.
	 */
	private long quantity;
	/**
	 *   price of each share of that Security.
	 */
	private long price;
	
	/**
	 * 
	 * @return the Id of this Trade.
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Changes the Id of this Trade
	 * @param id This trade's new Id.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the side of this Trade (either BUY or SELL).
	 */
	public String getSide() {
		return side;
	}

	/**
	 * Changes the side of this Trade.
	 * @param side This trade's new Side.
	 */
	public void setSide(String side) {
		this.side = side;
	}

	/**
	 * 
	 * @return the security of this Trade.
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * Changes the security of this Trade.
	 * @param security This trade's new Security.
	 */
	public void setSecurity(String security) {
		this.security = security;
	}

	/**
	 * 
	 * @return the fund name of this Trade.
	 */
	public String getFundName() {
		return fundName;
	}

	/**
	 * Changes the fund name of this Trade.
	 * @param fundName This trade's new fund name.
	 */
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	/**
	 * 
	 * @return the quantity of this Trade. 
	 */

	public long getQuantity() {
		return quantity;
	}

	/**
	 * Changes the quantity of this Trade.
	 * @param quantity This trade's new quantity.
	 */
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	/**
	 * 
	 * @return the price of each share
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Changes the price of each share involved in this Trade.
	 * @param price The price of each share for this trade.
	 */
	public void setPrice(long price) {
		this.price = price;
	}
}
