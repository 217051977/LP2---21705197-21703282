package pt.ulusofona.lp2.crazyChess;

import java.awt.*;

public class Equipa {

    private int id;
    private String nome;
    private Color cor;

    Equipa(int id, String nome, Color cor) {

        this.id = id;
        this.nome = nome;
        this.cor = cor;

    }

    int getId() {

        return id;

    }

    String getNome() {

        return nome;

    }

    Color getCor() {

        return cor;

    }


    boolean changeColor(Color color) {

        try {

            this.cor = color;

            return true;

        } catch (Exception impossibleToChangeColor) {

            System.out.println("Impossible to change color!");

            return false;

        }

    }

    boolean changeName(String nome) {

        try {

            this.nome = nome;

            return true;

        } catch (Exception impossibleToChangeName) {

            System.out.println("Impossible to change the name");

            return false;

        }

    }

    public String toString() {

        return "ID: " + id + "\nNome: " + nome + "\nCor: " + cor;

    }
}
