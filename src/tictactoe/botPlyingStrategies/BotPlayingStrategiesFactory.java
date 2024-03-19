package tictactoe.botPlyingStrategies;

import tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategiesFactory {
    public static BotPlayingStrategies getBotPlayinfStrategiesForDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {

        return new EasyBotPlayerStrategies();


    }
}
