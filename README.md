SAMPLE JSON INPUT

**User: http://localhost:9000/api/v1/users**
* {
"userName": "John Doe",
"email": "john.doe@example.com",
"address": "123 Elm Street, Springfield, IL",
"phone": "+1-555-1234"
}

**Order 1: http://localhost:9000/api/v1/orders**
* {
"userId": 1,
"status": "DELIVERED",
"totalAmount": 150.75,
"items": [
{
"productId": 101,
"productName": "Wireless Mouse",
"quantity": 1,
"price": 25.50
},
{
"productId": 102,
"productName": "Mechanical Keyboard",
"quantity": 1,
"price": 125.25
}
]
}

**Order 2: http://localhost:9000/api/v1/orders**
* {
"userId": 1,
"status": "PROCESSING",
"totalAmount": 85.50,
"items": [
{
"productId": 103,
"productName": "USB-C Hub",
"quantity": 1,
"price": 45.00
},
{
"productId": 104,
"productName": "Bluetooth Speaker",
"quantity": 1,
"price": 40.50
}
]
}

**HANDLED EXCEPTION CASES SO FAR:**
* All the validation cases are handled and appropriate message is being displayed
* Searching by an invalid userId
* Searching by an invalid orderId
* Searching for all orders by a user handled both non-existing user and no orders placed by user 
* Also wrote a custom method in controller to handle validation error messages and display them properly in postman instead of default 500 Internal server error

**JUnit Testing Progress**
* Repository testing for one custom method
* Service testing done
* Controller testing in progress