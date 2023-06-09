<script>
import axios from 'axios';
const base_url = "http://localhost:8080/api/v1"

export default {
    name: "PhotoComp",
    props: { photo: Object },
    data() {
        return {

        }
    },
}
</script>

<template>
    <li class="list-group-item">
        <h2>{{ photo.title }}</h2>
        <p>{{ photo.description }}</p>
    </li>
</template>

<style scoped></style>