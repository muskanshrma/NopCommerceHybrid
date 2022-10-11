Feature: Add Product to Cart and Checkout

  Scenario: Add product to cart and checkout successfully after registering with valid details
    Given User is on Homepage
    When User adds a product to cart and checkout
    And User registers with valid details and checkout the product
    Then Order is placed successfully