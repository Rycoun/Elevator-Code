import { createStore as _createStore } from 'vuex';


export function createStore() {
  return _createStore({
    state: {
      name: 'Head First Design Patterns',
      description: 'A brain friendly guide to building extensible and maintainable object-oriented software.',
      filter: 0,
      nextReviewId: 1005,
      reviews: [
        {
          id: 1000,
          reviewer: 'R Pérez',
          title: 'Approachable pattern guide',
          review:
            'I love the uncomplicated, informal narrative style. I highly recommend this text for anyone trying to understand Design Patterns in a super simple way.',
          rating: 4,
          favorited: false
        },
        {
          id: 1001,
          reviewer: 'Carmen',
          title: 'Pattern complexity gone!',
          review:
            'I struggled for years to understand patterns and how to implement the design and how them. This is by far the best book to learn design patterns. I would give this 10 stars if I could.',
          rating: 5,
          favorited: false
        },
        {
          id: 1002,
          reviewer: 'J. King',
          title: 'Not for me',
          review:
            'The content is thorough and well described. However, the format just doesn\'t work for me. I need something more traditional.',
          rating: 1,
          favorited: false
        },
        {
          id: 1003,
          reviewer: 'Safa H.',
          title: 'Good for beginners',
          review:
            'Good introduction to design patterns especially if you have never used them before or are relatively new to OO principles.',
          rating: 3,
          favorited: false
        },
        {
          id: 1004,
          reviewer: 'L Wang',
          title: 'Entertaining',
          review:
            'If you are new to design patterns I HIGHLY recommend this book. You might think it\'s not "serious enough" at first. But as you go through it things just stick. It makes learning enjoyable.',
          rating: 4,
          favorited: false
        }
      ],
    }
  });
}
