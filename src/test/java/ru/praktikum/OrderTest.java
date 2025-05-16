package ru.praktikum;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
    public class OrderTest {

        private WebDriver driver;
        private static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";
        private static final String CONFIRM_HEADER_TEXT = "Посмотреть статус"; // Update with actual expected text

        // Поля для оформления Самоката
        private final String name;
        private final String surname;
        private final String address;
        private final String subwayStation;
        private final String phoneNumber;
        private final String deliveryDate;
        private final String rentalPeriod;
        private final String scooterColor;
        private final String comment;

        public OrderTest(String name, String surname, String address, String subwayStation,
                         String phoneNumber, String deliveryDate, String rentalPeriod,
                         String scooterColor, String comment) {
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

        @Parameterized.Parameters(name = "Test data: {0} {1}")
        public static Object[][] testData() {
            return new Object[][] {
                    {"Лариса", "Гузеева", "г. Москва, ул. Стрелецкая, д.18", "Марьина Роща",
                            "89324445566", "01.05.2025", "сутки", "чёрный жемчуг", "самокат с колесами"},
                    {"Роза", "Сябитова", "г. Москва, ул. Профсоюзная, д. 136", "Коньково",
                            "+79225556677", "10.05.2025", "двое суток", "серая безысходность", "самокат с рулем"},
            };
        }

        @Before
        public void setUp() {
            driver = new ChromeDriver();
            driver.get(SITE_URL);
        }

        @Test
        public void shouldCreateOrderSuccessfully() {
            HOMEPAGESCOOTER Page = new HOMEPAGESCOOTER(driver);
            Page.clickHeaderOrderButton();

            ORDERPAGESCOOTER orderPage = new ORDERPAGESCOOTER(driver);
            orderPage.acceptCookies();

            // Заполнение формы Заказа
            orderPage.setName(name);
            orderPage.setSurname(surname);
            orderPage.setAddress(address);
            orderPage.setSubway(subwayStation);
            orderPage.setPhoneNumber(phoneNumber);
            orderPage.clickNextButton();

            // Дополнительные детали аренды
            orderPage.setDeliveryDate(deliveryDate);
            orderPage.setRentalPeriod(rentalPeriod);
            orderPage.setScooterColor(scooterColor);
            orderPage.setComment(comment);

            // Завершение Заказа
            orderPage.clickCreateOrderButton();
            orderPage.confirmOrder();

            // Успешное создание Заказа
            String actualConfirmation = orderPage.getConfirmHeader();
            assertThat("Заголовок подтверждения заказа должен содержать ожидаемый текст",
                    actualConfirmation, containsString(CONFIRM_HEADER_TEXT));
        }

        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }