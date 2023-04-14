import Vue from 'vue';
import Vuex from 'vuex';
import arm from './arm-spark'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {},
    getters: {},
    mutations: {},
    actions: {},
    modules: {
        arm,
    }
});

export default store