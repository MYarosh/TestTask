# REST API

## Accounts

1. **GET**
   
    ```
   /account/get
   ```

    **Return**
   ```
    {
    "name": "Ivan",
    "balance": 123
    }
   ```

2. **POST**

   ```
   /account/add
   ```
    
   **Body**
   ```
   {
   "name" : "Ivan",
   "pin": "0000"
   }
   ```

   **Return**
   (It'll return id of account if all right)
   ```
    1
   ```
   
## Operations

1. **POST**

   ```
   /operation/deposit
   ```

   **Body**
   ```
   {
      {
      "id" : 1,
      "pin": "0000"
      },
      money: 123
   }
   ```

   **Return**
   
   It'll return `200` if all right
   

2. **POST**

   ```
   /operation/withdraw
   ```

   **Body**
   ```
   {
      {
      "id" : 1,
      "pin": "0000"
      },
      money: 123
   }
   ```

   **Return**

   It'll return `200` if all right


3. **POST**

   ```
   /operation/transfer
   ```

   **Body**
   ```
   {
      {
      "id" : 1,
      "pin": "0000"
      },
      {
      "id" : 2
      },
      money: 123
   }
   ```

   **Return**

   It'll return `200` if all right