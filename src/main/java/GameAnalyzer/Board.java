package GameAnalyzer;

import java.util.List;

/**
 * Represents a Game baord
 * @author Rohit_Vaidya
 *
 */
public interface Board {
  /**
   * Initialize(Create) the Game Board	
   * @param x
   * @param y
   * @return A grid of initialized game board
   */
  Object[][] initializeBoard(int x,int y);
  /**
   * Provided a List<Objects> create populate the game with a configuration
   * @param objects
   * @param position
   * @return
   */
  boolean populateBoard(List<Object> objects,Object position);
}
