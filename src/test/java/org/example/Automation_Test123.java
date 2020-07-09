package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Automation_Test123 {
        static WebDriver driver;
// reuse methods

        public static void clickOnElement(By by, int time) {
            driver.findElement(by).click();
        }

        public static void TypeText(By by, String text) {
            driver.findElement(by).sendKeys(text);
        }

        public static String getTextFromElement(By by) {
            return driver.findElement(by).getText();
        }

        public static long timestamp() {
            return (System.currentTimeMillis());
        }

        public static void selectFromDropDownByVisibleText(By by, String text) {
            Select select = new Select(driver.findElement(by));
            select.selectByVisibleText(text);
        }

        public static void selectFromDropDownByValue(By by, String value) {
            Select select = new Select(driver.findElement(by));
            select.selectByVisibleText(value);

        }

        public static void selectFromDropDownByVisibleIndex(By by, int n) {
            Select select = new Select(driver.findElement(by));
            select.selectByIndex(n);
        }

        @BeforeMethod
        public static void setupBrowser() {
            System.setProperty("webdriver.chrome.driver", "C:\\SOFT\\chromedriver.exe");// path to chrome driver
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);// allows to avoid chrome pop ups while running programme
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});//Chrome is being controlled by automated test software notification that
            driver = new ChromeDriver(options);//open chrome driver
            driver.manage().window().maximize();// Maximize the screen
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //implicitlyWait
            driver.get("https://demo.nopcommerce.com/");// opens demo nop commerce URL
        }

        @AfterMethod
        public static void closeBrowser() {
           driver.quit();

        }

         @Test(priority = 1)
        public static void UserShouldBeAbleToRegistration() {
            // All registration form with reusable methods and with links
            clickOnElement(By.linkText("Register"), 10);
            clickOnElement(By.xpath("//input[@id=\"gender-female\"]"), 10);
            TypeText(By.id("FirstName"), "NilPari");
            TypeText(By.cssSelector("input#LastName"), "Dabhi");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicitlyWait for open the next button

            selectFromDropDownByVisibleText(By.xpath("//select[@name='DateOfBirthDay']"), "14");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            selectFromDropDownByValue(By.xpath("//select[@name='DateOfBirthMonth']"), "February");
            selectFromDropDownByValue(By.xpath("//select[@name='DateOfBirthYear']"), "2006");
            TypeText(By.name("Email"), "Nilpari+" + timestamp() + "@gmail.com");
            TypeText(By.id("Company"), "Mahineti27");
            TypeText(By.id("Password"), "123456");
            TypeText(By.id("ConfirmPassword"), "123456");
            clickOnElement(By.id("register-button"), 20);
            String expectedText = "Your registration completed";

            //  String actualText = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        }

        @Test (priority = 2)
        public static void Computer() {
            UserShouldBeAbleToRegistration(); // user should be able to last steps for Registration form

            clickOnElement(By.linkText("Computers"), 20);

            clickOnElement(By.linkText("Desktops"), 20);

            clickOnElement(By.linkText("Digital Storm VANQUISH 3 Custom Performance PC"), 20);

            clickOnElement(By.xpath("//input[@value=\"Email a friend\"]"), 20);

            TypeText(By.xpath("//input[@id=\"FriendEmail\"]"), "Pareshdabhi2905@gmail.com");

            //TypeText(By.xpath("//input[@id=\"YourEmailAddress\"]"), "Nilpari+" + timestamp() + "@gmail.com");



            TypeText(By.xpath("//textarea[@id=\"PersonalMessage\"]"), "DAS NA DAS BANAVSOJI");
            TypeText(By.xpath("//textarea[@id=\"PersonalMessage\"]"), "You are being invited ");
            clickOnElement(By.xpath("//input[@name=\"send-email\"]"), 20);
            String expectedText1 = "Your message has been sent.";
            String actualText1 = getTextFromElement(By.xpath("//div[@class=\"result\"]"));

            Assert.assertEquals(actualText1, expectedText1);
        }

        @Test
        public static void AddToCart(){
            clickOnElement(By.linkText("Electronics"),20); // click on Electronics
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            clickOnElement(By.xpath("//a[@title=\"Show products in category Cell phones\"]"), 20); // you can see category name cell phone
            clickOnElement(By.linkText("HTC One Mini Blue"), 20 ); // product name display
            clickOnElement(By.xpath("//input[@id=\"add-to-cart-button-19\"]"),20); // product in shopping cart

            clickOnElement(By.linkText("Books"), 20); // Books category
            clickOnElement(By.linkText("Fahrenheit 451 by Ray Bradbury"), 20); // select book
            clickOnElement(By.xpath("//input[@id=\"add-to-cart-button-37\"]"), 20); // book in shopping cart
            clickOnElement(By.linkText("Shopping cart"), 20);// Go to shopping cart
            driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
            String expectedText="Fahrenheit 451 by Ray Bradbury";
            String actualText = getTextFromElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));
            Assert.assertEquals(actualText,expectedText);
            String expectedText1= "HTC One Mini Blue"; //  product text in shopping cart
            String actualText1 = getTextFromElement(By.linkText("HTC One Mini Blue" ));
            Assert.assertEquals(actualText1,expectedText1);
        }


    }








































