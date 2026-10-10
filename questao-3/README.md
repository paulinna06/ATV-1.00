# SauceDemo E2E — Carrinho de compras

Teste automatizado em Java com Selenium WebDriver e JUnit 5 para:
https://www.saucedemo.com/

## Cenário coberto

1. Faz login com `standard_user` e `secret_sauce`.
2. Adiciona três produtos diferentes ao carrinho.
3. Verifica que o contador mostra `3`.
4. Remove um dos produtos adicionados.
5. Valida que o emblema do carrinho mostra exatamente `2` e está visível.

## Requisitos

- JDK 17 ou superior
- Maven
- Google Chrome instalado e acessível no ambiente
- VS Code ou GitHub Codespaces

## Como executar no Codespaces

1. Baixe e extraia o ZIP, ou envie os arquivos para um repositório GitHub.
2. Abra a pasta como projeto no Codespaces.
3. No terminal, execute:

```bash
mvn test
```

O Selenium Manager tentará configurar automaticamente o driver compatível com o Chrome. Em Codespaces, pode ser necessário configurar um navegador/driver disponível no container se o Chrome não estiver instalado.

## Arquivo do teste

`src/test/java/SauceDemoCartTest.java`
