package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HOMEPAGESCOOTER {

    private final WebDriver driver;

    // Локаторы Вопросов
    private final By Question1 = By.id("accordion__heading-0");
    private final By Question2 = By.id("accordion__heading-1");
    private final By Question3 = By.id("accordion__heading-2");
    private final By Question4 = By.id("accordion__heading-3");
    private final By Question5 = By.id("accordion__heading-4");
    private final By Question6 = By.id("accordion__heading-5");
    private final By Question7 = By.id("accordion__heading-6");
    private final By Question8 = By.id("accordion__heading-7");

    // Локаторы Ответов
    private final By Answer1 = By.id("accordion__panel-0");
    private final By Answer2 = By.id("accordion__panel-1");
    private final By Answer3 = By.id("accordion__panel-2");
    private final By Answer4 = By.id("accordion__panel-3");
    private final By Answer5 = By.id("accordion__panel-4");
    private final By Answer6 = By.id("accordion__panel-5");
    private final By Answer7 = By.id("accordion__panel-6");
    private final By Answer8 = By.id("accordion__panel-7");

    // Кнопки большая/маленькая Заказать
    private final By headerOrderButton = By.xpath(".//button[text()='Заказать'][1]");
    private final By pageOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");

    public HOMEPAGESCOOTER(WebDriver driver) {
        this.driver = driver;
    }

    // Клики по Вопросам
    public void clickQuestion1() {driver.findElement(Question1).click();}
    public void clickQuestion2() {driver.findElement(Question2).click();}
    public void clickQuestion3() {driver.findElement(Question3).click();}
    public void clickQuestion4() {driver.findElement(Question4).click();}
    public void clickQuestion5() {driver.findElement(Question5).click();}
    public void clickQuestion6() {driver.findElement(Question6).click();}
    public void clickQuestion7() {driver.findElement(Question7).click();}
    public void clickQuestion8() {driver.findElement(Question8).click();}

    // Клики по Ответам
    public String getAnswer1() {return driver.findElement(Answer1).getText();}
    public String getAnswer2() {return driver.findElement(Answer2).getText();}
    public String getAnswer3() {return driver.findElement(Answer3).getText();}
    public String getAnswer4() {return driver.findElement(Answer4).getText();}
    public String getAnswer5() {return driver.findElement(Answer5).getText();}
    public String getAnswer6() {return driver.findElement(Answer6).getText();}
    public String getAnswer7() {return driver.findElement(Answer7).getText();}
    public String getAnswer8() {return driver.findElement(Answer8).getText();}

    // Клик Большая кнопка Заказать
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //Клик Маленькая кнопка Заказать
    public void clickPageOrderButton() {
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        driver.findElement(pageOrderButton).click();
    }
}