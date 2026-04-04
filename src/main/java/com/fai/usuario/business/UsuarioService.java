package com.fai.usuario.business;

import com.fai.usuario.business.converter.UsuarioConverter;
import com.fai.usuario.business.dto.UsuarioDTO;
import com.fai.usuario.infrastructure.entity.Usuario;
import com.fai.usuario.infrastructure.exeptions.ConlictException;
import com.fai.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return  usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConlictException("Email já cadastrado"  + email);
            }
        }catch (ConlictException e){
            throw new ConlictException("Email já cadastrado" + e.getCause());
        }
    }

    public  boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

}


