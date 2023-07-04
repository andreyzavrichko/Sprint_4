package ru.practicum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest extends TestBase {

    public final String name;
    public final String lastName;
    public final String address;
    public final String phone;
    public final String orderDate;
    public final String comment;
    public final By orderButton;

    public OrderTest(String name, String lastName, String address, String phone, String orderDate, String comment, By orderButton) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.orderDate = orderDate;
        this.comment = comment;
        this.orderButton = orderButton;
    }

    public String orderPopupTitle = "Хотите оформить заказ?" + "\n ";

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {"Сергей", "Лукьянов", "Московская 85", "77777788888", "01.05.2024", "Просьба позвонить за 1 час до прибытия", By.xpath("//button[text()='Заказать']")},
                {"Валерий", "Симонов", "Ивановская 94", "11711711888", "12.12.2023", "Просьба позвонить за 2 часа до прибытия", By.xpath("//button[text()='Заказать']")},
                {"Сергей", "Лукьянов", "Московская 85", "77777788888", "01.05.2024", "Просьба позвонить за 1 час до прибытия", By.xpath("(//button[text()='Заказать'])[2]")},
                {"Валерий", "Симонов", "Ивановская 94", "11711711888", "12.12.2023", "Просьба позвонить за 2 часа до прибытия", By.xpath("(//button[text()='Заказать'])[2]")}
        };
    }

    @Test
    public void orderOneTest() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        // принять куки
        mainPage.clickCookieConfirmButton();
        // кликаем на кнопку (первые два раза на верхнюю, далее на нижнюю)
        driver.findElement(orderButton).click();
        // перейти на первую страницу формы заполнения заказа
        orderPage.setName(name);
        orderPage.setLastName(lastName);
        orderPage.setAddressInput(address);
        orderPage.clickStationInput();
        orderPage.clickStationValue();
        orderPage.setPhoneInput(phone);
        orderPage.clickNextButton();
        // перейти на вторую страницу формы заполнения заказа
        orderPage.setOrderDate(orderDate);
        orderPage.clickRentDate();
        orderPage.clickRentItemDate();
        orderPage.clickColorCheckbox();
        orderPage.setCommentInput(comment);
        orderPage.clickOrderButton();
        orderPage.shouldVisibleOrderPopup();
        orderPage.shouldTextOrderPopupTitle(orderPopupTitle);
        orderPage.clickConfirmButton();
        // проверить закрытие popup
        orderPage.shouldClosePopup();
        // при клике на кнопку Да - не происходит отправка запроса, поэтому тест валится
    }



}
