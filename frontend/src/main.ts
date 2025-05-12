import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from '@/App.vue'
import router from './router'
import 'simple-line-icons/css/simple-line-icons.css';
import '@/assets/base.css';

import { Affix, Badge, Button, Col, Divider, Dropdown, Input, Layout, Menu, message, notification, Progress, Row, Space } from 'ant-design-vue';


const app = createApp(App)

app.use(Row);
app.use(Col);
app.use(Layout);
app.use(Menu);
app.use(Divider);
app.use(Input);
app.use(Button);
app.use(Dropdown);
app.use(Affix);
app.use(Progress);
app.use(Badge);
app.use(Space);

notification.config({
    placement: 'topRight',
    top: '70px',
    duration: 4
});

app.config.globalProperties.$notification = notification;
app.config.globalProperties.$message = message;


app.use(createPinia())

app.use(router)

app.mount('#app')
