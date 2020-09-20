package pojo;

import java.util.List;

public interface BoardDTOInterface {
   void setRoomId(String var1);

   String getRoomId();

   void setRoomName(String var1);

   String getRoomName();

   void setStatus(boolean var1);

   boolean getStatus();

   void setCode(int var1);

   int getCode();

   void setName(String var1);

   void addComponent(ComponentDTOInterface var1);

   String getName();

   List<ComponentDTOInterface> getComponents();
}
    