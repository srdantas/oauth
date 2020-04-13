package com.data.science.net.oauth.gateway.http.controller;

import com.data.science.net.oauth.usercase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class UserCreateGatewayController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public void execute(@RequestHeader final String username,
                        @RequestHeader final String password) {
        createUserUseCase.execute(username, password);
    }
}
