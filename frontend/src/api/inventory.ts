import type { IBatteryType, IDieselInjectionPumpType, IGasFuelType, IVehicle, IVehicleType } from "@/models/inventory";
import type { IVehicleDetailsDTO, IVehicleDTO, IVehicleListFilter } from "@/api/types";
import api from '@/api'
import type { IPage } from "@/models/common";
import type { AxiosResponse } from "axios";

export const fetchVehicles = async (filters: IVehicleListFilter): Promise<AxiosResponse<IPage<IVehicle>>> => {
    return await api.get('/inventory/vehicles', { params: filters });
}

export const fetchVehicleTypes = async (): Promise<AxiosResponse<IVehicleType[]>> => {
    return await api.get('/inventory/vehicles-types');
}

export const fetchDieselInjectionPumpTypes = async (): Promise<AxiosResponse<IDieselInjectionPumpType[]>> => {
    return await api.get('/inventory/injection-pump-types');
}

export const fetchGasFuelTypes = async (): Promise<AxiosResponse<IGasFuelType[]>> => {
    return await api.get('/inventory/gas-fuel-types');
}

export const fetchBatteryTypes = async (): Promise<AxiosResponse<IBatteryType[]>> => {
    return await api.get('/inventory/battery-types');
}

export const storeVehicle = async (data: IVehicleDTO): Promise<AxiosResponse> => {
    return await api.post('/inventory/vehicles', data);
}

export const archiveVehicle = async (vin: number): Promise<AxiosResponse> => {
    return await api.delete('/inventory/vehicles/' + vin);
}

export const fetchVehicleDetails = async (vin: number): Promise<AxiosResponse<IVehicleDetailsDTO>> => {
    return await api.get('/inventory/vehicles/' + vin);
}

