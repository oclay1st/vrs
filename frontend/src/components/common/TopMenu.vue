<script lang="ts">
import { Menu } from "ant-design-vue";
import { delLocalStorageKey } from '@/config/lstorage';
import routesNames from '@/router/routesNames';
import { useRouter } from 'vue-router';

export default {
    components: {
        "a-menu": Menu,
        "a-sub-menu": Menu.SubMenu,
        'a-menu-item': Menu.Item
    }
}
</script>

<script lang="ts" setup>
import { LogoutOutlined } from '@ant-design/icons-vue';
import { computed, onMounted } from "vue";
import { useSecurityStore } from '@/stores/security';

const { currentUserProfile } = useSecurityStore()

const router = useRouter();

const firstCharacter = computed(() => (currentUserProfile.name ? currentUserProfile.name.charAt(0) : '').toUpperCase());

const logout = () => {
    delLocalStorageKey('accessToken');
    router.push({ name: routesNames.login })
};

</script>

<template>
    <div class="top-menu">
        <div class="top-menu-white">
            <a-menu class="right-menu" mode="horizontal" theme="light">
                <a-sub-menu>
                    <template #title>
                        <a-space align="baseline" class="submenu-title-wrapper">
                            <span class="hello-message">Hi, <strong>{{ currentUserProfile.name }}
                                    {{ currentUserProfile.lastName }}</strong></span>
                            <div class="admin-avatar"><span>{{ firstCharacter }}</span></div>
                        </a-space>
                    </template>
                    <a-menu-item key="logout" @click="logout">
                        <LogoutOutlined />
                        Logout
                    </a-menu-item>
                </a-sub-menu>
            </a-menu>
        </div>
    </div>

</template>


<style>
.top-menu {
    width: 100%;
    display: flex;
    background: #0f2133;
}

.top-menu-white {
    width: 100%;
    background: #ffffff;
    border-bottom: 1px solid #f0f1f2;
}

.collapsible-icon {
    margin-left: 30px;
    font-size: 18px;
    color: #009EF7;
}

.right-menu {
    float: right;
    background: transparent;
    min-width: 150px;
    line-height: 64px;
}

.admin-avatar span {
    padding: 10px 12px;
    background-color: #C9F7F5;
    color: #1BC5BD;
    text-align: center;
    border-radius: 6px;
    font-weight: bold;
    font-size: 16px;
}
</style>
