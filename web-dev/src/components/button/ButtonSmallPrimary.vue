<template>
    <div class="button-small-primary">
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonSmallPrimary"
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .button-small-primary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 8px;
        padding-bottom: 8px;
        min-width: 80px;
        background: @comOrange;
        border-radius: 5px;
        color: @comWhite;
        font-size: 1em;
        letter-spacing: 0.32px;
        line-height: 1;
        text-decoration: none;
        cursor: pointer;
        transition: 0.3s;
        text-align: center;
    }

    .button-small-primary:hover {
        background: #ee4a11;
    }
</style>