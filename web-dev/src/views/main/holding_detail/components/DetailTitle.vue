<template>
    <div>
        <div class="detail-title__container">
            <div class="detail-title__name">
                {{mainInfo.fullnamerus}}
            </div>
        </div>

        <div v-if="id" class="detail-title__sections">
            <div class="detail-title__segment detail-title__section">
                {{mainInfo.sector}}
            </div>

            <div class="detail-title__inn-kpp detail-title__section">
                ИНН: {{mainInfo.inn}}
            </div>

            <div class="detail-title__inn-kpp detail-title__section">
                КПП: {{mainInfo.kpp}}
            </div>
        </div>

        <div v-if="hid" class="detail-title__sections">
            <div class="detail-title__segment detail-title__section">
                {{mainInfo.sector}}
            </div>

            <div class="detail-title__inn-kpp detail-title__section">
                Выручка холдинга: <span class="black-text">{{getVirHolding()}}</span>
            </div>

            <div class="detail-title__inn-kpp detail-title__section">
                Выручка по ГК РТК: <span class="black-text">{{getVir()}}</span>
            </div>

            <div class="detail-title__inn-kpp detail-title__section">
                КАМ (РТК): <span class="blue-text">{{getKamRTK()}}</span>
            </div>

            <div v-if="getLprH() !== '-'" class="detail-title__inn-kpp detail-title__section">
                ЛПР холдинга: <span class="blue-text">{{getLprH()}}</span>
            </div>
        </div>
    </div>
</template>

<script>
    import numerUtils from "@/js/utils/numer-utils";
    import dateFormat from "@/js/utils/date";

    export default {
        name: "DetailTitle",
        components: {},
        props: ['mainInfo', 'hid', 'id', 'kamLprInfo', 'holdingMainsTotalFront', 'holdingMains', 'holdingActualDates'],

        methods: {
            ...numerUtils,
            ...dateFormat,

            getKamRTK() {
                let kamLpr = this.kamLprInfo.filter(x => ((x.dzo_id === 1) && (x.kam_type_id === 2)));
                if ((kamLpr == null) || (kamLpr.length === 0)) {
                    return '-'
                }
                return kamLpr[0].kam_name;
            },

            getLprH() {
                let kamLpr = this.kamLprInfo.filter(x => ((x.dzo_id === 5) && (x.kam_type_id === 5)));
                if ((kamLpr == null) || (kamLpr.length === 0)) {
                    return '-'
                }
                return kamLpr[0].kam_name;
            },

            getVir() {
                if ((this.holdingMains == null) || (this.holdingMains.length === 0)) {
                    return '-'
                }
                return this.numberWithSpaces(this.holdingMains[0].virGkRtk2022, 2) + ' млн. ₽';
            },

            getVirHolding() {
                if (this.holdingMains === null) {
                    return null;
                }
                let val = this.holdingMains.filter(x => (x.dzoId === 1));
                if (val == null) {
                    return '-'
                }
                if (val[0] === undefined) {
                    return "-"
                }
                return this.numberWithSpaces(val[0].virEgrul2021, 2) + ' млн. ₽';
            }


        }
    }

</script>

<style lang="less">

    .detail-title__container {
        display: grid;
        padding-right: 20px;
        grid-template-columns: calc(100% - 53px) 53px;
    }

    .detail-title__container-right {
        /*text-align: right;*/
        padding-top: 26px;
        /*padding-right: 153px;*/
        /*border-style: solid;*/
    }

    .detail-title__sections {
        display: inline-block;
    }

    .detail-title__sections .detail-title__section {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 14px;
        line-height: 16px;
        color: rgba(16, 24, 40, 0.5);

        display: inline-block;
        padding: 4px 8px;
    }

    .detail-title__name {
        font-style: normal;
        font-weight: 700;
        font-size: 22px;
        line-height: 24px;
        padding: 20px;
        color: #101828;
    }

    .black-text {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 16px;
        color: #101828;
        margin-left: 4px;
    }

    .blue-text {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 16px;
        color: #9466FF;
        margin-left: 4px;
    }

    .detail-title__segment {
        background: rgba(31, 105, 255, 0.12);
        border-radius: 3px;
        margin: 0px 20px;
    }

    .detail-title__inn-kpp {
        font-style: normal;
        font-weight: 400;
        font-size: 16px;
        line-height: 16px;
        color: rgba(16, 24, 40, 0.5);
    }

    .tooltip {
        position: relative;
        display: inline-block;
        border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
    }

    /* Tooltip text */
    .tooltip .tooltiptext {
        visibility: hidden;
        /*width: 320px;*/
        background-color: #F0EFFF;
        /*color: black;*/
        /*!*text-align: center;*!*/
        /*padding: 5px 0;*/
        /*border-radius: 6px;*/

        /* Position the tooltip text - see examples below! */
        position: absolute;
        z-index: 1;


        border-radius: 10px;
        padding: 32px;
        border: 1px solid rgba(16, 24, 40, 0);
        margin-top: 32px;
        /*box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 20px;*/

    }

    /* Show the tooltip text when you mouse over the tooltip container */
    .tooltip:hover .tooltiptext {
        visibility: visible;
    }

    .detail-title__struct__text {
        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 16px;
        color: #101828;
    }
</style>

