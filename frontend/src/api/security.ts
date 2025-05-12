import type { ILoginDTO, IUser, IUserProfile } from "@/models/security";
import api from '@/api'
import type { AxiosResponse } from "axios";
import type { IPage } from "@/models/common";
import type { IUserDTO, IUserListFilter } from "./types";

export const loginUser = async (data: ILoginDTO): Promise<AxiosResponse> => {
    return await api.post('/auth/token', data);
}

export const fetchUserProfile = async (): Promise<AxiosResponse<IUserProfile>> => {
    return await api.get('/users/profile');
}

export const fetchUsers = async (filters: IUserListFilter): Promise<AxiosResponse<IPage<IUser>>> => {
    return await api.get('/users', { params: filters });
}

export const createUser = async (data: IUserDTO): Promise<AxiosResponse> => {
    return await api.post('/auth/registration', data);
}
