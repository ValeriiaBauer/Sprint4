package ru.praktikum;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FAQTest {

    private WebDriver driver;

    //Ожидаемы ответы на вопросы
    private static final String Answer1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String Answer2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String Answer3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String Answer4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String Answer5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String Answer6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String Answer7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String Answer8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    @Test
    public void FAQCORRECTANSWER() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // Прокрутка к FAQ
        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);

        //Создание объекта класса
        HOMEPAGESCOOTER Page = new HOMEPAGESCOOTER(driver);

        //Взаимодействие и проверки для каждого Вопроса
        Page.clickQuestion1();
        assertEquals("Ответ для вопроса 1 не совпадает", Answer1, Page.getAnswer1());
        Page.clickQuestion2();
        assertEquals("Ответ для вопроса 2 не совпадает", Answer2, Page.getAnswer2());
        Page.clickQuestion3();
        assertEquals("Ответ для вопроса 3 не совпадает", Answer3, Page.getAnswer3());
        Page.clickQuestion4();
        assertEquals("Ответ для вопроса 4 не совпадает", Answer4, Page.getAnswer4());
        Page.clickQuestion5();
        assertEquals("Ответ для вопроса 5 не совпадает", Answer5, Page.getAnswer5());
        Page.clickQuestion6();
        assertEquals("Ответ для вопроса 6 не совпадает", Answer6, Page.getAnswer6());
        Page.clickQuestion7();
        assertEquals("Ответ для вопроса 7 не совпадает", Answer7, Page.getAnswer7());
        Page.clickQuestion8();
        assertEquals("Ответ для вопроса 8 не совпадает", Answer8, Page.getAnswer8());
        System.out.println("Все ответы соответствуют ожидаемым");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}