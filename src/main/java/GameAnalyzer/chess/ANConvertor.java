package GameAnalyzer.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.util.Pair;

/**
 * ANConvertor utility class converts given algebraic notation position 
 * in an 8x8 array 
 * @author Rohit_Vaidya
 */
 public final class ANConvertor {
	  static Logger logger = LoggerFactory.getLogger(ANConvertor.class.getName());
	  enum File{
	      A,B,C,D,E,F,G,H;
		  static int getOridinal(char ch){
		    for(File file: File.values()){
		       if(file.toString().equalsIgnoreCase(String.valueOf(ch)))
		    	   return file.ordinal();
		    }
			return -1;
		  }
	  }
	 /**
	  * Takes an algebraic notation and returns a Pair 
	  * @param an The chess algebraic notation
	  * @return Pair<Integer,Integer>
	  *  0,0           0,7 
	  * 8 . . . . . . . . 
	  * . . . . . . . . .
	  * . . . . . . . . .
	  * 3 . . . . . . . .
	  * 2 . . . . . . . .
	  * 1 . . . . . . . . 
	  *   A B C D E F G H
	  *  0,7           7,7
	  */
 	 public static Pair<Integer,Integer> getPosition(String an){
 		 String strippedNotation = null;
 		 if(an.length()>2){
 			 strippedNotation = an.substring(1,an.length());
 		 }
         else{
        	 strippedNotation = an;
         }
         Integer file = File.getOridinal(strippedNotation.charAt(0));
         Integer rank = Character.getNumericValue(strippedNotation.charAt(1));
         Pair<Integer,Integer> pair = new Pair<Integer,Integer>(8-rank,file);
         return pair;
     }
  }
 		
      
 
