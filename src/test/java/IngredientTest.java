import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IngredientTest {

    AndroidDriver driver;

    @BeforeClass()
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", Constants.apk);
        capabilities.setCapability("VERSION", Constants.version);
        capabilities.setCapability("deviceName", Constants.deviceName);
        capabilities.setCapability("platformName", Constants.platformName);
        driver = new AndroidDriver(new URL(Constants.url), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

    @Test
    public void ingredientTest(){
        MobileElement fab = (MobileElement)
                driver.findElement(By.id("fabButtonCalories"));
        fab.click();

        MobileElement search = (MobileElement)
                driver.findElement(By.id("button_searchIngredients"));
        search.click();

        MobileElement ingredient = (MobileElement)
                driver.findElement(By.id("editText_enterIngredient"));
        ingredient.click();
        //change with every test
        ingredient.setValue("badem");

        MobileElement searchIngredient = (MobileElement)
                driver.findElement(By.id("button_search"));
        searchIngredient.click();

        MobileElement yes = (MobileElement)
                driver.findElement(By.id("android:id/button1"));
        yes.click();

        MobileElement calories = (MobileElement)
                driver.findElement(By.id("editText_enterCalories"));
        calories.click();
        calories.setValue(Constants.calories);

        MobileElement saveIngredient = (MobileElement)
                driver.findElement(By.id("button_saveToBase"));
        saveIngredient.click();

        searchIngredient.click();

        MobileElement measurement = (MobileElement)
                driver.findElement(By.id("editText_measurement"));

        driver.quit();
    }

}
