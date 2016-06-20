package content;

/**
 * @author Michele Franceschetti
 */

public abstract class Tool 
{
	private String type;

	/**
	 * @return the type
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public abstract String act();
}
