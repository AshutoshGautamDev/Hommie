//package pojo;
public interface ComponentDTOInterface {
   void setName(String var1);

   void setCode(int var1);

   String getName();

   int getCode();

   void setMin(int var1);

   void setMax(int var1);

   int getMin();

   int getMax();

   void setRegulatable(boolean var1);

   boolean getRegulatable();

   boolean getStatus();

   void setStatus(boolean var1);
   
   void setRoomName(String var1);

   void setRoomId(String var1); 
   
   String getRoomId();
   
   String getRoomName();

   void setBoardId(String var1);
   
   String getBoardId();

   void setBoardName(String var1);
   
   String getBoardName();


}