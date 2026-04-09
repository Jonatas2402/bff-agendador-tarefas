package com.projeto.bff_agendador.business.DTO.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDtoRequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDtoRequest> enderecos;
    private List<TelefoneDtoRequest> telefones;
}
