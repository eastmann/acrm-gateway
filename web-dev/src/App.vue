<template>
    <div class="page-login">
        <template v-if="isLoginPage()">
            <LoginPage />
        </template>

        <template v-else>
            <sidebar-menu-akahon
                    bg-color="#F0EFFF"
                    is-exit-button=false
                    :is-search="false"
                    :is-menu-open="isMenuOpen()"
                    menu-title="Ростелеком"
                    menu-logo="@/assets/img/img/logo/rtk_logo.png"
            />

            <div class="main-page">
                <transition name="fade" mode="out-in">
                  <v-app>
                    <router-view />
                  </v-app>
                </transition>
            </div>
        </template>
    </div>
</template>

<script>
    import paths from './js/utils/paths'
    import SidebarMenuAkahon from "@/components/sidebar/Sidebar-menu-akahon";
    import LoginPage from "@/views/login/LoginPage";
    import auth from "@/js/utils/auth";
    import {VApp} from "vuetify/lib/components";

    export default {

        data() {
            return {
                client: null,
            }
        },

        created() {
            this.setAuthorities();
        },

        components: {
          "v-app": VApp,
          LoginPage, SidebarMenuAkahon
        },

        methods: {
            ...auth,

            isLoginPage() {
                return this.$router.currentRoute.name === 'login';
            },

            isMenuOpen(){
                return localStorage.isMenuOpen === 'true';
            },

            isHomePage() {
                return this.$route.fullPath === paths.home();
            },

            goHome() {
                if (this.$route.fullPath !== paths.home()) {
                    this.$router.push(paths.home());
                }
            },

            isHideMenu() {
                return this.$route.fullPath === paths.ofertaPage();
            },

            beforeLeave(element) {
                this.prevHeight = getComputedStyle(element).height;
            },
        }
    }
</script>
<style lang="less">
    @import "css/style.less";

    .menu-part {
        font-style: normal;
        align-self: center;
        font-weight: 500;
        font-size: 28px;
        line-height: 36px;
        color: #101828;

        /*margin-right: 0px;*/
        /*display: inline-block;*/
        /*height: 62px;*/
        /*line-height: 62px;*/
        /*vertical-align: top;*/
        /*text-align: right;*/
        width: calc(100% - 200px);
    }

    .sections {
        display: inline-block;
        padding-right: 37px;
        font-size: 14px;
    }

    .sections .section {
        display: inline-block;

        font-size: 14px;
        font-weight: 300;
        text-decoration: none;
        /*text-transform: uppercase;*/
        padding: 0 7px;
        color: #101828;
    }

    .container-space-between {
        display: flex;
        justify-content: space-between;
    }
    .main-page{

    }
    .page-login{

    }

    .exit-button {
        cursor: pointer;
        padding-right: 30px;
        color: rgba(16, 24, 40, 0.5);
        font-family: 'Rostelecom Basis';
        text-decoration: none;
        font-style: normal;
        font-weight: 500;
        font-size: 13px;
        line-height: 20px;
    }
</style>
