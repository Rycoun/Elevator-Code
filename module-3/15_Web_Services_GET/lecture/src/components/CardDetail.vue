<template>
  <div>
    <h1>Card Detail</h1>
  </div>
</template>

<script>
import CommentsList '@'

export default {
  props: ['card'],

  componets: {
    CommentsList
  }
};
</script>
