package com.projeto.bff_agendador.business.DTO.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDtoRequest {

    private String ddd;
    private String numero;
}
