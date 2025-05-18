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
        Marketplace marketplace = gerenciadora.getMarketplace();

        int quantidadeIngressosDisponiveisAntes = marketplace.listarOfertas().size();
        Double saldoOriginal = gerenciadora.getCLientes().get("email").getSaldo();
        Double saldoEsperado = saldoOriginal -  marketplace.listarOfertas().get(0).getPreco();
        
        try {
            gerenciadora.venderIngresso(gerenciadora.getCLientes().get("email"),
            marketplace.listarOfertas().get(0), marketplace);
        } catch (Exception e) {}
        
        assertEquals(quantidadeIngressosDisponiveisAntes - 1, marketplace.listarOfertas().size());
        assertEquals(saldoEsperado, gerenciadora.getCLientes().get("email").getSaldo());
    }
}
