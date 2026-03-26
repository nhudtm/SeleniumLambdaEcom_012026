# Selenium Automation test - LambdaEcommerce Website

[![Execute Selenium UI Tests](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml/badge.svg)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Tests Passed](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/passed.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Tests Failed](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/failed.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Total Tests Run](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/total.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)

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

# Run a single test class and override env/url from command line
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST.xml -Dtest=tests.TC_MyAccount -Denv=local -DbrowserName=chrome -Durl=https://ecommerce-playground.lambdatest.io test

# Run a single test method and override env/url from command line
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST.xml -Dtest=tests.TC_MyAccount#TC_05_Login_With_Valid_Info -Denv=local -Durl=https://ecommerce-playground.lambdatest.io -DbrowserName=chrome test
```

If you run tests from an IDE, use the TestNG runner or the test class/method run actions.

---

## Environment & setup notes

- Ensure the correct Java JDK is installed (11+). Check `pom.xml` for exact source/target levels.
- Chrome/Firefox drivers should be managed by the project's driver managers or set via environment variables if necessary.
- If you see flaky failures around carousels or dynamic content, try running with a single thread (disable parallel runs) and enable screenshots on failure for debugging.

---

## Implemented Functionalities

The test suite covers the following core e-commerce functionalities:

**User Account & Authentication:**
- User Registration (including cross-device testing with iOS/Android and BrowserStack)
- User Login (including SoftAssert approaches)
- My Account management

**Product Navigation & Discovery:**
- Home page component interactions and Mega Menu navigation
- Category page navigation and Pagination
- Product Search functionality
- Product Filtering (by Category and Special criteria)
- Blog and Special product pages

**Product Interactions:**
- Product detail page views and actions
- Add to Cart from various locations (Home, Category, Product Detail)
- Add to Wishlist (including cookie-based testing)
- Product Comparisons (capacity limits, deductions)

**Cart Operations & Ordering:**
- Cart management (adding/removing products, quantity updates)
- Calculation validations (Subtotal, Tax, Total)
- Buy Now / Checkout flows

---

## Known flakiness & troubleshooting tips

- Note: This e-commerce website is inherently flaky for UI automation because many images/icons are lazy-loaded and rendered asynchronously; visual and clickable states can change between runs.
- Note: Test cases should be written independently, with each test owning its own setup/teardown and avoiding shared state or cross-test dependencies.
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
