package dataEntry.pl;
import java.awt.*;
import javax.swing.*;
import dataEntry.pl.model.*;
import dataEntry.exception.*;
import dataEntry.dl.*;
import pojo.*;
public class HomeFrame extends JFrame
{
private HomePanel homePanel;
private Container container;
public HomeFrame()
{
initComponents();
setAppearance();
}
private void initComponents()
{
homePanel=new HomePanel();
container=getContentPane();
container.setLayout(null);
homePanel.setBounds(1,1,682,608);
container.add(homePanel);
setSize(618,640);
Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(dimension.width/2-getWidth()/2,dimension.height/2-getHeight()/2);
setVisible(true);
setResizable(false);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
private void setAppearance()
{
setTitle("TMHome Manager");
ImageIcon appIcon=new ImageIcon(this.getClass().getResource("/images/logo.png"));
setIconImage(appIcon.getImage());
}
public static void main(String args[])
{
HomeFrame hf = new HomeFrame();
}
}