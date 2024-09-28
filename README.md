IMPLEMENTATION

- Coupon-Types
  - `CART_WISE`
  - `PRODUCT_WISE_BY_PRODUCT_ID`
  - `PRODUCT_WISE_BY_PRODUCT_CATEGORY`
  - `BXGY_BY_PRODUCT_ID`
  - `BXGY_BY_PRODUCT_CATEGORY`

- APIs
  - POST `coupon/create`
  - GET `coupon/get/all`
  - GET `coupon/get/{id}`
  - PUT `coupon/update/{id}`
  - DELETE `coupon/delete/{id}`
  - POST `coupon/get/applicable`
  - POST `coupon/apply/{id}`

    CURLS HERE:- https://github.com/sanket1309/monk-prj/tree/master/src/main/resources/curls

- Coupon Structure

  - BXGY Coupon by product id
  
    ```
        {
            "couponId": {
                "id": "CPN-2a6ee7b7-8e25-47ea-3"
            },
            "couponType": "BXGY_BY_ID",
            "couponDetails": {
                "couponType": "BXGY_BY_ID",
                "x": 5,
                "y": 1,
                "repetitionLimit": 3,
                "productBuyIds": [
                    "PRD-2a6ee7b7-8e25-47ea-0"
                ],
                "productGetIds": [
                    "PRD-2a6ee7b7-8e25-47ea-1"
                ]
            }
        }
    ```
  - Cart wise coupon
    ```
        {
            "couponId": {
                "id": "CPN-2a6ee7b7-8e25-47ea-1"
            },
            "couponType": "CART_WISE",
            "couponDetails": {
                "couponType": "CART_WISE",
                "discount": 30,
                "threshold": 1000
            }
        }
    ```
  - Product wise coupon
    ```
        {
            "couponId": {
                "id": "CPN-2a6ee7b7-8e25-47ea-4"
            },
            "couponType": "PRODUCT_WISE_BY_ID",
            "couponDetails": {
                "couponType": "PRODUCT_WISE_BY_ID",
                "discount": 40,
                "productIds": [
                    {"id": "PRD-2a6ee7b7-8e25-47ea-2"},
                    {"id": "PRD-2a6ee7b7-8e25-47ea-3"}
                ]
            }
        }
    ```

  - Database
    InMemory DB is used to avoid schema and transactional complications in SQL

  - Constraints and Strategies implemented

    Ids (length = 24)
    - Coupon id  - `CPN-2a6ee7b7-8e25-47ea-3`
    - Product id - `PRD-2a6ee7b7-8e25-47ea-3`
    - Cart id    - `CRT-2a6ee7b7-8e25-47ea-3`
 

  
    Inclusiveness of new strategy with following steps:-
     - new strategy can easily be added by adding `CouponType`
     - Implement data class `CouponDetails`
     - Use `ApplyCouponStrategy` to writing logic to apply the new coupon on a cart

 
   
    ### CART_WISE Scenarios
    - Currently implemented
      - cart total >= threshold
      - discount % on entire cart
      - Cart after applying coupon:- 
        ```
          {
            "updatedCart": {
            "cartId": {
              "id": "CRT-2a6ee7b7-8e25-47ea-0"
            },
            "cartItems": [
              {
                "product": {
                    "productId": {
                        "id": "PRD-2a6ee7b7-8e25-47ea-0"
                    },
                    "productCategory": "FOOD",
                    "productName": "Sugar (1KG)",
                    "price": 45
                },
                "quantity": 10,
                "totalPrice": 450
              },
              {
                "product": {
                    "productId": {
                        "id": "PRD-2a6ee7b7-8e25-47ea-2"
                    },
                    "productCategory": "FASHION",
                    "productName": "Shirt (42cm)",
                    "price": 799
                },
                "quantity": 1,
                "totalPrice": 799
              },
              {
                "product": {
                    "productId": {
                        "id": "PRD-2a6ee7b7-8e25-47ea-1"
                    },
                    "productCategory": "FOOD",
                    "productName": "Oats (1KG)",
                    "price": 450
                },
                "quantity": 2,
                "totalPrice": 900
              }
          ],
            "total": 3398,
            "discountAmount": 900,
            "finalTotal": 2498,
            "size": 13
          }
        ```
        
    -Non implemented
      - `MAX_NUMBER_OF_ITEMS` in cart allowed to avoid `STOCKING`
      - `MIN_NUMBER_OF_ITEMS` in cart allowed to avoid `UNDERPRICING`

    ### BXGY Scenarios
    - Currently implemented
      - BXGY_BY_PRODUCT_ID with following details
        ```
          "couponDetails": {
                "couponType": "BXGY_BY_ID",
                "x": 2,
                "y": 1,
                "productBuyIds": [
                    "PRD-2a6ee7b7-8e25-47ea-5",
                    "PRD-2a6ee7b7-8e25-47ea-4"
                ],
                "productGetIds": [
                    "PRD-2a6ee7b7-8e25-47ea-3"
                ],
                "repetitionLimit": 1
            }
        ```
        - `productBuyIds` and `productGetIds` to include products in offer
        - count(`"PRD-2a6ee7b7-8e25-47ea-5"`) + count(`PRD-2a6ee7b7-8e25-47ea-4`) >= X in cart
        - currently adds the product from `productGetIds` which is `EXPENSIVE` (which benefits more to the DISCOUNT)
        - `number_of_items_added_as_get` = `MIN( sum_count_of_all_BUY_ids_present / X, repetitionLimit)`
        - before coupon => `TOTAL_OF_CART` = 1000
          after coupon => `TOTAL_OF_CART` = 1250 and `DISCOUNT` = 250, where 250 is worth of GET items added

    - Non implemented
      - BXGY_BY_PRODUCT_CATEGORY with following details
        ```
            "couponDetails": {
                "couponType": "BXGY_BY_CTG",
                "x": 2,
                "y": 1,
                "productBuyCategory": ["ELECTRONICS,FASHION"],
                "productGetCategory": ["FOOD"]
            }
        ```
        - ex:- BUY 2 of ELECTRONICS,FASHION and GET 1 FOOD item free
        - can add threshold for different categories differently both in BUY,GET with both `UPPERBOUND` and `LOWERBOUND` limit
          - ex:- BUY 2 Electronics upto 10000 Rs. and Any 1 FOOD Item worth no more than 500 for FREE
          - BUY 2 FASHION items worth more than 5000 and ...
 
      

    ### PRODUCT_WISE Scenarios
    - Currently implemented
      - PRODUCT_WISE_BY_PRODUCT_ID with following details
        ```
            "couponDetails": {
                "couponType": "PRODUCT_WISE_BY_ID",
                "discount": 40,
                "productIds": [
                    "PRD-2a6ee7b7-8e25-47ea-2",
                    "PRD-2a6ee7b7-8e25-47ea-3"
                ]
            }
        ```
        - 40% discount on each of product ids mentioned in the list, used in HIGH DEMANDING PRODUCTS

    - Non implemented
       - PRODUCT_WISE_BY_CATEGORY with following details
        ```
            "couponDetails": {
                "couponType": "PRODUCT_WISE_BY_CTG",
                "discount": 40,
                "productCategories": [
                    "ELECTRONICS",
                    "FOOD"
                ]
            }
        ```
        - 40% discount on each of product ids mentioned in the list
        - Applying discount on all of the category, useful in BUMPER SALES
