function flipCard(e) {
  console.log(`current Target: ${e.currentTarget}`);
  console.log(`target: ${e.target}`);

  this.classList.toggle("flip");
}

function deleteCard(e) {
  e.stopPropagation();
  const elementToDelete = e.target.closest('.cards__single');

  showDeleteDialog(elementToDelete);
}

function showDeleteDialog(elementToDelete) {
  const result = confirm('Are you sure you want to delete this?');

  if (result) {
    elementToDelete.remove();
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const cards = document.querySelectorAll(".cards__single");
  cards.forEach((card) => card.addEventListener("click", flipCard));

  const deleteButtons = document.querySelectorAll('.btn__delete');
  deleteButtons.forEach(btn => btn.addEventListener('click', deleteCard));
});


//You can see full tutorial here
//https://www.ricardomoreira.io/blog/2020-06-15-flip-cards-with-javascript