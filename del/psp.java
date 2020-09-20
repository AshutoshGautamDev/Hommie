//import pojo.*;
//import ason.*;
public class psp
{
public static void main(String gg[])
{
//ason.Pojo2Json("home.data");
java.util.List<RoomDTOInterface> rooms;
java.util.List<BoardDTOInterface> boards;
java.util.List<ComponentDTOInterface> components;
rooms = (java.util.List<RoomDTOInterface>)ason.Json2Pojo("home.json");
for(RoomDTOInterface r: rooms)
{
System.out.println("Room Name:"+r.getName());
System.out.println("Room Id:"+r.getCode());
boards=r.getBoards();
for(BoardDTOInterface b:boards)
{
System.out.println("RName:"+b.getRoomName());
System.out.println("RId:"+b.getRoomId());
System.out.println("BoardName:"+b.getName());
System.out.println("BoardId:"+b.getCode());
System.out.println("BoardStatus:"+b.getStatus());
components=b.getComponents();
for(ComponentDTOInterface c:components)
{
System.out.println("RName:"+c.getRoomName());
System.out.println("RId:"+c.getRoomId());
System.out.println("BName:"+c.getBoardName());
System.out.println("BId:"+c.getBoardId());
System.out.println("Name:"+c.getName());
}
}
}
}
}