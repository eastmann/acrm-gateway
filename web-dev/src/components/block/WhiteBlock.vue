<template>
    <div class="white-block">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "WhiteBlock",
        props: {
        },

        data() {
            return {

            }
        },

        mounted() {

        },

        methods: {

        }
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .white-block {
        background: @corpBackground;
        border-radius: 5px;
        padding: 32px;
        margin-top: 26px;
        /*box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 20px;*/
    }

</style>