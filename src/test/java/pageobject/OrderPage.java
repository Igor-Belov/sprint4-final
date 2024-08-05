//Описание элементов и методов нужных для проведения позитивных тестов из сценария OrderTest
package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {
    //Нужные элементы страницы
    //Поле Имя
    private static final By NAME_FIELD = By.xpath("//Input[@placeholder = '* Имя']");
    //Поле фамилия
    private static final By LAST_NAME_FIELD = By.xpath("//Input[@placeholder = '* Фамилия']");
    //Поле адрес
    private static final By ADDRESS_FIELD = By.xpath("//Input[@placeholder = '* Адрес: куда привезти заказ']");
    //Поле метро
    private static final By SUBWAY_FIELD = By.xpath("//Input[@placeholder = '* Станция метро']");
    //Поле телефон
    private static final By PHONE_FIELD = By.xpath("//Input[@placeholder = '* Телефон: на него позвонит курьер']");

    //Поле дата привоза самоката
    private static final By WHEN_FIELD = By.xpath("//Input[@placeholder = '* Когда привезти самокат']");
    //Поле срок аренды
    private static final By PERIOD_FIELD = By.className("Dropdown-root");
    //Поле цвет самоката
    private static final By BLACK_COLOR_FIELD = By.xpath("//input[@id = 'black']/parent::label");
    private static final By GRAY_COLOR_FIELD = By.xpath("//input[@id = 'grey']/parent::label");
    //Поле комментарий курьеру
    private static final By COMMENT_FIELD = By.xpath("//input[@placeholder = 'Комментарий для курьера']");
    //Сообщение, что заказ оформлен.
    private static final By ORDER_PLACES = By.xpath("//div[text() = 'Заказ оформлен']");

    //Кнопка Далее на первом экране
    private static final By NEXT_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']");
    //Кнопка Заказать на втором экране
    private static final By ORDER_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");
    //Кнопка да (подтвердить заказ) на третьем экране
    private static final By YES_ORDER_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    private final WebDriver driver;

    //конструктор объекта OrderPage с методами страницы (трех экранов и сообщения об успешном заказе) заказа.
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Методы
    //Ввод имени
    public void enterName(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    //Ввод фамилии
    public void enterLastName(String lastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
    }

    //Ввод адреса доставки
    public void enterAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    //Ввод ближайшего метро
    public void enterSubway(String subway) {
        driver.findElement(SUBWAY_FIELD).click();
        WebElement subwayStation = driver.findElement(By.xpath("//div[text() = '"+subway+"']/parent::button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", subwayStation);
        subwayStation.click();
    }

    //Ввод телефона для ос
    public void enterPhone(String Phone) {
        driver.findElement(PHONE_FIELD).sendKeys(Phone);
    }

    //Нажатие кнопки "Далее" (первый экран заказа)
    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    //Ввод даты
    public void enterDateStart(String date) {
        driver.findElement(WHEN_FIELD).sendKeys(date);
        driver.findElement(WHEN_FIELD).sendKeys(Keys.ESCAPE);//нужно для сброса окна выбора даты
    }

    //Ввод срока аренды
    public void enterPeriod(String period) {
        driver.findElement(PERIOD_FIELD).click();
        driver.findElement(By.xpath("//div[@class = 'Dropdown-option' and text() = '" + period + "']")).click();
    }

    //Выбор цвета
    public void enterColor(String color) {
        if (color.equals(driver.findElement(BLACK_COLOR_FIELD).getText())) {
            driver.findElement(BLACK_COLOR_FIELD).click();
        } else if (color.equals(driver.findElement(GRAY_COLOR_FIELD).getText())) {
            driver.findElement(GRAY_COLOR_FIELD).click();
        }
    }

    //Написать комментарий
    //Если параметр пустой, поле не трогаем
    public void enterComment(String comment) {
        if (!comment.isEmpty()) {
        driver.findElement(COMMENT_FIELD).sendKeys(comment);
        }
    }

    //Нажатие кнопки "заказать" (второй экран заказа)
    public void clickOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    //Нажатие кнопки "да" (третий экран, подтвердить заказ)
    public void clickYesOrderButton() {
        driver.findElement(YES_ORDER_BUTTON).click();
    }

    //Метод проверки появилось ли окно подтверждения заказ
    public boolean isSuccessfulOrderWindowDisplayed() {
        try {
            driver.findElement(ORDER_PLACES).isDisplayed();
            return true;
        } catch (NoSuchElementException nse ) {
            return false;
        }
    }
}
