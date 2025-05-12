<script setup lang="ts">
import { onBeforeRouteUpdate, useRoute, type RouteLocation } from "vue-router";
import { onMounted, ref } from "vue";
import { useUser } from '@/composables/security';
import { type IUserListFilter } from '@/api/types';
import UserFilterForm from '@/components/security/UserFilterForm.vue';
import UserTable from '@/components/security/UserTable.vue';
import { UserOutlined } from '@ant-design/icons-vue'

const route = useRoute();

const { userPage, loadingUsers, loadUserPage } = useUser();

const filters = ref<IUserListFilter>({});

const getUserFilters = (route: RouteLocation) => {
    return {
        page: route.query.page ? Number.parseFloat(route.query?.page as string) : 1,
        size: route.query.size ? Number.parseFloat(route.query?.size as string) : 20,
        search: route.query.search ? route.query?.search : undefined,
    } as IUserListFilter
};

async function loadUserList() {
    const params = getUserFilters(route);
    filters.value = { ...params };
    await loadUserPage(params);
}


onMounted(() => {
    loadUserList()
});

onBeforeRouteUpdate(async (to: RouteLocation, from: RouteLocation, next: Function) => {
    const params = getUserFilters(to);
    filters.value = { ...params };
    await loadUserPage(params);
    next();
})
</script>

<template>
    <div>
        <h2 class="header-title">
            <UserOutlined />
            Users
        </h2>
        <a-row :gutter="15">
            <a-col :sm="{ span: 21 }" :xs="{ span: 21 }">
                <UserFilterForm :filters="filters" class="user-filter-form" />
            </a-col>
        </a-row>
        <UserTable :loading="loadingUsers" :page="userPage" />
    </div>
</template>

<style scoped>
.user-filter-form {
    margin-bottom: 12px;
}
</style>
