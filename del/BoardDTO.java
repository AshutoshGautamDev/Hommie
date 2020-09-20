//package pojo;
import java.util.LinkedList;
import java.util.List;

public class BoardDTO implements BoardDTOInterface {
   private LinkedList<ComponentDTOInterface> components;
   private String name;
   private int id;
   private String roomId;
   private String roomName;
   private boolean status;

   public BoardDTO() {
      this.components = new LinkedList();
      this.name = "";
      this.id = 0;
   }

   public BoardDTO(String var1) {
      this.name = var1;
      this.id = 0;
      this.components = new LinkedList();
   }

   public void addComponent(ComponentDTOInterface var1) {
      this.components.add(var1);
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setCode(int var1) {
      this.id = var1;
   }

   public List<ComponentDTOInterface> getComponents() {
      return this.components;
   }

   public int getCode() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void setRoomId(String var1) {
      this.roomId = var1;
   }

   public String getRoomId() {
      return this.roomId;
   }

   public void setRoomName(String var1) {
      this.roomName = var1;
   }

   public String getRoomName() {
      return this.roomName;
   }

   public void setStatus(boolean var1) {
      this.status = var1;
   }

   public boolean getStatus() {
      return this.status;
   }
}
    