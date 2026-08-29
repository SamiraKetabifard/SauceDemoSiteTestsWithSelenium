package Base;

import com.example.Utility.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverSetUp.getDriver("chrome");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverSetUp.quit();
    }
}