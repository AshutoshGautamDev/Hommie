package dataEntry.dl;
import java.io.*;
import java.util.*;
import dataEntry.util.*;
import dataEntry.exception.*;
import pojo.*;
public class RoomDAO
{
private final static String dataFile="home.data";
public void add(RoomDTOInterface roomDTO) throws DAOException
{
try
{
File f=new File(dataFile);
RandomAccessFile raf=new RandomAccessFile(f,"rw");
String name=roomDTO.getName();

int x=0,y=0;
int i=0,j=0;
while(raf.getFilePointer()<raf.length())
{
raf.readLine();
if(raf.readLine().equalsIgnoreCase(name))
{
raf.close();
throw new DAOException("Room already exists");
}
x=Integer.parseInt(raf.readLine());
i=0;
while(i<x)
{
raf.readLine();
raf.readLine();
y=Integer.parseInt(raf.readLine());
j=0;
while(j<y)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
j++;
}
i++;
}
}
raf.seek(raf.length());
roomDTO.setCode((int)Sequence.next("room"));
raf.writeBytes(roomDTO.getCode()+"\n");
raf.writeBytes(roomDTO.getName()+"\n");
List<BoardDTOInterface> boards=roomDTO.getBoards();
List<ComponentDTOInterface> components;
raf.writeBytes(boards.size()+"\n");
int code=roomDTO.getCode();
for(BoardDTOInterface board:boards)
{
board.setCode((int)Sequence.next("board"));
raf.writeBytes(board.getCode()+"\n");
raf.writeBytes(board.getName()+"\n");
components=board.getComponents();
raf.writeBytes(components.size()+"\n");
for(ComponentDTOInterface component:components)
{
component.setCode((int)Sequence.next("component"));
raf.writeBytes(component.getCode()+"\n");
raf.writeBytes(component.getName()+"\n");
raf.writeBytes(component.getStatus()+"\n");
raf.writeBytes(component.getRegulatable()+"\n");
raf.writeBytes(component.getMin()+"\n");
raf.writeBytes(component.getMax()+"\n");
}
}
raf.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void update(RoomDTOInterface room) throws DAOException
{
try
{
int w=0,x=0,y=0,z=0;
int h=0,i=0,j=0,k=0;
int count=0;
String name=room.getName();
int code=room.getCode();
File f=new File(dataFile);
if(!f.exists())
{
throw new DAOException("Invalid code");
}
RandomAccessFile raf=new RandomAccessFile(f,"rw");
while(raf.getFilePointer()<raf.length())
{
if(Integer.parseInt(raf.readLine().trim())==room.getCode())
{
count=1;
break;
}
raf.readLine();
y=Integer.parseInt(raf.readLine());
j=0;
while(j<y)
{
raf.readLine();
raf.readLine();
z=Integer.parseInt(raf.readLine());
k=0;
while(k<z)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
k++;
}
j++;
}
}
if(count==0)
{
throw new DAOException("Invalid code");
}
raf.seek(0);
while(raf.getFilePointer()<raf.length())
{
code=Integer.parseInt(raf.readLine());
name=raf.readLine();
if(room.getName().equals(name) && code!=room.getCode())
{
throw new DAOException("Room already exist ");
}
y=Integer.parseInt(raf.readLine());
j=0;
while(j<y)
{
raf.readLine();
raf.readLine();
z=Integer.parseInt(raf.readLine());
k=0;
while(k<z)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
k++;
}
j++;
}
}

List<BoardDTOInterface> boards;
List<ComponentDTOInterface> components;

File tf=new File("temp.data");
RandomAccessFile traf=new RandomAccessFile(tf,"rw");
traf.seek(0);
raf.seek(0);
while(raf.getFilePointer()<raf.length())
{
code=Integer.parseInt(raf.readLine());
if(code==room.getCode())
{
traf.writeBytes(room.getCode()+"\n");
traf.writeBytes(room.getName()+"\n");
boards=room.getBoards();
traf.writeBytes(boards.size()+"\n");
for(BoardDTOInterface board:boards)
{
board.setCode((int)Sequence.next("board"));
traf.writeBytes(board.getCode()+"\n");
traf.writeBytes(board.getName()+"\n");
components=board.getComponents();
traf.writeBytes(components.size()+"\n");
for(ComponentDTOInterface component:components)
{
component.setCode((int)Sequence.next("component"));
traf.writeBytes(component.getCode()+"\n");
traf.writeBytes(component.getName()+"\n");
traf.writeBytes(component.getStatus()+"\n");
traf.writeBytes(component.getRegulatable()+"\n");
traf.writeBytes(component.getMin()+"\n");
traf.writeBytes(component.getMax()+"\n");
}
}
raf.readLine();
y=Integer.parseInt(raf.readLine());
j=0;
while(j<y)
{
raf.readLine();
raf.readLine();
z=Integer.parseInt(raf.readLine());
k=0;
while(k<z)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
k++;
}
j++;
}
//if khatam
}
else
{
traf.writeBytes(code+"\n");
traf.writeBytes(raf.readLine()+"\n");
y=Integer.parseInt(raf.readLine());
traf.writeBytes(y+"\n");
j=0;
while(j<y)
{
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
z=Integer.parseInt(raf.readLine());
traf.writeBytes(z+"\n");
k=0;
while(k<z)
{
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
k++;
}
j++;
}
}
}//raf ka loop khatam
traf.seek(0);
raf.seek(0);
while(traf.getFilePointer()<traf.length())
{
raf.writeBytes(traf.readLine()+"\n");
}
raf.setLength(traf.length());
traf.setLength(0);
raf.close();
traf.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void delete(int code) throws DAOException
{
try
{
File f=new File(dataFile);
RandomAccessFile raf=new RandomAccessFile(f,"rw");
int count=0;
int x=0,y=0,z=0;
int i=0,j=0,k=0;
while(raf.getFilePointer()<raf.length())
{
if(Integer.parseInt(raf.readLine().trim())==code)
{
count=1;
}
raf.readLine();
x=Integer.parseInt(raf.readLine());
i=0;
while(i<x)
{
raf.readLine();
raf.readLine();
y=Integer.parseInt(raf.readLine());
j=0;
while(j<y)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
j++;
}
i++;
}
}
if(count==0)throw new DAOException("Invalid code");
File tf=new File("temp.data");
RandomAccessFile traf=new RandomAccessFile(tf,"rw");
int code1;
raf.seek(0);
while(raf.getFilePointer()<raf.length())
{
code1=Integer.parseInt(raf.readLine().trim());
if(code1==code)
{
raf.readLine();
y=Integer.parseInt(raf.readLine());
k=0;
while(k<y)
{
raf.readLine();
raf.readLine();
z=Integer.parseInt(raf.readLine());
i=0;
while(i<z)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
i++;
}
k++;
}
}
else
{
traf.writeBytes(code1+"\n");
traf.writeBytes(raf.readLine()+"\n");
y=Integer.parseInt(raf.readLine());
traf.writeBytes(y+"\n");
j=0;
while(j<y)
{
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
z=Integer.parseInt(raf.readLine());
traf.writeBytes(z+"\n");
k=0;
while(k<z)
{
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
traf.writeBytes(raf.readLine()+"\n");
k++;
}
j++;
}
}
}
traf.seek(0);
raf.seek(0);
while(traf.getFilePointer()<traf.length())
{
raf.writeBytes(traf.readLine()+"\n");
}
raf.setLength(traf.length());
traf.setLength(0);
raf.close();
traf.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public long getCount() throws DAOException
{
int count=0;
int h=0,i=0,j=0,k=0,w=0,x=0,y=0,z=0,found=0;
try
{
File f=new File(dataFile);
RandomAccessFile raf=new RandomAccessFile(f,"rw");
while(raf.getFilePointer()<raf.length())
{
count++;
raf.readLine();
raf.readLine();
y=Integer.parseInt(raf.readLine());
k=0;
while(k<y)
{
raf.readLine();
raf.readLine();
z=Integer.parseInt(raf.readLine());
i=0;
while(i<z)
{
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
raf.readLine();
i++;
}
k++;
}
}
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return count;
}
public RoomDTOInterface getByCode(int code) throws DAOException
{
List<RoomDTOInterface> rooms=getAll();
for(RoomDTOInterface room:rooms)
{
if(room.getCode()==code)
{
return room;
}
}
throw new DAOException("Invalid code");
}
public RoomDTOInterface getByName(String name) throws DAOException
{
List<RoomDTOInterface> rooms=getAll();
for(RoomDTOInterface room:rooms)
{
if(room.getName().equalsIgnoreCase(name))
{
return room;
}
}
throw new DAOException("Name not count");
}
public List<RoomDTOInterface> getAll()
{
List<RoomDTOInterface> rooms=new LinkedList<>();
try
{
RoomDTOInterface roomDTO;
File f=new File(dataFile);
if(!f.exists())
{
return rooms;
}
RandomAccessFile raf=new RandomAccessFile(f,"rw");
RoomDTOInterface room;
BoardDTOInterface board;
ComponentDTOInterface component;
int w=0,x=0,y=0,z=0;
int h=0,i=0,j=0,k=0;
while(raf.getFilePointer()<raf.length())
{
room=new RoomDTO();
room.setCode(Integer.parseInt(raf.readLine()));
room.setName(raf.readLine());
x=Integer.parseInt(raf.readLine());
k=0;
while(k<x)
{
board=new BoardDTO();
board.setCode(Integer.parseInt(raf.readLine()));
board.setName(raf.readLine());
w=Integer.parseInt(raf.readLine());
h=0;
while(h<w)
{
component=new ComponentDTO();
component.setCode(Integer.parseInt(raf.readLine()));
component.setName(raf.readLine());
if(raf.readLine().equals("true"))
{
component.setStatus(true);
}
else
{
component.setStatus(false);
}
if(raf.readLine().equals("true"))
{
component.setRegulatable(true);
}
else
{
component.setRegulatable(false);
}
component.setMin(Integer.parseInt(raf.readLine()));
component.setMax(Integer.parseInt(raf.readLine()));
board.addComponent(component);
h++;
}
room.addBoard(board);
k++;
}
rooms.add(room);
}
return rooms;
}catch(Exception e)
{
return rooms;
}
}
public void addBoard(RoomDTOInterface roomDTO,BoardDTOInterface boardDTO) throws DAOException
{
String roomName=roomDTO.getName().toUpperCase();
String boardName=boardDTO.getName().toUpperCase();
try
{
File f=new File(dataFile);
if(!f.exists())
{
add(roomDTO);
}
int count=0;
List<RoomDTOInterface> rooms=getAll();
List<BoardDTOInterface> boards;
for(RoomDTOInterface room:rooms)
{
if(room.getName().toUpperCase().equalsIgnoreCase(roomName))
{
boards=room.getBoards();
for(BoardDTOInterface board:boards)
{
count=0;
if(board.getName().toUpperCase().equalsIgnoreCase(boardName))
{
throw new DAOException(boardName+" already exist in room "+roomName);
}
}
roomDTO.addBoard(boardDTO);
//delete(roomDTO.getCode());
update(roomDTO);
}
else
{
continue;
}
}

}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void addComponent(RoomDTOInterface roomDTO,BoardDTOInterface boardDTO,ComponentDTOInterface componentDTO) throws DAOException
{
String roomName=roomDTO.getName().toUpperCase();
String boardName=boardDTO.getName().toUpperCase();
String componentName=componentDTO.getName().toUpperCase();
try
{
File f=new File(dataFile);
if(!f.exists())
{
add(roomDTO);
}
List<RoomDTOInterface> rooms=getAll();
List<BoardDTOInterface> boards;
List<ComponentDTOInterface> components;
for(RoomDTOInterface room:rooms)
{
if(room.getName().toUpperCase().equalsIgnoreCase(roomName))
{
boards=room.getBoards();
for(BoardDTOInterface board:boards)
{
if(board.getName().toUpperCase().equalsIgnoreCase(boardName))
{
components=board.getComponents();
for(ComponentDTOInterface component:components)
{
if(component.getName().toUpperCase().equalsIgnoreCase(componentName))
{
throw new DAOException(componentName+" already exist in board "+boardName+" of room "+roomName);
}
}
boardDTO.addComponent(componentDTO);
//delete(roomDTO.getCode());
update(roomDTO);
}
}
}
else
{
continue;
}
}
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void editBoard(RoomDTOInterface roomDTO,BoardDTOInterface boardDTO,String oldBoardName) throws  DAOException
{
String roomName=roomDTO.getName().toUpperCase();
String boardName=boardDTO.getName().toUpperCase();
oldBoardName=oldBoardName.toUpperCase();
try
{
File f=new File(dataFile);
if(!f.exists())
{
add(roomDTO);
}
int count=0;
List<RoomDTOInterface> rooms=getAll();
List<BoardDTOInterface> boards;

for(RoomDTOInterface room:rooms)
{
if(room.getName().toUpperCase().equalsIgnoreCase(roomName))
{
boards=room.getBoards();
count=0;
for(BoardDTOInterface board:boards)
{
if(board.getName().toUpperCase().equalsIgnoreCase(oldBoardName))
{
boards.remove(count);
roomDTO.addBoard(boardDTO);
//delete(roomDTO.getCode());
update(roomDTO);
}
count++;
}
}
else
{
continue;
}
}

}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void editComponent(RoomDTOInterface roomDTO,BoardDTOInterface boardDTO,ComponentDTOInterface componentDTO,String oldComponentName) throws DAOException
{
String roomName=roomDTO.getName().toUpperCase();
String boardName=boardDTO.getName().toUpperCase();
String componentName=componentDTO.getName().toUpperCase();
oldComponentName=oldComponentName.toUpperCase();
try
{
File f=new File(dataFile);
if(!f.exists())
{
add(roomDTO);
}
int count=0;
List<RoomDTOInterface> rooms=getAll();
List<BoardDTOInterface> boards;
List<ComponentDTOInterface> components;
for(RoomDTOInterface room:rooms)
{
if(room.getName().toUpperCase().equalsIgnoreCase(roomName))
{
boards=room.getBoards();
for(BoardDTOInterface board:boards)
{
if(board.getName().toUpperCase().equalsIgnoreCase(boardName))
{
components=board.getComponents();
count=0;
for(ComponentDTOInterface component:components)
{
if(component.getName().toUpperCase().equalsIgnoreCase(oldComponentName))
{
components.remove(count);
boardDTO.addComponent(componentDTO);
update(roomDTO);
}
count++;
}
}
}
}
else
{
continue;
}
}
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void deleteBoard(RoomDTOInterface room,BoardDTOInterface board)throws DAOException
{
try
{
String roomName=room.getName().toUpperCase();
String boardName=board.getName().toUpperCase();
File f=new File(dataFile);
if(!f.exists())
{
throw new DAOException("Invalid Board");
}
List<BoardDTOInterface> boards=room.getBoards();
int count=0;
int found=0;
for(BoardDTOInterface board1:boards)
{
if(board1.getName().toUpperCase().equalsIgnoreCase(boardName))
{
found=1;
boards.remove(count);
break;
}
count++;
}
if(found==1)
{
update(room);
}
else
{
throw new DAOException("Board Not Found");
}
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public void deleteComponent(RoomDTOInterface room,BoardDTOInterface board,ComponentDTOInterface component) throws DAOException
{

try
{
String roomName=room.getName().toUpperCase();
String boardName=board.getName().toUpperCase();
String componentName=component.getName().toUpperCase();
File f=new File(dataFile);

if(!f.exists())
{
throw new DAOException("Invalid Board");
}
List<ComponentDTOInterface> components=board.getComponents();
int count=0;
int found=0;

for(ComponentDTOInterface c1:components)
{
if(c1.getName().toUpperCase().equalsIgnoreCase(componentName))
{
found=1;
components.remove(count);
break;
}
count++;
}

if(found==1)
{
update(room);
}
else
{
throw new DAOException("Component Not Found");
}

}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

}
