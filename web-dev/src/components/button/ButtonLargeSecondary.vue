<template>
    <div  :style="{width: widthParam}"  class="button-large-secondary">
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonLargeSecondary",
        props: {
            'widthParam': {
                type: [Number, String],
                default: 250
            },
        }
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .button-large-secondary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 18px;
        padding-bottom: 18px;
        min-width: 140px;
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

    .button-large-secondary:hover {
        color: @comOrange;
    }
</style>