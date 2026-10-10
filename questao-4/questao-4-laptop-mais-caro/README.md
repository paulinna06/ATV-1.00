# Questão 4 - Laptop mais caro no Demoblaze (Cypress + TypeScript)

Teste E2E em https://www.demoblaze.com/:

1. Clica na categoria "Laptops" no menu lateral esquerdo.
2. Aguarda o carregamento dos produtos (espera a requisição `bycat` e os cards na tela).
3. Extrai nome e preço de cada laptop da página e identifica o mais caro.
4. Valida com uma asserção que o preço extraído é maior que $0.

O nome e o preço do laptop mais caro são exibidos no log do Cypress e no terminal (`npm run cy:run`).

Observação: a análise considera os laptops da primeira página da categoria.

## Pré-requisitos
- Node.js 18 ou superior

## Como rodar
```bash
npm install
npm run cy:run    # modo headless
npm run cy:open   # modo interativo
```

## Como enviar pelo GitHub
```bash
git init
git add .
git commit -m "Questão 4 - Laptop mais caro no Demoblaze (Cypress + TypeScript)"
git branch -M main
git remote add origin https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
git push -u origin main
```

## Estrutura
```
cypress/
  e2e/laptop-mais-caro.cy.ts
  support/e2e.ts
cypress.config.ts
tsconfig.json
package.json
```
