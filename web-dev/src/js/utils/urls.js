export default {
    suggestionUrlForCompany(company, count) {
        return `/facade/service-search/by-all?value=${company}`;
    },

    getUser() {
        return `/auth-api/get-user`;
    },

    authCheckUrl() {
        return '/auth-api/check'
    },

    loginUrl() {
        return '/auth-api/login'
    },

    logoutUrl() {
        return '/auth-api/logout'
    },

    getRolesUrl() {
        return '/auth-api/get-roles'
    },

    loadHoldingsUrl() {
        return `/facade/holdings/`;
    },

    loadDetailInfoUrl(hid, id, dzoId) {
        let dzoIdUrl = '';
        if (dzoId){
            dzoIdUrl = '?dzo=' + dzoId;
        }
        if ((hid !== null) && (hid !== '') && (hid !== undefined)){
            return `/facade/detail-info/by-hid?hid=${hid}`;
        }

        return `/facade/detail-info/by-id?id=${id}` + dzoIdUrl;
    },

    getAuthoritiesUrl() {
        return `/auth-api/get-roles`;
    },
}