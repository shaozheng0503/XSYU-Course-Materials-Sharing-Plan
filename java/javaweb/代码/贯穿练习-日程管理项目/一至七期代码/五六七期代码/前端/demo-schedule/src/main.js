import { createApp } from 'vue'

import App from './App.vue'

import router from './router/router'


import pinia from './pinia.js'


const app =createApp(App)
app.use(router)
app.use(pinia)
app.mount('#app')
