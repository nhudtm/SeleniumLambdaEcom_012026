# LambdaEcommerceRefactor

Project: Automated UI tests for a sample e-commerce site (Lambda Ecommerce) using Selenium WebDriver and TestNG, organized with the Page Object Model.

---

## Project summary

This repository contains an automated UI test suite that verifies core e-commerce flows such as:
- Browsing product lists (Top Products, Collections)
- Adding products to Compare and Compare page behavior
- Add-to-cart flows (popup and in-list Add to Cart)
- Cart quantity, subtotal, tax and total calculations
- Wishlist and quick view interactions

The tests are implemented in Java using Selenium WebDriver and TestNG, executed via Maven. The codebase follows the Page Object Model (POM) pattern.

Goals:
- Provide reliable, maintainable end-to-end UI tests
- Make tests easy to run locally and in CI
- Minimize flakiness by consolidating common wait/utility code in `commons` and `pageObjects`

---

## Tech stack

- Java (project configured for Java 11/17 depending on local setup)
- Maven
- Selenium WebDriver
- TestNG
- Page Object Model

---

## Project structure (high level)

```
LambdaEcommerceRefactor/
├─ pom.xml
├─ README.md
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  ├─ commons/           # Base classes, wait utilities, BasePage
│  │  │  ├─ factoryBrowser/    # Browser driver managers and factories
│  │  │  ├─ factoryEnvironment/ # Environment factories (local/remote)
│  │  │  ├─ pageObjects/       # Page object classes (HomePO, CartPO, ComparePO...)
│  │  │  ├─ pageUIs/           # Locators grouped as constants
│  │  │  ├─ components/        # Reusable UI components (product component)
│  │  │  └─ reportConfig/      # Reporting utilities
│  │  └─ resources/
│  └─ test/
│     ├─ java/
│     │  ├─ tests/             # Test classes (TC_Cart, TC_Compare, ...)
│     │  ├─ steps/             # Step definitions or helper flows
│     │  └─ dataProviders/     # TestNG data providers
│     └─ resources/            # test resources (runOnTEST.xml, device configs)
└─ target/                    # build/test outputs
```

Key locations:
- `src/main/java/pageObjects/HomePO.java` — home page interactions (carousels, add-to-cart from collections)
- `src/main/java/pageUIs/HomeUI.java` — locators for home page
- `src/test/java/tests/TC_Cart.java` — cart-related testcases
- `src/test/java/tests/TC_Compare.java` — compare-related testcases

---

## How to run tests

From the repository root, using Maven:

```bash
# Run the full test suite
mvn test

# Run a single test class
mvn -Dtest=tests.TC_Cart test

# Run a single test method (Surefire pattern may vary on your config)
mvn -Dtest=tests.TC_Cart#TC_10_Verify_Tax_And_Subtotal_1_Product_Quantity_1 test
```

You can pass additional parameters (if your test framework supports them) with `-Dbrowser=chrome -Durl=https://example.com`.

If you run tests from an IDE, use the TestNG runner or the test class/method run actions.

---

## Environment & setup notes

- Ensure the correct Java JDK is installed (11+). Check `pom.xml` for exact source/target levels.
- Chrome/Firefox drivers should be managed by the project's driver managers or set via environment variables if necessary.
- If you see flaky failures around carousels or dynamic content, try running with a single thread (disable parallel runs) and enable screenshots on failure for debugging.

---

## Test case list (detailed)

Each test case maps to Test classes under `src/test/java/tests`.

- TC_01_Add_To_Compare_Popup_1_Product
  - Steps: On Home -> add product #1 from Popular collection to Compare
  - Expected: Compare popup appears with 1 product; open Compare page -> compare shows 1 product

- TC_02_Add_To_Compare_Popup_2_Products
  - Steps: Add product #1 and #2 to Compare
  - Expected: popup updates count; compare page lists 2 products

- TC_03_Add_To_Compare_Popup_4_Product
  - Steps: Add products #1..#4
  - Expected: popup & compare page show 4 products

- TC_04_Add_To_Compare_Popup_5_Product
  - Steps: Add 5th product and observe behavior when capacity exceeded
  - Expected: UI handles overflow (per app rules) and shows the correct set on compare page

- TC_05_Verify_Compare_Page_0_Product
  - Steps: Navigate to Compare page with no product added
  - Expected: "no product" message shown

- TC_06_Verify_Compare_Page_1_Product
  - Steps: Add 1 product and open Compare page
  - Expected: compare contains one product with correct name/price

- TC_07_Verify_Compare_Page_3_Product
  - Steps: Add 3 products, open Compare page
  - Expected: compare shows 3 products and correct product details

- TC_08_Compare_Page_5_Product
  - Steps: Add up to 5 products and open compare page
  - Expected: app-defined display/rotation behavior; verify final set

- TC_09_Verify_Total_Price_Calculation_Quantity_5
  - Steps: addProductToCartAndGoToCartPage(2) (the tests historically use index 2)
  - Expected: Cart shows correct subtotal/total for quantity 5 (test data driven)

- TC_10_Verify_Tax_And_Subtotal_1_Product_Quantity_1
  - Steps: addProductToCartAndGoToCartPage(2)
  - Expected: Verify tax & subtotal are calculated correctly for quantity 1

- TC_11_Verify_Cart_Remove_Product
  - Steps: Remove product(s) from cart and verify messages & totals
  - Expected: Cart updates and messages shown

- TC_12_Wishlist_Flows
  - Steps: Add product to wishlist via action button and verify wishlist page
  - Expected: wishlist contains the added product

- TC_13_Add_To_Compare_Same_Product_Twice
  - Steps: Try adding the same product twice and verify deduplication or message
  - Expected: app prevents duplicates or reports accordingly

- TC_14_Add_To_Cart_From_Compare_Popup
  - Steps: From compare popup or compare page, open add-to-cart popup, choose options, add to cart
  - Expected: add-to-cart success, cart contains product

- TC_15_Quantity_Controls_In_Popup
  - Steps: Use + / - controls in add-to-cart popup and verify value changes
  - Expected: quantity increments/decrements as expected

- TC_16_BuyNow_Flow
  - Steps: Use Buy Now in add-to-cart popup and verify navigation to Checkout/Cart
  - Expected: navigation to cart/checkout as expected

(Several other tests exist in the suite; these are representative core tests.)

---

## Known flakiness & troubleshooting tips

- Carousel / swiper elements (Home collections): these are often lazy-initialized or partially visible; tests that interact with a specific slide by index can be flaky when the slide isn't in the current viewport.
  - Workarounds: ensure the collection/tab is activated before interacting; scroll the slider into view; use JS to bring target slide into view or click carousel controls.
- Add-to-cart popup: if the popup appears but Selenium reports element not visible, capture a screenshot and page source on failure to inspect DOM differences.
- Test ordering: some tests rely on page state. Prefer tests to set up their own preconditions and clean up after themselves.

---

## Recommendations & next steps

- Add screenshot and page-source capture on test failure (helps diagnose intermittent DOM differences).
- Replace fragile index-based product selectors with stable identifiers (product id or product name) where possible.
- Consider adding a small utility that ensures a carousel tab is active and slides are populated before interacting (to reduce timeouts seen in TC_10).
- Add a small `verify` helper to reliably check cart totals/formatting in different locales.

---

If you want, I can also:
- Create a `CLAIM.md` or `CONTRIBUTING.md` with developer run instructions and CI config notes.
- Create a small script (shell) that runs a single test with useful debug flags (save screenshots on failure).


