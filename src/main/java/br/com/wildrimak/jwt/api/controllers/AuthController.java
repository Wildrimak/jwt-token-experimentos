package br.com.wildrimak.jwt.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wildrimak.jwt.api.requests.AutenticationRequest;

@RestController
@RequestMapping("/autenticar")
public class AuthController {

    @PostMapping
    public void gerarToken(@Valid @RequestBody AutenticationRequest autenticationRequest) {

    }

}
