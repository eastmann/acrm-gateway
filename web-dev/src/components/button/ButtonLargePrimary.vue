<template>
    <div :style="{width: widthParam}" v-bind:class="!disabled ? 'button-large-primary' : 'button-large-primary-disabled'" >
        <slot/>
    </div>
</template>

<script>
    export default {
        name: "ButtonLargePrimary",
        props: {
            'widthParam': {
                type: [Number, String],
                default: 250,
            },
            'disabled': {
                type: Boolean,
                default: false,
            }
        }
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .button-large-primary {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 20px;
        padding-bottom: 20px;
        min-width: 140px;
        background: #9466FF;
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

    .button-large-primary:hover {
        background: #8c5bfd;
    }

    .button-large-primary-disabled {
        font-family: 'Rostelecom Basis';
        font-weight: 100;
        display: inline-block;
        padding-top: 20px;
        padding-bottom: 20px;
        min-width: 140px;
        background: @comEnabledText;
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

    .button-large-primary-disabled:hover {
        background: @comBackgroundSelect;
    }
</style>