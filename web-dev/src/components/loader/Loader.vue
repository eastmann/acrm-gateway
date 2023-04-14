<template>
    <span class="loader"></span>
</template>

<script>
    export default {
        name: "Loader",
    }
</script>

<style lang="less">
    @import "../../css/colors";

    .loader {
        width: 8px;
        height: 40px;
        border-radius: 4px;
        display: block;
        margin: 20px auto;
        position: relative;
        background: rgba(16, 24, 40, 0.5);
        color: #FFF;
        box-sizing: border-box;
        animation: animloader 0.3s 0.3s linear infinite alternate;
    }

    .loader::after, .loader::before {
        content: '';
        width: 8px;
        height: 40px;
        border-radius: 4px;
        background: rgba(16, 24, 40, 0.5);
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        left: 20px;
        box-sizing: border-box;
        animation: animloader 0.3s  0.45s  linear infinite alternate;
    }

    .loader::before {
        left: -20px;
        animation-delay: 0s;
    }

    @keyframes animloader {
        0%   { height: 48px}
        100% { height: 4px}
    }

</style>