package listeners;

import java.util.ArrayList;
import java.util.List;

public class VerificationFailures {
    // Dùng Threadlocal để đảm bảo mỗi luồng có list throwable riêng
    private static final ThreadLocal<List<Throwable>> failuresThreadLocal = new ThreadLocal<List<Throwable>>() {
        @Override
        protected List<Throwable> initialValue() {
            return new ArrayList<>();
        }
    };

    private VerificationFailures() {
        //Constructor private đề ng8an tạo instance bên ngoài
    }

    //Lấy danh sách lỗi (Soft assert) vào danh sách lỗi của luồng hiện tại
    public static List<Throwable> getFailuresForTest() {
        return failuresThreadLocal.get();
    }

    //Ghi lại lỗi (AssertionError) vào danh sách lỗi
    public static void addFailuresForTest(Throwable throwable) {
        // Lấy danh sách lỗi hiện tại của luồng
        List<Throwable> exceptions = getFailuresForTest();
        // Thêm lỗi mới vào danh sách
        exceptions.add(throwable);
    }

    //Dọn dẹp
    public static void clearFailures() {
        failuresThreadLocal.remove();
    }
}
