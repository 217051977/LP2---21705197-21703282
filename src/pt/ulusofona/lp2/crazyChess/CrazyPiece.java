package pt.ulusofona.lp2.crazyChess;

class CrazyPiece {

    private int ID;
    private String imagePNG = null;
    private Position position;
    private Tipo tipo;
    private boolean podeMudarTipo, mudouTipo = false;


//  Construtores
    CrazyPiece() {}

    CrazyPiece(int ID, Position position, Tipo tipo, Boolean podeMudarTipo) {

        this.ID = ID;
        this.position = position;
        this.tipo = tipo;
        this.podeMudarTipo = podeMudarTipo;

    }


//  gets
    int getID() {

        return ID;

    }

    String getImagePNG() {

        return imagePNG;

    }

    Position getPosition() {

        return position;

    }

    Tipo getTipo() {

        return tipo;

    }

    boolean getPodeMudarTipo() {

        return podeMudarTipo;

    }

    boolean getMudouTipo() {

        return mudouTipo;

    }

//  set
    boolean setImagePNG(String imagePNG) {

        try {

            if (!imagePNG.toLowerCase().contains(".png")) {

                imagePNG += ".png";

            }

            this.imagePNG = imagePNG;

            return true;

        } catch (Exception imageNotFound){

            System.out.println("Impossible to remove the image");

            return false;

        }

    }


//  remove
    boolean removeIamgePNG(){

        try{

            this.imagePNG = null;

            return true;

        } catch (Exception impossibleToRemoveImage){

            System.out.println("Impossible to remove the image");

            return false;

        }

    }

//  has
    boolean hasImagePNG(String imagePNG) {

        return this.imagePNG.equals(imagePNG);

    }

//  change
    boolean changePosition(int xPos, int yPos) {

        return position.chagePosition(xPos, yPos);

    }

    boolean changeType(Tipo tipo) {

        if (podeMudarTipo && !mudouTipo && tipo.changeTipo(tipo)){

            mudouTipo = true;
            return true;

        }

        return false;

    }


//  toString (por acabar)
    public String toString() {

        String string = "ID = " + ID +
                        " | Tipo = " + tipo.getTipo() +
                        " | ID Equipa = " /*missing id equipa e \n alcunha*/;
        if(position.getxAtual() == null || position.getyAtual() == null){

            string += "(n/a)";

        }else {

            string += "(<" + position.getxAtual() + ">, <" + position.getyAtual() + ">)";

        }

        return string;
    }

}
