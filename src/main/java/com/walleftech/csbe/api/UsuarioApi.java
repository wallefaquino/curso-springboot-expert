package com.walleftech.csbe.api;

import com.walleftech.csbe.entities.Usuario;
import com.walleftech.csbe.entities.dto.CredenciaisDTO;
import com.walleftech.csbe.entities.dto.TokenDTO;
import com.walleftech.csbe.entities.dto.UsuarioDTO;
import com.walleftech.csbe.exceptions.SenhaInvalidaException;
import com.walleftech.csbe.security.JwtService;
import com.walleftech.csbe.services.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioApi {

    private final UserDetailsServiceImpl service;
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@Valid @RequestBody Usuario usuario) {
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        service.salvar(usuario);
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .build();
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();

            UserDetails usuarioAutenticado = userDetailsService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
