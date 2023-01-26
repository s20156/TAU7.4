package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.Assert.*;


import java.util.concurrent.TimeUnit;

public class IntLoginTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        EdgeOptions options = new EdgeOptions();
//        options.setHeadless(true);
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://int.pl/#/login-clear");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void loginToExistingAccount() {
        WebElement loginInput = driver.findElement(By.id("emailId"));
        WebElement passwordInput = driver.findElement(By.id("passwordId"));
        loginInput.sendKeys("tau74");
        passwordInput.sendKeys("hasloTau74");
        passwordInput.submit();
        WebElement greeting = driver.findElement(By.cssSelector("h1.msglist-header__folder-name"));
        assertNotNull(greeting);
    }

    @Test
    public void loginWrongInput() {
        WebElement loginInput = driver.findElement(By.id("emailId"));
        WebElement passwordInput = driver.findElement(By.id("passwordId"));
        loginInput.sendKeys("123");
        passwordInput.sendKeys("123");
        passwordInput.submit();
        assertEquals("Darmowa poczta email – Logowanie do poczty INT.PL – Załóż darmowy webmail z krótkim adresem mail-owym", driver.getTitle());
    }
}
