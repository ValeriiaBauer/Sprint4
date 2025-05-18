package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;


public class OrderPageScooter {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private static final By ORDER_HEADER = By.xpath(".//div[text()='Для кого самокат']");
    private static final By ABOUT_ORDER_HEADER = By.xpath(".//div[text()='Про аренду']");
    private static final By ACCEPT_COOKIE_BUTTON = By.xpath(".//button[text()='да все привыкли']");
    private static final By NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    private static final By SURNAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By ADDRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By SUBWAY_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By PHONE_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");
    private static final By DATE_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD_FIELD = By.xpath(".//div[@class='Dropdown-placeholder']");
    private static final By COMMENT_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By ORDER_CREATE_BUTTON = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private static final By ORDER_CONFIRM_BUTTON = By.xpath(".//button[text()='Да']");
    private static final By CONFIRM_HEADER = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");
    private static final By BLACK_COLOR_CHECKBOX = By.id("black");
    private static final By GREY_COLOR_CHECKBOX = By.id("grey");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Основные действия
    public void fillFirstOrderPage(String name, String surname, String address, String subway, String phone) {
        acceptCookies();
        setName(name);
        setSurname(surname);
        setAddress(address);
        setSubway(subway);
        setPhoneNumber(phone);
        clickNextButton();
    }

    public void fillSecondOrderPage(String date, String period, String color, String comment) {
        setDeliveryDate(date);
        setRentalPeriod(period);
        setScooterColor(color);
        setComment(comment);
        clickCreateOrderButton();
        confirmOrder();
    }

    // Методы для работы с элементами
    public String getOrderHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_HEADER)).getText();
    }

    public String getOrderConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_HEADER)).getText();
    }

    public void acceptCookies() {
        try {
            clickElement(ACCEPT_COOKIE_BUTTON);
        } catch (TimeoutException e) {
            // Куки уже приняты или кнопка не появилась
        }
    }

    private void setName(String name) {
        setInputField(NAME_FIELD, name);
    }

    private void setSurname(String surname) {
        setInputField(SURNAME_FIELD, surname);
    }

    private void setAddress(String address) {
        setInputField(ADDRESS_FIELD, address);
    }

    private void setSubway(String subway) {
        clickElement(SUBWAY_FIELD);
        WebElement station = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[@class='select-search__select']//*[text()='" + subway + "']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", station);
        station.click();
    }

    private void setPhoneNumber(String phoneNumber) {
        setInputField(PHONE_FIELD, phoneNumber);
    }

    private void clickNextButton() {
        clickElement(NEXT_BUTTON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ABOUT_ORDER_HEADER));
    }

    private void setDeliveryDate(String date) {
        setInputField(DATE_FIELD, date);
        // Нажимаем Enter для подтверждения даты
        driver.findElement(DATE_FIELD).sendKeys(Keys.ENTER);
    }

    private void setRentalPeriod(String period) {
        clickElement(RENTAL_PERIOD_FIELD);
        clickElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']"));
    }


    private void setComment(String comment) {
        setInputField(COMMENT_FIELD, comment);
    }

    private void clickCreateOrderButton() {
        clickElement(ORDER_CREATE_BUTTON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_CONFIRM_BUTTON));
    }

    private void confirmOrder() {
        clickElement(ORDER_CONFIRM_BUTTON);
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_HEADER));
    }

    private void setInputField(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(value);
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public void setScooterColor(String color) {
        String normalizedColor = color.toLowerCase().trim();
        By colorLocator;

        switch(normalizedColor) {
            case "черный жемчуг":
                colorLocator = By.id("black");
                break;
            case "серая безысходность":
                colorLocator = By.id("grey");
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый цвет: " + color);
        }

        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(colorLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbox);
    }
}