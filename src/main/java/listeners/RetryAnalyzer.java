package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 0; // Ví dụ: Cho phép chạy lại tối đa 2 lần

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            // Log thông tin về lần chạy lại
            System.out.println("Running again for failed test: " + result.getName() +
                    " | Lần thử: " + (retryCount + 1));
            retryCount++;
            return true; // Trả về TRUE để yêu cầu TestNG chạy lại Test Case
        }
        return false; // Trả về FALSE để ngừng chạy lại
    }
}
