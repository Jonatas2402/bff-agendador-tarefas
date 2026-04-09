package com.projeto.bff_agendador.infrastructure.client;

import com.projeto.bff_agendador.business.DTO.in.EnderecoDtoRequest;
import com.projeto.bff_agendador.business.DTO.in.LoginRequestDto;
import com.projeto.bff_agendador.business.DTO.in.TelefoneDtoRequest;
import com.projeto.bff_agendador.business.DTO.in.UsuarioDtoRequest;
import com.projeto.bff_agendador.business.DTO.out.EnderecoDtoResponse;
import com.projeto.bff_agendador.business.DTO.out.TelefoneDtoResponse;
import com.projeto.bff_agendador.business.DTO.out.UsuarioDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDtoResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDtoResponse salvaUsuario(@RequestBody UsuarioDtoRequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDto usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);


    @PutMapping
    UsuarioDtoResponse atualizaDadosDeUsuario(@RequestBody UsuarioDtoRequest dto,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDtoResponse atualizaEndereco(@RequestBody EnderecoDtoRequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDtoResponse atualizaTelefone(@RequestBody TelefoneDtoRequest dto,
                                         @RequestParam Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDtoResponse cadastraEndereco(@RequestBody EnderecoDtoRequest dto,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDtoResponse cadastraTelefone(@RequestBody TelefoneDtoRequest dto,
                                        @RequestHeader("Authorization") String token);
}
