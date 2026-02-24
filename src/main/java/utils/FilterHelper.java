package utils;

import components.FilterComponent;
import components.ProductComponent;
import org.testng.Assert;

import java.util.List;

public class FilterHelper {
    public static void verifyFilterByPrice(String minPrice, String maxPrice, List<ProductComponent> productList) {
        float minPriceFloat = Float.parseFloat(minPrice);
        float maxPriceFloat = Float.parseFloat(maxPrice);
        for (ProductComponent product : productList) {
            float productPrice = product.getProductPrice();
            System.out.println("Product Price: " + productPrice);
            Assert.assertTrue(productPrice >= minPriceFloat && productPrice <= maxPriceFloat);
        }
    }

    public static void verifyFilterBySize(String size, FilterComponent filter) {
        Assert.assertTrue(filter.isSizeSelected(size));
        //Có thể mở rộng thêm
    }


}
