//package simulator.interfaces;
public class StatusEvent
{
private BoardDTOInterface board;
private ComponentDTOInterface component;
private String mode;
public void setBoard(BoardDTOInterface board)
{
this.board=board;
}
public BoardDTOInterface getBoard()
{
return this.board;
}
public void setComponent(ComponentDTOInterface component)
{
this.component=component;
}
public ComponentDTOInterface getComponent()
{
return this.component;
}
public void setMode(String mode)
{
this.mode=mode;
}
public String getMode()
{
return this.mode;
}
}