package ru.practicum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ScooterTest {
    private WebDriver driver;
    // Заказ 1
    public String nameOne = "Сергей";
    public String lastNameOne = "Лукьянов";
    public String addressOne = "Московская 85";
    public String phoneOne = "77777788888";
    public String orderDateOne = "01.05.2024";
    public String commentOne = "Просьба позвонить за 1 час до прибытия";
    // Заказ 2
    public String nameTwo = "Валерий";
    public String lastNameTwo = "Симонов";
    public String addressTwo = "Ивановская 94";
    public String phoneTwo = "11711711888";
    public String orderDateTwo = "12.12.2023";
    public String commentTwo = "Просьба позвонить за 2 часа до прибытия";
    public String orderPopupTitle = "Хотите оформить заказ?" + " \n";


    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }


    @Test
    public void questionAccordionTest() {
        MainPage mainPage = new MainPage(driver);
        // Принять куки
        mainPage.clickCookieConfirmButton();
        mainPage.waitVisibleAccordionPanel();
        // Открыть аккордеон
        mainPage.openAccordionHeading();
        // Проверить открытие аккордеона
        mainPage.shouldVisibleAccordionPanel();
        mainPage.shouldTextAccordionPanel("Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
    }


    @Test
    public void orderOneTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        // принять куки
        mainPage.clickCookieConfirmButton();
        mainPage.clickOrderButtonUp();
        // перейти на первую страницу формы заполнения заказа
        orderPage.setName(nameOne);
        orderPage.setLastName(lastNameOne);
        orderPage.setAddressInput(addressOne);
        orderPage.clickStationInput();
        orderPage.clickStationValue();
        orderPage.setPhoneInput(phoneOne);
        orderPage.clickNextButton();
        // перейти на вторую страницу формы заполнения заказа
        orderPage.setOrderDate(orderDateOne);
        orderPage.clickRentDate();
        orderPage.clickRentItemDate();
        orderPage.clickColorCheckbox();
        orderPage.setCommentInput(commentOne);
        orderPage.clickOrderButton();
        orderPage.shouldVisibleOrderPopup();
        orderPage.shouldTextOrderPopupTitle(orderPopupTitle);
        orderPage.clickConfirmButton();
        // проверить закрытие popup
        orderPage.shouldClosePopup();
        // при клике на кнопку Да - не происходит отправка запроса, поэтому тест валится
    }

    @Test
    public void orderTwoTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        // принять куки
        mainPage.clickCookieConfirmButton();
        mainPage.clickOrderButtonUp();
        // перейти на первую страницу формы заполнения заказа
        orderPage.setName(nameTwo);
        orderPage.setLastName(lastNameTwo);
        orderPage.setAddressInput(addressTwo);
        orderPage.clickStationInput();
        orderPage.clickStationValue();
        orderPage.setPhoneInput(phoneTwo);
        orderPage.clickNextButton();
        // перейти на вторую страницу формы заполнения заказа
        orderPage.setOrderDate(orderDateTwo);
        orderPage.clickRentDate();
        orderPage.clickRentItemDate();
        orderPage.clickColorCheckbox();
        orderPage.setCommentInput(commentTwo);
        orderPage.clickOrderButton();
        orderPage.shouldVisibleOrderPopup();
        orderPage.shouldTextOrderPopupTitle(orderPopupTitle);
        orderPage.clickConfirmButton();
        // проверить закрытие popup
        orderPage.shouldClosePopup();
        // при клике на кнопку Да - не происходит отправка запроса, поэтому тест валится
    }


    @Test
    public void mainLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieConfirmButton();
        // Перейти на страницу заказа
        mainPage.clickOrderButtonUp();
        // Нажать на лого
        mainPage.clickMainLogo();
        // Проверить переход
        mainPage.shouldVisibleHomeTitle();
        mainPage.shouldTextHomeTitle("Самокат\nна пару дней\nПривезём его прямо к вашей двери,\nа когда накатаетесь — заберём");
    }


    @Test
    public void yandexLogoTest() {
        MainPage mainPage = new MainPage(driver);
        String originalWindow = driver.getWindowHandle();
        driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        mainPage.clickCookieConfirmButton();
        mainPage.clickOrderButtonUp();
        mainPage.clickYandexLogo();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        new WebDriverWait(driver, 3)
                .until(numberOfWindowsToBe(2));

        // Проверить заголовок новой страницы
        new WebDriverWait(driver, 3)
                .until(titleIs("Дзен"));


    }


    @Test
    public void orderStatusTest() {
        MainPage mainPage = new MainPage(driver);
        // принять куки
        mainPage.clickCookieConfirmButton();
        // ввести несуществующий номер заказа
        mainPage.clickStatusOrderButton();
        mainPage.waitVisibleOrderInput();
        mainPage.setOrderInput("5555");
        mainPage.clickGoButton();
        // проверить экран заглушку
        mainPage.shouldVisibleEmptySearchPage();
    }


    @Test
    public void orderErrorTest() {
        // создать объект класса страницы авторизации
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        // принять куки
        mainPage.clickCookieConfirmButton();
        mainPage.clickOrderButtonUp();
        // перейти на первую страницу формы заполнения заказа
        orderPage.clickNextButton();
        orderPage.shouldFirstScreenError();

    }


    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
