package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = isCiCdRun() ? 2 : 1;

    private static boolean isCiCdRun() {
        String ciSystemProperty = System.getProperty("ci");
        if (ciSystemProperty != null && Boolean.parseBoolean(ciSystemProperty)) {
            return true;
        }

        String ciEnv = System.getenv("CI");
        if (ciEnv != null && Boolean.parseBoolean(ciEnv)) {
            return true;
        }

        return isTruthy(System.getenv("GITHUB_ACTIONS"))
                || isTruthy(System.getenv("GITLAB_CI"))
                || isTruthy(System.getenv("TF_BUILD"))
                || System.getenv("JENKINS_URL") != null
                || System.getenv("BUILD_BUILDID") != null;
    }

    private static boolean isTruthy(String value) {
        return value != null
                && !value.isBlank()
                && ("true".equalsIgnoreCase(value)
                || "1".equals(value)
                || "yes".equalsIgnoreCase(value));
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            System.out.println("Running again for failed test: " + result.getName() +
                    " | Retry attempt: " + (retryCount + 1) +
                    " | Max retry: " + MAX_RETRY_COUNT +
                    " | Mode: " + (MAX_RETRY_COUNT == 2 ? "CI/CD" : "Local"));
            retryCount++;
            return true;
        }
        return false;
    }
}
