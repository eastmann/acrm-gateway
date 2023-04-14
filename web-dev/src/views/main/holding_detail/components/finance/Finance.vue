<template>
    <div>
        <vertical-tabs mode="light">
            <vertical-tab title="Всего">
                <div class="finaice-tab">
                    <table-finance :columns="creteColumnsBudgetFront()"
                                   :rows="creteRowsTotal(this.holdingMainsTotalFront, 0)"
                                   header="Ключевые бюджетные показатели (Все)"
                                   segment="Всего"
                                   :show-total="showTotal"
                                   :sort-enabled="true"
                                   :hide-setting="true"
                                   :hide-search="true"
                                   :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="РТК">
                <div class="finaice-tab">
                    <table-finance  :columns="creteColumnsBudget4kvFront()"
                                    :rows="creteRows(this.holdingMainsFront, 1)"
                                    header="Ключевые бюджетные показатели"
                                    segment="РТК"
                                    :show-total="showTotal"
                                    :project-desc="getKeyProjectDesc(this.holdingMains, 1)"
                                    :sort-enabled="true"
                                    :comment="'Ключевые проекты:'"
                                    :hide-setting="true"
                                    :hide-search="true"
                                    :holding-actual-dates="holdingActualDates"/>

                    <table-wallet :columns="creteColumnsWallet()"
                                :rows="creteRows(this.holdingMainsWallet, 1)"
                                header="Кошелек холдинга"
                                segment="РТК"
                                :show-total="showTotal"
                                :sort-enabled="true"
                                :hide-setting="true"
                                :hide-search="true"
                                :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="ЦОД">
                <div class="finaice-tab">
                    <table-finance  :columns="creteColumnsBudgetFront()"
                                    :rows="creteRows(this.holdingMainsFront, 2)"
                                    header="Ключевые бюджетные показатели"
                                    segment="ЦОД"
                                    :show-total="showTotal"
                                    :project-desc="getKeyProjectDesc(this.holdingMains, 2)"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :hide-search="true"
                                    :holding-actual-dates="holdingActualDates"/>

                    <table-wallet :columns="creteColumnsWallet()"
                                :rows="creteRows(this.holdingMainsWallet, 2)"
                                header="Кошелек холдинга"
                                segment="ЦОД"
                                :show-total="showTotal"
                                :sort-enabled="true"
                                :hide-setting="true"
                                :hide-search="true"
                                :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Солар">
                <div class="finaice-tab">
                    <table-finance  :columns="creteColumnsBudgetFront()"
                                    :rows="creteRows(this.holdingMainsFront, 3)"
                                    header="Ключевые бюджетные показатели"
                                    segment="Солар"
                                    :show-total="showTotal"
                                    :project-desc="getKeyProjectDesc(this.holdingMains, 3)"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :hide-search="true"
                                    :holding-actual-dates="holdingActualDates"/>

                    <table-wallet :columns="creteColumnsWallet()"
                                :rows="creteRows(this.holdingMainsWallet, 3)"
                                header="Кошелек холдинга"
                                segment="Солар"
                                :show-total="showTotal"
                                :sort-enabled="true"
                                :hide-setting="true"
                                :hide-search="true"
                                :holding-actual-dates="holdingActualDates"/>
                </div>
            </vertical-tab>

            <vertical-tab title="Теле2">
                <div class="finaice-tab">
                    <table-finance  :columns="creteColumnsBudgetFront()"
                                    :rows="creteRows(this.holdingMainsFront, 4)"
                                    header="Ключевые бюджетные показатели"
                                    segment="Теле2"
                                    :show-total="showTotal"
                                    :project-desc="getKeyProjectDesc(this.holdingMains, 4)"
                                    :sort-enabled="true"
                                    :hide-setting="true"
                                    :hide-search="true"
                                    :holding-actual-dates="holdingActualDates"/>

                    <table-wallet :columns="creteColumnsWallet()"
                                :rows="creteRows(this.holdingMainsWallet, 4)"
                                header="Кошелек холдинга"
                                segment="Теле2"
                                :show-total="showTotal"
                                :sort-enabled="true"
                                :hide-setting="true"
                                :hide-search="true"
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
    import VerticalTabs from "../../../../../components/tabs/vertical/VerticalTabs";
    import VerticalTab from "../../../../../components/tabs/vertical/VerticalTab";
    import table_fin from "@/js/table/table-finance";
    import TableFinance from "@/views/main/holding_detail/components/finance/TableFinance";
    import TableWallet from "@/views/main/holding_detail/components/finance/TableWallet";

    export default {
        name: "Finance",
        components: {TableWallet, TableFinance, VerticalTab, VerticalTabs},
        props: {
            'holdingMains': [Object, Array],
            'holdingMainsWallet': [Object, Array],
            'holdingMainsFront': [Object, Array],
            'holdingMainsTotalFront': [Object, Array],
            'holdingActualDates': [Array],
            'showTotal': {
                type: Boolean,
                default: true,
            },
            'sortOptions':{
                enabled: false,
            },
            // 'holderInn': {
            //     type: String,
            // }
        },
        data() {
            return {
            }
        },

        computed: {

        },

        methods: {
            ...table_fin,
        }
    }

</script>

<style>
    .finaice-tab {
        margin: 30px;
    }

</style>

