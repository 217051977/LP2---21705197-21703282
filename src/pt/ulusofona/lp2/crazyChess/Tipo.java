package pt.ulusofona.lp2.crazyChess;

class Tipo {

//  variable
    private String tipo;
    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
                moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;
    private int maxMovHorizontal, maxMovVertical;


//  constructor
    Tipo(String tipo,
         boolean moveBaixo, boolean moveEsquerda, boolean moveDireita, boolean moveCima,
         boolean moveBaixoDireta, boolean moveBaixoEsquerda, boolean moveCimaDireta, boolean moveCimaEsquerda,
         int maxMovHorizontal, int maxMovVertical) {

//      set the variable of this same class as the value received
        this.tipo = tipo;
        this.moveBaixo = moveBaixo;
        this.moveEsquerda = moveEsquerda;
        this.moveDireita = moveDireita;
        this.moveCima = moveCima;
        this.moveCimaDireita = moveCimaDireta;
        this.moveCimaEsquerda = moveCimaEsquerda;
        this.moveBaixoDireita = moveBaixoDireta;
        this.moveBaixoEsquerda = moveBaixoEsquerda;
        this.maxMovHorizontal = maxMovHorizontal;
        this.maxMovVertical = maxMovVertical;

    }


//  gets
    String getTipo() {

//      return the value of the variable "tipo" of this same class
        return tipo;

    }

    boolean getMoveCima() {

        return moveCima;

    }

    boolean getMoveBaixo() {

        return moveBaixo;

    }

    boolean getMoveEsquerda() {

        return moveEsquerda;

    }

    boolean getMoveDireita() {

        return moveDireita;

    }

    boolean getMoveBaixoDireta() {

        return moveBaixoDireita;

    }

    boolean getMoveBaixoEsquerda() {

        return moveBaixoEsquerda;

    }

    boolean getMoveCimaDireita() {

        return moveCimaDireita;

    }

    boolean getMoveCimaEsquerda() {

        return moveCimaEsquerda;

    }

    public int getMaxMovHorizontal() {

        return maxMovHorizontal;

    }

    public int getMaxMovVertical() {

        return maxMovVertical;

    }

    //  change
    boolean changeTipo(Tipo tipo) {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            this.tipo = tipo.getTipo();

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception notAbleToChangeType) {

//          print this message in screen
            System.out.println("Impossible to change the type!");

//          return the value of the variable "false" of this same class
            return false;

        }

    }

}
