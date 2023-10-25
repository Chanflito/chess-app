package chess.utils;

import chess.board.Position;
import common.board.interfaces.Board;
import chess.piece.Piece;

import java.util.Map;

public class Finder {
    public static Integer getHighestIdFromCurrentBoard(Board board){
        Map<Position, Piece> pieces=board.getPieces();
        int highestId=0;
        for (Position position:pieces.keySet()){
            int id=Integer.parseInt(pieces.get(position).getId());
            if (id>highestId){
                highestId=id;
            }
        }
        return highestId;
    }

}
