<template>
    <div>
        <vertical-tabs mode="light">
            <vertical-tab title="РТК">
                <div class="finaice-tab">
                    <table-products :rows="creteRows(this.productMargin, 1)"
                                    header="Продукты"
                                    segment="РТК"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="ЦОД">
                <div class="finaice-tab">
                    <table-products :rows="creteRows(this.productMargin, 2)"
                                    header="Продукты"
                                    segment="ЦОД"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Солар">
                <div class="finaice-tab">
                    <table-products :rows="creteRows(this.productMargin, 3)"
                                    header="Продукты"
                                    segment="Солар"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Теле2">
                <div class="finaice-tab">
                    <table-products :rows="creteRows(this.productMargin, 4)"
                                    header="Продукты"
                                    segment="Теле2"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>
        </vertical-tabs>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../../../js/utils/paths'
    import VerticalTabs from "@/components/tabs/vertical/VerticalTabs";
    import VerticalTab from "@/components/tabs/vertical/VerticalTab";
    import table_products from "@/js/table/table-products";
    import TableProducts from "@/views/main/holding_detail/components/products/TableProducts";

    export default {
        name: "Products",
        components: {TableProducts, VerticalTab, VerticalTabs},
        props: ['productMargin', 'holdingActualDates'],
        data() {
            return {
                holdings: null,
                name1: '',
                newName: '',
                productsDemo: null,
                productsDemo1: [
                    {
                        "inn": "",
                        "product": "Продукт 1",
                        "margin": "574 млн.",
                        "dzoId": 1,
                    },
                    {
                        "inn": "",
                        "product": "Продукт 2",
                        "margin": "222 млн.",
                        "dzoId": 1,
                    },
                    {
                        "inn": "",
                        "product": "Продукт 3",
                        "margin": "111 млн.",
                        "dzoId": 2,
                    },
                ],
            }
        },

        methods: {
            ...table_products,

        }
    }

</script>

<style>

</style>

