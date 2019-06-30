package com.example.trader.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trader.models.Trade;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true" , allowedHeaders = "*")
public class TradeService {
	List<Trade> trades = new ArrayList<Trade>();
	boolean isRunning =false;

	public void runScheduler() {
		int MINUTES = 1;
		int SECONDS = 60;
		int MILLISECONDS =1000;
		Timer timer = new Timer();
		 timer.schedule(new TimerTask() {
			/*	
			 * DEFINITION: schedule(TimerTask task,long delay,long period)
			 * Overriding the run function of the timer to do custom work
			 */
		    @Override
		    public void run() {
		    	String time = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()); 
		    	System.out.println("Last Updated "+time);
		    }
		 }, 0, MILLISECONDS * SECONDS * MINUTES);
	}
	
	@GetMapping("/api/trades")
	public List<Trade> findAllTrades(){
		System.out.println(isRunning);
		if(!isRunning) {
			runScheduler();
			isRunning = true;
		}
		
		if(trades.isEmpty())
		{
			return new ArrayList<Trade>();
		}
		
		return trades;	
	}
	
	@GetMapping("/api/summary/security/{securityName}")
	public List<Trade> findBySecurity(@PathVariable("securityName") String security){
		List<Trade> result = trades.stream()
				.filter(trade -> (trade.getSecurity()).equals(security.toUpperCase()))
				.collect(Collectors.toList());
		if(trades.isEmpty())
		{
			return new ArrayList<Trade>();
		}
		
		return result;	
	}
	
	@GetMapping("/api/summary/fund/{fundName}")
	public List<Trade> findByFund(@PathVariable("fundName") String fund){
		List<Trade> result = trades.stream()
				.filter(trade -> (trade.getFundName()).equals(fund.toUpperCase()))
				.collect(Collectors.toList());
		if(trades.isEmpty())
		{
			return new ArrayList<Trade>();
		}
		
		return result;	
	}
	
	@PostMapping("/api/trade")
	public Trade createTrade(@RequestBody Trade trade)
	{
		trade.setId(System.currentTimeMillis());
		trades.add(trade);
		return trade;
	}

}
