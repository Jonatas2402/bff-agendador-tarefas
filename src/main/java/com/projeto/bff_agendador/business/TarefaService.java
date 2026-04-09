package com.projeto.bff_agendador.business;


import com.projeto.bff_agendador.business.DTO.in.TarefaDtoRequest;
import com.projeto.bff_agendador.business.DTO.out.TarefaDtoResponse;
import com.projeto.bff_agendador.infrastructure.client.TarefasClient;
import com.projeto.bff_agendador.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefasClient client;


    public TarefaDtoResponse gravarTarefa(String token, TarefaDtoRequest dto) {
        return client.salvaTarefa(dto, token);
    }

    public List<TarefaDtoResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                   LocalDateTime dataFinal,
                                                                   String token) {
        return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }


    public void deletarTarefaPorId(String id, String token) {
        client.deletarTarefa(id, token);
    }

    public List<TarefaDtoResponse> buscaTarefasPorEmail(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public TarefaDtoResponse atualizaStatusDaTarefa(String id, StatusNotificacaoEnum status, String token) {
       return client.alteraStatusNotificacao(status,id, token);
    }

    public TarefaDtoResponse updateTarefas(TarefaDtoRequest dto, String id, String token) {
       return client.atualizaTarefa(dto, id, token);
    }
}
