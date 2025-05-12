<script setup lang="ts">

import { useRouter } from "vue-router";
import { ref } from "vue";


const router = useRouter();

const preloading = ref<boolean>(true);

// const { start, stop } = useProgress();

router.beforeEach((to, from, next: Function) => {
    // start();
    next();
});

router.afterEach((to, from) => {
    preloading.value = false;
    // stop();
});

</script>

<template>
    <router-view />
</template>

<style lang="css">
#app {
    height: 100%;
    width: 100%;
}

.master-container {
    min-height: 600px;
}

.preloader-container {
    position: absolute;
    width: 100vw;
    height: 100vh;
    background-color: #f1f1f1;
    z-index: 1001;
}

.preloader {
    width: 180px;
    height: auto;
    display: block;
    margin: calc((100vh / 2) - (180px / 2)) auto;
}

.preloader {
    animation: waveIconPulse 1.83s .4s infinite;
    animation-duration: 1.83s;
    animation-timing-function: ease;
    animation-delay: 0.4s;
    animation-iteration-count: infinite;
    animation-direction: normal;
    animation-fill-mode: none;
    animation-play-state: running;
    animation-name: waveIconPulse;
}

@keyframes waveIconPulse {
    0% {
        transform: scaleX(1);
    }

    30% {
        transform: scale3d(1.1, 1.1, 1.1);
    }

    60% {
        transform: scale3d(1.1, 1.1, 1.1);
    }

    100% {
        transform: scaleX(1);
    }
}
</style>
