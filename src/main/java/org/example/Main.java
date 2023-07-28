package org.example;

import org.asynchttpclient.util.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;
import static java.lang.Integer.parseInt;

public class Main {
        WebDriver driver;

        @Before
        public void setUp(){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

        @Test
        public void amazon() {

            driver.get("https://www.amazon.com.tr/");
            WebElement acceptCookies= driver.findElement(By.id("sp-cc-accept"));
            acceptCookies.click();

            WebElement homeLink_Ex1= driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
            homeLink_Ex1.sendKeys("laptop");


            WebElement searchButton=driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
            searchButton.click();

            WebElement dropdownButton= driver.findElement(By.cssSelector("[class='a-button a-button-dropdown a-button-small']"));
            dropdownButton.click();
            WebElement minSort= driver.findElement(By.id("s-result-sort-select_1"));
            minSort.click();
            List<WebElement> productsLaptopName= driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
            List<WebElement> productsPrice= driver.findElements(By.xpath("//span[@class='a-offscreen']"));
            System.out.println("productsPrice.get(0).getText() = " + productsPrice.get(0).getText());
            List<Product> products= new ArrayList<>();
            for(int i = 0 ; i< productsLaptopName.size();i++ ){
                products.add(new Product(productsLaptopName.get(i).getText(), productsPrice.get(i).getText().replace("TL","")
                ));
            }

            if(!products.isEmpty()){
                Product minPriceProduct= products.get(0);
                System.out.println("Product with the min price: = \"" + minPriceProduct.getName()
                        + "\", Price: " + minPriceProduct.getPrice() );

            // Add product to box
                WebElement element = driver.findElement(By.xpath("//img[@class='s-image']"));

                // Scroll to the element using JavascriptExecutor
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.findElement(By.id("add-to-cart-button")).click();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Go to Box
                WebElement goBox= driver.findElement(By.xpath("//a[@href='/cart?ref_=sw_gtc']"));
                wait.until(ExpectedConditions.elementToBeClickable(goBox));
                goBox.click();
            // Product in the box
                WebElement productInBoxText= driver.findElement(By.xpath("//span[@class='a-truncate-cut']"));
                WebElement productInBoxPrice= driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold']"));

            // Verify Products

                Assert.assertEquals(minPriceProduct.getName(), (productInBoxText.getText()));
                Assert.assertEquals(minPriceProduct.getPrice().trim(),productInBoxPrice.getText().replace("TL","").trim());


            }





        }








}