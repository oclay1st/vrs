import type { RouteRecordRaw } from "vue-router";
import RouteNames from "./routesNames";

export const Routes: Readonly<RouteRecordRaw[]> = [
    {
        path: '/',
        name: RouteNames.home,
        meta: {
            requiresAuth: false
        },
        redirect: '/auth',
    },
    {
        path: '/admin',
        name: RouteNames.admin,
        meta: {
            requiresAuth: true
        },
        redirect: '/admin/vehicles',
        component: () => import('@/views/HomeView.vue'),
        children: [
            {
                path: 'vehicles',
                name: RouteNames.vehicleList,
                component: () => import('@/components/inventory/VehicleListView.vue')
            },
            {
                path: 'users',
                name: RouteNames.userList,
                component: () => import('@/components/security/UserListView.vue')
            }
        ]
    },
    {
        path: '/auth',
        name: RouteNames.auth,
        component: () => import('@/views/SignView.vue'),
        meta: {
            anonymousOnly: true
        },
        redirect: '/auth/login',
        children: [
            {
                path: 'login',
                name: RouteNames.login,
                component: () => import('@/components/security/LoginForm.vue')
            }, {
                path: 'signup',
                name: RouteNames.signup,
                component: () => import('@/components/security/SignUpForm.vue')
            }

        ]
    },
    {
        path: '/:pathMatch(.*)*',
        name: RouteNames.notFound,
        component: () => import('@/views/NotFoundView.vue')
    }
];
