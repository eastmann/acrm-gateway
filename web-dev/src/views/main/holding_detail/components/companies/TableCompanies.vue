<template>
    <div>
        <ExpandBox :header="header"
                   :segment="segment"
                   :count="rowsLength"
                   v-on:on-search="onSearchText"
                   v-on:on-click-setting="onShowSetting"
                   :show-total="showTotal"
                   :expanded="true"
                   :enabled="false"
                   :show-toggler="true"
                   v-on:on-toggle="onToggle"
                   :holding-actual-dates="holdingActualDates">

            <template v-slot:setting>
                <setting :columns="headers"
                         :show-setting="showSetting"
                         v-on:on-click-setting="onShowSetting()"/>
            </template>

            <base-data-table
                    :headers-prop="headers"
                    :items-prop="items"
                    :options-prop="options"
                    :search-prop="searchText"
                    click-row-prop
                    @clickRow="onRowClick"
                    show-pagination-prop
                    :custom-sort-prop="customSortCompanies"
            />

<!--            <vue-good-table-->
<!--                    :columns="columns"-->
<!--                    :rows="rows"-->
<!--                    @on-row-click="onRowClick"-->
<!--                    styleClass="vgt-table striped"-->
<!--                    :pagination-options="{-->
<!--                        enabled: true,-->
<!--                        // mode: 'records',-->
<!--                        // nextLabel: 'след.',-->
<!--                        // prevLabel: 'пред.',-->
<!--                        // rowsPerPageLabel: 'Строк на стр.',-->
<!--                        // perPageDropdown: [15, 30, 50],-->
<!--                        // allLabel: 'Все',-->
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

<!--                <template slot="pagination-bottom" slot-scope="props">-->
<!--                    <custom-pagination-->
<!--                            :total="props.total"-->
<!--                            :page-changed="props.pageChanged"-->
<!--                            :per-page-changed="props.perPageChanged"-->
<!--                            :pag-props="props"-->
<!--                    >-->
<!--                    </custom-pagination>-->
<!--                </template>-->

<!--                <template slot="table-column" slot-scope="props">-->
<!--                    <span class="v-left" v-if="props.column.field ==='vyruchkaRtk2022'">-->
<!--                        <div>Выручка РТК</div>-->
<!--                        <span>2022, млн. ₽</span>-->
<!--                    </span>-->
<!--                    <span class="v-left" v-else-if="props.column.field ==='vyruchkaTsod2022'">-->
<!--                        <div>Выручка ЦОД</div>-->
<!--                        <span>2022, млн. ₽</span>-->
<!--                    </span>-->
<!--                    <span class="v-left" v-else-if="props.column.field ==='vyruchkaSolar2022'">-->
<!--                        <div>Выручка Солар</div>-->
<!--                        <span>2022, млн. ₽</span>-->
<!--                    </span>-->
<!--                    <span class="v-left" v-else-if="props.column.field ==='vyruchkaTele22022'">-->
<!--                        <div>Выручка Теле2</div>-->
<!--                        <span>2022, млн. ₽</span>-->
<!--                    </span>-->
<!--                    <span v-else class="rt-font">-->
<!--                        {{props.column.label}}-->
<!--                    </span>-->
<!--                </template>-->

<!--                <template slot="table-row" slot-scope="props">-->
<!--                    <span v-if="props.column.field === 'nameOrg' && props.row.isHolding">-->
<!--                        <span class="holdings__text">Холдинг {{props.row.nameOrg}}</span>-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchkaRtk2022'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchkaRtk2022, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchkaTsod2022'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchkaTsod2022, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchkaSolar2022'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchkaSolar2022, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchkaTele22022'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchkaTele22022, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'kamRtk'">-->
<!--                        <div v-for="(item, index) in splitString(props.row.kamRtk)" :key="index">-->
<!--                            {{item }}-->
<!--                        </div>-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'kamTsod'">-->
<!--                        <div v-for="(item, index) in splitString(props.row.kamTsod)" :key="index">-->
<!--                            {{item }}-->
<!--                        </div>-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'kamSolar'">-->
<!--                        <div v-for="(item, index) in splitString(props.row.kamSolar)" :key="index">-->
<!--                            {{item }}-->
<!--                        </div>-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'kamTele2'">-->
<!--                        <div v-for="(item, index) in splitString(props.row.kamTele2)" :key="index">-->
<!--                            {{item }}-->
<!--                        </div>-->
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
    import 'vue-good-table/dist/vue-good-table.css'
    import {VueGoodTable} from 'vue-good-table';
    import ExpandBox from "../../../../../components/layout/ExpandBox";
    import numerUtils from "@/js/utils/numer-utils";
    import Setting from "@/components/setting/Setting";
    import CustomPagination from "@/components/pagination/CustomPagination";
    import BaseDataTable from "@/components/base/BaseDataTable";
    import table_companies from "@/js/table/table-companies";

    export default {
        name: "TableCompanies",
        components: {
            BaseDataTable,
            // CustomPagination,
            Setting,
            ExpandBox,
            // VueGoodTable
        },
        props: {
            'rows': [Object, Array],
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
            'holdingActualDates': {
                type: Array,
                default: null,
            }
        },

        data() {
            return {
                showSetting: false,
                searchText: '',
                headers: this.creteHeadersVir(),

                options: {
                    sortBy: ["vyruchkaRtk2022"],
                    sortDesc: [true]
                },

                columns: [
                    {
                        label: 'Наименование',
                        field: 'nameOrg',
                        thClass: 'rt-header',
                        tdClass: 'rt-col-cursor',
                        width: '32%',
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'Выручка РТК 2022, млн. ₽',
                        field: 'vyruchkaRtk2022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'Выручка ЦОД 2022, млн. ₽',
                        field: 'vyruchkaTsod2022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'Выручка Солар 2022, млн. ₽',
                        field: 'vyruchkaSolar2022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'Выручка Теле2 2022, млн. ₽',
                        field: 'vyruchkaTele22022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'КАМ РТК',
                        field: 'kamRtk',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-mini',
                        width: '17%',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'КАМ ЦОД',
                        field: 'kamTsod',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-mini',
                        width: '17%',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'КАМ Солар',
                        field: 'kamSolar',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-mini',
                        width: '17%',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'КАМ Теле2',
                        field: 'kamTele2',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-mini',
                        width: '17%',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'ИНН',
                        field: 'inn',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                    {
                        label: 'Холдинг',
                        field: 'isHolding',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        hidden: true,
                        sortFn(x, y, col, rowX, rowY) {
                            if ((rowX.isHolding === true) ||
                                (rowY.isHolding === true)) {
                                return false;
                            }
                            return x < y ? -1 : x > y ? 1 : 0;
                        }
                    },
                ],
            }
        },

        mounted() {
            // for (let index in this.columns) {
            //     alert(this.columns[index].field)
            //     alert(this.rows[this.columns[index].field])
            // }
        },

        computed: {
            items() {
                return this.rows.map((row) => {
                        return {
                            "id": row.id,
                            "nameOrg": row.isHolding ?
                                `<span class="holdings__text">Холдинг ${row.nameOrg} (Total) </span>`
                                : `<span class="no-holdings__text"> ${row.nameOrg} </span>`,
                            "vyruchkaRtk2022": row.vyruchkaRtk2022 ? this.numberWithSpaces(row.vyruchkaRtk2022) : null,
                            "vyruchkaTsod2022": row.vyruchkaTsod2022 ? this.numberWithSpaces(row.vyruchkaTsod2022) : null,
                            "vyruchkaSolar2022": row.vyruchkaSolar2022 ? this.numberWithSpaces(row.vyruchkaSolar2022) : null,
                            "vyruchkaTele22022": row.vyruchkaTele22022 ? this.numberWithSpaces(row.vyruchkaTele22022) : null,
                            "kamRtk": this.splitStrusture(row.kamRtk),
                            "kamTsod": this.splitStrusture(row.kamTsod),
                            "kamSolar": this.splitStrusture(row.kamSolar),
                            "kamTele2": this.splitStrusture(row.kamTele2),
                            "isHolding": row.isHolding,
                        }
                    }
                );
            },

            rowsLength() {
                if (this.rows === null) {
                    return 0;
                }
                return this.rows.length;
            },

            countShow() {
                // let cols = 0;
                // for (let index in this.columns) {
                //     if (!this.columns[index].hidden){
                //         cols += 1;
                //     }
                // }
                // if (cols >= 4){
                //     return '120px'
                // }
                return '120px';//(75 / (cols - 1)) + '%';
            }
        },

        methods: {
            ...numerUtils,
            ...table_companies,

            splitStrusture(val){
                let elems = this.splitString(val);
                let res = '';
                for (let i = 0; i < elems.length; i++) {
                    res += '<div>' + elems[i] + '</div>';
                }
                return res;
            },
            onShowSetting() {
                this.showSetting = !this.showSetting;
                //this.$refs['vuetable'].$forceUpdate()
            },

            onRowClick(params) {
                let path = "/holding-detail/id/" + params.id;
                this.$router.push(path);
                //alert(JSON.stringify(params.row));
                // params.row - row object
                // params.pageIndex - index of this row on the current page.
                // params.selected - if selection is enabled this argument
                // indicates selected or not
                // params.event - click event
            },

            splitString(str) {
                if ((str === null) || (str === undefined)) {
                    return '';
                }
                let arrayOfStrings = str.split(',');
                return arrayOfStrings
            },

            onSearchText(val) {
                this.searchText = val;
            },

            onToggle(val) {
                this.headers = val ? this.creteHeadersKam() : this.creteHeadersVir();
            },

            customSortCompanies(items, sortBy, sortDesc) {
                const index = sortBy[0];
                return items.sort((x, y) => {
                    if ((x.isHolding === true) ||
                        (y.isHolding === true)) {
                        return false;
                    }
                    if (["vyruchkaRtk2022",
                        "vyruchkaTsod2022",
                        "vyruchkaSolar2022",
                        "vyruchkaTele22022"].includes(index)) {
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

    .holdings__text {
        font-weight: bold;
    }

    .no-holdings__text {
        font-weight: normal;
    }

    .v-left {
        text-align: left;
        display: inline-block;
    }

</style>