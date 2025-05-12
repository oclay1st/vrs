<script lang="ts">
import { Badge, Popconfirm, Table, Tooltip } from "ant-design-vue";
import { useInventoryStore } from "@/stores/inventory";
import type { ColumnsType } from "ant-design-vue/es/table";

export default {
    components: {
        'a-table': Table,
        'a-popconfirm': Popconfirm,
        'a-tooltip': Tooltip,
        'a-badge': Badge,
    }
}
</script>

<script lang="ts" setup>
import { type IVehicle } from "@/models/inventory";
import { type IPage } from "@/models/common";
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { EyeOutlined, DeleteOutlined } from '@ant-design/icons-vue';
import { useVehicle } from '@/composables/inventory';
import VehicleView from '@/components/inventory/VehicleView.vue';

interface Props {
    page: IPage<IVehicle>,
    loading: boolean
}

const emit = defineEmits(['deleteSuccess']);

const props = withDefaults(defineProps<Props>(), {
    page: undefined,
    loading: false
});

const inventoryStore = useInventoryStore();

const route = useRoute();

const router = useRouter();

const data = ref<any>([]);

const pagination = ref<any>({ current: 1, pageSize: 0 })

const { deleteVehicle } = useVehicle()

const columns = [
    {
        title: 'VIN',
        key: 'vin',
        dataIndex: 'vin',
        align: 'center',
        width: 100
    },
    {
        title: 'License Plate',
        key: 'licensePlate',
        dataIndex: 'licensePlate'
    },
    {
        title: 'Type',
        key: 'type',
        dataIndex: 'type',
        customRender: (value: any) => {
            return inventoryStore.vehicleTypes.find((type) => type.value == value.text)?.name
        }
    },
    {
        title: 'Registration Date',
        key: 'createdAt',
        align: 'center',
        width: 200,
        dataIndex: 'createdAt',
        customRender: (value: any) => { return new Date(value.text).toLocaleString() }
    },
    {
        title: 'Actions',
        key: 'action',
        align: 'center',
        width: 100
    }
] as ColumnsType<any>;

const isVehicleModalVisible = ref<boolean>(false);

const currentVIN = ref<number>();

function handleTableChange(pagination: any) {
    const newQuery = JSON.parse(JSON.stringify(route.query));
    newQuery['page'] = pagination.current;
    router.push({ query: newQuery });
}

async function handleDeleteVehicle(vin: number) {
    await deleteVehicle(vin)
        .then(() => emit('deleteSuccess'))
}

watch(() => props.page, (newValue: IPage<IVehicle>) => {
    data.value = newValue.items
    pagination.value = {
        showSizeChanger: false,
        hideOnSinglePage: true,
        total: newValue.totalElements,
        pageSize: route.query?.size ? Number.parseFloat(route.query?.size as string) : 20,
        current: newValue.page
    };
});

function showVehicleModal(record: any) {
    currentVIN.value = record.vin;
    isVehicleModalVisible.value = true;
}

</script>

<template>
    <div>
        <a-table ref="vehicleTable" :columns="columns" :data-source="data" :loading="props.loading"
            :pagination="pagination" :row-key="(record: any) => record.id" @change="handleTableChange">
            <template #bodyCell="{ column, text, record }">
                <template v-if="column.key === 'action'">
                    <div class="vehicle-actions">
                        <a-tooltip title="Show vehicle">
                            <a @click="showVehicleModal(record)">
                                <EyeOutlined />
                            </a>
                        </a-tooltip>
                        <a-tooltip title="Archive">
                            <a-popconfirm placement="left" @confirm="handleDeleteVehicle(record.vin)"
                                title="Are you sure archive this vehicle?">
                                <a>
                                    <DeleteOutlined style="color:red" />
                                </a>
                            </a-popconfirm>
                        </a-tooltip>
                    </div>
                </template>
            </template>
        </a-table>
        <VehicleView v-model:visible="isVehicleModalVisible" :vin="currentVIN" />
    </div>
</template>

<style scoped>
.vehicle-actions a {
    margin-left: 8px;
    margin-right: 8px;
}
</style>
