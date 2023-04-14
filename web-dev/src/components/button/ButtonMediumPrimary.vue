<template>
    <div :style="{width: widthParam}" class="button-medium-primary">
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonMediumPrimary",
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

    .button-medium-primary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 16px;
        padding-bottom: 16px;
        min-width: 120px;
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

    .button-medium-primary:hover {
        background: #ee4a11;
    }
</style>