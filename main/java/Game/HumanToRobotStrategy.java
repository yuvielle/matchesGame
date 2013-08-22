package Game;
import GameConfig.GameConfig;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 21.08.13
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */

public class HumanToRobotStrategy implements BaseGameStrategy {

    private Integer n=b;
    private Integer winner;
    private Integer currentGamer;

    public HumanToRobotStrategy(){
        gamers.put(1, "human");
        gamers.put(2, "computer");
        currentGamer = 1;
    }

    @Override
    public Integer getRemainingMatches() {
        return b - n;
    }

    @Override
    public boolean isRemainingMatches() {
        if(b > n){return true;}
        return false;
    }

    @Override
    public boolean takeMatches(Integer matches) throws Exception {
        if(matches >10 || matches < 1){
            throw new Exception(GameConfig.errorMessages.get("countError"));
        }
        if(n <= matches){
            throw new Exception(GameConfig.errorMessages.get("remainedError"));
        }
        n = n - matches;
        if (n == 0){
            winner = currentGamer;
            return true;
        }
        else{
            this.nextGamer();
        }
        return false;
    }

    @Override
    public boolean isWinner() {
        if(currentGamer.equals(winner)){
            return true;
        }
        return false;
    }

    @Override
    public Integer nextGamer(){
       if(currentGamer == gamers.size()){
           currentGamer = 1;
       }
       else{
           currentGamer++;
       }
        return currentGamer;
    }

    @Override
    public Boolean RobotTurn() throws Exception {
        if(!isRemainingMatches()){
            throw new Exception("no matches for this turn");
        }
        else if(getRemainingMatches() <= 10){
            this.takeMatches(getRemainingMatches());
            return true;
        }
        else{
            Random r = new Random();
            Integer m = Math.abs(r.nextInt(9)+1);
            this.takeMatches(m);
            return false;
        }
    }
}
