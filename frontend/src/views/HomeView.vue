<script lang="ts" setup>
import SidebarMenu from '@/components/common/SidebarMenu.vue';
import TopMenu from '@/components/common/TopMenu.vue';
import { onMounted } from "vue";
import { message } from "ant-design-vue";

onMounted(() => message.info(`Welcome to the Vehicle Registration System`));

</script>

<template>
    <div class="home">
        <a-layout id="app-sidebar">
            <a-layout-sider class="app-layout-sider" width="270">
                <SidebarMenu />
            </a-layout-sider>
            <a-layout>
                <a-layout-header class="top-menu-header">
                    <TopMenu />
                </a-layout-header>
                <a-layout class="app-layout-cover">
                    <a-layout-content class="app-layout-content">
                        <router-view />
                    </a-layout-content>
                </a-layout>
            </a-layout>
        </a-layout>
    </div>
</template>

<style>
#app-sidebar {
    height: 100vh;
}

.top-menu-header {
    padding: 0 !important;
    display: flex;
}

.app-layout-content {
    padding: 0 24px;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: 0 0 30px 0 rgba(82, 63, 105, 0.05);
    max-width: 1540px;
    width: 100%;
    margin: 0 auto;
    min-height: auto;
}

.app-layout-cover {
    padding: 30px 25px;
    background-color: #EEF0F8;
    overflow-x: hidden
}

.app-layout-sider {
    background: #122538;
}
</style>
