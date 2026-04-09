package com.projeto.bff_agendador.infrastructure.client;

import com.projeto.bff_agendador.business.DTO.out.TarefaDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefaDtoResponse dto);
}
