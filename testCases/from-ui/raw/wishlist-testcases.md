# Wishlist Test Cases (Generated from UI)

**Source URL:** https://ecommerce-playground.lambdatest.io/index.php?route=account/wishlist  
**Entry point:** https://ecommerce-playground.lambdatest.io/index.php?route=common/home  
**Saved to:** testCases/from-ui/wishlist-testcases.md  
**Scope:** standard  

**Assumptions:**
- A registered test account exists (or can be created) for login-required flows.
- "Add to Wish List" from a product page is the primary entry point to populate the wishlist.
- Out-of-stock products in wishlist cannot be added to cart and must show an error notification.
- The "Continue" button on wishlist always redirects to Homepage (`route=common/home`).
- Adding the same product to wishlist twice must show a duplicate warning message.
- Max number of wishlist items is not explicitly shown; assumed unlimited unless confirmed otherwise.

---

## TC-WISH-001: Navigate to Wishlist while logged out — redirect to Login
**Visual ref:** Header Wishlist icon (heart, top right area)  
**Req ID:** SEC-WISHLIST-001  
**Priority:** P0 (Critical)  
**Risk rationale:** Unauthenticated access to wishlist must be blocked; failure exposes a security gap.  
**Technique tags:** Equivalence (unauthenticated), Security, Error Handling  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open homepage: `https://ecommerce-playground.lambdatest.io/index.php?route=common/home` | Homepage loads successfully |
| 2 | Verify user is logged out (no active account session) | No account indicator or Logout option shown |
| 3 | Click the Wishlist icon in the top header | User is redirected to the Login page (`route=account/login`) |
| 4 | Verify page heading | Heading "Returning Customer" or "Login" is displayed |

---

## TC-WISH-002: Navigate to Wishlist while logged in — empty state
**Visual ref:** "My Wish List" heading, "No results!" message, "Continue" button  
**Req ID:** US-WISHLIST-001  
**Priority:** P1 (High)  
**Risk rationale:** Logged-in users must see their empty wishlist, not a broken page.  
**Technique tags:** Equivalence (valid / empty state), Navigation  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open homepage | Homepage loads successfully |
| 2 | Log in with valid registered credentials for an account with empty wishlist | Login succeeds, redirected to account page |
| 3 | Click the Wishlist icon in the top header | Navigated to `route=account/wishlist` |
| 4 | Verify page heading | "My Wish List" is displayed |
| 5 | Verify empty state message | "No results!" is displayed |
| 6 | Verify action button | "Continue" button is visible and clickable |

---

## TC-WISH-003: Add product to Wishlist from Homepage product card (logged in)
**Visual ref:** Product card hover → Wishlist heart icon on product card  
**Req ID:** US-WISHLIST-002  
**Priority:** P0 (Critical)  
**Risk rationale:** Core add-to-wishlist flow; failure blocks all wishlist population.  
**Technique tags:** Equivalence (valid), Risk-based  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open homepage and log in with valid credentials | Login succeeds |
| 2 | Hover over the first product card in the Top Products section | Product action icons appear (Cart, Wishlist, Compare, Quick View) |
| 3 | Click the Wishlist heart icon on product "iMac" | A success notification appears: "Success: You have added iMac to your wish list!" |
| 4 | Navigate to Wishlist page via the header icon | Wishlist page loads at `route=account/wishlist` |
| 5 | Verify the product appears in the wishlist table | Product Name, Model, Stock, Unit Price and action buttons are visible in the table |

---

## TC-WISH-004: Add product to Wishlist from Product Detail page (logged in)
**Visual ref:** Product Detail page → "Add to Wish List" button  
**Req ID:** US-WISHLIST-003  
**Priority:** P1 (High)  
**Risk rationale:** Product detail page is a primary entry point for adding items to wishlist.  
**Technique tags:** Equivalence (valid), Navigation  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open homepage and log in with valid credentials | Login succeeds |
| 2 | Click product "iMac" to open the Product Detail page | Product Detail page loads |
| 3 | Click the "Add to Wish List" button on the detail page | Success notification: "Success: You have added iMac to your wish list!" |
| 4 | Navigate to Wishlist page | Product "iMac" is listed in the wishlist table |

---

## TC-WISH-005: Attempt to add product to Wishlist while logged out
**Visual ref:** "You must login or create an account to save iMac to your wish list!" alert  
**Req ID:** SEC-WISHLIST-002  
**Priority:** P1 (High)  
**Risk rationale:** System must prevent wishlist saving without session; error message quality matters for UX.  
**Technique tags:** Equivalence (invalid / unauthenticated), Error Handling, Security  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Open homepage without logging in | Homepage loads, user is a guest |
| 2 | Hover over the first product card in the Top Products section | Product action icons appear |
| 3 | Click the Wishlist heart icon on product "iMac" | An alert/notification appears: "You must login or create an account to save iMac to your wish list!" |
| 4 | Verify the alert contains a login link | A link to the login page is present in the notification |
| 5 | Click the login link in the notification | User is redirected to `route=account/login` |

---

## TC-WISH-006: Wishlist table columns verification with a product
**Visual ref:** Populated wishlist table — columns: Image, Product Name, Model, Stock, Unit Price, Action  
**Req ID:** US-WISHLIST-004  
**Priority:** P1 (High)  
**Risk rationale:** Data integrity of the wishlist display is critical for user trust.  
**Technique tags:** Equivalence (valid), Quality  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add at least one product to the wishlist | Product is added |
| 2 | Navigate to Wishlist page | Wishlist page loads |
| 3 | Verify the table has 6 columns | Columns "IMAGE", "PRODUCT NAME", "MODEL", "STOCK", "UNIT PRICE", "ACTION" are all visible |
| 4 | Verify IMAGE column | A product thumbnail image is displayed |
| 5 | Verify PRODUCT NAME column | Product name is displayed as a clickable link |
| 6 | Verify MODEL column | Product model/code is displayed |
| 7 | Verify STOCK column | Stock status (e.g., "In Stock" or "Out Of Stock") is displayed |
| 8 | Verify UNIT PRICE column | Price with currency symbol (e.g., "$170.00") is displayed |
| 9 | Verify ACTION column | Two buttons are shown: Add to Cart (cart icon) and Remove (× icon) |

---

## TC-WISH-007: Product Name link in Wishlist redirects to Product Detail page
**Visual ref:** PRODUCT NAME column — clickable blue link  
**Req ID:** US-WISHLIST-005  
**Priority:** P2 (Medium)  
**Risk rationale:** Navigation from wishlist to product detail is a core user journey.  
**Technique tags:** Navigation, Equivalence (valid)  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and navigate to a populated wishlist | Wishlist page loads with at least one product |
| 2 | Click the product name link (e.g., "iMac") in the PRODUCT NAME column | User is redirected to the Product Detail page for that product |
| 3 | Verify URL contains `route=product/product&product_id=` | URL matches the product's detail page |
| 4 | Verify product page heading matches the wishlist product name | Page heading is "iMac" |

---

## TC-WISH-008: Remove product from Wishlist
**Visual ref:** ACTION column — Remove button (red × circle)  
**Req ID:** US-WISHLIST-006  
**Priority:** P0 (Critical)  
**Risk rationale:** Remove is a core wishlist action; failure leaves users unable to manage their list.  
**Technique tags:** Equivalence (valid), Risk-based  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add at least one product to the wishlist | Product appears in wishlist |
| 2 | Navigate to Wishlist page | Wishlist page loads with the product |
| 3 | Click the Remove button (× icon) in the ACTION column for the product | The product row is removed from the list |
| 4 | Verify page update after remove action | Success notification appears and removed product row no longer exists in wishlist table |
| 5 | Navigate away then return to Wishlist page | Removed product is still absent (deletion persists) |

---

## TC-WISH-009: Add product to Cart from Wishlist
**Visual ref:** ACTION column — Add to Cart button (cart icon)  
**Req ID:** US-WISHLIST-007  
**Priority:** P0 (Critical)  
**Risk rationale:** Wishlist-to-cart is a primary conversion flow; failure blocks the purchase journey.  
**Technique tags:** Equivalence (valid), Risk-based  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add an in-stock product ("iMac") to the wishlist | Product appears in wishlist |
| 2 | Navigate to Wishlist page | Wishlist page loads |
| 3 | Verify STOCK column for "iMac" shows "In Stock" | Product is eligible for add-to-cart |
| 4 | Click the Add to Cart button (cart icon) in the ACTION column | Success notification: "Success: You have added iMac to your shopping cart!" |
| 5 | Navigate to the Cart page | Product "iMac" appears in the shopping cart with quantity = 1 |
| 6 | Navigate back to Wishlist page | Product remains in wishlist table |

---

## TC-WISH-010: Add out-of-stock product to Cart from Wishlist
**Visual ref:** STOCK column shows "Out Of Stock"; ACTION column still shows cart icon  
**Req ID:** US-WISHLIST-008  
**Priority:** P1 (High)  
**Risk rationale:** System must handle out-of-stock add-to-cart gracefully with a clear message.  
**Technique tags:** Equivalence (invalid — stock edge case), Error Handling, Boundary  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add an out-of-stock product (e.g., iMac showing "Out Of Stock") to the wishlist | Product appears in wishlist with "Out Of Stock" status |
| 2 | Navigate to Wishlist page | Wishlist page loads |
| 3 | Click the Add to Cart button (cart icon) for the out-of-stock product | Error toast/notification appears: product is out of stock and cannot be added |
| 4 | Verify the product is NOT added to the cart | Cart counter does not increase |
| 5 | Navigate to Cart page | Out-of-stock product is not present in cart |

---

## TC-WISH-011: Add same product to Wishlist twice
**Visual ref:** Wishlist table — product count  
**Req ID:** US-WISHLIST-009  
**Priority:** P2 (Medium)  
**Risk rationale:** Duplicate items in wishlist degrade user experience and data integrity.  
**Technique tags:** Boundary (duplicate entry), Error Handling  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add product "iMac" to the wishlist | Product appears in wishlist |
| 2 | Navigate back to the Product Detail page of "iMac" | Product detail page for "iMac" is displayed |
| 3 | Attempt to add "iMac" to the wishlist again | Warning notification appears: "You have already added iMac to your wish list!" |
| 4 | Navigate to Wishlist page | The product appears only once in the table |

---

## TC-WISH-012: Wishlist persists after page refresh
**Visual ref:** Wishlist page — product table  
**Req ID:** US-WISHLIST-010  
**Priority:** P2 (Medium)  
**Risk rationale:** Session state must be stable; losing wishlist on refresh is a serious usability bug.  
**Technique tags:** Session/State, Equivalence (valid)  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add a product to the wishlist | Product appears in wishlist |
| 2 | Navigate to Wishlist page | Wishlist page loads with the product |
| 3 | Press F5 or use the browser refresh button | Page refreshes |
| 4 | Verify the product is still in the wishlist table | The same product is still visible with all columns intact |

---

## TC-WISH-013: Wishlist persists after logout and re-login
**Visual ref:** Wishlist page after re-login  
**Req ID:** US-WISHLIST-011  
**Priority:** P1 (High)  
**Risk rationale:** Wishlist is a server-side persistent feature; data must survive session end.  
**Technique tags:** Session/State, Equivalence (valid)  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in with valid credentials | Login succeeds |
| 2 | Add a product to the wishlist | Product appears in wishlist |
| 3 | Log out from the account | User is logged out; session ends |
| 4 | Log in again with the same credentials | Login succeeds |
| 5 | Navigate to Wishlist page | The product added in Step 2 is still present in the wishlist |

---

## TC-WISH-014: "Continue" button on wishlist redirects to Homepage (empty and populated states)
**Visual ref:** "Continue" button below "No results!" message or below wishlist table  
**Req ID:** US-WISHLIST-012  
**Priority:** P2 (Medium)  
**Risk rationale:** Continue is the main exit action from wishlist and must be consistent in all states.  
**Technique tags:** Navigation, State-based  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Scenario A: Log in and ensure wishlist is empty, then open Wishlist page | Wishlist shows "No results!" and "Continue" button is visible |
| 2 | Click "Continue" in empty state | User is redirected to Homepage (`route=common/home`) |
| 3 | Scenario B: Add at least one product, then open Wishlist page | Wishlist table is visible and "Continue" button is visible |
| 4 | Click "Continue" in populated state | User is redirected to Homepage (`route=common/home`) |
| 5 | Verify page status for both scenarios | Homepage loads successfully with no broken/error page |

---

## TC-WISH-016: Wishlist count in header updates after adding a product
**Visual ref:** Wishlist icon in top header  
**Req ID:** US-WISHLIST-013  
**Priority:** P2 (Medium)  
**Risk rationale:** Header counter reflects wishlist state; inconsistency confuses users.  
**Technique tags:** Equivalence (valid), UI State  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and navigate to the homepage | Header wishlist icon is visible |
| 2 | Note the current wishlist icon state (no count badge or count = 0) | Baseline count recorded |
| 3 | Add one product to the wishlist via hover → Wishlist icon on a product card | Success notification appears |
| 4 | Observe the Wishlist icon in the header | Numeric badge/count updates to reflect 1 item |

---

## TC-WISH-017: Keyboard accessibility on Wishlist page
**Visual ref:** Wishlist table — tab order through interactive elements  
**Req ID:** ACC-WISHLIST-001  
**Priority:** P2 (Medium)  
**Risk rationale:** Keyboard-only users must be able to operate the wishlist.  
**Technique tags:** Accessibility  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and navigate to Wishlist page with at least one product | Wishlist page loads |
| 2 | Press Tab from the page heading | Focus moves to the first Product Name link in wishlist table |
| 3 | Continue pressing Tab through all interactive elements | Focus order follows a logical sequence: Product link → Add to Cart → Remove → Continue |
| 4 | Press Enter on the "Add to Cart" button when focused | Success notification appears for add-to-cart action |
| 5 | Press Enter on the "Remove" button when focused | Product is removed from the list |

---

## TC-WISH-018: Breadcrumb navigation on Wishlist page
**Visual ref:** Breadcrumb: Home / Account / My Wish List  
**Req ID:** US-WISHLIST-014  
**Priority:** P3 (Low)  
**Risk rationale:** Breadcrumbs must be accurate and functional for wayfinding.  
**Technique tags:** Navigation  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and navigate to the Wishlist page | "My Wish List" breadcrumb is visible: Home / Account / My Wish List |
| 2 | Click "Home" in the breadcrumb | User is redirected to the Homepage |
| 3 | Navigate back to Wishlist page | Breadcrumb is still correct: Home / Account / My Wish List |
| 4 | Click "Account" in the breadcrumb | User is redirected to the Account page |

---

## TC-WISH-019: Sidebar "Wish List" link is active/highlighted on Wishlist page
**Visual ref:** Sidebar Account navigation — "Wish List" highlighted in teal/active color  
**Req ID:** US-WISHLIST-015  
**Priority:** P3 (Low)  
**Risk rationale:** Active sidebar state helps user know their location.  
**Technique tags:** UI State, Navigation  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and navigate to Wishlist page | Page loads at `route=account/wishlist` |
| 2 | Inspect the sidebar navigation | "Wish List" menu item is highlighted (active background color, e.g., teal) |
| 3 | Click "My Account" in the sidebar | User is redirected to the My Account page |
| 4 | Verify "Wish List" is no longer the active item | "My Account" is now highlighted instead |

---

## TC-WISH-020: Wishlist page loading performance
**Visual ref:** Full page load including table and sidebar  
**Req ID:** PERF-WISHLIST-001  
**Priority:** P2 (Medium)  
**Risk rationale:** Slow loading degrades user experience, especially with large wishlists.  
**Technique tags:** Performance  

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Log in and add several products to the wishlist (3+) | Multiple products appear in wishlist |
| 2 | Navigate away from the page | User is on a different page |
| 3 | Navigate back to the Wishlist page and start timing with browser DevTools Network/Performance panel | Page begins loading |
| 4 | Verify the page fully loads within an acceptable time threshold (≤ 5 seconds) | All table rows, images, and buttons render within 5 seconds |

---

### Traceability Matrix

| TC ID | Req ID | Feature/Flow | UI Element(s) | Technique(s) | Priority | Requirement Intent |
|-------|--------|--------------|---------------|--------------|----------|--------------------|
| TC-WISH-001 | SEC-WISHLIST-001 | Access Control | Header Wishlist icon | Equivalence(unauth), Security | P0 | Unauthenticated users cannot access wishlist |
| TC-WISH-002 | US-WISHLIST-001 | Empty State | "My Wish List" heading, "No results!", Continue | Equivalence(empty), Navigation | P1 | Logged-in users see empty state correctly |
| TC-WISH-003 | US-WISHLIST-002 | Add to Wishlist (Homepage) | Product card hover → Wishlist icon | Equivalence(valid), Risk-based | P0 | User can add products from homepage |
| TC-WISH-004 | US-WISHLIST-003 | Add to Wishlist (Detail) | Product Detail "Add to Wish List" button | Equivalence(valid), Navigation | P1 | User can add from product detail page |
| TC-WISH-005 | SEC-WISHLIST-002 | Add to Wishlist (Logged Out) | Add-to-wishlist alert notification | Equivalence(unauth), Security | P1 | Guest users get proper error message |
| TC-WISH-006 | US-WISHLIST-004 | Table Columns | Image, Name, Model, Stock, Price, Action columns | Equivalence(valid), Quality | P1 | Wishlist table displays all required data |
| TC-WISH-007 | US-WISHLIST-005 | Product Name Link | PRODUCT NAME column link | Navigation | P2 | Product link navigates to detail page |
| TC-WISH-008 | US-WISHLIST-006 | Remove from Wishlist | ACTION — Remove (×) button | Equivalence(valid), Risk-based | P0 | User can remove products from wishlist |
| TC-WISH-009 | US-WISHLIST-007 | Add to Cart from Wishlist | ACTION — Add to Cart button | Equivalence(valid), Risk-based | P0 | User can move items from wishlist to cart |
| TC-WISH-010 | US-WISHLIST-008 | Out-of-stock Add to Cart | STOCK "Out Of Stock" + Cart button | Equivalence(invalid), Error Handling | P1 | Out-of-stock items show error and are not added |
| TC-WISH-011 | US-WISHLIST-009 | Duplicate Add | Wishlist add action | Boundary(duplicate), Error Handling | P2 | Duplicate add shows warning and prevents duplicate row |
| TC-WISH-012 | US-WISHLIST-010 | Page Refresh Persistence | Wishlist table after refresh | Session/State | P2 | Wishlist survives browser refresh |
| TC-WISH-013 | US-WISHLIST-011 | Logout-Login Persistence | Wishlist table after re-login | Session/State | P1 | Wishlist data persists across sessions |
| TC-WISH-014 | US-WISHLIST-012 | Continue Button (Both States) | "Continue" button in empty/populated wishlist | Navigation, State-based | P2 | Continue always redirects to Homepage |
| TC-WISH-016 | US-WISHLIST-013 | Header Counter Update | Header Wishlist icon/badge | Equivalence(valid), UI State | P2 | Header reflects real-time wishlist count |
| TC-WISH-017 | ACC-WISHLIST-001 | Keyboard Accessibility | Wishlist table interactive elements | Accessibility | P2 | Wishlist is fully keyboard operable |
| TC-WISH-018 | US-WISHLIST-014 | Breadcrumb Navigation | Breadcrumb: Home / Account / My Wish List | Navigation | P3 | Breadcrumbs are accurate and clickable |
| TC-WISH-019 | US-WISHLIST-015 | Sidebar Active State | Sidebar "Wish List" item | UI State, Navigation | P3 | Active page highlighted in sidebar |
| TC-WISH-020 | PERF-WISHLIST-001 | Page Loading Performance | Full page load w/ products | Performance | P2 | Wishlist loads within 5 seconds |

---

### Coverage Summary

| Category | Count |
|----------|-------|
| Navigation | 5 |
| Access Control / Security | 2 |
| CRUD (Add/Remove/Cart) | 4 |
| Error Handling | 3 |
| Session/State | 2 |
| UI State / Display | 2 |
| Accessibility | 1 |
| Performance | 1 |
| **Total** | **19** |

| Priority | Count |
|----------|-------|
| P0 (Critical) | 4 |
| P1 (High) | 6 |
| P2 (Medium) | 6 |
| P3 (Low) | 2 |
