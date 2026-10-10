# Dynamic Loading — Selenium com Java

Teste automatizado para a página:
https://the-internet.herokuapp.com/dynamic_loading/1

## O que o teste faz

1. Abre a página de carregamento dinâmico.
2. Clica em **Start**.
3. Usa uma espera explícita do Selenium para aguardar o elemento `#loading` (texto `Loading...`) ficar invisível.
4. Aguarda o elemento `#finish h4` ficar visível.
5. Valida que o texto final contém `Hello World!`.
6. Fecha o navegador no fim do teste.

## Requisitos

- JDK 17 ou superior
- Maven
- Google Chrome instalado
- VS Code ou GitHub Codespaces

## Executar no VS Code / Codespaces

Abra esta pasta como projeto e execute no terminal:

```bash
mvn test
```

Se o ambiente não tiver o Chrome instalado ou acessível, configure um navegador compatível no ambiente de execução. O Selenium Manager pode precisar de internet na primeira execução para resolver o driver.

## Arquivo principal

`src/test/java/DynamicLoadingTest.java`
