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

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private HomePageScooter homePage;

    private final int questionIndex;
    private final String expectedAnswer;
    private final String description;

    public FAQTest(int questionIndex, String expectedAnswer, String description) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
        this.description = description;
    }

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Проверка ответа о стоимости аренды"},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "Проверка ответа о нескольких самокатах"},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "Проверка ответа о расчете времени аренды"},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "Проверка ответа о заказе на сегодня"},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Проверка ответа о продлении аренды"},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "Проверка ответа о времени работы батареи"},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "Проверка ответа об отмене заказа"},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "Проверка ответа о зоне доставки"}
        });
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.scrollToFAQSection();
    }

    @Test
    public void checkFAQAnswer() {
        String actualAnswer = homePage.getFAQAnswer(questionIndex);
        assertEquals(description, expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}