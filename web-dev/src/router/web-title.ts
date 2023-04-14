import Vue from "vue";

let initWebTitle = function(router) {

    router.afterEach((to, from) => {
        Vue.nextTick(() => {
            if (to.params.webTitle != null) {
                // @ts-ignore
                document.title = to.params.webTitle
            } else {
                document.title = window.location.href
            }
        })
    })

};

export default initWebTitle;

