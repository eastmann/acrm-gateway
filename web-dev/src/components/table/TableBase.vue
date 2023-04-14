<template>
    <div>
        <ExpandBox :header="header"
                   :segment="segment"
                   :count="rowsLength"
                   v-on:on-search="onSearchText"
                   :show-total="showTotal"
                   :hide-setting="hideSetting"
                   :hide-search="hideSearch"
                   :expanded="expanded"
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

        </ExpandBox>
    </div>
</template>

<script>
    import ExpandBox from "../layout/ExpandBox";
    import BaseDataTable from "@/components/base/BaseDataTable";

    export default {
        name: "TableBase",
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
            'expanded':{
                type: Boolean,
                default: true,
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


</style>