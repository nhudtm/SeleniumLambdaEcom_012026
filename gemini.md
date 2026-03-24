# Gemini Context & Project Encapsulation

## Project Overview
**Name**: LambdaEcommerceRefactor  
**Type**: Automated UI Test Suite (E-commerce)  
**Tech Stack**: Java 17, Selenium WebDriver (4.28.1), TestNG (7.10.2), Maven, Page Object Model (POM), Allure Reports.  
**Target site**: `https://ecommerce-playground.lambdatest.io`

This project is structured using the Page Object Model, with separating layers for:
- **commons**: Base classes (`BaseTest`, `BasePage`), utilities, waits.
- **factoryBrowser**: WebDriver manager factories (Chrome, Edge, Firefox, and their headless variants).
- **pageObjects** & **pageUIs**: Locators and interactions for specific web pages (e.g., `HomePO`, `SpecialPO`, `ProductCategoryPO`).
- **components**: Reusable UI parts (`FilterComponent`, `ProductComponent`).
- **tests**: TestNG test classes grouping validations (e.g., `TC_Cart`, `TC_Filter_ProductCategory`).

---

## Accomplishments & Work Done So Far

1. **Documentation Updates**:
   - Assessed all test files and updated `README.md` to accurately reflect the implemented functionality, grouped into core areas (Auth, Product Navigation, Product Interactions, Cart).

2. **Framework Fixes & Enhancements**:
   - Added `HEdgeDriverManager.java` to fix a compilation failure caused by an unimplemented Enum reference (`case HEDGE:` in `BaseTest.java`).
   - Investigated and resolved a missing `@DataProvider` dependency in `TC_Filter_Special.java` by explicitly declaring `dataProviderClass = dataProviders.FilterDataProviders.class`.

3. **Execution Troubleshooting**:
   - Configured and executed ad-hoc test runs using temporary TestNG XML suites (like `temp_run.xml`).
   - Fixed missing required parameters (`url`) when running independent XML files, enabling `BeforeClass` setups to execute correctly.

---

## Important Instructions for Future Models (Agents)

When assisting with this codebase, refer to these critical ground rules:

1. **Test Execution**:
   - To run a specific XML test suite via Maven, always use the `surefire.suiteXmlFiles` property to correctly override what is written in `pom.xml`. 
     *Example*: `mvn test -Dsurefire.suiteXmlFiles=temp_run.xml`
   - Every ad-hoc TestNG XML file **must** include standard `<parameter>` tags like `browserName` and `url` to prevent `BaseTest` failures.
   - For headless execution, use the prefixes defined in `factoryBrowser`: `hchrome`, `hfirefox`, `hedge`.

2. **Known Flaky Areas / Expected Failures**:
   - `TC_Filter_Special`: The target "Special" page currently has *no products to filter on*. Any test exercising standard UI filters here will time out because it's looking for `mz_fp[min]` or elements that don't load. This is a known issue.
   - When building loops to paginate through products (`while(true) { clickNextPageButton() }`), be careful of infinite loops or immediate timeouts if elements are obscured.

3. **Data Providers**:
   - Standard data providers are located in `src/test/java/dataProviders/`. Be sure to reference them correctly using `dataProviderClass = dataProviders.[ClassName].class` if the `dataProvider` differs from the main test class location.

4. **Debugging Driver Issues**:
   - The framework heavily uses `ThreadLocal<WebDriver>` for parallelization. When modifying configuration or running sequential tests, always monitor the `quit()`/`remove()` mechanics to prevent dangling browser processes.
