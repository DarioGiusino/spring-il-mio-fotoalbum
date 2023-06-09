<script>
import axios from 'axios';
import PhotoComp from './components/PhotoComp.vue';
const base_url = "http://localhost:8080/api/v1/photos"

export default {
  name: "App",
  components: { PhotoComp },
  data() {
    return {
      photos: null,
      searchTerm: "",
      email: "",
      message: ""
    }
  },

  methods: {

    // fetch photos from java be
    fetchPhotos() {
      axios.get(base_url).then(res => {
        // console.log(res.data);
        this.photos = res.data
      }).catch(e => {
        console.log(e);
      })
    },

    // filter photos
    filterPhoto(searchTitle) {
      axios.get(base_url + "/filter", { params: { title: searchTitle } }).then(res => {
        // console.log(res.data);
        this.photos = res.data
      }).catch(e => {
        console.log(e);
      })
    },

    sendMessage(emailForm, messageFrom) {

      const json = JSON.stringify({ email: emailForm, message: messageFrom })

      const customConfig = {
        headers: {
          'Content-Type': 'application/json'
        }
      };

      axios.post("http://localhost:8080/api/v1/contact/store", json, customConfig)
        .then(res => {
          console.log(res.data);
        }).catch(e => { console.log(e); })

      this.email = "";
      this.message = "";
    }

  },

  mounted() {
    this.fetchPhotos()
  }
}
</script>

<template>
  <!-- # header -->
  <header>
    <nav class="navbar bg-body-tertiary">
      <div class="container-fluid">
        <span class="navbar-brand">Photo Album</span>
        <a href="http://localhost:8080" class="btn btn-outline-success">Login</a>
      </div>
    </nav>
  </header>

  <!-- # main -->
  <main class="container">
    <!-- title -->
    <h1 class="text-center my-5">Photo Album!!</h1>

    <!-- filter -->
    <div class="input-group mb-3">
      <input @keyup.enter="filterPhoto(searchTerm)" type="text" class="form-control" placeholder="Input photo title"
        v-model="searchTerm">
      <button @click="filterPhoto(searchTerm)" class="btn btn-outline-secondary" type="button"
        id="button-addon2">Search</button>
    </div>

    <!-- photos list -->
    <ul class="list-group list-group-flush">
      <PhotoComp v-for="photo in photos" :photo="photo" :key="photo.id" />
    </ul>

    <!-- contact form -->
    <section>
      <!-- section title -->
      <h3 class="text-center my-3">Contattaci!</h3>
      <!-- email form -->
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" v-model="email">
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
      </div>
      <!-- textarea form -->
      <div class="form-floating mb-3">
        <textarea class="form-control" id="floatingTextarea2" style="height: 100px" v-model="message"></textarea>
        <label for="floatingTextarea2">Message</label>
      </div>
      <button @click="sendMessage(email, message)" class="btn btn-primary">Submit</button>
    </section>
  </main>
</template>

<style scoped></style>
