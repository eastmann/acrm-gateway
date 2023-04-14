import urls from "./urls";
import axios from "axios";
import {SET_AUTHORITY} from "../../store/data/arm-spark";

export default {

    isAuthorized() {
        if (this.authorities === null) {
            return false;
        }
        return this.authorities.includes("ROLE_AUTH")
    },

    isRolePayment() {
        if (this.authorities === null) {
            return false;
        }
        return this.authorities.includes("ROLE_PAYMENT")
    },

    isRoleAdmin() {
        if (this.authorities === null) {
            return false;
        }
        return this.authorities.includes("ROLE_ADMIN")
    },

    setAuthorities() {
        let $this = this
        let url = urls.getAuthoritiesUrl();
        axios.get(url)
            .then(data => {
                //let res = data.data.map(el => el.authority)
                this.$store.commit(SET_AUTHORITY, data.data.roles)
            })
            .catch(e => {
                //console.log(e)
            })
    },

    async setAuthoritiesAsync() {
        let $this = this
        let url = urls.getAuthoritiesUrl();
        await axios.get(url)
            .then(data => {
                // let res = data.data.map(el => el.authority)
                this.$store.commit(SET_AUTHORITY, data.data.roles)
            })
            .catch(e => {

            })
    },
}