package ru.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderPage {
    private WebDriver driver;
    private static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ORDER_HEADER = "Про аренду"; // Update with actual expected header

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(SITE_URL);
    }

    @Test
    public void orderPageShouldOpenAfterHeaderButtonClick() {
        HOMEPAGESCOOTER Page = new HOMEPAGESCOOTER(driver);
        Page.clickHeaderOrderButton();

        ORDERPAGESCOOTER orderPage = new ORDERPAGESCOOTER(driver);
        String actualHeader = orderPage.getOrderHeader();

        assertThat("Заголовок страницы заказа должен содержать ожидаемый текст",
                actualHeader, containsString(ORDER_HEADER));
    }

    @Test
    public void orderPageShouldOpenAfterPageButtonClick() {
        HOMEPAGESCOOTER Page = new HOMEPAGESCOOTER(driver);
        Page.clickPageOrderButton();

        ORDERPAGESCOOTER orderPage = new ORDERPAGESCOOTER(driver);
        String actualHeader = orderPage.getOrderHeader();

        assertThat("Заголовок страницы заказа должен содержать ожидаемый текст",
                actualHeader, containsString(ORDER_HEADER));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}