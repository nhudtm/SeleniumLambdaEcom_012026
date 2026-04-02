# Blog Home Test Cases (Ready-to-Execute)

**Feature:** Blog Home  
**URL:** https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/blog/home  
**Source:** Live UI snapshot + review refinement  
**Document version:** v2 (execution-ready)

## 1) Scope, Method, Priority Scale

- **Scope:** Blog home page, blog navigation links, search behavior, slider controls, sidebar widgets, resilience/NFR
- **Methodologies:** Equivalence Partitioning, Boundary Value Analysis, Risk-based prioritization, Traceability mapping
- **Priority scale:**
  - **P0:** Blocker core flow
  - **P1:** Critical business/UX/security flow
  - **P2:** Important but workaround exists
  - **P3:** Minor impact

## 2) Test Environment & Global Preconditions

- Environment: TEST_AI
- Browser baseline: Chrome latest (desktop)
- Mobile checks: 390x844 and 768x1024
- Network baseline: Stable broadband (unless test case states throttling)
- Test account state: Anonymous user (not logged in) unless otherwise specified
- Cache/Cookies: Clear before each performance/resilience case

## 3) Test Data Set

| Data ID | Value | Use Case |
|---|---|---|
| D-SEARCH-VALID-01 | lorem | Valid search keyword |
| D-SEARCH-EMPTY-01 | "" (empty) | Empty search |
| D-SEARCH-SPECIAL-01 | !@#$%^&* | Special characters |
| D-SEARCH-XSS-01 | "><script>alert(1)</script> | Output encoding/XSS check |
| D-SEARCH-MIN-01 | a | Minimum inferred boundary |
| D-SEARCH-MAX-01 | 256-char alphanumeric string | Maximum inferred boundary |
| D-SEARCH-NORESULT-01 | zzz_non_existing_keyword_987654 | No result scenario |

---

## 4) Test Cases

### TC-BLOG-001: Open Blog Home successfully
**Priority:** P0  
**Req:** N/A (add AC-BLOG-ACCESS later)  
**Technique tags:** Smoke, Risk-based  
**Visual ref:** Blog container, section headers

**Preconditions:** Browser opened, network stable.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Navigate to blog home URL | HTTP 200 and page renders without browser/server error page |
| 2 | Observe main content area | Main blog container is visible |
| 3 | Verify key sections | "Latest Articles" and "Most viewed" headings are visible |

### TC-BLOG-002: Verify Latest Articles card rendering
**Priority:** P1  
**Req:** N/A  
**Technique tags:** UI Integrity  
**Visual ref:** Latest Articles slider cards

**Preconditions:** TC-BLOG-001 passed.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Scroll to Latest Articles | Section heading is visible |
| 2 | Inspect first visible card | Card shows image, title, author, comments, views, date |
| 3 | Inspect next 2 visible cards | No text clipping/overlap; card layout remains aligned |

### TC-BLOG-003: Verify Most viewed card rendering
**Priority:** P1  
**Req:** N/A  
**Technique tags:** UI Integrity  
**Visual ref:** Most viewed slider cards

**Preconditions:** TC-BLOG-001 passed.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Scroll to Most viewed section | Section heading is visible |
| 2 | Inspect first visible card | Card shows title, author, comments, views, date |
| 3 | Inspect next 2 visible cards | Layout is consistent and readable |

### TC-BLOG-004: Open article from Latest Articles
**Priority:** P0  
**Req:** N/A  
**Technique tags:** Navigation, Happy path  
**Visual ref:** First article title link in Latest Articles

**Preconditions:** TC-BLOG-002 passed.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click first article title in Latest Articles | User is redirected to article detail page |
| 2 | Observe article page | Article heading and content body are displayed |
| 3 | Check URL | URL contains `route=extension/maza/blog/article` |

### TC-BLOG-005: Open article from Most viewed
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Navigation  
**Visual ref:** First article title link in Most viewed

**Preconditions:** TC-BLOG-003 passed.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click first article title in Most viewed | User is redirected to article detail page |
| 2 | Check URL | URL contains `route=extension/maza/blog/article` |
| 3 | Verify article content | Heading and content area are visible |

### TC-BLOG-006: Open author profile from article card
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Link validation  
**Visual ref:** Author link in metadata row

**Preconditions:** At least one article card is visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click author name on any article card | Author page opens successfully |
| 2 | Check URL | URL contains `route=extension/maza/blog/author` |
| 3 | Verify author page content | Author-related article list is shown |

### TC-BLOG-007: Open blog category from category widget
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Navigation, Widget  
**Visual ref:** Sidebar category links

**Preconditions:** Category widget is visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click category link (e.g., Business) | Category listing page opens |
| 2 | Check URL | URL contains `route=extension/maza/blog/category` |
| 3 | Verify page context | Category title/filter context matches selected category |

### TC-BLOG-008: Validate category count labels format
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Data display  
**Visual ref:** Category labels with counts `(n)`

**Preconditions:** Category widget visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Observe all category items | Each category displays count in `(n)` format |
| 2 | Compare formatting | Same format applied consistently across items |

### TC-BLOG-009: Verify sidebar Latest widget presence and navigation
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Widget validation, Navigation  
**Visual ref:** Sidebar widget title "Latest"

**Preconditions:** Sidebar loaded.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Locate sidebar Latest widget | Widget title is visible |
| 2 | Click first product card in widget | User is redirected to product detail page |
| 3 | Validate URL pattern | URL contains `route=product/product` |

### TC-BLOG-010: Verify slider next navigation in Latest Articles
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Interaction, Boundary-like continuity  
**Visual ref:** Latest Articles next arrow

**Preconditions:** Latest Articles slider visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Record first visible card title | Baseline title captured |
| 2 | Click Next arrow once | First visible card title changes to a different item |
| 3 | Click Next arrow repeatedly until no new card appears | Slider reaches terminal state consistently (either disabled-end or wrap behavior) |
| 4 | Click Next arrow one more time at terminal state | No JavaScript error, no visual corruption, behavior remains consistent with step 3 |

### TC-BLOG-011: Verify slider previous navigation in Latest Articles
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Interaction  
**Visual ref:** Latest Articles previous arrow

**Preconditions:** Latest slider has been moved forward at least once.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Capture current first card title after moving next | Current title recorded |
| 2 | Click Previous arrow once | First visible card changes toward previous set |
| 3 | Repeat click Previous | Control remains responsive without UI corruption |

### TC-BLOG-012: Verify slider next navigation in Most viewed
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Interaction  
**Visual ref:** Most viewed next arrow

**Preconditions:** Most viewed slider visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Record current first card title | Baseline title captured |
| 2 | Click Next arrow once | First visible card title changes |
| 3 | Click Next repeatedly (5 times) | Slider remains stable and interactive |

### TC-BLOG-013: Verify slider previous navigation in Most viewed
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Interaction  
**Visual ref:** Most viewed previous arrow

**Preconditions:** Most viewed slider moved forward at least once.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click Previous arrow | Slider moves back to prior items |
| 2 | Repeat previous navigation | No visual corruption or control lock |

### TC-BLOG-014: Search with valid keyword
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Equivalence(valid), Navigation  
**Visual ref:** Header search textbox + search button

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-VALID-01` | Input accepts text |
| 2 | Submit search | User navigates to blog search results page |
| 3 | Check positive route | URL contains `route=extension/maza/blog/search` |
| 4 | Check negative route | URL does NOT contain `route=product/search` and results area is visible |

### TC-BLOG-015: Search with empty keyword
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Equivalence(invalid-empty), Error Handling  
**Visual ref:** Header search field

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Keep search input empty (`D-SEARCH-EMPTY-01`) | Input remains empty |
| 2 | Click search submit | System handles gracefully: no crash, no raw exception page |
| 3 | Verify UX response | Either validation feedback or stable empty/all-result state is displayed consistently |

### TC-BLOG-016: Search with special characters
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Equivalence(edge), Security, Error Handling  
**Visual ref:** Search textbox

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-SPECIAL-01` | Input accepted/sanitized |
| 2 | Submit search | No server/browser crash |
| 3 | Observe output | No stack trace/SQL path/debug info is exposed |

### TC-BLOG-017: Search boundary length - minimum inferred
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Boundary(min, inferred)  
**Visual ref:** Search textbox

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-MIN-01` | Input accepts 1 character |
| 2 | Submit search | Page remains stable and result state is rendered |

### TC-BLOG-018: Search boundary length - maximum inferred
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Boundary(max, inferred), Error Handling  
**Visual ref:** Search textbox

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-MAX-01` | Input is handled (accept/truncate) without UI freeze |
| 2 | Submit search | Search page responds with controlled state |
| 3 | Observe UI health | No crash and no sensitive error disclosure |

### TC-BLOG-019: Verify metadata consistency (comments/views/date)
**Priority:** P3  
**Req:** N/A  
**Technique tags:** Data display  
**Visual ref:** Article metadata row

**Preconditions:** At least 3 cards are visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Inspect 3 article cards | Each card shows comments, views, date |
| 2 | Compare formatting | Metadata style is consistent and readable |

### TC-BLOG-020: Verify image loading fallback and success threshold
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Error Handling, Performance  
**Visual ref:** Article thumbnails

**Preconditions:** Hard refresh performed.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Load page and observe thumbnails | Placeholder/loading behavior is controlled |
| 2 | Wait up to 5 seconds | At least 95% visible thumbnails render successfully |
| 3 | Check failures | No broken image icon flood in visible viewport |

### TC-BLOG-021: Responsive layout on mobile viewport
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Responsive, Accessibility  
**Visual ref:** Mobile header/search/blog sections

**Preconditions:** Browser dev tools device emulation enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Set viewport to 390x844 | Layout reflows with no severe overlap/cut-off |
| 2 | Set viewport to 768x1024 | Tablet layout remains readable and navigable |
| 3 | Verify slider controls in tablet viewport | Slider arrows are visible and tappable (not hidden behind content) |
| 4 | Scroll vertically through sections | No severe horizontal overflow and controls remain usable |

### TC-BLOG-022: Keyboard navigation accessibility
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Accessibility  
**Visual ref:** Search input + article/category links

**Preconditions:** Page focused, no mouse interaction during test.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Press `Tab` from search input through interactive elements | Focus indicator is visible and order is logical |
| 2 | Press `Enter` on focused article/category link | Action matches mouse click behavior |
| 3 | Continue tabbing across visible controls | No focus trap on visible page elements |

### TC-BLOG-023: Verify no sensitive error disclosure
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Security, Error Handling  
**Visual ref:** Error/feedback states

**Preconditions:** Search box enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Submit malformed query (`D-SEARCH-SPECIAL-01` or `D-SEARCH-XSS-01`) | UI remains controlled |
| 2 | Observe error/result area | No stack trace, SQL details, filesystem path, or debug info displayed |

### TC-BLOG-024: Performance baseline for initial blog render
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Performance  
**Visual ref:** Header + first section/cards

**Preconditions:** Cache cleared, stable network profile.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Open blog URL and start timing | Initial render starts without blocking |
| 2 | Measure first visible content | Header + first visible cards appear within 3 seconds |
| 3 | Observe usability | Page remains responsive during initial render |

### TC-BLOG-025: Empty/failure resilience for feed sections
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Error Handling, Resilience  
**Visual ref:** Latest/Most viewed containers

**Preconditions:** Ability to simulate partial feed failure using DevTools/proxy.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Block one feed-related request and reload | Page shell still renders |
| 2 | Observe affected section | Graceful fallback is shown (empty state/hidden broken block) |
| 3 | Observe unaffected section | Other sections continue to work |

### TC-BLOG-026: Invalid article id should show graceful not-found behavior
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Negative, Error Handling  
**Visual ref:** Article detail route

**Preconditions:** Construct article URL with non-existing `article_id`.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Open non-existing article URL | Not-found state renders (not raw server error page) |
| 2 | Inspect user-facing message | Message is understandable and non-technical |

### TC-BLOG-027: Search output encoding should prevent XSS execution
**Priority:** P0  
**Req:** N/A  
**Technique tags:** Security, Output encoding  
**Visual ref:** Search field + search result area

**Preconditions:** Search field enabled, popup blockers default.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-XSS-01` | Input accepted as plain text |
| 2 | Submit search | No script executes, no alert popup appears |
| 3 | Inspect rendered text | Payload is escaped/safely rendered |

### TC-BLOG-028: Browser Back/Forward should preserve stable navigation state
**Priority:** P2  
**Req:** N/A  
**Technique tags:** UX flow, Navigation  
**Visual ref:** Blog list + article detail

**Preconditions:** User can open any article from blog list.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Open an article from blog home | Article detail page loads |
| 2 | Click browser Back | Returns to blog listing page without error |
| 3 | Click browser Forward | Returns to same article page correctly |

### TC-BLOG-029: Search no-result state should be explicit and stable
**Priority:** P1  
**Req:** N/A  
**Technique tags:** Equivalence(invalid), UX  
**Visual ref:** Search results area

**Preconditions:** Search field enabled.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Enter `D-SEARCH-NORESULT-01` | Input accepted |
| 2 | Submit search | Search page loads successfully |
| 3 | Verify no-result UX | Clear empty-state message shown; no crash/blank page |

### TC-BLOG-030: Article thumbnail click behavior should match title-link behavior
**Priority:** P2  
**Req:** N/A  
**Technique tags:** Navigation, Link parity  
**Visual ref:** Latest Articles card thumbnail image

**Preconditions:** Blog home loaded and at least one Latest Articles card is visible.

| Step | Action | Expected Result |
|---|---|---|
| 1 | Click thumbnail image of first Latest Articles card (not title link) | Navigation behavior is triggered or image is confirmed non-clickable |
| 2 | If navigated, compare destination route with title-link behavior | Route matches article detail (`route=extension/maza/blog/article`) |
| 3 | Document actual behavior | Either thumbnail opens same article detail OR explicitly not a link by design |

---

## 5) Equivalence Partitioning Summary

| Input/Field | Valid Class | Invalid Class(es) | Covered By |
|---|---|---|---|
| Search keyword | Meaningful alphanumeric text | empty, special-only, no-result, script-like payload | TC-BLOG-014/015/016/027/029 |
| Link navigation | article/author/category links resolve correctly | invalid article ID route, inconsistent thumbnail-link behavior | TC-BLOG-004/006/007/026/030 |
| Slider controls | next/prev works continuously | control freeze/visual corruption | TC-BLOG-010/011/012/013 |

## 6) Boundary Value Summary (Inferred)

| Parameter | Boundary Set | Covered By | Note |
|---|---|---|---|
| Search length | 1 char, 256+ chars | TC-BLOG-017, TC-BLOG-018 | exact constraints not exposed by UI |
| Slider continuity | repeated sequential next/prev actions | TC-BLOG-010/011/012/013 | interaction boundary |

## 7) Traceability Matrix

| TC ID | Feature/Flow | UI Element(s) | Technique(s) | Priority | Requirement Intent |
|---|---|---|---|---|---|
| TC-BLOG-001 | Blog Access | Blog container, section headers | Smoke | P0 | User can access blog page |
| TC-BLOG-002 | Latest Feed Render | Latest cards | UI Integrity | P1 | Latest content visible and readable |
| TC-BLOG-003 | Most Viewed Render | Most viewed cards | UI Integrity | P1 | Most viewed content visible |
| TC-BLOG-004 | Article Navigation | Latest article title link | Navigation | P0 | Open article detail from latest |
| TC-BLOG-005 | Article Navigation | Most viewed article title link | Navigation | P1 | Open article detail from most viewed |
| TC-BLOG-006 | Author Navigation | Author links | Link validation | P1 | Navigate by author |
| TC-BLOG-007 | Category Navigation | Category links | Navigation, Widget | P1 | Navigate by category |
| TC-BLOG-008 | Category Metadata | Category count labels | Data display | P2 | Category count format consistency |
| TC-BLOG-009 | Sidebar Widget | Sidebar latest items | Widget, Navigation | P2 | Sidebar recommendations are usable |
| TC-BLOG-010 | Slider Next (Latest) | Next arrow | Interaction | P1 | Browse next latest cards |
| TC-BLOG-011 | Slider Prev (Latest) | Previous arrow | Interaction | P2 | Browse previous latest cards |
| TC-BLOG-012 | Slider Next (Most viewed) | Next arrow | Interaction | P2 | Browse next most-viewed cards |
| TC-BLOG-013 | Slider Prev (Most viewed) | Previous arrow | Interaction | P2 | Browse previous most-viewed cards |
| TC-BLOG-014 | Search Valid | Search field + submit | Equivalence(valid) | P1 | Search blog with valid keyword |
| TC-BLOG-015 | Search Empty | Search field + submit | Equivalence(invalid), Error handling | P2 | Graceful empty search behavior |
| TC-BLOG-016 | Search Special | Search field + submit | Security, Equivalence(edge) | P1 | Safe handling of special chars |
| TC-BLOG-017 | Search Min Boundary | Search field | Boundary(min) | P2 | 1-char query handled safely |
| TC-BLOG-018 | Search Max Boundary | Search field | Boundary(max), Error handling | P2 | Overlong query handled safely |
| TC-BLOG-019 | Metadata Consistency | Comments/views/date row | Data display | P3 | Metadata consistency |
| TC-BLOG-020 | Image Behavior | Thumbnails | Performance, Error handling | P2 | Image loading robustness |
| TC-BLOG-021 | Responsive UX | Mobile/tablet layout | Responsive | P1 | Mobile/tablet usability |
| TC-BLOG-022 | Keyboard A11y | Focusable controls | Accessibility | P1 | Keyboard-only usability |
| TC-BLOG-023 | Sensitive Error Disclosure | Result/error area | Security, Error handling | P1 | No sensitive internals shown |
| TC-BLOG-024 | Initial Render Performance | Header + first cards | Performance | P1 | Initial render meets baseline |
| TC-BLOG-025 | Feed Resilience | Feed section containers | Resilience | P1 | Graceful fallback under partial failure |
| TC-BLOG-026 | Invalid Article Route | Article detail route | Negative, Error handling | P1 | Graceful not-found handling |
| TC-BLOG-027 | Search Output Encoding | Search + result area | Security | P0 | Prevent XSS execution |
| TC-BLOG-028 | Browser History Flow | List/detail navigation | UX flow | P2 | Stable back/forward behavior |
| TC-BLOG-029 | Search No Result | Search result area | Equivalence(invalid), UX | P1 | Clear empty-state UX |
| TC-BLOG-030 | Thumbnail Link Parity | Latest card thumbnail image | Navigation, Link parity | P2 | Thumbnail behavior consistent with article entry points |

## 8) Execution Notes

- Execute P0/P1 first for release gate.
- Capture evidence per case: screenshot + URL + timestamp.
- If any security case fails (TC-BLOG-023/027), mark release risk as **High**.
- Add requirement IDs when product owner provides official AC mapping.
