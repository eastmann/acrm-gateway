package com.rtk.acrm.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.rtk.acrm.gateway.session.HttpSession;
import org.springframework.stereotype.Component;
import static com.rtk.acrm.gateway.utils.AuthUtils.authHeaderForToken;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Фильтр добавляет хэдер с токеном авторизации для доступа к другим серверам
 */
@Component
public class AuthHeaderAddingFilter extends ZuulFilter {

    private final HttpSession session;

    public AuthHeaderAddingFilter(HttpSession session) {
        this.session = session;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        if(session.getAccessToken() != null) {
            ctx.addZuulRequestHeader("Authorization", authHeaderForToken(session.getAccessToken()));
        }

        return null;
    }

}
