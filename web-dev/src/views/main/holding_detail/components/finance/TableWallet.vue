<template>
    <div>
        <ExpandBox :header="header"
                   :segment="segment"
                   :count="rowsLength"
                   v-on:on-search="onSearchText"
                   :show-total="showTotal"
                   :hide-setting="hideSetting"
                   :hide-search="hideSearch"
                   :holding-actual-dates="holdingActualDates">

            <base-data-table
                    :headers-prop="columns"
                    :items-prop="rows"
                    :options-prop="options"
                    :search-prop="searchText"
                    click-row-prop
                    @clickRow="onRowDoubleClick"
                    show-pagination-prop
            />

<!--            <vue-good-table-->
<!--                    :columns="columns"-->
<!--                    :rows="rows"-->
<!--                    @on-row-dblclick="onRowDoubleClick"-->
<!--                    styleClass="vgt-table striped"-->
<!--                    :pagination-options="{-->
<!--                        enabled: false,-->
<!--                        mode: 'records',-->
<!--                        nextLabel: 'след.',-->
<!--                        prevLabel: 'пред.',-->
<!--                        rowsPerPageLabel: 'Строк на стр.',-->
<!--                        perPageDropdown: [15, 30, 50],-->
<!--                        allLabel: 'Все',-->
<!--                    }"-->
<!--                    :sort-options="{-->
<!--                        enabled: sortEnabled,-->
<!--                    }"-->
<!--                    :search-options="{-->
<!--                        enabled: true,-->
<!--                        placeholder: 'Поиск',-->
<!--                        externalQuery: this.searchText,-->
<!--                }">-->

<!--                <template slot="table-row" slot-scope="props">-->
<!--                     <span v-if="props.column.field === 'name'">-->
<!--                        <div>{{ getName(props.row.name)}} <span class="table-wallet__measure">-->
<!--                            {{getMeasure(props.row.name)}} </span>-->
<!--                        </div>-->
<!--                    </span>-->
<!--                    <span v-else-if="(props.column.field === 'year2023') &&-->
<!--                        (props.formattedRow.name !== 'Комментарий к бюджету/проектам')">-->
<!--                        {{ numberWithSpaces(props.row.year2023, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="(props.column.field === 'year2024') &&-->
<!--                        (props.formattedRow.name !== 'Комментарий к бюджету/проектам')">-->
<!--                        {{ numberWithSpaces(props.row.year2024, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else>-->
<!--                        {{props.formattedRow[props.column.field]}}-->
<!--                    </span>-->
<!--                </template>-->

<!--                <div slot="emptystate">-->
<!--                    Нет данных-->
<!--                </div>-->
<!--            </vue-good-table>-->

        </ExpandBox>
    </div>
</template>

<script>
    import ExpandBox from "../../../../../components/layout/ExpandBox";
    import numerUtils from "@/js/utils/numer-utils";
    import BaseDataTable from "@/components/base/BaseDataTable";

    export default {
        name: "TableWallet",
        components: {
            BaseDataTable,
            ExpandBox,
        },
        props: {
            'rows': [Object, Array],
            'columns': [Object, Array],
            'header': {
                type: String,
                default: '',
            },
            'segment': {
                type: String,
                default: '',
            },
            'showTotal': {
                type: Boolean,
                default: true,
            },
            'sortEnabled': {
                type: Boolean,
                default: true,
            },
            'hideSetting':{
                type: Boolean,
                default: false,
            },
            'hideSearch':{
                type: Boolean,
                default: false,
            },
            'holdingActualDates':{
                type: Array,
                default: null,
            }
        },

        data() {
            return {
                searchText: '',
                options: {
                    sortBy: ["year2023"],
                    sortDesc: [true]
                },
            }
        },

        computed: {
            rowsLength(){
                if (this.rows === null){
                    return 0;
                }
                return this.rows.length;
            }
        },

        methods: {
            ...numerUtils,

            getName(val){
                if (val.lastIndexOf(',') === -1){
                    return val;
                }
                return val.substr(0, val.lastIndexOf(","));
            },

            getMeasure(val){
                if (val.lastIndexOf(',') === -1){
                    return '';
                }
                return '(' + val.substr(val.lastIndexOf(',') + 1, val.length).trim() + ')';
            },

            onRowDoubleClick(params) {
                //alert(JSON.stringify(params.row));
                // params.row - row object
                // params.pageIndex - index of this row on the current page.
                // params.selected - if selection is enabled this argument
                // indicates selected or not
                // params.event - click event
            },

            onSearchText(val) {
                this.searchText = val;
            }
        }
    }
</script>

<style lang="less">
    .table-wallet__measure{
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }
</style>