<template>
    <div>
        <ExpandBox :header="header"
                   :segment="segment"
                   :count="rowsLength"
                   v-on:on-search="onSearchText"
                   :show-total="showTotal"
                   :holding-actual-dates="holdingActualDates">
            <vue-good-table
                    :columns="columns"
                    :rows="rows"
                    @on-row-dblclick="onRowDoubleClick"
                    styleClass="vgt-table striped"
                    :pagination-options="{
                        enabled: false,
                        mode: 'records',
                        nextLabel: 'след.',
                        prevLabel: 'пред.',
                        rowsPerPageLabel: 'Строк на стр.',
                        perPageDropdown: [15, 30, 50],
                        allLabel: 'Все',
                    }"
                    :sort-options="{
                        enabled: sortEnabled,
                    }"
                    :search-options="{
                        enabled: true,
                        placeholder: 'Поиск',
                        externalQuery: this.searchText,
                }">
                <template slot="table-row" slot-scope="props">
                    <span v-if="props.column.field === 'name'">
                        <div v-on:click="onPdfTestDownload('Справка по клиенту.docx')">
                            <img v-if="props.row.format === 'DOC'" src="@/assets/img/word.png" class="table-reports__download-logo">
                            <img v-if="props.row.format === 'EXCEL'" src="@/assets/img/excel.png" class="table-reports__download-logo">
                            <img v-if="props.row.format === 'PDF'" src="@/assets/img/pdf.png" class="table-reports__download-logo">
                            <span class="table-reports__text">{{props.row.name}}</span>
                        </div>
                    </span>
                    <span v-else-if="props.column.field === 'link'">
<!--                        <div style="margin:5px; background-color: #1F69FF; font-weight: bold; color: blue;">{{props.row.otrasl}}</div>-->
                        <div v-on:click="onPdfTestDownload('Справка по клиенту.docx')">
                            <img src="@/assets/img/download.png" class="table-reports__download-logo">
                            <span class="table-reports__download-link">
                                Скачать
                                <!--                            {{props.row.link}}-->
                            </span>
                       </div>
                    </span>
                    <span v-else>
                        {{props.formattedRow[props.column.field]}}
                    </span>
                </template>

                <div slot="emptystate">
                    Нет данных
                </div>
            </vue-good-table>

        </ExpandBox>
    </div>
</template>

<script>
    import 'vue-good-table/dist/vue-good-table.css'
    import {VueGoodTable} from 'vue-good-table';
    import ExpandBox from "@/components/layout/ExpandBox";
    import axios from "axios";

    export default {
        name: "TableReports",
        components: {
            ExpandBox,
            VueGoodTable
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
            'holdingActualDates': {
                type: Array,
                default: null,
            },
        },

        data() {
            return {
                searchText: '',
                filePlugin: require('../../../../../assets/files/reportClient.png')
            }
        },

        computed: {
            rowsLength() {
                if (this.rows === null) {
                    return 0;
                }
                return this.rows.length;
            }
        },

        methods: {
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

            onPdfTestDownload(fileName) {
                axios({
                    url: this.filePlugin,
                    method: 'GET',
                    responseType: 'blob',
                }).then((response) => {
                    var fileURL = window.URL.createObjectURL(new Blob([response.data]));
                    var fileLink = document.createElement('a');

                    fileLink.href = fileURL;
                    fileLink.setAttribute('download', fileName);
                    document.body.appendChild(fileLink);

                    fileLink.click();
                });
            }
        }
    }
</script>

<style lang="less">
    .table-reports__download-link {
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 20px;
        color: #9466FF;
        cursor: pointer;
    }

    .table-reports__download-logo {
        width: 20px;
        height: 18px;
        cursor: pointer;
        margin-right: 6px;
    }

    .table-reports__text {
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 20px;
        color: #101828;
        cursor: pointer;

        &:hover {
            color: #9466FF;
        }
    }
</style>