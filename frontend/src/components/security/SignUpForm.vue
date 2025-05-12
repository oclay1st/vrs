<script lang="ts">
import { Form, Spin } from "ant-design-vue";

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
        'a-spin': Spin,
    },
}

</script>
<script setup lang="ts">
import type { RuleObject } from 'ant-design-vue/es/form';
import type { UnwrapRef } from 'vue';
import { reactive, ref } from 'vue';
import router from '@/router';
import routesNames from '@/router/routesNames';
import { required } from '@/utils';
import { useUser } from '@/composables/security';


interface FormState {
    name: string
    lastName: string
    username: string,
    password: string
}

const singUpFormState: UnwrapRef<FormState> = reactive({
    name: '',
    lastName: '',
    username: '',
    password: ''
});

const { registerUser, registeringUser } = useUser()

const signUpForm = ref();

defineExpose({ "signUpForm": signUpForm });

const rules = {
    name: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    lastName: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    username: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    password: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
};

async function handleSignUp() {
    signUpForm.value.validate()
        .then(() => {
            registerUser(singUpFormState)
                .then(async (response) => {
                    router.push({ name: routesNames.login })
                })
        }).catch((error: any) => {
            console.log("error", error)
        })
}

function showLogin() {
    router.push({ name: routesNames.login })
}

</script>


<template>
    <a-form ref="signUpForm" :model="singUpFormState" :rules="rules" layout="vertical">
        <a-form-item name="name">
            <a-input v-model:value="singUpFormState.name" :disabled="registeringUser" placeholder="Name">
                <template #addonBefore>
                    <span class="icon-user" />
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="lastName">
            <a-input v-model:value="singUpFormState.lastName" :disabled="registeringUser" placeholder="Last Name">
                <template #addonBefore>
                    <span class="icon-user" />
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="username">
            <a-input v-model:value="singUpFormState.username" :disabled="registeringUser" placeholder="Username">
                <template #addonBefore>
                    <span class="icon-user" />
                </template>
            </a-input>
        </a-form-item>
        <a-form-item name="password">
            <a-input-password v-model:value="singUpFormState.password" :disabled="registeringUser"
                placeholder="Password">
                <template #addonBefore>
                    <span class="icon-key" />
                </template>
            </a-input-password>
        </a-form-item>
        <a-form-item>
            <div class="signup-buttons">
                <a-button :loading="registeringUser" class="signup-button" html-type="submit" @click="handleSignUp">
                    Sing Up
                </a-button>
                <a-button :loading="registeringUser" class="signup-login-button" @click="showLogin">
                    Cancel
                </a-button>
            </div>
        </a-form-item>
    </a-form>
</template>

<style scoped>
.signup-button {
    width: 100px;
    background: #009ef7;
    border: none;
    color: #ffffff;
    border-radius: 20px;
    display: inline-block;
    margin-right: 10px;
}

.signup-login-button {
    display: inline-block;
    width: 100px;
    border-radius: 20px;
}

.signup-buttons {
    text-align: center;
}
</style>
