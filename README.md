# Welcome to the Bitpanda Android code challenge

## Objective:
Develop an Android application that displays a list of the best/worst performing crypto coins in the last 24 hours. You can use the provided template to kick start you implementation or start from scratch.

## Requirements:
* Display a list of the top 10 crypto coins based on price change percentage over the last 24 hours.
* Provide a way to switch between the 10 best and 10 worst performing crypto coins
* Each list item should contain the following asset info:
    * Name
    * Symbol
    * Change % (format: xx.xx%)
    * Price in Euro
* Provide an option to refresh the list using fresh data
* Use Jetpack Compose for the UI implementation

## Resources:
Use the CoinCap API 3.0 to implement the task: https://pro.coincap.io/api-docs
* Fetching all assets: `GET https://rest.coincap.io/v3/assets`
* Converting price to Euro: `GET https://rest.coincap.io/v3/rates` or `GET https://rest.coincap.io/v3/rates/{slug}`
* Asset details: `GET https://rest.coincap.io/v3/assets/{slug}`

## Deliverables:
* Source code of the Android application
* Documentation explaining the implementation details and architecture of the application
* Optionally, provide a demo video or screenshots showcasing the functionality of the application

## Submission:
* You can push your code in the repository provided in this classroom.

## Additional Notes:
* Follow Android development best practices and architectural guidelines
* Take care of error handling and loading states
* Use coroutines for reactive programming practices
* Make sure the application has good test coverage
* BONUS: Feel free to add extra features or enhancements to demonstrate your Android development skills and creativity
* BONUS: Implement real-time price updates for Bitcoin using a websocket from TwelveData [TwelveData WebSocket Documentation](https://twelvedata.com/docs#ws-real-time-price)
    * WebSocket endpoint: `wss://ws.twelvedata.com/v1/quotes/price?apikey=your_api_key`
    * Please note that you will have to create a separate API key for TwelveData.

**Good Luck!!**

**From Bitpanda Android team**
