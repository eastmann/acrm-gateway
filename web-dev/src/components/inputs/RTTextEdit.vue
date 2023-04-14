<template>
    <div class="rt-text-edit__rt-input">
        <div class="rt-text-edit__group">
            <input ref="inp"  :type="type !== '' ? type : 'text'" :value="value" v-on:keyup="rtOnKeyUp($event)" v-on:focusout="rtFocusOut($event.target.value)" @input="$emit('input', $event.target.value)"
                   :size="size" :placeholder="placeholder" readonly="readonly" onfocus="this.removeAttribute('readonly');" required>
            <div :class="(errorInfo ==='') ? 'btn ok' : 'btn err'"   v-on:click="clear" ></div>
            <span class="rt-text-edit__bar"></span>
            <label class="rt-text-edit__caption">{{caption}}</label>
            <div class="rt-text-edit__error">{{errorInfo}}</div>
        </div>
        <!--        <div class="data-field-caption">{{caption}}</div>-->
    </div>
</template>

<script>
    export default {
        props: {
            'caption': String,
            'errorInfo': String,
            'placeholder': String,
            'size': [String, Number],
            'type': String,
            'value': [String, Number, Boolean, Object, Array],
            'disabled': Boolean,
        },
        name: "RTTextEdit",
        data() {
            return {
                counter: 0,
            }
        },

        methods: {
            rtFocusOut(val){
                this.$emit('rt-focus-out', val)
            },

            rtOnKeyUp(val){
                this.$emit('rt-on-key-up', val)
            },

            clear(){
                this.$emit('input', "")
            }
        }
    }
</script>

<style lang="less">
    @import "../../css/colors";


    /* form starting stylings ------------------------------- */
    .rt-text-edit__caption{
        font-family: 'Rostelecom Basis';
    }

    .rt-text-edit__group {

        position: relative;
        margin-bottom: 30px;

        .btn {
            width: 16px;
            height: 16px;

            position: absolute;
            top: -10px;
            bottom: 10px;
            right: 10px;
            display: none;
            border: 0;
            padding-top: 0 -5px;
            border-radius: 50%;
            background-color: #fff;
            transition: background 200ms;
            outline: none;

            &:hover {
                width: 16px;
                height: 16px;
                display: block;
                background: url("../../../src/assets/img/navigation/close.svg") no-repeat;
            }

        }

        input:valid ~ div {
            display: block;
        }

        .ok {
            background: url("../../../src/assets/img/navigation/ok.svg") no-repeat;
        }

        .err {
            background: url("../../../src/assets/img/navigation/close_gray.svg") no-repeat;
        }

    }

    .rt-text-edit__rt-input {
        input {
            font-size: 16px;
            //padding: 5px;
            display: block;
            width: 100%;
            border: none;
            border-bottom: 2px solid @comBackground;
            &:hover {
                border-bottom: 2px solid @comFieldCaption;
            }
        }

        input:-webkit-autofill {
            /*transition: all 5000s ease-in-out 0s;*/
            -webkit-box-shadow: 0 0 0 1000px white inset;
        }

        input:focus {
            outline: none;
        }

        input:focus::placeholder {
            color: @comBackground;
        }

        input::placeholder {
            color: white;
        }
        /* LABEL ======================================= */

        label {
            color: #999;
            font-size: 16px;
            position: absolute;
            pointer-events: none;
            left: 0px;
            top: -5px;
            transition: 0.2s ease all;
            -moz-transition: 0.2s ease all;
            -webkit-transition: 0.2s ease all;
            font-weight: 100;
        }

        /* active state */

        input:focus ~ label, input:valid ~ label {
            top: -15px;
            font-size: 12px;
            color: rgba(16, 24, 40, 0.5);
        }

        /* active state */
        input:focus ~ .bar:before,
        input:focus ~ .bar:after {
            width: 50%;
        }

    }

    /* BOTTOM BARS ================================= */
    .rt-text-edit__bar {
        position: relative;
        display: block;
        width: 100%;

    }

    .rt-text-edit__bar:before, .rt-text-edit__bar:after {
        content: "";
        height: 2px;
        width: 0;
        bottom: 0;
        position: absolute;
        background: @comPurple;
        transition: 0.2s ease all;
        -moz-transition: 0.2s ease all;
        -webkit-transition: 0.2s ease all;

    }

    .rt-text-edit__bar:before {
        left: 50%;
    }

    .rt-text-edit__bar:after {
        right: 50%;
    }

    .rt-text-edit__error{
        font-family: 'Rostelecom Basis';
        padding-top: 5px;
        color: @comOrange;
        font-weight: 100;
        font-size: 12px;
    }
</style>