package comp303.fivehundred.ai;

/**
 * Represents a bug related to the misuse of an AI class.
 */
@SuppressWarnings("serial")
public class AIException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param pMessage The error message to pass on.
	 * @param pException The Exception thrown.
	 */
	public AIException(String pMessage, Throwable pException)
	{
		super(pMessage, pException);
	}

	/**
	 * Constructor.
	 * @param pMessage The error message to pass on.
	 */
	public AIException(String pMessage)
	{
		super(pMessage);
	}

	/**
	 * Constructor.
	 * @param pException The exception thrown.
	 */
	public AIException(Throwable pException)
	{
		super(pException);
	}

}
