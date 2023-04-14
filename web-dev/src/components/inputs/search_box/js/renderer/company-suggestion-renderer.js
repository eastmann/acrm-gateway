import SuggestionRenderer from "./suggestion-renderer";

class CompanySuggestionRenderer extends SuggestionRenderer{
    renderSuggestion(suggestion) {
        if(suggestion.data != null) {
            let data = suggestion.data;
            return `<div class="company-suggestion">
                    <div class="acrm__container">
                        <div class="company-suggestion-name">${data.name}</div>  
                        <div class="company-suggestion-status">${data.status}</div>                 
                    </div>
                    <div class="acrm__container">
                        <div class="company-suggestion-address">${data.address}</div>
                    </div> 
                    <hr class="hr">             
                </div>`
        } else {
            return ``;
        }
    }
}
export default CompanySuggestionRenderer;