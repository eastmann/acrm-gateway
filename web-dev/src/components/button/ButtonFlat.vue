<template>
    <div class="utils-button">
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "ButtonFlat"
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .utils-button {
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        padding: 13px 32px 14px 32px;
        background: #FD4D21;
        color: @comWhite;
        size: 16px;
        border-radius: 5px;
        cursor: pointer;
        font-weight: 100;
    }
</style>