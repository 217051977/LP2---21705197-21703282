package pt.ulusofona.lp2.crazyChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Simulador {

    private int boardSize;
    private int numberOfBlackPiecesCaptured = 0;
    private int numberOfWhitePiecesCaptured = 0;
    private int numberOfValidPlaysByBlackTeam = 0;
    private int numberOfValidPlaysByWhiteTeam = 0;
    private int numberOfInvalidPlaysByBlackTeam = 0;
    private int numberOfInvalidPlaysByWhiteTeam = 0;
    List<CrazyPiece> crazyPiecesInGame = new ArrayList<>();
    private List<CrazyPiece> allCrazyPieces = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<ValidPlay> suggestedPlay = new ArrayList<>();
    private List<String> scores = new ArrayList<>();
    Shift shift = new Shift();
    boolean firstCapture = false;
    private Position previousPosition;
    private CrazyPiece previousCrazyPiece;
    private CrazyPiece crazyPieceRemovedFromTheGame;
    private List<CrazyPiece> crazyPieceRemovedFromTheGameAux = new ArrayList<>();
    private int hasCaughtAPiece = 0;
    private int previousCountNoCapture = -1;
    private boolean hasMadeUndo = false;
    private String s = "EMPATE";
    private Position positionErased;
    private Map<String, List<String>> estatisticas;
    private Map<String, Integer> captures = new HashMap<>();
    private Map<String, Integer> captured = new HashMap<>();
    private Map<String, Integer> valid = new HashMap<>();
    private Map<String, Integer> invalids = new HashMap<>();

    public Map<String, List<String>> getEstatistivas() {

        estatisticas.clear();

//      create a map so it can keep the relation number of invalid plays/number of plays of each piece
        Map<String, Integer> relation_NInvalidPlays_NPlays_map = new HashMap<>();
        List<String> valueToReturn = new ArrayList<>();

        List<Map.Entry<String, Integer>> invalids_List = changeMap_To_List(invalids);

        invalids_List.stream().filter((invalid) -> valid.containsKey(invalid.getKey()))
                .forEach((invalid) ->
                        relation_NInvalidPlays_NPlays_map.put(invalid.getKey(),
                                (invalids.get(invalid.getKey()) /
                                        (invalids.get(invalid.getKey()) + valid.get(invalid.getKey())))));

//      create a list with the key entry of captures, map type variable
        List<Map.Entry<String, Integer>> capturesList = orderedList(
                changeMap_To_List(captures));

//      create lists of the best x with the key entry of the map type variable used
        List<Map.Entry<String, Integer>> top5Points_list = getTop_X(orderedList(
                changeMap_To_List(valid)), 5);

        List<Map.Entry<String, Integer>> mostCaptured_list = getTop_X(orderedList(
                changeMap_To_List(captured)), captured.size());

        List<Map.Entry<String, Integer>> top3_Relation_NInvalidPlays_NPlays_list = getTop_X(orderedList(
                changeMap_To_List(relation_NInvalidPlays_NPlays_map)), relation_NInvalidPlays_NPlays_map.size());

//      create lists of the best x with the the list used
        List<Map.Entry<String, Integer>> top5Captures_list = getTop_X(capturesList, 5);

        List<Map.Entry<String, Integer>> piecesWith_5OrMore_Captures_list = getTop_X(capturesList,
                (int) capturesList.stream().filter((vp) -> vp.getValue() > 5).count());

//      create maps by creating a list like the above one, order it by it value and the changing it to map again
        Map<String, Integer> top5Captures_map = changeList_To_Map(top5Captures_list);
        Map<String, Integer> top5Points_map = changeList_To_Map(top5Points_list);
        Map<String, Integer> piecesWith_5OrMore_Captures_map = changeList_To_Map(piecesWith_5OrMore_Captures_list);
        Map<String, Integer> top3_Relation_NInvalidPlays_NPlays_map = changeList_To_Map(
                top3_Relation_NInvalidPlays_NPlays_list);
        Map<String, Integer> MostCaptured_map = changeList_To_Map(mostCaptured_list);

        estatisticas.put("top5Capturas", getValueToReturn(top5Captures_list, top5Captures_map));
        estatisticas.put("top5Pontos", getValueToReturn(top5Points_list, top5Points_map));
        estatisticas.put("pecasMais5Capturas", getValueToReturn(piecesWith_5OrMore_Captures_list,
                                                                    piecesWith_5OrMore_Captures_map));

        top3_Relation_NInvalidPlays_NPlays_list.stream()
                .forEach((i) ->
                        crazyPiecesInGame.stream()
                                .filter((thisPiece) -> thisPiece.getId() == Integer.parseInt(i.getKey()))
                                .forEach((thisPiece) ->
                                        valueToReturn.add(thisPiece.getIDTeam() + ":" + thisPiece.getName() + ":" +
                                                invalids.get(i.getKey()) + ":" + valid.get(i.getKey()))
                                ));

        estatisticas.put("3pecasMaisBaralhadas",valueToReturn);

        mostCaptured_list.stream()
                .forEach((i) ->
                        crazyPiecesInGame.stream()
                                .filter((thisPiece) -> thisPiece.getId() == Integer.parseInt(i.getKey()))
                                .forEach((thisPiece) ->
                                        valueToReturn.add(thisPiece.getType() + ":" + i.getValue())
                                ));

        estatisticas.put("tiposPecaCapturados", valueToReturn);

        return estatisticas;

    }

//  Constructor
    public Simulador() {}//*********************************************************************************************

    public Simulador(int boardSize) {

        this.boardSize = boardSize;

    }//*****************************************************************************

//  gets
    public int getTamanhoTabuleiro() {

        return boardSize;

    }//****************************************************************************

    public List<CrazyPiece> getPecasMalucas() {

        return crazyPiecesInGame;

    }//*******************************************************************

    public List<String> getAutores() {

        if (authors.size() == 0) {

            this.authors.add("Bruno Miguel Dias Leal, nº 21705197");
            this.authors.add("João Domingos, nº 21703282");

        }

        return authors;

    }//****************************************************************************

    private List<Integer> checkNumberOfBlackKings_WhiteKings_PiecesInGame() {

        s = "EMPATE";

//      set nWhiteKing and nBlackKing as 0
        int nBlackKing = 0;
        int nWhiteKing = 0;
        int piecesInGame = 0;
        List<Integer> blackKings_WhiteKings_PiecesInGame = new ArrayList<>();

        for (CrazyPiece piece : crazyPiecesInGame) {

            if (piece.getInGame()) {

                if (piece.getType() == 0) {

                    if (piece.getIDTeam() == 10) {

                        nBlackKing++;

                    } else if (piece.getIDTeam() == 20) {

                        nWhiteKing++;

                    }

                }

                piecesInGame++;

            }

        }

        blackKings_WhiteKings_PiecesInGame.add(nBlackKing);
        blackKings_WhiteKings_PiecesInGame.add(nWhiteKing);
        blackKings_WhiteKings_PiecesInGame.add(piecesInGame);

        return blackKings_WhiteKings_PiecesInGame;

    }

    public List<String> getResultados() {

        List<Integer> kingsAndPieces = checkNumberOfBlackKings_WhiteKings_PiecesInGame();

//      set nWhiteKing and nBlackKing as the values returned from the previous function
        int piecesInGame = kingsAndPieces.get(2);
        int nWhiteKing = kingsAndPieces.get(1);
        int nBlackKing = kingsAndPieces.get(0);
        boolean hasScore = false;

        if (piecesInGame <= 2) {

            if (nBlackKing == 1 && nWhiteKing == 1 || nBlackKing == 0 && nWhiteKing == 0) {

                s = "EMPATE";

                hasScore = true;

            }

        }

        if (!hasScore) {

            if (nBlackKing == 0) {

                s = "VENCERAM AS BRANCAS";

            } else if (nWhiteKing == 0) {

                s = "VENCERAM AS PRETAS";

            }

        }

        scores.clear();

        scores.add("JOGO DE CRAZY CHESS");
        scores.add("Resultado: " + s);
        scores.add("---");
        scores.add("Equipa das Pretas");
        scores.add(" Capturas: " + numberOfWhitePiecesCaptured);
        scores.add(" Jogadas válidas: " + numberOfValidPlaysByBlackTeam);
        scores.add(" Tentativas inválidas: " + numberOfInvalidPlaysByBlackTeam);
        scores.add("Equipa das Brancas");
        scores.add(" Capturas: " + numberOfBlackPiecesCaptured);
        scores.add(" Jogadas válidas: " + numberOfValidPlaysByWhiteTeam);
        scores.add(" Tentativas inválidas: " + numberOfInvalidPlaysByWhiteTeam);

        return scores;

    }//*************************************************************************

    public int getIDPeca(int x, int y) {

        Position position = new Position(x, y);

        for (CrazyPiece piece: crazyPiecesInGame){

            if (piece.getPosition().equals(position)) {

                return piece.getId();

            }

        }

        //System.out.println("Piece not founded!");

        return 0;

    }//**************************************************************************

    public int getIDEquipaAJogar() {

        return shift.getIdTeam();

    }//******************************************************************************

    public int getNumberOfBlackPiecesCaptured() {

        return numberOfBlackPiecesCaptured;

    }//*****************************************************************

    public int getNumberOfWhitePiecesCaptured() {

        return numberOfWhitePiecesCaptured;

    }//*****************************************************************

    public int getNumberOfValidPlaysByBlackTeam() {

        return numberOfValidPlaysByBlackTeam;

    }//***************************************************************

    public int getNumberOfValidPlaysByWhiteTeam() {

        return numberOfValidPlaysByWhiteTeam;

    }//***************************************************************

    public int getNumberOfInvalidPlaysByBlackTeam() {

        return numberOfInvalidPlaysByBlackTeam;

    }//*************************************************************

    public int getNumberOfInvalidPlaysByWhiteTeam() {

        return numberOfInvalidPlaysByWhiteTeam;

    }//*************************************************************

    public Shift getShift() {

        return shift;

    }//*************************************************************************************

    public boolean getFirstCapture() {

        return firstCapture;

    }//****************************************************************************

    public int getPreviousCountNoCapture() {

        return previousCountNoCapture;

    }//**********************************************************************

//  set
    private String setTeamStats(int numberOfValidPlays, int numberOfPiecesCaptured, int numberOfInvalidPlays) {

        return ":" + numberOfValidPlays +
                ":" + numberOfPiecesCaptured +
                ":" + numberOfInvalidPlays;

    }//***

//  gravar jogo
    public void iniciaJogo(File ficheiroInicial) throws InvalidSimulatorInputException {

//      inicia o jogo por fazer reset a todas as variaveis necessarias
        reset();
//      cria uma lista que contera todos os ids presentes no ficheiro
        List<Integer> piecesIds = new ArrayList<>();

//      tenta ler o ficheiro
        try {

//          cria um scanner de for a poder ler o ficheiro
            Scanner scan = new Scanner(ficheiroInicial);

//          caso nao haja nenhuma linha, ou seja, o ficheiro ser vazio
            if (!scan.hasNextLine()) {

//              ativa uma excepcao que informara o utilizador do problema
                throw new InvalidSimulatorInputException(Thread.currentThread().getStackTrace()[1].getLineNumber(),
                        "Ficheiro está vazio!");

            }

/*
*           declara e inicializa variaveis necessarias para o decorrer do programa:
*
*           nPiecesMaxIndex -> ultima linha do ficheiro que contem informacao sobre as pecas
*           boardSizeMaxIndex -> ultima linha do ficheiro que contem informacao sobre o tabuleiro
*           nLines -> numero da linha do ficheiro que estamos a ver
*           yPosition -> posicao do eixo do y das pecas. Esta variavel e utilizada na leitura da informacao do tabueiro
*           piecesInfo -> array de string que contem a informacao da peca que se esta a ler do ficheiro
*           boardInfo -> array de string que contem a informacao da linha do tabuleiro que se esta a ler do ficheiro
*           saveInfo -> array de string que contem a informacao da gravacao do jogo anterior
* */
            int nPieces,
                nPieces_Counter = 0,
                nPiecesMaxIndex = 2,
                boardSizeMaxIndex = 0,
                nLines = 0,
                yPosition = 0;
            String[] piecesInfo;
            String[] boardInfo;
            String[] saveInfo;

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                System.out.println(line);

//              se estivermos na primeira linha
                if (nLines == 0) {

//                  tenta guardar na variavel boardSize em valor numerico o tamanho do tabuleiro
                    try {

                        boardSize = Integer.parseInt(line);

//                  caso a primeira linha do ficheiro nao seja um numero
                    }catch (ArithmeticException notAnInteger) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "Tem de colocar o tamanho do tabuleiro em numérico!");

                    }

//              se estivermos na segunda linhado ficheiro
                } else if (nLines == 1) {

//                  tenta guardar na variavel boardSize em valor numerico o numero de pecas existentes em jogo
                    try {

                        nPieces = Integer.parseInt(line);

//                  caso a primeira linha do ficheiro nao seja um numero
                    }catch (ArithmeticException notAnInteger) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "Tem de colocar o número de peças existêntes em jogo em numérico!");

                    }

//                  guarda na variavel nPiecesMaxIndex o numero da ultima linha do ficheiro relativa as pecas
                    nPiecesMaxIndex += nPieces;

//                  guarda na variavel boardSizeMaxIndex o numero da ultima linha do ficheiro relativa ao tabuleiro
                    boardSizeMaxIndex = boardSize + nPiecesMaxIndex;

//              se estivermos dentro da parte do ficheiro que contem a informacao de cada peca
                } else if (nLines < nPiecesMaxIndex) {

//                  tenta dividir a linha em questao por ":" e guarda cada parcela da mesma na variavel piecesInfo
                    try {

                        piecesInfo = line.split(":");

                    } catch (NumberFormatException missingFileLines) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "Falta o tamanho do tabuleiro ou o número de peças");

                    }

//                  se a variavel nao contiver 4 parcelas
                    if (piecesInfo.length > 4) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MAIS (Esperava: " + 4 + " ; Obtive: " + piecesInfo.length + " )");

                    } else if (piecesInfo.length < 4) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MENOS (Esperava: " + 4 + " ; Obtive: " + piecesInfo.length + " )");

                    }

//                  para a primeira e a segunda parcela que tem de ser numerico da variavel piecesInfo
                    for (int i = 0; i < 3; i++) {

//                      declara a variavel auxiliar
                        int aux;

//                      tenta guardar na variavel aux em valor numerico o valor da parcela i da linha em especifico
                        try {

                            aux = Integer.parseInt(piecesInfo[i]);

//                      caso a primeira linha do ficheiro nao seja um numero
                        } catch (Exception e) {

//                          ativa uma excepcao que informara o utilizador do problema
                            throw new InvalidSimulatorInputException(
                                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                    "As primeiras três parcelas da informação das peças têm de ser em numérico!");

                        }

//                      se for a primeira parcela da linha
                        if (i == 0) {

//                          percorre a lista de ids das pecas existentes no jogo
                            for (Integer thisPieceId : piecesIds) {

//                              se o id desta peça em especifico ja existir anteriormente ou se for 0
                                if (thisPieceId == aux || aux == 0) {

//                                  ativa uma excepcao que informara o utilizador do problema
                                    throw new InvalidSimulatorInputException(
                                            Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                            "Já existe uma peça com este ID!");

                                }

                            }

                        }

                    }

//                  adiciona o id desta peca a lista de ids
                    piecesIds.add(Integer.parseInt(piecesInfo[0]));

//                  cria um objeto do tipo CrazyPiece chamado piece
                    CrazyPiece piece;

//                  cria uma peca da equipa preta perante o seu tipo de peca
                    switch (Integer.parseInt(piecesInfo[2])) {

//                      equipa preta
                        case 10: {

                            switch (Integer.parseInt(piecesInfo[1])) {

//                              cria um rei
                                case 0: {

                                    piece = new ReiPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma rainha
                                case 1: {

                                    piece = new RainhaPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um ponei magico
                                case 2: {

                                    piece = new PoneiMagicoPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um padre da vila
                                case 3: {

                                    piece = new PadreDaVilaPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma torre horizontal
                                case 4: {

                                    piece = new TorreHPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma torre vertical
                                case 5: {

                                    piece = new TorreVPreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma lebre
                                case 6: {

                                    piece = new LebrePreta(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um joker
                                case 7: {

                                    piece = new JokerPreto(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              se o tipo da peca nao for nenhum dos anteriores
                                default: {

//                                  ativa uma excepcao que informara o utilizador do problema
                                    throw new InvalidSimulatorInputException(
                                            Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                            "Esse tipo de peça não existe!");

                                }

                            }

                        }
                        break;

//                      equipa branca
                        case 20: {

                            switch (Integer.parseInt(piecesInfo[1])) {

//                              cria um rei
                                case 0: {

                                    piece = new ReiBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma rainha
                                case 1: {

                                    piece = new RainhaBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um ponei magico
                                case 2: {

                                    piece = new PoneiMagicoBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um padre da vila
                                case 3: {

                                    piece = new PadreDaVilaBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma torre horizontal
                                case 4: {

                                    piece = new TorreHBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma torre vertical
                                case 5: {

                                    piece = new TorreVBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria uma lebre
                                case 6: {

                                    piece = new LebreBranca(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              cria um joker
                                case 7: {

                                    piece = new JokerBranco(Integer.parseInt(piecesInfo[0]), piecesInfo[3]);

                                }
                                break;

//                              se o tipo da peca nao for nenhum dos anteriores
                                default: {

//                                  ativa uma excepcao que informara o utilizador do problema
                                    throw new InvalidSimulatorInputException(
                                            Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                            "Esse tipo de peça não existe!");

                                }

                            }

                        }
                        break;

//                      se a equipa nao for nem branca (20) nem preta (10)
                        default: {

//                          ativa uma excepcao que informara o utilizador do problema
                            throw new InvalidSimulatorInputException(
                                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                    "Essa equipa não existe!");

                        }

                    }

//                  adiciona a peca a lista de pecas crazyPiecesInGame
                    crazyPiecesInGame.add(piece);

                    nPieces_Counter++;

//              se estivermos dentro da parte do ficheiro que contem a informacao do tabuleiro
                } else if (nLines < boardSizeMaxIndex) {

//                  tenta dividir a linha em questao por ":" e guarda cada parcela da mesma na variavel piecesInfo
                    try {

                        boardInfo = line.split(":");

                    } catch (NumberFormatException missingFileLines) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MENOS (Esperava: " + 4 + " ; Obtive: " + nPieces_Counter + " )");

                    }

//                  se a variavel nao contiver o mesmo numero de parcelas que o tamanho do tabuleiro
                    if (boardInfo.length > boardSize) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MAIS (Esperava: " + boardSize + " ; Obtive: " + boardInfo.length + " )");

                    } else if (boardInfo.length < boardSize) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MENOS (Esperava: " + boardSize + " ; Obtive: " + boardInfo.length + " )");

                    }

//                  percorre todas as parcelas da da linha
                    for (int i = 0; i < boardInfo.length; i++) {

//                      declara a variavel auxiliar
                        int aux;

//                      tenta guardar na variavel aux em valor numerico o valor da parcela i da linha em especifico
                        try {

                            aux = Integer.parseInt(boardInfo[i]);

//                      caso a primeira linha do ficheiro nao seja um numero
                        } catch (Exception e) {

//                          ativa uma excepcao que informara o utilizador do problema
                            throw new InvalidSimulatorInputException(
                                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                    "Tem de colocar o tamanho do tabuleiro em valor numérico!");

                        }

//                      se a variavel aux for diferente de 0
                        if (aux != 0) {

//                          percorre a lista de pecas em jogo
                            for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                              se o id da peca thisPiece for igual ao valor da variavel aux
                                if (thisPiece.getId() == aux) {

//                                  cria uma posicao sendo o valor de x = i e o valor do y = yPosition
                                    Position position = new Position(i, yPosition);
//                                  coloca a peca thisPiece na posicao position
                                    thisPiece.setPosition(position);
//                                  afirma que esta peca esta em jogo
                                    thisPiece.isInGame();
//                                  imprime a peca na consola
                                    System.out.println(thisPiece);
//                                  sai do ciclo
                                    break;

                                }

                            }

                        }

                    }

//                  passa para a proxima linha do tabuleiro
                    yPosition++;

//              se o numero de linhas - 1 for menor que o index que o valor da variavel boardSizeMaxIndex
                } else if (nLines - 1 < boardSizeMaxIndex) {

//                  tenta dividir a linha em questao por ":" e guarda cada parcela da mesma na variavel piecesInfo
                    try {

                        saveInfo = line.split(":");

                    } catch (NumberFormatException missingFileLines) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(), "Gravação corrumpida!");

                    }

//                  se a variavel nao contiver 7 parcelas
                    if (saveInfo.length > 7) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MAIS (Esperava: " + 7 + " ; Obtive: " + saveInfo.length + " )");

                    } else if (saveInfo.length < 7) {

//                      ativa uma excepcao que informara o utilizador do problema
                        throw new InvalidSimulatorInputException(
                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                "DADOS A MENOS (Esperava: " + 7 + " ; Obtive: " + saveInfo.length + " )");

                    }

//                  percorre todas as parcelas da da linha
                    for (int i = 0; i < saveInfo.length; i++) {

//                      declara a variavel auxiliar
                        int aux;

//                      tenta guardar na variavel aux em valor numerico o valor da parcela i da linha em especifico
                        try {

                            aux = Integer.parseInt(saveInfo[i]);

//                      caso a primeira linha do ficheiro nao seja um numero
                        } catch (Exception e) {

//                          ativa uma excepcao que informara o utilizador do problema
                            throw new InvalidSimulatorInputException(
                                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                    "Tem de introduzir valores numéricos!");

                        }

/*
*                       se o valor da primeira parcela for igual a zero
*                       E
*                       o valor da variavel aux for diferente de 10 E diferente de 20
* */
                        if (i == 0 && (aux != 10 && aux != 20)) {

//                          ativa uma excepcao que informara o utilizador do problema
                            throw new InvalidSimulatorInputException(
                                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                    "A primeira parcela tem de ser 10 ou 20, segundo a equipa que começa a jogar!");

                        }

                    }

/*
*                   declara e inicializa variaveis necessarias:
*
*                   shiftCount -> servira para alterar o turno
*                   jokerPieceType -> servira para descobrir que tipo de peca o joker e inicializado
* */
                    int shiftCount;
                    int jokerPieceType = 0;

/*
*                   altera os valor da simulador:
*
*                   altera a equipa a jogar
*                   altera o numero de:
*                       - jogadas validas que ambas as equipas fizeram
*                       - jogadas invalidas que ambas as equipas fizeram
*                       - numero de capturas que mabas as equipas fizeram
* */
                    shift.changeIdTeam(Integer.parseInt(saveInfo[0]));
                    numberOfValidPlaysByBlackTeam = Integer.parseInt(saveInfo[1]);
                    numberOfWhitePiecesCaptured = Integer.parseInt(saveInfo[2]);
                    numberOfInvalidPlaysByBlackTeam = Integer.parseInt(saveInfo[3]);
                    numberOfValidPlaysByWhiteTeam = Integer.parseInt(saveInfo[4]);
                    numberOfBlackPiecesCaptured = Integer.parseInt(saveInfo[5]);
                    numberOfInvalidPlaysByWhiteTeam = Integer.parseInt(saveInfo[6]);

/*
*                   altera o valor da variavel shiftCounter de forma a ficar com a soma do total de jogadas
*                   efectuadas por ambas as equipas
* */
                    shiftCount = numberOfValidPlaysByBlackTeam + numberOfValidPlaysByWhiteTeam;

//                  altera o valor do turno perante o valor da variavel shiftCount
                    shift.changeCount(shiftCount);

/*
*                   descobre qual o tipo do joker atravez de:
*
*                   numa variavel auxiliar i que comeca com o valor do shiftCount e e reduzido sempre 6 unidades
*                   (equivalente a completar um ciclo, ou seja, voltar a ter o valor de rainha).
*
*                   Quando o valor de i for menor que 0, entao, jokerPieceType fica com o valor do i anterior.
* */
                    for (int i = shiftCount; i >= 0; i -= 6) {

                        jokerPieceType = i;

                    }

//                  percorre todas as pecas existentes no jogo
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      se a peca thisPiece for um joker
                        if (thisPiece.getType() == 7) {

/*
*                           modifica o tipo de peca que este joker contem atraves de alteral o seu tipo para o valor da
*                           variavel jokerPieceType + 1
* */
                            thisPiece.changePieceType(jokerPieceType + 1);

                        }

                    }

//                  desincrementa o valor da variavel nLines
                    nLines = boardSizeMaxIndex - 1;

                }else {

//                  ativa uma excepcao que informara o utilizador do problema
                    throw new InvalidSimulatorInputException(Thread.currentThread().getStackTrace()[1].getLineNumber(),
                            "Existe informação a mais neste ficheiro!");

                }

//              incrementa o valor da variavel nLines
                nLines++;

            }

            List<Position> oneBarrierPiecePositions;
            Position piecePositionToCheck;

//          percorre todas as pecas no jogo
            for (CrazyPiece thisPiece : crazyPiecesInGame) {

//              se o tipo da peca thisPiece for uma rainha
                if (thisPiece.getType() == 1) {

//                  cria uma barreira a sua volta e guarda-as numa lista
                    oneBarrierPiecePositions = (thisPiece.getPosition().oneSquareBarrier(boardSize));

//                  percorre de novo as pecas em jogo
                    for (CrazyPiece crazyPieceToCheck : crazyPiecesInGame) {

//                      se a equipa da crazyPieceToCheck for diferente da equipa da thisPiece
                        if (crazyPieceToCheck.getIDTeam() != thisPiece.getIDTeam()) {

//                          se o tipo da peca crazyPieceToCheck for um padre da vila
                            if (crazyPieceToCheck.getType() == 3) {

//                              guarda a posicao desta peca
                                piecePositionToCheck = crazyPieceToCheck.getPosition();

//                              percorre todas as posicoes na lista
                                for (Position thisPosition : oneBarrierPiecePositions) {

//                                  se a posicao da peca thisPiece for igual a posicao piecePositionToCheck
                                    if (thisPosition.equals(piecePositionToCheck)) {

/*
*                                       lanca uma excepcao de forma a informar que hexiste um padre da vila
*                                       imediatamente ao lado de uma rainha
* */

                                        throw new InvalidSimulatorInputException(
                                                Thread.currentThread().getStackTrace()[1].getLineNumber(),
                                                "Padre da vila junto a uma rainha");

                                    }

                                }

                            }

                        }

                    }

                }

            }

//          se o numero de linhas do ficheiro (nLines) for igual ao index da suposta ultima linha do ficheiro
            if (nLines == boardSizeMaxIndex) {

//              guarda todas as pecas em jogo na variavel allCrazyPieces
                allCrazyPieces.addAll(crazyPiecesInGame);

            }

        } catch (FileNotFoundException e) {

            new InvalidSimulatorInputException(
                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                    ficheiroInicial.getName() + " not found!").printStackTrace();

        } catch (NumberFormatException notInt) {

            new InvalidSimulatorInputException(
                    Thread.currentThread().getStackTrace()[1].getLineNumber(),
                    "The file inputs are not valid!").printStackTrace();

        }

    }//**************************

//  termina o jogo
    public boolean jogoTerminado() {

        if (crazyPiecesInGame.size() == 0) {

            return true;

        } else if (shift.getCountNoCapture() == 10 && firstCapture) {

            shift.resetCount();
            shift.resetCountNoCapture();

            return true;

        } else {

            List<Integer> kingsAndPieces = checkNumberOfBlackKings_WhiteKings_PiecesInGame();

//          set nWhiteKing and nBlackKing as the values returned from the previous function
            int nBlackKing = kingsAndPieces.get(0);
            int nWhiteKing = kingsAndPieces.get(1);
            int piecesInGame = kingsAndPieces.get(2);

            if (piecesInGame <= 2) {

                if (nBlackKing == 1 && nWhiteKing == 1 || nBlackKing == 0 && nWhiteKing == 0) {

                    return true;

                }

            }

            if (nBlackKing == 0) {

                return true;

            } else if (nWhiteKing == 0) {

                return true;

            }

            return false;

        }

    }//******************************************************************************

//  Save Game
    public boolean gravarJogo(File ficheiroDestino) {

        boolean pieceFounded;

        String newLine = System.getProperty("line.separator");

        try {

            FileWriter writer = new FileWriter(ficheiroDestino);

            writer.write(String.valueOf(boardSize));
            writer.write(newLine);
            writer.write(String.valueOf(allCrazyPieces.size()));
            writer.write(newLine);

            for (CrazyPiece thisPiece : allCrazyPieces) {

                writer.write(thisPiece.saveFile_ToString());
                writer.write(newLine);

            }

            for (int line = 0 ; line < boardSize; line++) {

                for (int column = 0; column < boardSize; column++) {

                    pieceFounded = false;

                    Position thisPosition = new Position(column, line);

                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

                        if (thisPiece.getPosition().equals(thisPosition)) {

                            writer.write(String.valueOf(thisPiece.getId()));

                            pieceFounded = true;

                            break;

                        }

                    }

                    if (!pieceFounded) {

                        writer.write(String.valueOf(0));

                    }

                    if (column != boardSize - 1) {

                        writer.write(":");

                    }

                }

                writer.write(newLine);

            }

            writer.write(String.valueOf(shift.getIdTeam()));
            writer.write(setTeamStats(numberOfValidPlaysByBlackTeam, numberOfWhitePiecesCaptured, numberOfInvalidPlaysByBlackTeam));
            writer.write(setTeamStats(numberOfValidPlaysByWhiteTeam, numberOfBlackPiecesCaptured, numberOfInvalidPlaysByWhiteTeam));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }//*************************************************************

//  jogada
    public List<ValidPlay> obterSugestoesJogada(int xO, int yO) {

        List<Position> possiblesPositions;

        List<ValidPlay> validPlaysOrdered = new ArrayList<>();

        ValidPlay validPlay;

//      clear the list
        suggestedPlay.clear();

//      Create a new position with the destination
        Position origin = new Position(xO, yO);

//      For each piece on crazyPiecesInGame variable
        for (CrazyPiece thisPiece : crazyPiecesInGame) {

//          Check if the this piece is in the position created
            if (thisPiece.getPosition().equals(origin)) {

//              Check if this piece belongs to the team that is playing
                if (thisPiece.getIDTeam() == shift.getIdTeam()) {

//                  Create a list with all the positions possibles for the piece moved
                    possiblesPositions = thisPiece.possiblesPositions(boardSize, crazyPiecesInGame, shift);

                    for (Position thisPosition : possiblesPositions) {

                        for (CrazyPiece piece : crazyPiecesInGame) {

                            if (piece.getPosition().equals(thisPosition)) {

//                                Collections.sort();

                                validPlay = new ValidPlay(thisPosition.getxActual(), thisPosition.getyActual(),
                                        piece.getRelativeValue());

                                suggestedPlay.add(validPlay);

                            }

                        }

                    }

                    Collections.sort(suggestedPlay);

                    return validPlaysOrdered;

                }

            }

        }

//      return the list
        return validPlaysOrdered;

    }//*************************************************

    private void addEstatistic(String key, Map<String, Integer> hashMap) {

        if (!hashMap.containsKey(key)) {

            hashMap.put(key, 1);

        } else {

            hashMap.put(key, hashMap.get(key) + 1);

        }

    }

    public boolean processaJogada(int xO, int yO, int xD, int yD) {

        previousCountNoCapture = 0;

//      If the positionOrigin is in side the board
        if (xO >= 0 && xO < boardSize && yO >= 0 && yO < boardSize) {

//          Check if the move is between the board rang
            if (xD >= 0 && xD <= (boardSize - 1) && yD >= 0 && yD <= (boardSize - 1)) {

//              If the originPosition is different dorm the destinyPosition
                if (xO != xD || yO != yD) {

//                  Creates the positionOrigin as the origin position
                    Position origin = new Position(xO, yO);

                    Position destiny = new Position(xD, yD);

//                  For every piece in the list of pieces in the game
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      If the position of this piece is in the positionOrigin
                        if (thisPiece.getPosition().equals(origin)) {

//                          Check if it belongs to the playing team
                            if (thisPiece.getIDTeam() == shift.getIdTeam()) {

//                              return the value returned of the move method of this piece
                                String score = thisPiece.move(destiny, boardSize, crazyPiecesInGame, crazyPieceRemovedFromTheGameAux, shift);

                                if (score.equals("")) {

                                    addScoresStatsInvalid();

                                    addEstatistic(String.valueOf(thisPiece.getId()), invalids);

                                    return false;

                                } else {

                                    String[] values = score.split(",");

                                    addScoresStats(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]));

                                    if (Integer.parseInt(values[0]) != 0 || Integer.parseInt(values[1]) != 0) {

                                        firstCapture = true;

                                        hasCaughtAPiece++;

                                        if (shift.getCountNoCapture() == 0) {

                                            previousCountNoCapture = -1;

                                        }

                                        else {

                                            previousCountNoCapture = shift.getCountNoCapture();

                                        }

                                        shift.resetCountNoCapture();

                                        crazyPieceRemovedFromTheGame = crazyPieceRemovedFromTheGameAux.get(0);

                                        crazyPieceRemovedFromTheGameAux.remove(crazyPieceRemovedFromTheGame);

                                        positionErased = thisPiece.getPosition();

                                        addEstatistic(String.valueOf(thisPiece.getId()), captures);

                                        for (CrazyPiece crazyPiece_Eaten : crazyPiecesInGame) {

                                            if (crazyPieceRemovedFromTheGame.equals(crazyPiece_Eaten)) {

                                                addEstatistic(String.valueOf(thisPiece.getType()), captures);

                                                crazyPiece_Eaten.isOutOfGame();
                                                break;

                                            }

                                        }

                                    } else {

                                        shift.addCountNoCapture();

                                    }

                                    shift.addCount(crazyPiecesInGame);

                                    previousCrazyPiece = thisPiece;

                                    previousPosition = origin;

                                    hasMadeUndo = false;

                                    addEstatistic(String.valueOf(thisPiece.getId()), valid);

                                    return true;

                                }

                            }

//                          If the piece doesn't belong to the playing team leaves the cycle
                            break;

                        }

                    }

                }

            }

        }

//      Add an invalid attempt to the playing team
        addScoresStatsInvalid();

//      Returns false
        return false;

    }//***********************************************

    public void anularJogadaAnterior() {

//      If there was already a move
        if (shift.getCount() > 0) {

//          If there wasn't an undo made
            if (!hasMadeUndo) {

//              undo shiftChange
                shift.removeCount();

//              If there was a capture
                if (previousCountNoCapture != 0) {

                    if (previousCountNoCapture != -1) {

//                      Get the previous countNoCapture
                        shift.undoCountNoCapture(previousCountNoCapture);

                    }
//                  Remove 1 unit from the number pieces captured
                    hasCaughtAPiece--;

//                  If it was the first capture
                    if (hasCaughtAPiece == 0) {

//                      set the firstCapture as false
                        firstCapture = false;

                    }

//                  Search in ALL pieces
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      If thisPiece is the same piece that was moved
                        if (thisPiece.equals(previousCrazyPiece)) {

//                          Undo the movement that thisPiece as made
                            thisPiece.undoMov(previousPosition);

                        }

                    }

                    s = "EMPATE";

                    for (CrazyPiece crazyPiece_Eaten : crazyPiecesInGame) {

                        if (crazyPieceRemovedFromTheGame.equals(crazyPiece_Eaten)) {

                            crazyPiece_Eaten.isInGame();
                            crazyPiece_Eaten.setPosition(positionErased);
                            break;

                        }

                    }

                    crazyPieceRemovedFromTheGame.isInGame();

                    switch (shift.idTeam) {

                        case 10 : {

                            undoScoresStats(0, 1, 0, 1);

                        }
                        break;

                        case 20 : {

                            undoScoresStats(1, 0, 1, 0);

                        }

                    }

                    s = "EMPATE";

                }
//              If there was no capture
                else {

//                  undo remove one unit to countNoCapture
                    shift.removeCountNoCapture();

//                  Search in the pieces in game
                    for (CrazyPiece thisPiece : crazyPiecesInGame) {

//                      If thisPiece is the same piece that was moved
                        if (thisPiece.equals(previousCrazyPiece)) {

//                          Undo the movement that thisPiece as made
                            thisPiece.undoMov(previousPosition);

                        }

                    }

                    switch (shift.idTeam) {

                        case 10 : {

                            undoScoresStats(0, 0, 0, 1);

                        }
                        break;

                        case 20 : {

                            undoScoresStats(0, 0, 1, 0);

                        }

                    }

                }

                for (CrazyPiece thisPiece : crazyPiecesInGame) {

                    if (thisPiece.getType() == 7) {

                        thisPiece.undoPieceType();

                    }

                }

//              set hasMadeUndo as true
                hasMadeUndo = true;

            }

        }

    }//**************************************************************************

//  scores
    private void addScoresStats(int numberOfBlackPiecesCaptured, int numberOfWhitePiecesCaptured,
                                      int numberOfValidPlaysByWhiteTeam, int numberOfValidPlaysByBlackTeam) {

        this.numberOfBlackPiecesCaptured += numberOfBlackPiecesCaptured;
        this.numberOfWhitePiecesCaptured += numberOfWhitePiecesCaptured;
        this.numberOfValidPlaysByWhiteTeam += numberOfValidPlaysByWhiteTeam;
        this.numberOfValidPlaysByBlackTeam += numberOfValidPlaysByBlackTeam;

    }//*****

    private void undoScoresStats(int numberOfBlackPiecesCaptured, int numberOfWhitePiecesCaptured,
                                      int numberOfValidPlaysByWhiteTeam, int numberOfValidPlaysByBlackTeam) {

        this.numberOfBlackPiecesCaptured -= numberOfBlackPiecesCaptured;
        this.numberOfWhitePiecesCaptured -= numberOfWhitePiecesCaptured;
        this.numberOfValidPlaysByWhiteTeam -= numberOfValidPlaysByWhiteTeam;
        this.numberOfValidPlaysByBlackTeam -= numberOfValidPlaysByBlackTeam;

    }//*****

    private void addScoresStatsInvalid() {

        switch (shift.getIdTeam()) {

            case 10: {

                this.numberOfInvalidPlaysByBlackTeam++;

            }break;

            case 20: {

                this.numberOfInvalidPlaysByWhiteTeam++;

            }

        }

    }//************************************************************************

    private List<String> getValueToReturn(List<Map.Entry<String, Integer>> thisList, Map<String, Integer> thisMap) {

        List<String> valueToReturn = new ArrayList<>();

        thisList.stream()
                .forEach((i) ->
                        crazyPiecesInGame.stream()
                                .filter((thisPiece) -> thisPiece.getId() == Integer.parseInt(i.getKey()))
                                .forEach((thisPiece) ->
                                        valueToReturn.add(thisPiece.getIDTeam() + ":" + thisPiece.getName() + ":" +
                                                thisPiece.getNPoints() + ":" + thisMap.get(i.getKey()))
                                ));

        return valueToReturn;

    }

    private List<Map.Entry<String, Integer>> getTop_X(List<Map.Entry<String, Integer>> thisList, int x) {

        if (thisList.stream().count() < x) {

            return thisList;

        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        thisList.stream().limit(x).forEach(list::add);

        return list;

    }//*********

    private List<Map.Entry<String, Integer>> changeMap_To_List(Map<String, Integer> map) {

//      creates a list of map<String, Integer> by it entrance state and return it
        return new LinkedList<>(map.entrySet());

    }//************************

    private List<Map.Entry<String, Integer>> orderedList(List<Map.Entry<String, Integer>> list) {

//      sort the list by the value of every map in it
        list.sort(Comparator.comparing(Map.Entry::getValue));

        return list;

    }//*****************

    private Map<String, Integer> changeList_To_Map(List<Map.Entry<String, Integer>> list) {

//      creates a map<String, Integer> type variable
        Map<String, Integer> map = new LinkedHashMap<>();

        list.stream().forEach((i) ->
                map.put(i.getKey(), i.getValue()));

//      return the map it self
        return map;

    }//***********************

//  reinicia o jogo
    private void reset() {

        boardSize = 0;
        numberOfBlackPiecesCaptured = 0;
        numberOfWhitePiecesCaptured = 0;
        numberOfValidPlaysByBlackTeam = 0;
        numberOfValidPlaysByWhiteTeam = 0;
        numberOfInvalidPlaysByBlackTeam = 0;
        numberOfInvalidPlaysByWhiteTeam = 0;
        hasCaughtAPiece = 0;
        crazyPiecesInGame.clear();
        crazyPieceRemovedFromTheGameAux.clear();
        allCrazyPieces.clear();
        authors.clear();
        suggestedPlay.clear();
        scores.clear();
        shift.reset();
        firstCapture = false;
        hasMadeUndo = false;
        previousPosition = null;
        previousCrazyPiece = null;
        crazyPieceRemovedFromTheGame = null;
        previousCountNoCapture = -1;
        s = "EMPATE";


    }//****************************************************************************************

}