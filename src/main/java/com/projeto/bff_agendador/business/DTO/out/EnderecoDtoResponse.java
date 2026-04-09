package com.projeto.bff_agendador.business.DTO.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDtoResponse {
    private Long id;
    private String rua;
    private Long numero;
    private String cidade;
    private String estado;
    private String cep;
}
