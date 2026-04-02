# Agent Context: Selenium UI Automation - LambdaEcommerce

This document provides essential context and instructions for AI agents working on the **SeleniumLambdaEcom_012026** project. It outlines the technology stack, architectural patterns, capabilities, and workflows used in this automation suite.

## 🌟 Project Overview
A comprehensive, maintainable UI test automation suite for the **LambdaTest Ecommerce Playground** (`https://ecommerce-playground.lambdatest.io`).
- **Framework**: Selenium WebDriver + TestNG + Maven.
- **Pattern**: Page Object Model (POM) with clear separation of Locators (`pageUIs`) and Interactions (`pageObjects`).

---

## 🛠️ Technology Stack
| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Core language |
| **Selenium WebDriver** | 4.28.1 | Browser automation |
| **TestNG** | 7.10.2 | Test framework & execution |
| **Maven** | Latest | Build & dependency management |
| **Allure** | 2.28.0 | Test reporting |
| **Log4j** | 2.24.3 | Logging |

---

## 🏗️ Architectural Patterns
- **Page Object Model (POM)**:
  - `src/main/java/pageUIs`: Locator constants (single source of truth). prefixing with `xpath=`, `css=`, etc.
  - `src/main/java/pageObjects`: Business-logic abstraction of page operations (extends `BasePage`).
- **Base Components**:
  - `BasePage`: Centralized wait strategies and utility methods. **NEVER use `Thread.sleep()`**.
  - `BaseTest`: Handlers for initialization, multi-environment support, and teardown.
- **Factories**: `factoryBrowser` (driver management) and `factoryEnvironment` (adapter pattern for Local/Cloud).
- **Parallel Execution**: Uses `ThreadLocal<WebDriver>` for thread-safe concurrent runs.
- **Smart Retries**: `RetryAnalyzer` implemented (Local: 1 retry, CI/CD: 2 retries).

---

## 🚀 Capabilities & Skills
The project includes a set of specialized skills defined in `.github/skills/`:
- `qa-testcase-from-ui`: Scans a URL to identify UI elements and generate comprehensive test cases.
- `review-testcase`: Acts as a Senior QA Lead to review test case quality across coverage, quality, and priority.
- `selenium-java-automation`: Generates Selenium Java code (POM structure) from standardized test cases.
- `skill-creator`: Used to generate new skills for the agent.

---

## 🔄 Core Workflow: URL → Automation Code
Follow the 5-stage workflow defined in `.agents/workflows/automation-test-from-url.md`:
1. **Extraction (Stage 1)**: Scan URL, identify UI elements, and generate raw test cases in `testCases/from-ui/raw/`.
2. **Quality Control (Stage 2)**: Review raw test cases using the `review-testcase` skill. Save to `testCases/review/`.
3. **Refinement (Stage 3)**: Address review feedback and finalize test cases in `testCases/from-ui/final/`.
4. **Code Generation (Stage 4)**: Use `selenium-java-automation` to generate `PageUI`, `PageObject`, and `TestClass` code.
5. **Execution & Self-Healing (Stage 5)**: Run `mvn test`, capture logs/screenshots if failing, and automatically fix code.

---

## 📝 Coding Standards & Guidelines
- **Wait Strategy**: Always use explicit waits from `BasePage` (`waitForElementVisible`, `waitForElementClickable`).
- **Locators**:
  - Keep locators in `pageUIs` package.
  - Prefer stable/semantic selectors (ID, name, data-testid).
  - Avoid index-based (nth-child) or absolute XPaths.
- **Test Naming**: `TC_[FeatureName]_[ScenarioNumber]_[Description]` (e.g., `TC_Cart_01_Add_To_Cart`).
- **Assertions**:
  - Use TestNG `Assert` with descriptive failure messages.
  - Use `SoftAssert` when multiple checks are required in a single test.
- **File Management**: Save all generated outputs (testcases, reviews) to appropriate markdown files in the project structure.

---

## 💻 Useful Commands
```bash
# Run all tests (default environment: local, browser: chrome)
mvn test

# Run a specific test class
mvn -Dtest=tests.TC_Cart test

# Run with custom parameters
mvn -Dsurefire.suiteXmlFiles=src/test/resources/runOnTEST_AI.xml -Denv=local -DbrowserName=hchrome test

# Report Generation
mvn allure:report
mvn allure:serve
```

---

## 📂 Project Navigation Reference
- `src/main/java/commons`: `BasePage`, `BaseTest`, `GlobalConstants`.
- `src/main/java/pageObjects`: Interaction methods.
- `src/main/java/pageUIs`: UI locators (CSS, Xpath).
- `src/test/java/tests`: Test scenarios.
- `testCases/`: Documentation and test definitions.
- `allure-results/`: Raw report data.
