import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    baseUrl: "https://www.demoblaze.com",
    specPattern: "cypress/e2e/**/*.cy.ts",
    supportFile: "cypress/support/e2e.ts",
    video: false,
    defaultCommandTimeout: 10000,
    setupNodeEvents(on) {
      on("task", {
        log(mensagem: string) {
          console.log(mensagem);
          return null;
        },
      });
    },
  },
});
