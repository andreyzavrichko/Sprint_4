package ru.practicum.tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MoreTest extends TestBase {

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


}
