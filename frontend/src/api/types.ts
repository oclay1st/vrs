
interface PageFilter {
    page?: number
    size?: number
}

export interface IVehicleListFilter extends PageFilter {
    vin?: string
    licensePlate?: string
    type?: string
    dieselInjectionPumpType?: string
    gasFuelTypes?: string[]
    batteryType?: string
    batteryVoltage?: number
    batteryAmperage?: number
}

export interface IVehicleDTO {
    vin?: number
    licensePlate?: string
    type?: string
    dieselInjectionPumpType?: string
    gasFuelTypes?: string[]
    batteryType?: string
    batteryVoltage?: string
    batteryAmperage?: string
    convertible: boolean
    convertibleLicensePlate: string
    convertibleGasFuelTypes: string[]
}

export interface IUserDTO {
    username: string
    password: string
    name: string
    lastName: string
}

export interface IVehicleDetailsDTO extends IVehicleDTO {
    createdAt: string
    updatedAt: string
}


export interface IUserListFilter extends PageFilter {
    search?: string
}

