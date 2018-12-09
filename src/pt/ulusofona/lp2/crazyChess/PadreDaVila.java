package pt.ulusofona.lp2.crazyChess;

public class PadreDaVila extends CrazyPiece{

    private byte id = 3;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
            moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;

    PadreDaVila() {

        this.moveBaixo = false;
        this.moveEsquerda = false;
        this.moveDireita = false;
        this.moveCima = false;
        this.moveCimaDireita = true;
        this.moveCimaEsquerda = true;
        this.moveBaixoDireita = true;
        this.moveBaixoEsquerda = true;
        this.maxMovHorizontal = 3;
        this.maxMovVertical = 3;
        this.minMovHorizontal = 1;
        this.minMovVertical = 1;

    }

//  so pode mover se no caminho (nao no local) nao houver pecas adeversarias.
//  nao pode estar a menos de 2 casa da rainha adversaria.

}
