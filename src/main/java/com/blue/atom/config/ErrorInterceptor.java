package com.blue.atom.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorInterceptor implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        // 部署到管理后台的配置
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        errorPageRegistry.addErrorPages(e404);
    }
}
