package com.qa.reports.autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait (10, TimeUnit.SECONDS);
        driver.get("https://qa.reports.spd-ukraine.com");
        String windowTitle = driver.getTitle();
        Assert.assertTrue("Wrong window title", windowTitle.equalsIgnoreCase("Login page"));
    }

    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.name("username"));
        loginField.sendKeys("yuliia.tkchnk@gmail.com");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("twTaecq4");
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        loginButton.click();

        WebElement profileUser = driver.findElement(By.linkText("Profile"));
        String emailUser = profileUser.getText();
        Assert.assertEquals("yuliia.tkchnk@gmail.com", emailUser);
    }

    @AfterClass
    public static void tearDown() {
        WebElement logoutButton = driver.findElement(By.id("logOutAction"));
        logoutButton.click();

        String windowTitle = driver.getTitle();
        Assert.assertTrue("Wrong window title", windowTitle.equalsIgnoreCase("Login page"));

        driver.quit();
    }

}
