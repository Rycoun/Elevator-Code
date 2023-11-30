import axios from 'axios';

const externalApiInstance = axios.create({
  baseURL: 'https://externalApi.com'
});


export default {

  getBoards() {
    return axios.get('/boards');
  },

  getBoard(boardId) {
    return axios.get(`/boards/${boardId}`)
  },

  getCard(cardId) {
    return axios.get(`/cards/${cardId}`)
  },

  externalApiCall() {
    return externalApiInstance.get('/blah'); // https://externalApi.com/blah GET
  }
  
}


