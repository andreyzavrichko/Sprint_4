package ru.practicum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import ru.practicum.pages.MainPage;

@RunWith(Parameterized.class)
public class AccordionTest extends TestBase {

    public final By accordionTitle;
    public final By accordionPanel;
    public final String accordionText;

    public AccordionTest(By accordionTitle, By accordionPanel, String accordionText) {
        this.accordionTitle = accordionTitle;
        this.accordionPanel = accordionPanel;
        this.accordionText = accordionText;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordion() {
        return new Object[][]{
                {By.id("accordion__heading-0"), By.id("accordion__panel-0"), "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {By.id("accordion__heading-1"), By.id("accordion__panel-1"), "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {By.id("accordion__heading-2"), By.id("accordion__panel-2"), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {By.id("accordion__heading-3"), By.id("accordion__panel-3"), "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {By.id("accordion__heading-4"), By.id("accordion__panel-4"), "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {By.id("accordion__heading-5"), By.id("accordion__panel-5"), "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {By.id("accordion__heading-6"), By.id("accordion__panel-6"), "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {By.id("accordion__heading-7"), By.id("accordion__panel-7"), "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void questionAccordionTest() {
        MainPage mainPage = new MainPage(driver);
        // Принять куки
        mainPage.clickCookieConfirmButton();
        // Открыть аккордеон
        mainPage.openAccordionHeading(accordionTitle);
        // Проверить открытие аккордеона
        Assert.assertEquals("Текст не совпадает", accordionText, driver.findElement(accordionPanel).getText());
    }


}
