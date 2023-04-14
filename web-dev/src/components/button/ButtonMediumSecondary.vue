<template>
    <div :style="{width: widthParam}" class="button-medium-secondary">
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonMediumSecondary",
        props: {
            'widthParam': {
                type: Number,
                default: 120
            },
        }
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .button-medium-secondary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 14px;
        padding-bottom: 14px;
        min-width: 120px;
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
        background-color: @comWhite;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .button-medium-secondary:hover {
        color: @comOrange;
    }
</style>