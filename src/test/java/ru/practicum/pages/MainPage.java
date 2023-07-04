package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    //локатор кнопки Заказать сверху
    private final By orderButtonUp = By.xpath("//button[text()='Заказать']");

    //локатор кнопки согласия сбора куки
    private final By cookieConfirmButton = By.id("rcc-confirm-button");

    //локатор логотипа
    private final By mainLogo = By.xpath("//img[@alt='Scooter']");

    //локатор заголовка Home
    private final By homeTitle = By.xpath("  //div[contains(@class,'Home_ArrowDown__fnDme Home_Animated__17o7s')]/following-sibling::div[1]");

    //локатор логотипа Yandex
    private final By yandexLogo = By.xpath("//img[@alt='Yandex']");

    //локатор кнопки Статус заказа
    private final By statusOrderButton = By.xpath("//button[text()='Заказать']/following-sibling::button");

    //локатор кнопки Статус заказа
    private final By orderInput = By.xpath("//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");

    //локатор кнопки Go
    private final By goButton = By.xpath("//button[text()='Go!']");


    //локатор заглушки поиска
    private final By emptySearchPage = By.xpath("//*[@class='Input_ErrorMessage__3HvIb']");


    //конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод кликает кнопку согласия куки
    public void clickCookieConfirmButton() {
        driver.findElement(cookieConfirmButton).click();
    }


    //метод кликает кнопку Заказать
    public void clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }


    //метод кликает на логотип
    public void clickMainLogo() {
        driver.findElement(mainLogo).click();
    }

    //метод кликает на логотип Yandex
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    //метод проверяет видимость главного заголовка
    public void shouldVisibleHomeTitle() {
        driver.findElement(homeTitle).isDisplayed();
    }


    //метод проверяет текст главного заголовка
    public void shouldTextHomeTitle(String value) {
        Assert.assertEquals("Текст не совпадает", value, driver.findElement(homeTitle).getText());
    }

    //метод кликает на кнопку Статус заказа
    public void clickStatusOrderButton() {
        driver.findElement(statusOrderButton).click();
    }

    //метод заполняет поля ввода имени
    public void setOrderInput(String order) {
        driver.findElement(orderInput).click();
        driver.findElement(orderInput).sendKeys(order);
    }

    //метод кликает на кнопку Go
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    //метод проверяет видимость экрана заглушки'
    public void shouldVisibleEmptySearchPage() {
        driver.findElement(emptySearchPage).isDisplayed();
    }


    //метод проверяет появление поля заказа
    public void waitVisibleOrderInput() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderInput));
    }

    //метод открытия аккордеона
    public void openAccordionHeading(By accordionTitle) {
        driver.findElement(accordionTitle).click();
    }

}
