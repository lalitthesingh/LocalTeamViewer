import java.net.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
class TeamViewerClient extends JFrame
{
	String ip;
	JLabel l;
	public static void main(String a[])
	{
		new TeamViewerClient("");
	}
	TeamViewerClient(String ip)
	{
	   super(ip);
	   setAlwaysOnTop(true);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   addMouseMotionListener(new MouseMotionAdapter(){
	      public void mouseMoved(MouseEvent e)
 	      {
		int x=e.getX();
		int y=e.getY();
		try{
		Socket client=new Socket(ip,6064);
		OutputStream out=client.getOutputStream();
		out.write((x+"#"+y).getBytes());	
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	      }
	  });
	  addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent e)
  		{
		try{
		  Socket client=new Socket(ip,6066);
		  OutputStream out=client.getOutputStream();
		  int b=e.getButton();
		  int cc=e.getClickCount();
		  if(cc==1)
		    out.write((b+"").getBytes());
		  else if(cc==2)
		    out.write(("4").getBytes());
		  }catch(Exception ex)
		  {
			System.out.println(ex);
		  }
		}	
	  });
	  addKeyListener(new KeyAdapter(){
		public void keyPressed(KeyEvent e)
		{
		  try{
		  Socket client=new Socket(ip,6068);
		  OutputStream out=client.getOutputStream();		
    		  int keycode=e.getKeyCode();
		  out.write(("keypressed#"+keycode).getBytes()); 
		  }catch(Exception ex)
		  {
			System.out.println(ex);
		  }
		}
		public void keyReleased(KeyEvent e)
		{
		  try{
		  Socket client=new Socket(ip,6068);
		  OutputStream out=client.getOutputStream();		
    		  int keycode=e.getKeyCode();
		  out.write(("keyreleased#"+keycode).getBytes()); 
		  }catch(Exception ex)
		  {
			System.out.println(ex);
		  }	
		}
	  });
	  this.ip=ip;
	  l=new JLabel("Please wait....");
	  l.setHorizontalAlignment(JLabel.CENTER);
	  add(l);
	  Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	  int sw=(int)d.getWidth();
	  int sh=(int)d.getHeight();
	  setSize(sw,sh);
	  setVisible(true);
	  ScreenThread st=new ScreenThread();
	  st.start();		
	}
	class ScreenThread extends Thread
	{
	   public void run()
	   {
  	     while(true)
	     {
	      try{
	      Socket client=new Socket(ip,6062);
	      InputStream in=client.getInputStream();
	      BufferedImage img=ImageIO.read(in);
    	      l.setText("");
	      ImageIcon icon=new ImageIcon(img);
	      l.setIcon(icon);
	      }catch(Exception e)
	      {
		System.out.println(e);
	      }
	     }
			
	  }
	}
}