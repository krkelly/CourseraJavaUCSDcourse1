package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a visual marker for Colorado state parks on an earthquake map
 * 
 * @author Kevin Kelly
 * 
 */
public class ParkMarker extends CommonMarker {
        
        public static int TRI_SIZE = 5;  // The size of the triangle marker
        
        public ParkMarker(Location location) {
                super(location);
        }
        
        
        public ParkMarker(Feature city) {
                super(((PointFeature)city).getLocation(), city.getProperties());
                // Parks have properties: "name" (park name)
                // and "primitive campsites" (number of primitive campsites)
        }
        
        
        // pg is the graphics object on which you call the graphics
        // methods.  e.g. pg.fill(255, 0, 0) will set the color to red
        // x and y are the center of the object to draw. 
        // They will be used to calculate the coordinates to pass
        // into any shape drawing methods.  
        // e.g. pg.rect(x, y, 10, 10) will draw a 10x10 square
        // whose upper left corner is at position x, y
        /**
         * Implementation of method to draw marker on the map.
         */
        public void drawMarker(PGraphics pg, float x, float y) {
                //System.out.println("Drawing a city");
                // Save previous drawing style
                pg.pushStyle();
                
                // IMPLEMENT: drawing triangle for each city
                pg.fill(52, 186, 48);
                pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
                
                // Restore previous drawing style
                pg.popStyle();
        }
        
        /** Show the title of the city if this marker is selected */
        public void showTitle(PGraphics pg, float x, float y)
        {
                String name = "Colorado State Park: " + getPark();
                String primSites = "Primitive camping: " + getCampsites();
                
                pg.pushStyle();
                
                pg.fill(255, 255, 255);
                pg.textSize(12);
                pg.rectMode(PConstants.CORNER);
                pg.rect(x, y-TRI_SIZE-39, Math.max(pg.textWidth(name), pg.textWidth(primSites)) + 6, 39);
                pg.fill(0, 0, 0);
                pg.textAlign(PConstants.LEFT, PConstants.TOP);
                pg.text(name, x+3, y-TRI_SIZE-33);
                pg.text(primSites, x+3, y - TRI_SIZE -18);
                
                pg.popStyle();
        }
        
        private String getPark()
        {
                return getStringProperty("name");
        }
       
        
        private int getCampsites()
        {
                return Integer.parseInt(getStringProperty("primitive camping"));
        }
}
