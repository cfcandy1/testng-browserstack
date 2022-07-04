package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {

        // navigate to ADUK
        driver.get("https://staging.admiralcasino.co.uk/en");

        // call basic authentication method
        basicAuthentication();

        // call accept cookies method
        acceptCookies();

        Thread.sleep(1000);

        // login with user
        driver.findElement(By.xpath("//a[@class='link link-main-header']"))
                .click();
        driver.findElement(By.id("nickname"))
                .sendKeys("stagtestrmg1");
        driver.findElement(By.id("password"))
                .sendKeys("qwertz12strmg");
        driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button"))
                .click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;

        // verify login success
        if (driver.findElement(By.xpath("//div[@class='item item-userpic ng-star-inserted']"))
                .isDisplayed()) {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Login successful!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Login unsuccessful\"}}");
        }
    }
    public void basicAuthentication(){
        driver.findElement(By.id("user"))
                .sendKeys("admin");
        driver.findElement(By.id("password"))
                .sendKeys("matrix4215");
        driver.findElement(By.xpath("/html/body/form/input[4]"))
                .click();
    }
    public void acceptCookies(){
        driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']"))
                .click();
    }
}
