import java.awt.*;

public class Sprite extends Rect
{

   int action = 0;

   boolean moving = false;

   boolean selected = false;

   Animation[] animation;

   public Sprite
   (
      int x,
      int y,
      int w,
      int h,
      String name,
      String[] pose,
      int duration,
      int images
   )
   {
      super(x, y, w, h);

      animation = new Animation[pose.length];

      for(int i = 0; i < animation.length; i++)

         animation[i] = new Animation(name + "_" + pose[i] + "_", duration, images);
   }


   public void draw(Graphics g)
   {
      if (moving)
         g.drawImage(animation[action].getNextImage(), x - Camera.x, y - Camera.y, null);
      else
         g.drawImage(animation[action].getStillImage(), x - Camera.x, y - Camera.y, null);


      if(selected)
      {
         g.drawLine(x - Camera.x, y, x+w - Camera.x, y);
         g.drawLine(x - Camera.x, y-1, x+w - Camera.x, y-1);
         g.drawLine(x - Camera.x, y-2, x+w - Camera.x, y-2);
      }
      moving = false;
   }



   public void moveLeftBy(int dx)
   {
      x -= dx;

      action = 0;

      moving = true;
   }

   public void moveRightBy(int dx)
   {
      x+= dx;

      action = 1;

      moving = true;
   }

   public void moveUpBy(int dy)
   {
      y -= dy;

      action = 3;

      moving = true;
   }

   public void moveDownBy(int dy)
   {
      y += dy;

      action = 2;

      moving = true;
   }

   public void moveBy(int dx, int dy)
   {
      x += dx;
      y += dy;
   }

}