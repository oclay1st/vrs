<script lang="ts">
import { Form, InputNumber, Select, Spin, Checkbox } from "ant-design-vue";
import type { RuleObject } from 'ant-design-vue/es/form';
import { onMounted } from 'vue';

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
        'a-input-number': InputNumber,
        'a-select': Select,
        'a-select-option': Select.Option,
        'a-spin': Spin,
        'a-checkbox': Checkbox,
    },
}

</script>
<script setup lang="ts">
import { ref, computed } from 'vue';
import { required } from '@/utils';
import { useInventoryStore } from '@/stores/inventory';
import type { IVehicleDTO } from '@/api/types';

export interface Props {
    loading?: boolean,
    vehicle: IVehicleDTO,
    currentId?: string
}

const props = withDefaults(defineProps<Props>(), {
    loading: false,
    vehicle: undefined,
    currentId: undefined
});

const inventoryStore = useInventoryStore();

const vehicleForm = ref();

defineExpose({ "vehicleForm": vehicleForm });

const rules = {
    vin: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    licensePlate: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    type: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject
};

const showDieselInfo = computed(() => props.vehicle.type === 'DIESEL');

const showGasInfo = computed(() => props.vehicle.type === 'GASOLINE');

const showElectricInfo = computed(() => props.vehicle.type === 'ELECTRIC');


onMounted(() => {
    console.log('hello')
})

</script>


<template>
    <a-spin :spinning="props.loading">
        <a-form ref="vehicleForm" :model="props.vehicle" :rules="rules" layout="vertical">
            <a-form-item label="Identification Number (VIN)" class="form-label-color-dark" name="vin">
                <a-input-number v-model:value="props.vehicle.vin" placeholder="Identification Number"
                    class="expand-item" />
            </a-form-item>
            <a-form-item label="License Plate" class="form-label-color-dark" name="licensePlate">
                <a-input v-model:value="props.vehicle.licensePlate" />
            </a-form-item>
            <a-form-item label="Type" class="form-label-color-dark" name="type">
                <a-select v-model:value="props.vehicle.type" placeholder="Select the vehicle type" label="children"
                    showSearch>
                    <a-select-option v-for="vehicleType in inventoryStore.vehicleTypes" :key="vehicleType.value">
                        {{ vehicleType.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-if="showDieselInfo" label="Injection Pump Type" class="form-label-color-dark"
                name="injectionPumpType">
                <a-select v-model:value="props.vehicle.dieselInjectionPumpType" placeholder="Select the pump type"
                    label="children" showSearch>
                    <a-select-option v-for="pumpType in inventoryStore.dieselInjectionPumpTypes" :key="pumpType.value">
                        {{ pumpType.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-if="showGasInfo" label="Gas Fuel Type" class="form-label-color-dark" name="gasFuelTypes">
                <a-select v-model:value="props.vehicle.gasFuelTypes" mode="tags" placeholder="Select the fuel type"
                    label="children" showSearch>
                    <a-select-option v-for="fuelType in inventoryStore.gasFuelTypes" :key="fuelType.value">
                        {{ fuelType.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-if="showElectricInfo" label="Battery Type" class="form-label-color-dark" name="batteryType">
                <a-select v-model:value="props.vehicle.batteryType" placeholder="Select the battery type"
                    label="children" showSearch>
                    <a-select-option v-for="batteryType in inventoryStore.batteryTypes" :key="batteryType.value">
                        {{ batteryType.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-if="showElectricInfo" label="Battery Voltage" class="form-label-color-dark"
                name="batteryVoltage">
                <a-input-number v-model:value="props.vehicle.batteryVoltage" placeholder="Voltage"
                    class="expand-item" />
            </a-form-item>
            <a-form-item v-if="showElectricInfo" label="Battery Amperage" class="form-label-color-dark"
                name="batteryAmperage">
                <a-input-number v-model:value="props.vehicle.batteryAmperage" placeholder="Amperage"
                    class="expand-item" />
            </a-form-item>
            <a-form-item v-if="showElectricInfo" class="form-label-color-dark" name="convertible">
                <a-checkbox v-model:checked="props.vehicle.convertible">Convertible</a-checkbox>
            </a-form-item>
            <a-form-item v-if="showElectricInfo && props.vehicle.convertible" label="New License Plate"
                class="form-label-color-dark" name="convertibleLicensePlate">
                <a-input v-model:value="props.vehicle.convertibleLicensePlate" />
            </a-form-item>
            <a-form-item v-if="showElectricInfo && props.vehicle.convertible" label="New Gas Fuel Type"
                class="form-label-color-dark" name="convertibleGasFuelTypes">
                <a-select v-model:value="props.vehicle.convertibleGasFuelTypes" mode="tags"
                    placeholder="Select the fuel type" label="children" showSearch>
                    <a-select-option v-for="fuelType in inventoryStore.gasFuelTypes" :key="fuelType.value">
                        {{ fuelType.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
        </a-form>
    </a-spin>
</template>

<style scoped>
.expand-item {
    width: 100%;
}
</style>
