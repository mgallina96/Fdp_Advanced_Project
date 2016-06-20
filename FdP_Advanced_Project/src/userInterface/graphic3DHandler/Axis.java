package userInterface.graphic3DHandler;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * @author Michele Franceschetti
 */

public class Axis 
{
	private static final double AXES_RADIUS = 1;

	private static final double AXES_LENGTH = 250;

	private Transform parent = new Transform();
	
	/**
	 * @return The parent Transform of the axis.
	 */
	public Transform getParent() 
	{
		return parent;
	}

	/**
	 * @param parent The parent Transform of the axis to set
	 */
	public void setParent(Transform parent) 
	{
		this.parent = parent;
	}

	/**
	 * The constructor.
	 * 
	 * @param _parent The parent Transform of the axis.
	 */
	public Axis(Transform _parent) 
    {
		this.parent = _parent;
		buildAxis();
    }
	
	/**
	 * Creates the hierarchy of the axis and builds the meshes.
	 */
	public void buildAxis()
	{
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.RED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.GREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.BLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXES_LENGTH, AXES_RADIUS, AXES_RADIUS);
        final Box yAxis = new Box(AXES_RADIUS, AXES_LENGTH, AXES_RADIUS);
        final Box zAxis = new Box(AXES_RADIUS, AXES_RADIUS, AXES_LENGTH);
        
        final Box xAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box yAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);
        final Box zAxisEnd = new Box(AXES_RADIUS * 2, AXES_RADIUS * 2, AXES_RADIUS * 2);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        xAxisEnd.setMaterial(redMaterial);
        yAxisEnd.setMaterial(greenMaterial);
        zAxisEnd.setMaterial(blueMaterial);
        
        xAxisEnd.setTranslateX(-AXES_LENGTH / 2);
        yAxisEnd.setTranslateY(-AXES_LENGTH / 2);
        zAxisEnd.setTranslateZ(-AXES_LENGTH / 2);
        
        parent.getChildren().addAll(xAxis, yAxis, zAxis, xAxisEnd, yAxisEnd, zAxisEnd);
        parent.setVisible(true);
    }
	
	/**
	 * Turns on/off the visibility of the axis.
	 * @param visible If the axis are visible.
	 */
	public void setVisibility(boolean visible)
	{
		parent.setVisible(true);
	}
}
