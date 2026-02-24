package pageUIs;

public class FilterUI {
    public static final String PRICE_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group price']/div[1]";
    public static final String SEARCH_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group search']";
    public static final String COLOR_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group custom']/div[contains(text(),'Color')]";
    public static final String SIZE_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group custom']/div[contains(text(),'Size')]";
    public static final String AVAILABILITY_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group stock_status']";
    public static final String RATING_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group rating']";
    public static final String DISCOUNT_FILTER_SECTION = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group discount']";

    public static final String MIN_PRICE = "xpath=//div[@id='mz-filter-0']//input[@name='mz_fp[min]']";
    public static final String MAX_PRICE = "xpath=//div[@id='mz-filter-0']//input[@name='mz_fp[max]']";
    public static final String CLEAR_PRICE_FILTER_BUTTON = "xpath=//div[@id='mz-filter-0']//div[@class='mz-filter-group price']//i[@class='fas fa-times']";
    public static final String DYNAMIC_COLOR_FILTER_OPTION = "xpath=//div[@id='mz-filter-panel-0-2']//img[@alt='%s']";
    public static final String DYNAMIC_SIZE_FILTER_OPTION_BY_ID = "xpath=//div[@id='mz-filter-panel-0-4']//input[contains(@id,'%s')]";
}