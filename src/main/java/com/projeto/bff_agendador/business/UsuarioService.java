package com.projeto.bff_agendador.business;

import com.projeto.bff_agendador.business.DTO.in.EnderecoDtoRequest;
import com.projeto.bff_agendador.business.DTO.in.LoginRequestDto;
import com.projeto.bff_agendador.business.DTO.in.TelefoneDtoRequest;
import com.projeto.bff_agendador.business.DTO.in.UsuarioDtoRequest;
import com.projeto.bff_agendador.business.DTO.out.EnderecoDtoResponse;
import com.projeto.bff_agendador.business.DTO.out.TelefoneDtoResponse;
import com.projeto.bff_agendador.business.DTO.out.UsuarioDtoResponse;
import com.projeto.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDtoResponse salvaUsuario(UsuarioDtoRequest usuarioDTO) {
       return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDto loginRequestDto){
        return client.login(loginRequestDto);
    }


    public UsuarioDtoResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletarPorEmail(String email, String token) {
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDtoResponse atualizaDadosDeUsuario(String token, UsuarioDtoRequest usuarioDTO){
       return client.atualizaDadosDeUsuario(usuarioDTO, token);
    }
    public EnderecoDtoResponse atualizaEndereco(Long idEndereco, EnderecoDtoRequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDtoResponse atualizaTelefone(Long idTelefone, TelefoneDtoRequest telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO,idTelefone, token);
    }
    public EnderecoDtoResponse cadastraEndereco(String token, EnderecoDtoRequest dto){
       return client.cadastraEndereco(dto, token);
    }

    public TelefoneDtoResponse cadastraTelefone(String token, TelefoneDtoRequest dto){
       return client.cadastraTelefone(dto, token);
    }
}