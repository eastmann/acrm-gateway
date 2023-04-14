<template>
    <div>
        <div class="finaice-tab">
            <table-reports :columns="creteColumnsReports()"
                           :rows="creteRowsReports()"
                           header="Отчеты"
                           :holding-actual-dates="holdingActualDates"
                           :sort-enabled="false"/>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../../../js/utils/paths'
    import VerticalTabs from "../../../../../components/tabs/vertical/VerticalTabs";
    import VerticalTab from "../../../../../components/tabs/vertical/VerticalTab";
    import table_rep from "@/js/table/table-reports";
    import TableReports from "@/views/main/holding_detail/components/reports/TableReports";

    export default {
        name: "Reports",
        components: {TableReports},
        props: ['kamLprInfo', 'holdingActualDates'],

        methods: {
            ...table_rep,
        }
    }

</script>

<style>
    .finaice-tab {
        margin: 30px;
    }
</style>

