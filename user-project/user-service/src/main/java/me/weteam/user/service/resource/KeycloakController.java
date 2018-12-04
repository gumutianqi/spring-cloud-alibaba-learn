/*
 * Copyright (c) 2018. WeTeam Inc. All Rights Reserved.
 *
 */

package me.weteam.user.service.resource;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Keycloak Resource
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2018/12/4 19:53
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
@Validated
@RefreshScope
@RestController
public class KeycloakController {

    @GetMapping("/index")
    public String index() {
        return "This is index page.";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "your are logout.";
    }

    @GetMapping("/user/index")
    public String userIndex(HttpServletRequest request) {



        return "This is user index page.";
    }

    @GetMapping("/admin/index")
    public String adminIndex(HttpServletRequest request) {
        return "This is admin index page.";
    }


}
