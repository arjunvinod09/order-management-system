"# order-management-system" 

SAMPLE JSON INPUT

User: http://localhost:9000/api/v1/users
{
"userName": "John Doe",
"email": "john.doe@example.com",
"address": "123 Elm Street, Springfield, IL",
"phone": "+1-555-1234"
}

Order 1: http://localhost:9000/api/v1/orders
{
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

Order 2: http://localhost:9000/api/v1/orders
{
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