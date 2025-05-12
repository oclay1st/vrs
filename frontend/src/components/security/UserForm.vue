<script lang="ts">
import { Form, Spin } from "ant-design-vue";
import type { RuleObject } from 'ant-design-vue/es/form';

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
        'a-spin': Spin,
    },
}

</script>
<script setup lang="ts">
import { ref, computed } from 'vue';
import { required } from '@/utils';
import type { IUserDTO } from '@/api/types';

export interface Props {
    loading?: boolean,
    user: IUserDTO,
    currentId?: string
}

const props = withDefaults(defineProps<Props>(), {
    loading: false,
    vehicle: undefined,
    currentId: undefined
});

const userForm = ref();

defineExpose({ "userForm": userForm });

const rules = {
    name: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    lastName: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    username: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
    password: [{ required: true, validator: required, trigger: 'blur' }] as RuleObject,
};

</script>


<template>
    <a-spin :spinning="props.loading">
        <a-form ref="vehicleForm" :model="props.user" :rules="rules" layout="vertical">
            <a-form-item label="Name" class="form-label-color-dark" name="name">
                <a-input v-model:value="props.user.name" />
            </a-form-item>
            <a-form-item label="Last Name" class="form-label-color-dark" name="licensePlate">
                <a-input v-model:value="props.user.lastName" />
            </a-form-item>
            <a-form-item label="Username" class="form-label-color-dark" name="username">
                <a-input v-model:value="props.user.name" />
            </a-form-item>
            <a-form-item label="Password" class="form-label-color-dark" name="password">
                <a-input v-model:value="props.user.password" />
            </a-form-item>
        </a-form>
    </a-spin>
</template>

<style scoped>
.expand-item {
    width: 100%;
}
</style>
