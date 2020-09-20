//package simulator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.websocket.*;
import java.net.*;
import java.io.*; 
import java.lang.reflect.*;
import com.google.gson.*;
//import simulator.interfaces.*;
//import pojo.*;
//import ason.*;
//import dataEntry.dl.*;
class SimulatorPanel extends JPanel implements MouseListener
{
private int mode;
private static final int ON=1;
private static final int OFF=2;  
private Map<JPanel,ComponentDTOInterface> componentPanelMap;
private Map<ComponentDTOInterface,JPanel> panelsMap;
private StatusListener statusListener;
private java.util.List<RoomDTOInterface> rooms;
private java.util.List<BoardDTOInterface> boards;
private java.util.List<ComponentDTOInterface> components;

private JPanel roomPanel[];
private JLabel roomLabel[];

private JPanel boardPanel[];
private JLabel boardLabel[];

private JPanel componentPanel[];
private JLabel componentLabel[];
private JLabel componentStatusLabel[];
private JLabel componentRegulatableLabel[];
private JLabel componentMinLabel[];
private JLabel componentMaxLabel[];
private JLabel componentIcon[];
private JLabel statusLabel[];

public SimulatorPanel(StatusListener se)
{
this.statusListener=se;
rooms = (java.util.List<RoomDTOInterface>)ason.Json2Pojo("home.json");
componentPanelMap=new HashMap<>();
panelsMap=new HashMap<>();
initComponents();
}
public void initComponents()
{
java.awt.Font font=new java.awt.Font("Verdana",java.awt.Font.PLAIN,20);
roomPanel=new JPanel[rooms.size()];
roomLabel=new JLabel[rooms.size()];
RoomDTOInterface room;
BoardDTOInterface board;
ComponentDTOInterface component;
for(int i = 0; i < rooms.size() ; i++)
{
room=rooms.get(i);
roomPanel[i] = new JPanel();
roomPanel[i].setBackground(new Color(255,255,255));
roomLabel[i] = new JLabel("Room :"+ room.getName());
roomLabel[i].setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,20));
roomPanel[i].add(roomLabel[i]);
roomPanel[i].setLayout(new BoxLayout(roomPanel[i],BoxLayout.Y_AXIS));
roomPanel[i].setBorder(BorderFactory.createLineBorder(Color.black,5));
boards=room.getBoards();
boardPanel=new JPanel[boards.size()];
boardLabel=new JLabel[boards.size()];

for(int j = 0; j < boards.size(); j++)
{
board=boards.get(j);
boardPanel[j] = new JPanel();
boardLabel[j] = new JLabel("Board: "+ board.getName());
boardLabel[j].setFont(new java.awt.Font("Verdana",java.awt.Font.BOLD,15));
boardPanel[j].setBackground(new Color(255,255,255));
boardPanel[j].add(boardLabel[j]);
boardPanel[j].setLayout(new BoxLayout(boardPanel[j],BoxLayout.Y_AXIS));
boardPanel[j].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
components=board.getComponents();
componentPanel=new JPanel[components.size()];
componentLabel=new JLabel[components.size()];
componentStatusLabel=new JLabel[components.size()];
componentRegulatableLabel=new JLabel[components.size()];
componentMinLabel=new JLabel[components.size()];
componentMaxLabel=new JLabel[components.size()];
componentIcon=new JLabel[components.size()];
statusLabel = new JLabel[components.size()];
for(int k =0 ; k < components.size() ; k++ )
{
component=components.get(k);
componentPanel[k]=new JPanel();
componentPanelMap.put(componentPanel[k],component);
panelsMap.put(component,componentPanel[k]);
componentPanel[k].setBackground(new Color(255,255,255));
componentLabel[k] = new JLabel("Component :"+component.getName()+"              ");
componentLabel[k].setForeground(new Color(160,82,45));
componentStatusLabel[k]=new JLabel("Status :");
if(component.getStatus())
{
statusLabel[k]=new JLabel(new ImageIcon("images/on.png"));
}
else
{
statusLabel[k]=new JLabel(new ImageIcon("images/off.png"));
}
componentRegulatableLabel[k]=new JLabel("Regulatable:"+component.getRegulatable()+"              ");
componentRegulatableLabel[k].setForeground(new Color(160,82,45));
componentMinLabel[k]=new JLabel("Min :"+component.getMin()+"              ");
componentMinLabel[k].setForeground(new Color(160,82,45));
componentMaxLabel[k]=new JLabel("Max :"+component.getMax()+"              ");
componentMaxLabel[k].setForeground(new Color(160,82,45));
if(component.getName().toUpperCase().contains("BULB"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/bulb.png"));
}
else
{
if(component.getName().toUpperCase().contains("DIODE"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/diode.png"));
}
else
{
if(component.getName().toUpperCase().contains("TV"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/tv.png"));
}
else
{
if(component.getName().toUpperCase().contains("FAN"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/fan.png"));
}
else
{
if(component.getName().toUpperCase().contains("LED"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/led.png"));
}
else
{
if(component.getName().toUpperCase().contains("LAMP"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/lamp.png"));
}
else
{
if(component.getName().toUpperCase().contains("CHARGE"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/charge.png"));
}
else
{
if(component.getName().toUpperCase().contains("MUSIC"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/music.png"));
}
else
{
if(component.getName().toUpperCase().contains("RADIO"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/radio.png"));
}
else
{
if(component.getName().toUpperCase().contains("LIGHT"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/light.png"));
}
else
{
if(component.getName().toUpperCase().contains("WASH"))
{
componentIcon[k]=new JLabel(new ImageIcon("images/washmachine.png"));
}
else
{
if(component.getName().toUpperCase().contains("MICRO") || component.getName().toUpperCase().contains("OVEN") )
{
componentIcon[k]=new JLabel(new ImageIcon("images/oven.png"));
}
else
{
componentIcon[k]=new JLabel(new ImageIcon("images/circuit.png"));
}
}
}
}
}
}
}
}
}
}
}
}
componentPanel[k].addMouseListener(this);
componentPanel[k].add(componentIcon[k]);
componentPanel[k].add(componentLabel[k]);
componentPanel[k].add(componentRegulatableLabel[k]);
componentPanel[k].add(componentMinLabel[k]);
componentPanel[k].add(componentMaxLabel[k]);
componentPanel[k].add(componentStatusLabel[k]);
componentPanel[k].add(statusLabel[k]);
componentPanel[k].setLayout(new FlowLayout());
componentPanel[k].setBorder(BorderFactory.createDashedBorder(Color.black));
boardPanel[j].add(componentPanel[k]);
}
roomPanel[i].add(boardPanel[j]);
}
add(roomPanel[i]);
}
setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
}

public void mouseClicked(MouseEvent ev)
{
JPanel panel=(JPanel)(ev.getSource());
Component cs[] =panel.getComponents();
JLabel label=(JLabel)(cs[cs.length-1]);
ComponentDTOInterface component=componentPanelMap.get(panel);
boolean status=component.getStatus();
if(status)
{
component.setStatus(false);
StatusEvent statusEvent=new StatusEvent();
statusEvent.setComponent(component);
label.setIcon(new ImageIcon("images/off.png"));
statusListener.onStatusChanged(statusEvent);
}
else
{
component.setStatus(true);
StatusEvent statusEvent=new StatusEvent();
statusEvent.setComponent(component);
label.setIcon(new ImageIcon("images/on.png"));
statusListener.onStatusChanged(statusEvent);
}
}
public void mouseEntered(MouseEvent ev){}
public void mouseExited(MouseEvent ev){}
public void mousePressed(MouseEvent ev){}
public void mouseReleased(MouseEvent ev){}
public void updateDataStructure(String message)
{
JsonObject jo=new Gson().fromJson(message,JsonObject.class);
String roomId=jo.get("roomId").getAsString();
String roomName=jo.get("roomName").getAsString();
String boardId=jo.get("boardId").getAsString();
String boardName=jo.get("boardName").getAsString();
String componentId=jo.get("componentId").getAsString();
String componentName=jo.get("componentName").getAsString();
for(RoomDTOInterface room:rooms)
{
boards=room.getBoards();
for(BoardDTOInterface board:boards)
{
components=board.getComponents();
for(ComponentDTOInterface component:components)
{
if(component.getCode()==Integer.parseInt(componentId))
{
JPanel panel=panelsMap.get(component);
Component cs[] =panel.getComponents();
JLabel label=(JLabel)(cs[cs.length-1]);
if(jo.get("componentStatus").getAsBoolean())
{
label.setIcon(new ImageIcon("images/on.png"));
}
else
{
label.setIcon(new ImageIcon("images/off.png"));
}
}
}
}
}
}
}
