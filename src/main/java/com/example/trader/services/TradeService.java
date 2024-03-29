package com.example.trader.services;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trader.models.Trade;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")

/**
 * Service Class which contains all the functions to operate on the Trade model.
 * @author Sougata Dafader
 *
 */

public class TradeService {

	/**
	 * ArrayList to store all the trades in-memory
	 */
	List<Trade> trades = new ArrayList<Trade>();

	/**
	 * boolean value signifying if the background service is running or not.
	 */
	boolean isRunning = false;
	/**
	 * Stores the total number of trades
	 */
	long totalTrades = 0;

	/**
	 * Stores the total value of all the trades
	 */
	double totalValue = 0;

	/**
	 * Stores the total quantity of all the trades.
	 */
	long totalQuantity = 0;

	/**
	 * HashMap to store all the trades in key,value format. key =>
	 * Side,Security,FundName; value => number of Occurrences
	 */
	Map<String, Integer> map = new HashMap<String, Integer>();

	/**
	 * HashMap to store trades with more than one occurrence whose side, security
	 * and fundName are same.
	 */
	Map<String, Integer> combinableMap = new HashMap<String, Integer>();

	int listSize = trades.size();

	/**
	 * Populates both the HashMaps and runs the scheduler only if the arrayList
	 *  size has increased. The algorithm runs in a defined interval of one 
	 *  minute.
	 */

	public void runScheduler() {

		int MINUTES = 1;
		int SECONDS = 60;
		int MILLISECONDS = 1000;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			int start = 0;

			@Override
			public void run() {
				if (trades.size() > start) {
					Iterator<Trade> tradeItr = trades.listIterator(start);
					System.out.println("--------- START POSITION:" + start + 
							"-------------------\n\n");

					while (tradeItr.hasNext()) {
						Trade tr = tradeItr.next();
						String hashValue = tr.getSide() + "," + tr.getSecurity()
						+ "," + tr.getFundName();
						Integer count = map.get(hashValue);
						map.put(hashValue, (count == null) ? 1 : count + 1);

						if (map.get(hashValue) > 1) {
							combinableMap.put(hashValue, map.get(hashValue));
						}
					}
					String time = DateFormat.getDateTimeInstance()
							.format(System.currentTimeMillis());
					System.out.println("Last Updated " + time + "\n\n");
					start = trades.size();
				}
			}

		}, 0, MILLISECONDS * SECONDS * MINUTES);

	}

	/**
	 * Utility function to print a Map.
	 * @param map2 Map to iterate over. Key is String type and Value is Integer 
	 * type
	 */
	public void printMap(Map<String, Integer> map2) {
		for (Map.Entry<String, Integer> entry : map2.entrySet()) {
			System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
		}
	}

	/**
	 * Starts the scheduler only if it is not running.
	 * @return message to indicate Scheduler is running.
	 */
	@GetMapping("/")
	public String invokeScheduler() {
		if (!isRunning) {
			runScheduler();
			isRunning = true;
		}
		return "Scheduler Started";
	}

	/**
	 * 
	 * @return total quantity, total number of orders, average price and all 
	 * the combinable orders for all the trades
	 */
	@GetMapping("/api/summary")
	public String findSummary() {
		String jsonString = "";
		if (totalTrades > 0) {

			try {
				jsonString = new JSONObject()
						.put("totalQuantity", totalQuantity)
						.put("totalNumberOfOrders", totalTrades)
						.put("averageprice", (totalValue / totalTrades))
						.put("combinableOrders", new JSONObject(combinableMap))
						.toString();
			} catch (JSONException e) {
				e.printStackTrace();
				return "Failure";
			}
		}

		return jsonString;
	}
	/**
	 * 
	 * @param security The security/ ticker symbol of the trade.
	 * @return total quantity, total number of orders, average price of all the trades 
	 * with that security
	 */
	@GetMapping("/api/summary/security/{securityName}")
	public String findSummaryBySecurity(@PathVariable("securityName") String security) {
		String jsonString = "";

		Predicate<Trade> hasSameSecurity = 
				trade -> (trade.getSecurity()).equals(security.toUpperCase());

		if (totalTrades > 0) {
			List<Trade> result = trades.stream().filter(hasSameSecurity).collect(Collectors.toList());
			long tradesSize = result.size();
			if (tradesSize > 0) {
				Iterator<Trade> tradeItr = result.iterator();
				long quantity = 0;
				long value = 0;
				while (tradeItr.hasNext()) {
					Trade t = tradeItr.next();
					quantity += t.getQuantity();
					value += t.getPrice();
				}

				try {
					jsonString = new JSONObject().put("total quantity", quantity)
							.put("total number of orders", tradesSize).put("average Price", (value / tradesSize))
							.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					return "Failure";
				}
			}

		}
		return jsonString;
	}

	/**
	 * 
	 * @param fund The fund name of the Trade
	 * @return total quantity, total number of orders, average price of all the trades 
	 * with that fund name
	 */
	@GetMapping("/api/summary/fund/{fundName}")
	public String findSummaryByFund(@PathVariable("fundName") String fund) {
		String jsonString = "";
		Predicate<Trade> hasSameFundName = trade -> (trade.getFundName()).equals(fund.toUpperCase());
		if (totalTrades > 0) {
			List<Trade> result = trades.stream().filter(hasSameFundName).collect(Collectors.toList());
			long tradesSize = result.size();
			if (tradesSize > 0) {
				Iterator<Trade> tradeItr = result.iterator();
				long quantity = 0;
				long value = 0;
				while (tradeItr.hasNext()) {
					Trade t = tradeItr.next();
					quantity += t.getQuantity();
					value += t.getPrice();
				}

				try {
					jsonString = new JSONObject().put("total quantity", quantity)
							.put("total number of orders", tradesSize).put("average Price", (value / tradesSize))
							.toString();
				} catch (JSONException e) {
					e.printStackTrace();
					return "Failure";
				}
			}

		}
		return jsonString;
	}

	/**
	 * finds all the trades that were posted
	 * @return List of all the trades in the memory
	 */
	@GetMapping("/api/trades")
	public List<Trade> findAllTrades() {
		if (trades.isEmpty()) {
			return new ArrayList<Trade>();
		}

		return trades;
	}

	/**
	 * Finds all the trades that have the same security.
	 * @param security   The security/ ticker symbol of the trade.
	 * @return All the trades with same security name.
	 */
	@GetMapping("/api/trades/security/{securityName}")
	public List<Trade> findBySecurity(@PathVariable("securityName") String security) {
		List<Trade> result = trades.stream().filter(trade -> (trade.getSecurity())
				.equals(security.toUpperCase())).collect(Collectors.toList());
		if (trades.isEmpty()) {
			return new ArrayList<Trade>();
		}

		return result;
	}

	/**
	 * Finds all the trades which has the same fund name
	 * @param fund fund name of the trade.
	 * @return List of all the trades with same fund name
	 */
	@GetMapping("/api/trades/fund/{fundName}")
	public List<Trade> findByFund(@PathVariable("fundName") String fund) {
		List<Trade> result = trades.stream().filter(trade -> (trade.getFundName())
				.equals(fund.toUpperCase())).collect(Collectors.toList());
		if (trades.isEmpty()) {
			return new ArrayList<Trade>();
		}
		return result;
	}

	/**
	 * Adds the trade to the list of trades in memory and calculates the totalTrades,
	 *  totalValue, and total Quantity of all the trades
	 * 
	 * @param trade Trade that is to be added in the memory
	 * @return the trade that was 
	 */
	@PostMapping("/api/trade")
	public Trade createTrade(@RequestBody Trade trade) {
		trade.setId(System.currentTimeMillis());
		trade.setFundName(trade.getFundName().toUpperCase());
		trade.setSecurity(trade.getSecurity().toUpperCase());
		trade.setSide(trade.getSide().toUpperCase());
		trades.add(trade);
		totalTrades += 1;
		totalValue += trade.getPrice();
		totalQuantity += trade.getQuantity();
		return trade;
	}

}
