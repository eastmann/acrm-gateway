<!--
    Суть задумки: поле с выпадающими подсказками
     - требует написания двух компонентов:
        - provider - класс, который подгружат подсказки с сервера, наследуется от SuggestionProvider
        - renderer - класс, который возвращает html-шаблон одной подсказки, наследуется от SuggestionRenderer
     - Виджет кидает событие suggestion-selected с аргументом объекта подсказки, в случае если подсказка выбрана мышью
     или по нажатию Enter

    Такая задумка позволяет отображать подсказки из разных источников в разном виде,
    сохраняя при этом управление стрелками, события по клавишам и остальную логику непосредственно виджета
    подсказок.
-->
<template>
    <div class="search-box" @click="onClickHideSuggestion">
        <div class="search-box-input-line">
            <input class="nosubmit-search" type="text" v-model="enteredText" v-on:keyup="onKeyUp" />
<!--            <div class="search-box-button" v-on:click="onButtonClick(enteredText)">Поиск</div>-->
        </div>
        <div v-if="suggestionsShown" class="suggestions">
            <div class="error-message" v-if="errorMessageShown">{{errorMessage}}</div>
            <div v-for="(suggestion, index) in suggestions"
                 v-bind:key="index"
                 v-on:click="onSuggestionClick(suggestion)"
                 v-on:mouseenter="selectSuggestion(index)"
                 :class="suggestionClass(index)"
                 v-html="renderSuggestion(suggestion)" />
        </div>
    </div>
</template>

<script>
    import SuggestionRenderer from "./js/renderer/suggestion-renderer";
    import SuggestionProvider from "./js/provider/suggestion-provider";

    let DEFAULT_SUGGESTIONS_COUNT = 10;

    export default {
        name: "SearchBox",
        props: {
            initText: String,
            disabled: Boolean,
            suggestionRenderer: SuggestionRenderer,
            suggestionProvider: SuggestionProvider,
            defaultSuggestionCount: {
                required: false,
                default: DEFAULT_SUGGESTIONS_COUNT
            }
        },
        data() {
            return {
                suggestionsUrl: "/factor/suggestions",
                suggestionsShown: false,
                enteredText: '',
                suggestions: [],
                selectedSuggestionIndex: -1,
                errorMessageShown: false,
                errorMessage: ''
            }
        },
        watch: {
            initText: function(newVal) {
                this.enteredText = newVal;
            }
        },

        mounted() {
            if (this.mode === 'manual' && this.value) {
                this.show()
            }
            document.addEventListener('click', this.onClickHideSuggestion, false)
        },

        destroyed() {
            document.removeEventListener('click', this.onClickHideSuggestion, false)
        },

        methods: {
            onClickHideSuggestion(evt){
                if (!this.$el.contains(evt.target)) {
                    this.hideSuggestions();
                }
            },

            onKeyUp(e) {
                if(e.key === 'Enter') {
                    if(this.selectedSuggestionIndex !== -1) {
                        let currentSuggestion = this.suggestions[this.selectedSuggestionIndex];
                        this.onSuggestionClick(currentSuggestion);
                    } else if(this.suggestions.length > 0) {
                        this.onSuggestionClick(this.suggestions[0]);
                    } else {
                        this.hideSuggestions();
                    }
                    this.hideSuggestions();
                    return;
                }
                if(e.key === 'Escape') {
                    this.hideSuggestions();
                    return;
                }
                if(e.key === 'ArrowUp') {
                    this.moveSelectionUp();
                }
                if(e.key === 'ArrowDown') {
                    this.moveSelectionDown();
                }
                this.loadSuggestions();
            },

            moveSelectionDown() {
                if(this.selectedSuggestionIndex >= this.suggestions.length - 1) {
                    this.selectedSuggestionIndex = 0;
                } else {
                    this.selectedSuggestionIndex++;
                }
            },

            moveSelectionUp() {
                if(this.selectedSuggestionIndex <= 0) {
                    this.selectedSuggestionIndex = this.suggestions.length - 1;
                } else {
                    this.selectedSuggestionIndex--;
                }
            },

            selectSuggestion(index) {
                this.selectedSuggestionIndex = index;
            },

            loadSuggestions() {
                var component = this;
                this.suggestionProvider.getSuggestions(this.enteredText, DEFAULT_SUGGESTIONS_COUNT, {
                    onSuggestionsLoaded(data) {
                        component.errorMessageShown = false;
                        component.suggestions = data.suggestions;
                        component.suggestionsShown = true;

                        if(component.selectedSuggestionIndex > component.suggestions.length) {
                            component.selectedSuggestionIndex = -1;                        }
                    },

                    onError(error) {
                        component.showError("< Сервис подсказок недоступен >");
                    }
                });
            },

            showError(error) {
                this.suggestionsShown = true;
                this.suggestions = [];
                this.errorMessageShown = true;
                this.errorMessage = error;
            },
            emitSelectedEvent(text) {
                this.$emit("suggestion-selected", text)
            },
            suggestionUrlForAddress(address, count) {
                return this.suggestionsUrl + "?address="+address+"&count="+count;
            },
            hideSuggestions() {
                this.suggestionsShown = false;
                this.selectedSuggestionIndex = -1;
            },
            showSuggestions() {
                this.suggestionsShown = true;
            },
            onButtonClick(suggestion){
                // if ((this.suggestionsShown) && (this.enteredText === '')) {
                //   this.enteredText = suggestion.value;
                // }
                this.loadSuggestions();
            },
            onSuggestionClick(suggestion) {
                this.enteredText = suggestion.value;
                this.hideSuggestions();
                this.emitSelectedEvent(suggestion);
            },

            suggestionClass(index) {
                if(index === this.selectedSuggestionIndex) {
                    return "suggestion selected"
                } else {
                    return "suggestion"
                }
            },

            renderSuggestion(data) {
                return this.suggestionRenderer.renderSuggestion(data);
            }
        }
    }
</script>

<style scoped>
    nosubmit-search {
        border: none;
        padding-top: 15px;
        padding-right: 20px;
    }

    input.nosubmit-search {
        padding: 9px 4px 9px 40px;
        background: transparent url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' class='bi bi-search' viewBox='0 0 16 16'%3E%3Cpath d='M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z'%3E%3C/path%3E%3C/svg%3E") no-repeat 13px center;
        width: 400px;
        height: 32px;
        background-color: rgba(16, 24, 40, 0.05);
        border-radius: 8px;
        border: none;
        border-style: solid;
    }

</style>