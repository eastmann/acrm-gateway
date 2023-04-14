import {SET_ARM_IS_SHOW, SET_AUTHORITY, SET_MODAL_SHOW} from './data/arm-spark'

const state = {
    armIsShow: true,
    authorities: null,
    isModalShow: false
}

const getters = {
    armIsShow: state => state.armIsShow,
    authorities: state => state.authorities,
    isModalShow: state => state.isModalShow
}

const mutations = {
    [SET_ARM_IS_SHOW] (state, payload) {
        state.armIsShow = payload
    },
    [SET_AUTHORITY] (state, payload) {
        state.authorities = payload
    },
    [SET_MODAL_SHOW] (state, payload) {
        state.isModalShow = payload
    }
}

export default {
    state,
    getters,
    mutations
}