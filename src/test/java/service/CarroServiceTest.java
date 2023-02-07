package service;

import model.Carro;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarroServiceTest {

    // carro service é responsável por executar todas as funções do carro
    // utilizar métodos do carro service

    private CarroService carroService;
    private Carro carro;

    @Before
    public void inicializaCarroEServico () {
        carroService = new CarroServiceImpl();
        carro = new Carro("Azul", "Fiat", "Uno", 2020, 150);
    }

    @Test
    // O carro inicia desligado
    public void umCarroDeveIniciarDesligado () {
        // given:
        // carro e carroService são inicializados no @Before

        // when:

        // then:
        Assert.assertFalse(carro.isLigado());
    }

    @Test
    // Enquanto o carro estiver desligado deve ser capaz de: Ligar
    public void verificarSeCarroLigaQuandoDesligado () {
        // given:
        // carro e carroService são inicializados no @Before

        // when:
        carroService.ligar(carro);

        // then:
        Assert.assertTrue(carro.isLigado());
    }

    @Test
    // Enquanto o carro estiver desligado deve ser capaz de: Mostrar estado atual
    public void verificaEstadoAtualQuandoDesligado() {
        // given:
        // carro e carroService são inicializados no @Before

        // when:
        carroService.desligar(carro);

        // then:
        Assert.assertFalse(carro.isLigado());
        System.out.println(carroService.estadoAtual(carro));
    }

    @Test
    // Enquanto o carro estiver ligado deve ser capaz de: Desligar
    public void deveSerCapazDeDesligarQuandoLigado (){
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);

        // when:
        carroService.desligar(carro);

        // then:
        Assert.assertFalse(carro.isLigado());
    }

    @Test
    // Só podemos realizar as ações como acelerar e frear com o carro ligado
    public void naoDeveFrearDesligado () {
        // given:
        // carro e carroService são inicializados no @Before

        // when:
        carroService.frear(carro, 10);

        // then:
        Assert.assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    // Só podemos realizar as ações como acelerar e frear com o carro ligado
    public void naoDeveAcelerarDesligado () {
        // given:
        // carro e carroService são inicializados no @Before

        // when:
        carroService.acelerar(carro, 10);

        // then:
        Assert.assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    // Enquanto o carro estiver ligado deve ser capaz de: Mostrar estado atual
    public void verificaEstadoAtualQuandoLigado () {
        // given:
        // carro e carroService são inicializados no @Before

        // when:
        carroService.ligar(carro);

        // then:
        Assert.assertTrue(carro.isLigado());
        System.out.println(carroService.estadoAtual(carro));
    }

    @Test
    // Enquanto o carro estiver ligado deve ser capaz de: Acelerar
    public void deveAcelerarCorretamente () {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);

        // when:
        carroService.acelerar(carro, 10);

        // then:
        Assert.assertEquals(10, carro.getVelocidadeAtual());
    }

    @Test
    // O carro não pode passar de sua velocidade máxima
    public void naoDevePassarDaVelocidadeMaxima() {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);

        // when:
        carroService.acelerar(carro, 50);
        carroService.acelerar(carro, 50);
        carroService.acelerar(carro, 50);

        // then:
        Assert.assertEquals(150, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveDesligarEnquantoNaoParado() {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);

        // when:
        carroService.acelerar(carro, 50);
        carroService.desligar(carro);

        // then:
        Assert.assertTrue(carro.isLigado());
    }

    @Test
    // Enquanto o carro estiver ligado deve ser capaz de: Frear
    public void deveFrearCorretamente () {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);
        carroService.acelerar(carro, 50);

        // when:
        carroService.frear(carro, 10);

        // then:
        Assert.assertEquals(40, carro.getVelocidadeAtual());
    }

    @Test
    // Enquanto o carro estiver ligado deve ser capaz de: Frear
    public void deveFrearTotalCorretamente () {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);
        carroService.acelerar(carro, 50);

        // when:
        carroService.frear(carro, 10);
        carroService.frear(carro, 40);

        // then:
        Assert.assertEquals(0, carro.getVelocidadeAtual());
    }

    @Test
    // Só podemos desligar o carro quando ele parar totalmente (velocidadeAtual = 0)
    public void deveDesligarAposAcelerarFrear () {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);
        carroService.acelerar(carro, 50);
        carroService.frear(carro, 10);
        carroService.frear(carro, 40);

        // when:
        carroService.desligar(carro);

        // then:
        Assert.assertEquals(0, carro.getVelocidadeAtual());
        Assert.assertFalse(carro.isLigado());
    }

    @Test
    // Não existe velocidade negativa
    public void naoDeveTerVelocidadeNegativa () {
        // given:
        // carro e carroService são inicializados no @Before
        carroService.ligar(carro);
        carroService.acelerar(carro, 50);

        // when:
        carroService.frear(carro, 60);

        // then:
        Assert.assertEquals(0, carro.getVelocidadeAtual());
    }
}
