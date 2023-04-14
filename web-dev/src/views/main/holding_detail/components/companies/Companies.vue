<template>
    <div>
        <div class="finaice-tab">
            <table-companies
                    :rows="creteRows(this.holdingAffiliatedCompanies)"
                    header="Список компаний"
                    :sort-enabled="true"
                    :holding-actual-dates="holdingActualDates"
            />
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../../../js/utils/paths'
    import table_companies from "@/js/table/table-companies";
    import TableCompanies from "@/views/main/holding_detail/components/companies/TableCompanies";

    export default {
        name: "Companies",
        components: {TableCompanies},
        props: ['holdingAffiliatedCompanies', 'holdingActualDates'],
        data() {
            return {
                holdings: null,
                companiesDemo: [
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
            ...table_companies,

        }
    }

</script>

<style>

</style>

