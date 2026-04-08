package com.projeto.bff_agendador.controller;


import com.projeto.bff_agendador.business.DTO.TarefaDTO;
import com.projeto.bff_agendador.business.TarefaService;
import com.projeto.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "tarefa", description = "criação de tarefas")
public class TarefaController {

    private final TarefaService service;

    @PostMapping
    @Operation(summary = "Grava tarefa", description = "Salva tarefa do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa já cadastrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTO dto,
                                                 @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(service.gravarTarefa(token, dto));
    }

    @DeleteMapping
    @Operation(summary = "deleta tarefa", description = "Deleta tarefa por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<Void> deletarTarefa(@PathVariable("id") String id,
                                              @RequestHeader("Authorization") String token) {
        service.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }
    /*DateTimeFormat = Formata os dados de data para um formato reconhecido no banco de dados*/
    @GetMapping("/{eventos}")
    @Operation(summary = "Busca dados da tarefa por datas",
            description = "Busca tarefas do usuário entre um período de tempo")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<List<TarefaDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String toekn){
        return ResponseEntity.ok(service.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, toekn));
    }
    @GetMapping
    @Operation(summary = "Busca dados da tarefa", description = "Busca tarefas do usuário por email")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.buscaTarefasPorEmail(token));
    }
    @PatchMapping
    @Operation(summary = "Altera status da tarefa", description = "Altera status tarefas do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("Status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.atualizaStatusDaTarefa(id, status, token));
    }
    @PutMapping
    @Operation(summary = "Altera dados da tarefa", description = "Altera tarefas do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<TarefaDTO> atualizaTarefa(@RequestBody TarefaDTO dto,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.updateTarefas(dto, id, token));
    }
}
