package ru.practicum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
