import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.StartsActivity;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginTest {

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

    }

    @Test
    public void loginUser() throws Exception {

        //login user
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

        //check if user is logged in by changed action bar
        MobileElement actionBar = (MobileElement)
                driver.findElement(By.id("action_bar"));

        logoutUser();
    }


    //logout user for next test
    public void logoutUser(){

        MobileElement profile = (MobileElement)
                driver.findElement(By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"My Profile\"]/android.widget.TextView"));
        profile.click();

        MobileElement logout = (MobileElement)
                driver.findElement(By.id("textView_logout"));
        logout.click();
    }

    //try to login user with non registered email and password
    @Test
    public void wrongEmailLogin(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        //login user
        MobileElement email = (MobileElement)
                driver.findElement(By.id("editText_emailLogin"));
        email.click();
        email.setValue(Constants.wrongEmail);

        MobileElement password = (MobileElement)
                driver.findElement(By.id("editText_passwordLogin"));
        password.click();
        password.setValue(Constants.wrongPassword);

        MobileElement login = (MobileElement)
                driver.findElement(By.id("button_login"));
        login.click();

        //check if still on login page
        MobileElement loginLabel = (MobileElement)
                driver.findElement(By.id("login_label"));

    }
}
