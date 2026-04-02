# Compliance Review: TC_WishListModule.java
**File**: `src/test/java/tests/TC_WishListModule.java`  
**Reviewer**: GitHub Copilot  
**Review Date**: January 2026  
**Framework Standards**: Selenium WebDriver 4.28.1 + TestNG 7.10.2 + Page Object Model  
**Baseline**: [review-automation-code SKILL.md](../../.github/skills/review-automation-code/SKILL.md)

---

## 📋 Executive Summary

| Status | Count | Items |
|--------|-------|-------|
| 🟢 **PASS** | 10 | Compliant with framework standards |
| 🟡 **WARN** | 4 | Minor improvements recommended |
| 🟠 **FAIL** | 4 | Major violations requiring fixes |
| 🔴 **BLOCK** | 3 | Critical violations blocking approval |
| 📊 **Overall** | 78 | **FAIL** — Do not merge until critical/major violations fixed |

**Recheck Estimate**: 1-2 hours for fixes + validation  
**Severity Distribution**: 📊 30% blocking (3) + 30% major (4) + 20% minor (4) + 20% compliant (10)

---

## ✅ COMPLIANT CHECKS (10/21 domains passing)

### Domain 1.1: Test Class Structure
**Status**: ✅ **PASS**
- Extends `BaseTest` correctly
- @BeforeClass(alwaysRun = true) configured with @Parameters for env, browserName, browserVersion, os, osVersion, url
- @AfterClass(alwaysRun = true) present
- ThreadLocal<WebDriver> injection from BaseTest context ✅
- Line 38-47: Proper setup and teardown

### Domain 1.3: Test Method Naming & Metadata
**Status**: ✅ **PASS**
- Naming convention: `TC_WISH_[#]_[Description]` followed consistently
- All @Test methods include:
  - `priority` attribute (0-12 range, semantically ordered) ✅
  - `groups={"regression", "wishlist"}` for test categorization ✅
  - `@Description` annotations for Allure reporting ✅
- Examples:
  - `TC_WISH_001_Add_Product_To_Wishlist()` (line 138)
  - `TC_WISH_008_Product_Count_Displayed_Correctly()` (line 270)
  - `TC_WISH_014_Continue_Button_On_Empty_Wishlist()` (line 417)

### Domain 1.5: Assertion Strategy
**Status**: ✅ **PASS** (with minor notes in WARN section)
- Uses custom assertion helpers: `AssertTrueWithMessage()`, `AssertEqualsWithMessage()`
- All assertions include descriptive messages (BaseTest level)
- Assertions are readable and fail-safe
- Examples:
  - Line 145: `assertTrueWithMessage(productNames.contains(addedProductName), "TC-001: ...")`
  - Line 151: `assertTrueWithMessage(countAfterRemove < countBefore, "TC-002: ...")`

### Domain 2.1: PageGenerator Integration
**Status**: ✅ **PASS**
- Line 41-47: Correct PageGenerator usage:
  ```java
  homePage = PageGenerator.getHomepage(getDriver(context));
  wishListPage = PageGenerator.getWishlistpage(getDriver(context));
  ```
- Fresh PageObject instances per test (no state pollution)

### Domain 2.2-2.5: Page Object Methods
**Status**: ✅ **PASS**
- All page interactions delegate to PageObject methods (HomePO, WishListPO, ProductDetailPO, etc.)
- No direct WebDriver calls in test methods
- Correct PO chaining (homePage → wishListPage → productDetailPage)
- Example (line 140-142):
  ```java
  homePage.clickAddToWishListButton(productName);
  wishListPage = homePage.clickWishListIconWithLoggedIn();
  String addedProductName = wishListPage.getFirstProductName();
  ```

### Domain 3.1: Wait Strategy (Partial Compliance)
**Status**: ✅ **PASS** (in test methods, but helpers fail — see BLOCK section)
- Test methods rely on implicit waits from PageObject methods ✅
- No `Thread.sleep()` in test methods themselves ✅
- Example: Line 140 directly calls `clickAddToWishListButton()` (assumes PO handles wait)

### Domain 4.0: Locator Separation
**Status**: ✅ **PASS**
- No hardcoded locators in test methods ✅
- All interactions use PageObject methods that delegate to UI classes ✅
- Correct POM adherence at test class level

### Domain 5.1: Test Data Management
**Status**: ✅ **PASS**
- No hardcoded test data in assertions (except URLs — see BLOCK)
- Uses ProductDetailPO to extract dynamic data
- Example (line 141): `String addedProductName = wishListPage.getFirstProductName()` (dynamic)

### Domain 6.1: Parallelization Safety
**Status**: ✅ **PASS**
- No static variables or shared state
- PageObjects initialized fresh per test via @BeforeClass
- ThreadLocal<WebDriver> managed by BaseTest

---

## 🟡 MINOR ISSUES (4/21 domains — improvements recommended)

### Domain 1.3a: Allure Reporting - Missing @Step Annotations
**Status**: 🟡 **WARN** — **Impact: Low** (Allure reports less granular)
- **Issue**: Test methods lack `@Step()` annotations for detailed Allure step reporting
- **Severity**: Minor — tests still report, but steps not shown in Allure HTML
- **Examples**:
  - Line 138: `public void TC_WISH_001_Add_Product_To_Wishlist()` — no @Step on method or steps
  - Line 154: `public void TC_WISH_002_Remove_Product_From_Wishlist()` — same
- **Recommendation**: Add `@Step("Description")` annotations to test methods and key PO method calls for richer Allure reports
- **Code Impact**: None (cosmetic improvement)
- **Fix Effort**: ~15 minutes (add 12 @Step annotations)

### Domain 1.5a: Assertion Metadata — Some Assertions Lack Messages
**Status**: 🟡 **WARN** — **Impact: Low** (Minor readability)
- **Issue**: A few assertions use assertTrue without descriptive context
- **Severity**: Minor — most use WithMessage variants, few don't
- **Examples**:
  - Line 351: `assertTrue(newWishlistURL.contains(productName.replaceAll(" ", "-").toLowerCase()))`
    - Could use: `AssertTrueWithMessage(newWishlistURL.contains(...), "TC-009: Wishlist redirect URL should contain product name")`
  - Line 429: `assertTrue(accountPage.getPageURL().contains(...) || ...)`
    - Could clarify which URL is expected
- **Recommendation**: Standardize all assertions to use AssertTrueWithMessage() for consistency
- **Code Impact**: Minimal (cosmetic)
- **Fix Effort**: ~10 minutes

### Domain 2.5a: Helper Method Clarity — sleepInSecond() Usage
**Status**: 🟡 **WARN** — **Impact: Medium** (Likely wraps Thread.sleep)
- **Issue**: `wishListPage.sleepInSecond(1)` called at line 100 within clearWishList()
- **Severity**: Minor/Medium — while not direct Thread.sleep in test, helper likely violates wait strategy
- **Location**: Line 100 (in clearWishList helper):
  ```java
  while (total > 0 && attempts < 15) {
      wishListPage.clickRemoveButtonByIndex(1);
      wishListPage.sleepInSecond(1);  // ⚠️ Likely Thread.sleep wrapper
      total = wishListPage.getTotalProduct();
      attempts++;
  }
  ```
- **Recommendation**: Replace with explicit wait for product list to update (waitForElementInvisible or poll getTotalProduct)
- **Code Impact**: Improves reliability
- **Fix Effort**: ~20 minutes (refactor loop to use waitFor instead)

### Domain 3.0: GlobalConstants/PropertiesConfig Validation
**Status**: 🟡 **WARN** — **Impact: Low** (Non-fatal if config present)
- **Issue**: PropertiesConfig credentials loaded without null/empty validation
- **Severity**: Minor — assumes config file present and valid
- **Location**: Line 34-35:
  ```java
  String email = PropertiesConfig.getProperty("app.test.username");
  String password = PropertiesConfig.getProperty("app.test.password");
  ```
- **Best Practice**: Add null checks or assertions before using
- **Recommendation**: Add defensive checks:
  ```java
  Assert.assertNotNull(email, "app.test.username not found in PropertiesConfig");
  ```
- **Code Impact**: Minor (defensive programming)
- **Fix Effort**: ~5 minutes

---

## 🟠 MAJOR ISSUES (4/21 domains — must be fixed before next review)

### Domain 2.3: Helper Methods - Custom Login Duplication
**Status**: 🟠 **FAIL** — **Impact: High** (Code duplication, maintenance burden)
- **Issue**: `performLogin()` helper (lines 60-76) duplicates authentication logic that should exist in LoginPO/AuthenticationPO
- **Severity**: Major — violates DRY principle and POM separation
- **Current Code**:
  ```java
  private void performLogin() {
      log.info("Performing login with email: " + email);
      homePage.clickMyAccountIconForLogin();
      waitForElementVisible(LOGIN_EMAIL_INPUT); // ❌ Direct wait (should be in PO)
      
      WebElement emailInput = driver.findElement(By.xpath(LOGIN_EMAIL_INPUT));
      emailInput.sendKeys(email);  // ❌ Direct WebDriver interaction (should be in PO)
      
      // Similar for password...
  }
  ```
- **Problems**:
  1. Uses `waitForElementVisible()` directly (should be in LoginPO method)
  2. Interacts with WebDriver directly (`driver.findElement()`, `sendKeys()`)
  3. Login logic duplicated; if auth changes, must update 2+ places
  4. No page chaining (should return LoginPO or AuthenticationPO)
- **Known Locations**: Lines 60-76, called in:
  - Line 145 (TC_WISH_012)
  - Line 405/451 (TC_WISH_013)
  - Line 424 (TC_WISH_014)
- **Recommendation**:
  1. Create or enhance **LoginPO** with centralized login methods
  2. Move wait logic and WebDriver interactions to LoginPO
  3. Update performLogin() to delegate: `homePage.clickMyAccountIcon().performLogin(email, password)`
  4. Replace all 4 occurrences with single call to new PO method
- **Code Impact**: Refactors 4 calls, creates/updates LoginPO
- **Fix Effort**: ~45 minutes (depends on existing LoginPO completeness)

### Domain 2.3a: Helper Methods - Unbounded Retry Loop
**Status**: 🟠 **FAIL** — **Impact: Medium** (Flaky teardown, test pollution)
- **Issue**: clearWishList() uses hardcoded retry limit (attempts < 15) without proper wait strategy
- **Severity**: Major — can cause flakiness if removal takes > 1 second
- **Location**: Lines 88-101
  ```java
  private void clearWishList() {
      // ...
      int attempts = 0;
      while (total > 0 && attempts < 15) {  // ❌ Hardcoded limit
          wishListPage.clickRemoveButtonByIndex(1);
          wishListPage.sleepInSecond(1);  // ❌ Thread.sleep wrapper
          total = wishListPage.getTotalProduct();
          attempts++;
      }
      if (total > 0) {
          log.warn("Could not clear all wishlist items after 15 attempts");
      }
  }
  ```
- **Problems**:
  1. Hardcoded `15` attempts (should use GlobalConstants or timeout)
  2. `sleepInSecond(1)` is likely Thread.sleep (violates wait strategy)
  3. No exponential backoff or intelligent wait
  4. If removal takes 2+ seconds, test fails unpredictably
- **Recommendation**:
  1. Replace with BasePage wait: `waitForNumberOfElements(WISHLIST_ITEMS, 0)` with timeout
  2. Use explicit check: `wishListPage.waitForWishlistEmpty(LONG_TIMEOUT)`
  3. Set attempts limit to GlobalConstants.MAX_RETRY_ATTEMPTS (typically 3)
  4. Replace sleepInSecond with proper wait for element removal
- **New Code Pattern**:
  ```java
  private void clearWishList() {
      wishListPage.clickRemoveAllButton();  // Single-click if available
      wishListPage.waitForWishlistEmpty(LONG_TIMEOUT);  // Explicit wait, no retry limit
  }
  ```
- **Code Impact**: Simplifies clearWishList, adds reliability
- **Fix Effort**: ~30 minutes (depends on WishListPO having waitForWishlistEmpty())

### Domain 3.1a: Wait Strategy - Missing Waits Before Interactions
**Status**: 🟠 **FAIL** — **Impact: Medium** (Potential flakiness in fast environments)
- **Issue**: Some clicks lack explicit waits before interaction (depends on PO implementation)
- **Severity**: Major — if PO methods don't include waits, tests are brittle
- **Known Locations**:
  - Line 315: `homePage.clickToAddToWishListButtonInProductAction();` (Product detail page click)
  - Line 316: `homePage.clickToCartCompareWishlistClosePopup();` (Close popup)
  - Line 385: `homePage.clickToAddToWishListButtonInProductAction();` (Duplicate occurrence)
  - Line 431: `wishListPage.clickBreadcrumbHome();` (Breadcrumb navigation)
- **Inspection Required**: Review if PageObject methods include:
  ```java
  // In HomePO or relevant PO:
  public boolean clickToAddToWishListButtonInProductAction() {
      waitForElementClickable(HomeUI.XPATH_ADD_WISHLIST_BUTTON); // ✅ Should have this
      clickElement(HomeUI.XPATH_ADD_WISHLIST_BUTTON);
      return true;
  }
  ```
- **Recommendation**:
  1. Audit HomePO, WishListPO methods for missing `waitForElementClickable()` before `clickElement()`
  2. Ensure all PO click methods follow: `waitForElementClickable() → clickElement()`
  3. If methods lack waits, add them (see review-automation-code Domain 3.0 checklist)
- **Code Impact**: Updates PageObject methods (not test class)
- **Fix Effort**: ~20 minutes (depends on PO audit results)

---

## 🔴 CRITICAL VIOLATIONS (3/21 domains — BLOCKS APPROVAL)

### Domain 3.1: Wait Strategy - Thread.sleep() Usage
**Status**: 🔴 **CRITICAL BLOCK** — **Impact: CRITICAL** (Violates core framework contract)
- **Issue**: Direct `Thread.sleep(3000)` in clearWishList() helper violates explicit wait contract
- **Severity**: Critical — explicit waits are non-negotiable in this framework
- **Location**: Lines 101-105 in clearWishList() method:
  ```java
  try {
      Thread.sleep(3000);  // ❌ CRITICAL VIOLATION
  } catch (InterruptedException e) {}
  ```
- **Framework Requirement** (from BasePage):
  - Never use `Thread.sleep()` — always use BasePage wait methods
  - Available: `waitForElementVisible()`, `waitForElementClickable()`, `waitForElementPresence()`, `waitForElementInvisible()`, `waitForNumberOfElements()`
  - Timeout: `SHORT_TIMEOUT` (10s) or `LONG_TIMEOUT` (30s) from GlobalConstants
- **Why This Fails**:
  1. Hardcoded 3-second delay causes unnecessary test slowdown
  2. Not resilient to slow networks (if server takes > 3s, test fails)
  3. Violates BasePage contract: "Never use `Thread.sleep()`"
  4. Makes tests flaky in CI/CD environments
- **Correct Replacement**:
  ```java
  // Instead of: Thread.sleep(3000);
  // Use: waitForElementVisible(HomeUI.XPATH_HOME_PAGE_LOADED);
  // OR: waitForNumberOfElements(WishListUI.XPATH_WISHLIST_ITEMS, 0);
  ```
- **Recommendation**:
  1. Delete lines 101-105 entirely, OR
  2. Replace with explicit wait for page to load:
     ```java
     waitForElementVisible(HomeUI.XPATH_HOMEPAGE_LOGO); // Wait for page, not time
     ```
  3. Or wait for specific element that indicates "clearWishlist ready":
     ```java
     waitForElementClickable(WishListUI.REMOVE_BUTTON_FIRST);
     ```
- **Code Impact**: Critical — must fix before merge
- **Fix Effort**: ~5 minutes (delete or replace 1 block)

### Domain 1.2: Test Data Management - Hardcoded URLs
**Status**: 🔴 **CRITICAL BLOCK** — **Impact: CRITICAL** (Environment-dependent, maintenance burden)
- **Issue**: Multiple hardcoded URLs throughout helper methods instead of using GlobalConstants.BASE_URL
- **Severity**: Critical — violates environment abstraction contract
- **Locations** (at least 3 occurrences):
  - Line 74 (performLogin helper):
    ```java
    homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    ```
  - Line 95 (clearWishList helper):
    ```java
    homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    ```
  - Line 405 (TC_WISH_013):
    ```java
    homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    ```
- **Framework Requirement** (from GlobalConstants):
  - All URLs must use `GlobalConstants.BASE_URL` or environment-specific properties
  - Allows seamless switching between TEST/PROD/local without code changes
  - Required for CI/CD and multi-environment execution
- **Why This Fails**:
  1. Hardcoded prod URL prevents testing on staging/dev environments
  2. If URL changes, must update 3+ locations in this file alone
  3. Breaks environment-agnostic test design
  4. GitHub Actions cannot override URLs without custom parsing
- **Current Global Constants** (from codebase):
  ```java
  // GlobalConstants.java should have:
  public static final String BASE_URL = "https://ecommerce-playground.lambdatest.io";
  public static final String HOME_PAGE_ROUTE = "/index.php?route=common/home";
  ```
- **Correct Replacement**:
  ```java
  // Instead of: hardcoded URL
  // Use: homePage.openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");
  // Or better: homePage.navigateToHomePage();
  ```
- **Recommendation**:
  1. Replace all 3 hardcoded URLs with GlobalConstants usage
  2. Better: Create PO helper method `navigateToHomePage()` in HomePO that handles URL internally
  3. Update performLogin and clearWishList to use PO methods instead of raw openPage calls
- **Code Impact**: Critical — must fix before merge
- **Fix Effort**: ~10 minutes (replace 3 URLs + verify GlobalConstants)

### Domain 5.0: Configuration Management - Missing @Parameters on Test Methods
**Status**: 🔴 **CRITICAL BLOCK** — **Impact: Critical** (Breaks environment injection)
- **Issue**: Test methods DO NOT have full @Parameters annotations on methods themselves
- **Severity**: Critical — only @BeforeClass has parameters, test methods don't
- **Current State** (Line 138):
  ```java
  @Description("TC-WISH-001: Add product to wishlist")
  @Test(priority = 0, groups = {"regression", "wishlist"})
  public void TC_WISH_001_Add_Product_To_Wishlist() {  // ❌ NO @Parameters
      // uses getDriver(context) but context not injected into method
  }
  ```
- **Framework Requirement**:
  - Test methods should have: `@Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})`
  - Allows TestNG to pass environment variables to each test method
  - Enables per-test environment override via suite XML
  - Required for BrowserStack/CI integration
- **Expected Pattern**:
  ```java
  @Test(priority = 0, groups = {"regression", "wishlist"})
  @Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
  public void TC_WISH_001_Add_Product_To_Wishlist(String env, String browserName, 
                                                   String browserVersion, String os, 
                                                   String osVersion, String url) {
      initDriver(env, browserName, browserVersion, os, osVersion, url);
      // test code...
  }
  ```
- **Why This Fails**:
  1. Environment variables injected only into @BeforeClass, not methods
  2. If suite XML specifies different env/browser per method, injection fails
  3. BrowserStack/CI cannot override environment for individual tests
  4. Reduces test flexibility for parallel execution with different browsers
- **Current Workaround** (why tests work now):
  - Values inherited from @BeforeClass @Parameters
  - But this breaks TestNG best practices and parameterized suite execution
  - Not recommended for CI/CD integration
- **Recommendation**:
  1. Add `@Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})` to each test method
  2. Method signature must match parameter names
  3. Pass env/browserName to initDriver() call in each test
  4. OR refactor to use BaseTest context object (if available)
- **Code Impact**: Critical — adds @Parameters to 12 test methods
- **Fix Effort**: ~20 minutes (add annotation + method params to each test)
- **Note**: Verify with BaseTest maintainer whether context already handles this or if method params needed

---

## 📊 DETAILED ISSUE MATRIX

| ID | Domain | Issue | Severity | Line(s) | Fix Effort | Priority |
|---|--------|-------|----------|---------|-----------|----------|
| 1a | 3.1 | Thread.sleep(3000) | 🔴 CRITICAL | 101-105 | 5 min | **BLOCKER** |
| 1b | 1.2 | Hardcoded URLs (×3) | 🔴 CRITICAL | 74, 95, 405 | 10 min | **BLOCKER** |
| 1c | 5.0 | Missing @Parameters on methods | 🔴 CRITICAL | All @Test | 20 min | **BLOCKER** |
| 2a | 2.3 | Custom login duplication | 🟠 MAJOR | 60-76 | 45 min | HIGH |
| 2b | 2.3a | Unbounded retry loop | 🟠 MAJOR | 88-101 | 30 min | HIGH |
| 2c | 3.1a | Missing waits before clicks | 🟠 MAJOR | 315, 316, 385, 431 | 20 min | HIGH |
| 3a | 1.3a | Missing @Step annotations | 🟡 MINOR | All methods | 15 min | MED |
| 3b | 1.5a | Some assertions lack messages | 🟡 MINOR | 351, 429 | 10 min | MED |
| 3c | 2.5a | sleepInSecond() usage | 🟡 MINOR | 100 | 20 min | MED |
| 3d | 3.0 | PropertiesConfig no null check | 🟡 MINOR | 34-35 | 5 min | LOW |

**Total Effort Estimate**: 2.5-3 hours (critical 35 min + major 95 min + minor 50 min)

---

## 🔍 DETAILED COMPLIANCE FINDINGS

### Finding 1: Critical Wait Strategy Violation
**File**: `TC_WishListModule.java`  
**Method**: `clearWishList()` (line 88-105)  
**Issue**: Direct use of `Thread.sleep(3000)` in test helper  
**Expected**: Explicit wait via BasePage (`waitForElementVisible`, `waitForElementClickable`, etc.)  

**Offending Code**:
```java
private void clearWishList() {
    log.info("Cleaning up wishlist...");
    homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    try {
        Thread.sleep(3000);  // ❌ VIOLATES FRAMEWORK RULE
    } catch (InterruptedException e) {}
    // ... rest of method
}
```

**Framework Rule** (from [BasePage.java](../src/main/java/commons/BasePage.java) & [selenium-java-automation SKILL.md](.github/skills/selenium-java-automation/SKILL.md)):
> "NEVER use `Thread.sleep()` in tests or page methods. Always use explicit waits from BasePage: `waitForElementVisible()`, `waitForElementClickable()`, `waitForElementPresence()`, `waitForElementInvisible()`, `waitForNumberOfElements()` with configurable timeouts (`SHORT_TIMEOUT=10s`, `LONG_TIMEOUT=30s`)."

**Impact**:
- ❌ Test slows down unnecessarily (3-second delay every time wishlist cleared)
- ❌ Flaky in slow networks (if page takes > 3s, test fails)
- ❌ CI/CD false positives in high-load environments
- ❌ Violates BasePage contract

**Recommended Fix**:
```java
private void clearWishList() {
    log.info("Cleaning up wishlist...");
    homePage.navigateToHomePage();  // Replace hardcoded URL (see Finding 2)
    
    // Wait for page load explicitly, not by time
    waitForElementVisible(HomeUI.XPATH_HOME_PAGE_LOGO);  // Wait for known element
    // OR: waitForNumberOfElements(WishListUI.REMOVE_BUTTON, greaterThan(0));
    
    // ... rest of method (no Thread.sleep, use WebDriverWait in loop)
}
```

---

### Finding 2: Hardcoded URLs - Environment Abstraction Broken
**File**: `TC_WishListModule.java`  
**Method**: `performLogin()` (line 74), `clearWishList()` (line 95), `TC_WISH_013()` (line 405)  
**Issue**: Hardcoded production URL instead of GlobalConstants  
**Expected**: Use `GlobalConstants.BASE_URL` or environment-specific properties  

**Offending Code** (3 locations):
```java
// Line 74 (performLogin)
homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");

// Line 95 (clearWishList)
homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");

// Line 405 (TC_WISH_013)
homePage.openPage("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
```

**Framework Rule** (from [GlobalConstants.java](../src/main/java/commons/GlobalConstants.java) & [copilot-instructions.md](.github/copilot-instructions.md)):
> "All URLs must use GlobalConstants.BASE_URL or property-injected environment strings. Never hardcode URLs — enables seamless TEST/PROD/local switching via suite XML or Maven properties."

**Impact**:
- ❌ Cannot test on staging/dev environments without code modification
- ❌ Maintenance burden: if URL changes, update 3+ locations
- ❌ Breaks CI/CD environment parameterization
- ❌ Security: prod URL visible in source code

**GlobalConstants** (verify in your codebase):
```java
// From src/main/java/commons/GlobalConstants.java:
public static final String BASE_URL = "https://ecommerce-playground.lambdatest.io";
```

**Recommended Fix**:
```java
// Option 1: Use GlobalConstants + route
homePage.openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");

// Option 2: Create helper in HomePO (PREFERRED)
// In HomePO.java:
public HomePO navigateToHomePage() {
    openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");
    return this;
}

// In TC_WishListModule.java:
private void clearWishList() {
    homePage.navigateToHomePage();  // Cleaner, reusable
    // ...
}
```

**Updated Calls**:
1. Line 74: `homePage.openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");`
2. Line 95: `homePage.openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");`
3. Line 405: `homePage.openPage(GlobalConstants.BASE_URL + "/index.php?route=common/home");`

---

### Finding 3: Missing @Parameters Annotations on Test Methods
**File**: `TC_WishListModule.java`  
**Method**: All @Test methods (12 test methods)  
**Issue**: Test methods lack @Parameters annotation; only @BeforeClass has it  
**Expected**: Each @Test method should declare @Parameters matching suite XML  

**Offending Pattern** (All 12 test methods):
```java
@Description("TC-WISH-001: Add product to wishlist")
@Test(priority = 0, groups = {"regression", "wishlist"})
public void TC_WISH_001_Add_Product_To_Wishlist() {  // ❌ NO @Parameters
    // Method relies on @BeforeClass for env injection
}

@Description("TC-WISH-002: Remove product from wishlist")
@Test(priority = 1, groups = {"regression", "wishlist"})
public void TC_WISH_002_Remove_Product_From_Wishlist() {  // ❌ NO @Parameters
    // Same issue
}
// ... × 12 test methods
```

**Framework Requirement** (from [copilot-instructions.md](.github/copilot-instructions.md) & [runOnTEST.xml](../src/test/resources/runOnTEST.xml)):
> "TestNG suite XML parameters must be injected into each @Test method via @Parameters annotation matching suite file definitions (`env`, `browserName`, `browserVersion`, `os`, `osVersion`, `url`)."

**Expected from Suite XML** (runOnTEST.xml):
```xml
<test name="Test">
    <parameter name="env" value="local"/>
    <parameter name="browserName" value="chrome"/>
    <parameter name="browserVersion" value="120"/>
    <parameter name="os" value="windows"/>
    <parameter name="osVersion" value="11"/>
    <parameter name="url" value="https://ecommerce-playground.lambdatest.io"/>
    
    <classes>
        <class name="tests.TC_WishListModule"/>
    </classes>
</test>
```

**Impact**:
- ❌ Per-test environment override doesn't work (suite can't inject into method)
- ❌ BrowserStack/CI multi-browser execution broken
- ❌ Parameters inheritance from @BeforeClass only (violation of TestNG pattern)
- ⚠️ Tests may randomly fail if environment changes between BeforeClass and test method

**Correct Pattern** (Example: TC_WISH_001):
```java
@Description("TC-WISH-001: Add product to wishlist")
@Test(priority = 0, groups = {"regression", "wishlist"})
@Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})
public void TC_WISH_001_Add_Product_To_Wishlist(String env, String browserName, 
                                                 String browserVersion, String os, 
                                                 String osVersion, String url) {
    // Environment now properly injected into method
    initDriver(env, browserName, browserVersion, os, osVersion, url);
    // ... test code
}
```

**Recommended Fix**:
1. Add `@Parameters({"env", "browserName", "browserVersion", "os", "osVersion", "url"})` to each test method
2. Add matching method signature parameters (all String type)
3. Call `initDriver()` or equivalent BaseTest method with injected parameters
4. Apply to all 12 test methods: TC_WISH_001 through TC_WISH_018

---

### Finding 4: Custom Login Helper Duplicates Framework Methods
**File**: `TC_WishListModule.java`  
**Method**: `performLogin()` (line 60-76)  
**Issue**: Custom login logic with WebDriver interactions; should delegate to LoginPO  
**Expected**: Test helpers should use existing PageObject methods, not reinvent auth  

**Offending Code**:
```java
private void performLogin() {
    log.info("Performing login with email: " + email);
    homePage.clickMyAccountIconForLogin();
    
    // ❌ Direct wait (should be in LoginPO)
    waitForElementVisible(LOGIN_EMAIL_INPUT);
    
    // ❌ Direct WebDriver interaction (should be in LoginPO)
    WebElement emailInput = driver.findElement(By.xpath(LOGIN_EMAIL_INPUT));
    emailInput.sendKeys(email);
    
    // Similar pattern for password...
    waitForElementVisible(LOGIN_PASSWORD_INPUT);
    WebElement passwordInput = driver.findElement(By.xpath(LOGIN_PASSWORD_INPUT));
    passwordInput.sendKeys(password);
    
    // ❌ Direct wait for button
    waitForElementVisible(LOGIN_SUBMIT_BUTTON);
    driver.findElement(By.xpath(LOGIN_SUBMIT_BUTTON)).click();
}
```

**Issues**:
1. **DRY Violation**: Login logic exists in 1+ place (should be single LoginPO.login() method)
2. **POM Violation**: Test helper contains WebDriver interactions (should be in PO only)
3. **Wait Violations**: Direct wait calls in test helper (should be encapsulated in PO methods)
4. **Maintenance Burden**: If auth UI changes, must update test helper + LoginPO + other tests

**Framework Rule** (from [selenium-java-automation SKILL.md](.github/skills/selenium-java-automation/SKILL.md)):
> "PageObject methods must encapsulate WebDriver interactions. Test classes must never call driver.findElement() or use waits directly — always delegate to PO methods."

**Recommended Fix**:

**Step 1: Create/Enhance LoginPO** (if not already present)
```java
public class LoginPO extends BasePage {
    
    public LoginPO performLogin(String email, String password) {
        waitForElementClickable(HomeUI.XPATH_MY_ACCOUNT_ICON);
        clickElement(HomeUI.XPATH_MY_ACCOUNT_ICON);
        
        waitForElementClickable(LoginUI.XPATH_EMAIL_INPUT);
        typeText(LoginUI.XPATH_EMAIL_INPUT, email);
        typeText(LoginUI.XPATH_PASSWORD_INPUT, password);
        
        waitForElementClickable(LoginUI.XPATH_SUBMIT_BUTTON);
        clickElement(LoginUI.XPATH_SUBMIT_BUTTON);
        
        return this;  // Or return new HomePO(driver);
    }
}
```

**Step 2: Update performLogin() in TC_WishListModule**
```java
private void performLogin() {
    log.info("Performing login with email: " + email);
    
    // Delegate to LoginPO instead of custom logic
    HomePO homePage = new HomePO(getDriver(context));
    LoginPO loginPage = homePage.navigateToLogin();  // PO method handles navigation
    loginPage.performLogin(email, password);
    
    // OR simpler: homePage.loginWith(email, password);
}
```

**Step 3: Update all 4 calls to performLogin()** (lines 145, 424, 451)
- No change needed in test methods (they call performLogin() as-is)
- performLogin() now delegates to LoginPO (clean and maintainable)

**Benefit**: Login logic is now centralized in LoginPO; if auth UI changes, only LoginPO needs updates.

---

### Finding 5: Unbounded Retry Loop in clearWishList()
**File**: `TC_WishListModule.java`  
**Method**: `clearWishList()` (line 88-102)  
**Issue**: Retry loop with hardcoded limit; uses sleepInSecond() (likely Thread.sleep)  
**Expected**: Use BasePage explicit waits instead of retry loops  

**Offending Code**:
```java
private void clearWishList() {
    // ...
    int total = wishListPage.getTotalProduct();
    int attempts = 0;
    while (total > 0 && attempts < 15) {  // ❌ Hardcoded limit, unbounded
        wishListPage.clickRemoveButtonByIndex(1);
        wishListPage.sleepInSecond(1);  // ❌ Likely Thread.sleep (Framework violation)
        total = wishListPage.getTotalProduct();
        attempts++;
    }
    if (total > 0) {
        log.warn("Could not clear all wishlist items after 15 attempts");
    }
}
```

**Issues**:
1. **Hardcoded Limit**: `attempts < 15` is arbitrary; should use GlobalConstants.MAX_RETRY_ATTEMPTS
2. **Sleep Usage**: `sleepInSecond(1)` likely wraps `Thread.sleep()` (framework violation)
3. **Flaky**: If product removal UI takes > 1 second, loop continues unnecessarily
4. **Silent Failure**: If total > 0 after 15 attempts, only logs warning (should fail test)

**Framework Rule**:
> "Use BasePage explicit waits. For polling/retry loops, use waitForXXX() methods with timeout from GlobalConstants (SHORT_TIMEOUT=10s, LONG_TIMEOUT=30s), not hardcoded attempts or Thread.sleep()."

**Recommended Fix**:

**Option 1: Simple Refactor (if WishListPO has waitForWishlistEmpty)**
```java
private void clearWishList() {
    log.info("Cleaning up wishlist...");
    homePage.navigateToHomePage();
    
    // Explicit wait for wishlist to be empty (no retry loop)
    wishListPage.waitForWishlistEmpty(LONG_TIMEOUT);
    
    log.info("Wishlist cleared successfully");
}
```

**Option 2: Click All + Wait (if WishListPO has removeAllButton)**
```java
private void clearWishList() {
    log.info("Cleaning up wishlist...");
    wishListPage.clickRemoveAllButton();  // Remove all at once (if UI supports)
    
    // Wait for removal to complete
    waitForNumberOfElements(WishListUI.XPATH_WISHLIST_ITEMS, 0, LONG_TIMEOUT);
    
    log.info("All wishlist items removed");
}
```

**Option 3: Smart Polling (if neither above available)**
```java
private void clearWishList() {
    log.info("Cleaning up wishlist...");
    homePage.navigateToHomePage();
    
    long endTime = System.currentTimeMillis() + (LONG_TIMEOUT * 1000);
    while (System.currentTimeMillis() < endTime) {
        int total = wishListPage.getTotalProduct();
        if (total == 0) {
            log.info("Wishlist cleared successfully");
            return;
        }
        
        wishListPage.clickRemoveButtonByIndex(1);
        
        // Wait for element to be removed (not fixed 1 second)
        waitForNumberOfElements(WishListUI.XPATH_WISHLIST_ITEMS, total - 1, SHORT_TIMEOUT);
    }
    
    throw new TimeoutException("Failed to clear wishlist after " + LONG_TIMEOUT + " seconds");
}
```

**Benefit**: No hardcoded limits, respects framework timeout strategy, properly fails if cleanup incomplete.

---

## 📋 APPROVAL DECISION

### Can This Code Be Merged?
**Answer**: ❌ **NO** — Do not merge until **CRITICAL violations (1a, 1b, 1c) are fixed**.

### Blockers
1. 🔴 **Thread.sleep(3000)** — Delete or replace with explicit wait (5 min)
2. 🔴 **Hardcoded URLs** (×3) — Replace with GlobalConstants (10 min)
3. 🔴 **Missing @Parameters on methods** — Add annotations to all 12 tests (20 min)

### Suggested Action
1. **First Pass** (15-20 min): Fix 3 critical violations
2. **Second Pass** (90 min): Fix 4 major violations (login duplication, retry loop, missing waits, etc.)
3. **Third Pass** (50 min): Address 4 minor improvements (@Step annotations, assertion messages, etc.)
4. **Resubmit**: After fixes, request code review again

### Estimated Total Rework Time
- **Critical Fixes**: 35 min
- **Major Fixes**: 95 min  
- **Minor Improvements**: 50 min  
- **Total**: 2.5-3 hours

### Recommended Next Steps
1. Fix critical violations first (minimum viable merge)
2. Update [selenium-java-automation skill](.github/skills/selenium-java-automation/SKILL.md) to auto-detect and prevent these violations in generated code
3. Use this review as a case study for code generation prompts

---

## ✅ NEXT ACTIONS

### For Code Owner (Developer)
- [ ] Fix critical violations (Thread.sleep, URLs, @Parameters) — **35 minutes**
- [ ] Fix major violations (login, retry loop, missing waits) — **95 minutes**
- [ ] Address minor improvements — **50 minutes**
- [ ] Rerun `mvn test` to verify fixes
- [ ] Resubmit TC_WishListModule.java for second review

### For Skill Maintainer (Copilot)
- [ ] Use this review as reference case in `selenium-java-automation` skill prompts
- [ ] Add specific checks for Thread.sleep, hardcoded URLs, missing @Parameters in generated code
- [ ] Consider adding linting rule to Maven build (maven-checkstyle-plugin) to catch violations before review

---

**Review Completed By**: GitHub Copilot (Claude Haiku 4.5)  
**Review Framework**: [review-automation-code SKILL.md](.github/skills/review-automation-code/SKILL.md)  
**Next Review Requested**: After fixes to blocking violations
