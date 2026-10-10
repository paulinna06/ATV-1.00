/// <reference types="cypress" />

const USUARIO = "standard_user";
const SENHA = "secret_sauce";
const LIMITE_OFERTA = 20.0;

interface Produto {
  nome: string;
  preco: number;
}

describe("SauceDemo - rastreador de ofertas abaixo de $20.00", () => {
  it("mapeia a vitrine e exibe apenas os produtos que custam menos de $20.00", () => {
    // Login
    cy.visit("/");
    cy.get('[data-test="username"]').type(USUARIO);
    cy.get('[data-test="password"]').type(SENHA);
    cy.get('[data-test="login-button"]').click();
    cy.url().should("include", "/inventory.html");

    // Mapeia todos os itens da página inicial
    cy.get('[data-test="inventory-item"]')
      .should("have.length.greaterThan", 0)
      .then(($itens) => {
        const todos: Produto[] = $itens.toArray().map((item) => {
          const nome = Cypress.$(item)
            .find('[data-test="inventory-item-name"]')
            .text()
            .trim();
          const textoPreco = Cypress.$(item)
            .find('[data-test="inventory-item-price"]')
            .text();
          const preco = parseFloat(textoPreco.replace(/[^0-9.]/g, ""));
          return { nome, preco };
        });

        // Filtra apenas as ofertas (preço < $20.00)
        const ofertas = todos.filter((p) => p.preco < LIMITE_OFERTA);

        // Exibe no console (Cypress e terminal)
        const linhas = ofertas.map(
          (p) => `${p.nome} - $${p.preco.toFixed(2)}`
        );
        cy.log(`Produtos mapeados: ${todos.length}`);
        cy.log(`Ofertas abaixo de $20.00: ${ofertas.length}`);
        cy.task(
          "log",
          [
            `Produtos mapeados: ${todos.length}`,
            `Ofertas abaixo de $${LIMITE_OFERTA.toFixed(2)}:`,
            ...linhas.map((l) => `  - ${l}`),
          ].join("\n")
        );

        // Salva em arquivo
        cy.writeFile("cypress/results/ofertas-abaixo-20.json", {
          limite: LIMITE_OFERTA,
          total_mapeados: todos.length,
          ofertas,
        });

        // Asserções
        expect(todos, "itens mapeados").to.have.length.greaterThan(0);
        expect(ofertas, "ofertas encontradas").to.have.length.greaterThan(0);
        ofertas.forEach((p) => {
          expect(p.preco, `preço de "${p.nome}"`).to.be.lessThan(LIMITE_OFERTA);
        });
      });
  });
});
