import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class IpWindow extends JFrame
{
	JTextField t;
	public IpWindow()
	{
		super("IP Window");
		GridBagLayout gb=new GridBagLayout();
		setLayout(gb);
		JLabel l=new JLabel("Enter IP");
		l.setFont(new Font("Tahoma",Font.BOLD,12));
		add(l);
		t=new JTextField(30);
		t.addKeyListener(new KeyAdapter(){
		  public void keyReleased(KeyEvent e)
		  {
			int c=e.getKeyCode();
			if(c==KeyEvent.VK_ENTER)	
			{
			String s=t.getText();
	 		new TeamViewerClient(s);
			dispose();
			}		
		  }
		});
		add(t);
		setSize(500,200);
		setVisible(true);
	}
	public static void main(String a[])
	{
		new IpWindow();
	}
}