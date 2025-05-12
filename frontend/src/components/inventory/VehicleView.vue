<script lang="ts">
import { Modal, Descriptions } from "ant-design-vue";
import { useInventoryStore } from "@/stores/inventory";

export default {
    components: {
        'a-modal': Modal,
    },
}
</script>

<script lang="ts" setup>
import { computed, watch } from "vue";
import { useVehicle } from "@/composables/inventory";

interface Props {
    visible: boolean
    vin?: number
}

const props = withDefaults(defineProps<Props>(), {
    visible: false
});

const emit = defineEmits(['update:visible', 'cancel']);

const { loadingVehicleDetails, loadVehicleDetails, vehicleDetails } = useVehicle();

const visibleModal = computed({
    get: () => props.visible,
    set: (value) => emit('update:visible', value)
});


const inventoryStore = useInventoryStore();

const resetAndClose = () => {
    visibleModal.value = false;
};

const handleCancel = () => {
    resetAndClose();
    emit('cancel');
};

watch(visibleModal, (newValue: boolean) => {
    if (newValue) {
        loadVehicleDetails(props.vin as number)
    }
})

const vehicleType = computed(() => inventoryStore.vehicleTypes.find((type) => type.value == vehicleDetails.value.type)?.name)

const dieselInjectionPumpType = computed(() => inventoryStore.dieselInjectionPumpTypes.find((type) => type.value == vehicleDetails.value.dieselInjectionPumpType)?.name)

const batteryType = computed(() => inventoryStore.batteryTypes.find((type) => type.value == vehicleDetails.value.batteryType)?.name)

const gasFuelTypes = computed(() => inventoryStore.gasFuelTypes.filter((type) => vehicleDetails.value.gasFuelTypes?.includes(type.value)).map((type) => type.name).join(', '))

const convertibleGasFuelTypes = computed(() => inventoryStore.gasFuelTypes.filter((type) => vehicleDetails.value.convertibleGasFuelTypes?.includes(type.value)).map((type) => type.name).join(', '))

</script>

<template>
    <a-modal title="Vehicle Info" v-model:open="visibleModal" :confirm-loading="loadingVehicleDetails"
        :destroyOnClose="true" :maskClosable="false" centered @cancel="handleCancel">
        <div>
            <div class="vehicle-attribute">
                <span>Identification Number:</span>
                <span>{{ vehicleDetails.vin }}</span>
            </div>
            <div class="vehicle-attribute">
                <span>License Plate:</span>
                <span>{{ vehicleDetails.licensePlate }}</span>
            </div>
            <div class="vehicle-attribute">
                <span>Type:</span>
                <span>{{ vehicleType }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'DIESEL'">
                <span>Injection Pump Type:</span>
                <span>{{ dieselInjectionPumpType }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'GASOLINE'">
                <span>Gas Fuel Types:</span>
                <span>{{ gasFuelTypes }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'ELECTRIC'">
                <span>Battery Type:</span>
                <span>{{ batteryType }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'ELECTRIC'">
                <span>Battery Voltage:</span>
                <span>{{ vehicleDetails.batteryVoltage }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'ELECTRIC'">
                <span>Battery Amperage:</span>
                <span>{{ vehicleDetails.batteryAmperage }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.type == 'ELECTRIC'">
                <span>Convertible:</span>
                <span>{{ vehicleDetails.convertible ? 'Yes' : 'No' }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.convertible">
                <span>New License Plate:</span>
                <span>{{ vehicleDetails.convertibleLicensePlate }}</span>
            </div>
            <div class="vehicle-attribute" v-if="vehicleDetails.convertible">
                <span>New Gas Fuel Types:</span>
                <span> {{ convertibleGasFuelTypes }}</span>
            </div>
        </div>
    </a-modal>
</template>

<style scoped>
.vehicle-attribute span:first-child {
    font-weight: bold;
    color: #777777;
}

.vehicle-attribute span {
    display: inline-block;
    margin-right: 5px;
    margin-bottom: 4px;
}
</style>
