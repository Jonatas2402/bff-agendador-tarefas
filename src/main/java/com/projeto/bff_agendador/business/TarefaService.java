package com.projeto.bff_agendador.business;


import com.projeto.bff_agendador.business.DTO.TarefaDTO;
import com.projeto.bff_agendador.infrastructure.client.TarefasClient;
import com.projeto.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefasClient client;


    public TarefaDTO gravarTarefa(String token, TarefaDTO dto) {
        return client.salvaTarefa(dto, token);
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                           LocalDateTime dataFinal,
                                                           String token) {
        return client.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }


    public void deletarTarefaPorId(String id, String token) {
        client.deletarTarefa(id, token);
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public TarefaDTO atualizaStatusDaTarefa(String id, StatusNotificacaoEnum status, String token) {
       return client.alteraStatusNotificacao(status,id, token);
    }

    public TarefaDTO updateTarefas(TarefaDTO dto, String id, String token) {
       return client.atualizaTarefa(dto, id, token);
    }
}
