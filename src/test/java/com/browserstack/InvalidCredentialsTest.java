package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class InvalidCredentialsTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {

        // navigate to ADUK
        driver.get("https://staging.admiralcasino.co.uk/en");

        // call basic authentication method
        basicAuthentication();

        // call accept cookies method
        acceptCookies();

        Thread.sleep(50);

        // login with user
        driver.findElement(By.xpath("//a[@class='link link-main-header']"))
                .click();
        driver.findElement(By.id("nickname"))
                .sendKeys("fakeuser1");
        driver.findElement(By.id("password"))
                .sendKeys("fakepassword2");
        driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button"))
                .click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;

        // verify login error message
        WebElement loginErrorMessage = driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/app-alert/div/ul/li"));

        if (loginErrorMessage.getText() == "Incorrect nickname/password combination.") {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Login error message displayed!\"}}");
        } else {
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Login error message not displayed\"}}");
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
