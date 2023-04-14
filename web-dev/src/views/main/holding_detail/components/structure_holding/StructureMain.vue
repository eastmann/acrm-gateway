<template>
    <div>
        <vertical-tabs :selected-index="getDzoId()" mode="light">
            <vertical-tab title="Эталон (ЕГРЮЛ)">
                <structure v-if="holdingOwners" :holding-owners="holdingOwners.holdingOwnersDzo5" :dzo-id="5"/>
            </vertical-tab>

            <vertical-tab title="РТК">
                <structure v-if="holdingOwners" :holding-owners="holdingOwners.holdingOwnersDzo1" :dzo-id="1"/>
            </vertical-tab>

            <vertical-tab title="ЦОД">
                <structure v-if="holdingOwners" :holding-owners="holdingOwners.holdingOwnersDzo2" :dzo-id="2"/>
            </vertical-tab>

            <vertical-tab title="Солар">
                <structure v-if="holdingOwners" :holding-owners="holdingOwners.holdingOwnersDzo3" :dzo-id="3"/>
            </vertical-tab>

            <vertical-tab title="Теле2">
                <structure v-if="holdingOwners" :holding-owners="holdingOwners.holdingOwnersDzo4" :dzo-id="4"/>
            </vertical-tab>
        </vertical-tabs>
    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import paths from '../../../../../js/utils/paths'
    import VerticalTabs from "../../../../../components/tabs/vertical/VerticalTabs";
    import VerticalTab from "../../../../../components/tabs/vertical/VerticalTab";
    import table_fin from "@/js/table/table-finance";
    import Structure from "./Structure";

    export default {
        name: "StructureMain",
        components: {Structure, VerticalTab, VerticalTabs},
        props: ['holdingOwners'],
        data() {
            return {
                selectedIndex: 0,
            }
        },

        created() {

        },

        methods: {
            ...table_fin,

            getDzoId(){
                let dzoId = this.$route.query.dzo;
                if (dzoId === undefined){
                    return 0;
                }
                return (dzoId === '5')||(dzoId === '')||(dzoId === undefined) ? 0 : Number(dzoId);
            }
        }
    }

</script>

<style>
    .finaice-tab {
        margin: 30px;
    }

</style>

