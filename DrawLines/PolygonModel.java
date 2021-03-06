import java.awt.*;

public abstract class PolygonModel
{
   int x;
   int y;

   int angle;

   int[] A;

   Color[] color = getColor();

   double cosA;
   double sinA;


   final int z_clip = 100;


   int[][] xStruct = getxStruct();

   int[][] yStruct = getyStruct();


   public PolygonModel(int x, int y, int angle)
   {
      this.x     = x;
      this.y     = y;
      this.angle = angle;

      cosA = Lookup.cos[angle];
      sinA = Lookup.sin[angle];

      A = new int[xStruct.length];

      this.color = color;
   }


   public abstract int[][] getxStruct();
   public abstract int[][] getyStruct();
   public abstract Color[] getColor();

   public void rotateBy(int degrees)
   {
      angle += degrees;

      if (angle >= 360)  angle -= 360;
      if (angle <    0)  angle += 360;

      cosA = Lookup.cos[angle];
      sinA = Lookup.sin[angle];
   }

   public void moveForwardBy(int dist)
   {
      moveBy((int)(dist*cosA), (int)(dist*sinA));
   }

   public void moveBy(int dx, int dy)
   {
      x += dx;

      y += dy;
   }


   public void draw(Graphics g)
   {

      int[] xpoints = new int[xStruct[0].length];
      int[] ypoints = new int[yStruct[0].length];

      for(int poly = 0; poly < xStruct.length; poly++)
      {
         cosA = Lookup.cos[angle + A[poly]];
         sinA = Lookup.sin[angle + A[poly]];

         for(int vert = 0; vert < xStruct[poly].length;  vert++)
         {
            xpoints[vert] = (int)(xStruct[poly][vert] * cosA - yStruct[poly][vert] * sinA) + x;
            ypoints[vert] = (int)(xStruct[poly][vert] * sinA + yStruct[poly][vert] * cosA) + y;
         }

         g.drawPolygon(xpoints, ypoints, xpoints.length);
      }
   }

 /*

   public void draw3D(Graphics g)
   {
      int d = 400;
      int x_origin = 512;
      int y_origin = 450;

      int[] xpoints = new int[xStruct[0].length];
      int[] ypoints = new int[yStruct[0].length];

      for(int poly = 0; poly < xStruct.length; poly++)
      {
         cosA = Lookup.cos[angle + A[poly]];
         sinA = Lookup.sin[angle + A[poly]];


         // World Coorrdinates
         int wx0 = xStruct[poly][0];
         int wy0 = 0;
         int wz0 = yStruct[poly][0] ;


         // World to View
         int vx0 =  wx0 - Camera.x;
         int vy0 =  wy0 - Camera.y;
         int vz0 =  wz0 - Camera.z;


         // Rotate about y-axis
         int rx0 = (int)(vx0 * Camera.cosA - vz0 * Camera.sinA);
         int ry0 = vy0;
         int rz0 = (int)(vx0 * Camera.sinA + vz0 * Camera.cosA);


         // For Clipping
         int cx0 = rx0;
         int cy0 = ry0;
         int cz0 = rz0;



         for(int vert = 1; vert < xStruct[poly].length;  vert++)
         {
       //     xpoints[vert] = (int)(xStruct[poly][vert] * cosA - yStruct[poly][vert] * sinA) + x;
       //     ypoints[vert] = (int)(xStruct[poly][vert] * sinA + yStruct[poly][vert] * cosA) + y;

            // World Coorrdinates
            int wx = xStruct[poly][vert];
            int wy = 0;
            int wz = yStruct[poly][vert] ;

            // World to View
            int vx =  wx - Camera.x;
            int vy =  wy - Camera.y;
            int vz =  wz - Camera.z;

            // Rotate about y-axis
            int rx = (int)(vx * Camera.cosA - vz * Camera.sinA);
            int ry = vy;
            int rz = (int)(vx * Camera.sinA + vz * Camera.cosA);

            // Perform Clipping
            int cx = rx;
            int cy = ry;
            int cz = rz;

            if((cz < z_clip) && (cz0 >= z_clip))
            {
               cz = z_clip;

               int dx = cx - cx0;

               int dz = cz - cz0;

               cx = dx * (z_clip - cz) / dz;
            }
            if((cz >= z_clip) && (cz0 < z_clip))
            {
            {
               cz0 = z_clip;

               int dx = cx - cx0;

               int dz = cz - cz0;

               cx0 = dx * (z_clip - cz0) / dz;
            }

          //  if()
            {

               // 3D Perspective Transformation on View Coordinates
               int sx =  d * cx / cz;
               int sy =  d * cy / cz;


               // Shift Origin on screen
               xpoints[vert] =  sx + x_origin;
               ypoints[vert] =  sy + y_origin;

            }


            cx0 = rx;
            cy0 = ry;
            cz0 = rz;

         }

         g.setColor(color[poly]);
         g.fillPolygon(xpoints, ypoints, xStruct[poly].length);

         g.setColor(Color.black);
         g.drawPolygon(xpoints, ypoints, xStruct[poly].length);
      }
*/




}



