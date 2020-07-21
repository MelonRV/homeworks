package mystepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {
    WebDriver driver = new ChromeDriver();


    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Melon\\IdeaProjects\\homeworks\\chromedriver.exe");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void close() { driver.quit();
    }

    @Пусть("открыть ресурс авито")
    public void openAvito() {

        driver.get("https://www.avito.ru");
    }

    @И("в выпадающем списке категорий выбрана {sortCategory}")
    public void selectByCategory(SortCategory category) {
        Select selector = new Select(driver.findElement(By.cssSelector("#category")));
        selector.selectByValue(SortCategory.valueOf(String.valueOf(category)).getVal());
    }

    @ParameterType(".*")
    public SortCategory sortCategory(String name) {
        return SortCategory.valueOf(name);

    }

    @И("в поле поиска введено значение {string}")
    public void input(String printer) {
        driver.findElement(By.cssSelector("#search")).sendKeys(printer);

    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void selectRegion() {
        driver.findElement(By.xpath("//div[@class='main-text-2PaZG']")).click();
    }

    @Тогда("в поле регион введено значение {string}")
    public void inputRegion(String vladik) throws InterruptedException {
        driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']")).sendKeys(vladik, Keys.ENTER);
        Thread.sleep(1000);
    }

    @И("нажата кнопка показать объявления")
    public void watch() {
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
    }

    @Тогда("открылась страница результаты по запросу {string}")
    public void checkPage(String printer) {
        String text = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertTrue(text.contains(printer), "найдена не та страница");
    }

    @И("активирован чекбокс только с фотографией")
    public void checkcheckbox() throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//input[@name='withImagesOnly']"));
        if (!checkbox.isSelected()) {
            checkbox.findElement(By.xpath("./..")).click();
            Thread.sleep(1000);
        }
    }

    @И("в выпадающем списке сортировка выбрано значение {sortPrice}")
    public void sortByPrice(SortByPrice sort) {
        Select selector = new Select(driver.findElement(By.xpath("//div[@class='form-select-v2 sort-select-3QxXG']/select")));
        selector.selectByValue(SortByPrice.valueOf(String.valueOf(sort)).getVal());
    }

    @ParameterType(".*")
    public SortByPrice sortPrice(String name) {
        return SortByPrice.valueOf(name);
    }

    @И("в консоль выведено значение название и цены {int} первых товаров")
    public void output(int arg) {
        List<WebElement> name = driver.findElements(By.xpath("//div[contains(@class, 'snippet-title-row')]"));
        List<WebElement> price = driver.findElements(By.xpath("//div[contains(@class, 'snippet-price-row')]"));

        for (int i = 0; i < arg; i++) {
            System.out.println(name.get(i).getText());
            System.out.println(price.get(i).getText());
        }
    }
}

