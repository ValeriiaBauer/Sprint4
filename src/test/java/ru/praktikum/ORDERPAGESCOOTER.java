package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ORDERPAGESCOOTER {

    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private final By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private final By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");
    private final By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By orderNextButton = By.xpath(".//button[text()='Далее']");
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private final By orderConfirmButton = By.xpath(".//button[text()='Да']");
    private final By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");

    public ORDERPAGESCOOTER(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Действия на странице
    public String getOrderHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderHeader)).getText();
    }

    public String getConfirmHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmHeader)).getText();
    }

    public void acceptCookies() {
        clickElement(acceptCookieButton);
    }

    public void setName(String name) {
        setInputField(nameField, name);
    }

    public void setSurname(String surname) {
        setInputField(surnameField, surname);
    }

    public void setAddress(String address) {
        setInputField(addressField, address);
    }

    public void setSubway(String subway) {
        clickElement(subwayField);
        clickElement(By.xpath(".//div[text()='" + subway + "']"));
    }

    public void setPhoneNumber(String phoneNumber) {
        setInputField(phoneNumberField, phoneNumber);
    }

    public void clickNextButton() {
        clickElement(orderNextButton);
    }

    public void setDeliveryDate(String date) {
        setInputField(dateField, date);
    }

    public void setRentalPeriod(String rentalPeriod) {
        clickElement(aboutOrderHeader);
        clickElement(rentalPeriodField);
        clickElement(By.xpath(".//div[text()='" + rentalPeriod + "']"));
    }

    public void setScooterColor(String color) {
        clickElement(By.xpath(".//label[text()='" + color + "']"));
    }

    public void setComment(String comment) {
        setInputField(commentField, comment);
    }

    public void clickCreateOrderButton() {
        clickElement(orderCreateButton);
    }

    public void confirmOrder() {
        clickElement(orderConfirmButton);
    }

    private void setInputField(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(value);
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}