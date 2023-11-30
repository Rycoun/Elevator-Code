const player1 = {
    id: 1,
    char: 'x',
    iconClass: 'fa-times'
};

const player2 = {
    id: 2,
    char: 'o',
    iconClass: 'fa-circle'
};

let currentPlayer = player1;

const GAME_RESULT_X_WIN = 1;
const GAME_RESULT_O_WIN = 2;
const GAME_RESULT_TIE = 3;
const gameResults = {
    xWins: 0,
    oWins: 0,
    ties: 0
};


function switchPlayer() {
    // TODO: Step 0

    // Change the player in JS
    if (currentPlayer == player1) {
        currentPlayer == player2;
    } else {
        currentPlayer = player1;
    }
    
    // CHange the player in the DOM

    document.getElementById('current-player').innerText = currentPlayer.char.toLocaleUpperCase();

}

/**
 * Place the current player's icon within the tile.
 * 
 * @param {Element} tile - the tile to record the move within
 */
function recordMove(tile) {
    // TODO: Step 1
    // 1. Find the icon inside of the tile (i element)
    const iconElement = tile.querySelector('i');
    // 2. Update the class of the icon to be the player's icon class
    iconElement.classList.add(currentPlayer.iconClass);
}

/**
 * Returns true if there is a tie. A tie occurs when the entire
 * board is full.
 * 
 * @returns {boolean} true if there is a tie, false otherwise
 */
function isATie() {
    // TODO: Step 2
    // 1. Find all icons
    const allIconElements = document.querySelectorAll('#game-board i');

    // 2. For each icon, confirm if they have fa-times or fa-circle. 
    // If every one does, return true
    let ifATie = true;

    allIconElements.forEach(iconElement => {
        if (!(iconElement.classList.contains(player1.iconClass) || iconElement.classList(player2.iconClass))) {

        }
    })

    return true;
}

/**
 * Record a win by increasing the number of ties on the UI
 * and saving the result to the server.
 */
 function recordTie() {
    // TODO: Step 3

    // Increase ties in the gameResults object
    gameResults.ties++
    // Update the UI
    document.querySelector('#score-ties . score-text')
    // Save the result to the server

}

/**
 * Returns true if the current player has won. A win can
 * be three in a row horizontally, vertically, or diagonally.
 * 
 * @returns {boolean} true if the current player has won, false otherwise
 */
 function isAWin() {
    if (Array.from(document.querySelectorAll('.top-row i')).every(iconElement => iconElement.classList.contains(currentPlayer.iconClass)))
    return true;
 }
    if (Array.from(document.querySelectorAll('.bottom-row i')).every(iconElement => iconElement.classList.contains(currentPlayer.iconClass)))
    return true;
 }
     if (Array.from(document.querySelectorAll('.top-row i')).every(iconElement => iconElement.classList.contains(currentPlayer.iconClass)))
    return true;
 }

    // TODO: Step 4
    return false;
}

/**
 * Record a win by increasing the current player's score on the UI
 * and saving the result to the server.
 */
 function recordWin() {
    // TODO: Step 5

    // find out which player won and record the game result for them

    // save the result to the server

    // update the UI
}


/**
 * Remove all click event handlers from tiles.
 */
function removeTileClickListeners() {
    // TODO: Step 6
}

function onTileClicked(e) {
    const tileClicked = e.currentTarget;
    recordMove(tileClicked);
    tileClicked.removeEventListener('click', onTileClicked); // don't respond to future click events for this tile

    if (isAWin()) {
        recordWin();
        removeTileClickListeners();
        return;
    }

    if (isATie()) {
        recordTie();
        return;
    }

    switchPlayer();
}

function updateGameResults() {
    const scoreTiesElement = document.querySelector('#score-ties .score-text');
    const xWinsElement = document.querySelector('#score-player-1 .score-text');
    const oWinsElement = document.querySelector('#score-player-2 .score-text');

    scoreTiesElement.innerText = gameResults.ties;
    xWinsElement.innerText = gameResults.xWins;
    oWinsElement.innerText = gameResults.oWins;
}

/**
 * Remove all player icons from the board.
 */
function removeAllIcons() {
    // TODO: Step 7
}

function addTileClickListeners() {
    const tiles = document.querySelectorAll('.tile');

    tiles.forEach(t => {
        t.addEventListener('click', onTileClicked);
    });
}

function onPlayAgainClicked() {
    removeAllIcons();
    switchPlayer();
    removeTileClickListeners();
    addTileClickListeners();
}

function loadExistingGames() {
    fetch('http://localhost:8080/games')
        .then(res => res.json())
        .then(savedGameResult => {

            savedGameResult.forEach(result => {
                switch(result.gameResult) {
                    case GAME_RESULT_X_WIN:
                        gameResults.xWins++;
                        break;
                    case GAME_RESULT_O_WIN:
                        gameResults.oWins++;
                        break;
                    case GAME_RESULT_TIE:
                        gameResults.ties++;
                        break;
                }
            });

            updateGameResults();
        });
}

function saveGame(gameResult) {
    const metaData = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({ gameResult })
    };

    fetch('http://localhost:8080/games', metaData);
}

document.addEventListener('DOMContentLoaded', () => {
    loadExistingGames();

    addTileClickListeners();

    // TODO: Step 8 - Add event listener to Play Again Button
});