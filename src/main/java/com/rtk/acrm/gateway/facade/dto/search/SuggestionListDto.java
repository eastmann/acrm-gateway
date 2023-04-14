package com.rtk.acrm.gateway.facade.dto.search;

import java.util.List;

public class SuggestionListDto {
    private List<? extends SuggestionDto> suggestions;

    public List<? extends SuggestionDto> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<? extends SuggestionDto> suggestions) {
        this.suggestions = suggestions;
    }
}
