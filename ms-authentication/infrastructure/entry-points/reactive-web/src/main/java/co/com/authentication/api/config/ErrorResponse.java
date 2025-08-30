package co.com.authentication.api.config;

import lombok.Builder;


@Builder
public record ErrorResponse(String code, String message) {}