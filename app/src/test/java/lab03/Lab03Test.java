/*
 * Lab03Test.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 * 
 */
package lab03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lab03.Clientes.Cliente;
import lab03.Exceptions.IngressoNaoPertenceAoClienteException;
import lab03.Exceptions.OfertaNaoEncontradaException;
import lab03.Exceptions.SaldoInsuficienteException;

public class Lab03Test {

    /**
     * Verifica se o método de receber oferta no marketplace funciona corretamente.
     * A quanidade de ofertas do marketplace deve aumentar em 1.
     */
    @Test
    public void VerificarReceberOferta(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();
        int qtdOfertasMarketplaceAntes = marketplace.listarOfertas().size();

        // Pela simulação, o cliente pego abaixo possui um ingresso.
        Cliente vendedor = gerenciadora.getCLientes().get("anana@unicamp.com");

        Ingresso ingressoAna = vendedor.getIngressos().get(0);

        // A exceção não é relevante para este teste.
        try {
            marketplace.receberOFerta(vendedor, ingressoAna, 200.0);
        } catch (Exception e) {}

        int qtdOfertasMarketplaceDepois = marketplace.listarOfertas().size();

        assertEquals(qtdOfertasMarketplaceAntes + 1, qtdOfertasMarketplaceDepois);
    }

    /**
     * Verifica se o método de processar compra no marketplace funciona corretamente.
     * É testado a atualização dos saldos (incluindo do marketplace) e a transferência de ingresso.
     */
    @Test
    public void VerificarProcessarCompra(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();

        // Pela simulação, Alonso já está vendendo no marketplace por R$100,00.
        Cliente alonso = gerenciadora.getCLientes().get("Alonso@gmail.com");
        Cliente comprador = gerenciadora.getCLientes().get("email");

        Double saldoAnteriorAlonso = alonso.getSaldo();
        Double saldoAnteriorComprador = comprador.getSaldo();
        Double saldoAnteriorMarketplace = marketplace.getSaldoComissao();

        int qtdIngressosCompradorAntes = comprador.getIngressos().size();

        // A oferta do Alonso é a única existente no marketplace
        OfertaIngresso ofertaAlonso = marketplace.listarOfertas().get(0);

        try{
            comprador.comprarIngressoNoMarketplace(ofertaAlonso, marketplace);
        } catch (Exception e) {}

        // A quantidade de ingressos do comprador deve aumentar em 1
        assertEquals(qtdIngressosCompradorAntes + 1, comprador.getIngressos().size());
        // Como a comissão é de 20% e o incresso custa 100, o novo saldo aumenta em 80.
        assertEquals(saldoAnteriorAlonso + 80, alonso.getSaldo());
        assertEquals(saldoAnteriorComprador - 100, comprador.getSaldo());
        assertEquals(saldoAnteriorMarketplace + 20, marketplace.getSaldoComissao());
    }

    /**
     * Verifica se os métodos de interação entre o cliente e o marketplace funcionam corretamente.
     * É testado o oferecimento de ingressos e a compra de ingresos no marketplace.
     */
    @Test
    public void VerificarInterecaoMarketplace(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();
        int qtdOfertasMarketplaceAntes = marketplace.listarOfertas().size();

        // Pela simulação, o cliente pego abaixo possui um ingresso.
        Cliente vendedor = gerenciadora.getCLientes().get("anana@unicamp.com");

        Ingresso ingressoAna = vendedor.getIngressos().get(0);

        // A exceção não é relevante para este teste.
        try {
            vendedor.oferecerIngressoParaVenda(ingressoAna, 200.0, marketplace);
        } catch (Exception e) {}
        
        // Pela simulação, Ana tem 1 ingresso. Ao oferecer para venda, ela deve ficar com 0.
        assertEquals(0, vendedor.getIngressos().size());
        // verificar aumento no número de ofertas
        assertEquals(qtdOfertasMarketplaceAntes + 1, marketplace.listarOfertas().size());

        // A oferta da Ana é segunda no marketplace
        OfertaIngresso ofertaAna = marketplace.listarOfertas().get(1);

        Cliente comprador = gerenciadora.getCLientes().get("email");

        try {
            comprador.comprarIngressoNoMarketplace(ofertaAna, marketplace);
        } catch (Exception e) {}

        // verificar resultados da compra:
        // quantidade de ofertas
        assertEquals(qtdOfertasMarketplaceAntes, marketplace.listarOfertas().size());
        // Por conta da compra, o comprador deve ter 1 ingresso
        assertEquals(1, comprador.getIngressos().size());
    }

    /**
     * Verifica se, quando um cliente vende um ingresso que não pertence a ele,
     * é lançada uma "IngressoNaoPertenceAoClienteException".
     * A exception lançada deve ser IngressoNaoPertenceAoClienteException e
     * a mensagem de erro deve ser igual à esperada.
     */
    @Test
    public void VerificarIngressoNaoPertenceAoCliente(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();

        // Abaixo é pego um ingresso comum disponível, ou seja, nenhum cliente o possui.
        Ingresso ingressoSemDono = gerenciadora.getIngressosComunsDisponiveis().get(0);
        Cliente vendedor = gerenciadora.getCLientes().get("email");

        String mensagemDeErro = null;
        try {
            vendedor.oferecerIngressoParaVenda(ingressoSemDono, 200, marketplace);
        } catch (IngressoNaoPertenceAoClienteException e) {
            mensagemDeErro = e.getMessage();
        }

        assertEquals(mensagemDeErro, "Você não tem esse ingresso");
    }

    /**
     * Verifica se, quando um cliente tenta comprar uma oferta que não está
     * presente no marketplace, é lançada a exceção "OfertaNaoEncontradaException".
     * A exception lançada deve ser OfertaNaoEncontradaException e
     * a mensagem de erro deve ser igual à esperada.
     */
    @Test
    public void VerificarOfertaNaoEncontrada(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();

        // Pega um ingresso qualquer, sem dono
        Ingresso ingresso = gerenciadora.getIngressosComunsDisponiveis().get(0);
        Cliente vendedor = gerenciadora.getCLientes().get("anana@unicamp.com");
        Cliente comprador = gerenciadora.getCLientes().get("email");

        // Cria uma oferta que não está presente no marketplace
        OfertaIngresso ofertaIngresso = new OfertaIngresso(ingresso, 200.0, vendedor);

        String mensagemDeErro = null;
        try {
            comprador.comprarIngressoNoMarketplace(ofertaIngresso, marketplace);
        } catch (OfertaNaoEncontradaException e) {
            mensagemDeErro = e.getMessage();
        } catch (Exception e) {
            // Outras exceções não são relevantes para este teste
        }

        assertEquals(mensagemDeErro, "Essa oferta não foi encontrada");
    }

    /**
     * Verifica se, quando um cliente tenta comprar no marketplace sem
     * ter saldo suficiente, é lançada uma "SaldoInsuficienteException".
     * A exception lançada deve ser SaldoInsuficienteException e
     * a mensagem de erro deve ser igual à esperada.
     */
    @Test
    public void VerificarSaldoInsuficiente(){
        Gerenciadora gerenciadora = Gerenciadora.getInstance();
        Gerenciadora.reset();
        gerenciadora.simularExemplo();

        Marketplace marketplace = gerenciadora.getMarketplace();
        // Pela simulação, marketplace já tem uma oferta.
        OfertaIngresso oferta = marketplace.listarOfertas().get(0);

        // criando um novo cliente que não tem dinheiro. Logo seu saldo é sempre insuficiente
        Cliente comprador = new Cliente("nome", "gmail", "1234", "123", 0.0);
        gerenciadora.getCLientes().put(comprador.getEmail(), comprador);

        String mensagemDeErro = null;
        try {
            comprador.comprarIngressoNoMarketplace(oferta, marketplace);
        } catch (SaldoInsuficienteException e) {
            mensagemDeErro = e.getMessage();
        } catch (Exception e) {
            // Outras exceções não são relevantes para este teste
        }

        assertEquals(mensagemDeErro, "Saldo insuficiente");
    }
}
