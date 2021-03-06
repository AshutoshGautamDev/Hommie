package simulator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.websocket.*;
import java.net.*;
import java.io.*; 
import java.lang.reflect.*;
import simulator.interfaces.*;
import pojo.*;
import ason.*;
import simulator.event.*;
@ClientEndpoint
public class Simulator extends JFrame implements StatusListener
{
private SimulatorPanel simulatorPanel;
private Container container;
private JScrollPane jsp;
private JLabel iconLabel;
private JLabel headingText;
private Session session;
public Simulator()
{
initComponents();
setAppearance();
}

public void onStatusChanged(StatusEvent se)
{
try
{
session.getBasicRemote().sendText("something");
}catch(Exception e)
{
e.printStackTrace();
System.exit(0);
}
}

public void initComponents()
{
simulatorPanel = new SimulatorPanel(this);
headingText = new JLabel("TM Simulator");
iconLabel = new JLabel(new ImageIcon(this.getClass().getResource("/images/simulator.png")));
container=getContentPane();
container.setLayout(null);
Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
jsp=new JScrollPane(simulatorPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
jsp.setBounds(10,100+5,dimension.width-130,dimension.height-250);
iconLabel.setBounds(600,1,80,80);
headingText.setBounds(570,80,200,20);
container.setBackground(new Color(255,255,255));
container.add(jsp);
container.add(iconLabel);
container.add(headingText);
setSize(dimension.width-100,dimension.height-100);
setLocation(dimension.width/2-getWidth()/2,dimension.height/2-getHeight()/2);
setVisible(true);
setResizable(false);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

public void setAppearance()
{
java.awt.Font font=new java.awt.Font("Verdana",java.awt.Font.BOLD,20);
headingText.setFont(font);
setTitle("TM Simulator");
ImageIcon appIcon=new ImageIcon(this.getClass().getResource("/images/logo.png"));
setIconImage(appIcon.getImage());
}


public static void main(String args[])
{
try
{
WebSocketContainer wsc = null;
wsc=ContainerProvider.getWebSocketContainer();
Session s=wsc.connectToServer(Simulator.class, java.net.URI.create("ws://localhost:8888/websocket"));
}catch (Exception e) 
{
e.printStackTrace();
}
}

@OnOpen
public void onOpen(Session session)
{
this.session=session;
System.out.println("onOpen");
}

@OnMessage
public void onMessage(String message, Session session)
{
System.out.println("onMessage");
}

@OnError
public void onError(Session session,Throwable th)
{
System.out.println("onError");
}

@OnClose
public void onClose(Session session,CloseReason cr)
{
System.out.println("onClose");
}

}
