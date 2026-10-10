# Questão 5 - Rastreador de ofertas no SauceDemo (Cypress + TypeScript)

Robô E2E em https://www.saucedemo.com/:

1. Faz login com `standard_user` / `secret_sauce` (credenciais públicas de demonstração do site).
2. Mapeia todos os itens exibidos na página inicial e extrai nome e preço de cada um.
3. Filtra apenas os produtos que custam menos de $20.00.
4. Exibe as ofertas no console (log do Cypress e terminal) e também salva em `cypress/results/ofertas-abaixo-20.json`.

## Pré-requisitos
- Node.js 18 ou superior

## Como rodar
```bash
npm install
npm run cy:run    # modo headless (as ofertas aparecem no terminal)
npm run cy:open   # modo interativo
```

## Como enviar pelo GitHub
```bash
git init
git add .
git commit -m "Questão 5 - Rastreador de ofertas no SauceDemo (Cypress + TypeScript)"
git branch -M main
git remote add origin https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
git push -u origin main
```

## Estrutura
```
cypress/
  e2e/ofertas.cy.ts
  support/e2e.ts
cypress.config.ts
tsconfig.json
package.json
```
