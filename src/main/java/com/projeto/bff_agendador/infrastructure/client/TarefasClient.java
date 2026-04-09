package com.projeto.bff_agendador.infrastructure.client;

import com.projeto.bff_agendador.business.DTO.in.TarefaDtoRequest;
import com.projeto.bff_agendador.business.DTO.out.TarefaDtoResponse;
import com.projeto.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {
    @PostMapping
    TarefaDtoResponse salvaTarefa(@RequestBody TarefaDtoRequest dto,
                                  @RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    void deletarTarefa(@PathVariable("id") String id,
                       @RequestHeader(name = "Authorization", required = false) String token);
    /*DateTimeFormat = Formata os dados de data para um formato reconhecido no banco de dados*/
    @GetMapping("/{eventos}")
    List<TarefaDtoResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaDtoResponse> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    TarefaDtoResponse alteraStatusNotificacao(@RequestParam("Status") StatusNotificacaoEnum status,
                                              @RequestParam("id") String id,
                                              @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    TarefaDtoResponse atualizaTarefa(@RequestBody TarefaDtoRequest dto,
                                     @RequestParam("id") String id,
                                     @RequestHeader(name = "Authorization", required = false) String token);
}
