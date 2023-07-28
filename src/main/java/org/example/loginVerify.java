package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class loginVerify {
    WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void logIn(){

        driver.get("https://www.amazon.com.tr/");

        //Find Login button and click
        WebElement loginButton= driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        loginButton.click();

        //Input email
        WebElement inputBar= driver.findElement(By.id("ap_email"));
        inputBar.sendKeys("ozbabuccu@hotmail.com");
        //continue
        WebElement continueButton= driver.findElement(By.id("continue"));
        continueButton.click();
        //password
        WebElement passwordTab= driver.findElement(By.id("ap_password"));
        passwordTab.sendKeys("8242t2");
        WebElement passwordButton= driver.findElement(By.id("signInSubmit"));
        passwordButton.click();

        //Verify

    }
}
