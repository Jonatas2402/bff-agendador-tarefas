package com.projeto.bff_agendador.business;

import com.projeto.bff_agendador.business.DTO.in.LoginRequestDto;
import com.projeto.bff_agendador.business.DTO.out.TarefaDtoResponse;
import com.projeto.bff_agendador.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
/*Buscar no agendador de tarefas a cada 5 min cada tarefa cujo a data seja daqui a 1 hora.*/
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasDaProximaHora() {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefaDtoResponse> listaTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefa encontrada " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuário " + tarefa.getEmailUsuario());
            tarefaService.atualizaStatusDaTarefa(tarefa.getId(),StatusNotificacaoEnum.NOTIFICADO,
                    token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginRequestDto dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDto converterParaRequestDTO(){
        return LoginRequestDto.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
