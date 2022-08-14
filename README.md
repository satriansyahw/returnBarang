# returnBarang

## Tech Stacks : 
1. Java 11
2. Maven
3. H2
4. Spring-boot

## How To Run
1. Copy/clone the source code
2. from Terminal, change directory to Barang
3. To run ,from Terminal, type : ./mvnw spring-boot:run, or if using IntelliJ , u can just click Run
4. Hit the endpoint using postman,curl or others

## API end point List
Port : 83 , u can update in application.properties if needed. Contect path : /api
ex :base_url : http://localhost:83
1. GET / , default API, no need auth
2. POST {{base_url}}/pending/returns, an endpoint to get the token (JWT bearer auth). this token will be used in others end point <br/>
   Body : 
   {
        "orderId":"RK-478",
        "email":"john@example.com"
   }
   <br/>
   Response :
   {
    "isSuccess": true,
    "message": " Successfully login",
    "data": {
        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuYty765r4ey0wIjoxNjYwNDgyNzkxLCJpYXQiOjE2NjA0NjQ3OTF9.rgixEa42ku654-YQKWyhmhoOMMU-          I3-5ruCiI6fFHjbVC9RGpj1h53GoWcwlOwVrIiu7e5iSeEjCKPiIc7KJ9Q"
    }
  }
3. POST {{base_url}}/returns, an endpoint to create returns, need bearer jwt token <br/>
  Body :
     [
       {
           "orderId":"RK-478",
           "sku":"NIKE-7",
           "qty":1
       },
       {
           "orderId":"FCKW2",
           "sku":"sku-2",
           "qty":11
       }
       ,{
           "orderId":"FCKW3",
           "sku":"sku-4",
           "qty":12
       }
   ]
   <br/>
   Response :
   {
    "isSuccess": true,
    "message": "Returns processed",
    "data": {
        "returnsId": 1,
        "refundAmount": 110.75,
        "orderId": "RK-478",
        "returnsDetail": [
            {
                "itemDetailId": 1,
                "sku": "NIKE-7",
                "qty": 1,
                "price": 110.75
            }
        ],
        "invalidReturns": [
            {
                "orderId": "FCKW2",
                "sku": "sku-2",
                "qty": 11
            },
            {
                "orderId": "FCKW3",
                "sku": "sku-4",
                "qty": 12
            }
        ]
    }
  }
4. GET {{base_url}}/returns/1 , to get Returns info and it's detail items,  need bearer jwt token <br/>
   Response : 
   {
    "isSuccess": true,
    "message": "Get Returns Data By id ",
    "data": {
        "returnsId": 1,
        "refundAmount": 110.75,
        "status": "AWAITING_APPROVAL",
        "detailItem": [
            {
                "itemDetailId": 1,
                "qty": 1,
                "qcStatus": "ACCEPTED",
                "price": 110.75
            }
        ]
    }
  }
5. PUT {{base_url}}/returns/1/items/1/qc/accepted, to set the qc for items, available enums : accepted and rejected , need bearer jwt token <br/>
   Response : 
   {
    "isSuccess": true,
    "message": "Item status successfully updated"
   }
6. PUT {{base_url}}/returns/1/status/complete , to set the status of Returns , available enums : AWAITING_APPROVAL,COMPLETE, default value is   AWAITING_APPROVAL , need bearer jwt token <br/>
  Response :
  {
    "isSuccess": true,
    "message": "Returns status successfully updated"
  }
