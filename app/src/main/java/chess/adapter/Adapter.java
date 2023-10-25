package chess.adapter;

import chess.board.Position;
import common.board.interfaces.Board;
import edu.austral.dissis.chess.gui.BoardSize;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.enums.Color;
import common.game.interfaces.TurnHandler;
import chess.piece.Movement;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Adapter {
    public static List<ChessPiece> getCurrentPieces(Board board){
        List<ChessPiece> piecesInAdapter=new ArrayList<>();
        Map<Position, Piece> boardState=board.getPieces();
        boardState.forEach((position, piece) -> {
            ChessPiece chessPiece= new ChessPiece(piece.getId(),
                    convertPlayerColor(piece.getColor()),convertPosition(position),piece.getType().toString().toLowerCase());
            piecesInAdapter.add(chessPiece);
        });
        return piecesInAdapter;
    }

    public static BoardSize getBoardSize(Board board){
        return new BoardSize(board.getSizeOfRows(),board.getSizeOfColumns());
    }

    public static PlayerColor getCurrentTurn(TurnHandler turnHandler){
        return turnHandler.getCurrentTurn()== Color.WHITE? PlayerColor.WHITE:PlayerColor.BLACK;
    }

    public static PlayerColor getWinner(Color color){
        return color==Color.WHITE? PlayerColor.WHITE:PlayerColor.BLACK;
    }


    public static Movement convertMove(Move move){
        return new Movement (new Position(move.getFrom().getColumn()-1,move.getFrom().getRow()-1)
                ,new Position(move.getTo().getColumn()-1,move.getTo().getRow()-1));
    }

    private static PlayerColor convertPlayerColor(Color color){
        return color==Color.WHITE? PlayerColor.WHITE:PlayerColor.BLACK;
    }

    public static edu.austral.dissis.chess.gui.Position convertPosition(Position position){
        return new edu.austral.dissis.chess.gui.Position(position.y()+1, position.x()+1);
    }

//    public static edu.austral.dissis.chess.gui.Position convertPositionInCapaBlanca(Position position){
//        int filaMisChess = position.x(); // Fila en "mis chess"
//        int columnaMisChess = position.y(); // Columna en "mis chess"
//
//        // Realiza la conversión
//        int filaChess = 8 - filaMisChess;
//        int columnaChess = 10 - columnaMisChess;
//
//        // Devuelve la nueva posición en "chess"
//        return new edu.austral.dissis.chess.gui.Position(filaChess, columnaChess);
//    }
//
//    public static List<ChessPiece> getCurrentPiecesCapaBlanca(Board board){
//        List<ChessPiece> piecesInAdapter=new ArrayList<>();
//        Map<Position, Piece> boardState=board.getPieces();
//        boardState.forEach((position, piece) -> {
//            ChessPiece chessPiece= new ChessPiece(piece.getId(),
//                    convertPlayerColor(piece.getColor()),convertPositionInCapaBlanca(position),piece.getType().toString().toLowerCase());
//            piecesInAdapter.add(chessPiece);
//        });
//        return piecesInAdapter;
//    }
}