package com.projeto.bff_agendador.business;

import com.projeto.bff_agendador.business.DTO.EnderecoDTO;
import com.projeto.bff_agendador.business.DTO.TelefoneDTO;
import com.projeto.bff_agendador.business.DTO.UsuarioDTO;
import com.projeto.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
       return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletarPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosDeUsuario(String token, UsuarioDTO usuarioDTO){
       return client.atualizaDadosDeUsuario(usuarioDTO, token);
    }
    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO,idTelefone, token);
    }
    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto){
       return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){
       return client.cadastraTelefone(dto, token);
    }
}