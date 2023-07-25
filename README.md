# wuthichai-table
A simple application that make reservations and cancel reservation in an imaginary restaurant, Wuthichai.

## Prerequisite
1. Java version 17 or higher. Get it [here](https://duckduckgo.com/?q=java+download)
2. Maven version 3.6.3 or higher. Get it [here](https://duckduckgo.com/?q=maven+download)
3. (Optional) Your favorite IDE

## Getting Started
Type `mvn spring-boot:run` at the root directory of the project.

## Usage
The service can be accessed at `http://localhost:8080/restaurant`

### Initialization
First, you need to initialize the application with the number of maximum tables in your restaurant. This can be done by submiting POST to `http://localhost:8080/restaurant/init` with body message `{"capacity": [numberOfTable]}`
In this simple application, each table can hold a maximum of 4 guests.

![init first time](https://github.com/fata-morgana/wuthichai-table/assets/4438815/b95ce90a-3d46-4429-9a12-07ce5ba65ffc)

You can only initialize once. The subsequent runs will give you error.
![init second time](https://github.com/fata-morgana/wuthichai-table/assets/4438815/1d307d7a-4d75-4e28-b39a-239d24b6810c)

### Make a reservation
After initialized, you can make a reservation by doing POST to `http://localhost:8080/restaurant/reserve` with body message `{"guessNumber": [numberOfGuest]}`. The application will group guests in to a table of 4, which the rest of guest will be assigned to the nex table(s). The location of each table is not taken into account and will be assigned as random.

This API return `reservationId`, which you need it when you can to cancel the reservation.

![reserve - 1st](https://github.com/fata-morgana/wuthichai-table/assets/4438815/ef1be1f0-8196-4efa-ac59-311bf38ee657)

If there is no available tables for the whole of guests, it will give you an error message.

![reserve - no table available](https://github.com/fata-morgana/wuthichai-table/assets/4438815/0f4e6390-d5c6-4374-a7e0-ab7da3d2faff)

If you call this API before initialize the tables, it will give you an error message.

![reserve - not init](https://github.com/fata-morgana/wuthichai-table/assets/4438815/f820ad7b-41d9-4a1a-93d9-091723e736ef)


### Cancel a reservation
After initialized and make a reservation, you can cancel the reservation. When cancel, the whole group of tables will be released together. You cannot cancel each table selectively. This can be done by doing POST to `http://localhost:8080/restaurant/cancel` with body message `{"reservationId": [reservationId]}`

If the cancelation is successful, it will return OK message.

![Cancel - OK](https://github.com/fata-morgana/wuthichai-table/assets/4438815/fefa2b31-01f0-4d09-9ee2-8d20728b1495)

If you try to cancel a non-existing reservation, the API will return error message.

![cancel - not found](https://github.com/fata-morgana/wuthichai-table/assets/4438815/5045b3ab-ef37-4059-872a-785ed5dd6410)

If you call this API before intialize the tables, the API will return error message.

![cancel - not init](https://github.com/fata-morgana/wuthichai-table/assets/4438815/c3b8d8d0-d320-4b78-ad2f-c702b0c6d7c6)

## License
[MIT](https://choosealicense.com/licenses/mit/) license.
See `License.txt`



