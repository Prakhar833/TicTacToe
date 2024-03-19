package tictactoe.models;

import tictactoe.WinningStrategies.WinningStrategies;
import tictactoe.exceptions.DuplicateSymbolException;
import tictactoe.exceptions.MoreThanOneBotException;
import tictactoe.exceptions.PlayerCountMisMatxhException;

import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;

    private List<WinningStrategies> winningStrategies;

    private Game(int dimension,List<Player> players, List<WinningStrategies> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROG;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = players.get(nextPlayerIndex);
        Cell cell =  player.makeMove(board);
        Move move = new Move(cell , player);
        moves.add(move);

        if(chackWinner(move , board)){
            gameState = GameState.SUCCESS;
            winner = player;
            return;

        }

        if(moves.size()==board.getDimensions()*board.getDimensions()){
            gameState = GameState.DRAW;
            return;
        }

        nextPlayerIndex++;
        nextPlayerIndex= nextPlayerIndex % players.size();
    }

    private boolean chackWinner(Move move, Board board) {
        for(WinningStrategies winningStrategies1 : winningStrategies){
            if(winningStrategies1.CheckWinner(board,move)){
                return  true;
            }
        }
        return false;
    }

    public void undo() {
        if(moves.size()==0){
            System.out.println("No moves to undo");
            return;
        }

        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        for(WinningStrategies winningStrategies1 : winningStrategies){
            winningStrategies1.undo(board , lastMove);
        }

        if(nextPlayerIndex!=0){
            nextPlayerIndex--;
        }else {
            nextPlayerIndex = players.size()-1;
        }

    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Game Build() throws MoreThanOneBotException, DuplicateSymbolException, PlayerCountMisMatxhException {

            ValidateBotCount();
            ValidateUniqueSymbolsForPlayers();
            ValodateDimensionAndPlayerCount();
            return new Game(dimension, players , winningStrategies);
        }

        private void ValodateDimensionAndPlayerCount() throws PlayerCountMisMatxhException {
            if(players.size()!=dimension-1){
                throw new PlayerCountMisMatxhException();
            }
        }

        private void ValidateUniqueSymbolsForPlayers() throws DuplicateSymbolException {

            Set<Character> symbols = new HashSet<>();

            for(Player player : players){
                if(symbols.contains(player.getSymbol())){
                    throw new DuplicateSymbolException();
                }else {
                    symbols.add(player.getSymbol());
                }
            }
        }

        private void ValidateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }

            if(botCount>1){
                throw new MoreThanOneBotException();
            }
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
}
