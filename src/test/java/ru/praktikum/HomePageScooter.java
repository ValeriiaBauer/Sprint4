package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePageScooter {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локатор секции FAQ
    private static final By FAQ_SECTION = By.xpath(".//div[@class='accordion']");
    private static final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    private static final By HEADER_ORDER_BUTTON = By.xpath(".//button[text()='Заказать'][1]");
    private static final By PAGE_ORDER_BUTTON = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");


    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для прокрутки к FAQ
    public void scrollToFAQSection() {
        WebElement faqSection = wait.until(ExpectedConditions.visibilityOfElementLocated(FAQ_SECTION));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqSection);
    }

    // Обобщенный метод для работы с вопросами
    public String getFAQAnswer(int questionIndex) {
        By questionLocator = By.id("accordion__heading-" + questionIndex);
        By answerLocator = By.id("accordion__panel-" + questionIndex);

        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        question.click();

        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

    // Методы для работы с кнопками заказа
    public void clickHeaderOrderButton() {
        driver.findElement(HEADER_ORDER_BUTTON).click();
    }

    public void clickPageOrderButton() {
        WebElement orderButton = driver.findElement(PAGE_ORDER_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButton);
        orderButton.click();
    }

    public void acceptCookies() {
        try {
            // Ожидаем появления кнопки и кликаем
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
            cookieButton.click();

            // Ждем исчезновения кнопки (опционально)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_BUTTON));
        } catch (TimeoutException e) {
            // Если кнопка не появилась за 10 секунд, продолжаем без ошибки
            System.out.println("Кнопка принятия куки не найдена, продолжаем выполнение");
        }
    }
}