package common.board.interfaces;


public interface BoardBuilder {
    BoardBuilder boardSize(int rows, int columns);

    Board build();

}
