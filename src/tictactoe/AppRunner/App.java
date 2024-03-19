package tictactoe.AppRunner;

import tictactoe.WinningStrategies.ColWinningStrategies;
import tictactoe.WinningStrategies.DiagonalWinningStrategies;
import tictactoe.WinningStrategies.RowWinningStrategies;
import tictactoe.WinningStrategies.WinningStrategies;
import tictactoe.controller.GameController;
import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.exceptions.PlayerCountMisMatxhException;
import tictactoe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws DuplicateSymbolException, PlayerCountMisMatxhException, MoreThanOneBotException {
        GameController gameController = new GameController();

         Scanner scanner = new Scanner(System.in);
        

        int dimension = 3;
        List<Player> playerList = new ArrayList<>();
        List<WinningStrategies> winningStrategiesList = new ArrayList<>();
        playerList.add(new Player('X' , "Prakhar" ,1, PlayerType.HUMAN));
        playerList.add(new Bot('0' , "Robo" ,2, PlayerType.BOT , BotDifficultyLevel.EASY));

        winningStrategiesList.add(new RowWinningStrategies());
        winningStrategiesList.add(new ColWinningStrategies());
        winningStrategiesList.add(new DiagonalWinningStrategies());

        Game game = gameController.startGame(dimension , playerList , winningStrategiesList);

        while (game.getGameState().equals(GameState.IN_PROG)){

            game.printBoard();
            System.out.println("Does anyone wants to undo? y/n");

            String undo = scanner.next();

            if(undo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }

            gameController.maleMove(game);
        }

        if(GameState.SUCCESS.equals(game.getGameState())){
            System.out.println(game.getWinner().getName()+" , congrats! You won the Game :");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("Match Ties :| ");
        }
         
    }
}
