<template>
    <div class="table-holding-detail-list__style">
<!--                <div class="container-left">-->
<!--                    <input class="search-input-text1" type="text" v-model="searchTerm">-->
<!--                </div>-->
            <vue-good-table
                    :columns="columns"
                    :rows="rows"
                    @on-row-dblclick="onRowDoubleClick"
                    styleClass="vgt-table striped"
                    :pagination-options="{
                        enabled: true,
                        mode: 'records',
                        nextLabel: 'след.',
                        prevLabel: 'пред.',
                        rowsPerPageLabel: 'Строк на стр.',
                        perPageDropdown: [15, 30, 50],
                        allLabel: 'Все',
                    }"

                    :search-options="{
                        // enabled: true,
                        placeholder: 'Поиск',
                        // externalQuery: searchTerm
                    }"
            >
                <!--            <div slot="table-actions">-->
                <!--                <i class="bx" :class="isOpened ? 'bx-cog' : 'bx-cog'" id="btn" @click="isOpened = !isOpened"/>-->
                <!--            </div>-->

                <!--                <template slot="table-column" slot-scope="props">-->
                <!--                    <span v-if="props.column.label =='Отрасль'">-->
                <!--                        {{props.column.label}} <i class="fa fa-address-book"></i>-->
                <!--                    </span>-->
                <!--                    <span v-else>-->
                <!--                        {{props.column.label}}-->
                <!--                    </span>-->

                <!--                </template>-->

                <template slot="table-row" slot-scope="props">
                    <span v-if="props.column.field == 'otrasl'">
<!--                        <div style="margin:5px; background-color: #1F69FF; font-weight: bold; color: blue;">{{props.row.otrasl}}</div>-->
                        <div class="holdings__square">{{props.row.otrasl}}</div>
                    </span>
                    <span v-else>
                        {{props.formattedRow[props.column.field]}}
                    </span>
                </template>

            </vue-good-table>
    </div>
</template>

<script>
    import 'vue-good-table/dist/vue-good-table.css'
    import {VueGoodTable} from 'vue-good-table';

    export default {
        name: "MainList",
        components: {
            VueGoodTable
        },
        props: {
            'rows': Object,
        },

        data() {
            return {
                searchTerm: '',
                columns: [
                    {
                        label: 'Наименование ГК/Компании',
                        field: 'name',
                        //tooltip: 'Тут описание',
                        filterOptions: {
                            styleClass: 'class1', // class to be added to the parent th element
                            enabled: true, // enable filter for this column
                            placeholder: 'Наименование', // placeholder for filter input
                            filterValue: '', // initial populated value for this filter
                            filterDropdownItems: [], // dropdown (with selected values) instead of text input
                            filterFn: function(data, filterString) {
                                return data.toUpperCase().indexOf(filterString.toUpperCase()) !== -1;
                            }
                          //  trigger: 'enter', //only trigger on enter not on keyup
                        },
                    },
                    {
                        label: 'Индустрия',
                        field: 'nameOsnOkv',
                        filterOptions: {
                            styleClass: 'class1', // class to be added to the parent th element
                            enabled: true, // enable filter for this column
                            placeholder: 'Все', // placeholder for filter input
                            filterValue: '', // initial populated value for this filter
                            filterDropdownItems: this.filterNameOsnOkv(), // dropdown (with selected values) instead of text input
                            trigger: 'enter', //only trigger on enter not on keyup
                        },
                    },
                    {
                        label: 'Выручка 2020',
                        field: 'vir2020format',
                    },
                    {
                        label: 'Выручка 2021',
                        field: 'vir2021format',
                    },
                    {
                        label: 'Выручка группы 2020',
                        field: 'allVir2020format',
                    },
                    {
                        label: 'Выручка группы 2021',
                        field: 'allVir2021format',
                    },
                ],
            }
        },
        methods: {
            filterNameOsnOkv(){
                if (this.rows == null){
                    return [];
                }
                let set = new Set();
                for (let i = 0; i < this.rows.length; i++) {
                    set.add(this.rows[i].nameOsnOkv);
                }
                return Array.from(set).sort()
            },

            onRowDoubleClick(params) {
                alert(JSON.stringify(params.row));
                // params.row - row object
                // params.pageIndex - index of this row on the current page.
                // params.selected - if selection is enabled this argument
                // indicates selected or not
                // params.event - click event
            }
        }
    }
</script>

<style scoped>
    .holdings__square {
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 4px 8px;
        gap: 4px;
        width: fit-content;
        height: 24px;
        background: rgba(31, 105, 255, 0.12);
        border-radius: 3px;
        flex: none;
        order: 0;
        flex-grow: 0;
    }

    .search-input-text1 {
        background: white;
        font-style: normal;
        font-weight: normal;
        /*font-size: 20px;*/
        margin: 10px 10px;

        color: #757575;
        border-radius: 5px;
    }

    .table-holding-detail-list__style{
       padding-bottom: 5px;
    }

</style>