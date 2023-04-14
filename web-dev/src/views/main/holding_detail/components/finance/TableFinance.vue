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
                    :items-prop="items"
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
<!--                        // searchFn: myFuncInn-->
<!--                }">-->

<!--                <template slot="table-row" slot-scope="props">-->
<!--                    <span v-if="props.column.field === 'name'">-->
<!--                        <div>{{ getName(props.row.name)}} <span class="table-finance__measure"> {{getMeasure(props.row.name)}} </span></div>-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'year2021'">-->
<!--                        {{ numberWithSpaces(props.row.year2021, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'year2022'">-->
<!--                        {{ numberWithSpaces(props.row.year2022, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'year2023'">-->
<!--                        {{ numberWithSpaces(props.row.year2023, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else>-->
<!--                        {{props.formattedRow[props.column.field]}}-->
<!--                    </span>-->
<!--                </template>-->

<!--                <div slot="emptystate">-->
<!--                    Нет данных-->
<!--                </div>-->
<!--            </vue-good-table>-->

            <div class="table-finance__comment" v-if="projectDesc">
                Ключевые проекты: {{projectDesc}}
            </div>
        </ExpandBox>
    </div>
</template>

<script>
    import ExpandBox from "../../../../../components/layout/ExpandBox";
    import numerUtils from "@/js/utils/numer-utils";
    import BaseDataTable from "@/components/base/BaseDataTable";


    export default {
        name: "TableFinance",
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
            'projectDesc': {
                type: String,
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
                // searchInn: '',
                // isInnHFilter: true,
                options: {
                    sortBy: ["year2023"],
                    sortDesc: [true]
                },
            }
        },

        mounted(){
            // for (let index in this.columns) {
            //     alert(this.columns[index].field)
            //     alert(this.rows[this.columns[index].field])
            // }
        },
        computed: {
            rowsLength(){
                if (this.rows === null){
                    return 0;
                }
                return this.rows.length;
            },

            items() {
                return this.rows.map((row) => {
                        return {
                            "name": row.name,
                            "year2023": row.year2023 ? this.numberWithSpaces(row.year2023, 2) : null,
                            "year2022": row.year2022 ? this.numberWithSpaces(row.year2022, 2) : null,
                            "year2021": row.year2021 ? this.numberWithSpaces(row.year2021, 2) : null,
                            "vyruchkaTele22022": row.vyruchkaTele22022 ? this.numberWithSpaces(row.vyruchkaTele22022) : null,
                            "kamRtk": row.kamRtk,
                            "kamTsod": row.kamTsod,
                            "kamSolar": row.kamSolar,
                            "kamTele2": row.kamTele2,

                        }
                    }
                );
            },
        },

        methods: {
            ...numerUtils,

            getName(val){
                let arr = val.split(",");
                return arr[0];
            },

            getMeasure(val){
                let arr = val.split(",");
                return '(' + arr[1].trim() + ')';
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
            },

            customSortCompanies(items, sortBy, sortDesc) {
                const index = sortBy[0];
                return items.sort((x, y) => {
                    if (["year2023",
                        "year2022",
                        "year2021"].includes(index)) {
                        x = x[index] ? this.numberWithoutSpaces(x[index]) : x[index];
                        y = y[index] ? this.numberWithoutSpaces(y[index]) : y[index];

                        if (!sortDesc[0]) {
                            return x < y ? -1 : 1;
                        } else {
                            return y < x ? -1 : 1;
                        }
                    } else {
                        if (!sortDesc[0]) {
                            return x[index] < y[index] ? -1 : 1;
                        } else {
                            return y[index] < x[index] ? -1 : 1;
                        }
                    }
                })
            }
        }
    }
</script>

<style lang="less">

    .table-finance__download-link {
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 20px;
        color: #9466FF;
        cursor: pointer;
        text-decoration: underline;
        margin-left: 5px;
    }

    .table-finance__comment{
        padding: 10px 5px 5px;
        font-style: normal;
        font-weight: 200;
        font-size: 14px;
        line-height: 20px;
        color: #101828;
    }

    .rt-header-center {
        text-align: center;
    }

    .table-finance__measure{
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }
</style>