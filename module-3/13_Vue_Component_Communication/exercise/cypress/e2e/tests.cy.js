// https://docs.cypress.io/api/introduction/api.html

describe('Component Communication Exercise', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  describe('Step One Tests', () => {
    it('should show divs for all book data', () => {
      cy.get(".card").should('have.length', 4);
    });
  });

  describe('Step Two Tests', () => {
    it('should show title for all books', () => {
      cy.get(".book-title").should("have.length", 4);
      cy.get(".book-title").eq(0).contains("", "Kafka on the Shore");
      cy.get(".book-title").eq(1).contains("", "The Girl With All the Gifts");
      cy.get(".book-title").eq(2).contains("", "The Old Man and the Sea");
      cy.get(".book-title").eq(3).contains("", "Le Petit Prince");
    });
    it('should show author for all books', () => {
      cy.get(".book-author").should("have.length", 4);
      cy.get(".book-author").eq(0).contains("", "Haruki Murakami");
      cy.get(".book-author").eq(1).contains("", "M.R. Carey");
      cy.get(".book-author").eq(2).contains("", "Ernest Hemingway");
      cy.get(".book-author").eq(3).contains("", "Antoine de Saint-Exupéry");
    });
    it('should show cover image for all books', () => {
      cy.get(".book-image").should("have.length", 4);
      cy.get(".book-image").eq(0).should("have.attr", "src").and("include", "9781784877989");
      cy.get(".book-image").eq(1).should("have.attr", "src").and("include", "9780356500157");
      cy.get(".book-image").eq(2).should("have.attr", "src").and("include", "9780684830490");
      cy.get(".book-image").eq(3).should("have.attr", "src").and("include", "9783125971400");
    });
  });

  describe("Step Three Tests", () => {
    // Make sure there are 4 buttons to start, either "Mark Read" or "Mark Unread"
    it('mark read/unread buttons should exist', () => {
      cy.get(".mark-read, .mark-unread").should("exist");
      cy.get(".mark-read:visible, .mark-unread:visible")
        .should("have.length", 4);
    })

    // There should be two cards marked Read to start
    it('cards should have read class', () => {
      cy.get(".card.read").should("have.length", 2);
    })

    it('should mark book as read when "Mark Read" button is pressed', () => {
      // Create a query that finds all <div> elements with class "card" 
      // and with child button, class "mark-read". Save that query as "card".
      cy.get("div.card:has(> button.mark-read:visible)").as("card");

      // Run the query to get all cards with a mark-read button
      cy.get("@card").each(($card) => {
        // For each card with child "mark-read" <button> element
        cy.wrap($card)
          // Get the button
          .find('.mark-read')
          .then(($button) => {
            // Click on it
            if ($button.length > 0) {
              cy.wrap($button).click();
            }
          });
      });

      // Re-run the query - there shouldn't be any cards with "mark-read" buttons
      cy.get("@card").should("not.exist");

      // Now all the cards should have class "read" and should contain a button with class "mark-unread"
      cy.get("div.card.read:has(> button.mark-unread:visible)").should("have.length", 4);

    });

    it('should mark book as unread when "Mark Unread" button is pressed', () => {
      // Create a query that finds all <div> elements with class "card" 
      // and with child button, class "mark-unread". Save that query as "card".
      cy.get("div.card:has(> button.mark-unread:visible)").as("card");

      // Run the query to get all cards with a mark-unread button
      cy.get("@card").each(($card) => {
        // For each card with child "mark-unread" <button> element
        cy.wrap($card)
          // Get the button
          .find('.mark-unread')
          .then(($button) => {
            // Click on it
            if ($button.length > 0) {
              cy.wrap($button).click();
            }
          });
      });

      // Re-run the query - there shouldn't be any cards with "mark-unread" buttons
      cy.get("@card").should("not.exist");

      // Now all the cards should NOT have class "read" and should contain a button with class "mark-read"
      cy.get("div.card:not(.read):has(> button.mark-read:visible)").should("have.length", 4);
    });
  });

  describe("Step Four Tests", () => {
    it('new book form should exist', () => {
      cy.get("form.new-book-form").should("exist");
      cy.get("input.title-input").should("exist");
      cy.get("input.author-input").should("exist");
      cy.get("input.isbn-input").should("exist");
    });

    it('should add a new card when a book is added in the form', () => {
      cy.get(".title-input").type("TEST BOOK");
      cy.get(".author-input").type("TEST AUTHOR");
      cy.get(".isbn-input").type("1111111111{enter}");

      cy.contains(".card", "TEST BOOK").as("card");

      cy.get("@card").find(".book-title", "TEST BOOK");
      cy.get("@card").find(".book-author", "TEST AUTHOR");
      cy.get("@card")
        .find(".book-image")
        .should("have.attr", "src")
        .and("include", "1111111111");
      cy.get("@card")
        .find("button")
        .should("have.class", "mark-read");
    });
  });
});
