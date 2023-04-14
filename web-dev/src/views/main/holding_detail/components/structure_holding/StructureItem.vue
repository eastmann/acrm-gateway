<template>
    <div>
        <tooltip placement="auto" mode="hover">
            <div slot="outlet">

                <div v-if="item"
                     :class="!item.selected ? 'holding-detail-structure__item' : 'holding-detail-structure__item-selected'">
                    <div class="holding-detail-structure__name" v-on:click="goToCompany(item)">
                        {{item.companyName}}
                    </div>

                    <div class="holding-detail-structure__sections">
                        <!--            <div class="holding-detail-structure__segment holding-detail-structure__section">-->
                        <!--                ООО-->
                        <!--            </div>-->
                        <div class="holding-detail-structure__segment holding-detail-structure__section">
                           Выручка {{item.dzoId === 5 ? numberFormat(item.vir2021) : numberFormat(item.vir2022)}}
                        </div>
                        <!--            <div class="holding-detail-structure__segment holding-detail-structure__section">-->
                        <!--                {{item.sharePart}}%-->
                        <!--            </div>-->
                        <div class="holding-detail-structure__segment holding-detail-structure__section">
                            <i v-on:click="rtClick(item)" class='bx bx-right-arrow-alt cur-pointer'></i>
                        </div>

                    </div>
                </div>

            </div>
            <div slot="tooltip">
                <div class="struct__padding">
                    <table class="struct-table">
                        <tr>
                            <td class="struct__title">
                                {{item.companyName}}
                            </td>
                        </tr>
                    </table>
                    <table class="struct-table">
<!--                        <tr>-->
<!--                            <td class="struct__caption">-->
<!--                                ОГРН-->
<!--                            </td>-->
<!--                            <td class="struct__text">-->
<!--                                {{item.coownerOgrn}}-->
<!--                            </td>-->
<!--                        </tr>-->

                        <tr>
                            <td class="struct__caption">
                                ИНН
                            </td>
                            <td class="struct__text">
                                {{item.inn}}
                            </td>
                        </tr>

                        <tr>
                            <td class="struct__caption">
                                <div>Вид</div>
                                <div>деятельности</div>
                            </td>
                            <td class="struct__text">
                                {{item.okved}}
                            </td>
                        </tr>

                        <tr>
                            <td class="struct__caption">
                                <div>Выручка</div>
                                <div>ЕГРЮЛ</div>
                            </td>
                            <td class="struct__text">
                                {{numberFormat(item.virEgrul2021)}}
                            </td>
                        </tr>

                        <tr>
                            <td class="struct__caption">
                                КАМ
                            </td>
                            <td class="struct__text">
                                {{item.kam}}
                            </td>
                        </tr>

                        <tr>
                            <td class="struct__caption">
                                Контракты
                            </td>
                            <td class="struct__text">
                                {{item.contracts}}
                            </td>
                        </tr>

                    </table>
                </div>
            </div>
        </tooltip>


    </div>
</template>

<script>
    import Tooltip from "@/views/main/holdings/Tooltip";

    export default {
        name: "StructureItem",
        components: {Tooltip},
        props: {
            'item': {
                type: Object,
            },
        },

        data() {
            return {}
        },

        methods: {
            rtClick(val) {
                this.$emit('rt-click', val)
            },

            goToCompany(item) {
                this.$router.push("/holding-detail/id/" + item.id + "?dzo=" + item.dzoId)
            },

            numberFormat(number) {
                if (number === -1){
                    return '-'
                }
                let numberMills = Math.trunc(number);
                return numberMills.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ") + ' млн.';
            }
        }
    }

</script>

<style lang="less">
    .tip {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 16px;
        display: flex;
        align-items: center;
        color: rgba(16, 24, 40, 0.5);
    }

    .tip-name {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 16px;
        line-height: 24px;
        color: #101828;
        width: 250px;
        overflow-wrap: break-word
    }

    .holding-detail-structure__item {
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        padding: 8px 12px;
        background: #F0EFFF;
        border-radius: 12px;
        align-self: stretch;
        margin-bottom: 10px;
    }

    .holding-detail-structure__item-selected {
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        padding: 8px 12px;
        background: #DBD6FF;
        border-radius: 12px;
        align-self: stretch;
        margin-bottom: 10px;
    }

    .holding-detail-structure__segment {
        /*align-items: center;*/
        padding: 4px 8px;
        width: fit-content;
        background: rgba(31, 105, 255, 0.12);
        border-radius: 3px;
    }

    .holding-detail-structure__sections {
        display: inline-block;
    }

    .holding-detail-structure__sections .holding-detail-structure__section {
        font-style: normal;
        font-weight: 500;
        font-size: 10px;
        line-height: 12px;

        display: inline-block;

        padding: 3px 5px;
        margin-right: 10px;
        color: #101828;
    }

    .holding-detail-structure__name {
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 20px;
        color: #101828;
        margin-bottom: 8px;
        cursor: pointer;
        width: fit-content;

        &:hover {
            text-decoration: underline;
            color: blue;
        }
    }

    .struct-table {
        margin-top: 4px;
        border-collapse: collapse;
        width: 100%;

        tr {
            vertical-align: text-top;
        }

        td {
            /*border: 1px solid red;*/
            padding-top: 4px;
            padding-left: 10px;
        }
    }

    .struct__title {
        width: 100%;
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 16px;
        line-height: 24px;
        color: #101828;

    }

    .struct__caption {
        width: 90px;
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 16px;
        color: rgba(16, 24, 40, 0.5);
    }

    .struct__text {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 16px;
        color: #101828;
    }

    .struct__padding{
        padding: 10px 10px 10px 0;
        width: 100%;
    }
</style>

