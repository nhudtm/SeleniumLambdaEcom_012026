# Selenium UI Automation - LambdaEcommerce

[![Execute Selenium UI Tests](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml/badge.svg)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Tests Passed](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/passed.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Tests Failed](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/failed.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)
[![Total Tests Run](https://img.shields.io/endpoint?url=https://nhudtm.github.io/SeleniumLambdaEcom_012026/badges/total.json)](https://github.com/nhudtm/SeleniumLambdaEcom_012026/actions/workflows/run-test.yml)

A comprehensive, maintainable UI test automation suite for the LambdaTest Ecommerce Playground. Built with Selenium WebDriver, TestNG, and the Page Object Model (POM) pattern for maximum reliability and scalability.

## Key Features

- **Page Object Model (POM)**: Clean separation of locators and page interactions for easy maintenance
- **Multi-environment support**: Run locally or on cloud platforms (BrowserStack, LambdaTest)
- **Intelligent retry policy**: 1 retry for local, 2 retries for CI/CD to handle transient failures
- **Framework-based waits**: Explicit wait wrappers eliminate unreliable sleeps
- **Comprehensive test coverage**: 30+ test cases covering core e-commerce flows
- **Allure reporting**: Detailed test reports with screenshots and logs
- **Parallel execution**: ThreadLocal-based driver management for concurrent test runs

---

## Tech Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Java | 17 | Application language |
| Selenium WebDriver | 4.28.1 | Browser automation |
| TestNG | 7.10.2 | Test framework |
| Maven | Latest | Build & dependency management |
| Allure | 2.28.0 | Test reporting |
| Log4j | 2.24.3 | Logging |
| Appium | 9.5.0 | Mobile testing support |

**Key Architectural Patterns:**
- Page Object Model (POM)
- Factory Pattern (Browser, Environment)
- ThreadLocal for parallel execution
- Listener Pattern (retry, reporting)

---

## Project Structure

```
src/
├── main/java/
│   ├── commons/                    # Framework foundation
│   │   ├── BasePage.java           # Wait wrappers & locator utilities
│   │   ├── BaseTest.java           # Test setup & environment handling
│   │   └── GlobalConstants.java    # Timeout & config constants
│   ├── factoryBrowser/             # WebDriver factories
│   │   ├── ChromeDriverManager.java
│   │   ├── FirefoxDriverManager.java
│   │   └── EdgeDriverManager.java
│   ├── factoryEnvironment/         # Environment adapters
│   │   ├── LocalFactory.java
│   │   ├── BrowserStackFactory.java
│   │   └── MobileFactory.java
│   ├── pageObjects/                # Page Object implementations
│   │   ├── HomePO.java
│   │   ├── BlogPO.java
│   │   ├── CartPO.java
│   │   └── ...
│   ├── pageUIs/                    # Locator constants
│   │   ├── HomeUI.java
│   │   ├── BlogUI.java
│   │   └── ...
│   ├── listeners/                  # TestNG listeners
│   │   ├── RetryAnalyzer.java      # Smart retry (local=1, CI/CD=2)
│   │   └── AnnotationTransformer.java
│   └── reportConfig/               # Reporting setup
│       └── AllureTestListener.java
├── test/java/
│   ├── tests/                      # Test classes (30+ test cases)
│   │   ├── TC_Blog.java            # Blog feature (P0/P1/P2/P3 prioritized)
│   │   ├── TC_Cart.java
│   │   ├── TC_Compare.java
│   │   └── ...
│   └── dataProviders/              # TestNG data providers
└── test/resources/
    ├── runOnTEST_AI.xml            # Main test suite configuration
    └── config.properties
```

### Core Modules

- **commons**: Centralized wait strategies (BasePage) - never use Thread.sleep
- **pageObjects**: Page interactions layer - encapsulates all element interactions
- **pageUIs**: Locator constants - single source of truth for element selectors
- **listeners**: RetryAnalyzer (smart retry), AnnotationTransformer (global retry setup)

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.1.2+
- Chrome, Firefox, or Edge browser installed

### Setup

1. Clone the repository
   ```bash
   git clone <repository-url>
   cd SeleniumEcomRefactor_012026
   ```

2. Verify build
   ```bash
   mvn -q -DskipTests compile
   ```

## Running Tests

### Quick Start

```bash
# Run all tests with default configuration
mvn test

# Run specific test class
mvn -Dtest=tests.TC_Blog test

# Run specific test method
mvn -Dtest=tests.TC_Blog#TC_14_Search_With_Valid_Keyword test
```

### Advanced Execution

```bash
# Run with custom suite and parameters
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST_AI.xml \
    -Denv=local \
    -DbrowserName=chrome \
    -Durl=https://ecommerce-playground.lambdatest.io \
    test

# Run specific test group (Blog P0 tests - critical flows)
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST_AI.xml \
    -Denv=local \
    -DbrowserName=chrome \
    -Durl=https://ecommerce-playground.lambdatest.io \
    -Dgroups=P0 \
    test

# Run tests headless
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST_AI.xml \
    -Denv=local \
    -DbrowserName=hchrome \
    -Durl=https://ecommerce-playground.lambdatest.io \
    test
```

### Environment Control

| Environment | Usage | Retry Policy |
|-------------|-------|--------------|
| **local** | Desktop/development | 1 retry (max 2 attempts) |
| **CI/CD** (GitHub Actions) | Automated pipeline | 2 retries (max 3 attempts) |
| **BrowserStack** | Cloud cross-browser | 2 retries (max 3 attempts) |

The retry policy is automatically detected via environment variables (CI, GITHUB_ACTIONS, JENKINS_URL, etc.). See `src/main/java/listeners/RetryAnalyzer.java` for details.

## Test Coverage

| Feature Area | Test Classes | Coverage |
|--------------|--------------|----------|
| **Authentication** | TC_Account | Registration, Login, My Account management |
| **Product Discovery** | TC_Category, TC_Filter | Navigation, pagination, filtering, search |
| **Product Interactions** | TC_ProductDetail | Detail views, wishlist, comparisons |
| **Cart Operations** | TC_Cart | Add/remove, quantity updates, calculations |
| **Special Features** | TC_Special, TC_Compare | Filtering edge cases, comparison capacity |

## Stability & Troubleshooting

> **Note**: The target application has inherent flakiness due to lazy-loaded images, asynchronous rendering, and dynamic content. Our framework mitigates this through intelligent waits and retry policies.

### Known Issues & Workarounds

| Issue | Root Cause | Workaround |
|-------|-----------|-----------|
| Carousel/slider timeouts | Lazy initialization or viewport visibility | Activate carousel tab first; scroll into view; use JS to bring target slide |
| Add-to-cart popup not clickable | DOM differs between runs | Enable screenshot on failure for debugging |
| Search results not visible | Incorrect DOM scope or stale element refs | Use framework waits only (no sleeps); normalize URLs with %2f encoding |
| Filter elements not found | Target page has no data to filter | Use data provider or pre-populate via API |

### Best Practices

1. **Write independent tests**: Each test should own its setup/teardown
2. **No shared state**: Avoid cross-test dependencies
3. **Use explicit waits**: Never use Thread.sleep; leverage BasePage wrappers
4. **Prioritize by ID**: Use stable product IDs instead of index-based selectors
5. **Normalize URLs**: Always normalize (lowercase + replace %2f) before route assertions

### Debugging Tips

```bash
# Run single test with detailed logging
mvn -Dtest=tests.TC_Blog#TC_14_Search_With_Valid_Keyword -X test

# Generate Allure report
mvn allure:report
open target/site/allure-maven-plugin.html

# Check ThreadLocal driver cleanup
grep "ThreadLocal" logs/*.log
```

## Architecture & Design Patterns

### Page Object Model (POM)
- **Locators** (`pageUIs/*.java`): Centralized selectors with Xpath/CSS strategies
- **Interactions** (`pageObjects/*.java`): Business-logic abstraction of page operations
- **Base** (`commons/BasePage.java`): Shared wait/utility methods

### Wait Strategy
All waits are explicit and centralized in BasePage:
```java
waitForElementVisible(locator);         // Visibility + presence + interaction-ready
waitForElementClickable(locator);       // Specifically for clickable state
waitForElementPresence(locator);        // DOM presence only
waitForAllElementPresence(locator);     // Multiple elements
```
✅ No `Thread.sleep()` — eliminates flakiness  
✅ Framework-managed timeouts — respects GlobalConstants  
✅ Smart retry logic — automatic in CI/CD

### Parallel Execution
- ThreadLocal-based WebDriver per thread
- Safe cleanup via `quit()` and `remove()`
- Aspect-oriented weaving (AspectJ) for thread management

## Framework Conventions

### Test Naming
```
TC_[FeatureName]_[ScenarioNumber]_[Description]
```
Example: `TC_Blog_14_Search_With_Valid_Keyword`

### Priority Levels 
- **P0**: Critical - core application flows that must work
- **P1**: High - business, UX, or security features
- **P2**: Medium - non-essential but expected functionality
- **P3**: Low - edge cases or cosmetic issues

### Locator Strategy
```
// Prefer stable, semantic selectors
Good:  id, class, [data-testid], aria-label
Avoid: Index-based (nth-child), position-based
```

For dynamic content:
- Use CSS/Xpath with parameter substitution
- Apply case-insensitive matching with `translate()`
- Normalize URLs (lowercase + replace %2f encoding)

## Quick Links

| Resource | Purpose |
|----------|---------|
| [TestNG Documentation](https://testng.org/) | Framework reference |
| [Selenium Documentation](https://www.selenium.dev/documentation/) | WebDriver API |
| [Allure Documentation](https://docs.qameta.io/allure/) | Reporting |

---

## To do
- Create agent skills
- Create workflow

**Status**: Production-ready with 140+ stable test cases
