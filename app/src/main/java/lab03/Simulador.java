/*
 * Simulador.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 * A documentação para javadoc deste arquivo foi feita com o uso de IA
 * e posteriormente revisada e/ou corrigida.
 */
package lab03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab03.Clientes.Cliente;
import lab03.Eventos.Evento;
import lab03.Eventos.EventoJogo;
import lab03.Eventos.EventoShow;
import lab03.Eventos.ImobiliariaDeEventos;
import lab03.Eventos.Organizadora;

/**
 * A classe Simulador é responsável por popular a aplicação com dados de exemplo
 * para fins de teste e demonstração. Ela simula a criação de imobiliárias,
 * locais, eventos, clientes e vendas de ingressos.
 * 
 * @author Vinícius de Oliveira - 251527
 */
public class Simulador {
    /**
     * Construtor padrão para a classe Simulador.
     */
    public Simulador(){}

    /**
     * Simula a criação de uma imobiliária e adiciona locais a ela,
     * em seguida, adiciona a imobiliária à gerenciadora.
     *
     * @param gerenciadora A instância de Gerenciadora onde a imobiliária e locais serão adicionados.
     */
    private void SimularImobiliariaELocais(Gerenciadora gerenciadora) {
        ImobiliariaDeEventos imobiliaria = new ImobiliariaDeEventos("Super Eventos");

        Local teatroArena = new Local("Teatro de Arena", 100);
        Local teatroMunicipal = new Local("Teatro Municipal de São Paulo", 400);
        Local estadio = new Local("Estádio Grande", 300);
        Local barzinho = new Local("Bar Tolomeu", 10);
        
        imobiliaria.adicionarLocal(teatroArena);
        imobiliaria.adicionarLocal(teatroMunicipal);
        imobiliaria.adicionarLocal(estadio);
        imobiliaria.adicionarLocal(barzinho);

        gerenciadora.getImobiliarias().add(imobiliaria);
    }

    /**
     * Simula a criação de uma organizadora e diversos eventos (shows e jogos),
     * aloca locais para esses eventos e cria ingressos comuns disponíveis.
     *
     * @param gerenciadora A instância de Gerenciadora onde a organizadora, eventos e ingressos serão adicionados.
     */
    private void SimularEventos(Gerenciadora gerenciadora) {
        Organizadora superEventos = new Organizadora("Super Eventos",
        12345678, "Rua Adalberto Einstein nº321");

        gerenciadora.getOrganizadoras().add(superEventos);

        EventoShow showAzul = superEventos.criarEvento("Caneta Azul",
        200, LocalDate.of(2025, 4, 29), "Manoel Gomes", gerenciadora.getHistorico());
        EventoShow showMichael = superEventos.criarEvento("Biridin",
        400, LocalDate.of(2025,12, 15), "Michael Jackson", gerenciadora.getHistorico());
        List<String> times = new ArrayList<String>(Arrays.asList("time1", "time2"));
        EventoJogo jogoEsporte = superEventos.criarEvento("Evento esportivo",
        1100, LocalDate.of(2026, 6, 30), times, gerenciadora.getHistorico());
        
        /*  Para exemplo, como só existe uma imobiliaria cadastrada, na simulação
        basta apenas pegar a imobiliaria no indice 0*/
        try {
            gerenciadora.getImobiliarias().get(0)
                .buscarLocal("Teatro de Arena").alocarParaEvento(showAzul);
            gerenciadora.getImobiliarias().get(0)
                .buscarLocal("Teatro Municipal de São Paulo").alocarParaEvento(showMichael);
            gerenciadora.getImobiliarias().get(0)
                .buscarLocal("Estádio Grande").alocarParaEvento(jogoEsporte);
        } catch (Exception e) {
            System.out.println(e);
        }

        Ingresso ingressoShowAzul = new Ingresso(showAzul, showAzul.getPrecoIngresso());
        Ingresso ingressoShowAzul2 = new Ingresso(showAzul, showAzul.getPrecoIngresso());
        Ingresso ingressoMichael = new Ingresso(showMichael, showMichael.getPrecoIngresso());
        Ingresso ingressoMichael2 = new Ingresso(showMichael, showMichael.getPrecoIngresso());
        Ingresso ingressoEsporte = new Ingresso(jogoEsporte, jogoEsporte.getPrecoIngresso());
        Ingresso ingressoEsporte2 = new Ingresso(jogoEsporte, jogoEsporte.getPrecoIngresso());

        gerenciadora.getIngressosComunsDisponiveis().addAll(Arrays.asList(
            ingressoShowAzul, ingressoShowAzul2,
            ingressoMichael, ingressoMichael2,
            ingressoEsporte, ingressoEsporte2));

    }

    /**
     * Simula a criação de clientes e adiciona-os à gerenciadora.
     * Também simula a venda de alguns ingressos para esses clientes.
     *
     * @param gerenciadora A instância de Gerenciadora onde os clientes e vendas serão processados.
     */
    private void SimularClientes(Gerenciadora gerenciadora) {
        Cliente alonso = new Cliente("Alonso", "Alonso@gmail.com", "senha", "1234-4321", 250.0);
        Cliente ana = new Cliente("Ana", "anana@unicamp.com", "qsdsijf", "9876-6789", 343000000000.0);
        Cliente gabriela = new Cliente("Gabriela", "EEEMAIL@gmail.com", "294748292", "9999-1111", 750.0);
        Cliente cliente = new Cliente("cliente1", "email", "123", "1029-3847", 1000.0);

        gerenciadora.getCLientes().put(alonso.getEmail(), alonso);
        gerenciadora.getCLientes().put(ana.getEmail(), ana);
        gerenciadora.getCLientes().put(gabriela.getEmail(), gabriela);
        gerenciadora.getCLientes().put(cliente.getEmail(), cliente);

        // Como o exemplo é planejado à mão, os erros não vão ocorrer, então não é necessário colocar nada no catch
        try {
            Evento showAzul = gerenciadora.getHistorico().getEventos().get(0);
            Ingresso ingressoAzul = gerenciadora.getIngressosComunsDisponiveis().get(0);
            gerenciadora.venderIngressoFormaComum(showAzul, ingressoAzul, alonso);
    
            Evento showMichael = gerenciadora.getHistorico().getEventos().get(1);
            Ingresso ingressoMichael = gerenciadora.getIngressosComunsDisponiveis().get(2);
            gerenciadora.venderIngressoFormaComum(showMichael, ingressoMichael, ana);
        } catch (Exception e) {}
    }

    /**
     * Simula a listagem de um ingresso no marketplace por um cliente.
     * Assume que o cliente "Alonso@gmail.com" existe e possui ingressos.
     *
     * @param gerenciadora A instância de Gerenciadora contendo o marketplace.
     */
    private void SimularMarketplace(Gerenciadora gerenciadora) {
        Cliente vendedor = gerenciadora.getCLientes().get("Alonso@gmail.com");
        try{
            vendedor.oferecerIngressoParaVenda(vendedor.getIngressos().get(0), 100.0, gerenciadora.getMarketplace());
        } catch (Exception e) {}
    }
    
    /**
     * Executa toda a simulação, populando a Gerenciadora com dados de exemplo
     * de imobiliárias, locais, eventos, clientes e vendas de ingressos,
     * incluindo uma listagem no marketplace.
     *
     * @param gerenciadora A instância de Gerenciadora a ser populada com os dados simulados.
     */
    public void Simular(Gerenciadora gerenciadora) {
        SimularImobiliariaELocais(gerenciadora);
        SimularEventos(gerenciadora);
        SimularClientes(gerenciadora);
        SimularMarketplace(gerenciadora);
    }
}
