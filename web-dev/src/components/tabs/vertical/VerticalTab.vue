<template lang="html">
    <div class='vertical__tab' v-show='isActive'>
        <slot></slot>
    </div>
</template>

<script>
    export default {
        name: "VerticalTab",
        props: {
            title: {
                type: String,
                default: 'Tab'
            }
        },
        data () {
            return {
                isActive: true
            }
        }
    }
</script>

<style lang="css">
</style>