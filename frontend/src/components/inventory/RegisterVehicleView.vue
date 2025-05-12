<script lang="ts">
import { Modal } from "ant-design-vue";

export default {
    components: {
        'a-modal': Modal,
    },
}
</script>

<script lang="ts" setup>
import { type IVehicleDTO } from "@/api/types";
import VehicleForm from "@/components/inventory/VehicleForm.vue";
import { computed, ref, watch } from "vue";
import { useVehicle } from "@/composables/inventory";

interface Props {
    visible: boolean
}

const props = withDefaults(defineProps<Props>(), {
    visible: false
});

const emit = defineEmits(['update:visible', 'cancel', 'createSuccess']);


const { registeringVehicle, registerVehicle } = useVehicle();

const visibleModal = computed({
    get: () => props.visible,
    set: (value) => emit('update:visible', value)
});

const vehicleDTO = ref<IVehicleDTO>({} as IVehicleDTO);

const resetAndClose = () => {
    vehicleDTO.value = {} as IVehicleDTO;
    visibleModal.value = false;
};

const handleCancel = () => {
    resetAndClose();
    emit('cancel');
};

const vehicleRegisterForm = ref();

async function handleCreate() {
    vehicleRegisterForm.value.vehicleForm.validate().then(async () => {
        const data: IVehicleDTO = { ...vehicleDTO.value as IVehicleDTO };
        await registerVehicle(data)
            .then(() => {
                emit('createSuccess');
                resetAndClose();
            }).catch((error: any) => console.log(error));
    }).catch((error: any) => console.log("error", error));
}

watch(visibleModal, (newValue: boolean) => {
    if (newValue) {
        vehicleDTO.value = {} as IVehicleDTO
    }
})

</script>

<template>
    <a-modal title="Register Vehicle" v-model:open="visibleModal" :confirm-loading="registeringVehicle"
        :destroyOnClose="true" :maskClosable="false" centered okText='Save' @cancel="handleCancel" @ok="handleCreate">
        <vehicle-form ref="vehicleRegisterForm" v-model:vehicle="vehicleDTO" :loading="registeringVehicle" />
    </a-modal>
</template>
