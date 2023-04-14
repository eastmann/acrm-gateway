<template>
    <div>
<!--    <div class="main-info__grid">-->
<!--        <div class="main-info__sidebar">-->

<!--        </div>-->

        <div class="tt-body-text">
            <div v-if="mainInfo" class="main-info__two-column-50-50">

                <table class="rkn-table">
                    <tr>
                        <td class="main-info__caption">
                            Полное наименование компании
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.fullnamerus}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            ОГРН
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.ogrn}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            ИНН
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.inn}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            Регион
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.rf_region_name}}
                        </td>
                    </tr>

                </table>


                <table class="rkn-table">
                    <tr v-if="mainInfo.sector">
                        <td class="main-info__caption">
                            Классификация индустрии по ОКВЭД
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.sector}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            НК/3К
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.hk_3k}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            Основной вид деятельности
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.okved}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            Уставной капитал
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.chartercapital}}
                        </td>
                    </tr>

                    <tr>
                        <td class="main-info__caption">
                            Руководитель
                        </td>
                        <td class="main-info__text">
                            {{mainInfo.position}} {{mainInfo.fio}}
                        </td>
                    </tr>

                </table>

            </div>

            <div style="width: 100%">
            <table-base class="margin-top-10"
                    :columns="creteColumnsHolding()"
                    :rows="items"
                    header="Руководители"
                    :show-total="false"
                    :sort-enabled="false"
                    :hide-setting="true"
                    :hide-search="true"
                    :expanded="false"/>
            </div>

        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../../../js/utils/paths'
    import table_cam from "@/js/table/table-contacts";
    import TableBase from "@/components/table/TableBase";

    export default {
        name: "MainInfo",
        components: {TableBase},
        props: ['mainInfo', 'kamLprInfo'],
        computed: {
            items() {
                let elems = this.creteRowsDir(this.kamLprInfo, 5);
                return elems.map((row) => {
                        return {
                            "kam_name": row.kam_name,
                            "type_name": row.type_name ? `<span class="table__holdings__square">${row.type_name}</span>` : '',
                        }
                    }
                );
            }
        },
        methods: {
            ...table_cam,
        }
    }

</script>

<style lang="less">

    .holding-detail__products-menu-element {
        font-style: normal;
        font-weight: 500;
        font-size: 12px;
        line-height: 16px;
        padding: 12px 20px;
        cursor: pointer;
        text-decoration: none;
        color: #1D2533;
    }

    .no-link {
        text-decoration: none;
        color: #1D2533;
    }

    .breadcrumb li a {
        color: black;
        text-decoration: none;
    }

    .main-info__grid {
        display: grid;
        grid-template-columns: 240px calc(100% - 240px);
        /*text-align: center;*/
        /*grid-column-gap: 10px;*/
        /*min-height: 600px;*/
    }

    .main-info__sidebar {
        /*height: 100%;*/
        /*width: 240px;*/
        border-right: 1px solid rgba(16, 24, 40, 0.1);

        scroll-behavior: smooth;
        height: calc(100vh - 215px);
    }

    .tt-sidebar div {
        /*display: inline-block;*/
    }

    .tt-body-text {
        padding: 30px;

        scroll-behavior: smooth;
        height: calc(100vh - 215px);
        overflow-x: hidden;
        overflow-y: scroll;
        /*height: calc(100% - 250px);*/
    }

    .main-info__two-column-50-50 {
        display: grid;
        grid-template-columns: 1fr 1fr;
        /*text-align: center;*/
        grid-column-gap: 10px;
        /*min-height: 600px;*/
    }

    .main-info__two-column-30-70 {
        display: grid;
        grid-template-columns: 1fr 3fr;
        /*text-align: center;*/
        grid-column-gap: 10px;
        /*min-height: 600px;*/
    }

    .main-info__caption {
        width: 30%;
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 16px;
        color: rgba(16, 24, 40, 0.5);
    }

    .main-info__text {
        width: 70%;
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: #101828;
    }


    .rkn-table {
        margin-top: 10px;
        border-collapse: collapse;
        width: 100%;

        tr {
            vertical-align: text-top;
            padding: 10px;
        }

        td {
            /*border: 1px solid red;*/
            padding: 10px;
        }
    }

</style>

