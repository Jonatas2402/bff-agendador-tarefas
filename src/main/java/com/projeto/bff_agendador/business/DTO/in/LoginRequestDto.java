package com.projeto.bff_agendador.business.DTO.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
    private String email;
    private String senha;
}
