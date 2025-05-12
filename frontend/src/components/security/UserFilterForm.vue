<script lang="ts">
import { Form } from "ant-design-vue";

export default {
    components: {
        'a-form': Form,
        'a-form-item': Form.Item,
    }
}
</script>

<script lang="ts" setup>
import _ from "lodash";
import { type IUserListFilter } from "@/api/types";
import { reactive, type UnwrapRef, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { SearchOutlined } from '@ant-design/icons-vue';

interface UserFilterFormState {
    search: string
}

interface Props {
    filters?: IUserListFilter
}

const props = withDefaults(defineProps<Props>(), {
    filters: undefined
});

const router = useRouter();

const route = useRoute();

const formState: UnwrapRef<UserFilterFormState> = reactive({
    search: '',
});

function handleSubmit() {
    let search = formState.search && formState.search != '' ? formState.search.trim() : undefined;
    let page = route.query?.page ? route.query?.page : undefined;
    if (route.query?.search != search) {
        page = "20";
    }
    let newQuery = Object.assign({}, route.query, { search: search, page: page });
    if (_.isEqual(_.pickBy(newQuery), _.pickBy({ ...route.query }))) {
        newQuery = Object.assign({}, newQuery, { size: newQuery?.size ? undefined : "20" });
    }
    router.push({ query: newQuery });
}

watch(() => props.filters, () => {
    formState.search = props.filters?.search as string
});

</script>

<template>
    <a-form :model="formState" class="custom-inline-form" layout="inline">
        <a-form-item>
            <a-input v-model:value="formState.search" allow-clear placeholder="Search...">
                <template #prefix>
                    <SearchOutlined style="color:rgba(0,0,0,.25)" />
                </template>
            </a-input>
        </a-form-item>
        <a-form-item>
            <a-button class="btn-search" type="primary" @click.prevent="handleSubmit">
                Search
            </a-button>
        </a-form-item>
    </a-form>
</template>
