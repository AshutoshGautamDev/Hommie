package dataEntry.pl.model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import dataEntry.exception.*;
import dataEntry.dl.*;
import pojo.*;
public class RoomModel extends AbstractTableModel
{
private String[] title = {"S.no","Rooms"};
private java.util.List<RoomDTOInterface> rooms= null;
public RoomModel()
{
rooms= new RoomDAO().getAll();
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
return this.rooms.get(rowIndex).getName();
}

public int getRowCount()
{
try
{
if(rooms.size()>0) return this.rooms.size();
}catch(Exception e)
{
return 0;
}
return 0;
}

public RoomDTOInterface getRoomAt(int columnIndex)
{
return rooms.get(columnIndex);
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

public int searchRoom(String s)
{
int index=0;
for(RoomDTOInterface room:rooms)
{
if(room.getName().toUpperCase().startsWith(s.toUpperCase()))
{
return index;
}
index++;
}
return -1;
}

public void populate()
{
rooms=new RoomDAO().getAll();
}

public void dPopulate()
{
rooms=null;
fireTableDataChanged();
}

public void add(RoomDTOInterface room) throws DAOException
{
try
{
//System.out.println(room.getCode());
new RoomDAO().add(room);
//System.out.println(room.getCode());
}catch(DAOException e)
{
throw new DAOException(e.getMessage());
}
room=new RoomDAO().getByName(room.getName());
//System.out.println(room.getCode());
rooms.add(room);
fireTableDataChanged();
}

public void update(int index,RoomDTOInterface room) throws DAOException
{
try
{
new RoomDAO().update(room);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
rooms.remove(index);
rooms.add(index,room);
fireTableDataChanged();
}
		
public void remove(int code) 
{
try
{
new RoomDAO().delete(code);
int count=0;
for(RoomDTOInterface room:rooms)
{
if(room.getCode()==code)
{
rooms.remove(count);
break;
}
count++;
}
fireTableDataChanged();
}catch(Exception e)
{
e.printStackTrace();
}
}
}