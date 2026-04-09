package com.projeto.bff_agendador.business.DTO.out;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDtoResponse {
    private Long id;
    private String ddd;
    private String numero;
}
