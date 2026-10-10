/// <reference types="cypress" />

interface Produto {
  nome: string;
  preco: number;
}

describe("Demoblaze - laptop mais caro da categoria Laptops", () => {
  it("extrai nome e preço do laptop mais caro e valida que o preço é maior que $0", () => {
    // Observa a requisição que carrega os produtos da categoria
    cy.intercept("POST", "**/bycat").as("produtosPorCategoria");

    cy.visit("/");

    // Clica em "Laptops" no menu lateral esquerdo
    cy.contains("#itemc", "Laptops").click();

    // Aguarda o carregamento dos produtos
    cy.wait("@produtosPorCategoria", { timeout: 15000 });
    cy.get("#tbodyid .card", { timeout: 15000 }).should(
      "have.length.greaterThan",
      0
    );

    // Extrai nome e preço de cada laptop da página
    cy.get("#tbodyid .card").then(($cards) => {
      const produtos: Produto[] = $cards
        .toArray()
        .map((card) => {
          const nome = Cypress.$(card).find(".card-title a").text().trim();
          const textoPreco = Cypress.$(card).find(".card-block h5").text();
          const preco = parseFloat(textoPreco.replace(/[^0-9.]/g, ""));
          return { nome, preco };
        })
        .filter((p) => p.nome !== "" && !Number.isNaN(p.preco));

      expect(produtos, "laptops extraídos").to.have.length.greaterThan(0);

      // Identifica o mais caro
      const maisCaro = produtos.reduce((maior, atual) =>
        atual.preco > maior.preco ? atual : maior
      );

      cy.log(`Laptop mais caro: ${maisCaro.nome} - $${maisCaro.preco}`);
      cy.task("log", `Laptop mais caro: ${maisCaro.nome} - $${maisCaro.preco}`);

      // Asserção final
      expect(maisCaro.preco, "preço do laptop mais caro").to.be.greaterThan(0);
    });
  });
});
