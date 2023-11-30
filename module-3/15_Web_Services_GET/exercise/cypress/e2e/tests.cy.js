// https://docs.cypress.io/api/introduction/api.html

describe('Web API (GET) Exercises', () => {

  it('Step One Tests: Home should display topic list', () => {
    cy.visit('/');
    cy.get('.topic').should('have.length', 20);
    cy.visit('/');
    cy.contains('div.topic a', 'Introduction to Programming');
  });

  it('Step Two Tests: Topic Details is populated', () => {
    cy.visit('/');
    cy.get('div.topic a').first().click();
    cy.contains('header h1', 'Introduction to Programming');
    cy.get('.message').should('have.length', 4);
  });

  it('Step Three Tests: Message Details is populated', () => {
    cy.visit('/');
    cy.get('div.topic a').first().click();
    cy.get('.message').first().click();
    cy.contains('header h1', 'Getting Started in Programming');
  });
});
