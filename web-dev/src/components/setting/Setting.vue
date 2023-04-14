<template>
    <div class="dropdown">
        <div v-if="showSetting" id="myDropdown" class="dropdown-content">
            <div class="modal-close" @click="clickSetting()">&#10006;</div>
            <div class="margin-top-10"></div>
            <div class="setting__caption">Отображение колонок:</div>
            <div class="margin-top-10"></div>
            <div class="toggle-row" v-for="column in columns" :key="column.value">
                <checkbox :label="column.text" v-model="column.hidden" :inverse="true"/>
            </div>
        </div>
    </div>
</template>

<script>
    import Checkbox from "../checkbox/CheckBox";
    export default {
        name: "Setting",
        components: {Checkbox},
        props: ['columns', 'showSetting'],
        methods: {
            clickSetting(val) {
                this.$emit('on-click-setting', val)
            }
        }
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .margin-top-20{
        margin-top: 20px;
    }
    .modal-close {
        border-radius: 50%;
        color: #9466FF;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 7px;
        right: 7px;
        width: 30px;
        height: 30px;
        cursor: pointer;
    }

    .toggle-row {
        padding-top: 5px;
    }

    .dropdown {
        position: relative;
        display: inline-block;
    }

    /* Dropdown Content (Hidden by Default) */
    .dropdown-content {
        display: block;
        position: absolute;
        padding: 8px 20px 8px;
        background-color: #fefefe;
        width: 300px;
        left: -300px;
        top: 50px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
        border-radius: 5px;
    }

    /* Links inside the dropdown */
    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    /* Change color of dropdown links on hover */
    .dropdown-content a:hover {
        background-color: #fefefe;
    }

    /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
    .show {
        display: block;
    }

    .setting__caption{
        font-style: normal;
        font-weight: 300;
        font-size: 16px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }
</style>