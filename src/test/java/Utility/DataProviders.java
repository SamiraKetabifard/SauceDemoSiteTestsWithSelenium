package Utility;
import org.testng.annotations.DataProvider;

public abstract class DataProviders {

    private DataProviders() {
    }

    @DataProvider(name = "correctCredentials")
    public static Object[][] correctCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
        };
    }
    @DataProvider(name = "correctUsernames")
    public static Object[] correctUsernames() {
        return new Object[]{
                "standard_user", "performance_glitch_user", "visual_user"
        };
    }
    @DataProvider(name = "problematicCredentials")
    public static Object[][] problematicCredentials() {
        return new Object[][]{
                {"problem_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
        };
    }
}