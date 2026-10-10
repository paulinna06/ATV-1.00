import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    baseUrl: "https://www.saucedemo.com",
    specPattern: "cypress/e2e/**/*.cy.ts",
    supportFile: "cypress/support/e2e.ts",
    video: false,
    defaultCommandTimeout: 8000,
    setupNodeEvents(on) {
      on("task", {
        // Imprime mensagens no terminal ao rodar com `npm run cy:run`
        log(mensagem: string) {
          console.log(mensagem);
          return null;
        },
      });
    },
  },
});
