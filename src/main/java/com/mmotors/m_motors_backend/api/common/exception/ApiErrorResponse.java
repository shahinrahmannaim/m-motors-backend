package com.mmotors.m_motors_backend.api.common.exception;

import java.time.Instant;
import java.util.List;

public record ApiErrorResponse(

        Instant timestamp,

        int status,

        String error,

        String message,

        List<String> errors,

        String path
) {
}