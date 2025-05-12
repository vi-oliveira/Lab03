package lab03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lab03.Eventos.EventoFestival;
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

        EventoShow showAzul = superEventos.criarEvento("Caneta Azul",
        200, LocalDate.of(2025, 4, 29), "Manoel Gomes", gerenciadora.getHistorico());
        EventoShow showMichael = superEventos.criarEvento("Biridin",
        400, LocalDate.of(2025,12, 15), "Michael Jackson", gerenciadora.getHistorico());
        List<String> times = new ArrayList<String>(Arrays.asList("time1", "time2"));
        EventoJogo jogoEsporte = superEventos.criarEvento("Evento 2",
        1100, LocalDate.of(2026, 6, 30), times, gerenciadora.getHistorico());
    }

    // Simular Clientes

    // Simular VendaIngressos
    
    public void Simular(Gerenciadora gerenciadora){
        SimularImobiliariaELocais(gerenciadora);

        // Falta alocar os locais para os eventos antes de simulá-los
    }
}
