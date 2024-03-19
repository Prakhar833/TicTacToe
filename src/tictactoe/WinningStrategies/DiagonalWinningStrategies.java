package tictactoe.WinningStrategies;

import tictactoe.models.Board;
import tictactoe.models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategies implements WinningStrategies{

    Map<Character , Integer> leftDiagnogalMap = new HashMap<>();
    Map<Character , Integer> rightDiagnogalMap = new HashMap<>();

    @Override
    public boolean CheckWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        char symbol = move.getPlayer().getSymbol();

        if(row == col){
            if(!leftDiagnogalMap.containsKey(symbol)){
                leftDiagnogalMap.put(symbol , 0);
            }
            leftDiagnogalMap.put(symbol , leftDiagnogalMap.get(symbol)+1);


            if(board.getDimensions()==leftDiagnogalMap.get(symbol)){
                return  true;
            }
        }

        if((row+col)==(board.getDimensions()-1)){
            if(!rightDiagnogalMap.containsKey(symbol)){
                rightDiagnogalMap.put(symbol , 0);
            }
            rightDiagnogalMap.put(symbol , rightDiagnogalMap.get(symbol)+1);

            if(board.getDimensions()==rightDiagnogalMap.get(symbol)){
                return true;
            }
        }



        return false;
    }

    @Override
    public void undo(Board board, Move lastMove) {

        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        char symbol = lastMove.getPlayer().getSymbol();

        if(row == col){
            leftDiagnogalMap.put(symbol , leftDiagnogalMap.get(symbol)-1);
        }
        if((row + col) == board.getDimensions()-1){
            rightDiagnogalMap.put(symbol , rightDiagnogalMap.get(symbol)-1);
        }
    }

}
