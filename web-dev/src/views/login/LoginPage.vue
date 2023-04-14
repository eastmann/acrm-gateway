<template>
    <div class="login-form-new">
        <div>
            <div class="login__title">
                <h1>ACRM</h1>
            </div>
            <div class="login-form">
                <div class="login__enter">Вход в систему</div>
                <p class="guiding-label">Для доступа в систему необходимо заполнить данные</p>

                <RTTextEdit class="margin-top-30" caption="Электронная почта"
                            v-on:rt-focus-out="loginOnFocusOut"
                            v-on:rt-on-key-up="onEnterGoToElement($event, 'password')"
                            :error-info="formErrors.login"
                            v-model="login"/>

                <RTPasswordEdit ref="password" caption="Пароль:"
                                v-on:rt-focus-out="passwordOnFocusOut"
                                v-on:rt-on-key-up="passwordOnKeyUp"
                                :error-info="formErrors.password"
                                v-model="password"/>

                <div v-if="message" class="login-message">{{message}}</div>

                <div class="mdm-button-row">
                    <ButtonLargePrimary :width-param="'100%'" class="login-style" tabindex="0" ref="buttonOK"
                                        v-on:click.native="loginUser">Войти
                    </ButtonLargePrimary>
                </div>

                <!--            <form>-->
                <!--                <div class="login-form-field">-->
                <!--                    <label for="login">Логин (E-mail):</label>-->
                <!--                    <input id="login" name="login"  v-on:keydown="loginOnEnter" v-model="login">-->
                <!--                </div>-->
                <!--                <div class="login-form-field">-->
                <!--                    <label for="password">Пароль:</label>-->
                <!--                    <input id="password" name="password" type="password" v-on:keydown="loginOnEnter" v-model="password"/>-->
                <!--                </div>-->
                <!--                <div v-if="message" class="login-message">{{message}}</div>-->
                <!--                <div class="mdm-button-row">-->
                <!--                    <div class="enter-button" v-on:click="loginUser" >Войти</div>-->
                <!--                </div>-->
                <!--            </form>-->
            </div>
        </div>

        <div class="login-img"></div>
    </div>
</template>

<script>
    import axios from 'axios';
    import userSession from '../../js/session/user-session'
    import paths from "@/js/utils/paths";
    import sessionManager from '../../js/session/session-manager';
    import LoginErrors from '../../js/session/helper/login-errors';
    import RTTextEdit from "@/components/inputs/RTTextEdit";
    import RTPasswordEdit from "@/components/inputs/RTPasswordEdit";
    import validators from "@/js/utils/validators";
    import ButtonLargePrimary from "@/components/button/ButtonLargePrimary";

    let WRONG_CREDENTIALS_ERROR = "Неверный логин и/или пароль";
    let UNKNOWN_ERROR = "Неизвестная ошибка";

    export default {
        name: "LoginPage",
        components: {ButtonLargePrimary, RTPasswordEdit, RTTextEdit},
        data() {
            return {
                login: null,
                password: null,
                message: null,
                formErrors: {
                    login: null,
                    password: null
                }
            }
        },

        mounted() {
            window.document.body.style.paddingLeft = '0px';
        },

        methods: {
            goToElement(el) {
                this.$refs[el].$refs.inp.focus()
            },

            onEnterGoToElement(event, el) {
                if (event.key === 'Enter') {
                    this.goToElement(el)
                }
            },

            loginOnFocusOut(val) {
                this.formErrors.login = validators.validateEmail(val);
            },

            passwordOnFocusOut(val) {
                this.formErrors.password = validators.validatePassword(val);
            },

            passwordOnKeyUp(event) {
                if (event.key === 'Enter') {
                    //Дополнительно валидируем, т.к. событие OnKeyUp наступает раньше потери фокуса
                    this.formErrors.password = validators.validatePassword(this.password);
                    this.loginUser()
                }
            },

            checkLoginError() {
                let isError = false;
                if ((this.formErrors.login !== '') ||
                    (this.formErrors.password !== '')) {

                    isError = true;
                }

                return isError;
            },

            loginUser() {
                if (this.checkLoginError()) {
                    return;
                }

                let self = this;

                sessionManager.login(this.login, this.password).then((success) => {
                    self.message = null;
                    self.$router.go(paths.home());
                }, (error) => {
                    self.message = self._messageForLoginError(error);
                }).catch((e) => {
                    self.message = UNKNOWN_ERROR
                });
            },
            loginOnEnter(e) {
                if (e.key === 'Enter') {
                    this.loginUser();
                }
            },

            _messageForLoginError(error) {
                switch (error) {
                    case LoginErrors.WRONG_LOGIN_OR_PASSWORD:
                        return WRONG_CREDENTIALS_ERROR;
                    default:
                        return UNKNOWN_ERROR;
                }
            }
        }
    }
</script>

<style scoped lang="less">
    @import "../../css/colors";

    .margin-top-30 {
        margin-top: 30px;
        font-size: 16px;
    }

    .alert-auth {
        padding: 10px;
        font-weight: bold;
        border-style: solid;
        border-color: @baseViolet;
        margin-top: 30px;
        max-width: 580px;
        text-align: justify;
        font-size: 14px;
    }

    .login-form-new {
        display: grid;
        /*padding-right: 20px;*/
        grid-template-columns: 30% 70%;
        height: 100%;
    }

    .login__title{
        padding: 40px;
        /*justify-self: first;*/
        /*align-self: first;*/
        h1 {
            background-image: url("../../assets/img/login/rtk_logo.png");
            background-size: 20px;
            background-repeat: no-repeat;

            padding-left: 40px;
            padding-top: 4px;
            font-size: 28px;
            font-family: 'Rostelecom Basis';
            color: #000000;
            font-weight: bold;
        }
    }

    .login__enter{
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 700;
        font-size: 22px;
        line-height: 24px;
        color: #101828;
    }

    .login-page {
        font-family: 'Rostelecom Basis';
        width: 100%;
        height: 100%;
        font-weight: lighter;
        display: grid;
        grid-template-columns: 1fr;

        .login-form-field {
            margin: 10px 0;
            display: grid;
            grid-template-columns: 1fr 1fr;

            font-size: 20px;

            input {
                padding: 3px;
                font-size: 20px;
                width: 300px;
            }
        }
    }

    .guiding-label {
        padding-top: 16px;
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 16px;
        line-height: 24px;
        color: #101828;
    }

    .login-form {
        justify-self: center;
        align-self: center;

        padding: 40px;
    }

    .login-img{
        background-color: #9466FF;
    }

    .mdm-button-row {
        margin-top: 20px;
        text-align: center;
    }

    .enter-button {
        background-color: @baseViolet;
        color: white;
        padding: 5px 0;
        border-radius: 5px;
        font-size: 20px;

        cursor: pointer;
    }

    .login-message {
        color: @errorRed;
    }
</style>