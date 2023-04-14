<template>
    <div>
    <span
            class="toggle-wrapper"
            role="checkbox"
            :aria-checked="value.toString()"
            tabindex="0"
            v-on:click="toggle"
            @keydown.space.prevent="toggle">

      <span class="toggle-background" :class="backgroundStyles">
          <div class="toggle__left-title">{{rightText}}</div>
      </span>

      <span class="toggle-indicator" :style="indicatorStyles">
          <div class="toggle__rigth-title">{{leftText}}</div>
      </span>

    </span>
    </div>
</template>

<script>
    export default {
        props: {
            value: {
                type: Boolean,
                required: true
            },
            rightLabel: {
                type: String,
            },
            leftLabel: {
                type: String,
            }
        },

        computed: {
            backgroundStyles() {
                return {
                    'gold-mid': this.value,
                    'gray-lighter': !this.value
                };
            },
            indicatorStyles() {
                return {transform: this.value ? 'translateX(90px)' : 'translateX(0)'};
            }
        },

        data () {
            return {
                rightText : this.rightLabel,
                leftText: this.leftLabel,
            }
        },

        methods: {
            toggle() {
                let tmp = this.leftText;
                this.leftText = this.rightText;
                this.rightText = tmp;
                this.$emit('input', !this.value);
            }
        }
    };
</script>
<style lang="less">
    @import "../../src/css/colors";

    .toggle__left-title {
        padding-top: 6px;
        padding-left: 14px;
        height: 100%;
    }

    .toggle__rigth-title {
        padding-top: 4px;
        padding-right: 12px;
        padding-left: 12px;
        height: 100%;
    }

    .gold-mid {
        background: rgba(16, 24, 40, 0.05);
        text-align: left;
    }

    .gray-lighter {
        background: rgba(16, 24, 40, 0.05);
        justify-content: right;
        padding-right: 14px;
    }

    .toggle-wrapper {
        display: inline-block;
        position: relative;
        cursor: pointer;
        width: 152px;
        height: 32px;
        border-radius: 8px;
    }

    .toggle-wrapper:focus {
        outline: 0;
    }

    .toggle-background {
        display: flex;
        border-radius: 8px;
        height: 100%;
        width: 100%;
        /*box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);*/
        /*transition: background-color .4s ease;*/
    }

    .toggle-indicator {
        position: absolute;
        height: 28px;
        left: 2px;
        bottom: 2px;
        background: #FFFFFF;
        box-shadow: 0px 0px 1px rgba(28, 41, 61, 0.2), 0px 2px 4px rgba(28, 41, 61, 0.12);
        border-radius: 6px;
        /*transition: transform .4s ease;*/
    }

</style>