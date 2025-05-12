<script setup lang="ts">
import { onBeforeRouteUpdate, useRoute, type RouteLocation } from "vue-router";
import { onMounted, ref } from "vue";
import { useVehicle } from '@/composables/inventory';
import { type IVehicleListFilter } from '@/api/types';
import VehicleFilterForm from '@/components/inventory/VehicleFilterForm.vue';
import VehicleTable from '@/components/inventory/VehicleTable.vue';
import RegisterVehicleView from '@/components/inventory/RegisterVehicleView.vue';
import { BlockOutlined } from '@ant-design/icons-vue'
import { useInventoryStore } from '@/stores/inventory';

const route = useRoute();

const { vehiclePage, loadingVehicles, loadVehiclePage } = useVehicle();

const inventoryStore = useInventoryStore();

const filters = ref<IVehicleListFilter>({});

const isRegisterVehicleModalVisible = ref<boolean>(false);

const getVehicleFilters = (route: RouteLocation) => {
    return {
        page: route.query.page ? Number.parseFloat(route.query?.page as string) : 1,
        size: route.query.size ? Number.parseFloat(route.query?.size as string) : 20,
        vin: route.query.vin ? route.query?.vin : undefined,
        licensePlate: route.query.licensePlate ? route.query?.licensePlate : undefined,
        type: route.query.type ? route.query?.type : undefined,
        dieselInjectionPumpType: route.query.dieselInjectionPumpType ? route.query?.dieselInjectionPumpType : undefined,
        gasFuelTypes: route.query.gasFuelTypes ? route.query?.gasFuelTypes : undefined,
        batteryType: route.query.batteryType ? route.query?.batteryType : undefined,
    } as IVehicleListFilter
};

async function loadVehicleList() {
    const params = getVehicleFilters(route);
    filters.value = { ...params };
    await loadVehiclePage(params);
}

function handleClickNewVehicle() {
    isRegisterVehicleModalVisible.value = true;
}

onMounted(() => {
    loadVehicleList()
    inventoryStore.listAllVehicleTypes()
    inventoryStore.listAllDieselInjectionPumpTypes()
    inventoryStore.listAllGasFuelTypes()
    inventoryStore.listAllBatteryTypes()
});

onBeforeRouteUpdate(async (to: RouteLocation, from: RouteLocation, next: Function) => {
    const params = getVehicleFilters(to);
    filters.value = { ...params };
    await loadVehiclePage(params);
    next();
})
</script>

<template>
    <div>
        <h2 class="header-title">
            <BlockOutlined />
            Vehicles
        </h2>
        <a-row :gutter="15">
            <a-col :sm="{ span: 21 }" :xs="{ span: 21 }">
                <VehicleFilterForm :filters="filters" class="vehicles-filter-form" />
            </a-col>
            <a-col :sm="{ span: 3 }" :xs="{ span: 3 }">
                <a-button class="new-vehicle-button" type="primary" @click="handleClickNewVehicle">
                    <template #icon>
                        <BlockOutlined />
                    </template>
                    Register Vehicle
                </a-button>
            </a-col>
        </a-row>
        <VehicleTable :loading="loadingVehicles" :page="vehiclePage" @deleteSuccess="loadVehicleList"
            @updateSuccess="loadVehicleList" />
        <RegisterVehicleView v-model:visible="isRegisterVehicleModalVisible" @createSuccess="loadVehicleList" />
    </div>
</template>

<style scoped>
.vehicles-filter-form {
    margin-bottom: 12px;
}

.new-vehicle-button {
    float: right;
    padding-left: 11px;
    padding-right: 11px;
}
</style>
