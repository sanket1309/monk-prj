IMPLEMENTATION

- Coupon-Types
  - CART_WISE
  - PRODUCT_WISE_BY_PRODUCT_ID
  - PRODUCT_WISE_BY_PRODUCT_CATEGORY
  - BXGY_BY_PRODUCT_ID
  - BXGY_BY_PRODUCT_CATEGORY

- APIs
  - POST coupon/create
  - GET coupon/get/all
  - GET coupon/get/{id}
  - PUT coupon/update/{id}
  - DELETE coupon/delete/{id}
  - POST coupon/get/applicable
  - POST coupon/apply/{id}

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
                    {
                        "id": "PRD-2a6ee7b7-8e25-47ea-2"
                    },
                    {
                        "id": "PRD-2a6ee7b7-8e25-47ea-3"
                    }
                ]
            }
        }
    ```
