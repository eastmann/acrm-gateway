<template>
    <div class="bread-crumbs__area">

        <div class="acrm__container">
            <div class="acrm__container-left">
                <div class="bread-crumbs__sections " id="nav">
                    <div class="bread-crumbs__section" v-for="(item, index) in crumbs" :key="index" >
                        <router-link :class="index !== crumbs.size - 1 ? 'bread-crumbs__router-link' : 'bread-crumbs__router-link-last'" :to="item[0]">{{item[1]}}</router-link>
                        <span class="bread-crumbs__dot" v-if='index !== crumbs.size - 1'> • </span>
                    </div>
                </div>
            </div>

            <div class="acrm__container-right">
                <span class="bread-crumbs__user" v-if="client"><i class="fa-solid fa-user"></i> {{client.fio}}</span>
                <div v-if="isAuthorized()" class="exit-button"  v-on:click="logout">
                    <span><i class="fas fa-sign-out-alt logout-icon"/> Выход</span>
                </div>
            </div>
        </div>


    </div>
</template>

<script>
    import sessionManager from '@/js/session/session-manager.js';
    import paths from '@/js/utils/paths'
    import auth from "@/js/utils/auth";
    import {mapGetters} from "vuex";
    import {profileApiService} from "@/services/profile-api-service";

    export default {
        name: "BreadCrumbs",
        components: {},

        data(){
            return {
                client: null,
            }
        },

        created() {
            this.getClient();
        },

        props: {
            crumbs: Map,
        },

        computed: {
            ...mapGetters(['authorities']),
        },

        methods: {
            ...auth,

            getClient() {
                let $this = this
                profileApiService.getClient()
                    .then(data => {
                        $this.client = data

                    })
                    .catch(() => {
                        //console.log(e)
                    })
            },

            logout() {
                let self = this;
                sessionManager.logout().then((success) => {
                    window.location.href = paths.home();
                }, (error) => {
                    // alert("Error during logout");
                })
            },
        }
    }

</script>

<style>
    .bread-crumbs__user{
        padding-right: 10px;
        cursor: pointer;
        font-family: 'Rostelecom Basis';
        text-decoration: none;
        color: rgba(16, 24, 40, 0.5);
        font-style: normal;
        font-weight: 500;
        font-size: 13px;
        line-height: 20px;
    }

    .bread-crumbs__area {
        border-bottom: 1px solid rgba(16, 24, 40, 0.1);
        padding: 18px 7px;
    }

    .bread-crumbs__sections {
        display: inline-block;
    }

    .bread-crumbs__sections .bread-crumbs__section {
        display: inline-block;
        padding: 0 7px;
    }

    .bread-crumbs__router-link{
        font-family: 'Rostelecom Basis';
        text-decoration: none;
        color: #101828 !important;
        font-style: normal;
        font-weight: 500;
        font-size: 13px;
        line-height: 20px;
    }

    .bread-crumbs__router-link-last{
        font-family: 'Rostelecom Basis';
        text-decoration: none;
        color: rgba(16, 24, 40, 0.5) !important;
        font-style: normal;
        font-weight: 500;
        font-size: 13px;
        line-height: 20px;
    }

    .bread-crumbs__dot{
        padding-left: 7px;
    }
</style>

