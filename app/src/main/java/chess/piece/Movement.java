package chess.piece;

import chess.board.Position;

public class Movement {
    private final Position from;
    private final Position to;

    public Movement(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
