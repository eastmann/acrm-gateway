<template>
    <BaseSuggestionBoxNew  :suggestion-provider="suggestionProvider"
                        :suggestion-renderer="suggestionRenderer"
                        :default-suggestion-count="suggestionCount"
                        :style-registration="styleRegistration"
                        v-on:suggestion-selected="emitSuggestionSelected"
                        v-on:rt-focus-out="rtFocusOut"
                        :init-text="initText"
                        :disabled="disabled"
                        :caption="caption"
                        :error-info="errorInfo"
                        :hide-search-button="hideSearchButton"
                        :placeholder="placeholder"
    />
</template>

<script>
    import BaseSuggestionBoxNew from "./BaseSuggestionBoxNew";
    import CompanySuggestionProvider from "./js/provider/company-suggestion-provider"
    import CompanySuggestionRenderer from "./js/renderer/company-suggestion-renderer"

    let DEFAULT_SUGGESTIONS_COUNT = 10;

    export default {
        name: "BusinessSearchBoxNew",
        props: ["initText", "disabled", "hideSearchButton", "styleRegistration", "placeholder", "caption", 'errorInfo'],
        components: {
            BaseSuggestionBoxNew
        },
        data() {
            return {
                suggestionProvider: new CompanySuggestionProvider(),
                suggestionRenderer: new CompanySuggestionRenderer(),
                suggestionCount: DEFAULT_SUGGESTIONS_COUNT
            }
        },
        methods: {
            emitSuggestionSelected(suggestion) {
                this.$emit("suggestion-selected", suggestion)
            },

            rtFocusOut(val) {
                this.$emit("rt-focus-out", val)
            }
        }
    }
</script>
<style lang="less" >
    @import "css/search-box";
</style>
