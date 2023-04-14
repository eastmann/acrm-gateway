<template>
    <div class="scrollbar holding-detail-structure__structure">
        <div class="scrollbar holding-detail-structure__level">
            <div v-if="holdingOwnersLevel1Selected" class="holding-detail-structure__selected">
                <div class="holding-detail-structure__selected-text">Выбрано</div>
                <structure-item :item="holdingOwnersLevel1Selected"/>
            </div>

            Уровень 1
            <div class="holding-detail-structure__frame">
                <div v-for="(item, index) in holdingOwnersLevel1" :key="index">
                    <structure-item v-on:rt-click="clickLevel1" :item="item"/>
                </div>
            </div>
        </div>

        <div class="scrollbar holding-detail-structure__level">
            <div v-if="holdingOwnersLevel2Selected" class="holding-detail-structure__selected">
                <div class="holding-detail-structure__selected-text">Выбрано</div>
                <structure-item :item="holdingOwnersLevel2Selected"/>
            </div>

            Уровень 2
            <div class="scrollbar holding-detail-structure__frame">
                <div v-for="(item, index) in holdingOwnersLevel2" :key="index">
                    <structure-item v-on:rt-click="clickLevel2" :item="item"/>
                </div>
            </div>
        </div>

        <div class="scrollbar holding-detail-structure__level">
            <div v-if="holdingOwnersLevel3Selected" class="holding-detail-structure__selected">
                <div class="holding-detail-structure__selected-text">Выбрано</div>
                <structure-item :item="holdingOwnersLevel3Selected"/>
            </div>

            Уровень 3
            <div class="scrollbar holding-detail-structure__frame">
                <div v-for="(item, index) in holdingOwnersLevel3" :key="index">
                    <structure-item v-on:rt-click="clickLevel3" :item="item"/>
                </div>
            </div>
        </div>

        <div class="scrollbar holding-detail-structure__level">
            <div v-if="holdingOwnersLevel4Selected" class="holding-detail-structure__selected">
                <div class="holding-detail-structure__selected-text">Выбрано</div>
                <structure-item :item="holdingOwnersLevel4Selected"/>
            </div>

            Уровень 4
            <div class="scrollbar holding-detail-structure__frame">
                <div v-for="(item, index) in holdingOwnersLevel4" :key="index">
                    <structure-item v-on:rt-click="clickLevel4" :item="item"/>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
    import axios from 'axios';
    import urls from "@/js/utils/urls";
    import StructureItem from "@/views/main/holding_detail/components/structure_holding/StructureItem";

    export default {
        name: "Structure",
        components: {StructureItem},
        props:{
            'holdingOwners': [Object, Array],
            'dzoId': [String, Number],
        },
        data() {
            return {
                holdingOwnersLevel1Selected: null,
                holdingOwnersLevel2Selected: null,
                holdingOwnersLevel3Selected: null,
                holdingOwnersLevel4Selected: null,
            }
        },

        created() {
            this.setLevelsSelected();
        },

        watch: {
            // selectedIndex(){
            //     this.holdingOwnersLevel1Selected = null;
            //     this.holdingOwnersLevel2Selected = null;
            //     this.holdingOwnersLevel3Selected = null;
            //     this.holdingOwnersLevel4Selected = null;
            //     this.clearLevel1();
            //     this.clearLevel2();
            //     this.clearLevel3();
            //     this.clearLevel4();
            // }
        },

        computed: {
            holdingOwnersLevel1(){
              return this.holdingOwners.holdingOwnersLevel1
                  .filter(x => x.dzoId === this.dzoId);
            },

            holdingOwnersLevel2(){
                if (this.holdingOwnersLevel1Selected == null){
                    return null;
                }

                return this.holdingOwners.holdingOwnersLevel2
                    .filter(x => (x.parentId === this.holdingOwnersLevel1Selected.id) && (x.dzoId === this.dzoId));
            },

            holdingOwnersLevel3(){
                if (this.holdingOwnersLevel2Selected == null){
                    return null;
                }

                return this.holdingOwners.holdingOwnersLevel3
                    .filter(x => (x.parentId === this.holdingOwnersLevel2Selected.id) && (x.dzoId === this.dzoId));
            },

            holdingOwnersLevel4(){
                if (this.holdingOwnersLevel3Selected == null){
                    return null;
                }

                return this.holdingOwners.holdingOwnersLevel4
                    .filter(x => (x.parentId === this.holdingOwnersLevel3Selected.id) && (x.dzoId === this.dzoId));
            },
        },

        methods: {
            clearLevel1() {
                for (let index in this.holdingOwners.holdingOwnersLevel1) {
                    this.holdingOwners.holdingOwnersLevel1[index].selected = false;
                }
            },

            clearLevel2() {
                for (let index in this.holdingOwners.holdingOwnersLevel2) {
                    this.holdingOwners.holdingOwnersLevel2[index].selected = false;
                }
            },

            clearLevel3() {
                for (let index in this.holdingOwners.holdingOwnersLevel3) {
                    this.holdingOwners.holdingOwnersLevel3[index].selected = false;
                }
            },

            clearLevel4() {
                for (let index in this.holdingOwners.holdingOwnersLevel4) {
                    this.holdingOwners.holdingOwnersLevel4[index].selected = false;
                }
            },

            clickLevel1(val){
                if (val === this.holdingOwnersLevel1Selected){
                    return;
                }
                this.clearLevel1();
                this.clearLevel2();
                this.clearLevel3();
                this.clearLevel4();

                this.holdingOwnersLevel1Selected = val;
                this.level1Selected();
            },

            clickLevel2(val){
                if (val === this.holdingOwnersLevel2Selected){
                    return;
                }
                this.clearLevel2();
                this.clearLevel3();
                this.clearLevel4();
                this.holdingOwnersLevel2Selected = val;
                this.level2Selected();
            },

            clickLevel3(val){
                if (val === this.holdingOwnersLevel3Selected){
                    return;
                }
                this.clearLevel3();
                this.clearLevel4();

                this.holdingOwnersLevel3Selected = val;
                this.level3Selected();
            },

            clickLevel4(val){
                if (val === this.holdingOwnersLevel4Selected){
                    return;
                }
                this.clearLevel4();

                this.holdingOwnersLevel4Selected = val;
                this.level4Selected();
            },

            setLevelsSelected(){
                for (let index in this.holdingOwners.holdingOwnersLevel1) {
                    if (this.holdingOwners.holdingOwnersLevel1[index].selected){
                        this.holdingOwnersLevel1Selected = this.holdingOwners.holdingOwnersLevel1[index];
                        break;
                    }
                }

                for (let index in this.holdingOwners.holdingOwnersLevel2) {
                    if (this.holdingOwners.holdingOwnersLevel2[index].selected){
                        this.holdingOwnersLevel2Selected = this.holdingOwners.holdingOwnersLevel2[index];
                        break;
                    }
                }

                for (let index in this.holdingOwners.holdingOwnersLevel3) {
                    if (this.holdingOwners.holdingOwnersLevel3[index].selected){
                        this.holdingOwnersLevel3Selected = this.holdingOwners.holdingOwnersLevel3[index];
                        break;
                    }
                }

                for (let index in this.holdingOwners.holdingOwnersLevel4) {
                    if (this.holdingOwners.holdingOwnersLevel4[index].selected){
                        this.holdingOwnersLevel4Selected = this.holdingOwners.holdingOwnersLevel4[index];
                        break;
                    }
                }
            },

            level1Selected(){
                if (this.holdingOwnersLevel1Selected !== null) {
                    this.holdingOwnersLevel1Selected.selected = true;
                }

                this.holdingOwnersLevel2Selected = null;
                this.holdingOwnersLevel3Selected = null;
                this.holdingOwnersLevel4Selected = null;
            },

            level2Selected(){
                if (this.holdingOwnersLevel2Selected !== null) {
                    this.holdingOwnersLevel2Selected.selected = true;
                }

                this.holdingOwnersLevel3Selected = null;
                this.holdingOwnersLevel4Selected = null;
            },

            level3Selected(){
                if (this.holdingOwnersLevel3Selected !== null) {
                    this.holdingOwnersLevel3Selected.selected = true;
                }

                this.holdingOwnersLevel4Selected = null;
            },

            level4Selected(){
                if (this.holdingOwnersLevel4Selected !== null) {
                    this.holdingOwnersLevel4Selected.selected = true;
                }
            }

        }
    }

</script>

<style lang="less">

    .holding-detail-structure__structure {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        /*text-align: center;*/
        /*grid-column-gap: 10px;*/
        /*min-height: 600px;*/

    }

    .holding-detail-structure__level {
        color: rgba(16, 24, 40, 0.7);
        padding: 20px;
        border: 1px solid rgba(16, 24, 40, 0.1);

        scroll-behavior: smooth;
        height: calc(100vh - 215px);

        overflow-x: auto;
        overflow-y: auto;
        /*overflow: visible;*/
        /*float: left;*/

    }

    .holding-detail-structure__frame {
        /*border: 2px dashed rgba(16, 24, 40, 0.1);*/
        margin-top: 20px;
        border-radius: 12px;
        /*min-height: 550px;*/
        /*text-align: center;*/
    }

    .holding-detail-structure__selected {
        border-bottom: 1px solid rgba(16, 24, 40, 0.1);
        padding-bottom: 20px;
        margin-bottom: 20px;
    }

    .holding-detail-structure__selected-text{
        padding-bottom: 20px;
    }

    /*.scrollbar::-webkit-scrollbar-track {*/
    /*    !*background: transparent;*!*/
    /*}*/

    .scrollbar::-webkit-scrollbar-thumb {
        background: #B5B7C0;
        box-shadow: 0px 0px 8px rgba(28, 41, 61, 0.05), 0px 2px 4px rgba(28, 41, 61, 0.06);
        border-radius: 3px;
        border: 2px solid transparent;
        background-clip: padding-box; // <== make the border work
    }

    .scrollbar::-webkit-scrollbar-thumb:hover {
        border: 0;
    }

    .scrollbar::-webkit-scrollbar {
        width: 8px;
        background: rgba(16, 24, 40, 0.1);
        border-radius: 3px;
        border: 2px solid white;
    }
    /*.scrollbar::-webkit-scrollbar-thumb:active {*/

    /*}*/

    /*.scrollbar::-webkit-scrollbar:hover {*/
    /*  */
    /*}*/
</style>

