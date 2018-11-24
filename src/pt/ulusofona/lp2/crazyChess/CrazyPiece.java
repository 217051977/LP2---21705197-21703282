package pt.ulusofona.lp2.crazyChess;

class CrazyPiece {

    private int ID;
    private String imagePNG = null;
    private Position position;
    private Tipo tipo;
    private boolean podeMudarTipo, mudouTipo = false;


//  Construtores

    CrazyPiece(int ID, Position position, Tipo tipo, Boolean podeMudarTipo) {

//      set the variable of this same class as the value received
        this.ID = ID;
        this.position = position;
        this.tipo = tipo;
        this.podeMudarTipo = podeMudarTipo;

    }


//  gets
    int getID() {

//      return the value of the variable "id" of this same class
        return ID;

    }

    String getImagePNG() {

//      return the value of the variable "imagePNG" of this same class
        return imagePNG;

    }

    Position getPosition() {

//      return the value of the variable "position" of this same class
        return position;

    }

    Tipo getTipo() {

//      return the value of the variable "tipo" of this same class
        return tipo;

    }

    boolean getPodeMudarTipo() {

//      return the value of the variable "podeMudarTipo" of this same class
        return podeMudarTipo;

    }

    boolean getMudouTipo() {

//      return the value of the variable "jaMudouTipo" of this same class
        return mudouTipo;

    }


//  set
    boolean setImagePNG(String imagePNG) {

//      it tries to chage the value of the variable "tipo"
        try {

//          if the string received doesn't have .png in the end
            if (imagePNG.toLowerCase().charAt(imagePNG.length() - 4) != '.') {

                imagePNG += ".png";

//          if the 4 last letters are different from ".png"
            } else if (imagePNG.toLowerCase().charAt(imagePNG.length() - 3) != 'p' ||
                    imagePNG.toLowerCase().charAt(imagePNG.length() - 2) != 'n' ||
                    imagePNG.toLowerCase().charAt(imagePNG.length() - 1) != 'g') {

//              returns false
                return false;

            }



//          set the variable of this same class as the value received
            this.imagePNG = imagePNG;

//          return the value of the variable "true" of this same class
            return true;

//      if there is any problem in the above code
        } catch (Exception impossibleToChangeColor) {

//          print this message in screen
            System.out.println("Impossible to remove the image");

//          return the value of the variable "false" of this same class
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
