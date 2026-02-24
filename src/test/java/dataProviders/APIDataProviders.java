package dataProviders;

import org.testng.annotations.DataProvider;
import utils.CategoryAPIHelper;

import java.util.Map;

public class APIDataProviders {
    @DataProvider(name = "categoryQuantityMapFromAPI")
    public Object[][] categoryQuantityMapFromAPI() {
        Map<String, Integer> categoryMap = CategoryAPIHelper.getCategoryQuantityMap();
        return new Object[][]{{categoryMap}};
    }
}
