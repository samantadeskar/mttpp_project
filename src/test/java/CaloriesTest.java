import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CaloriesTest {

    AndroidDriver driver;

    @BeforeClass()
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "D:\\MTTPP_Projekt\\app-release.apk");
        capabilities.setCapability("VERSION", "10.0");
        capabilities.setCapability("deviceName", "emulator");
        capabilities.setCapability("platformName", "Android");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginUser();
    }

    public void loginUser() {

        MobileElement email = (MobileElement)
                driver.findElement(By.id("editText_emailLogin"));
        email.click();
        email.setValue(Constants.email);

        MobileElement password = (MobileElement)
                driver.findElement(By.id("editText_passwordLogin"));
        password.click();
        password.setValue(Constants.password);

        MobileElement login = (MobileElement)
                driver.findElement(By.id("button_login"));
        login.click();

    }

    //enter calories
    @Test
    public void enterCalories() {
        MobileElement fab = (MobileElement)
                driver.findElement(By.id("fabButtonCalories"));
        fab.click();

        MobileElement search = (MobileElement)
                driver.findElement(By.id("button_searchIngredients"));
        search.click();

        MobileElement ingredient = (MobileElement)
                driver.findElement(By.id("editText_enterIngredient"));
        ingredient.click();
        ingredient.setValue(Constants.ingredient);

        MobileElement searchIngredient = (MobileElement)
                driver.findElement(By.id("button_search"));
        searchIngredient.click();

        MobileElement measurement = (MobileElement)
                driver.findElement(By.id("editText_measurement"));
        measurement.click();
        measurement.setValue(Constants.measurement);

        MobileElement addIngredient = (MobileElement)
                driver.findElement(By.id("button_addIngredient"));
        addIngredient.click();

        MobileElement saveIngredients = (MobileElement)
                driver.findElement(By.id("button_saveEntry"));
        saveIngredients.click();

        MobileElement day = (MobileElement)
                driver.findElement(By.id("editText_dayCalories"));
        day.click();
        day.setValue(Constants.day);

        MobileElement month = (MobileElement)
                driver.findElement(By.id("editText_monthCalories"));
        month.click();
        month.setValue(Constants.month);

        MobileElement year = (MobileElement)
                driver.findElement(By.id("editText_yearCalories"));
        year.click();
        year.setValue(Constants.year);

        driver.hideKeyboard();

        MobileElement save = (MobileElement)
                driver.findElement(By.id("fabButton_saveCalories"));
        save.click();
    }


}
