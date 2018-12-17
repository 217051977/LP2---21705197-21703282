package pt.ulusofona.lp2.crazyChess;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSimulador {

//  Board Size
    @Test
    public void testGetTamanhoTabuleiro() {
        Simulador simulador = new Simulador(4);
        assertEquals("The board size is not correct!", 4, simulador.getTamanhoTabuleiro());
    }

//  Crazy Pieces
    @Test
    public void testGetPecasMalucas() {
        Simulador simulador = new Simulador();
        createCrazyPiece(simulador, 1, 0, 3, 20, "White");
        createCrazyPiece(simulador, 2, 0, 0, 10, "Black");

        assertEquals("Those pieces wasn't the right ones!", simulador.crazyPieces, simulador.getPecasMalucas());
    }

//  Authors
    @Test
    public void testGetAutores() {
        Simulador simulador = new Simulador();
        List<String> authors = new ArrayList<>();
        authors.add("Bruno Miguel Dias Leal, nº 21705197");
        authors.add("João Domingos, nº 21703282");
        assertEquals("That wasn't the right author!", authors, simulador.getAutores());
    }

//  Number Of Pieces Captured
    @Test
    public void testGetNumberOfWhitePiecesCaptured() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 1, 20, "White");
        createCrazyPiece(simulador, 2, 0, 0, 10, "Black");
        simulador.processaJogada(0,0,0,1);
        assertEquals("Should exist 1 white piece captured!", 1, simulador.getNumberOfWhitePiecesCaptured());
    }

    @Test
    public void testGetNumberOfBlackPiecesCaptured() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 1, 20, "White");
        createCrazyPiece(simulador, 2, 0, 0, 10, "Black");
        simulador.processaJogada(0,1,0,0);
        assertEquals("Should exist 1 black piece captured!", 1, simulador.getNumberOfBlackPiecesCaptured());
    }

//  Number Of Invalid Plays
    //  White
    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 1, 20, "White");
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 4, 20, "White");
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 4, 20, "White");
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByWhiteTeam_Correct() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 1, 20, "White");
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByWhiteTeam());
    }

    //  Black
    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveEnemyPieces() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 20, "White");
        simulador.processaJogada(0,0,0,2);
        assertEquals("You can't move the enemy pieces!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 1, 10, "Black");
        simulador.processaJogada(0,1,0,1);
        assertEquals("You can't move the to the same position!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 2, 10, "Black");
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can't move an not existent piece!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 4, 10, "Black");
        simulador.processaJogada(0,4,0,0);
        assertEquals("You can't move more than what the piece allow you to!", 1, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

    @Test
    public void testGetNumberOfInvalidPlaysByBlackTeam_Correct() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 1, 10, "Black");
        simulador.processaJogada(0,1,0,0);
        assertEquals("You can move more the piece!", 0, simulador.getNumberOfInvalidPlaysByBlackTeam());
    }

//  Number Of Valid Plays
    //  White
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 0, 20, "White");
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsWhiteTeam() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 2, 20, "White");
        simulador.processaJogada(0,2,0,3);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

//  Black
    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_InvalidPlayAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 0, simulador.getNumberOfValidPlaysByWhiteTeam());
    }

    @Test
    public void testGetNumberOfValidPlaysByWhiteTeam_CorrectAsBlackTeam() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 1, 10, "Black");
        simulador.processaJogada(0,1,0,0);
        assertEquals("Shouldn't be considered as a valid play!", 1, simulador.getNumberOfValidPlaysByBlackTeam());
    }

//  Score
    @Test
    public void testGetScore() {
        Simulador simulador = new Simulador();
        simulador.scores.add("ola");
        List<String> list = new ArrayList<>();
        list.add("ola");
        assertEquals("Should be \"ola\"!", list, simulador.getResultados());
    }

//  ID Piece
    @Test
    public void testGetIDPiece_Exists() {
        Simulador simulador = new Simulador();
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        assertEquals("Should be \"1\"!", 1, simulador.getIDPeca(0, 0));
    }

    @Test
    public void testGetIDPiece_DoesntExists() {
        Simulador simulador = new Simulador();
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        assertEquals("Should be \"0\"!", 0, simulador.getIDPeca(0, 1));
    }

//  This Shift Team Id
    @Test
    public void testGetThisShiftTeamID() {
        Simulador simulador = new Simulador();
        assertEquals("Should be \"10\"!", 10, simulador.getThisShiftTeamID());
    }

//  Shift
    @Test
    public void testGetShift() {
        Simulador simulador = new Simulador();
        Shift shift = new Shift();
        assertEquals("Should be:\n Team ID: 10\nCount without captures: 0\nShift counter: 0", shift.toString(), simulador.getShift().toString());
    }

//  First Capture
    //  White
    @Test
    public void testGetFirstCapture_True_AsWhite() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 1, 1, 20, "White");
        createCrazyPiece(simulador, 2, 0, 0, 10, "Black");
        simulador.processaJogada(1,1,0,0);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsWhite() {
        Simulador simulador = new Simulador(4);
        simulador.shift.addCount();
        createCrazyPiece(simulador, 1, 0, 0, 20, "White");
        createCrazyPiece(simulador, 3, 1, 1, 10, "Black");
        simulador.processaJogada(1,1,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

    //  Black
    @Test
    public void testGetFirstCapture_True_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 1, 1, 20, "White");
        createCrazyPiece(simulador, 2, 0, 0, 10, "Black");
        simulador.processaJogada(0,0,1,1);
        assertTrue("First Capture should be true!", simulador.getFirstCapture());
    }

    @Test
    public void testGetFirstCapture_False_AsBlack() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 20, "White");
        createCrazyPiece(simulador, 2, 1, 1, 10, "Black");
        simulador.processaJogada(0,0,1,0);
        assertFalse("First Capture should be false!", simulador.getFirstCapture());
    }

//    @Test
//    public void testsetPeca(){
//        Simulador simulador = new Simulador();
//        Team brancas = new Team(1);
//        Tipo rei = new Tipo((byte) 0);
//        Position positionPeca = new Position(0,0);
//        CrazyPiece peca = new CrazyPiece(7, rei, brancas, "Jonny Macarrony");
//        peca.setPosition(positionPeca);
//        simulador.setPeca(peca);
//        simulador.processaJogada(0,1,1,0);
//        assertEquals("Erro", 1, simulador.pecasMalucas.size());
//    }
//
//    @Test
//    public void testremovePeca(){
//        Simulador simulador = new Simulador();
//        Team brancas = new Team(1);
//        Tipo rei = new Tipo((byte) 0);
//        Position positionPeca = new Position(0,0);
//        CrazyPiece peca = new CrazyPiece(7, rei, brancas, "Jonny Macarrony");
//        peca.setPosition(positionPeca);
//        simulador.setPeca(peca);
//        simulador.removePeca(peca);
//        simulador.processaJogada(0,1,1,0);
//        assertEquals("Erro", 0, simulador.pecasMalucas.size());
//    }

//  Start Game
    @Test
    public void testStartGame_Empty() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_EMPTY.txt");
        assertFalse("There's no info in it!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_BOARD_SIZE.txt");
        assertFalse("Board size or numbers of pieces are missing!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Lower_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_LOWER_BOARD_SIZE.txt");
        assertFalse("Board Size is lower than the layout or is too much info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Higher_BoardSize() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_HIGHER_BOARD_SIZE.txt");
        assertFalse("Board Size is higher than the layout or is missing info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_NUMBER_OF_PIECES.txt");
        assertFalse("Board size or numbers of pieces are missing!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Lower_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_LOWER_NUMBER_OF_PIECES.txt");
        assertFalse("Board Size is lower than the layout or is too much info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Higher_NumberOfPieces() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_HIGHER_NUMBER_OF_PIECES.txt");
        assertFalse("Board Size is higher than the layout or is missing info on the layout it self!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_PIECES_LINE_AND_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_Line() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_PIECES_LINE.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Pieces_Column() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_PIECES_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_LineAndColumn() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_BOARD_LINE_AND_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_Line() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_BOARD_LINE.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Missing_Board_Column() {
        Simulador simulador = new Simulador();
        File file = new File("FILE_TEST_MISSING_BOARD_COLUMN.txt");
        assertFalse("There's missing info on the layout it self or board Size is higher than the layout!", simulador.iniciaJogo(file));
    }

    @Test
    public void testStartGame_Correct() {
        Simulador simulador = new Simulador();
        File file = new File("RUN_FILE.txt");
        assertTrue("Should work!", simulador.iniciaJogo(file));
    }

//  Execute The Move
    @Test
    public void testExecuteTheMove_AbleToMoveThePiece() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        assertTrue("You can move the piece!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_MoveEnemyPiece() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 20, "White");
        assertFalse("You can only move your teams pieces!", simulador.processaJogada(0,0,0,1));
    }

    @Test
    public void testExecuteTheMove_MoveToTheSamePosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 10, "Black");
        assertFalse("You can't move to the same position!", simulador.processaJogada(0,0,0,0));
    }

    @Test
    public void testExecuteTheMove_MoveToAnOutBoardHouse() {
        Simulador simulador = new Simulador(4);
        assertFalse("You can't move a piece to an out of the board house!", simulador.processaJogada(0,0,0,-1));
    }

    @Test
    public void testExecuteTheMove_MoveFromAnEmptyPosition() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 20, "Black");
        assertFalse("There's no piece there!", simulador.processaJogada(0,1,0,2));
    }

    @Test
    public void testExecuteTheMove_MoveFromOutOfTheBoard() {
        Simulador simulador = new Simulador(4);
        assertFalse("You can't move a piece form out of the board!", simulador.processaJogada(0,-1,0,2));
    }

    @Test
    public void testExecuteTheMove_MoveMoreThanItCan() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 1, 0, 0, 20, "Black");
        assertFalse("You can't move so many hoses!", simulador.processaJogada(0,0,0,2));
    }

//  Game Over
    @Test
    public void testGameOver_NoPieces() {
        Simulador simulador = new Simulador(4);
        assertTrue("White team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_10ShiftsWithoutAnyCapture_And_NoFirstCapture() {
        Simulador simulador = new Simulador(4);
        simulador.shift.countNoCapture = 10;
        createCrazyPiece(simulador, 3, 0, 0, 20, "White");
        createCrazyPiece(simulador, 4, 0, 0, 10, "Black");
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_10ShiftsWithoutAnyCapture_And_FirstCapture() {
        Simulador simulador = new Simulador(4);
        simulador.shift.countNoCapture = 10;
        simulador.firstCapture = true;
        createCrazyPiece(simulador, 5, 0, 0, 20, "White");
        createCrazyPiece(simulador, 6, 0, 0, 10, "Black");
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 7, 0, 0, 20, "White");
        createCrazyPiece(simulador, 8, 0, 0, 10, "Black");
        assertTrue("One white king and one black king is a draw!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 9, 0, 0, 20, "White");
        createCrazyPiece(simulador, 10, 0, 0, 20, "White");
        assertTrue("White team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiece(simulador, 11, 0, 0, 10, "Black");
        createCrazyPiece(simulador, 12, 2, 0, 10, "Black");
        assertTrue("Black team wins!", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_TwoWhiteKingsAndOneBlackKing() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame(simulador, 13, 0, 20, "White");
        createCrazyPiecePresentInGame(simulador, 14, 1, 10, "Black");
        createCrazyPiecePresentInGame(simulador, 15, 2, 20, "White");
        assertFalse("With two white kings and one black king the game keeps rolling", simulador.jogoTerminado());
    }

    @Test
    public void testGameOver_OneWhiteKingAndTwoBlackKings() {
        Simulador simulador = new Simulador(4);
        createCrazyPiecePresentInGame(simulador, 13, 0, 10, "Black");
        createCrazyPiecePresentInGame(simulador, 14, 1, 20, "White");
        createCrazyPiecePresentInGame(simulador, 15, 2, 10, "Black");
        assertFalse("With one white king and two black kings the game keeps rolling!", simulador.jogoTerminado());
    }

    //movHorizontal


    private void createCrazyPiece (Simulador simulador, int PieceId, int x, int y, int idTeam, String name) {

        Position piecePosition = new Position(x,y);
        CrazyPiece piece = new ReiBranco(PieceId, idTeam, name);
        piece.setPosition(piecePosition);
        simulador.crazyPieces.add(piece);

    }

    private void createCrazyPiecePresentInGame(Simulador simulador, int PieceId, int x, int idTeam, String name) {

        Position piecePosition = new Position(x, 0);
        CrazyPiece piece = new ReiBranco(PieceId, idTeam, name);
        piece.isInGame();
        piece.setPosition(piecePosition);
        simulador.crazyPieces.add(piece);

    }

}