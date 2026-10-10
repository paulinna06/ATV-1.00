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

public class DynamicLoadingTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void configurarNavegador() {
        // Selenium Manager tenta encontrar/gerenciar automaticamente o driver do Chrome.
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    void deveAguardarCarregamentoEAmostrarHelloWorld() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Clica no botão Start.
        driver.findElement(By.cssSelector("#start button")).click();

        // Aguarda o indicador "Loading..." desaparecer.
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

        // Aguarda o resultado final ficar visível.
        WebElement resultado = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4"))
        );

        // Valida o texto exibido.
        assertTrue(
                resultado.getText().contains("Hello World!"),
                "O texto final esperado era 'Hello World!', mas foi exibido: " + resultado.getText()
        );
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
