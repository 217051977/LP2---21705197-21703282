package pt.ulusofona.lp2.crazyChess;

class Tipo {

//  variable
    private String tipo;
    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita;
    private int maxMov;


//  constructor
    Tipo(String tipo, boolean moveBaixo, boolean moveEsquerda, boolean moveDireita, boolean moveCima, int maxMov) {

//      set the variable of this same class as the value received
        this.tipo = tipo;
        this.moveBaixo = moveBaixo;
        this.moveEsquerda = moveEsquerda;
        this.moveDireita = moveDireita;
        this.moveCima = moveCima;
        this.maxMov = maxMov;

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

    public int getMaxMov() {

        return maxMov;

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
