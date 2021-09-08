package com.ayla.emqxruleenginedemo.exception;

import com.aylaasia.corecloud.common.exception.AylaExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: API 全局异常处理
 * @author: Gary.Jin
 * @create: 2021-09-02 15:21
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends AylaExceptionHandler {
    public GlobalExceptionHandler(AylaExceptionHandler.CodeConfig codeConfig) {
        super(codeConfig);
    }
    @Component
    public static class ExceptionCodeConfig implements AylaExceptionHandler.CodeConfig {

        @Override
        public int serverError() {
            return 500;
        }

        @Override
        public int badRequest() {
            return 400;
        }

        @Override
        public int uriNotFound() {
            return 404;
        }

        @Override
        public int invalidParam() {
            return 422;
        }
    }
}
