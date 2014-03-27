package comp303.fivehundred.engine;
import java.util.Observable;
import java.util.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Andrei
 *
 */
public class GameLogger implements Observer
{
	private Logger aLogger;

	/**
	 * 
	 */
	public GameLogger()
	{
		System.setProperty("org.slf4j.simplelogger.defaultlog", "info");
		aLogger = LoggerFactory.getLogger(GameLogger.class);
	}

	@Override
	public void update(Observable pObservable, Object pArg)
	{
		aLogger.info(pArg.toString());
	}
}
