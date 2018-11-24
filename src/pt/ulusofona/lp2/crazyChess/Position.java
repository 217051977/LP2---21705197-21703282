package pt.ulusofona.lp2.crazyChess;

class Position {

    private Integer xAtual, yAtual, xInicial, yInicial;

//    construtor
    Position(int xInicial, int yInicial) {

        this.xInicial = xInicial;
        xAtual = xInicial;
        this.yInicial = yInicial;
        yAtual = yInicial;

    }

//    gets
    Integer getxAtual() {

        return xAtual;

    }

    Integer getyAtual() {

        return yAtual;

    }

    Integer getxInicial() {

        return xInicial;

    }

    Integer getyInicial() {

        return yInicial;

    }

//    chage the atual position
    boolean chagePosition(int x, int y) {

        try {

            this.xAtual = x;
            this.yAtual = y;

            return true;

        } catch (Exception notAbleToChangePosition) {

            System.out.println("Não foi possivel de mudar a posição");
            return false;

        }

    }

//    reset the position to the initial one
    boolean resetAtualPosition() {

        try {

            xAtual = xInicial;
            yAtual = yInicial;

            return true;

        } catch (Exception notAbleToReset) {

            System.out.println("Não foi possível voltar a posição inicial!");

            return false;

        }
    }

//    erase position
    boolean erasePosition() {
        try {

            xAtual = null;
            yAtual = null;

            return true;

        } catch (Exception notAbleToErase) {

            System.out.println("Foi impossível de elimar a posição");

            return false;

        }
    }

}
