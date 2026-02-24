package components;

import java.util.ArrayList;
import java.util.List;

public class ProductGroup {
    List<ProductComponent> products = new ArrayList<>();

    public void addProduct(ProductComponent product) {
        products.add(product);
    }

    public List<ProductComponent> getProducts() {
        return products;
    }

    public void clickAddToCart() {
        for (ProductComponent product : products) {
            product.clickAddToCart();
        }
    }

    public void clickAddToWishList() {
        for (ProductComponent product : products) {
            product.clickAddToWishList();
        }
    }

    public void clickAddToCompare() {
        for (ProductComponent product : products) {
            product.clickAddToCompare();
        }
    }

    public void clickQuickView() {
        for (ProductComponent product : products) {
            product.clickQuickView();
        }
    }

    public void isAddToCartButtonDisplayed() {
        for (ProductComponent product : products) {
            product.isAddToCartButtonDisplayed();
        }
    }


}
