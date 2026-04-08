package com.projeto.bff_agendador.infrastructure.client;

import com.projeto.bff_agendador.business.DTO.TarefaDTO;
import com.projeto.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {
    @PostMapping
    TarefaDTO salvaTarefa(@RequestBody TarefaDTO dto,
                          @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefa(@PathVariable("id") String id,
                       @RequestHeader("Authorization") String token);
    /*DateTimeFormat = Formata os dados de data para um formato reconhecido no banco de dados*/
    @GetMapping("/{eventos}")
    List<TarefaDTO> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDTO> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTO alteraStatusNotificacao(@RequestParam("Status") StatusNotificacaoEnum status,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDTO atualizaTarefa(@RequestBody TarefaDTO dto,
                             @RequestParam("id") String id,
                             @RequestHeader("Authorization") String token);
}
