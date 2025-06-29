package ru.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String ORDER_CONFIRMATION_TEXT = "Заказ оформлен";

    // Параметры теста
    private final String testName;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderTest(String testName, String name, String surname, String address,
                     String subwayStation, String phoneNumber, String deliveryDate,
                     String rentalPeriod, String scooterColor, String comment) {
        this.testName = testName;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Заказ через верхнюю кнопку (сolor black)",
                        "Лариса", "Гузеева", "г. Москва, ул. Стрелецкая, д.18", "Сокольники",
                        "89324445566", "01.05.2025", "сутки", "черный жемчуг", "самокат с колесами"},

                {"Заказ через нижнюю кнопку (сolor grey)",
                        "Роза", "Сябитова", "г. Москва, ул. Профсоюзная, д. 136", "Коньково",
                        "+79225556677", "10.05.2025", "двое суток", "серая безысходность", "самокат с рулем"}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(SITE_URL);
    }

    @Test
    public void shouldCreateOrderSuccessfully() {
        HomePageScooter homePage = new HomePageScooter(driver);
        homePage.acceptCookies();

        // Выбор кнопки заказа в зависимости от теста
        if (testName.contains("верхнюю")) {
            homePage.clickHeaderOrderButton();
        } else {
            homePage.scrollToFAQSection();
            homePage.clickPageOrderButton();
        }

        OrderPageScooter orderPage = new OrderPageScooter(driver);

        // Заполнение первой страницы заказа
        orderPage.fillFirstOrderPage(name, surname, address, subwayStation, phoneNumber);

        // Заполнение второй страницы заказа
        orderPage.fillSecondOrderPage(deliveryDate, rentalPeriod, scooterColor, comment);

        // Проверка подтверждения заказа
        String actualConfirmation = orderPage.getOrderConfirmationText();
        assertThat("Должно отображаться подтверждение заказа",
                actualConfirmation, containsString(ORDER_CONFIRMATION_TEXT));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}