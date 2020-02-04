# Automation testing for FitnessApp

## 1. Introduction

### About the project

This project is created for the assignment at "Methods and Techinques of Software Testing" course at FERIT Osijek. 
In this project, automation testing was implemented for the Android application FitnessApp which I have developed. 
There are 6 UI tests - for logging users, adding calories and training for a day, adding a new ingredient into the database and changing the username.


### What do you need to start the project?

1. FitnessApp .apk file which can be downloaded [here](https://drive.google.com/open?id=1MxghGcdtHydnwHo_xq2Mvte0MGYT7NON).
2. Android Studio - [download here](https://developer.android.com/studio)
3. JDK- [download here](https://www.guru99.com/install-java.html)
4. IntelliJ - [download here](https://www.jetbrains.com/idea/download/#section=windows)
5. TestNG Framework - [download here](https://mvnrepository.com/artifact/org.testng/testng)
6. Selenium Java - [download here](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
7. Node.js - [download here](https://nodejs.org/en/download/)
8. Appium - [download here](https://github.com/appium/appium-desktop/releases)

## 2. Enviroment setup

Here I will explain, step by step, how to setup everything you need, before we can start writing test.

### Download .apk file

Firstly, download .apk file from the first section and save it somewhere where you will find it easily.

### Download Android studio

1. Download Android Studio from the link at the first section
2. Follow [this tutorial](https://developer.android.com/studio/install) for successful installation. 
*Note: Remember where you installed Android SDK

##### Add AndroidSDK to system path

- Instructions for Windows and MacOS are [here](https://www.dev2qa.com/how-to-set-android-sdk-path-in-windows-and-mac/)
- Instructions for Linux are [here](https://www.dev2qa.com/how-to-install-and-run-android-studio-on-linux/)

### Download JDK

1. Download JDK
2. Follow [this instructions](https://docs.oracle.com/en/java/javase/13/install/installation-guide.pdf) for installing 

### Download IntelliJ

1. Download IntelliJ
2. Follow [instructions](https://www.jetbrains.com/help/idea/installation-guide.html) for installing.

### Download TestNG

1. Download TestNG
2. Open IntelliJ
3. Go to configure -> plugins
4. Search for testNG
5. Add checkmark

### Download Selenium Java

1. Download Selenium
2. Follow [this instructions](https://www.guru99.com/intellij-selenium-webdriver.html#4)

### Download Node.js

1. Download Node.js
2. Follow instructions for [Windows](https://www.webfx.com/blog/web-design/installing-node-windows/), [MacOS](https://nodesource.com/blog/installing-nodejs-tutorial-mac-os-x/) or [Linux](https://www.ostechnix.com/install-node-js-linux/)


### Finally, download Appium

1. Download Appium
- .exe file for Windows
- for MacOS you'll also need .dmg file

## 3. Let's create one test together

In this part we will together create an automation test to see if the user login works correctly. I am working on Windows 10, but the process should be similar for any operating system.

#### Step 1)
We should start a daemon background process before starting emulator. To do this, you should go to AndroidSDK folder and inside that folder, go to platform-tools. My path is ``` D:\AndroidStudioSDK\platform-tools ```. 

After you found the platform-tools folder open command prompt simply by writing ```cmd``` in the Windows search bar. 

The starting point in command prompt should be something like ```C:\Users\<yourname>```. If your AndroidSDK folder is on D: disk like mine, type ```D:``` in command prompt and after that ```cd AndroidStudioSDK\platform-tools```. Now you are in the platform-tools folder and we can start the daemon proccess.

Type ```adb devices``` and you should get something like 
```sh
List of devices attached
* daemon not running; starting now at tcp:5037
* daemon started successfully
```

#### Step 2)
In this step we should run an Android emulator.

Run Android Studio, go to configure -> AVD manager
Run emulator.
 - Note: If you don't have any emulator created, follow [this instructions](https://developer.android.com/studio/run/managing-avds) for creating one.

#### Step 3) 
Go back to command prompt and enter ``` adb devices ``` once again. Now your result should look like this
```sh
List of devices attached
emulator-5554 device
```

#### Step 4)
Now we should install the .apk file on the emulator
Enter ``` adb -s emulator-5554 install D:\<path_to_apk>\app-release.apk ```

The result should look like this
```sh
Performing Streamed Install
Success
```

#### Step 5)
We must start a server which Appium will use for sending instructions to the emulator.

To do this, enter ``` adb start-server ``` into command prompt

#### Step 6)
Run Appium Desktop Application.

At start, at ANDROID_HOME enter AndroidSDK path from step 1) but without platform-tools. It should look something like this ```D:\AndroidStudioSDK```. After that, at JAVA_HOME enter path to JDK. Mine is ``` C:\Program Files\Java\jdk-13.0.1\bin ```.
Press Save and Restart.

After adding ANDROID_HOME and JAVA_HOME there is a screen which has ``` Start server ``` button. Press it without changing Host and Port. And the server is now running.

The screen which you see now is the log screen. Now go to File -> New Session Window. 

#### Step 7)
Before starting a session we should create our custom server. You can do that by pressing the little pencil in JSON Representation. Now copy and paste this
```sh
{
 "app": "D:\<path_to_apk>\app-release.apk", 
 "VERSION": "9.0",
 "deviceName": "emulator",
 "platformName": "Android"
}
```
Of course, instead of <path_to_apk> enter path where you downloaded .apk file and instead of "9.0" for version, enter the Android version you have on your emulator.

After saving JSON Representation, press Start Session button.

Now you can inspect elements on FitnessApp.

*Note: For this project you don't need to inspect elements because there are already all element ids and xpaths you'll need in the code. But it is helpful when you are writing tests for other applications.

##### Finally writing the test
#### Step 8)
Open IntelliJ and create new Maven project. 
- If you don't have Maven installed, you can add it in the same way as you added testNG to IntelliJ 

When the project is created, enable Maven auto-import. It should be at the bottom right as a pop-up.

#### Step 9)
Go to POM.xml and insert Appium and TestNG dependencies.
```sh
<dependencies>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.0.0</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>7.3.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### Step 10)
Now go to src -> test and right-click on java. Go to new -> Java Class and name it ```LoginTest```.

Copy and paste this code into the class
```sh
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
    }

    @Test
    public void loginUser() throws Exception {
        //login user
        MobileElement email = (MobileElement)
                driver.findElement(By.id("editText_emailLogin")); 
                //we found id of this and other elements in Appium when inspecting elements
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
       driver.quit();
    }
```
*Note: You can find all constants in the testing branch -> src/test/java/Constants.class

#### Step 11)
Now right-click on project name in project view and add new file. Name it ```testng.xml```

Copy and paste this code into testng.xml
```sh
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="AllTestsSuite">
    <test name="LoginTest">
        <classes>
            <class name="LoginTest"/>
        </classes>
    </test>
</suite>
```

Right-click anywhere in testng.xml and then click ```Run '<project_path>\testng.xml'```. 

Switch to your emulator and watch how the automation test is done. 

When it's done, you can see in IntelliJ, at the bottom, in ```Run``` tab the result of your test. 
