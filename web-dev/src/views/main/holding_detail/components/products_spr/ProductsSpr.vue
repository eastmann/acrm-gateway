<template>
    <div>
        <vertical-tabs mode="light">
            <vertical-tab title="РТК">
                <div class="finaice-tab">
                    <table-base :columns="creteColumnsProducts()" :rows="creteRows(this.sprProducts, 1)"
                                header="Продукты" segment="РТК" :sort-enabled="false"/>
                </div>
            </vertical-tab>

            <vertical-tab title="ЦОД">
                <div class="finaice-tab">
                    <table-base :columns="creteColumnsProducts()" :rows="creteRows(this.sprProducts, 2)"
                                header="Продукты" segment="ЦОД" :sort-enabled="false"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Солар">
                <div class="finaice-tab">
                    <table-base :columns="creteColumnsProducts()" :rows="creteRows(this.sprProducts, 3)"
                                header="Продукты" segment="Солар" :sort-enabled="false"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Теле2">
                <div class="finaice-tab">
                    <table-base :columns="creteColumnsProducts()" :rows="creteRows(this.sprProducts, 4)"
                                header="Продукты" segment="Теле2" :sort-enabled="false"/>
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
    import table_products_spr from "@/js/table/table-products-spr";
    import TableBase from "@/components/table/TableBase";

    export default {
        name: "ProductsSpr",
        components: {TableBase, VerticalTab, VerticalTabs},
        props: ['sprProducts'],
        data() {
            return {
            }
        },

        methods: {
            ...table_products_spr,

        }
    }

</script>

<style>

</style>

