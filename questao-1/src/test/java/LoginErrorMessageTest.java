import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginErrorMessageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void configurarNavegador() {
        // Selenium Manager tenta localizar/baixar o driver compatível automaticamente.
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void deveMostrarErroQuandoLoginEstaVazio() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Os campos ficam em branco: não digitamos usuário nem senha.
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();

        // Clica no botão Login.
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Aguarda o balão de mensagem de erro aparecer.
        WebElement flash = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
        );

        // 1. Confere o texto da mensagem.
        assertTrue(
                flash.getText().contains("Your username is invalid!"),
                "A mensagem deveria conter: Your username is invalid!"
        );

        // 2. Confere se o balão tem a classe CSS 'error'.
        String classes = flash.getAttribute("class");
        assertTrue(
                classes != null && classes.matches(".*\\berror\\b.*"),
                "O elemento #flash deveria possuir a classe CSS 'error'. Classes encontradas: " + classes
        );
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
