import { fetchUserProfile } from "@/api/security";
import type { IUserProfile } from "@/models/security";
import { defineStore } from "pinia";
import { ref } from "vue";


export const useSecurityStore = defineStore('security', () => {

    const currentUserProfile = ref<IUserProfile>({} as IUserProfile)

    async function loadUserProfile() {
        const response = await fetchUserProfile();
        currentUserProfile.value = response.data;
    }

    return {
        currentUserProfile,
        loadUserProfile
    }
})
