//Содержит методы для тестов из файлов OrderTest и QuestionTest.
package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage {
    //Стартовая страница приложения
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru";
    //Кнопка "заказать" в хэдере
    private static final By ORDER_HEADER_BUTTON = By.xpath("//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");
    //Кнопка "заказать" в мэйне
    private static final By ORDER_HOME_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Строка для вставки для поиска корневых элементов в FAQ
    private static final By ACCORDION_ITEM = By.xpath("//div[@class = 'accordion__item']");
    //Кнопка принятия кук
    private static final By ACCEPT_COOKIES = By.id("rcc-confirm-button");


    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Открыть страницу и согласиться принять куки, что бы это окно более не мешало.
    public void pageOpen() {
        driver.get(PAGE_URL);
    }

    //Принять куки
    public void AcceptCookies() {
        driver.findElement(ACCEPT_COOKIES).click();
    }

    //Нажать на кнопку заказ (верхнюю или нижнюю, в зависимости от внешнего параметра)
    public void clickOrderButton(String entryPoint) {
        if (entryPoint.equals("низ")) {
            driver.findElement(ORDER_HOME_BUTTON).click();
        } else if (entryPoint.equals("верх")) {
            driver.findElement(ORDER_HEADER_BUTTON).click();
        }
    }

    //Нажать на заголовок FAQ, передав номер элемента FAQ
    public void clickAccordionItemButton(int number_items) {
        driver.findElements(ACCORDION_ITEM).get(number_items).click();
    }

    //Текст вопроса
    public String isQuestionTextDisplayed(int number_items) {
        WebElement QuestionTextDisplayed = driver.findElement(By.id("accordion__heading-"+number_items));
        return QuestionTextDisplayed.getText();
    }

    //Блок ответа отображается на экране
    public boolean isBlockAnswerTextDisplayed(int number_items) {
        WebElement answerBlockTextDisplayed = driver.findElement(By.id("accordion__panel-"+number_items));
        return answerBlockTextDisplayed.isDisplayed();//
    }

    //Наличие видимого текста (не пустая строка) в блоке ответа
    public String isAnswerTextDisplayed(int number_items) {
        clickAccordionItemButton(number_items);
        WebElement answerTextDisplayed = driver.findElement(By.id("accordion__panel-"+number_items));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-"+number_items)));
        return answerTextDisplayed.getText();//getText - возвращает только для видимых объектов
    }

    //Получить количество вопросов в FAQ
    public int getQuestionsSize() {
        return driver.findElements(ACCORDION_ITEM).size();
    }
}