package Abc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Run {

    @Test
    public void a()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\sanjeev.kumar\\Desktop\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.com/");
      
    }
}
