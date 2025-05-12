<script lang="ts">
import { Table, Tooltip } from "ant-design-vue";
import type { ColumnsType } from "ant-design-vue/es/table";

export default {
    components: {
        'a-table': Table,
        'a-tooltip': Tooltip,
    }
}
</script>

<script lang="ts" setup>
import { type IUser } from "@/models/security";
import { type IPage } from "@/models/common";
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";

interface Props {
    page: IPage<IUser>,
    loading: boolean
}

const props = withDefaults(defineProps<Props>(), {
    page: undefined,
    loading: false
});

const route = useRoute();

const router = useRouter();

const data = ref<any>([]);

const pagination = ref<any>({ current: 1, pageSize: 0 })

const columns = [
    {
        title: 'id',
        key: 'id',
        dataIndex: 'id',
        align: 'center',
        width: 100
    },
    {
        title: 'Name',
        key: 'name',
        dataIndex: 'name'
    },
    {
        title: 'Last Name',
        key: 'lastName',
        dataIndex: 'lastName',
    },
    {
        title: 'Username',
        key: 'username',
        align: 'center',
        width: 200,
        dataIndex: 'username',
    }
] as ColumnsType<any>;

function handleTableChange(pagination: any) {
    const newQuery = JSON.parse(JSON.stringify(route.query));
    newQuery['page'] = pagination.current;
    router.push({ query: newQuery });
}

watch(() => props.page, (newValue: IPage<IUser>) => {
    data.value = newValue.items
    pagination.value = {
        showSizeChanger: false,
        hideOnSinglePage: true,
        total: newValue.totalElements,
        pageSize: route.query?.size ? Number.parseFloat(route.query?.size as string) : 20,
        current: newValue.page
    };
});

</script>

<template>
    <div>
        <a-table ref="userTable" :columns="columns" :data-source="data" :loading="props.loading"
            :pagination="pagination" :row-key="(record: any) => record.id" @change="handleTableChange">
        </a-table>
    </div>
</template>

<style scoped></style>
