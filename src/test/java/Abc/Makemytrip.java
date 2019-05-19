package Abc;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Makemytrip {


    WebDriver driver = null;
    Robot robot = null;

    @Test(priority = 0)
    public void open() throws InterruptedException, AWTException {

        //WebDriverManager.chromedriver().setup();

        System.setProperty("webdriver.chrome.driver","C:\\Users\\sanjeev.kumar\\Desktop\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
        driver.manage().deleteAllCookies();
        /*
         * driver.findElement(By.
         * xpath("//a[contains(@class,'primaryBtn font24 latoBlack widgetSearchBtn')]"))
         * .click(); driver.manage().deleteAllCookies();﻿
         */

        Thread.sleep(15000);

        driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
        List<WebElement> allImages = driver.findElements(By.xpath("//*[@id=\"second-img\"]"));

        if (allImages.size() > 0) {
            driver.findElement(By.xpath("//*[@class='close']/i[@class='wewidgeticon we_close']")).click();
            // driver.findElement(By.xpath("//*[@id=\"webklipper-publisher-widget-container-notification-close-div\"]/i")).click();
            System.out.println("Pop-up closed");
        } else {
            System.out.println("Images not detected");
        }
    }

    @Test(priority = 1)
    public void searchFlight() throws AWTException, InterruptedException {

        robot = new Robot();
        driver.findElement(By.xpath("//*[text()='Flights' and @class='chNavText darkGreyText']")).click();
        driver.findElement(By.xpath("//*[text()='Round Trip']")).click();

        //List<String> = driver.findElements(By.tagName("iframe")

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"fromCity\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"fromCity\"]")).sendKeys("DELHI");
        Thread.sleep(1000);
        robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(By.xpath("//*[@id=\"toCity\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"toCity\"]")).sendKeys("BANGLORE");

        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[3]/label/span")).click();
        String currentDate = driver.findElement(By.xpath("//*[@class=\"DayPicker-Day DayPicker-Day--today\"]"))
                .getText();
        driver.findElement(By.xpath("//*[@class=\"DayPicker-Day DayPicker-Day--today\"]")).click();
        // add conditions if from is in next month
        int fromDate = Integer.parseInt(currentDate) + 7;
        System.out.println(currentDate);
        List<WebElement> allDates = driver.findElements(By.xpath(
                "//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/div[3]/div/div/div/p"));
        for (int i = 0; i < allDates.size(); i++) {
            if (allDates.get(i).getText().equals(String.valueOf(fromDate))) {
                allDates.get(i).click();
                System.out.println("Clicked on fromdate");
                break;

            }
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Search']")).click();
    }
}
