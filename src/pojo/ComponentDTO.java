package pojo;

public class ComponentDTO implements ComponentDTOInterface {
   private String name;
   private String roomName;
   private String boardName;
   private String roomId;
   private String boardId;
   private int id;
   private int max;
   private int min;
   private boolean status;
   private boolean regulatable;

   public ComponentDTO() {
      this.name = "";
      this.id = 0;
   }

   public ComponentDTO(String var1) {
      this.name = var1;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setCode(int var1) {
      this.id = var1;
   }

   public int getCode() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void setMax(int var1) {
      this.max = var1;
   }

   public void setMin(int var1) {
      this.min = var1;
   }

   public void setRegulatable(boolean var1) {
      this.regulatable = var1;
   }

   public void setStatus(boolean var1) {
      this.status = var1;
   }

   public int getMin() {
      return this.min;
   }

   public int getMax() {
      return this.max;
   }

   public boolean getRegulatable() {
      return this.regulatable;
   }

   public boolean getStatus() {
      return this.status;
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

   public void setBoardId(String var1) {
      this.boardId = var1;
   }

   public String getBoardId() {
      return this.boardId;
   }

   public void setBoardName(String var1) {
      this.boardName = var1;
   }

   public String getBoardName() {
      return this.boardName;
   }
}
    