package tictactoe.botPlyingStrategies;

import tictactoe.models.Board;
import tictactoe.models.Cell;

public interface BotPlayingStrategies {

    Cell makeMove(Board board);
}
