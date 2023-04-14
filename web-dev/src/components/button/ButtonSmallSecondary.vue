<template>
    <div class="button-small-secondary">
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonSmallSecondary"
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .button-small-secondary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 6px;
        padding-bottom: 6px;
        min-width: 80px;
        border: 2px solid @comOrange;
        border-radius: 5px;
        color: black;
        font-size: 1em;
        letter-spacing: 0.32px;
        line-height: 1;
        text-decoration: none;
        cursor: pointer;
        transition: 0.3s;
        text-align: center;
    }

    .button-small-secondary:hover {
        color: @comOrange;
    }
</style>