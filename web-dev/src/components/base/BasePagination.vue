<template>
    <div class="pagination-container">
        <div class="pagination-container-left">
            <DropDown caption="Показать" v-model="perPage" v-on:change="dropDownChange"
                      :options="pagesCounts"/>

            <span class="pagination__info"> {{pageStart}} - {{pageEnd}} из {{total}}</span>
        </div>

        <div class="pagination-container-right ">
            <span class="pagination__right-block">
                <span class="pagination__cursor" v-on:click="previous">&lt;</span>
                <span class="pagination__current">{{current + 1}}</span>
                <span class="pagination__cursor" v-on:click="next">&gt;</span>
            </span>
            <span class="pagination__pages">из {{pages}}</span>
        </div>
    </div>
</template>

<script>
    import DropDown from "@/views/main/holding_detail/components/DropDown";

    export default {
        name: "BasePagination",
        components: {DropDown},
        props: {
            pagProps: Object,
            total: {type: Number},
            pageChanged: {type: Function},
            perPageChanged: {type: Function}
        },
        data() {
            return {
                current: 0,
                pages: 1,
                pagesCounts: [10, 30, 50, 100],
                perPage: 10,
            }
        },
        created() {
            // console.log(this.pageChanged);
            this.calPageCount();
        },
        computed:{
            pageStart(){
                return this.current * this.perPage + 1
            },

            pageEnd(){
              let page = Number(this.current * this.perPage) + Number(this.perPage);
              return page > this.total ? this.total : page;
            }
        },
        methods: {
            setPage(page) {
                this.current = page;
                this.pageChanged({currentPage: this.current + 1});
            },
            previous() {
                if (this.current > 0)
                    this.setPage(this.current - 1)
            },
            next() {
                if (this.current < this.pages - 1)
                    this.setPage(this.current + 1)
            },
            setPerPage(perPage) {
                this.perPage = perPage;
                this.calPageCount();
                this.perPageChanged({currentPerPage: this.perPage});
            },
            calPageCount() {
                if (this.total > this.perPage) {
                    this.pages = Math.ceil(this.total / this.perPage);
                    // console.log(this.pages);
                } else {
                    this.pages = 1
                    this.setPage(0);
                }
            },

            dropDownChange(count) {
                this.setPerPage(count);
                this.calPageCount();
                this.setPage(0);
            }
        }
    }
</script>

<style scoped>
    .pagination-container {
        padding-top: 12px;
        padding-bottom: 12px;
        display: flex;
        justify-content: space-between;
    }

    .pagination-container-right {
        display: flex;
        justify-content: right;
    }

    .pagination-container-left {
        display: flex;
        justify-content: left;
    }

    .pagination__right-block{
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        padding: 6px 12px;
        background: rgba(16, 24, 40, 0.05);
        border-radius: 8px;
        border-style: none;
        color: #101828;
        /*vertical-align: center;*/
    }

    .pagination__pages{
        padding: 6px 16px 6px 10px;

        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }

    .pagination__info{
        padding: 6px 12px;
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }

    .pagination__current{
        padding-right: 9.5px;
        padding-left: 9.5px;
    }

    .pagination__cursor{
        cursor: pointer;
    }

</style>