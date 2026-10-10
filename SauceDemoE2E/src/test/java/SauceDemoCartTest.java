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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoCartTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void configurarNavegador() {
        // Selenium Manager tenta resolver automaticamente o driver do Chrome.
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    void deveAtualizarContadorDoCarrinhoAposAdicionarTresERemoverUmProduto() {
        driver.get("https://www.saucedemo.com/");

        // 1. Login com usuário de teste e senha fornecidos na própria página.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                .sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Aguarda a página de produtos carregar.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));

        // 2. Adiciona três produtos diferentes ao carrinho.
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        // Confirma que o contador chegou a 3 antes de remover um item.
        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        assertEquals("3", cartBadge.getText(), "O carrinho deveria mostrar 3 produtos após as adições.");

        // 3. Remove um dos produtos adicionados.
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

        // 4. Valida que o emblema do carrinho mostra exatamente '2'.
        WebElement updatedBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
        );
        assertEquals("2", updatedBadge.getText(), "O contador do carrinho deveria mostrar exatamente 2.");

        // Confirma que o badge é exibido no carrinho no topo da página.
        assertTrue(updatedBadge.isDisplayed(), "O emblema do carrinho deveria estar visível.");
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
