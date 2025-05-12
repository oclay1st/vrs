<template>
    <div id="login-page">
        <div class="login-container">
            <a-row class="sign-forms">
                <a-col :md="{ span: 14 }" :xs="{ span: 0 }" class="login-description">
                    <div class="login-logo">
                        <img alt="VRS logo" src="@/assets/logo.png">
                    </div>
                    <div class="login-description-box">
                        <p class="app-description">
                            A system for managing vehicles in a workshop, supporting diesel, gasoline, and electric
                            vehicles. It records general details about each vehicle along with additional attributes
                            based on the vehicle type.
                        </p>
                    </div>
                </a-col>
                <a-col :md="{ span: 10 }" :xs="{ span: 24 }" class="form-container">
                    <div class="app-welcome">Vehicle Registration System</div>
                    <router-view></router-view>
                </a-col>
            </a-row>
        </div>
    </div>
</template>

<style scoped>
.login-container {
    max-width: 800px;
    width: 100%;
    max-height: 400px;
    height: 100%;
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.12), 14px 14px 14px rgba(0, 0, 0, 0.1);
    border-radius: 15px;
    transition: all 0.3s ease 0s;
    background-color: #ffffff;
    position: fixed;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}

#login-page {
    display: flex;
    justify-content: center;
    height: 100vh;
    background: #F1F5F9 url('@/assets/background.jpg') right top no-repeat;
}

.login-description {
    background: #1E293B;
    border-bottom-left-radius: 15px;
    border-top-left-radius: 15px;
}

.sign-forms {
    display: flex;
    height: inherit;
}

.form-container {
    padding: 2px 35px;
    margin-top: 30px;
}

.login-logo {
    text-align: center;
    margin-bottom: 10px;
    margin-top: 60px;
}

.login-logo img {
    width: 90px;
    height: auto;
}

.login-description-box {
    position: relative;
    padding: 20px 58px;
}

.app-welcome {
    font-size: 20px;
    font-weight: bold;
    color: #222F3D;
    margin: 10px 0 30px 20px;
    text-align: center;
}

.app-description,
.app-description-header {
    font-size: 13px;
    color: #ffffff;
    text-align: center;
}
</style>
