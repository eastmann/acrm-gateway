<template>
    <div class="expand-box">
        <div class="expand-box-header acrm__container">
            <div v-on:click="toggleExpand" class="acrm__container-left">
                <slot name="header">
                    <div class="expand-box-header-label">
                        {{header}}
                    </div>
                    <div v-if="showToggler" class="expand-box-toggle">
                        <ToggleButton v-model="toggle" left-label="Выручка" right-label="КАМ"   v-on:input="toggleClick"/>
                    </div>
                    <div v-if="segment" class="in-block__segment">
                        {{segment}}
                    </div>
                    <div v-if="showTotal & count != null" class="in-block__count-caption">
                        Всего:
                    </div>
                    <div v-if="showTotal & count != null" class="in-block__count">
                        {{count}}
                    </div>
                </slot>
            </div>
            <div class="expanded-box__container-right">
                <slot  name="search-box"></slot>

                <div v-if="isExpanded & !hideSearch" style="padding-top: 15px; padding-right: 20px;">
                    <input class="nosubmit1" type="search" v-model="searchText" placeholder="Поиск">
                </div>

                <div v-if="isExpanded & !hideSetting" class="vl"></div>

                <tooltip-origin v-if="holdingActualDates" placement="left-top" mode="hover">
                    <div slot="outlet">
                        <img src="@/assets/img/refresh_gray.png" class="detail-title__date-update-logo">
                    </div>
                    <div slot="tooltip">
                        <div class="detail-title__tooltip-area">
                            Дата актуальности
                        </div>
                        <table class="detail-title__struct-table">
                            <tr>
                                <td class="struct__caption">
                                    Финансы
                                </td>
                                <td class="detail-title__struct__text">
                                    {{getFinanceDate}}
                                </td>
                            </tr>

                            <tr>
                                <td class="struct__caption">
                                    Контакты
                                </td>
                                <td class="detail-title__struct__text">
                                    {{getContactDate}}
                                </td>
                            </tr>

                            <tr>
                                <td class="struct__caption">
                                    Продукты
                                </td>
                                <td class="detail-title__struct__text">
                                    {{getProductDate}}
                                </td>
                            </tr>
                        </table>
                    </div>
                </tooltip-origin>

                <img v-if="isExpanded & !hideSetting" v-on:click="clickSetting()" src="@/assets/img/setting.png" class="setting-logo">
                <slot name="setting"></slot>
                <i v-on:click="toggleExpand" v-if="enabled" :class="['fa', iconClass, 'expand-icon', 'cur-pointer']"></i>
            </div>
        </div>
        <!--        <transition v-on:enter="animEnter" v-on:leave="animLeave">-->
        <transition v-on:leave="animLeave">
            <div class="expand-box-content" v-if="isExpanded">
                <slot/>
            </div>
        </transition>
    </div>
</template>

<script>
    import * as $ from 'jquery'
    import ToggleButton from "@/components/ToggleButton";
    import TooltipOrigin from "@/components/tooltip/TooltipOrigin";
    import dateFormat from "@/js/utils/date";

    export default {
        name: "ExpandBox",
        components: {TooltipOrigin, ToggleButton},
        props: {
            'header': String,
            'headerClass': String,
            'enabled': {
                type: Boolean,
                default: true,
            },
            'newStyle': String,
            'segment': String,
            'count': [Number, String],
            'expanded': {
                type: Boolean,
                default: true
            },
            'showToggler': {
                type: Boolean,
                default: false
            },
            'showTotal': {
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
            'holdingActualDates':{
                type: Array,
                default: null,
            }
        },
        data() {
            return {
                toggle: false,
                searchText: "",
                isExpanded: this.expanded,
            }
        },

        computed: {
            holdingActualDatesBySegment(){
               if (this.holdingActualDates === null){
                   return null;
               }

               let segmentCode = this.getSegmentCode(this.segment);
               return this.holdingActualDates.filter(x => x.dzo_id === segmentCode);
            },

            getFinanceDate(){
                return (this.holdingActualDatesBySegment !== null) && (this.holdingActualDatesBySegment.length > 0) ?
                    this.formatSimple(this.holdingActualDatesBySegment[0].fin_actual_date) : '-'
            },
            getContactDate(){
                return (this.holdingActualDatesBySegment !== null) && (this.holdingActualDatesBySegment.length > 0) ?
                    this.formatSimple(this.holdingActualDatesBySegment[0].kam_actual_date) : '-'
            },
            getProductDate(){
                return (this.holdingActualDatesBySegment !== null) && (this.holdingActualDatesBySegment.length > 0) ?
                    this.formatSimple(this.holdingActualDatesBySegment[0].product_actual_date) : '-'
            },

            iconClass() {
                return this.isExpanded ? 'fa-caret-up' : 'fa-caret-down'
            },

            headerClassObject() {
                let result = {};
                if (this.newStyle) {
                    result['expand-box-header-label-new-style'] = true;
                } else {
                    result['expand-box-header-label'] = true;
                }

                if (this.headerClass != null) {
                    result[this.headerClass] = true;
                }

                return result;
            }
        },

        watch: {
            searchText() {
                this.onSearch(this.searchText)
            }
        },

        methods: {
            ...dateFormat,

            getSegmentCode(segment){
               if ((segment === null) || (segment === '') || (segment === undefined)){
                   return 1;
               }
               switch (segment) {
                   case 'РТК': return 1;
                   case 'ЦОД': return 2;
                   case 'Солар': return 3;
                   case 'Теле2': return 4;
                   case 'Всего': return 5;
                   default: return 1;
               }
            },

            toggleClick(){
                this.$emit('on-toggle', this.toggle)
            },

            clickSetting(val){
                this.$emit('on-click-setting', val)
            },

            onSearch(val) {
                this.$emit('on-search', val)
            },

            toggleExpand() {
                if (!this.enabled) {
                    this.isExpanded = true;
                } else {
                    this.isExpanded = !this.isExpanded;
                }
            },
            animEnter(el) {
                let parent = $(el).closest('expand-box-content');
                parent.css('max-height', parent.scrollHeight);
                $(el).animate({"max-height": el.scrollHeight}, 300, () => {
                    $(el).css('max-height', '')
                })
            },
            animLeave(el, done) {
                $(el).css('max-height', el.scrollHeight);
                $(el).animate({"max-height": 0}, 300, () => done())
            }

        }
    }
</script>

<style lang="less">
    @import "../../css/colors";
    @import "../../css/animations";

    .detail-title__struct-table {
        margin-top: 4px;
        border-collapse: collapse;
        width: 100%;

        tr {
            vertical-align: text-top;
        }

        td {
            /*border: 1px solid red;*/
            padding-top: 4px;
        }
    }

    .detail-title__tooltip-area {

        font-family: 'Rostelecom Basis';
        font-style: normal;
        font-weight: 400;
        font-size: 16px;
        line-height: 24px;
        color: #101828;
    }

    .detail-title__date-update-logo {
        cursor: pointer;
        width: 16px;
        height: 20px;
        margin-top: 20px;
        margin-right: 15px;
        &:hover, &.active {
            background-image: url("../../assets/img/refresh_black.png");
        }
    }

    .expand-box {
        margin-top: 16px;
        border-radius: 10px;
        border: 1px solid rgba(16, 24, 40, 0.1);
    }

    .expand-box-header {
        display: grid;
        padding-right: 20px;
        grid-template-columns: 30px calc(100% - 30px);
    }

    .expand-box-toggle{
        padding: 20px;
    }

    .expand-box-header-label {
        /*margin-left: 20px;*/
        color: #101828;
        cursor: pointer;
        font-style: normal;
        font-weight: 700;
        font-size: 22px;
        line-height: 24px;
        padding: 20px;
    }

    .expand-box-header-label-new-style {
        /*margin-left: 20px;*/
        color: @comGray_1;
        cursor: pointer;
    }

    .expand-box-content {
        padding-left: 0px;
        overflow: hidden;
    }

    .expand-icon {
        font-size: 16px;
        color: rgba(16, 24, 40, 0.5);
        justify-self: center;
        align-self: center;
    }

    //Стиль применяется по желанию, указывается в header-class
    .big-expand-header {
        font-size: 20px;
        font-weight: bold;
    }

    .in-block__segment {
        color: #101828;
        cursor: pointer;
        font-style: normal;
        font-weight: 500;
        font-size: 12px;
        line-height: 16px;
        margin: 20px;
        padding-left: 20px;
        padding-right: 20px;
        align-items: center;
        ///padding: 4px 8px;
        /*gap: 4px;*/
        /*width: fit-content;*/
        height: 24px;
        background: rgba(31, 105, 255, 0.12);
        border-radius: 3px;

        /*flex: none;*/
        /*order: 0;*/
        /*flex-grow: 0;*/
        /* identical to box height, or 133% */

        display: flex;
        align-items: center;
        letter-spacing: 0.02em;
    }

    .in-block__count-caption {
        color: rgba(16, 24, 40, 0.5);
        cursor: pointer;
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 24px;
        padding-right: 10px;
        padding-top: 23px;
    }

    .in-block__count {
        color: #101828;
        cursor: pointer;
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 24px;
        padding-right: 10px;
        padding-top: 23px;
    }

    nosubmit1 {
        border: none;
        padding-top: 15px;
        padding-right: 20px;
    }

    input.nosubmit1 {
        padding: 9px 4px 9px 40px;
        background: transparent url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' class='bi bi-search' viewBox='0 0 16 16'%3E%3Cpath d='M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z'%3E%3C/path%3E%3C/svg%3E") no-repeat 13px center;
        width: 200px;
        height: 32px;
        background-color: rgba(16, 24, 40, 0.05);
        border-radius: 8px;
        border: none;
    }

    .setting-logo {
        /*padding-top: 15px;*/
        height: 20px;
        width: 20px;
        margin-top: 20px;
        margin-right: 15px;
        cursor: pointer;
    }

    .vl {
        border-left: 1px solid rgba(16, 24, 40, 0.1);
        width: 1px;
        height: 16px;
        margin-top: 20px;
        margin-right: 20px;
    }

    .expanded-box__container-right {
        display: flex;
        justify-content: right;
    }
</style>