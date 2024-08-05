import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.MainPage;

import java.util.concurrent.TimeUnit;

public abstract class ParentTest {

    public WebDriver driver;
    public MainPage mainPage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //Точка входа для всех тестов одна. Вынесем ее в before.
        mainPage = new MainPage(driver);
        mainPage.pageOpen();
        mainPage.AcceptCookies();
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
