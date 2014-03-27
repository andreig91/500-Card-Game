package comp303.fivehundred.util;
import comp303.fivehundred.ai.BasicRobot;
import comp303.fivehundred.ai.RandomRobot;
import comp303.fivehundred.engine.GameEngine;
import comp303.fivehundred.model.*;
import comp303.fivehundred.util.Card.Suit;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 * @author Andrei
 */
public class TestPlayer
{
	private Player ra = new Player(new RandomRobot(), "ra");
	private Player rb = new Player(new RandomRobot(), "rb");
	private Player rc = new Player(new RandomRobot(), "rc");
	private Player rd = new Player(new RandomRobot(), "rd");
	private Player ba = new Player(new BasicRobot(), "ba");
	private Player bb = new Player(new BasicRobot(), "bb");
	private Player bc = new Player(new BasicRobot(), "bc");
	private Player bd = new Player(new BasicRobot(), "bd");
	private Player h = new Player(null, "h");
	
	@Test
	public void TestGetName()
	{
		assertEquals("ra", ra.getName());
		assertEquals("rb", rb.getName());
		assertEquals("rc", rc.getName());
		assertEquals("rd", rd.getName());
		assertEquals("ba", ba.getName());
		assertEquals("h", h.getName());
	}
	
	@Test
	public void TestIsHuman()
	{
		assertFalse(ra.isHuman());
		assertTrue(h.isHuman());
	}
}
