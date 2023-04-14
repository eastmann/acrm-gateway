package com.rtk.acrm.gateway.utils;

import org.springframework.lang.NonNull;

public class AuthUtils {
    public static String authHeaderForToken(@NonNull String token) {
        return "Bearer "+token;
    }
}
