import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UsernameTest {

    AndroidDriver driver;

    @BeforeClass()
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", Constants.apk);
        capabilities.setCapability("VERSION", Constants.version);
        capabilities.setCapability("deviceName", Constants.deviceName);
        capabilities.setCapability("platformName", Constants.platformName);
        driver = new AndroidDriver(new URL(Constants.url), capabilities);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
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
    public void UsernameTest(){

        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement profileTab = (MobileElement)
                driver.findElementByAccessibilityId("My Profile");
        profileTab.click();

        MobileElement edit = (MobileElement)
                driver.findElement(By.id("imageButton_editUsername"));
        edit.click();

        MobileElement newUsername = (MobileElement)
                driver.findElement(By.id("editText_usernameProfile"));
        newUsername.click();
        newUsername.clear();
        newUsername.setValue(Constants.newUsername);

        MobileElement save = (MobileElement)
                driver.findElement(By.id("imageButton_saveUsername"));
        save.click();
        driver.quit();

    }

}
