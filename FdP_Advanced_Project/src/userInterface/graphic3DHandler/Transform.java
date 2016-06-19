/*
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package userInterface.graphic3DHandler;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * @author Michele Franceschetti
 */

public class Transform extends Group
{
	public static final double RAD_TO_DEG = 180.0 / Math.PI;
	
	public Translate position  = new Translate(0,0,0); 
	public Rotation rotation = new Rotation(); 
    public Scale scale = new Scale();
    
    public Transform() 
    { 
        super(); 
        getTransforms().addAll(position, rotation.x, rotation.y, rotation.z, scale); 
        reset();
    }

    public void setPosition(double x, double y, double z) 
    {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
    }

    public void setPosition(double x, double y)
    {
    	position.setX(x);
    	position.setY(y);
    }

    public void setRotation(double _x, double _y, double _z)
    {
    	rotation.setRotation(_x, _y, _z);
    }
    
    public void setScale(double scaleFactor)
    {
        scale.setX(scaleFactor);
        scale.setY(scaleFactor);
        scale.setZ(scaleFactor);
    }

    public void setScale(double x, double y, double z) 
    {
        scale.setX(x);
        scale.setY(y);
        scale.setZ(z);
    }

    public void reset() 
    {
        position.setX(0.0);
        position.setY(0.0);
        position.setZ(0.0);
        
        rotation.setX(0);
        rotation.setY(0);
        rotation.setZ(0);
        
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
    }

    public void resetTSP() 
    {
    	position.setX(0.0);
    	position.setY(0.0);
    	position.setZ(0.0);
    	
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
    }

    /**
     * Returns the distance between two positions.
     * 
     * @param a first position
     * @param b second position
     * @return the distance between a and b
     */
    public static double distance(Translate a, Translate b)
    {
    	double distance = 0;
    	
    	distance = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + 
    							(a.getY() - b.getY()) * (a.getY() - b.getY()) + 
    							(a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
    	
    	return distance;
    }
    
	/** 
	 * This method changes the rotation axes to make the x-axis looking at the target position. 
	 *  
	 * @param target the target position 
	 * @author Manuel Gallina 
	 */ 
	public void lookAt(Translate target) 
	{ 
		double xp = this.position.getX();	//X axis position. 
		double yp = this.position.getY();	//Y axis position. 
		double zp = this.position.getZ();	//Z axis position. 
	
		double xt = target.getX();	//X axis target position. 
		double yt = target.getY();	//Y axis target position. 
		double zt = target.getZ();	//Z axis target position. 
	 	 
	 	Point3D xAxisDirection = new Point3D(xp + (2 * zp - zt)/(2 * yp - yt), yp + (2 * zp -zt)/(2 * xp - xt), zt - (2 * yp - yt)/(2 * xp - xt) - (2 * xp -xt)/(2 * yp - yt)).normalize(); 
	 	Point3D yAxisDirection = new Point3D(xt - xp, yt - yp, zt - zp).normalize(); 
	 	//Point3D zAxisDirection = new Point3D(xp - 1/(xp - xt), yp + 1/(yp - yt), zp); 
	 	 
	 	this.rotation.x.setAxis(xAxisDirection); 
	 	this.rotation.y.setAxis(yAxisDirection); 
	 	//this.rotation.z.setAxis(zAxisDirection);
		
		double angle = Math.asin((target.getY() - position.getY()) / distance(this.position, target)) * RAD_TO_DEG;
		
		if(target.getX() < position.getX())
			angle = 180.0 - angle;
		
	 	rotation.setZ(angle);
	} 

    @Override 
    public String toString() 
    {
        return "Transform [t = (" +
        				   position.getX() + ", " +
        				   position.getY() + ", " +
        				   position.getZ() + ")  " +
        				   rotation.toString() +
                           "s = (" +
                           scale.getX() + ", " + 
                           scale.getY() + ", " + 
                           scale.getZ() + ") ]";
    }
}
