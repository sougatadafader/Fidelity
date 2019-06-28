package com.example.trader.services;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.trader.models.Trade;

@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class TradeService {
	
	List<Trade> trades = new ArrayList<Trade>();
	
	@GetMapping("/api/trades")
	public List<Trade> findAllTrades(){
		if(trades.isEmpty())
		{
			return new ArrayList<Trade>();
		}
		
		return trades;	
	}
	
	@PostMapping("/api/trade")
	public Trade createTrade(@RequestBody Trade trade)
	{
		trade.setId(System.currentTimeMillis());
		trades.add(trade);
		return trade;
	}
	
	

}
