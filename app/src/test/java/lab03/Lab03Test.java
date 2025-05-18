package lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Lab03Test {

    /**
     * Teste se a compra de ingressos na forma comum funciona corretamente.
     * Espera-se que o saldo do cliente diminua em relação ao preço pago pelo ingresso
     * adquirido. Além disso, a lista de ingressos disponíveis deve diminuir em 1.
     */
    @Test
    public void verificarCompraComum(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        gerenciadora.simularExemplo();

        int quantidadeIngressosDisponiveisAntes = gerenciadora.getIngressosComunsDisponiveis().size();
        Double saldoOriginal = gerenciadora.getCLientes().get("email").getSaldo();
        Double saldoEsperado = saldoOriginal -  gerenciadora.getIngressosComunsDisponiveis().get(0).getPreco();
        
        try {
            gerenciadora.venderIngressoFormaComum(gerenciadora.getHistorico().getEventos().get(0),
            gerenciadora.getIngressosComunsDisponiveis().get(0), gerenciadora.getCLientes().get("email"));
        } catch (Exception e) {}
        
        assertEquals(quantidadeIngressosDisponiveisAntes - 1, gerenciadora.getIngressosComunsDisponiveis().size());
        assertEquals(saldoEsperado, gerenciadora.getCLientes().get("email").getSaldo());
    }
}
