<template>
    <div>
        <bread-crumbs :crumbs="crumbs"/>

        <detail-title v-if="mainInfo"
                      :mainInfo="mainInfo"
                      :kam-lpr-info="kamLprInfo"
                      :holding-mains-total-front="holdingMainsTotalFront"
                      :hid="getHid()"
                      :id="getId()"
                      :holding-mains="holdingMains"
                      :holding-actual-dates="holdingActualDates"/>

        <div class="holding-detail__div-loader">
            <loader class="holding-detail__loader" v-if="!mainInfo"/>
        </div>

        <div v-if="mainInfo" class="margin-top-10">
            <tabs :selected-index="selectedIndex" mode="light">

<!--                <tab v-if="isNoParents()" title="Список компаний">-->
                <tab title="Список компаний">
                    <companies
                            :holding-affiliated-companies="holdingAffiliatedCompanies"
                            :holding-actual-dates="holdingActualDates"/>
                </tab>

                <tab title="Финансы">
                    <finance :holding-mains="holdingMains"
                             :holding-mains-wallet="holdingMainsWallet"
                             :holding-mains-front="holdingMainsFront"
                             :holding-mains-total-front="holdingMainsTotalFront"
                             :show-total="false"
                             :holding-actual-dates="holdingActualDates"/>
                </tab>

                <tab title="Контакты">
                    <contacts :kam-lpr-info="kamLprInfo"
                              :kam-info-front="kamInfoFront"
                              :holding-actual-dates="holdingActualDates"/>
                </tab>

<!--                <tab title="Продукты">-->
<!--                    <products :product-margin="productMargin"/>-->
<!--                </tab>-->

                <tab title="Продукты">
                    <products :product-margin="productMargin"
                              :holding-actual-dates="holdingActualDates"/>
                </tab>

                <tab title="Структура холдинга">
                    <structure-main v-if="holdingOwners" :holding-owners="holdingOwners"/>
                </tab>

                <tab title="Отчеты">
                    <reports :holding-actual-dates="holdingActualDates"/>
                </tab>

                <tab title="Основная информация">
                    <main-info v-if="getId() && mainInfo" :main-info="mainInfo" :kam-lpr-info="kamLprInfo"/>
                    <pre v-html="mainInfo.descr" class="holding-detail__descr" v-if="getHid() && mainInfo">
                        {{mainInfo.descr}}
                    </pre>
                </tab>
            </tabs>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../js/utils/paths'
    import Tabs from "@/components/tabs/Tabs";
    import Tab from "@/components/tabs/Tab";
    import DetailTitle from "@/views/main/holding_detail/components/DetailTitle";
    import BreadCrumbs from "@/components/breadcrumbs/BreadCrumbs";
    import MainInfo from "@/views/main/holding_detail/components/main_info/MainInfo";
    import Contacts from "@/views/main/holding_detail/components/contacts/Contacts";
    import Finance from "@/views/main/holding_detail/components/finance/Finance";
    import StructureMain from "@/views/main/holding_detail/components/structure_holding/StructureMain";
    import Reports from "@/views/main/holding_detail/components/reports/Reports";
    import Companies from "@/views/main/holding_detail/components/companies/Companies";
    import Products from "@/views/main/holding_detail/components/products/Products";
    import Loader from "@/components/loader/Loader";

    export default {
        name: "HoldingDetail",
        components: {
            Loader,
            Products,
            Companies,
            Reports,
            StructureMain,
            Finance,
            Contacts,
            MainInfo,
            BreadCrumbs, DetailTitle, Tab, Tabs
        },
        data() {
            return {
                holdingAffiliatedCompanies: null,
                mainInfo: null,
                kamLprInfo: null,
                kamInfoFront: null,
                holdingMains: null,
                holdingMainsWallet: null,
                holdingMainsFront: null,
                holdingMainsTotalFront: null,
                productMargin: null,
                crumbs: null,
                holdingOwners: null,
                parents: [],
                selectedIndex: 0,
                holdingActualDates: null,
            }
        },

        mounted() {
            this.loadHoldingDetail();
        },

        watch: {
            '$route'() {
                location.reload();
                this.loadHoldingDetail();
            }
        },

        computed: {},

        methods: {
            isNoParents(){
                return ((this.parents === undefined) || (this.parents.length === 0));
            },

            getHolderInn(){
                if ((this.parents === undefined) || (this.parents.length === 0)){
                    return null;
                }
                return this.parents[0].holderInn;
            },

            getShortnamerus() {
                if (this.mainInfo === null) {
                    return "";
                }

                return this.mainInfo.shortnamerus;
            },

            findByLvl(array, lvl) {
                if ((array === null) || (array.length === 0)) {
                    return null;
                }

                for (let i = 0; i < this.parents.length; i++) {
                    if (this.parents[i].lvl === lvl) {
                        return this.parents[i];
                    }
                }

                return null;
            },

            createBreadCrumbs() {
                let lvl_0 = this.findByLvl(this.parents, 0);
                let lvl_1 = this.findByLvl(this.parents, 1);
                let lvl_2 = this.findByLvl(this.parents, 2);
                let lvl_3 = this.findByLvl(this.parents, 3);
                let lvl_4 = this.findByLvl(this.parents, 4);
                let map = new Map([["/holdings", "Список холдингов"]]);

                if (lvl_0 !== null){
                    map.set("/holding-detail/hid/" + lvl_0.id, lvl_0.companyName);
                }
                if (lvl_1 !== null){
                    map.set("/holding-detail/id/" + lvl_1.id, lvl_1.companyName);
                }
                if (lvl_2 !== null){
                    map.set("/holding-detail/id/" + lvl_2.id, lvl_2.companyName);
                }
                if (lvl_3 !== null){
                    map.set("/holding-detail/id/" + lvl_3.id, lvl_3.companyName);
                }
                if (lvl_4 !== null){
                    map.set("/holding-detail/id/" + lvl_4.id, lvl_4.companyName);
                }

                this.crumbs = map;
            },

            getHid() {
                return this.$route.params.hid
            },

            getId() {
                return this.$route.params.id
            },

            getDzoId() {
                let dzoId = this.$route.query.dzo;
                if (dzoId === undefined){
                    return "";
                }
                return dzoId;
            },

            loadHoldingDetail() {
                this.loadDetailInfo(this.getHid(), this.getId(), this.getDzoId())
            },

            loadDetailInfo(hid, id, dzoId) {
                let url = urls.loadDetailInfoUrl(hid, id, dzoId);
                this.loadAndShowDetailInfoByUrl(url);
            },

            setActiveTab(){
                let dzoId = this.$route.query.dzo;
                if (dzoId === undefined){
                    return;
                }

                //Если есть в параметрах ДЗО, переключимся на структуру компаний
                this.selectedIndex = 3;
            },

            loadAndShowDetailInfoByUrl(url) {
                this.holdingOwners = null;
                let component = this;
                axios.get(url)
                    .then(function (response) {
                        component.parents = response.data.parents;
                        component.holdingAffiliatedCompanies = response.data.holdingAffiliatedCompanies;
                        component.holdingOwners = response.data.holdingOwners;
                        component.mainInfo = response.data.mainInfo;
                        component.createBreadCrumbs();
                        component.kamLprInfo = response.data.kamLprInfo;
                        component.kamInfoFront = response.data.kamInfoFront;
                        component.holdingMains = response.data.holdingMains;
                        component.holdingMainsWallet = response.data.holdingMainsWallet;
                        component.holdingMainsFront = response.data.holdingMainsFront;
                        component.holdingMainsTotalFront = response.data.holdingMainsTotalFront;
                        component.productMargin = response.data.productMargin;
                        component.holdingActualDates = response.data.holdingActualDates;
                        component.setActiveTab();
                    })
                    .catch(function (e) {
                        component.errorInfo = e;
                    });
            },

            goToAddressSearch() {
                this.$router.push(paths.addressPage());
            },
        }
    }

</script>

<style>
    .holding-detail__div-loader{

    }

    .holding-detail__loader{
        position: absolute;
        top: 50%;
        right: 50%;
    }

    .holding-detail__descr {
        padding: 10px;
    }
</style>

