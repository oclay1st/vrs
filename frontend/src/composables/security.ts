
import { ref } from "vue";
import { notification } from "ant-design-vue";
import type { IPage } from "@/models/common";
import type { IUser } from "@/models/security";
import type { IUserDTO, IUserListFilter } from "@/api/types";
import { createUser, fetchUsers } from "@/api/security";

export function useUser() {

    const userPage = ref<IPage<IUser>>({
        page: 1,
        pageSize: 0,
        totalElements: 0,
        totalPages: 1,
        items: []
    });

    const loadingUsers = ref<boolean>(false);

    const registeringUser = ref<boolean>(false);

    async function loadUserPage(filters: IUserListFilter) {
        loadingUsers.value = true;
        await fetchUsers(filters)
            .then((response) => {
                userPage.value = response.data;
            })
            .catch(() => {
                notification["error"]({
                    message: 'Users',
                    description: 'Fail to list the users',
                });
            })
            .finally(() => {
                loadingUsers.value = false;
            })
    }

    async function registerUser(data: IUserDTO) {
        return new Promise<void>((resolve: Function, reject: Function) => {
            registeringUser.value = true;
            createUser(data)
                .then(async () => {
                    notification['success']({
                        message: "User Registration",
                        description: "User successfully registered"
                    });
                    resolve();
                })
                .catch((error) => {
                    let description = "Failed user registration"
                    if (error.response.data?.errorType == 'INVALID_DATA') {
                        description = error.response.data.message
                    }
                    notification['error']({
                        message: "User Registration",
                        description: description
                    });
                    reject();
                }).finally(() => {
                    registeringUser.value = false;
                });
        });
    }

    return {
        userPage,
        loadingUsers,
        registeringUser,
        loadUserPage,
        registerUser
    }
}
