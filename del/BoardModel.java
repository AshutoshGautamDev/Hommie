//package dataEntry.pl.model;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
//import dataEntry.exception.*;
//import dataEntry.dl.*;
//import pojo.*;
public class BoardModel extends AbstractTableModel
{
private String[] title = {"S.no","Boards"};
private java.util.List<BoardDTOInterface> boards=new java.util.ArrayList<>(); 

public BoardModel()
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
return this.boards.get(rowIndex).getName();
}
public int getRowCount()
{
try
{
if(boards.size()>0) return this.boards.size();
}catch(Exception e)
{
return 0;
}
return 0;
}

public BoardDTOInterface getBoardAt(int columnIndex)
{
return boards.get(columnIndex);
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

public int searchBoard(String s)
{
int index=0;
for(BoardDTOInterface board:boards)
{
if(board.getName().toUpperCase().startsWith(s.toUpperCase()))
{
return index;
}
index++;
}
return -1;
}

public void populate(java.util.List<BoardDTOInterface> boards)
{
this.boards=boards;
fireTableDataChanged();
}

public void dPopulate()
{
this.boards=null;
fireTableDataChanged();
}

public void add(BoardDTOInterface board,RoomDTOInterface room) throws Exception
{
if( boards != null)
{
for(BoardDTOInterface b : boards)
{
String name=board.getName(); 
if(b.getName().equals(name))
{
throw new Exception("Board Already Exsits");
}
}
}
try
{
new RoomDAO().addBoard(room,board);
}catch(Exception e)
{
throw new Exception(e.getMessage());
}
boards=room.getBoards();
//boards.add(board);
fireTableDataChanged();
}

public void update(int index,BoardDTOInterface board,RoomDTOInterface room,String oldName)  throws Exception
{
boards.remove(index);
System.out.println(index);
if( boards != null)
{
for(BoardDTOInterface b : boards)
{
String name=board.getName(); 
if(b.getName().equals(name))
{
throw new Exception("Board Already Exsits");
}
}
}
System.out.println(index);
try
{
System.out.println(index);
new RoomDAO().editBoard(room,board,oldName);
}catch(Exception e)
{
throw new Exception(e.getMessage());
}
System.out.println(index);
boards=room.getBoards();
//boards.add(index,board);
fireTableDataChanged();
}

public void remove(RoomDTOInterface room,BoardDTOInterface board)  throws Exception
{
int count=0;
/*
for(BoardDTOInterface board:boards)
{
if(board.getCode()==code)
{
boards.remove(count);
break;
}
count++;
}
*/
try
{
new RoomDAO().deleteBoard(room,board);
}catch(Exception e)
{
throw new Exception(e.getMessage());
}
boards=room.getBoards();
fireTableDataChanged();
}

}