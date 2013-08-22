package Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 21.08.13
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public interface BaseGameStrategy {
    Integer b = 100;
    Map<Integer, String> gamers = new HashMap<Integer, String>();
    Integer getRemainingMatches();
    boolean isRemainingMatches();
    boolean takeMatches(Integer matches) throws Exception;
    boolean isWinner();
    Integer nextGamer();
    Boolean RobotTurn() throws Exception;
}
