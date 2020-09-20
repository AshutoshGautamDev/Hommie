package dataEntry.pl.model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import dataEntry.exception.*;
import dataEntry.dl.*;
import pojo.*;
public class ComponentModel extends AbstractTableModel
{
private String[] title = {"S.no","Components"};
private java.util.List<ComponentDTOInterface> components=new java.util.ArrayList<>(); 

public ComponentModel()
{
}

public int getColumnCount()
{
return title.length;
}

public Font getDataFont()
{
Font dataFont = new Font("Serif", Font.PLAIN, 20);  
return dataFont;
}

public Font getTitleFont()
{
Font titleFont=new Font("Serif",Font.BOLD,26);
return titleFont;
}

public String getColumnName(int columnIndex)
{
if(columnIndex==0) return title[0];
return title[1];
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return this.components.get(rowIndex).getName();
}

public int getRowCount()
{
try
{
if(components.size()>0) return this.components.size();
}catch(Exception e)
{
return 0;
}
return 0;
}

public ComponentDTOInterface getComponent(int columnIndex)
{
return components.get(columnIndex);
}

public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
return String.class;
}

public boolean isCellEditable()
{
return false;
}

public int searchComponent(String s)
{
int index=0;
for(ComponentDTOInterface component:components)
{
if(component.getName().toUpperCase().startsWith(s.toUpperCase()))
{
return index;
}
index++;
}
return -1;
}
public void populate()
{
fireTableDataChanged();
}

public void populate(java.util.List<ComponentDTOInterface> components)
{
this.components=components;
fireTableDataChanged();
}

public void dPopulate()
{
this.components=null;
fireTableDataChanged();
}

public void add(ComponentDTOInterface component,BoardDTOInterface board,RoomDTOInterface room) throws Exception
{
if(components != null)
{
for(ComponentDTOInterface b : components)
{
String name=component.getName(); 
if(b.getName().equals(name))
{
throw new Exception("Component Already Exsits");
}
}
}
try
{
new RoomDAO().addComponent(room,board,component);
}catch(Exception e)
{
throw new Exception("kuch exccepton");
}
components=board.getComponents();
fireTableDataChanged();
}

public void update(int index,ComponentDTOInterface component,BoardDTOInterface board,RoomDTOInterface room,String oldName)  throws Exception
{
components.remove(index);
if(components != null)
{
for(ComponentDTOInterface b : components)
{
String name=component.getName(); 
if(b.getName().equals(name))
{
throw new Exception("Component Already Exsits");
}
}
}
try
{
new RoomDAO().editComponent(room,board,component,oldName);
}catch(Exception e)
{
throw new Exception("kuch exccepton");
}
fireTableDataChanged();
}

public void remove(RoomDTOInterface room,BoardDTOInterface board,ComponentDTOInterface component) throws DAOException
{
/*
int count=0;
for(ComponentDTOInterface component:components)
{
if(component.getCode()==code)
{
components.remove(count);
break;
}
count++;
}
*/
try
{
new RoomDAO().deleteComponent(room,board,component);
}catch(Exception e) 
{
throw new DAOException(e.getMessage());
}
components=board.getComponents();
fireTableDataChanged();
}

}