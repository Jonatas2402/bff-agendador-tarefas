package com.projeto.bff_agendador.business;


import com.projeto.bff_agendador.business.DTO.out.TarefaDtoResponse;
import com.projeto.bff_agendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;


    public void enviaEmail(TarefaDtoResponse dto) {
         emailClient.enviarEmail(dto);
    }
}
