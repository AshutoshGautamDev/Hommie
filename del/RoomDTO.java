  //package pojo;

import java.util.LinkedList;
import java.util.List;

public class RoomDTO implements RoomDTOInterface {
   private int id = 0;
   private String name = "";
   private List<BoardDTOInterface> boards = new LinkedList();

   public void setName(String var1) {
      this.name = var1;
   }

   public void setCode(int var1) {
      this.id = var1;
   }

   public void addBoard(BoardDTOInterface var1) {
      this.boards.add(var1);
   }

   public int getCode() {
      return this.id;
   }

   public List<BoardDTOInterface> getBoards() {
      return this.boards;
   }

   public String getName() {
      return this.name;
   }
}
    