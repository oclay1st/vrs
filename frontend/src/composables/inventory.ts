import type { IVehicleDetailsDTO, IVehicleDTO, IVehicleListFilter } from "@/api/types";
import type { IPage } from "@/models/common";
import type { IVehicle } from "@/models/inventory";
import { ref } from "vue";
import { notification } from "ant-design-vue";
import { archiveVehicle, fetchVehicles, storeVehicle, fetchVehicleDetails } from "@/api/inventory";

export function useVehicle() {

    const vehiclePage = ref<IPage<IVehicle>>({
        page: 1,
        pageSize: 0,
        totalElements: 0,
        totalPages: 1,
        items: []
    });

    const vehicleDetails = ref<IVehicleDetailsDTO>({} as IVehicleDetailsDTO)

    const loadingVehicleDetails = ref<boolean>(false);

    const loadingVehicles = ref<boolean>(false);

    const registeringVehicle = ref<boolean>(false);

    const deletingVehicle = ref<boolean>(false);

    async function loadVehiclePage(filters: IVehicleListFilter) {
        loadingVehicles.value = true;
        await fetchVehicles(filters)
            .then((response) => {
                vehiclePage.value = response.data;
            })
            .catch(() => {
                notification["error"]({
                    message: 'Vehicles',
                    description: 'Fail to list the vehicles in the inventory',
                });
            })
            .finally(() => {
                loadingVehicles.value = false;
            })
    }

    async function registerVehicle(data: IVehicleDTO) {
        return new Promise<void>((resolve: Function, reject: Function) => {
            registeringVehicle.value = true;
            storeVehicle(data)
                .then(async () => {
                    notification['success']({
                        message: "Vehicle Registration",
                        description: "Vehicle successfully registered"
                    });
                    registeringVehicle.value = false;
                    resolve();
                })
                .catch((error: any) => {
                    let description = "Fail registering the vehicle"
                    if (error.response.data?.errorType == 'INVALID_DATA') {
                        description = error.response.data.message
                    }
                    notification['error']({
                        message: "Vehicle Registration",
                        description: description
                    });
                    registeringVehicle.value = false;
                    reject();
                });
        });
    }

    async function deleteVehicle(vin: number) {
        return new Promise<void>((resolve: Function, reject: Function) => {
            deletingVehicle.value = true;
            archiveVehicle(vin)
                .then(async () => {
                    notification['success']({
                        message: "Vehicle",
                        description: "Vehicle successfully archived"
                    });
                    deletingVehicle.value = false;
                    resolve();
                })
                .catch((error) => {
                    let description = "Fail archiving the vehicle"
                    if (error.response.data?.errorType == 'RESOURCE_NOT_FOUND') {
                        description = "Vehicle not found"
                    }
                    notification['error']({
                        message: "Vehicle",
                        description: description
                    });
                    deletingVehicle.value = false;
                    reject();
                });
        });
    }

    async function loadVehicleDetails(vin: number) {
        loadingVehicleDetails.value = true;
        await fetchVehicleDetails(vin)
            .then((response) => {
                vehicleDetails.value = response.data;
            })
            .catch(() => {
                notification["error"]({
                    message: 'Vehicle',
                    description: 'Fail to get the vehicle info from the inventory',
                });
            })
            .finally(() => {
                loadingVehicleDetails.value = false;
            })
    }

    return {
        vehiclePage,
        loadingVehicles,
        registeringVehicle,
        deletingVehicle,
        vehicleDetails,
        loadingVehicleDetails,
        loadVehiclePage,
        registerVehicle,
        deleteVehicle,
        loadVehicleDetails
    }
}
