package tictactoe.models;

import tictactoe.botPlyingStrategies.BotPlayingStrategies;
import tictactoe.botPlyingStrategies.BotPlayingStrategiesFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategies botPlayingStrategies;
    public Bot(char symbol, String name, int id, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(symbol, name, id, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategies =
                BotPlayingStrategiesFactory.getBotPlayinfStrategiesForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Cell makeMove(Board board){
        System.out.println("Get ready for BOT's move :");
        Cell cell = botPlayingStrategies.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);

        return cell;
    }
}
