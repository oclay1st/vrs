import axios from 'axios'
import { getLocalStorageKey } from '@/config/lstorage';

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL ? import.meta.env.VITE_API_BASE_URL : 'http://localhost:8080', // from .env files
})

axiosInstance.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
axiosInstance.interceptors.request.use((config) => {
    const token = getLocalStorageKey("accessToken")
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }
    return config
})

axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            console.error('Unauthorized, logging out...')
        }
        return Promise.reject(error)
    }
)

export default axiosInstance
