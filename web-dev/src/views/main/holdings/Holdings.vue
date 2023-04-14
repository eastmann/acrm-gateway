<template>
    <div>
        <bread-crumbs :crumbs="crumbs"/>
        <div class="page" v-if="holdings">
            <table-holding-list :rows="holdings" :search-name="searchName" />
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../js/utils/paths'
    import TableHoldingList from "@/views/main/holdings/TableHoldingList";
    import BreadCrumbs from "@/components/breadcrumbs/BreadCrumbs";

    export default {
        name: "Holdings",
        components: {BreadCrumbs, TableHoldingList},
        data() {
            return {
                crumbs: null,
                holdings: null,
                searchName: "",

                holdingsDemo: [
                    {
                        "sparkid": 939852,
                        "inn": "7707067683",
                        "name": "ПАО СК РОСГОССТРАХ",
                        "vir2020": 574894000,
                        "vir2020format": "574 млн.",
                        "vir2021": null,
                        "vir2021format": "",
                        "allVir2020": 494285000,
                        "allVir2020format": "494 млн.",
                        "allVir2021": 571586000,
                        "allVir2021format": "571 млн.",
                        "codeOsnOkv": "65.12",
                        "nameOsnOkv": "Страхование, кроме страхования жизни"
                    },
                    {
                        "sparkid": 211,
                        "inn": "7708004767",
                        "name": "ПАО ЛУКОЙЛ",
                        "vir2020": 322811966000,
                        "vir2020format": "322 811 млн.",
                        "vir2021": 2389317290000,
                        "vir2021format": "2 389 317 млн.",
                        "allVir2020": 3897356530000,
                        "allVir2020format": "3 897 356 млн.",
                        "allVir2021": 4773057840000,
                        "allVir2021format": "4 773 057 млн.",
                        "codeOsnOkv": "71.12.3",
                        "nameOsnOkv": "Работы геолого-разведочные, геофизические и геохимические в области изучения недр и воспроизводства минерально-сырьевой базы"
                    }
                ],
            }
        },

        mounted() {
            this.loadBusinessData();
            this.createBreadCrumbs();
        },

        methods: {
            createBreadCrumbs(){
                this.crumbs = new Map([["/holdings", "Список холдингов"]])
            },

            loadBusinessData() {
                this.loadHoldings()
            },

            loadHoldings() {
                let url = urls.loadHoldingsUrl();
                this.loadAndShowHoldingsByUrl(url);
            },

            loadAndShowHoldingsByUrl(url) {
                this.holdings = null;

                let component = this;
                axios.get(url)
                    .then(function (response) {
                        component.holdings = response.data.holdings;
                    })
                    .catch(function (e) {
                        component.holdings = e;
                    });
            },

            goToAddressSearch() {
                this.$router.push(paths.addressPage());
            },

            onSearchName(val){
                this.searchName = val;
            }
        }
    }

</script>

<style lang="less">
    @import url('https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css');

</style>

