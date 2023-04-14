<template>
    <label class="checkbox-other">
        <input  type="checkbox" :value="inputValue"  v-model="model" />
        <span class="check-box__text">{{ label }}</span>
    </label>
</template>

<script>
    export default {
        name: "Checkbox",
        props: {
            label: String,
            value: [Array, Boolean],
            inputValue: String,
            'inverse': {
                type: Boolean,
                default: false,
            }
        },
        computed: {
            model: {
                get() {
                    return this.inverse ? !this.value : this.value;
                },
                set(value) {
                    this.$emit("input", this.inverse ? !value : value);
                },
            },
        },
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .checkbox-other {
        display: block;
        margin: 0;
        cursor: pointer;
        user-select: none;
        position: relative;
    }
    .checkbox-other input[type=checkbox] {
        position: absolute;
        z-index: -1;
        opacity: 0;
        display: block;
        width: 0;
        height: 0;
    }
    .checkbox-other span {
        display: inline-block;
        position: relative;
        padding: 0 0 0 30px;
        line-height: 22px;
    }
    .checkbox-other span:before {
        content: "";
        display: inline-block;
        width: 22px;
        height: 22px;
        position: absolute;
        left: 0;
        top: 0;
        transition: background 0.3s ease;
        /*background: url(/upload2/checkbox-1.png) 0 0 no-repeat;*/
        background: none;
    }

    /* Checked */
    .checkbox-other input[type=checkbox]:checked + span:before {
        /*background-image: url(/upload2/checkbox-2.png);*/
        background: url("../../../src/assets/img/ok.png") no-repeat;
    }

    /* Focus */
    .focused span:before {
        box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
    }

    /* Hover */
    .checkbox-other span:hover:before {
        filter: brightness(110%);
    }

    /* Active */
    .checkbox-other span:active:before {
        filter: brightness(80%);
    }

    /* Disabled */
    .checkbox-other input[type=checkbox]:disabled + span {
        color: #666;
        cursor: default;
    }
    .checkbox-other input[type=checkbox]:disabled + span:before {
        filter: grayscale(100%);
        opacity: 0.6;
    }

    .check-box__text{
        font-style: normal;
        font-weight: 300;
        font-size: 14px;
        line-height: 20px;
        color: rgba(16, 24, 40, 0.5);
    }
</style>