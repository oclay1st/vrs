<script lang="ts">
import { Menu, Space } from "ant-design-vue";

export default {
    components: {
        'a-space': Space,
        'a-menu': Menu,
        'a-menu-item': Menu.Item,
        'a-menu-item-group': Menu.ItemGroup,
    }
}
</script>
<script lang="ts" setup>
import routesNames from "@/router/routesNames";
import { computed, ref } from "vue";
import { SwitcherOutlined, UserOutlined } from '@ant-design/icons-vue';
import { useRoute } from "vue-router";

const route = useRoute();

const selected = ref(["1"]);

const current = computed(() => {
    switch (route.name) {
        case "vehicleList":
            return ["1"]
        case "userList":
            return ["2"]
        default:
            return selected.value
    }
});

</script>

<template>
    <div>
        <a-space class="sidebar-image">
            <router-link :to="{ name: routesNames.home }">
                <img alt="" src="@/assets/logo.png">
                <span class="site-name">Vehicles Registration System</span>
            </router-link>
        </a-space>
        <a-menu v-model:selectedKeys="current" :default-selected-keys="['1']" class="admin-sidebar-menu" mode="inline"
            theme="dark">
            <a-menu-item-group key="g1" title="Inventory">
                <a-menu-item key="1">
                    <router-link :to="{ name: routesNames.vehicleList }">
                        <SwitcherOutlined />
                        <span>Vehicles</span>
                    </router-link>
                </a-menu-item>
            </a-menu-item-group>
            <a-menu-item-group key="g2" title="Security">
                <a-menu-item key="2">
                    <router-link :to="{ name: routesNames.userList }">
                        <UserOutlined />
                        <span>Users</span>
                    </router-link>
                </a-menu-item>
            </a-menu-item-group>
        </a-menu>
    </div>
</template>

<style>
.ant-menu.ant-menu-dark .ant-menu-item {
    border-left: 4px solid transparent;
}

.ant-menu.ant-menu-dark .ant-menu-item-selected {
    background-color: #343E55 !important;
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
    border-left: 4px solid coral;
}

.admin-sidebar-menu>li>div {
    font-size: 11px;
    color: #636979 !important;
    font-weight: 700;
}

.admin-sidebar-menu {
    background: #122538 !important;
}


.sidebar-image {
    max-width: 270px;
    width: 100%;
    background: #0f2133;
    height: 64px;
    padding-left: 25px;
    margin-bottom: 25px;
}

.sidebar-image img {
    width: 40px;
    height: auto;
    margin-right: 8px;
}

.sidebar-image .site-name {
    margin-top: 8px;
    color: white;
    width: 160px;
    font-size: 14px;
    color: #636979;
    font-weight: 700;
    /* font-weight: bold; */
    text-align: center;
    position: absolute;
}

.environment {
    font-size: 10px;
    font-style: italic;
    color: #FF7F50;
}
</style>
