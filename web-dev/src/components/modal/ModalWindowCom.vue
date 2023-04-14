<template>
    <div v-if="show || isModalShow" class="modal-shadow-com" @click.self="closeModal">
        <div class="modal-com">
            <div class="modal-com-close" @click="closeModal">&#10006;</div>
            <slot name="title">
<!--                <h3 class="modal-title">Заголовок</h3>-->
            </slot>
            <slot name="body">
                <div class="modal-content">

                </div>
            </slot>
            <slot name="footer">
                <div class="modal-footer">
<!--                    <button class="modal-footer__button" @click="closeModal">-->
<!--                        Ок-->
<!--                    </button>-->
                </div>
            </slot>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from "vuex";
    import {SET_MODAL_SHOW} from "@/store/data/arm-spark";

    export default {
        name: "ModalWindowCom",
        data: function () {
            return {
                show: false
            }
        },
        computed: {
            ...mapGetters(['isModalShow'])
        },
        methods: {
            closeModal: function () {
                this.show = false
                this.$store.commit(SET_MODAL_SHOW, false)
            }
        }
    }
</script>

<style lang="less">
    @import "../../../css/colors";
    .modal-shadow-com {
        position: absolute;
        top: 0;
        left: 0;
        min-height: 100%;
        width: 100%;
        background: rgba(0, 0, 0, 0.3);
        z-index: 1;
    }

    .modal-com {
        background: @comWhite;
        border-radius: 5px;
        padding: 32px 62px;
        margin-top: 26px;
        /*box-shadow: rgba(0, 0, 0, 0.54) 0px 3px 20px;*/

        background: #fff;

        border-style: none;

        /*min-width: 420px;*/
        /*max-width: 480px;*/
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);

        &-close {
            border-radius: 50%;
            color: black;
            background: @comWhite;
            display: flex;
            align-items: center;
            justify-content: center;
            position: absolute;
            top: 7px;
            right: 7px;
            width: 30px;
            height: 30px;
            cursor: pointer;
        }

        &-title {
            color: #0971c7;
        }

        &-content {
            margin-bottom: 20px
        }

        &-footer {
            &__button {
                display: inline-block;
                margin-left: 10px;
                font-size: 16px;
                font-weight: normal;

                padding: 8px 20px 8px;
                border-radius: 5px;
                border: 2px solid @baseViolet;
                background-color: #fefefe;
                color: @baseViolet;
                cursor: pointer;
            }
        }
    }
</style>