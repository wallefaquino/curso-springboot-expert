package com.walleftech.csbe.services.impl;

import com.walleftech.csbe.entities.Usuario;
import com.walleftech.csbe.exceptions.SenhaInvalidaException;
import com.walleftech.csbe.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado!"));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasConferem = encoder.matches(usuario.getSenha(), user.getPassword());

        if(senhasConferem) {
            return user;
        }

        throw new SenhaInvalidaException();
    }
}
