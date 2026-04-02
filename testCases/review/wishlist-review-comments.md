# Wishlist Test Cases — Comprehensive Review
**Reviewer:** Senior QA Lead (20+ years experience)  
**Review Date:** 2026-04-02  
**Scope:** 20 test cases from `testCases/from-ui/raw/wishlist-testcases.md`  
**Recommendation:** **CONDITIONAL READY** — Address critical gaps before execution

---

## Section 1 — Inline Test Case Review

**[TC-WISH-001]** Navigate to Wishlist while logged out  
Priority: P0 ✅  
✅ Good: Security flow covered correctly.  
⚠️ [MINOR][Quality] Step 2 "no session cookie" is too technical → Fix: "Ensure no active login session (use incognito or log out first)"

---

**[TC-WISH-002]** Navigate to Wishlist while logged in — empty state  
Priority: P1 ✅  
⚠️ [MAJOR][Quality] Missing explicit precondition: "User has no items in their wishlist"  
⚠️ [MINOR][Quality] Step 1 is compound (open + login) → Split into 2 atomic steps

---

**[TC-WISH-003]** Add product to Wishlist from Homepage (logged in)  
Priority: P0 ✅  
✅ Good: Core happy path, correct P0.  
⚠️ [MINOR][Quality] Step 3 expected result uses placeholder `[Product Name]` → Use concrete: "iMac"

---

**[TC-WISH-004]** Add product to Wishlist from Product Detail page  
Priority: P1 ✅  
⚠️ [MINOR][Quality] Step 2 "click any product" is vague → Specify product name e.g., "MacBook Air"

---

**[TC-WISH-005]** Add to Wishlist while logged out  
Priority: P1 ✅  
✅ Good: Important negative path.  
⚠️ [MINOR][Quality] Expected result uses placeholder → Use concrete product name

---

**[TC-WISH-006]** Wishlist table columns verification  
Priority: P1 ✅  
⚠️ [MAJOR][Quality] TC has 9 steps mixing setup + verification. Add explicit precondition.  
⚠️ [MINOR][Quality] Precondition should specify "at least one in-stock product in wishlist"

---

**[TC-WISH-007]** Product Name link redirects to Product Detail  
Priority: P2 ✅  
⚠️ [MINOR][Quality] Step 3 URL check too vague → Specify: URL must contain `route=product/product&product_id=`

---

**[TC-WISH-008]** Remove product from Wishlist  
Priority: P0 ✅  
⚠️ [CRITICAL][Quality] Step 4 has OR condition in expected result → Cannot be verified deterministically. Fix: Test actual behavior; use single concrete expected result.
→ Revised: "A success notification appears OR page refreshes showing the item removed. Based on testing: [confirm actual behavior]"

---

**[TC-WISH-009]** Add to Cart from Wishlist  
Priority: P0 ✅  
⚠️ [MAJOR][Quality] Precondition must explicitly state "in-stock product"  
⚠️ [MINOR][Coverage] Missing behavior: does product remain or get removed from wishlist after add-to-cart? Add Step 5.

---

**[TC-WISH-010]** Add out-of-stock product to Cart from Wishlist  
Priority: P1 ✅  
⚠️ [MAJOR][Quality] Step 3 has OR condition → Determine actual behavior (disabled button vs error message)

---

**[TC-WISH-011]** Add same product twice  
Priority: P2 → Recommend P1 (data integrity bug)  
⚠️ [MAJOR][Coverage] Expected result has OR condition → Determine actual duplicate handling behavior

---

**[TC-WISH-012]** Wishlist persists after page refresh  
Priority: P2 ✅  
⚠️ [MINOR][Quality] "Press F5" → "Refresh the browser page using browser's Refresh button"

---

**[TC-WISH-013]** Wishlist persists after logout/re-login  
Priority: P1 ✅  
✅ No major issues. Add explicit test account in precondition.

---

**[TC-WISH-014]** Continue button on empty wishlist  
Priority: P2 ✅  
⚠️ [MINOR][Quality] Redirect destination unclear → Confirm actual target URL after testing

---

**[TC-WISH-015]** Continue button on populated wishlist  
Priority: P3 ✅  
✅ No significant issues.

---

**[TC-WISH-016]** Wishlist count in header updates  
Priority: P2 ✅  
⚠️ [MINOR][Quality] Step 4 expected result vague ("count/icon... or visual indicator") → Confirm if header shows numeric badge

---

**[TC-WISH-017]** Keyboard accessibility  
Priority: P2 ✅  
⚠️ [MINOR][Quality] Steps 4 & 5 perform destructive actions (add/remove) within the same TC → May corrupt test state. Add cleanup or separate into dedicated TC.

---

**[TC-WISH-018]** Breadcrumb navigation: No significant issues. ✅  
**[TC-WISH-019]** Sidebar active state: No significant issues. ✅  
**[TC-WISH-020]** Performance: ⚠️ [MINOR] "Note load start time" is impractical for manual testing → Replace with "visually observe page load"

---

## Section 2 — Summary Scorecard

```
Feature: Wishlist (account/wishlist)
Total TCs reviewed: 20

| Dimension     | Score | Finding                                                      |
|---------------|-------|--------------------------------------------------------------|
| Coverage      |  8/10 | Missing: XSS search, session timeout                         |
| Quality       |  7/10 | 5 TCs have OR conditions in expected results                 |
| Traceability  |  9/10 | Matrix complete; no requirement IDs                          |
| Duplication   | 10/10 | No duplicates found                                          |
| Priority      |  9/10 | TC-WISH-011 should be P1                                     |
| OVERALL       |  8/10 |                                                              |

Statistics:
- Total issues: 18 (Critical: 1 | Major: 5 | Minor: 10 | Suggestion: 2)
- Must fix before automation: 6 (OR conditions in expected results)
- Missing TCs to add: 2
- TCs to remove: 0

Sign-off: CONDITIONAL
Condition: Resolve all OR-condition ambiguities in expected results before writing automation code.
```

---

## Section 3 — Missing Test Cases

### [GAP-01] XSS input sanitization on Search bar (from Wishlist page)
Priority: P1  
Risk: Reflected/stored XSS if search input is not sanitized  
Precondition: User is logged in and on the Wishlist page  
Steps:
  1. In the header search bar, enter `<script>alert(1)</script>`
  2. Click the Search button
Expected Result: Search results page loads; script is NOT executed; payload is HTML-encoded in the response

### [GAP-02] Wishlist state after session timeout
Priority: P2  
Risk: Wishlist items may be lost after session expires  
Precondition: User is logged in with products in wishlist  
Steps:
  1. Add products to wishlist
  2. Leave the browser idle past the session expiry period
  3. Attempt to navigate to the Wishlist page
Expected Result: User is redirected to Login page; after re-login, all previously added wishlist items are preserved
