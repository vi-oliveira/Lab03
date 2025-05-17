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

public class Simulador {
    public Simulador(){}

    private void SimularImobiliariaELocais(Gerenciadora gerenciadora){
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

    private void SimularEventos(Gerenciadora gerenciadora){
        Organizadora superEventos = new Organizadora("Super Eventos",
        12345678, "Rua Adalberto Einstein nº321");

        gerenciadora.getOrganizadoras().add(superEventos);

        EventoShow showAzul = superEventos.criarEvento("Caneta Azul",
        200, LocalDate.of(2025, 4, 29), "Manoel Gomes", gerenciadora.getHistorico());
        EventoShow showMichael = superEventos.criarEvento("Biridin",
        400, LocalDate.of(2025,12, 15), "Michael Jackson", gerenciadora.getHistorico());
        List<String> times = new ArrayList<String>(Arrays.asList("time1", "time2"));
        EventoJogo jogoEsporte = superEventos.criarEvento("Evento 2",
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

        gerenciadora.getMarketplace().listarOfertas().addAll(Arrays.asList(
            ingressoShowAzul, ingressoShowAzul2,
            ingressoMichael, ingressoMichael2,
            ingressoEsporte));

    }

    private void SimularClientes(Gerenciadora gerenciadora){
        Cliente alonso = new Cliente("Alonso", "Alonso@gmail.com", "senha", "1234-4321", 250.0);
        Cliente ana = new Cliente("Ana", "anana@unicamp.com", "qsdsijf", "9876-6789", 343000000000.0);
        Cliente gabriela = new Cliente("Gabriela", "EEEMAIL@gmail.com", "294748292", "9999-1111", 750.0);
        Cliente cliente = new Cliente("cliente1", "email", "123", "1029-3847", 1000.0);

        gerenciadora.getCLientes().put(alonso.getEmail(), alonso);
        gerenciadora.getCLientes().put(ana.getEmail(), ana);
        gerenciadora.getCLientes().put(gabriela.getEmail(), gabriela);
        gerenciadora.getCLientes().put(cliente.getEmail(), cliente);

        gerenciadora.setUsuario(cliente);

        // Como o exemplo é planejado à mão, os erros não vão ocorrer, então não é necessário colocar nada no catch
        try {
            Evento showAzul = gerenciadora.getHistorico().getEventos().get(0);
            Marketplace marketplace = gerenciadora.getMarketplace();
            Ingresso ingressoAzul = (Ingresso) marketplace.listarOfertas().get(0);
            gerenciadora.venderIngresso(alonso, ingressoAzul, marketplace);
    
            Evento showMichael = gerenciadora.getHistorico().getEventos().get(1);
            Ingresso ingressoMichael = (Ingresso) marketplace.listarOfertas().get(2);
            gerenciadora.venderIngresso(ana, ingressoMichael, marketplace);
        } catch (Exception e) {}
    }

    // Simular marketplace
    
    public void Simular(Gerenciadora gerenciadora){
        SimularImobiliariaELocais(gerenciadora);
        SimularEventos(gerenciadora);
        SimularClientes(gerenciadora);
    }
}
