package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSimulador {

    @Test
    public void testgetTamanhoTabuleiro1(){
        Simulador simulador = new Simulador(4);
        assertEquals("Erro", 4, simulador.getTamanhoTabuleiro());
    }

    @Test
    public void testgetTamanhoTabuleiro2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        assertEquals("Erro", 4, simulador.getTamanhoTabuleiro());
    }

    @Test
    public void testgetPecasMalucas(){
        Simulador simulador = new Simulador();
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca3 = new Position(0,3);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca1.setPosition(positionPeca1);
        CrazyPiece peca3 = new CrazyPiece(9, rei, 1, "This is crazy");
        peca3.setPosition(positionPeca3);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca3);
        assertEquals("Erro", simulador.pecasMalucas, simulador.getPecasMalucas());
    }

    @Test
    public void testgetAutores(){
        Simulador simulador = new Simulador();
        simulador.autores.add("Zé das Esquinas");
        simulador.processaJogada(0,0,0,1);
        assertEquals("Erro", simulador.autores, simulador.getAutores());
    }

    @Test
    public void testgetBrancasCapturadas(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(0,1);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca1.setPosition(positionPeca1);
        CrazyPiece peca2 = new CrazyPiece(8, rei, 1, "Mr. Cenas");
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.processaJogada(0,0,0,1);
        assertEquals("Erro", simulador.numeroDeBrancasCapturadas, simulador.getNumeroDeBrancasCapturadas());
    }

    @Test
    public void testgetPretasCapturas(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(0,1);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca1.setPosition(positionPeca1);
        CrazyPiece peca2 = new CrazyPiece(8, rei, 1, "Mr. Cenas");
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Erro", simulador.numeroDePretasCapturas, simulador.getNumeroDePretasCapturas());
    }

    @Test
    public void testgetBrancasInvalidas1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,0,0,2);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetBrancasInvalidas2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,1);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetBrancasInvalidas3(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetPretasInvalidas1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,0,0,2);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetPretasInvalidas2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,1);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetPretasInvalidas3(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetTentativasBrancas1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,0,0,1);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetTentativasBrancas2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetTentativasBrancas3(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,1,0);
        assertEquals("Erro", simulador.numeroDeBrancasInvalidas, simulador.getNumeroDeBrancasInvalidas());
    }

    @Test
    public void testgetTentativasPretas1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,0,0,1);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetTentativasPretas2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,0,0);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetTentativasPretas3(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        simulador.processaJogada(0,1,1,0);
        assertEquals("Erro", simulador.numeroDePretasInvalidas, simulador.getNumeroDePretasInvalidas());
    }

    @Test
    public void testgetResultados(){
        Simulador simulador = new Simulador();
        simulador.resultados.add("ola");
        assertEquals("Erro", simulador.resultados, simulador.getResultados());
    }

    @Test
    public void testgetIDPeca(){
        Simulador simulador = new Simulador();
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca = new Position(0,0);
        CrazyPiece peca = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        peca.setPosition(positionPeca);
        simulador.pecasMalucas.add(peca);
        assertEquals("Erro", peca.getId(), simulador.getIDPeca(0, 0));
    }

    @Test
    public void testgetIDEquipaAJogar(){
        Simulador simulador = new Simulador();
        simulador.turno = new Turno();
        assertEquals("Erro", simulador.turno.getIdTeam(), simulador.getIDEquipaAJogar());
    }

    @Test
    public void testgetTurno(){
        Simulador simulador = new Simulador();
        simulador.turno = new Turno();
        assertEquals("Erro", simulador.turno, simulador.getTurno());
    }

    @Test
    public void testgetPrimeiraCaptura(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(1,1);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        CrazyPiece peca2 = new CrazyPiece(7, rei, 0, "The lazy");
        peca1.setPosition(positionPeca1);
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.processaJogada(1,1,0,0);
        assertEquals("Erro", simulador.primeiraCaptura, simulador.getPrimeiraCaptura());
    }

//    @Test
//    public void testsetPeca(){
//        Simulador simulador = new Simulador();
//        Equipa brancas = new Equipa(1);
//        Tipo rei = new Tipo((byte) 0);
//        Position positionPeca = new Position(0,0);
//        CrazyPiece peca = new CrazyPiece(7, rei, brancas, "Jonny Macarrony");
//        peca.setPosition(positionPeca);
//        simulador.setPeca(peca);
//        simulador.processaJogada(0,1,1,0);
//        assertEquals("Erro", 1, simulador.pecasMalucas.size());
//    }
//
//    @Test
//    public void testremovePeca(){
//        Simulador simulador = new Simulador();
//        Equipa brancas = new Equipa(1);
//        Tipo rei = new Tipo((byte) 0);
//        Position positionPeca = new Position(0,0);
//        CrazyPiece peca = new CrazyPiece(7, rei, brancas, "Jonny Macarrony");
//        peca.setPosition(positionPeca);
//        simulador.setPeca(peca);
//        simulador.removePeca(peca);
//        simulador.processaJogada(0,1,1,0);
//        assertEquals("Erro", 0, simulador.pecasMalucas.size());
//    }

    @Test
    public void testiniciaJogo(){
        Simulador simulador = new Simulador();
        File file = new File("");
        assertFalse("Erro", simulador.iniciaJogo(file));
    }

    @Test
    public void testProcessaJogada1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        simulador.autores.add("Zé das Esquinas");
        Equipa pretas = new Equipa(0);
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(0,1);
        Position positionPeca3 = new Position(0,3);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca1.setPosition(positionPeca1);
        CrazyPiece peca2 = new CrazyPiece(8, rei, 1, "Mr. Cenas");
        peca2.setPosition(positionPeca2);
        CrazyPiece peca3 = new CrazyPiece(9, rei, 1, "This is crazy");
        peca3.setPosition(positionPeca3);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.pecasMalucas.add(peca3);
        simulador.processaJogada(0,0,0,1);
        assertTrue("Há uma peça comida", simulador.getPrimeiraCaptura());
    }

    @Test
    public void testProcessaJogada2(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        simulador.autores.add("Zé das Esquinas");
        Equipa pretas = new Equipa(0);
        Equipa brancas = new Equipa(1);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(0,1);
        Position positionPeca3 = new Position(0,3);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        peca1.setPosition(positionPeca1);
        CrazyPiece peca2 = new CrazyPiece(8, rei, 1, "Mr. Cenas");
        peca2.setPosition(positionPeca2);
        CrazyPiece peca3 = new CrazyPiece(9, rei, 1, "This is crazy");
        peca3.setPosition(positionPeca3);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.pecasMalucas.add(peca3);
        simulador.processaJogada(0,0,0,2);
        assertFalse("Não há nenhuma peça comida", simulador.getPrimeiraCaptura());
    }

    @Test
    public void testjogoTerminado1(){
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(1);
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0,0);
        Position positionPeca2 = new Position(2,0);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        CrazyPiece peca2 = new CrazyPiece(7, rei, 0, "Mr. me");
        peca1.setPosition(positionPeca1);
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        assertTrue("Erro", simulador.jogoTerminado());
    }

    @Test
    public void testjogoTerminado2() {
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0, 0);
        Position positionPeca2 = new Position(2, 0);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 0, "Jonny Macarrony");
        CrazyPiece peca2 = new CrazyPiece(7, rei, 0, "Mr. me");
        peca1.setPosition(positionPeca1);
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        assertTrue("Erro", simulador.jogoTerminado());
    }

    @Test
    public void testjogoTerminado3() {
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0, 0);
        Position positionPeca2 = new Position(2, 0);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        CrazyPiece peca2 = new CrazyPiece(7, rei, 1, "Mr. me");
        peca1.setPosition(positionPeca1);
        peca2.setPosition(positionPeca2);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        assertTrue("Erro", simulador.jogoTerminado());
    }

    @Test
    public void testjogoTerminado4() {
        Simulador simulador = new Simulador();
        simulador.tamanhoTabuleiro = 4;
        Equipa brancas = new Equipa(0);
        Equipa pretas = new Equipa(0);
        Tipo rei = new Tipo((byte) 0);
        Position positionPeca1 = new Position(0, 0);
        Position positionPeca2 = new Position(2, 0);
        Position positionPeca3 = new Position(1, 0);
        CrazyPiece peca1 = new CrazyPiece(7, rei, 1, "Jonny Macarrony");
        CrazyPiece peca2 = new CrazyPiece(7, rei, 1, "Mr. me");
        CrazyPiece peca3 = new CrazyPiece(7, rei, 0, "Yeah");
        peca1.setPosition(positionPeca1);
        peca2.setPosition(positionPeca2);
        peca2.setPosition(positionPeca3);
        simulador.pecasMalucas.add(peca1);
        simulador.pecasMalucas.add(peca2);
        simulador.pecasMalucas.add(peca3);
        assertTrue("Erro", simulador.jogoTerminado());
    }

    //movHorizontal

}