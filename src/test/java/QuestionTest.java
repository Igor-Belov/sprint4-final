//Задание: Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
//Реализация - на стрелочку нельзя навестись, так как это псевдоэлемент. Поэтому наводимся на контейнер содержащий стрелочку и текст
//Сами тексты не представлены в таске и макет отсутствует. Поэтому тестируем само наличие(не пустой) текста в выпадающем меню, а не его качество.

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class QuestionTest {

    private WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void expand_accordion_items_click_item_expand() {
        MainPage mainPage = new MainPage(driver);
        mainPage.pageOpen();
        for (int i = 0; i < mainPage.getQuestionsSize(); i++) {
            Assert.assertFalse(mainPage.isAnswerDisplayed(i));//проверим, что до клика блок скрыт
            mainPage.clickAccordionItemButton(i);
            Assert.assertTrue(mainPage.isAnswerDisplayed(i));//проверим, что после клика блок ответа появился
            Assert.assertTrue(mainPage.isAnswerTextDisplayed(i));//проверим что в блоках есть какой-то текст
        }
    }
}
