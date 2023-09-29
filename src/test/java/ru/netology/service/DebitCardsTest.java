package ru.netology.service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import static org.junit.jupiter.api.Assertions.assertEquals;


public class DebitCardsTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldPositiveTest1() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+72563587459");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);

    }

    @Test
    void shouldPositiveTest2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров-Иванов Влад");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+76545852563");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
    }

    @Test
    void shouldPositiveTest3() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петрова-Иванова Эмилия-Анна");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+74785412635");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText= driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
    }

    @Test
    void shouldPositiveTest4() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Краснянский Денис Игоревич");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79967949758");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
    }

    @Test
    void shouldNegativeResultNameTest1() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Eric Winchester");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+75555555555");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualText);

    }

    @Test
    void shouldNegativeResultPhoneNumberTest2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Марк");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+711111");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);

    }

    @Test
    void shouldNegativeResultPhoneNumberTest3() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сыкунова Алина");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+755665465564564564564566546");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);

    }

    @Test
    void shouldNegativeResultPhoneNumberTest4() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("86589541265");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualText);


    }

    @Test
    void shouldNegativeResultNameTest5() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+71111111111");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals("Поле обязательно для заполнения", actualText);


    }

    @Test
    void shouldNegativeResultPhoneNumberTest6() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("22");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        assertEquals("Поле обязательно для заполнения", actualText);

    }

    @Test
    void shouldNegativeResultAgreementTest7() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+71111111111");
        driver.findElement(By.cssSelector("button")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).getText().trim();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", actualText);

    }

}