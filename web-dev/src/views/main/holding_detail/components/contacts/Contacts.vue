<template>
    <div>
        <vertical-tabs mode="light">
            <vertical-tab title="РТК">
                <div class="finaice-tab">
                    <table-base
                            :columns="creteColumnsCamDir('')"
                            :rows="creteRowsCam(this.kamLprInfo, 1)"
                            header="Менеджер по работе с клиентом"
                            segment="РТК"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                    <table-base
                            :columns="creteColumnsLpr('')"
                            :rows="creteRowsCam(this.kamLprInfo, 0)"
                            header="Лицо, принимающее решение"
                            segment="РТК"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                </div>
            </vertical-tab>

            <vertical-tab title="ЦОД">
                <div class="finaice-tab">
                    <table-base
                            :columns="creteColumnsCamDir('')"
                            :rows="creteRowsCam(this.kamLprInfo, 2)"
                            header="Менеджер по работе с клиентом"
                            segment="ЦОД"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                    <table-base
                            :columns="creteColumnsLpr('')"
                            :rows="creteRowsCam(this.kamLprInfo, 0)"
                            header="Лицо, принимающее решение"
                            segment="ЦОД"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                </div>
            </vertical-tab>

            <vertical-tab title="Солар">
                <div class="finaice-tab">
                    <table-base
                            :columns="creteColumnsCamDir('')"
                            :rows="creteRowsCam(this.kamLprInfo, 3)"
                            header="Менеджер по работе с клиентом"
                            segment="Солар"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                    <table-base
                            :columns="creteColumnsLpr('')"
                            :rows="creteRowsCam(this.kamLprInfo, 0)"
                            header="Лицо, принимающее решение"
                            segment="Солар"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                </div>
            </vertical-tab>

            <vertical-tab title="Теле2">
                <div class="finaice-tab">
                    <table-base
                            :columns="creteColumnsCamDir('')"
                            :rows="creteRowsCam(this.kamLprInfo, 4)"
                            header="Менеджер по работе с клиентом"
                            segment="Теле2"
                            :show-total="false"
                            :sort-enabled="false"
                            :hide-setting="true"
                            :hide-search="true"
                            :holding-actual-dates="holdingActualDates"/>

                    <table-base
                            :columns="creteColumnsLpr('')"
                            :rows="creteRowsCam(this.kamLprInfo, 0)"
                            header="Лицо, принимающее решение"
                            segment="Теле2"
                            :show-total="false"
                            :sort-enabled="false"
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
    import TableBase from "@/components/table/TableBase";
    import table_cam from "@/js/table/table-contacts";

    export default {
        name: "Contacts",
        components: {TableBase, VerticalTab, VerticalTabs},
        props: ['kamLprInfo', 'kamInfoFront', 'holdingActualDates'],

        methods: {
            ...table_cam,
        }
    }

</script>

<style>
    .finaice-tab {
        margin: 30px;
    }
</style>

