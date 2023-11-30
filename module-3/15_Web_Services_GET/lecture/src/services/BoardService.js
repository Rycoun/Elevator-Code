import axios from 'axios';

export default {

  getBoards() {
    return axios.get('/boards');
  },

  getBoard(boardId) {
    return axios.get(`/boards/${boardId}`)
  },

  getCard(cardId) {
    return axios.get(`/cards/${cardId}`)
  }
  
}
