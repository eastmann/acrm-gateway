<template>
    <div class="block-border">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "Block",
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

    .block-border {
        background: @corpBackground;
        border-radius: 10px 10px 10px 10px;
        padding-top: 15px;
        padding-bottom: 10px;

        /*box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 20px;*/
        border: 1px solid rgba(16, 24, 40, 0.1);
    }

</style>