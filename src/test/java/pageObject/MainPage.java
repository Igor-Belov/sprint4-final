//Содержит методы для тестов из файлов OrderTest и QuestionTest.
package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    //Стартовая страница приложения
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru";
    //Кнопка "заказать" в хэдере
    private static final By ORDER_HEADER_BUTTON = By.xpath("//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']");
    //Кнопка "заказать" в мэйне
    private static final By ORDER_HOME_BUTTON = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Строка для вставки для поиска корневых элементов в FAQ
    private static final By ACCORDION_ITEM = By.xpath("//div[@class = 'accordion__item']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Открыть страницу и согласиться принять куки, что бы это окно более не мешало.
    public void pageOpen() {
        driver.get(PAGE_URL);
        driver.findElement(By.id("rcc-confirm-button")).click();
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

    //Видимость блока ответа
    public boolean isAnswerDisplayed(int number_items) {
        WebElement answer = driver.findElements(ACCORDION_ITEM).get(number_items).findElement(By.xpath(".//div[@class = 'accordion__panel']"));
        return answer.isDisplayed();
    }

    //Наличие видимого текста (не пустая строка) в блоке ответа
    public boolean isAnswerTextDisplayed(int number_items) {
        WebElement answerTextDisplayed = driver.findElements(ACCORDION_ITEM).get(number_items).findElement(By.xpath(".//div[@class = 'accordion__panel']/p"));
        return !answerTextDisplayed.getText().isEmpty();//getText - возвращает только для видимых объектов
    }

    //Получить количество вопросов в FAQ
    public int getQuestionsSize() {
        return driver.findElements(ACCORDION_ITEM).size();
    }
}



