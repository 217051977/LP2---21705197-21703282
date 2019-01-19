package pt.ulusofona.lp2.crazyChess;

import java.io.IOException;

public class InvalidSimulatorInputException extends IOException {

    private String descricaoProblema;
    private int linhaErro;

    public InvalidSimulatorInputException(int linhaErro, String descricaoProblema) {

        this.descricaoProblema = descricaoProblema;
        this.linhaErro = linhaErro;

    }

    public int getLinhaErro() {

        return linhaErro;

    }

    public String getDescricaoProblema() {

        return descricaoProblema;

    }

    @Override
    public String toString() {

        return "Na linha " + linhaErro + " deu o erro: " + descricaoProblema;

    }

}
