import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { IBatteryType, IDieselInjectionPumpType, IGasFuelType, IVehicleType } from '@/models/inventory';
import { fetchBatteryTypes, fetchDieselInjectionPumpTypes, fetchGasFuelTypes, fetchVehicleTypes } from '@/api/inventory';

export const useInventoryStore = defineStore('inventory', () => {

    const vehicleTypes = ref<IVehicleType[]>([] as IVehicleType[]);

    async function listAllVehicleTypes() {
        const response = await fetchVehicleTypes();
        vehicleTypes.value = response.data;
    }

    const dieselInjectionPumpTypes = ref<IDieselInjectionPumpType[]>([]);

    async function listAllDieselInjectionPumpTypes() {
        const response = await fetchDieselInjectionPumpTypes();
        dieselInjectionPumpTypes.value = response.data;
    }

    const gasFuelTypes = ref<IGasFuelType[]>([]);

    async function listAllGasFuelTypes() {
        const response = await fetchGasFuelTypes();
        gasFuelTypes.value = response.data;
    }

    const batteryTypes = ref<IBatteryType[]>([]);

    async function listAllBatteryTypes() {
        const response = await fetchBatteryTypes();
        batteryTypes.value = response.data;
    }

    return {
        vehicleTypes,
        dieselInjectionPumpTypes,
        gasFuelTypes,
        batteryTypes,
        listAllVehicleTypes,
        listAllDieselInjectionPumpTypes,
        listAllGasFuelTypes,
        listAllBatteryTypes
    }
})

