package tictactoe.controller;

import tictactoe.WinningStrategies.WinningStrategies;
import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.exceptions.PlayerCountMisMatxhException;
import tictactoe.models.Game;
import tictactoe.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension , List<Player> playerList , List<WinningStrategies> winningStrategiesList) throws DuplicateSymbolException, PlayerCountMisMatxhException, MoreThanOneBotException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(playerList)
                .setWinningStrategies(winningStrategiesList)
                .Build();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void maleMove(Game game){

        game.makeMove();
    }

    public void undo(Game game){
        game.undo();
    }

}
