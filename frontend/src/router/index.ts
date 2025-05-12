import { createRouter, createWebHistory, type RouteLocation } from 'vue-router'
import { Routes } from './routes'
import { delLocalStorageKey, getLocalStorageKey, setLocalStorageKey } from '@/config/lstorage';
import routesNames from './routesNames';
import { useSecurityStore } from '@/stores/security';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: Routes,
    scrollBehavior() {
        window.scroll({
            top: 0,
            left: 0
        })
    }
})

router.beforeEach(async (to: RouteLocation, from: RouteLocation, next: Function) => {
    if (to.matched.some(record => record.meta?.requiresAuth)) {
        const { currentUserProfile, loadUserProfile } = useSecurityStore()
        if (getLocalStorageKey('accessToken')) {
            if (!currentUserProfile.username) {
                await loadUserProfile()
                    .then(() => {
                        next()
                    }).catch(() => {
                        console.log('hello')
                        delLocalStorageKey('accessToken')
                        next({ name: routesNames.login })
                    })
            } else {
                next()
            }
        } else {
            next({ name: routesNames.login });
        }
    } else if (to.matched.some(record => record.meta?.anonymousOnly)) {
        if (getLocalStorageKey('accessToken')) {
            next(({ name: routesNames.admin }))
        } else {
            next()
        }
    } else {
        next();
    }
});

export default router
