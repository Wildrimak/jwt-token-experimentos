package br.com.wildrimak.jwt.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AuthController {

    @PostMapping
    public void gerarToken() {
    
    }

}
