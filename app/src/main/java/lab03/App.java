/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Eventos.EventoEmBar;
import lab03.Eventos.EventoFestival;
import lab03.Eventos.EventoJogo;
import lab03.Eventos.EventoShow;
import lab03.Eventos.HistoricoEventos;
import lab03.Eventos.ImobiliariaDeEventos;
import lab03.Eventos.Organizadora;
import lab03.Eventos.Caracteristicas.CaracteristicaEventoEmBar;
import lab03.Exceptions.CancelamentoNaoPermitidoException;
import lab03.Exceptions.CapacidadeInsuficienteException;
import lab03.Exceptions.IngressoNaoEncontradoException;
import lab03.Exceptions.LocalIndisponivelException;
import lab03.Exceptions.LocalNaoEncontradoException;
import lab03.Filtros.AndFiltro;
import lab03.Filtros.EventoPorDataFiltro;
import lab03.Filtros.EventoPorLocalFiltro;
import lab03.Filtros.EventoPorNomeFiltro;
import lab03.Filtros.EventoPorTipoFiltro;
import lab03.Filtros.Filtro;
import lab03.Notificacoes.NotificacaoEmail;
import lab03.Notificacoes.NotificacaoSMS;
import lab03.Notificacoes.Notificavel;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class App {

    /**
     * Construtor padrão da classe App.
     * Construtor vazio, pois a classe é utilizada apenas para a main.
     */
    public App(){}

    /**
     * Aplicação principal
     * @param args argumentos para execução
     */
    public static void main(String[] args) {

        // Desenvolvimento de Cenário de Uso

        // Criação de ingressos, locais e organizadora que serão usados nos exemplos de uso:
        Local teatroArena = new Local("Teatro de Arena", 100);
        Local ibirapuera = new Local("Ibirapuera", 300);
        Local teatroMunicipal = new Local("Teatro Municipal de São Paulo", 400);
        
        Organizadora superEventos = new Organizadora("Super Eventos",
        12345678, "Rua Adalberto Einstein nº321");
        HistoricoEventos historico = new HistoricoEventos();

        // Eventos para exemplos (serão criados mais quando necessário):
        EventoShow showAzul = superEventos.criarEvento("Caneta Azul",
        200, LocalDate.of(2025, 4, 29), "Manoel Gomes", historico);
        EventoShow show2 = superEventos.criarEvento("Biridin",
        400, LocalDate.of(2025,12, 15), "Michael Jackson", historico);
        try {
            teatroArena.alocarParaEvento(showAzul);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            teatroMunicipal.alocarParaEvento(show2);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Ingressos para exemplos (serão criados mais quando necessário):
        Ingresso ingresso1 = new Ingresso(showAzul, showAzul.getPrecoIngresso());
        Ingresso ingresso2 = new Ingresso(show2, show2.getPrecoIngresso());



        // DEMONSTRAÇÃO PASSO 1:
        System.out.println("DEMONSTRAÇÃO DO CLIENTE:");
        Cliente Alonso = new Cliente("Alonso", "Alonso@gmail.com", "1234-4321");
        // O método "adicionarIngresso" é chamado dentro de "VenderIngresso"
        try{
            showAzul.venderIngresso(Alonso, ingresso1);
            show2.venderIngresso(Alonso, ingresso2);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(" - Ingressos antes: " + Alonso.getIngressos());
        Alonso.removerIngresso(ingresso2);
        System.out.println(" - Ingressos depois: " + Alonso.getIngressos());



        // DEMONSTRAÇÃO PASSO 2:
        System.out.println("\nDEMONSTRAÇÃO DA SOBRECARGA:");
        
        // Criação de eventos (EventoShow já criado no passo 1): 
        List<String> lineupFestival = new ArrayList<String>(Arrays.asList("lineup1"));
        EventoFestival eventoFestival = superEventos.criarEvento("Festival",
        20, LocalDate.of(2025, 9, 14), lineupFestival, 4, historico);
        try {
            ibirapuera.alocarParaEvento(eventoFestival);
        } catch (Exception e) {
            System.out.println(e);
        }

        List<String> times = new ArrayList<String>(Arrays.asList("time1", "time2"));

        // Sobrecarga nos ingressos dos clientes:
        Cliente Ana = new Cliente("Ana", "anana@unicamp.com", "9876-6789");
        Ingresso ingresso3 = new Ingresso(showAzul, showAzul.getPrecoIngresso());
        Ingresso ingressoFest = new Ingresso(eventoFestival, eventoFestival.getPrecoIngresso());
        Ingresso ingressoFest2 = new Ingresso(eventoFestival, eventoFestival.getPrecoIngresso());
        List<Ingresso> ingressos = new ArrayList<Ingresso>(Arrays.asList(ingressoFest, ingressoFest2));

        /* Como o método"adicionarIngresso" é chamado
         * por "venderIngresso", os dois tem sobrecarga.
         */
        try{
            showAzul.venderIngresso(Ana, ingresso3);
            eventoFestival.venderIngresso(Ana, ingressos);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(" - Ingressos sobrecarga: ");
        System.out.println(Ana.getIngressos());

        // Sobrecarga na busca por locais:

        ImobiliariaDeEventos imobiliaria = new ImobiliariaDeEventos("ImobiVentos");
        imobiliaria.adicionarLocal(teatroArena);
        imobiliaria.adicionarLocal(ibirapuera);
        imobiliaria.adicionarLocal(teatroMunicipal);

        System.out.println("Busca de locais:");
        try {
            Local localEncontrado = imobiliaria.buscarLocal("Ibirapuera");
            System.out.println(" - Local por nome: ");
            System.out.println(localEncontrado.getNome());
        } catch (LocalNaoEncontradoException e) {
            System.out.println(e);
        }

        try {
            List<Local> locaisEncontrados = imobiliaria.buscarLocal(200);
            System.out.println(" - Local por capacidade: ");
            for (Local local : locaisEncontrados){
                System.out.println(local.getNome() + "; ");
            }
        } catch (LocalNaoEncontradoException e) {
            System.out.println(e);
        }



        // DEMONSTRAÇÃO PASSO 3:
        System.out.println("\nDEMONSTRAÇÃO DAS EXCEÇÕES:");
        
        Local estadio = new Local("Estádio Grande", 300);
        imobiliaria.adicionarLocal(estadio);
        List<String> lineupFestival2 = new ArrayList<String>(Arrays.asList("lineup2"));

        EventoFestival festivalCarros = superEventos.criarEvento("Festival de Carros",
        20, LocalDate.of(2025, 9, 14), lineupFestival2, 4, historico);

        EventoShow eventoShow = superEventos.criarEvento("show",
        20, LocalDate.of(2025, 9, 16), "Manoel Gomes", historico);

        try {
            estadio.alocarParaEvento(festivalCarros);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Festival de carros: " + e);
        } catch (LocalIndisponivelException e){
            System.out.println("Festival de carros: " + e);
        }

        /* Por conta da duração do Evento festivalCarros,
        * o local não está disponível na data do eventoShow. */
        try {
            estadio.alocarParaEvento(eventoShow);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Evento Show: " + e);
        } catch (LocalIndisponivelException e){
            System.out.println("Evento Show: " + e);
        }

        /* Em outro local disponível na data
        é possível alocar sem problemas. */
        try {
            teatroArena.alocarParaEvento(eventoShow);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Evento Show: " + e);
        } catch (LocalIndisponivelException e){
            System.out.println("Evento Show: " + e);
        }

        EventoShow eventoShow2 = superEventos.criarEvento("Evento 2",
        20, LocalDate.of(2025, 5, 21), "Manoel Gomes", historico);
        try {
            teatroMunicipal.alocarParaEvento(eventoShow2);
        } catch (Exception e) {
            System.out.println(e);
        }

        Cliente julio = new Cliente("Julio", "julio@yahoo.com.br", "4002-8922");
        Ingresso ingresso4 = new Ingresso(eventoShow2, eventoShow2.getPrecoIngresso());
        try{
            eventoShow2.venderIngresso(julio, ingresso4);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Cancelando um ingresso não comprado por júlio (ingresso1);
        try {
            julio.cancelarIngresso(ingresso1);
        } catch (CancelamentoNaoPermitidoException e){
            System.out.println("Cancelamento ingresso Julio: " + e);
        } catch (IngressoNaoEncontradoException e){
            System.out.println("Cancelamento ingresso Julio: " + e);
        }
        
        System.out.println("Ingressos Julio antes:\n" + julio.getIngressos());
        // Cancelando um ingresso válido do Julio:
        try {
            julio.cancelarIngresso(ingresso4);
        } catch (CancelamentoNaoPermitidoException e){
            System.out.println("Cancelamento ingresso Julio: " + e);
        } catch (IngressoNaoEncontradoException e){
            System.out.println("Cancelamento ingresso Julio: " + e);
        }
        System.out.println("Ingressos Julio depois:\n" + julio.getIngressos());



        // DEMONSTRAÇÃO PASSO 4:
        System.out.println("\nDEMONSTRAÇÃO DOS FILTROS:");

        // Criando mais eventos para utilizar os filtros
        EventoJogo jogoEsporte = superEventos.criarEvento("Evento 2",
        1100, LocalDate.of(2026, 6, 30), times, historico);
        try {
            estadio.alocarParaEvento(jogoEsporte);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println(e);
        } catch (LocalIndisponivelException e){
            System.out.println(e);
        }

        EventoShow classico = superEventos.criarEvento("Clássicos",
        150, LocalDate.of(2025, 8, 31), "Mozart", historico);
        try {
            ibirapuera.alocarParaEvento(classico);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println(e);
        } catch (LocalIndisponivelException e){
            System.out.println(e);
        }

        EventoFestival festivalCultural = superEventos.criarEvento("Festival Cultural",
        10, LocalDate.of(2025, 7, 3), null, 3, historico);
        try {
            teatroMunicipal.alocarParaEvento(festivalCultural);
        } catch (CapacidadeInsuficienteException e) {
            System.out.println(e);
        } catch (LocalIndisponivelException e){
            System.out.println(e);
        }

        // Funcionamento dos filtros:
        // O objeto jogoEsporte e o EventoShow2 possuem o nome para o filtro abaixo:
        Filtro filtroNome = new EventoPorNomeFiltro("Evento 2");
        List<Evento> eventosFiltradosPorNome = historico.buscarEventos(filtroNome);
        System.out.println(" - Eventos filtrados por nome:");
        for(Evento evento : eventosFiltradosPorNome) System.out.println(evento.getClass());

        // O objeto "classico" atende ao filtro abaixo:
        Filtro filtroData = new EventoPorDataFiltro(LocalDate.of(2025, 8, 31));
        List<Evento> eventosFiltradosPorData = historico.buscarEventos(filtroData);
        System.out.println(" - Eventos filtrados por data:");
        for(Evento evento : eventosFiltradosPorData) System.out.println(evento.getNome());

        // Combinação de filtros (O objeto festivalCultural atende aos filtros (tipo/classe e local)): 
        Filtro filtroTipo = new EventoPorTipoFiltro(EventoFestival.class);
        Filtro filtroLocal = new EventoPorLocalFiltro(teatroMunicipal);
        List<Filtro> filtros = new ArrayList<Filtro>(Arrays.asList(filtroTipo, filtroLocal));
        AndFiltro filtrosTipoLocal = new AndFiltro(filtros);

        List<Evento> eventosFiltradosPorTipoLocal = historico.buscarEventos(filtrosTipoLocal);
        System.out.println(" - Eventos filtrados por tipo e local:");
        for(Evento evento : eventosFiltradosPorTipoLocal) System.out.println(evento.getNome());



        // DEMONSTRAÇÃO PASSO 5:
        System.out.println("\nDEMONSTRAÇÃO DAS NOTIFICAÇÕES:");
        Notificavel julioNotificacaoEmail = new NotificacaoEmail(
            "Houve uma tentativa de login na sua conta recentemente.",
            "Login desonhecido");

        Notificavel julioNotificaSMS = new NotificacaoSMS(
        "Confira a nova promoção de ingressos para shows!!!");

        julio.adiconarNotificacao(julioNotificacaoEmail);
        julio.adiconarNotificacao(julioNotificaSMS);
        julio.enviarNotificacoes();



        // DEMONSTRAÇÃO PASSO 6:
        System.out.println("\nDEMONSTRAÇÃO DA COMPARAÇÃO:");
        Cliente gabriela = new Cliente("Gabriela", "EEEMAIL@gmail.com", "9999-1111");
        Ingresso ingressoShow2Repete = new Ingresso(eventoShow2, eventoShow2.getPrecoIngresso());
        Ingresso ingressoGabriela = new Ingresso(festivalCarros, festivalCarros.getPrecoIngresso());
        try{
            eventoShow2.venderIngresso(julio, ingresso4); // Ingresso 4 é para o EventoShow2
            eventoShow2.venderIngresso(gabriela, ingressoShow2Repete); // Julio e Gabriela têm ingresso para este evento
            festivalCarros.venderIngresso(gabriela, ingressoGabriela);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Eventos em comum: ");
        for (Evento evento : julio.compararA(gabriela)){
            System.out.println("Nome do evento: " + evento.getNome());
        }


        
        // DEMONSTRAÇÃO PASSO 7:
        System.out.println("\nDEMONSTRAÇÃO DA COMPOSIÇÃO:");
        EventoEmBar eventoBar = superEventos.criarEvento("SUPER BAR",
        20, LocalDate.of(2025, 12, 14), "Bar Tolomeu", "22:00", 2, historico);
        Local barzinho = new Local("Bar Tolomeu", 10);
        try {
            barzinho.alocarParaEvento(eventoBar);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Evento bar: " + eventoBar.getNome());
        CaracteristicaEventoEmBar caracteristicasBar = (CaracteristicaEventoEmBar) eventoBar.getCaracteristicas();
        System.out.println("Nome do bar: " + caracteristicasBar.getNomeDoBar());



        // DEMONSTRAÇÃO PASSO 8
        /*Por conta de todo o cenário de uso feito até aqui,
         * cada um dos itens já foi demosntrado. */
    }
}
