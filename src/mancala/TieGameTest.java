package mancala;

/**
 * @author Papa B., Nisha Bhatta, 
 * 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TieGameTest extends TieGame {
	//	TieGame t = new TieGame();
	int [] testBoard = {1,2,3,4,5};

	
	@Test
	public void originStatetest() {
		boolean a = tieGame(testBoard);
		if (a == true) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}

		fail("Not yet implemented"); 
	}
	
	//TODO
	
}