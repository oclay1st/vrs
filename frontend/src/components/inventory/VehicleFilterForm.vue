<script lang="ts">
import { Form, Select, InputNumber } from "ant-design-vue";
import { computed } from "vue";

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
        'a-select': Select,
        'a-select-option': Select.Option,
        'a-input-number': InputNumber,
    }
}
</script>

<script lang="ts" setup>
import _ from "lodash";
import { type IVehicleListFilter } from "@/api/types";
import { reactive, type UnwrapRef, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useInventoryStore } from "@/stores/inventory";

interface VehicleFilterFormState {
    search: string
    vin?: string
    licensePlate?: string
    type?: string
    dieselInjectionPumpType?: string
    gasFuelType?: string
    batteryType?: string
}

interface Props {
    filters?: IVehicleListFilter
}

const inventoryStore = useInventoryStore()

const props = withDefaults(defineProps<Props>(), {
    filters: undefined
});

const router = useRouter();

const route = useRoute();

const formState: UnwrapRef<VehicleFilterFormState> = reactive({
    search: '',
});

function handleSubmit() {
    let search = formState.search && formState.search != '' ? formState.search.trim() : undefined;
    let page = route.query?.page ? route.query?.page : undefined;
    if (route.query?.search != search) {
        page = "1";
    }
    let licensePlate = formState.licensePlate && formState.licensePlate != '' ? formState.licensePlate.trim() : undefined;
    let type = formState.type && formState.type != '' ? formState.type.trim() : undefined;
    let dieselInjectionPumpType = formState.dieselInjectionPumpType && formState.dieselInjectionPumpType != '' ? formState.dieselInjectionPumpType.trim() : undefined;
    let gasFuelType = formState.gasFuelType && formState.gasFuelType != '' ? formState.gasFuelType.trim() : undefined;
    let batteryType = formState.batteryType && formState.batteryType != '' ? formState.batteryType.trim() : undefined;
    let newQuery = Object.assign({}, route.query, {
        search: search,
        page: page,
        vin: formState.vin,
        licensePlate: licensePlate,
        type: type,
        dieselInjectionPumpType: dieselInjectionPumpType,
        gasFuelType: gasFuelType,
        batteryType: batteryType
    });
    // If the new and current query object are equals, add or remove the size param
    if (_.isEqual(_.pickBy(newQuery), _.pickBy({ ...route.query }))) {
        newQuery = Object.assign({}, newQuery, { size: newQuery?.size ? undefined : "20" });
    }
    router.push({ query: newQuery });
}

const showDieselInfo = computed(() => formState.type === 'DIESEL');

const showGasInfo = computed(() => formState.type === 'GASOLINE');

const showElectricInfo = computed(() => formState.type === 'ELECTRIC');

watch(() => props.filters, () => {
    formState.search = props.filters?.vin as string
});

</script>

<template>
    <a-form :model="formState" class="custom-inline-form" layout="inline">
        <a-form-item name="vin">
            <a-input-number class="filter-item" v-model:value="formState.vin" placeholder="Identification Number" />
        </a-form-item>
        <a-form-item name="licensePlate">
            <a-input v-model:value="formState.licensePlate" allow-clear placeholder='License Plate'
                class="filter-item" />
        </a-form-item>
        <a-form-item name="type">
            <a-select v-model:value="formState.type" class="filter-item" allow-clear
                placeholder="Select the vehicle type" label="children" showSearch>
                <a-select-option v-for="vehicleType in inventoryStore.vehicleTypes" :key="vehicleType.value">
                    {{ vehicleType.name }}
                </a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item v-if="showDieselInfo" name="injectionPumpType">
            <a-select v-model:value="formState.dieselInjectionPumpType" class="filter-item" allow-clear
                placeholder="Select the pump type" label="children" showSearch>
                <a-select-option v-for="pumpType in inventoryStore.dieselInjectionPumpTypes" :key="pumpType.value">
                    {{ pumpType.name }}
                </a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item v-if="showGasInfo" name="gasFuelType">
            <a-select v-model:value="formState.gasFuelType" placeholder="Select the fuel type" class="filter-item"
                allow-clear label="children" showSearch>
                <a-select-option v-for="fuelType in inventoryStore.gasFuelTypes" :key="fuelType.value">
                    {{ fuelType.name }}
                </a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item v-if="showElectricInfo" name="batteryType">
            <a-select v-model:value="formState.batteryType" placeholder="Select the battery type" class="filter-item"
                allow-clear label="children" showSearch>
                <a-select-option v-for="batteryType in inventoryStore.batteryTypes" :key="batteryType.value">
                    {{ batteryType.name }}
                </a-select-option>
            </a-select>
        </a-form-item>

        <a-form-item>
            <a-button class="btn-search" type="primary" @click.prevent="handleSubmit">
                Search
            </a-button>
        </a-form-item>
    </a-form>
</template>

<style scoped>
.filter-item {
    width: 200px;
}
</style>
