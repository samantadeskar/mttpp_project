import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TrainingTest {

    AndroidDriver driver;

    @BeforeClass()
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", Constants.apk);
        capabilities.setCapability("VERSION", Constants.version);
        capabilities.setCapability("deviceName", Constants.deviceName);
        capabilities.setCapability("platformName", Constants.platformName);

        driver = new AndroidDriver(new URL(Constants.url), capabilities);
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

    @Test
    public void trainingTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement trainingTab = (MobileElement)
                driver.findElement(By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Training\"]/android.widget.TextView"));
        trainingTab.click();

        MobileElement fab = (MobileElement)
                driver.findElement(By.id("fabButtonTraining"));
        fab.click();

        MobileElement day = (MobileElement)
                driver.findElement(By.id("editText_day"));
        day.click();
        day.setValue(Constants.day);

        MobileElement month = (MobileElement)
                driver.findElement(By.id("editText_month"));
        month.click();
        month.setValue(Constants.month);

        MobileElement year = (MobileElement)
                driver.findElement(By.id("editText_year"));
        year.click();
        year.setValue(Constants.year);

        MobileElement training = (MobileElement)
                driver.findElement(By.id("editText_trainingDetails"));
        training.click();
        training.setValue(Constants.training);

        driver.hideKeyboard();

        MobileElement save = (MobileElement)
                driver.findElement(By.id("fabButtonAddTraining"));
        save.click();

    }
}
