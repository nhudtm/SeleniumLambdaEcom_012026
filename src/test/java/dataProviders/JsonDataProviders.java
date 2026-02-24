package dataProviders;

import utils.CategoryQuantityJsonReader;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class JsonDataProviders {
    @DataProvider(name="categoryProductQuantityFromJson")
    public Object[][] categoryProductQuantityFromJson() {
        Map<String, Integer> categoryMap= CategoryQuantityJsonReader.getCategoryQuantity();

        if (categoryMap == null) {
            throw new RuntimeException("CategoryQuantityJsonReader.getCategoryQuantity() returned null. Check JSON format and path.");
        }
        System.out.println("Category Map:" + categoryMap);
        return new Object[][] { { categoryMap } };
    }
}