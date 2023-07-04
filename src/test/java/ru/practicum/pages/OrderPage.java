package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;
    //локатор имени
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    //локатор фамилии
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    //локатор адреса
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор станции
    private final By stationInput = By.xpath("//input[@placeholder='* Станция метро']");
    //локатор элемента станции
    private final By stationValue = By.xpath("//li[@data-value=1]");
    //локатор телефона
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки Далее
    private final By nextButton = By.xpath("//button[text()='Далее']");
    //локатор даты заказа
    private final By orderDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор даты аренды
    private final By rentDate = By.xpath("//div[text()='* Срок аренды']");
    //локатор элемента даты аренды
    private final By rentItemDate = By.xpath("//div[@id='root']/div[1]/div[2]/div[2]/div[2]/div[2]");
    //локатор цвета
    private final By colorCheckbox = By.xpath("//label[@for='black']");
    //локатор комментария
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки Заказать
    private final By orderButton = By.xpath("(//button[text()='Заказать'])[2]");
    //локатор popup
    private final By orderPopup = By.xpath("//div[@class='Order_Overlay__3KW-T']/following-sibling::div[1]");
    //локатор заголовка popup
    private final By orderPopupTitle = By.xpath("//div[contains(text(),\"Хотите оформить заказ?\")]");
    //локатор кнопки Да
    private final By confirmButton = By.xpath("//button[text()='Да']");


    //локатор ошибки имени
    private final By nameError = By.xpath("//div[text()='Введите корректное имя']");


    //локатор ошибки фамилии
    private final By lastNameError = By.xpath("//div[text()='Введите корректную фамилию']");


    //локатор ошибки станции
    private final By stationError = By.xpath("//div[text()='Выберите станцию']");

    //локатор ошибки телефона
    private final By phoneError = By.xpath("//div[text()='Введите корректный номер']");


    //конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполняет поля ввода имени
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    //метод заполняет поля ввода фамилии
    public void setLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    //метод заполняет поля ввода адреса
    public void setAddressInput(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    //метод кликает на поле Станции
    public void clickStationInput() {
        driver.findElement(stationInput).click();
    }

    //метод кликает на элемент Станции
    public void clickStationValue() {
        driver.findElement(stationValue).click();
    }

//метод заполняет поля ввода телефона

    public void setPhoneInput(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    //метод кликает на кнопку Далее

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //метод заполняет поля ввода даты заказа
    public void setOrderDate(String date) {
        driver.findElement(orderDate).sendKeys(date);
        driver.findElement(orderDate).sendKeys(Keys.ENTER);
    }


    //метод кликает на поле Срок аренды
    public void clickRentDate() {
        driver.findElement(rentDate).click();
    }

    //метод кликает на элемент Срока аренды
    public void clickRentItemDate() {
        driver.findElement(rentItemDate).click();
    }

    //метод выбирает чек-бокс Цвета
    public void clickColorCheckbox() {
        driver.findElement(colorCheckbox).click();
    }

    //метод заполняет поля ввода комментарий
    public void setCommentInput(String phone) {
        driver.findElement(commentInput).sendKeys(phone);
    }

    //метод кликает на кнопку Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //метод проверяет видимость popup заказа
    public void shouldVisibleOrderPopup() {
        driver.findElement(orderPopup).isDisplayed();
    }

    //метод проверяет текст заголовка popup
    public void shouldTextOrderPopupTitle(String value) {
        Assert.assertEquals("Текст не совпадает", value, driver.findElement(orderPopupTitle).getText());
    }

    //метод кликает на кнопку Да
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    //метод проверяет закрытие popup
    public void shouldClosePopup() {
        Assert.assertFalse(driver.findElement(orderPopupTitle).isDisplayed());
    }

    //метод проверяет текст ошибок первого экрана заказа
    public void shouldFirstScreenError() {
        driver.findElement(nameError).isDisplayed();
        driver.findElement(lastNameError).isDisplayed();
        driver.findElement(stationError).isDisplayed();
        driver.findElement(phoneError).isDisplayed();
    }

}
