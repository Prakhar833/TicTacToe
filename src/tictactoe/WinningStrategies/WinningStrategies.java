package tictactoe.WinningStrategies;

import tictactoe.models.Board;
import tictactoe.models.Move;

public interface WinningStrategies {
    boolean CheckWinner(Board board , Move move);

    void undo(Board board, Move lastMove);
}
