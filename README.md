## 👀 Overview
This backend system is a fintech application that retrieves and stores information about companies and their share prices. The system fetches data from a mocked data provider and refreshes the data at least every 20 seconds to show the current real share price.

## 📖 Architecture
The system follows a hexagonal architecture, with the application layer interacting with the external system through a data provider interface. This allows the application layer to be isolated from the implementation details of the external system and makes it easier to test and maintain the code.

## 🤖 Technologies
- Kotlin: The backend system is implemented using Kotlin, a modern programming language that is interoperable with Java and runs on the Java Virtual Machine.
- Spring Boot: The system uses Spring Boot as the web framework to handle HTTP requests and responses.

## ☝️ How to run this project
1. Move to the project directory: `cd ninety-nine-challenge`
2. Build the project for the first time: `./gradlew build`
3. To just run the project execute: `./gradlew run`

## 🎯 API Calls
- `GET /api/v1/health` - Health check
- `GET /stocks` - Get a list of stocks
- `GET /stocks/{name}` - Get a stock by name; ex: `GET /stocks/AAPL`
- `GET /companies/{name}/time-series?type={hourly|daily|weekly}` - Returns the time series of the share price of the stock with the given name for the specified time period (hourly, daily, or weekly).

## ⚠ Comments
This is my first time using Kotlin, so it is possible that the project has bugs. I would appreciate any feedback on how to improve the code. 

Thank you!