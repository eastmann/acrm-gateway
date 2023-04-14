<template>
    <div>
        <ExpandBox :header="header"
                   :segment="segment"
                   :count="rowsLength"
                   v-on:on-search="onSearchText"
                   :show-total="showTotal"
                   :hide-setting="hideSetting"
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
<!--                        // initialSortBy: [-->
<!--                        //     {field: 'vyruchka2022', type: 'desc'},-->
<!--                        //     {field: 'vyruchka2021', type: 'desc'},-->
<!--                        // ]-->
<!--                    }"-->
<!--                    :search-options="{-->
<!--                        enabled: true,-->
<!--                        placeholder: 'Поиск',-->
<!--                        externalQuery: this.searchText,-->
<!--                }">-->

<!--                <template slot="table-column" slot-scope="props">-->
<!--                    <span v-if="props.column.field ==='product_type'" class="rt-font">-->
<!--                        {{props.column.label}} <img src="@/assets/img/filter.png" v-on:click="showFilter"-->
<!--                                                    class="filter-logo">-->
<!--                    </span>-->
<!--                </template>-->

<!--                <template slot="table-row" slot-scope="props">-->
<!--                   <span v-if="props.column.field === 'product_type'">-->
<!--                       <div v-if="props.row.product_type" class="table__holdings__square">{{props.row.product_type}}</div>-->
<!--                   </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchka2021'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchka2021, 2)}}-->
<!--                    </span>-->
<!--                    <span v-else-if="props.column.field === 'vyruchka2022'">-->
<!--                        {{ numberWithSpaces(props.row.vyruchka2022, 2)}}-->
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
        name: "TableProducts",
        components: {
            BaseDataTable,
            ExpandBox,
        },
        props: {
            'rows': [Object, Array],
            // 'columns': [Object, Array],
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
            'holdingActualDates':{
                type: Array,
                default: null,
            },
        },

        data() {
            return {
                options: {
                    // sortBy: ["vyruchkaRtk2022"],
                    // sortDesc: [true]
                },
                searchText: '',
                isFilter: false,
                columns: [
                    {
                        text: 'Продукт',
                        value: 'productName',
                        thClass: 'rt-header',
                        tdClass: 'rt-col',
                        width: '20%',
                        sortFn(x, y) {
                            return x.localeCompare(y);
                        },
                    },
                    {
                        text: 'Тип продукта',
                        value: 'product_type',
                        thClass: 'rt-header',
                        tdClass: 'rt-col',
                        width: '12%',
                        sortFn(x, y) {
                            return x.localeCompare(y);
                        },
                        filterOptions: {
                            enabled: false,
                            placeholder: 'Все',
                            filterValue: '',
                            filterDropdownItems: this.filterProductType(),
                            trigger: 'enter',
                        },
                    },
                    {
                        text: 'Выручка по продукту 2021, млн. ₽',
                        value: 'vyruchka2021',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17px',
                        sortFn(x, y) {
                            let xNum = x;
                            let yNum = y;
                            if ((x !== null) && (x !== undefined)) {
                                xNum = Number(x.replace(',', '.'))
                            }
                            if ((y !== null) && (y !== undefined)) {
                                yNum = Number(y.replace(',', '.'))
                            }

                            return xNum < yNum ? -1 : xNum > yNum ? 1 : 0;
                        },
                    },
                    {
                        text: 'Выручка по продукту 2022, млн. ₽',
                        value: 'vyruchka2022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y) {
                            let xNum = x;
                            let yNum = y;
                            if ((x !== null) && (x !== undefined)) {
                                xNum = Number(x.replace(',', '.'))
                            }
                            if ((y !== null) && (y !== undefined)) {
                                yNum = Number(y.replace(',', '.'))
                            }

                            return xNum < yNum ? -1 : xNum > yNum ? 1 : 0;
                        },
                    },
                    {
                        text: 'Маржинальность по продукту 2021, %',
                        value: 'margin2021',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y) {
                            return x < y ? -1 : x > y ? 1 : 0;
                        },
                    },
                    {
                        text: 'Маржинальность по продукту 2022, %',
                        value: 'margin2022',
                        thClass: 'rt-header-center',
                        tdClass: 'rt-col-center-latter-spacing',
                        width: '17%',
                        sortFn(x, y) {
                            return x < y ? -1 : x > y ? 1 : 0;
                        },
                    },
                ],

            }
        },

        watch: {
            isFilter() {
                this.changeShowFilterProduct('product_type', this.isFilter);
            }
        },

        computed: {
            items() {
                return this.rows.map((row) => {
                        return {
                            "productName": row.productName,
                            "product_type": row.product_type ? `<span class="table__holdings__square">${row.product_type}</span>` : "",
                            "vyruchka2022": row.vyruchka2022 ? this.numberWithSpaces(row.vyruchka2022) : null,
                            "vyruchka2021": row.vyruchka2021 ? this.numberWithSpaces(row.vyruchka2021) : null,
                            "margin2022": row.margin2022 ? this.numberWithSpaces(row.margin2022) : null,
                            "margin2021": row.margin2021 ? this.numberWithSpaces(row.margin2021) : null,
                        }
                    }
                );
            },

            rowsLength(){
                if (this.rows === null){
                    return 0;
                }
                return this.rows.length;
            }
        },

        methods: {
            ...numerUtils,

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

            showFilter() {
                this.isFilter = !this.isFilter;
            },

            changeShowFilterProduct(field, newFilter) {
                let found = this.columns.find((c) => {
                    return c.field == field
                });
                if (found) {
                    if (found.hasOwnProperty("filterOptions")) {
                        if (found.filterOptions.hasOwnProperty("filterValue")) {
                            found.filterOptions.enabled = newFilter;
                        }
                    }
                }
            },

            changeFilter(field, newFilter) {
                let found = this.columns.find((c) => {
                    return c.field == field
                });
                if (found) {
                    if (found.hasOwnProperty("filterOptions")) {
                        if (found.filterOptions.hasOwnProperty("filterValue")) {
                            found.filterOptions.filterValue = newFilter;
                        }
                    }
                }
            },

            filterProductType() {
                if (this.rows == null) {
                    return [];
                }
                let set = new Set();
                for (let i = 0; i < this.rows.length; i++) {
                    set.add(this.rows[i].product_type);
                }
                return Array.from(set).sort()
            },

        }
    }
</script>

<style lang="less">

</style>