<script lang="ts">
import { Form, Input } from "ant-design-vue";
import type { ITokenDTO } from '@/models/security';
import type { RuleObject } from 'ant-design-vue/es/form';

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
        'a-input-password': Input.Password
    },
}
</script>

<script lang="ts" setup>
import { reactive, ref, type UnwrapRef } from 'vue';
import { notification } from "ant-design-vue";
import { useRouter } from "vue-router";
import routesNames from "@/router/routesNames";
import { loginUser } from '@/api/security';
import { setLocalStorageKey, delLocalStorageKey } from '@/config/lstorage';

interface FormState {
    username: string,
    password: string
}

const router = useRouter();

const confirmLoading = ref<boolean>(false);

const loginForm = ref();

const loginFormState: UnwrapRef<FormState> = reactive({
    username: '',
    password: ''
});

const rules = {
    username: [{ required: true, message: 'User required', trigger: 'blur' }] as RuleObject,
    password: [{ required: true, message: 'Password required', trigger: 'blur' }] as RuleObject,
};

async function handleLogin() {
    loginForm.value.validate()
        .then(() => {
            confirmLoading.value = true;
            loginUser(loginFormState)
                .then(async (response) => {
                    confirmLoading.value = false;
                    setLocalStorageKey('accessToken', response.data.accessToken)
                    router.push({ name: routesNames.admin })
                })
                .catch(error => {
                    confirmLoading.value = false;
                    delLocalStorageKey('accessToken')
                    notification['error']({
                        message: 'Authentication',
                        description: 'Invalid login credentials!',
                    });
                    console.log(error)
                });
        }).catch((error: any) => {
            console.log("error", error)
        })
}
function showSignUp() {
    router.push({ name: routesNames.signup })
}

</script>

<template>
    <a-form id="login-form" ref="loginForm" :model="loginFormState" :rules="rules">
        <a-form-item name="username">
            <a-input v-model:value="loginFormState.username" :disabled="confirmLoading" placeholder="Username"
                type="text">
                <template #addonBefore>
                    <span class="icon-user" />
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="password">
            <a-input-password v-model:value="loginFormState.password" :disabled="confirmLoading" placeholder="Password">
                <template #addonBefore>
                    <span class="icon-key" />
                </template>
            </a-input-password>
        </a-form-item>
        <a-form-item>
            <div class="login-buttons">
                <a-button :loading="confirmLoading" class="login-button" html-type="submit" @click="handleLogin">
                    Login
                </a-button>
                <a-button :loading="confirmLoading" class="login-signup-button" @click="showSignUp">
                    Sign Up
                </a-button>
            </div>
        </a-form-item>
    </a-form>
</template>

<style scoped>
.login-button {
    width: 140px;
    background: #009ef7;
    border: none;
    color: #ffffff;
    margin-top: 10px;
    border-radius: 20px;
}

.login-buttons {
    text-align: center;
}

.login-signup-button {
    margin-top: 10px;
    width: 140px;
    border-radius: 20px;
}
</style>
