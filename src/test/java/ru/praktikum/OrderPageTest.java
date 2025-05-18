package ru.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderPageTest {
    private WebDriver driver;
    private static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ORDER_HEADER = "Для кого самокат"; // Исправленный ожидаемый заголовок

    @Before
    public void setUp() {
        // Инициализация драйвера с настройками
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SITE_URL);

        // Принятие кук, если они есть
        new HomePageScooter(driver).acceptCookies();
    }

    @Test
    public void orderPageShouldOpenAfterHeaderButtonClick() {
        HomePageScooter homePage = new HomePageScooter(driver);
        homePage.clickHeaderOrderButton();

        OrderPageScooter orderPage = new OrderPageScooter(driver);
        String actualHeader = orderPage.getOrderHeader();

        assertThat("После клика по кнопке заказа в шапке должен открываться экран с правильным заголовком",
                actualHeader, containsString(ORDER_HEADER));
    }

    @Test
    public void orderPageShouldOpenAfterPageButtonClick() {
        HomePageScooter homePage = new HomePageScooter(driver);
        homePage.scrollToFAQSection(); // Прокрутка к нижней кнопке
        homePage.clickPageOrderButton();

        OrderPageScooter orderPage = new OrderPageScooter(driver);
        String actualHeader = orderPage.getOrderHeader();

        assertThat("После клика по кнопке заказа в середине страницы должен открываться экран с правильным заголовком",
                actualHeader, containsString(ORDER_HEADER));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}