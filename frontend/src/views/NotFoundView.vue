<script lang="ts">
import { Result } from 'ant-design-vue';

export default {
    components: {
        'a-result': Result,
    }
}
</script>
<template>
    <a-result status="404" sub-title="Sorry, the page you visited does not exist." title="404">
        <template #extra>
            <a-button type="primary">Back Home</a-button>
        </template>
    </a-result>
</template>
