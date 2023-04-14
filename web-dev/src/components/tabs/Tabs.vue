<template>
    <div :class='{"tabs__light": mode === "light", "tabs__dark": mode === "dark", "tabs__gray": mode === "gray"}'>
        <ul :class='{"tabs__header-light": mode === "light", "tabs__header-dark": mode === "dark", "tabs__header-gray": mode === "gray"}'>
            <li v-for='(tab, index) in tabs'
                :key='tab.title'
                @click='selectTab(index)'
                :class='{"tab__selected": (index == selIndex)}'>
                <span>{{ tab.title }}</span>
            </li>
        </ul>
        <div>
            <slot></slot>
        </div>
    </div>
</template>

<script>
    export default {
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

        watch: {
            selectedIndex(){
                this.selIndex = this.selectedIndex;
                this.$emit('on-select-tab', this.selIndex);
            },
            selIndex(){
                this.$emit('on-select-tab', this.selIndex);
            }
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
    @import "../../css/colors";

    ul.tabs__header-light {
        display: inline-block;
        vertical-align: top;
        width: auto;
        list-style: none;
        margin: 0;
        padding: 0;
        background: none;

        font-style: normal;
        font-weight: 500;
        font-size: 14px;
        line-height: 20px;
    }

    ul.tabs__header-light > li {
        padding: 15px 15px;
        display: inline-block;
        cursor: pointer;
        background: none;
        margin: 2px;

        &:hover {
            background: @corpBackground;
        }
    }

    ul.tabs__header-light > li.tab__selected {
        font-weight: 100;
        &:hover {
            color: @acrmViolet;
            background: none;
        }
    }

    ul.tabs__header-gray {
        font-weight: 100;
        display: inline-block;
        vertical-align: top;
        width: auto;
        list-style: none;
        margin: 0;
        padding: 0;
        background: @comBackgroundSelect;
    }

    ul.tabs__header-gray > li {
        padding: 6px 15px;
        display: inline-block;
        cursor: pointer;
        background: @comBackgroundSelect;
        margin: 2px;

        &:hover {
            background: @corpBackground;
        }
    }

    ul.tabs__header-gray > li.tab__selected {
        font-weight: 100;

        &:hover {
            background: @corpBackground;
        }
    }

    .tab {
        display: inline-block;
        padding: 0px;
        min-width: 10px;
        min-height: 400px;
        width: 100%;
        border-top: 1px solid rgba(16, 24, 40, 0.1);
    }

    .tabs__light li {
        color: @comGray_1;
    }

    .tabs__light li.tab__selected {
        color: @acrmViolet;;
        /*background-color: none;*/
    }

    .tabs__dark .tab {
        background-color: #555;
        color: #eee;
    }

    .tabs__dark li {
        background-color: #ddd;
        color: #aaa;
        border-bottom: 3px solid transparent;
    }

    .tabs__dark li.tab__selected {
        background-color: #555;
        color: white;
    }

    .tabs__gray li {
        color: @comTab;
        //padding: 8px 30px;
    }

    .tabs__gray li.tab__selected {
        //color: @comOrange;
        background-color: @corpBackground;
    }

    .tabs__gray .tab__selected span {
        color: @comGray_1;
    }

</style>