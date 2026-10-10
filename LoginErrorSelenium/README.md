# Teste de mensagem de erro de login com Selenium (Java)

Este projeto automatiza o cenário em que o usuário tenta entrar sem preencher usuário e senha.

## O que o teste verifica

1. A mensagem dentro de `div#flash` contém `Your username is invalid!`.
2. O elemento `div#flash` possui a classe CSS `error`.

## Requisitos

- JDK 17 ou superior
- Maven
- Google Chrome instalado
- VS Code com a extensão **Extension Pack for Java** (recomendado)

## Como executar no VS Code

1. Extraia o arquivo ZIP.
2. Abra a pasta `LoginErrorSelenium` no VS Code.
3. Aguarde o Maven baixar as dependências.
4. Abra o terminal integrado (`Terminal > New Terminal`).
5. Execute:

```bash
mvn test
```

O Selenium 4 usa o Selenium Manager para tentar gerenciar automaticamente o driver do Chrome. Na primeira execução, pode ser necessário ter conexão com a internet.

## Arquivo principal

`src/test/java/LoginErrorMessageTest.java`

O teste não preenche os campos: ele limpa ambos e clica em **Login**.
