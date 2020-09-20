class Tester
{

static RoomDTO roomDTO;
static BoardDTO boardDTO;
static ComponentDTO componentDTO;
public static void main(String gg[]) throws DAOException
{
int i=0,j=0,k=0;
for(i=1;i<=10;i++)
{
roomDTO=new RoomDTO();
roomDTO.setName("room"+i);
for(j=1;j<=5;j++)
{
boardDTO=new BoardDTO();
boardDTO.setName("Board"+j);
for(k=1;k<=4;k++)
{
componentDTO=new ComponentDTO();
componentDTO.setName("component"+k);
componentDTO.setRegulatable(true);
componentDTO.setStatus(false);
componentDTO.setMin(0);
componentDTO.setMax(7);
boardDTO.addComponent(componentDTO);
}
roomDTO.addBoard(boardDTO);
}
new RoomDAO().add(roomDTO);
}
}
}
