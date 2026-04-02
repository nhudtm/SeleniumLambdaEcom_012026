# AddOns Module Test Cases (Final Version)

**Source image:** [modules_page_full.png](file:///Users/macos/.gemini/antigravity/brain/d8c7881f-78dd-4ac8-8566-46aea7297efa/modules_page_full_1774945962092.png)
**Saved to:** `testCases/from-ui/addons-module-testcases.md`

**Assumptions:**
- Product images and names link to product detail pages.
- "Shop Now" and banner buttons link to specific promotional pages.
- Brand logos link to brand-specific product lists.
- Blog metadata (comments/views) are dynamic but display static placeholders in UI.
- All sliders support manual arrow/dot interaction as well as touch-swiping.

---

## TC-MODULE-001: Navigation to AddOns > Module
**Visual ref:** Main navigation bar, "AddOns" menu item
**Priority:** P0 (Critical)
**Technique tags:** Equivalence (valid), Risk-based

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Hover over or Click "AddOns" in the main menu | Dropdown menu appears (verify touch compatibility) |
| 2 | Click on "Modules" | Page redirects to "Available Modules" |
| 3 | Verify "Available Modules" header is visible | Header is displayed correctly |

## TC-MODULE-002: Header Search with valid keywords
**Visual ref:** Top search input field and "SEARCH" button
**Priority:** P1 (High)
**Technique tags:** Equivalence (valid), Risk-based

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Enter "iMac" in search bar | Term is visible in input |
| 2 | Click "SEARCH" button | Redirect to search results page |
| 3 | Verify results | "iMac" matches the search term and is displayed as the first result |

## TC-MODULE-003: Product Listing Slider - Manual Navigation
**Visual ref:** "Product Listing" section with left/right arrows
**Priority:** P1 (High)
**Technique tags:** Table/list operations, Interaction

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click the right arrow (>) in the Product Listing section | Slider scrolls to show the next set of products |
| 2 | Click the left arrow (<) in the Product Listing section | Slider scrolls back to previous products |

## TC-MODULE-004a: Product Redirect - Image Click
**Visual ref:** Product image (e.g., "iMac")
**Priority:** P1 (High)
**Technique tags:** Navigation

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on the image of "iMac" | Redirect to iMac product detail page |

## TC-MODULE-004b: Product Redirect - Title Click
**Visual ref:** Product title (e.g., "iMac")
**Priority:** P1 (High)
**Technique tags:** Navigation

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on the title "iMac" | Redirect to iMac product detail page |

## TC-MODULE-005: Article Listing Slider - Read More
**Visual ref:** "Article Listing" section with titles and dates
**Priority:** P2 (Medium)
**Technique tags:** Navigation

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on an article title (e.g., "Amet volutpat...") | Redirect to the full blog post page |
| 2 | Verify article metadata (author, comments, views) is visible | Metadata displayed correctly |

## TC-MODULE-006: Brand Section Navigation
**Visual ref:** "Brand" section with logos and pagination dots
**Priority:** P2 (Medium)
**Technique tags:** Navigation, Filter

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on a brand logo (e.g., "Sony") | Redirect to Sony brand product list |
| 2 | Click on pagination dots under brand logos | Page scrolls/switches to next set of brands |

## TC-MODULE-007: Category Wall - Icon Redirection
**Visual ref:** "Category wall" with icons for Laptops, Desktops, etc.
**Priority:** P1 (High)
**Technique tags:** Navigation

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on the "Laptops" icon | Navigate to Laptops category page |
| 2 | Click on the "Tablets" icon | Navigate to Tablets category page |

## TC-MODULE-008: Slider Banner Pagination
**Visual ref:** Large promotional slider with pagination dots
**Priority:** P2 (Medium)
**Technique tags:** Interaction, Pagination

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on the second dot in the banner slider | Slider transitions to the second banner |
| 2 | Click on a "Shop now" button on a banner | Navigate to the linked promotion/product |

## TC-MODULE-009: Global Header Links (Compare/Wishlist/Cart)
**Visual ref:** Top right icons for Compare, Wishlist, and Cart
**Priority:** P0 (Critical)
**Technique tags:** Navigation, Risk-based

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click on the Wishlist icon (heart) | Navigate to Wishlist page |
| 2 | Click on the Cart icon (bag) | Opens cart dropdown or navigates to cart page |

## TC-MODULE-010: Keyboard Accessibility - Focus & Controls
**Visual ref:** All interactive elements
**Priority:** P2 (Medium)
**Technique tags:** Accessibility

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Press `Tab` through the page | Focus moves logically; highlights are visible |
| 2 | Open "AddOns" dropdown and press `Esc` | Dropdown menu closes |
| 3 | Press `Enter` on a focused product | Product detail page opens |

## TC-MODULE-011: Loading State - Image Lazy Loading
**Visual ref:** Product and article images
**Priority:** P2 (Medium)
**Technique tags:** Performance, Visual

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Scroll rapidly to the bottom of the page | Placeholders or skeleton loaders appear briefly |
| 2 | Verify images load and replace placeholders | Final images appear without layout shifts |

## TC-MODULE-012: Search Input Sanitization (Security)
**Visual ref:** Global search bar
**Priority:** P1 (High)
**Technique tags:** Security, Error Handling

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Enter `<script>alert(1)</script>` or `' OR 1=1` | Search result page handles input as a literal string |
| 2 | Verify no script execution or server error | No popups or leaked data; graceful "no results" state |

## TC-MODULE-013: Hover Interaction on Product Card
**Visual ref:** Product Listing card icons (Cart/Wishlist/Compare)
**Priority:** P1 (High)
**Technique tags:** Interaction, Risk-based

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Hover over a product card in "Product Listing" | Icons for "Add to Cart", "Wishlist", and "Compare" appear on hover |
| 2 | Click "Add to Cart" icon | Product added to cart successfully; feedback message shown |

## TC-MODULE-014: Currency Switcher Impact on Module
**Visual ref:** Header currency switcher, module prices
**Priority:** P1 (High)
**Technique tags:** Business Rule, Cross-feature

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Change currency to "Euro" via header switcher | Currency transitions in header |
| 2 | Scroll back to "Product Listing" module | All prices display the "€" symbol and are converted correctly |

## TC-MODULE-015: Mobile View - Slider Touch Interaction
**Visual ref:** Product/Brand slider on mobile
**Priority:** P2 (Medium)
**Technique tags:** Responsive, UX Flow

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Use touch-swipe gesture on "Product Listing" slider | Slider moves smoothly to the next set of items |

## TC-MODULE-016: Page Refresh Persistence
**Visual ref:** Slider position
**Priority:** P3 (Low)
**Technique tags:** UX Flow, Session

| Step | Action | Expected Result |
|------|--------|-----------------|
| 1 | Click right arrow (>) in Product Slider once | Slider transitions to next item |
| 2 | Press F5 (Refresh) | Slider resets gracefully or stays at position without layout shift |

---

### Traceability Matrix

| TC ID | Feature/Flow | UI Element(s) | Technique(s) | Priority | Requirement Intent |
|-------|--------------|---------------|--------------|----------|-------------------|
| TC-MODULE-001 | Menu Navigation | AddOns Menu, Modules item | Equivalence, Risk | P0 | User can access page via menu |
| TC-MODULE-002 | Search | Search Input, Button | Equivalence, Risk | P1 | Product search is functional |
| TC-MODULE-003 | Product Slider | Arrows, Items | Interaction, List ops | P1 | Products can be browsed |
| TC-MODULE-004a | Product Detail | Product Image | Navigation | P1 | Image link works |
| TC-MODULE-004b | Product Detail | Product Title | Navigation | P1 | Title link works |
| TC-MODULE-005 | Blog Navigation | Article Titles | Navigation | P2 | Users can access blog content |
| TC-MODULE-006 | Brand Navigation| Brand Logos, Dots | Navigation, Filter | P2 | Brand filtering is accessible |
| TC-MODULE-007 | Category Access | Category Icons | Navigation | P1 | Main category entry points work |
| TC-MODULE-008 | Banner Sliders | Pagination Dots | Interaction, Paging | P2 | Promotions are discoverable |
| TC-MODULE-009 | Checkout Entry | Cart/Wishlist Icons | Navigation, Risk | P0 | Cart access is stable |
| TC-MODULE-010 | Accessibility | Full Page Focus, ESC | Accessibility | P2 | Keyboard-operable & control |
| TC-MODULE-011 | User Experience | All image assets | Performance, Visual | P2 | Images load gracefully |
| TC-MODULE-012 | Security | Search Input | Security, Error | P1 | Input is sanitized/safe |
| TC-MODULE-013 | Quick Interaction| Hover Icons (Cart/...) | Interaction, Risk | P1 | Direct checkout from module |
| TC-MODULE-014 | Multi-Currency | Currency Switcher | Business Rule, Cross | P1 | Prices update in module |
| TC-MODULE-015 | Mobile UX | Sliders | Responsive, UX | P2 | Touch-friendly navigation |
| TC-MODULE-016 | Persistence | Refresh Action | UX, Session | P3 | Graceful recovery after refresh |

### Coverage Summary

- **By Category:**
  - Navigation/UX: 9
  - Interaction: 1
  - Form/Search/Security: 3
  - Accessibility: 1
  - Performance/Visual: 1
  - Business Rule: 2
- **By Priority:**
  - P0: 2 | P1: 9 | P2: 5 | P3: 1

Total Test Cases: 17
