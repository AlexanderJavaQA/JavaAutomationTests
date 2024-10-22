package listener;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class RetryListener implements TestExecutionExceptionHandler {

    private static final int MAX_RETRIES = 3;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
                return;
            } catch (Throwable e) {
                System.err.println("Попытка перезапуска упавшего теста " + (i + 1) + " неудачно: " + e.getMessage());
                e.printStackTrace();
            }
        }
        throw throwable;
    }
}
