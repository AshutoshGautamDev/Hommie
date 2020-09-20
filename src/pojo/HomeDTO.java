 package pojo;

import java.util.ArrayList;
import java.util.List;

public class HomeDTO implements HomeDTOInterface {
   private List<RoomDTOInterface> rooms;

   public HomeDTO() {
      this.rooms = new ArrayList();
   }

   public HomeDTO(List<RoomDTOInterface> var1) {
      this.rooms = var1;
   }

   public void addRoom(RoomDTOInterface var1) {
      this.rooms.add(var1);
   }

   public void setRooms(List<RoomDTOInterface> var1) {
      this.rooms = var1;
   }

   public List<RoomDTOInterface> getRooms() {
      return this.rooms;
   }
}
    