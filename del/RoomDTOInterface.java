// package pojo;

import java.util.List;

public interface RoomDTOInterface {
   void setName(String var1);

   void setCode(int var1);

   void addBoard(BoardDTOInterface var1);

   int getCode();

   String getName();

   List<BoardDTOInterface> getBoards();
}
    