//Параметрический тест. Тестовые данные представляют собой два массива с данными для заполнения форм и выбором точки входа.
//На последнем шаге в хроме выявлен баг с появлением окна с информацией о созданном заказе. В firefox все ок.

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import pageObject.OrderPage;

import java.util.concurrent.TimeUnit;

//создадим класс с параметрическим запуском
@RunWith(Parameterized.class)
public class OrderTest {
    //переменные класса
    private final String name;
    private final String lastName;
    private final String address;
    private final String subway;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    private final String entryPoint;

    private WebDriver driver;

    //конструктор класса OrderTest
    public OrderTest(String name, String lastName, String address, String subway, String phone, String date, String period, String color, String comment, String entryPoint) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.entryPoint = entryPoint;
    }

    //перед каждым тестом делаем создание объекта драйвера и включаем ему неявное ожидание поиска.
    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    //после каждого теста закрываем браузер и драйвер
    @After
    public void teardown() {
        driver.quit();
    }

    //Входные данные для позитивных сценариев. Комментарий может быть пустым "", тогда поле комментария не будет кликаться.
    @Parameterized.Parameters()
    public static Object[][] getData() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][] {
                {/*имя*/"Имя", /*фамилия*/"Фамилия", /*адрес Москва и область*/"Какой то адрес", /*метро*/"Лубянка", /*телефон*/"01234567891",
                        /*дата начала*/"25.10.2025", /*срок аренда*/"семеро суток", /*Цвет самоката*/ "чёрный жемчуг", /*комментарий*/ "Звонить три раза",/*Точка входа*/ "верх"},
                {"Иван", "Петров", "д. Горки, Ул. Ивана Петрова, д.1", "Речной вокзал", "+71231234567",
                        "31.12.2024", "сутки", "серая безысходность", "", "низ"}
        };
    }

    //Прохождение позитивного пути - нажатие одной из двух кнопок заказа,
    // заполнение первой формы и нажатие 'далее', заполнение второй формы и нажатие 'заказ',
    // подтверждение заказа в третьем окне кнопкой 'Да'.
    // Проверка, что окно о создании оффера появилось.
    @Test
    public void create_order_positive_data_order_created() {
        //главная страница
        MainPage pageOpen = new MainPage(driver);
        pageOpen.pageOpen();
        pageOpen.clickOrderButton(entryPoint);
        //первая страница заказа
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterName(name);
        orderPage.enterLastName(lastName);
        orderPage.enterAddress(address);
        orderPage.enterSubway(subway);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();
        //вторая страница заказа
        orderPage.enterDateStart(date);
        orderPage.enterPeriod(period);
        orderPage.enterColor(color);
        orderPage.enterComment(comment);
        orderPage.clickOrderButton();
        //третья страница
        orderPage.clickYesOrderButton();
        Assert.assertTrue(orderPage.isSuccessfulOrderWindowDisplayed());
    }
}
