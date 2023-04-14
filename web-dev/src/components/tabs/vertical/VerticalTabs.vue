<template>
    <div class="vertical__grid">
        <div :class='{"vertical__tabs__light": mode === "light", "vertical__tabs__dark": mode === "dark", "vertical__tabs__gray": mode === "gray"}'>
            <ul :class='{"vertical__tabs__header-light": mode === "light", "vertical__tabs__header-dark": mode === "dark", "vertical__tabs__header-gray": mode === "gray"}'>
                <li v-for='(tab, index) in tabs'
                    :key='tab.title'
                    @click='selectTab(index)'
                    :class='{"vertical__tab__selected": (index == selIndex)}'>
                    <span>{{ tab.title }}</span>
                </li>
            </ul>
        </div>
        <div class="tesst">
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "VerticalTabs",
        props: {
            mode: {
                type: String,
                default: 'light'
            },
            'selectedIndex': {
                type: Number,
                default: 0,
            },
        },

        watch: {
            selectedIndex(){
                this.selIndex = this.selectedIndex;
                this.$emit('on-select-tab', this.selIndex);
            },
            selIndex(){
                this.$emit('on-select-tab', this.selIndex);
            }
        },

        data() {
            return {
                selIndex: 0,
                tabs: []         // all of the tabs
            }
        },

        created() {
            this.tabs = this.$children
        },

        mounted() {
            if (this.selectedIndex){
                this.selIndex = this.selectedIndex;
            }
            this.selectTab( this.selIndex)
        },

        methods: {
            selectTab(i) {
                this.selIndex = i
                // loop over all the tabs
                this.tabs.forEach((tab, index) => {
                    tab.isActive = (index === i)
                })
            }
        }
    }
</script>

<style lang="less">
    @import "../../../css/colors";

    ul.vertical__tabs__header-light {
        font-style: normal;
        font-weight: 500;
        font-size: 12px;
        line-height: 16px;

        /*display: inline-block;*/
        vertical-align: top;
        width: auto;
        list-style: none;
        margin: 0;
        padding: 0;
        background: none;
    }

    ul.vertical__tabs__header-light > li {
        padding: 12px 20px;
        /*display: inline-block;*/
        cursor: pointer;
        background: none;
        margin: 2px;

        &:hover {
            background: @corpBackground;
        }
    }

    ul.vertical__tabs__header-light > li.vertical__tab__selected {
        font-weight: 100;
        &:hover {
            color: @acrmViolet;
            background: none;
        }
    }

    ul.vertical__tabs__header-gray {
        font-weight: 100;
        /*display: inline-block;*/
        vertical-align: top;
        width: auto;
        list-style: none;
        margin: 0;
        padding: 0;
        background: @comBackgroundSelect;
    }

    ul.vertical__tabs__header-gray > li {
        padding: 6px 15px;
        /*display: inline-block;*/
        cursor: pointer;
        background: @comBackgroundSelect;
        margin: 2px;

        &:hover {
            background: @corpBackground;
        }
    }

    ul.vertical__tabs__header-gray > li.vertical__tab__selected {
        font-weight: 100;

        &:hover {
            background: @corpBackground;
        }
    }

    .vertical__tab {
        /*display: inline-block;*/
        padding: 0px;
        min-width: 10px;
        min-height: 400px;
        width: 100%;
        /*border-top: 1px solid rgba(16, 24, 40, 0.1);*/
    }

    .vertical__tabs__light{
        height: calc(100vh - 215px);
        border-right: 1px solid rgba(16, 24, 40, 0.1);
    }

    .vertical__tabs__light li {
        color: @comGray_1;
    }

    .vertical__tabs__light li.vertical__tab__selected {
        color: @acrmViolet;
        /*background-color: none;*/
    }

    .vertical__tabs__dark .tab {
        background-color: #555;
        color: #eee;
    }

    .vertical__tabs__dark li {
        background-color: #ddd;
        color: #aaa;
        border-bottom: 3px solid transparent;
    }

    .vertical__tabs__dark li.vertical__tab__selected {
        background-color: #555;
        color: white;
    }

    .vertical__tabs__gray li {
        color: @comTab;
        //padding: 8px 30px;
    }

    .vertical__tabs__gray li.vertical__tab__selected {
        //color: @comOrange;
        background-color: @corpBackground;
    }

    .vertical__tabs__gray .vertical__tab__selected span {
        color: @comGray_1;
    }

    .vertical__grid {
        display: grid;
        grid-template-columns: 240px calc(100% - 240px);
        /*text-align: center;*/
        /*grid-column-gap: 10px;*/
        /*min-height: 600px;*/
    }

    .tesst{

    }
</style>