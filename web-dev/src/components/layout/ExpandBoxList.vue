<template>
    <div class="expand-box">
        <div class="expand-box-header acrm__container" v-on:click="toggleExpand">
            <div class="acrm__container-left">
                <slot name="header">
                    <div class="expand-box-header-label">
                        {{header}}
                    </div>
                    <div v-if="segment != null" class="in-block__segment">
                        {{segment}}
                    </div>
                    <div v-if="count != null" class="in-block__count-caption">
                        Всего:
                    </div>
                    <div v-if="count != null" class="in-block__count">
                        {{count}}
                    </div>


                </slot>
            </div>
            <div class="acrm__container-right">
                <i v-if="enabled" :class="['fa', iconClass, 'expand-icon']"></i>
            </div>
        </div>
        <transition v-on:leave="animLeave">
            <div class="expand-box-content" v-if="expanded">
                <slot/>
            </div>
        </transition>
    </div>
</template>

<script>
    import * as $ from 'jquery'

    export default {
        name: "ExpandBoxList",
        props: {
            'header': String,
            'headerClass': String,
            'enabled': Boolean,
            'newStyle': String,
            'segment': String,
            'count': Number,
            'expanded': {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                searchText: ""
            }
        },

        computed: {
            iconClass() {
                return this.expanded ? 'fa-caret-up' : 'fa-caret-down'
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
            onSearch(val) {
                this.$emit('on-search', val)
            },

            toggleExpand() {
                if (!this.enabled) {
                    this.expanded = true;
                } else {
                    this.expanded = !this.expanded;
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
        padding: 20px 10px;
    }

    .in-block__count {
        color: #101828;
        cursor: pointer;
        font-style: normal;
        font-weight: 400;
        font-size: 12px;
        line-height: 24px;
        padding: 20px 0;
    }

    form.nosubmit {
        border: none;
        padding-top: 15px;
        padding-right: 20px;
    }

    input.nosubmit {
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
        margin-right: 0px;
        cursor: pointer;
    }

    .vl {
        border-left: 1px solid rgba(16, 24, 40, 0.1);
        width: 1px;
        height: 16px;
        margin-top: 20px;
        margin-right: 20px;
    }
</style>