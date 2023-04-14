<template>
    <div class="white-block-new">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "WhiteBlockNew",
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

    .white-block-new {
        border-radius: 10px;
        padding: 32px;
        border: 1px solid rgba(16, 24, 40, 0.1);
        margin-top: 32px;
        /*box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 20px;*/
    }

</style>