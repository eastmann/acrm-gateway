<template>
    <div class="table-holding-list__style">

        <ExpandBox header="Список холдингов"
                   :enabled="false"
                   :count="rowsLength"
                   :expanded="true"
                   v-on:on-search="onSearchName"
                   v-on:on-click-setting="onShowSetting()"
                   :show-toggler="false"
                   :hide-search="true">

            <template v-slot:setting>
                <setting :columns="headers"
                         :show-setting="showSetting"
                         v-on:on-click-setting="onShowSetting()"/>
            </template>

            <template v-slot:search-box>
                <BusinessSearchBoxNew caption="Компания:" class="business-search-box"
                                      :hide-search-button="true"
                                      :style-registration="true"
                                      v-on:suggestion-selected="loadRegInnBySuggestion"/>
            </template>

            <base-expanded-data-table
                    :headers-prop="headers"
                    :items-prop="items"
                    :options-prop="options"
                    :show-expand-prop="true"
                    :expanded-prop="expanded"
                    :item-key-prop="itemKey"
                    :search-prop="searchText"
                    show-pagination-prop
                    click-row-prop
                    @itemExpanded="expand"
                    @clickRow="onRowClick"
                    :custom-sort-prop="customSort"
            />

            <!--            <vue-good-table-->
            <!--                    :columns="columns"-->
            <!--                    :rows="rows"-->
            <!--                    :sort-options="{-->
            <!--                        enabled: true,-->
            <!--                        initialSortBy: [-->
            <!--                            {field: 'allVir2021', type: 'desc'},-->
            <!--                            {field: 'allVir2020', type: 'desc'},-->
            <!--                            {field: 'name', type: 'asc'},-->
            <!--                        ]-->
            <!--                    }"-->
            <!--                    @on-row-click="onRowClick"-->
            <!--                    styleClass="vgt-table striped"-->
            <!--                    :pagination-options="{enabled: true}"-->
            <!--                    :search-options="{-->
            <!--                    enabled: true,-->
            <!--                    placeholder: 'Поиск',-->
            <!--                    externalQuery: this.searchText,-->
            <!--                    searchFn: myFunc-->
            <!--                }"-->
            <!--            >-->
            <!--                <template slot="pagination-bottom" slot-scope="props">-->
            <!--                    <custom-pagination-->
            <!--                            :total="props.total"-->
            <!--                            :page-changed="props.pageChanged"-->
            <!--                            :per-page-changed="props.perPageChanged"-->
            <!--                            :pag-props="props"-->
            <!--                    >-->
            <!--                    </custom-pagination>-->
            <!--                </template>-->

            <!--                <template slot="table-row" slot-scope="props">-->
            <!--                   <span v-if="props.column.field === 'industry'">-->
            <!--                       <div class="table__holdings__square">{{props.row.industry}}</div>-->
            <!--                   </span>-->
            <!--                    <span v-else-if="props.column.field === 'allVir2020'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.allVir2020, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else-if="props.column.field === 'allVir2021'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.allVir2021, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else-if="props.column.field === 'virRtk2021'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.virRtk2021, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else-if="props.column.field === 'virRtk2022'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.virRtk2022, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else-if="props.column.field === 'virTsod2021'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.virTsod2021, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else-if="props.column.field === 'virTsod2022'" class="rt-font">-->
            <!--                        {{numberWithSpaces(props.row.virTsod2022, 0)}}-->
            <!--                    </span>-->
            <!--                    <span v-else class="rt-font">-->
            <!--                        {{props.formattedRow[props.column.field]}}-->
            <!--                    </span>-->
            <!--                </template>-->

            <!--                <template slot="table-column" slot-scope="props">-->
            <!--                    <span v-if="props.column.field ==='industry'" class="rt-font">-->
            <!--                        {{props.column.label}} <img src="@/assets/img/filter.png" v-on:click="showFilter"-->
            <!--                                                    class="filter-logo">-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='allVir2020'">-->
            <!--                        <div>Выручка</div>-->
            <!--                        <span>2020 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='allVir2021'">-->
            <!--                        <div>Выручка</div>-->
            <!--                        <span>2021 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='virRtk2021'">-->
            <!--                        <div>Выручка РТК</div>-->
            <!--                        <span>2021 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='virRtk2022'">-->
            <!--                        <div>Выручка РТК</div>-->
            <!--                        <span>4 кв. 2022 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='virTsod2021'">-->
            <!--                        <div>Выручка ЦОД</div>-->
            <!--                        <span>2021 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span class="v-left" v-else-if="props.column.field ==='virTsod2022'">-->
            <!--                        <div>Выручка ЦОД</div>-->
            <!--                        <span>2022 (млн. ₽)</span>-->
            <!--                    </span>-->
            <!--                    <span v-else class="rt-font">-->
            <!--                        {{props.column.label}}-->
            <!--                    </span>-->
            <!--                </template>-->

            <!--                &lt;!&ndash;            <template slot="column-filter" slot-scope="props">&ndash;&gt;-->
            <!--                &lt;!&ndash;                <my-custom-filter&ndash;&gt;-->
            <!--                &lt;!&ndash;                        v-if="props.column.filterOptions.customFilter"&ndash;&gt;-->
            <!--                &lt;!&ndash;                        @input="handleCustomFilter"/>&ndash;&gt;-->
            <!--                &lt;!&ndash;            </template>&ndash;&gt;-->

            <!--            </vue-good-table>-->
        </ExpandBox>
    </div>

</template>

<script>
    import 'vue-good-table/dist/vue-good-table.css'
    import {VueGoodTable} from 'vue-good-table';
    import numerUtils from "@/js/utils/numer-utils";
    import ExpandBox from "@/components/layout/ExpandBox";
    import Setting from "@/components/setting/Setting";
    import CustomPagination from "@/components/pagination/CustomPagination";
    import BaseExpandedDataTable from "@/components/base/BaseExpandedDataTable";
    import BusinessSearchBoxNew from "@/components/inputs/search_box/BusinessSearchBoxNew";

    export default {
        name: "TableHoldingList",
        components: {
            BaseExpandedDataTable,
            // CustomPagination,
            BusinessSearchBoxNew,
            Setting,
            ExpandBox,
            // VueGoodTable
        },
        props: {
            'rows': [Object, Array],
            'searchName': String,
        },

        watch: {
            searchName() {
                this.changeFilter('name', this.searchName)
            },

            isFilter() {
                this.changeShowFilter('industry', this.isFilter);
                // this.changeShowFilter('sector', this.isFilter);
            }
        },

        data() {
            return {
                showSetting: false,
                searchText: "",
                isFilter: false,
                expanded: {},
                headers: [
                    {
                        text: "",
                        value: "main",
                        subHeaders: [
                            {
                                value: "name",
                                text: "Наименование ГК/Компании",
                                filterable: true,
                                hidden: false
                            },
                            {
                                value: "industry",
                                text: "Индустрия",
                                filterable: true,
                                hidden: false,
                                // thClass - классы для шапки, tdClass - классы для строк
                            }
                        ]
                    },
                    {
                        text: "Выручка ГК по ФНС, млн. ₽",
                        value: "allVir",
                        subHeaders: [
                            {
                                value: "allVir2020",
                                text: "<span>2020</span>", // можно задавать html
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                            {
                                value: "allVir2021",
                                text: "<span>2021</span>",
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                            {
                                value: "allVir2022",
                                text: "<span>2022</span>", // можно задавать html
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                        ]
                    },
                    {
                        text: "Выручка группы компаний РТК, млн. ₽",
                        value: "allVirGK",
                        subHeaders: [
                            {
                                value: "allVirGK2020",
                                text: "<span>2020</span>", // можно задавать html
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                            {
                                value: "allVirGK2021",
                                text: "<span>2021</span>",
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                            {
                                value: "allVirGK2022",
                                text: "<span>2022</span>", // можно задавать html
                                width: '10%',
                                sortable: true,
                                filterable: true,
                                hidden: false
                            },
                        ]
                    },
                    // {
                    //   text: "Выручка РТК",
                    //   value: "virRtk",
                    //   subHeaders: [{
                    //     value: "virRtk2021",
                    //     text: "<span>2021 (млн. ₽)</span>",
                    //     width: '120px',
                    //     sortable: true,
                    //     filterable: true,
                    //     hidden: false
                    //   }, {
                    //       value: "virRtk2022",
                    //       text: "<span>4 кв. 2022 (млн. ₽)</span>",
                    //       width: '120px',
                    //       sortable: true,
                    //       filterable: true,
                    //       hidden: false
                    //   }]
                    // }, {
                    //   text: "Выручка ЦОД",
                    //   value: "virTsod",
                    //   subHeaders: [{
                    //     value: "virTsod2021",
                    //     text: "<span>2021 (млн. ₽)</span>",
                    //     width: '120px',
                    //     sortable: true,
                    //     filterable: true,
                    //     hidden: false
                    //   }, {
                    //       value: "virTsod2022",
                    //       text: "<span>2022 (млн. ₽)</span>",
                    //       width: '120px',
                    //       sortable: true,
                    //       filterable: true,
                    //       hidden: false
                    //   }]
                    //   }
                ],
                options: {
                    sortBy: ["allVir2021"],
                    sortDesc: [true]
                },
                itemKey: "name"

                // columns: [
                //   {
                //     label: 'Наименование ГК/Компании',
                //     field: 'name',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-cursor',
                //     width: '250px',
                //     //tooltip: 'Тут описание',
                //     sortFn(x, y) {
                //       return x.localeCompare(y);
                //     },
                //     filterOptions: {
                //       styleClass: 'class1',
                //       enabled: false,
                //
                //       placeholder: 'Наименование',
                //       filterValue: "",
                //       filterDropdownItems: [],
                //       filterFn: function (data, filterString) {
                //         return data.toUpperCase().indexOf(filterString.toUpperCase()) !== -1;
                //       },
                //     },
                //   },
                //   {
                //     label: 'Индустрия',
                //     field: 'industry',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col',
                //     width: '270px',
                //     sortFn(x, y) {
                //       return x.localeCompare(y);
                //     },
                //     filterOptions: {
                //       enabled: false,
                //       placeholder: 'Все',
                //       filterValue: '',
                //       filterDropdownItems: this.filterIndustry(),
                //       trigger: 'enter',
                //     },
                //   },
                //   {
                //     label: 'Выручка ГК 2021 (млн. ₽)',
                //     field: 'allVir2021',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //   {
                //     label: 'Выручка ГК 2020 (млн. ₽)',
                //     field: 'allVir2020',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //   {
                //     label: 'Выручка РТК 2022 (млн. ₽)',
                //     field: 'virRtk2022',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //   {
                //     label: 'Выручка РТК 2021 (млн. ₽)',
                //     field: 'virRtk2021',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //   {
                //     label: 'Выручка ЦОД 2022 (млн. ₽)',
                //     field: 'virTsod2022',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //   {
                //     label: 'Выручка ЦОД 2021 (млн. ₽)',
                //     field: 'virTsod2021',
                //     width: '150px',
                //     thClass: 'rt-header',
                //     tdClass: 'rt-col-center',
                //     sortFn(x, y) {
                //       return x < y ? -1 : x > y ? 1 : 0;
                //     },
                //   },
                //
                // ],
            }
        },

        computed: {
            rowsLength() {
                if (this.rows === null) {
                    return 0;
                }
                return this.rows.length;
            },
            items() {
                return this.rows.map((row) => {
                        return {
                            "hid": row.hid,
                            "name": row.name,
                            "industry": `<span class="table__holdings__square">${row.industry}</span>`,
                            "allVir2020": row.allVir2020 ? this.numberWithSpaces(row.allVir2020) : null,
                            "allVir2021": row.allVir2021 ? this.numberWithSpaces(row.allVir2021) : null,
                            "virRtk2021": row.virRtk2021 ? this.numberWithSpaces(row.virRtk2021) : null,
                            "virRtk2022": row.virRtk2022 ? this.numberWithSpaces(row.virRtk2022) : null,
                            "virTsod2021": row.virTsod2021 ? this.numberWithSpaces(row.virTsod2021) : null,
                            "virTsod2022": row.virTsod2022 ? this.numberWithSpaces(row.virTsod2022) : null
                        }
                    }
                );
            }
        },

        methods: {
            ...numerUtils,

            loadRegInnBySuggestion(suggestion) {
               let path = "/holding-detail/id/" + suggestion.id;
               if (suggestion.isHolding){
                   path = "/holding-detail/hid/" + suggestion.hid;
               }

               this.$router.push(path);
            },

            expand(data) {
                // Добавить получение данных по внешнему API
                let headers = [
                    {
                        value: "pre",
                        text: "",
                        subHeaders: [
                            {value: "nothing", text: "", width: "80px"}
                        ]
                    },
                    {
                        value: "main",
                        text: "",
                        subHeaders: [
                            {value: "dzo", text: "ДЗО", sortable: true, sort: (x, y) => x < y ? -1 : x > y ? 1 : 0}
                        ]
                    }, {
                        value: "vir",
                        text: "Выручка, млн. ₽",
                        subHeaders: [
                            {value: "virYear2020", text: "2020", width: "10%"},
                            {value: "virYear2021", text: "2021", width: "10%"},
                            {value: "virYear2022", text: "2022", width: "10%"},
                        ]
                    }, {
                        value: "plan",
                        text: "План, млн. ₽",
                        subHeaders: [
                            {value: "planYear2021", text: "2021", width: "10%"},
                            {value: "planYear2022", text: "2022", width: "10%"},
                            {value: "planYear2023", text: "2023", width: "10%"}
                        ]
                    }
                ];

                let items = [
                    {
                        dzo: "РТК",
                        virYear2022: data.virRtk2022,
                        virYear2021: data.virRtk2021,
                    },
                    {
                        dzo: "ЦОД",
                        virYear2022: data.virTsod2022,
                        virYear2021: data.virTsod2021,
                    },
                    {
                        dzo: "Солар",
                        virYear2022: "",
                        virYear2021: "",
                    },
                    {
                        dzo: "Теле2",
                        virYear2022: "",
                        virYear2021: "",
                    }
                ];

                // this.expanded.customSort = (items, sortBy, sortDesc) => {
                // ...
                // return items;
                //}

                this.expanded[data[this.itemKey]] = { headers: headers, items: items };
            },

            onShowSetting() {
                this.showSetting = !this.showSetting;
            }
            ,

            onSearchName(val) {
                this.searchText = val;
            }
            ,

            myFunc(row, col, cellValue, searchTerm) {
                if (col.field !== "name") {
                    return false
                }
                return cellValue.toUpperCase().indexOf(this.searchText.toUpperCase()) !== -1;
            }
            ,

            filterIndustry() {
                if (this.rows == null) {
                    return [];
                }
                let set = new Set();
                for (let i = 0; i < this.rows.length; i++) {
                    set.add(this.rows[i].industry);
                }
                return Array.from(set).sort()
            }
            ,

            filterSector() {
                if (this.rows == null) {
                    return [];
                }
                let set = new Set();
                for (let i = 0; i < this.rows.length; i++) {
                    set.add(this.rows[i].sector);
                }
                return Array.from(set).sort()
            }
            ,

            onRowClick(params) {
                let path = "/holding-detail/hid/" + params.hid;
                this.$router.push(path);
                // params.row - row object
                // params.pageIndex - index of this row on the current page.
                // params.selected - if selection is enabled this argument
                // indicates selected or not
                // params.event - click event
            }
            ,

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
            }
            ,

            showFilter() {
                this.isFilter = !this.isFilter;
            }
            ,

            changeShowFilter(field, newFilter) {
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
            }
            ,

            customSort(items, sortBy, sortDesc) {
                const index = sortBy[0];
                return items.sort((x, y) => {
                    if (["allVir2022",
                        "allVir2021",
                        "allVir2020",
                        "allVirGK2022",
                        "allVirGK2021",
                        "allVirGK2020"].includes(index)) {
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
    .table__holdings__square {
        align-items: center;
        padding: 4px 8px;
        background: rgba(31, 105, 255, 0.12);
        border-radius: 3px;
        width: fit-content;
    }

    .table-holding-list__style {
        padding-bottom: 5px;
    }

    .search-input-text1 {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 6px 12px 6px 8px;
        gap: 8px;
        width: 200px;
        height: 32px;
        background: rgba(16, 24, 40, 0.05);
        border-radius: 8px;
        flex: none;
        order: 1;
        align-self: stretch;
        flex-grow: 0;
    }

    /*Переопределение свойств Good-Table*/
    table.vgt-table td {
        border-style: none;
    }

    table.vgt-table th {
    }

    .vgt-table {
        font-style: normal !important;
        font-weight: 100 !important;
        font-size: 14px !important;
        color: #101828 !important;

        &.striped tbody tr:nth-of-type(odd) {
            background: linear-gradient(0deg, rgba(0, 66, 237, 0.025), rgba(0, 66, 237, 0.025)) !important;
        }

        th.sortable {

        }

        th.sortable button {
            /*position: relative !important;*/
            width: 20px !important;
            height: 20px !important;
            vertical-align: top;
            position: sticky;

        }
    }

    .rt-header {
        /*font-style: normal !important;*/
        /*font-weight: 500 !important;*/
        /*font-size: 14px !important;*/
        /*color: rgba(16, 24, 40, 0.5) !important;*/
        /*!*text-align: center;*!*/
        /*padding-right: 3px !important;*/
        /*padding-left: 10px !important;*/
        /*vertical-align: top !important;*/
        /*margin: 0 !important;*/
        /*vertical-align: center;*/
    }

    .rt-cursor {
        /*font-family: 'Rostelecom Basis' !important;*/
        font-style: normal !important;
        font-weight: bold !important;
        font-size: 14px !important;
        line-height: 20px !important;
        color: #101828 !important;
        cursor: pointer !important;

        &:hover {
            /*font-weight: bold !important;*/
            color: #9466FF !important;
        }
    }

    .rt-col {
        font-style: normal !important;
        font-weight: 400 !important;
        font-size: 14px !important;
        line-height: 20px !important;
        color: #101828 !important;
    }

    .rt-col-mini {
        font-style: normal !important;
        font-weight: 400 !important;
        font-size: 12px !important;
        line-height: 20px !important;
        color: #101828 !important;

        text-align: center;
    }

    .rt-col-cursor {
        font-style: normal !important;
        font-weight: 400 !important;
        font-size: 14px !important;
        line-height: 20px !important;
        color: #101828 !important;
        cursor: pointer !important;

        &:hover {
            /*font-weight: bold !important;*/
            color: #9466FF !important;
        }
    }

    .class1 {
        /*font-family: 'Rostelecom Basis' !important;*/
        /*font-style: normal !important;*/
        /*font-weight: 100 !important;*/
        /*font-size: 14px !important;*/
        /*color: #101828 !important;*/
        /*width: 300px !important;*/
        /*height: 400px !important;*/
        /*text-align: center !important;*/
        /*border-style: solid;*/
    }

    .filter-logo {
        width: 12px;
        height: 12px;
        cursor: pointer;
    }

    .table-summ {
        text-align: right;
        margin-right: 100px;
    }

    .v-left {
        text-align: left;
        display: inline-block;
    }

    .business-search-box {
        padding-top: 15px;
        padding-right: 20px;
        width: 400px;
    }
</style>