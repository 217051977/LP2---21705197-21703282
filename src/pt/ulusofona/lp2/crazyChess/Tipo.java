package pt.ulusofona.lp2.crazyChess;

class Tipo {

    private String tipo;

//    construtor
    Tipo(String tipo) {

//        set the variable of this same class as the value received
        this.tipo = tipo;

    }

//    get
    String getTipo() {

//        return the value of the variable "tipo" of this same class
        return tipo;

    }

//    chages the type
    boolean changeTipo(Tipo tipo) {

//        it tries to chage the value of the variable "tipo"
        try {

//            set the variable of this same class as the value received
            this.tipo = tipo.getTipo();

//            return the value of the variable "true" of this same class
            return true;

//            if there is any problem in the above code
        } catch (Exception notAbleToChangeType) {

            System.out.println("Impossible to change the type!");

//            return the value of the variable "false" of this same class
            return false;

        }

    }

}
