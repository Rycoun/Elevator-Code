document.addEventListener('DOMContentLoaded', () => {

    document.querySelector('button').addEventListener('click', () => {
        changeBackgroundColor();
    });


    document.querySelector('td').addEventListener('click', (event) => {

        console.log(event.target.tagName);

    });

});

function changeBackgroundColor() {
    // document.querySelector('div').classList.toggle('changed-color');

    const tableRowsList = document.querySelectorAll('tr');

    tableRowsList.forEach(row => {
        row.classList.toggle('changed-color');
    });

}