<template>
  <div class="topic" v-for="topic in topics" v-bind:key="topic.id">
    <router-link v-bind:to="{ name: 'TopicDetailsView', params: { topicId: topic.id } }">
      {{ topic.title }}  
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    topics: {
      type: Array,
      required: true
    }
  },
};
</script>

<style>
.topic {
  border-bottom: 1px solid #f2f2f2;
}
.topic:last-child {
  border-bottom: 0px;
}
.topic a {
  display: block;
  padding: 10px 20px;
}
.topic a:link, .topic a:visited {
  color: black;
  text-decoration: none;
}
.topic a:hover {
  background-color: #f2f2f2;
  cursor: pointer;
}
</style>
