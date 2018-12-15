
package pt.ulusofona.lp2.crazyChess.CrazyPiece;

public class Tipo {

    private byte id;

    private boolean moveCima, moveBaixo, moveEsquerda, moveDireita,
                moveCimaDireita, moveCimaEsquerda, moveBaixoDireita, moveBaixoEsquerda;

    private int maxMovHorizontal, maxMovVertical, minMovHorizontal, minMovVertical;


//  constructor
    public Tipo() {}

    public Tipo(byte id) {

//      set the variable of this same class as the value received
        this.id = id;

        switch (id) {

            case 0: {

                this.moveBaixo = true;
                this.moveEsquerda = true;
                this.moveDireita = true;
                this.moveCima = true;
                this.moveCimaDireita = true;
                this.moveCimaEsquerda = true;
                this.moveBaixoDireita = true;
                this.moveBaixoEsquerda = true;
                this.maxMovHorizontal = 1;
                this.maxMovVertical = 1;
                this.minMovHorizontal = 0;
                this.minMovVertical = 0;

            }break;

            case 1: {

                System.out.println("Coming soon");

            }break;

            case 2: {

                System.out.println("Coming soon");

            }break;

            case 3: {

                System.out.println("Coming soon");

            }break;

            case 4: {

                System.out.println("Coming soon");

            }break;

            case 5: {

                System.out.println("Coming soon");

            }break;

            case 6: {

                System.out.println("Coming soon");

            }break;

            case 7: {

                System.out.println("Coming soon");

            }break;

            case 8: {

                System.out.println("Coming soon");

            }break;

            case 9: {

                System.out.println("Coming soon");

            }break;

            case 10: {

                System.out.println("Coming soon");

            }break;

            case 11: {

                System.out.println("Coming soon");

            }break;

            default: {

                System.out.println("That type does not exists!");

            }

        }


    }


//  gets
    public byte getid() {

//      return the value of the variable "tipo" of this same class
        return id;
    }

    public boolean getMoveCima() {

        return moveCima;

    }

    public boolean getMoveBaixo() {

        return moveBaixo;

    }

    public boolean getMoveEsquerda() {

        return moveEsquerda;

    }

    public boolean getMoveDireita() {

        return moveDireita;

    }

    public boolean getMoveBaixoDireta() {

        return moveBaixoDireita;

    }

    public boolean getMoveBaixoEsquerda() {

        return moveBaixoEsquerda;

    }

    public boolean getMoveCimaDireita() {

        return moveCimaDireita;

    }

    public boolean getMoveCimaEsquerda() {

        return moveCimaEsquerda;

    }

    public int getMaxMovHorizontal() {

        return maxMovHorizontal;

    }

    public int getMaxMovVertical() {

        return maxMovVertical;

    }

    public int getMinMovHorizontal() {

        return minMovHorizontal;

    }

    public int getMinMovVertical() {

        return minMovVertical;

    }

//  change
    public boolean changeTipo(Tipo tipo) {

//      it tries to chage the value of the variable "tipo"
        try {

//          set the variable of this same class as the value received
            this.id = tipo.getid();
            this.moveCima = tipo.getMoveCima();
            this.moveBaixo = tipo.getMoveBaixo();
            this.moveEsquerda = tipo.getMoveEsquerda();
            this.moveDireita = tipo.getMoveDireita();
            this.moveCimaDireita = tipo.getMoveCimaDireita();
            this.moveCimaEsquerda = tipo.getMoveCimaEsquerda();
            this.moveBaixoDireita = tipo.getMoveBaixoDireta();
            this.moveBaixoEsquerda = tipo.getMoveBaixoEsquerda();
            this.maxMovHorizontal = tipo.getMaxMovHorizontal();
            this.minMovHorizontal = tipo.getMinMovHorizontal();
            this.maxMovVertical = tipo.getMaxMovVertical();
            this.minMovVertical = tipo.getMinMovVertical();

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