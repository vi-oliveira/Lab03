package lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Eventos.EventoEmBar;
import lab03.Eventos.EventoFestivalDeShows;
import lab03.Eventos.EventoMusicaAoVivo;
import lab03.Eventos.EventoShow;
import lab03.Eventos.HistoricoEventos;
import lab03.Eventos.Organizadora;
import lab03.Exceptions.CancelamentoNaoPermitidoException;
import lab03.Exceptions.EventoNaoEncontradoException;
import lab03.Exceptions.IngressoEsgotadoException;
import lab03.Exceptions.IngressoNaoEncontradoException;
import lab03.Notificacoes.NotificacaoEmail;
import lab03.Notificacoes.Notificavel;

public class Lab03Test {

    /**
     * Testa as funções de notificação do cliente.
     * A notificação é adicionada (vai para a lista de notificações pendentes).
     * Depois o usuário é notificado (vai para a lista de notificações enviadas).
     * Espera-se que a lista de notificações pendentes esteja vazia e a de notificações
     * enviadas tenha 1 notificação.
     */
    @Test
    public void notificar() {
        Cliente daniel = new Cliente("Daniel Franco", "shaolinMatadorDePorco@gmail.com", "4002-8922");
        Notificavel danielNotificacaoEmail = new NotificacaoEmail(
            "Houve uma tentativa de login na sua conta recentemente.",
            "Login desconhecido");

        daniel.adiconarNotificacao(danielNotificacaoEmail);
        daniel.enviarNotificacoes();
        assertEquals(0, daniel.getNotificaoPendentes().size());
        assertEquals(1, daniel.getNotificaoEnviadas().size());
    }

    /**
     * Testa a comparação entre clientes.
     * Espera-se que os dois clientes tenham em comum o evento showGoogle.
     */
    @Test
    public void compararClientes() {
        Local teatroArena = new Local("Teatro de Arena", 100);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");
    
        EventoShow showGoogle = javaEventos.criarEvento("Músicas IA",
        150, LocalDate.of(2025, 7, 29), "Chat Gemini", historico);
        
        try{
            teatroArena.alocarParaEvento(showGoogle);
        } catch (Exception e){}

        Cliente daniel = new Cliente("Daniel Franco", "shaolinMatadorDePorco@gmail.com", "4002-8922");
        Cliente gabriela = new Cliente("Gabriela", "EEEMAIL@gmail.com", "9999-1111");

        Ingresso ingressoShowGoogle = new Ingresso(showGoogle, showGoogle.getPrecoIngresso());
        Ingresso ingressoShowGoogle2 = new Ingresso(showGoogle, showGoogle.getPrecoIngresso());

        try{
            showGoogle.venderIngresso(daniel, ingressoShowGoogle);
            showGoogle.venderIngresso(gabriela, ingressoShowGoogle2);
        } catch (Exception e){}

        Set<Evento> eventosEmComum = daniel.compararA(gabriela);

        assertEquals(1, eventosEmComum.size());
    }

    /**
     * Testa a implementação de EventoEmBar
     * Espera-se que a descrição do evento esteja correta
     */
    @Test
    public void testarEventoEmBar(){
        Local barzinho = new Local("Bar Tolomeu", 10);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");

        EventoEmBar eventoBar = javaEventos.criarEvento("SUPER BAR",
        20, LocalDate.of(2025, 12, 14), barzinho.getNome(), "22:00", 2, historico);
        try {
            barzinho.alocarParaEvento(eventoBar);
        } catch (Exception e) {
            System.out.println(e);
        }

        String descricaoEsperada = "Evento no bar: Bar Tolomeu, Happy Hour Inicio: 22:00, Duração: 2";
        String descricaoBar = eventoBar.descricao();

        assertEquals(descricaoEsperada, descricaoBar);
        
    }

    /**
     * Testa a implementação de EventoMusicaAoVivo
     * Espera-se que a descrição do evento esteja correta
     */
    @Test
    public void testarEventoMusicaAoVivo(){
        Local palco = new Local("Mega Palco", 200);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");

        EventoMusicaAoVivo eventoMusica = javaEventos.criarEvento("Músicas legais",
        500, LocalDate.of(2026, 3, 19), "Elvis", "Rock", historico);
        try {
            palco.alocarParaEvento(eventoMusica);
        } catch (Exception e) {
            System.out.println(e);
        }

        String descricaoEsperada = "Música ao vivo com Elvis (Rock)";
        String descricaoBar = eventoMusica.descricao();

        assertEquals(descricaoEsperada, descricaoBar);
        
    }
    
    /**
     * Testa a implementação da nova hierarquia de eventos
     * Espera-se que seja possível criar um evento, por meio
     * de composição, que tenha características em comum com outro
     * evento (EventoFestival). Na demonstração é necessário apenas
     * adicionar um outro objeto com as características secundárias.
     */
    @Test
    public void novaHierarquia(){
        Local teatroArena = new Local("Teatro de Arena", 100);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");
        
        EventoShow showGoogle = javaEventos.criarEvento("Músicas IA",
        150, LocalDate.of(2025, 7, 1), "Chat Gemini", historico);

        EventoShow showLinux = javaEventos.criarEvento("Músicas OS",
        0, LocalDate.of(2025, 7, 2), "Tux", historico);

        try{
            teatroArena.alocarParaEvento(showGoogle);
            teatroArena.alocarParaEvento(showLinux);
        } catch (Exception e){}

        List<String> lineup = new ArrayList<String>(Arrays.asList("Chat Gemini", "Tux"));

        List<EventoShow> shows = new ArrayList<EventoShow>(Arrays.asList(showGoogle, showLinux));
        EventoFestivalDeShows festivalDeShows = javaEventos.criarEvento("Paloozalolla", 1000, LocalDate.of(2025, 7, 1), lineup, 2, shows, historico);

        assertEquals(2, festivalDeShows.getShows().size());
    }

    /* TRATAMENTO DE 2 ERROS: 
    CancelamentoNaoPermitidoException e EventoNaoEncontradoException*/

    /**
     * Testa o tratamento da exceção de cancelamento não permitido
     * Espera-se que a mensagem de erro seja a correta.
     */
    @Test
    public void tratamentoCancelamentoNaoPermitido(){
        Local teatroArena = new Local("Teatro de Arena", 100);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");
        
        /*A regra para que o cancelamento não seja permitido é para quando
         * se tenta cancelar o evento após uma data limite. A data limite é
         * o dia anterior do Evento.
         * Para que o teste funcione, a data do evento está definida como hoje.
         * Assim, como a tentativa de cancelamento vai ser no dia do evento,
         * será lançada a exceção.
         */
        EventoShow showGoogle = javaEventos.criarEvento("Músicas IA",
        150, LocalDate.now(), "Chat Gemini", historico);
        
        try{
            teatroArena.alocarParaEvento(showGoogle);
        } catch (Exception e){}

        Cliente daniel = new Cliente("Daniel Franco", "shaolinMatadorDePorco@gmail.com", "4002-8922");

        Ingresso ingressoShowGoogle = new Ingresso(showGoogle, showGoogle.getPrecoIngresso());

        try{
            showGoogle.venderIngresso(daniel, ingressoShowGoogle);
        } catch (Exception e){}

        String mensagemDeErroEsperada = "A data limite para o cancelamento já foi ultrapassada";
        String mensagemDeErroRecebida = null;

        try {
            daniel.cancelarIngresso(ingressoShowGoogle);
        } catch (CancelamentoNaoPermitidoException e){
            mensagemDeErroRecebida = e.getMessage();
        } catch (IngressoNaoEncontradoException e){
            mensagemDeErroRecebida = e.getMessage();
        }

        assertEquals(mensagemDeErroEsperada, mensagemDeErroRecebida);
    }

    /**
     * Testa o tratamento da exceção de Evento não encontrado.
     * (Caso com a venda de 1 ingresso)
     * Espera-se que a mensagem de erro seja a correta.
     */
    @Test
    public void tratamentoEventoNaoEncontradoUmIngresso(){
        Local teatroArena = new Local("Teatro de Arena", 100);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");
        
        EventoShow showGoogle = javaEventos.criarEvento("Músicas IA",
        150, LocalDate.of(2025, 12, 14), "Chat Gemini", historico);

        EventoShow showLinux = javaEventos.criarEvento("Músicas OS",
        0, LocalDate.of(2025, 12, 15), "Tux", historico);
        
        try{
            teatroArena.alocarParaEvento(showGoogle);
            teatroArena.alocarParaEvento(showLinux);
        } catch (Exception e){}

        Cliente daniel = new Cliente("Daniel Franco", "shaolinMatadorDePorco@gmail.com", "4002-8922");

        Ingresso ingressoShowGoogle = new Ingresso(showGoogle, showGoogle.getPrecoIngresso());

        String mensagemDeErroEsperada = "O evento do ingresso e o evento fornecido são diferentes: Músicas IA != Músicas OS";
        String mensagemDeErroRecebida = null;
        
        try{
            showLinux.venderIngresso(daniel, ingressoShowGoogle);
        } catch (IngressoEsgotadoException e){
            mensagemDeErroRecebida = e.getMessage();
        } catch (EventoNaoEncontradoException e){
            mensagemDeErroRecebida = e.getMessage();
        }

        assertEquals(mensagemDeErroEsperada, mensagemDeErroRecebida);
    }

    /**
     * Testa o tratamento da exceção de Evento não encontrado.
     * (Caso com a venda por uma lista de ingressos)
     * Espera-se que a mensagem de erro seja a correta.
     */
    @Test
    public void tratamentoEventoNaoEncontradoListIngressos(){
        Local teatroArena = new Local("Teatro de Arena", 100);

        HistoricoEventos historico = new HistoricoEventos();

        Organizadora javaEventos = new Organizadora("Java Eventos ltda",
        12345678, "Rua que brilha nº123");
        
        EventoShow showGoogle = javaEventos.criarEvento("Músicas IA",
        150, LocalDate.of(2025, 12, 14), "Chat Gemini", historico);

        EventoShow showLinux = javaEventos.criarEvento("Músicas OS",
        0, LocalDate.of(2025, 12, 15), "Tux", historico);
        
        try{
            teatroArena.alocarParaEvento(showGoogle);
            teatroArena.alocarParaEvento(showLinux);
        } catch (Exception e){}

        Cliente daniel = new Cliente("Daniel Franco", "shaolinMatadorDePorco@gmail.com", "4002-8922");

        Ingresso ingressoShowGoogle = new Ingresso(showGoogle, showGoogle.getPrecoIngresso());
        Ingresso ingressoShowLinux = new Ingresso(showLinux, showLinux.getPrecoIngresso());
        
        List<Ingresso> ingressosEventosDiferentes = new ArrayList<Ingresso>(Arrays.asList(
            ingressoShowGoogle, ingressoShowLinux
        ));

        String mensagemDeErroEsperada = "O evento de algum ingresso e o evento fornecido são diferentes (Nenhum ingresso vendido)";
        String mensagemDeErroRecebida = null;

        try{
            showLinux.venderIngresso(daniel, ingressosEventosDiferentes);
        } catch (IngressoEsgotadoException e){
            mensagemDeErroRecebida = e.getMessage();
        } catch (EventoNaoEncontradoException e){
            mensagemDeErroRecebida = e.getMessage();
        }

        assertEquals(mensagemDeErroEsperada, mensagemDeErroRecebida);
    }
}
