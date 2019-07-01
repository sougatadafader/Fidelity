# Fidelity 
Java Assignment for Fidelity High Income group

## ABOUT

**a Java REST service that will consume a collection of trade orders, and perform some asynchronous
order summary analysis on them.**

- [x] API to add trades (POST) 
- [x] API to fetch summary of all trades (GET)
- [x] API to fetch summary based on specific security (GET)
- [x] API to fetch summary based on specific fund (GET)
- [x] order summary analysis done asynchronously, at a **pre-defined interval of 1 minute.**


### Extra endpoints provided
 - [x] API to fetch all the trades (GET)
 - [x] API to fetch tades based on specific security (GET)
 - [x] API to fetch tades based on specific fund (GET)

# Getting started

## Definition of an Order

An order contains the following fields which has to be sent during a ```POST``` call.
   * order id
   * side (buy or sell)
   * security
   * fund name
   * quantity
   * price

## HEROKU URL
 **https://fid-trade.herokuapp.com/**

## API Documentation
The REST service should provide a number of endpoints, as follows:

**POST** ```api/trade``` 
 <br /> 
@required body (example) : 
``` { "side":"BUY", "security":"AAPL", "fundName":"MAG", "quantity":2000, "price":100}```

>consume a trade order which will be stored in memory (not in a database)
 - - - -
 
**GET** ```api/summary``` 
>returns total number of orders, with the total quantity and average price. It should also return
the total number of combinable orders
 - - - -

**GET** ```api/summary/security/{securityName}```
<br /> 
@required path variable (example) :
``` AAPL```
>returns total number of orders, with the total quantity and average price of that specific security
 - - - -

**GET** ```api/summary/fund/{fundName}```
<br /> 
@required path variable (example) :
``` MAG```
>returns total number of orders, with the total quantity and average price of that specific fund
 - - - -

### Extra API endpoints

 
**GET** ```api/trades``` 
>returns list of all the trades
 - - - -

**GET** ```api/trades/security/{securityName}```
<br /> 
@required path variable (example) :
``` AAPL```
>returns list of all the trades for that specific security
 - - - -

**GET** ```api/trades/fund/{fundName}```
<br /> 
@required path variable (example) :
``` MAG```
>returns list of all the trades for that specific fund
 - - - -



## SETUP Documentation
### Trade Server Setup
---

*  Install STS or Eclipse for running the server

*  Open Terminal/ Command Prompt and type in the following command

   ```git clone https://github.com/sougatadafader/Fidelity.git```

*  Open the Fidelity inside the STS or Eclipse application

*  Find the port inside the application.properties file. Change the port based on your suitability.

   example: ```server.port = 9000```

*  Update the client services to point the updated port.

## Congratulations! :fireworks:


 If you successfully followed the instructions, you're now ready for taking off! :rocket:	



