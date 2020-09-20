package ason;
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import pojo.*;
public class ason
{
public static void Pojo2Json(String pojo)
{
try
{
String fileName=pojo;
File pojoFile= new File(fileName);
RandomAccessFile pojoRandomAccessFile= new RandomAccessFile(pojoFile,"r");
File jsonFile= new File("home.json");
RandomAccessFile jsonRandomAccessFile= new RandomAccessFile(jsonFile,"rw");
pojoRandomAccessFile.seek(0);
String rooms="\"rooms\"";
String boards="\"boards\"";
String components="\"components\"";
String name="\"name\"";
String id="\"id\"";
boolean applyComma=false;
int nob=0;
int noc=0;
jsonRandomAccessFile.writeBytes("{"+"\n"+"    "+rooms+":"+"["+"\n");
String roomName="";
String roomId="";
String boardName="";
String boardId="";
while(pojoRandomAccessFile.getFilePointer()<pojoRandomAccessFile.length())
{
if(applyComma)jsonRandomAccessFile.writeBytes(",");
jsonRandomAccessFile.writeBytes("	{ \n");
roomId=pojoRandomAccessFile.readLine();
jsonRandomAccessFile.writeBytes("        "+id+": \""+roomId+"\""+","+"\n");
roomName=pojoRandomAccessFile.readLine();
jsonRandomAccessFile.writeBytes("        "+name+": \""+roomName+"\""+","+"\n");
nob=Integer.parseInt(pojoRandomAccessFile.readLine());
jsonRandomAccessFile.writeBytes("	"+boards+":"+"["+"\n");
while(nob>0)
{
if(nob>0)
{
jsonRandomAccessFile.writeBytes("	{"+"\n");
}
jsonRandomAccessFile.writeBytes("\n");
boardId=pojoRandomAccessFile.readLine();
boardName=pojoRandomAccessFile.readLine();
jsonRandomAccessFile.writeBytes("            "+"\"roomId\""+": \""+roomId+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+"\"roomName\""+": \""+roomName+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+id+": \""+boardId+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+name+": \""+boardName+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("        "+"\"status\""+": \""+false+"\""+","+"\n");
noc=Integer.parseInt(pojoRandomAccessFile.readLine());
if(noc>0)
{
jsonRandomAccessFile.writeBytes("            "+components+":"+"[");
}
while(noc>0)
{
jsonRandomAccessFile.writeBytes("            {"+"\n");
jsonRandomAccessFile.writeBytes("            "+"\"roomId\""+": \""+roomId+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+"\"roomName\""+": \""+roomName+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+"\"boardId\""+": \""+boardId+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+"\"boardName\""+": \""+boardName+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+id+": \""+pojoRandomAccessFile.readLine()+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("            "+name+": \""+pojoRandomAccessFile.readLine()+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("          "+"\"status\""+": \""+pojoRandomAccessFile.readLine()+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("          "+"\"regulatable\""+": \""+pojoRandomAccessFile.readLine()+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("          "+"\"minLevel\""+": \""+pojoRandomAccessFile.readLine()+"\""+","+"\n");
jsonRandomAccessFile.writeBytes("          "+"\"maxLevel\""+": \""+pojoRandomAccessFile.readLine()+"\""+"\n");
if(noc==1)
{
jsonRandomAccessFile.writeBytes("            }"+"\n"+"        ]");
}
else
{
jsonRandomAccessFile.writeBytes("            },"+"\n");
}
noc--;
}
if(nob==1)
{
jsonRandomAccessFile.writeBytes("            }"+"\n"+"        ]");
}
else
{
jsonRandomAccessFile.writeBytes("            },"+"\n");
}
nob--;
}
jsonRandomAccessFile.writeBytes("        }");
applyComma=true;
}
//to delete one character
//jsonRandomAccessFile.writeBytes("\b");
jsonRandomAccessFile.writeBytes("    ]"+"\n");
jsonRandomAccessFile.writeBytes("}"+"\n");
pojoRandomAccessFile.close();
jsonRandomAccessFile.close();
}catch(Exception exception)
{
exception.printStackTrace();
}
}
//------------------------------------------------------------------
public static List<RoomDTOInterface> Json2Pojo(String fName)
{
List<RoomDTOInterface> roomsList = new ArrayList<>();
String fileName=fName;
String json="";
try
{
File f= new File(fileName);
RandomAccessFile r= new RandomAccessFile(f,"r");
r.seek(0);
String c="";
while(r.getFilePointer()<r.length())
{
c=r.readLine();
json=json+c;
}
}catch(Exception e)
{
System.out.println("File parsing error "+e);
}
JSONParser jParser;
JSONObject jObject;
JSONArray jArray;
try
{
jParser= new JSONParser();
jObject=(JSONObject) jParser.parse(json);
JSONArray rooms=(JSONArray)jObject.get("rooms");
JSONObject jsonRoom;
JSONObject jsonBoard;
JSONObject jsonComponent;
JSONArray boards;
JSONArray components;
RoomDTOInterface roomDTO;
BoardDTOInterface boardDTO;
ComponentDTOInterface componentDTO;
String name;
int code;
Iterator roomIterator= rooms.iterator();
Iterator boardIterator;
Iterator componentIterator;
while(roomIterator.hasNext())
{
jsonRoom=(JSONObject)roomIterator.next();
name=(String)jsonRoom.get("name");
code=Integer.parseInt((String)jsonRoom.get("id"));
roomDTO= new RoomDTO();
roomDTO.setName(name);
roomDTO.setCode(code);
boards=(JSONArray)jsonRoom.get("boards");
boardIterator=boards.iterator();
while(boardIterator.hasNext())
{
jsonBoard= (JSONObject) boardIterator.next();
name=(String)jsonBoard.get("name");
code=Integer.parseInt((String)jsonBoard.get("id"));
boardDTO= new BoardDTO();
boardDTO.setCode(code);
boardDTO.setName(name);
boardDTO.setRoomName(roomDTO.getName());
boardDTO.setRoomId(roomDTO.getCode()+"");
boardDTO.setStatus(false);
components=(JSONArray)jsonBoard.get("components");
componentIterator=components.iterator();
while(componentIterator.hasNext())
{
jsonComponent= (JSONObject) componentIterator.next();
name=(String)jsonComponent.get("name");
code=Integer.parseInt((String)jsonComponent.get("id"));
int min=Integer.parseInt((String)jsonComponent.get("minLevel"));
int max=Integer.parseInt((String)jsonComponent.get("maxLevel"));
boolean status=Boolean.parseBoolean((String)jsonComponent.get("status"));
boolean regulatable=Boolean.parseBoolean((String)jsonComponent.get("regulatable"));
componentDTO= new ComponentDTO();
componentDTO.setCode(code);
componentDTO.setName(name);
componentDTO.setMin(min);
componentDTO.setMax(max);
componentDTO.setRegulatable(regulatable);
componentDTO.setStatus(status);
componentDTO.setRoomName(roomDTO.getName());
componentDTO.setBoardName(roomDTO.getCode()+"");
componentDTO.setRoomId(boardDTO.getName());
componentDTO.setBoardId(boardDTO.getCode()+"");
boardDTO.addComponent(componentDTO);
}
roomDTO.addBoard(boardDTO);
}
roomsList.add(roomDTO);
}
}catch(Exception p)
{
p.printStackTrace();
}
return roomsList;
}
}
