package com.projeto.bff_agendador.business.DTO.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDtoRequest {

    private String rua;
    private Long numero;
    private String cidade;
    private String estado;
    private String cep;
}
