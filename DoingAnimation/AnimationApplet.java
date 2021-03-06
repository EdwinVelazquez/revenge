import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public abstract class AnimationApplet extends Applet
   implements Runnable, KeyListener, MouseListener
{

   Image    offscreen;
   Graphics offscreen_g;

   Thread t;



   boolean lt_pressed = false;
   boolean rt_pressed = false;
   boolean dn_pressed = false;
   boolean up_pressed = false;


   boolean r_pressed = false;




   boolean q_pressed  = false;
   boolean p_pressed  = false;


   // Abreviations for Virtual Keys

   public static final int UP = KeyEvent.VK_UP;
   public static final int DN = KeyEvent.VK_DOWN;
   public static final int LT = KeyEvent.VK_LEFT;
   public static final int RT = KeyEvent.VK_RIGHT;

   public static final int _P = KeyEvent.VK_P;
   public static final int _Q = KeyEvent.VK_Q;
   public static final int _R = KeyEvent.VK_R;


   public static final int _0 = KeyEvent.VK_0;
   public static final int _1 = KeyEvent.VK_1;
   public static final int _2 = KeyEvent.VK_2;
   public static final int _3 = KeyEvent.VK_3;


   public abstract void initialize();

   public final void init()
   {
       offscreen   = createImage(getWidth(), getHeight());
       offscreen_g = offscreen.getGraphics();


       addKeyListener(this);
       addMouseListener(this);

       requestFocus();


       initialize();


       t = new Thread(this);

       t.start();
   }


   public abstract void moveObjects();


   public final void run()
   {
      while(!q_pressed)
      {
         try
         {
            t.sleep(20);
         }
         catch(Exception x){}


         moveObjects();


         repaint();
      }
   }


   public void keyPressed(int code) {}

   public void keyReleased(int code) {}



   public final void keyPressed(KeyEvent e)
   {
      int code = e.getKeyCode();

      if(code == _Q)  q_pressed = true;
      if(code == _P)  p_pressed = true;

      if(code == _R)  r_pressed = true;

      if (code == UP)  up_pressed = true;
      if (code == DN)  dn_pressed = true;
      if (code == LT)  lt_pressed = true;
      if (code == RT)  rt_pressed = true;

      keyPressed(code);
   }

   public final void keyReleased(KeyEvent e)
   {
      int code = e.getKeyCode();

      if (code == UP)  up_pressed = false;
      if (code == DN)  dn_pressed = false;
      if (code == LT)  lt_pressed = false;
      if (code == RT)  rt_pressed = false;

      keyReleased(code);
   }

   public final void keyTyped(KeyEvent e)
   {

   }
/*
   public void repaint()
   {
      update(getGraphics());
   }
*/

   public final void update(Graphics g)
   {
      offscreen_g.clearRect(0, 0, getWidth(), getHeight());

      paint(offscreen_g);

      g.drawImage(offscreen, 0, 0, null);
   }



   public void mouseEntered(MouseEvent e)
   {
   }

   public void mouseExited(MouseEvent e)
   {
   }

   public void mouseClicked(MouseEvent e)
   {
   }

   public void mouseReleased(MouseEvent e)
   {
   }

   public void mousePressed(MouseEvent e)
   {
      repaint();
   }





}