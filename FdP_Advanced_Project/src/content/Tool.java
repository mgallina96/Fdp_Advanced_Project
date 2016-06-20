package content;

import userInterface.graphic3DHandler.Transform;

/**
 * @author Michele Franceschetti
 */

public abstract class Tool 
{
	private String type;
	
	private Transform parent = new Transform();

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
	
	/**
	 * @return The parent transform.
	 */
	public Transform getParent() 
	{
		return parent;
	}

	/**
	 * @param parent The parent transform to set.
	 */
	public void setParent(Transform parent) 
	{
		this.parent = parent;
	}

	public abstract String act();
}
