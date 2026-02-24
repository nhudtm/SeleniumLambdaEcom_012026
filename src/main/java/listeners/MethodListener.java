package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import java.util.List;

public class MethodListener implements IInvokedMethodListener {
    // gọi trước khi Test Method được thực thi
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    // gọi sau khi Test Method được thực thi
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        Reporter.setCurrentTestResult(testResult);

        //Chỉ xử lý cho các method @Test
        if (method.isTestMethod()) {
            //1. Lấy tất ca lỗi SoftAssert đạ dc ghi lại trong luồng hiện tại
            List<Throwable> softFailures = VerificationFailures.getFailuresForTest();

            // 2. Nếu có Hard Assertion thất bại, thêm nó vào danh sách Soft Failures
            if (testResult.getThrowable() != null) {
                softFailures.add(testResult.getThrowable());
            }

            int size = softFailures.size();

            if (size > 0) {
                testResult.setStatus(ITestResult.FAILURE);

                if (size == 1) {
                    // Nếu chỉ có 1 lỗi, gán lỗi đó làm lỗi chính
                    testResult.setThrowable(softFailures.get(0));
                } else {
                    // Nếu có nhiều lỗi (Soft + Hard), hợp nhất chúng thành một thông báo duy nhất
                    StringBuffer message = new StringBuffer("Multiple failures(").append(size).append("):\n");
                    for (int failure = 0; failure < size -1; failure++) {

                        message.append("Failure ").append(failure + 1).append(" of ").append("(").append(size).append("):\n");
                        // Utils.longStackTrace giúp định dạng stack trace
                        message.append(Utils.longStackTrace(softFailures.get(failure), false)).append("\n");
                    }

                    //Xử lý lỗi cuối cùng
                    Throwable last = softFailures.get(size - 1);
                    message.append("Failure ").append(size).append(" of ").append("(").append(size).append("):\n");
                    message.append(last.toString());

                    // Tạo một Throwable mới để chứa thông báo hợp nhất
                    Throwable merged = new Throwable(message.toString());
                    merged.setStackTrace(last.getStackTrace());
                    testResult.setThrowable(merged);
                }
            }
            // 4. QUAN TRỌNG: Dọn dẹp ThreadLocal ngay sau khi báo cáo xong
            VerificationFailures.clearFailures();
        }
    }
}
