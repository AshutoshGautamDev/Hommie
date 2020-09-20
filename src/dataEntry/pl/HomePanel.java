package dataEntry.pl;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import dataEntry.pl.model.*;
import dataEntry.exception.*;
import dataEntry.dl.*;
import pojo.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class HomePanel extends JPanel implements ActionListener,MouseListener
{
//---------------------------------------------------------------------------------------------------
private int mode;
private static final int VIEW_ROOM_MODE=1;
private static final int VIEW_BOARD_MODE=2;
private static final int VIEW_COMPONENT_MODE=3;
private static final int ADD_ROOM_MODE=4;
private static final int EDIT_ROOM_MODE=5;
private static final int DELETE_ROOM_MODE=6;
private static final int ADD_BOARD_MODE=7;
private static final int EDIT_BOARD_MODE=8;
private static final int DELETE_BOARD_MODE=9;
private static final int ADD_COMPONENT_MODE=10;
private static final int EDIT_COMPONENT_MODE=11;
private static final int DELETE_COMPONENT_MODE=12;
private static final int EXPORT_TO_PDF_MODE=13;
//---------------------------------------------------------------------------------------------------
private JLabel moduleTitle;
private JLabel imageLabel;
private JLabel searchCaptionLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private RoomModel rModel;
private JScrollPane rJsp;
private JTable rTable;
private BoardModel bModel;
private JScrollPane bJsp;
private ComponentModel cModel;
private JScrollPane cJsp;
private JTable bTable;
private JTable cTable;
private HomeDetailsPanel homeDetailsPanel;
private JLabel OuterClassRoomCaptionLabel;
private JLabel OuterClassRoomNameLabel;
private JLabel OuterClassBoardNameLabel;
private JLabel OuterClassBoardCaptionLabel;
//---------------------------------------------------------------------------------------------------
public HomePanel()
{
rModel=new RoomModel();
bModel=new BoardModel();
cModel=new ComponentModel();
initComponents();
setAppearance();
addListeners();
setViewRoomMode();
homeDetailsPanel.setViewRoomMode();
}
//---------------------------------------------------------------------------------------------------
public void initComponents()
{
int lm=0;
homeDetailsPanel=new HomeDetailsPanel();
imageLabel= new JLabel(new ImageIcon(this.getClass().getResource("/images/logo.png")));
moduleTitle=new JLabel("TM HOME");

OuterClassRoomCaptionLabel=new JLabel("ROOM :");
OuterClassRoomNameLabel=new JLabel("");
OuterClassBoardNameLabel=new JLabel("");
OuterClassBoardCaptionLabel=new JLabel("BOARD :");

searchCaptionLabel=new JLabel("Search:");
searchErrorLabel=new JLabel("");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));
rTable=new JTable(rModel);
rJsp=new JScrollPane(rTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
bTable=new JTable(bModel);
bJsp=new JScrollPane(bTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
rTable=new JTable(rModel);
rJsp=new JScrollPane(rTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
cTable=new JTable(cModel);
cJsp=new JScrollPane(cTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//---------------------------------------------------------------------------------------------------
setLayout(null);
imageLabel.setBounds(0,5,70,50);
moduleTitle.setBounds(lm+235,5,400,50);
searchCaptionLabel.setBounds(lm+10,60,90,30);
searchTextField.setBounds(lm+70+20,60,400,30);
clearSearchTextFieldButton.setBounds(lm+475+20,60,30,30);
searchErrorLabel.setBounds(lm+510+20,70,75,20);

OuterClassRoomCaptionLabel.setBounds(lm+5+5,95+5,100,20);
OuterClassBoardCaptionLabel.setBounds(lm+100+75+75+25+40,95+5,100,20);
OuterClassRoomNameLabel.setBounds(lm+100+5,95+5,250,20); 
OuterClassBoardNameLabel.setBounds(lm+100+70+100+100+50,95+5,250,20); 

rJsp.setBounds(lm+5,95,600,170+21+30);
bJsp.setBounds(lm+5,95+20+10,600,170+21);
cJsp.setBounds(lm+5,95+20+10,600,170+21);
homeDetailsPanel.setBounds(lm+5,270+50,600,250+80-50);
//---------------------------------------------------------------------------------------------------

add(OuterClassRoomCaptionLabel);
add(OuterClassRoomNameLabel);
add(OuterClassBoardNameLabel);
add(OuterClassBoardCaptionLabel);

add(moduleTitle);
add(imageLabel);
add(searchCaptionLabel);
add(searchTextField);
add(clearSearchTextFieldButton);
add(searchErrorLabel);
add(cJsp);
add(rJsp);
add(bJsp);
add(homeDetailsPanel);
}
//---------------------------------------------------------------------------------------------------
public void addListeners()
{
searchTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
searchErrorLabel.setText("");
if(mode == VIEW_ROOM_MODE) searchR(searchTextField.getText());
if(mode == VIEW_BOARD_MODE) searchB(searchTextField.getText());
if(mode == VIEW_COMPONENT_MODE) searchC(searchTextField.getText());
}
public void removeUpdate(DocumentEvent ev)
{
searchErrorLabel.setText("");
if(mode == VIEW_ROOM_MODE) searchR(searchTextField.getText());
if(mode == VIEW_BOARD_MODE) searchB(searchTextField.getText());
if(mode == VIEW_COMPONENT_MODE) searchC(searchTextField.getText());
}
public void changedUpdate(DocumentEvent ev)
{
searchErrorLabel.setText("");
if(mode == VIEW_ROOM_MODE) searchR(searchTextField.getText());
if(mode == VIEW_BOARD_MODE) searchB(searchTextField.getText());
if(mode == VIEW_COMPONENT_MODE) searchC(searchTextField.getText());
}
});
rTable.addMouseListener(this);
bTable.addMouseListener(this);
cTable.addMouseListener(this);
}
//---------------------------------------------------------------------------------------------------
public void setAppearance()
{
java.awt.Font moduleTitleFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,20);
moduleTitle.setFont(moduleTitleFont);
java.awt.Font font=new java.awt.Font("Verdana",java.awt.Font.PLAIN,20);
java.awt.Font searchErrorFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,12);
searchCaptionLabel.setFont(font);
searchTextField.setFont(font);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(new Color(111,0,0));
java.awt.Font tableTitleFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,16);



OuterClassRoomCaptionLabel.setFont(moduleTitleFont);
OuterClassRoomNameLabel.setFont(moduleTitleFont);
OuterClassBoardNameLabel.setFont(moduleTitleFont);
OuterClassBoardCaptionLabel.setFont(moduleTitleFont);



rTable.setRowHeight(25);
rTable.setFont(font);
rTable.getTableHeader().setFont(tableTitleFont);
rTable.getColumnModel().getColumn(0).setPreferredWidth(100);
rTable.getColumnModel().getColumn(1).setPreferredWidth(560);
rTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
rTable.getTableHeader().setResizingAllowed(false);
rTable.getTableHeader().setReorderingAllowed(false);

bTable.setRowHeight(25);
bTable.setFont(font);
bTable.getTableHeader().setFont(tableTitleFont);
bTable.getColumnModel().getColumn(0).setPreferredWidth(100);
bTable.getColumnModel().getColumn(1).setPreferredWidth(560);
bTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
bTable.getTableHeader().setResizingAllowed(false);
bTable.getTableHeader().setReorderingAllowed(false);

cTable.setRowHeight(25);
cTable.setFont(font);
cTable.getTableHeader().setFont(tableTitleFont);
cTable.getColumnModel().getColumn(0).setPreferredWidth(100);
cTable.getColumnModel().getColumn(1).setPreferredWidth(560);
cTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
cTable.getTableHeader().setResizingAllowed(false);
cTable.getTableHeader().setReorderingAllowed(false);

}
//---------------------------------------------------------------------------------------------------
public void mouseClicked(MouseEvent ev)
{

if(ev.getSource() == rTable)
{
int selectedIndex=rTable.getSelectedRow();
if(selectedIndex==-1) 
{
return;
}
if(ev.getClickCount() == 2)
{
this.setViewBoardMode();
homeDetailsPanel.setViewBoardMode();
RoomDTOInterface r=rModel.getRoomAt(selectedIndex);
homeDetailsPanel.setRoom(r);
setOuterRoom(r);
}
RoomDTOInterface r=rModel.getRoomAt(selectedIndex);
java.util.List<BoardDTOInterface> boards=r.getBoards();
bModel.populate(boards);
homeDetailsPanel.setRoom(r);
}

if(ev.getSource() == bTable)
{
int selectedIndex=bTable.getSelectedRow();
if(selectedIndex==-1) 
{
return;
}
if(ev.getClickCount() == 2)
{
this.setViewComponentMode();
homeDetailsPanel.setViewComponentMode();
BoardDTOInterface b=bModel.getBoardAt(selectedIndex);
homeDetailsPanel.setBoard(b);
setOuterBoard(b);

}
BoardDTOInterface b=bModel.getBoardAt(selectedIndex);
java.util.List<ComponentDTOInterface> components=b.getComponents();
cModel.populate(components);
homeDetailsPanel.setBoard(b);
}

if(ev.getSource() == cTable)
{
int selectedIndex=cTable.getSelectedRow();
if(selectedIndex==-1) 
{
return;
}
ComponentDTOInterface componentDTOInterface= cModel.getComponent(selectedIndex);
homeDetailsPanel.setComponent(componentDTOInterface);
}
}		
//---------------------------------------------------------------------------------------------------
public void mouseEntered(MouseEvent ev){}
public void mouseExited(MouseEvent ev){}
public void mousePressed(MouseEvent ev){}
public void mouseReleased(MouseEvent ev){}
//---------------------------------------------------------------------------------------------------
public void setOuterRoom(RoomDTOInterface room)
{
if(room==null)
{
OuterClassRoomNameLabel.setText("");
OuterClassBoardNameLabel.setText("");
}
else
{
OuterClassRoomNameLabel.setText(room.getName());
OuterClassBoardNameLabel.setText("");
}
}

public void setOuterBoard(BoardDTOInterface board)
{
if(board==null)
{
OuterClassBoardNameLabel.setText("");
}
else
{
OuterClassBoardNameLabel.setText(board.getName());
}
}



public void actionPerformed(ActionEvent ev)
{
searchErrorLabel.setText("");
clearSearchTextFieldButton.setText("");
searchTextField.requestFocus();
}
//---------------------------------------------------------------------------------------------------
private void searchR(String s)
{
int index=rModel.searchRoom(s);
if(s.length()==0) return;
if(index==-1)
{
searchErrorLabel.setText("Not found");
homeDetailsPanel.setRoom(null);
return;
}
else
{
searchErrorLabel.setText("");
rTable.setRowSelectionInterval(index,index);
rTable.scrollRectToVisible(new java.awt.Rectangle(rTable.getCellRect(index,0,false)));
homeDetailsPanel.setRoom(rModel.getRoomAt(index));
} 
}
//---------------------------------------------------------------------------------------------------
private void searchB(String s)
{
if(bModel.getRowCount()==0)
{
return;
}
int index=bModel.searchBoard(s);
if(s.length()==0) return;
if(index==-1)
{
searchErrorLabel.setText("Not found");
homeDetailsPanel.setBoard(null);
return;
}
else
{
searchErrorLabel.setText("");
bTable.setRowSelectionInterval(index,index);
bTable.scrollRectToVisible(new java.awt.Rectangle(bTable.getCellRect(index,0,false)));
homeDetailsPanel.setBoard(bModel.getBoardAt(index));
} 
}
//---------------------------------------------------------------------------------------------------

private void searchC(String s)
{
if(cModel.getRowCount()==0)
{
return;
}
int index=cModel.searchComponent(s);
if(s.length()==0) return;
if(index==-1)
{
searchErrorLabel.setText("Not found");
homeDetailsPanel.setComponent(null);
return;
}
else
{
searchErrorLabel.setText("");
cTable.setRowSelectionInterval(index,index);
cTable.scrollRectToVisible(new java.awt.Rectangle(cTable.getCellRect(index,0,false)));
homeDetailsPanel.setComponent(cModel.getComponent(index));
} 
}

public void setPDFMode()
{
mode=EXPORT_TO_PDF_MODE;
bJsp.setVisible(false);
rJsp.setVisible(true);
cJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setViewRoomMode()
{
mode=VIEW_ROOM_MODE;
if(rModel.getRowCount()==0)
{
searchTextField.setText("");
searchErrorLabel.setText("");
clearSearchTextFieldButton.setEnabled(false);
rTable.setEnabled(false);
searchTextField.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
rTable.setEnabled(true);
}
OuterClassRoomCaptionLabel.setVisible(false);
OuterClassBoardCaptionLabel.setVisible(false);
OuterClassRoomNameLabel.setVisible(false); 
OuterClassBoardNameLabel.setVisible(false); 
bJsp.setVisible(false);
rJsp.setVisible(true);
cJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setAddRoomMode()
{
mode=ADD_ROOM_MODE;
bJsp.setVisible(false);
rJsp.setVisible(true);
cJsp.setVisible(false);
rTable.clearSelection();
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
rTable.setEnabled(false);

}
//---------------------------------------------------------------------------------------------------
public void setEditRoomMode()
{
mode=EDIT_ROOM_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
rTable.setEnabled(false);
bJsp.setVisible(false);
rJsp.setVisible(true);
cJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setDeleteRoomMode()
{
mode=DELETE_ROOM_MODE;
bJsp.setVisible(false);
rJsp.setVisible(true);
cJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setViewBoardMode()
{
mode=VIEW_BOARD_MODE;
if(bModel.getRowCount()==0)
{
searchTextField.setText("");
searchErrorLabel.setText("");
clearSearchTextFieldButton.setEnabled(false);
bTable.setEnabled(false);
searchTextField.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
bTable.setEnabled(true);
}
OuterClassRoomCaptionLabel.setVisible(true);
OuterClassBoardCaptionLabel.setVisible(false);
OuterClassRoomNameLabel.setVisible(true); 
OuterClassBoardNameLabel.setVisible(false); 
cJsp.setVisible(false);
rJsp.setVisible(false);
bJsp.setVisible(true);
}
//---------------------------------------------------------------------------------------------------
public void setAddBoardMode()
{
mode=ADD_BOARD_MODE;
cJsp.setVisible(false);
rJsp.setVisible(false);
bJsp.setVisible(true);
bTable.clearSelection();
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
bTable.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setEditBoardMode()
{
mode=EDIT_BOARD_MODE;
cJsp.setVisible(false);
rJsp.setVisible(false);
bJsp.setVisible(true);
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
bTable.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setDeleteBoardMode()
{
mode=DELETE_BOARD_MODE;
cJsp.setVisible(false);
rJsp.setVisible(false);
bJsp.setVisible(true);
}
//---------------------------------------------------------------------------------------------------
public void setViewComponentMode()
{
mode=VIEW_COMPONENT_MODE;

if(cModel.getRowCount()==0)
{
searchTextField.setText("");
searchErrorLabel.setText("");
clearSearchTextFieldButton.setEnabled(false);
cTable.setEnabled(false);
searchTextField.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
cTable.setEnabled(true);
}
OuterClassRoomCaptionLabel.setVisible(true);
OuterClassBoardCaptionLabel.setVisible(true);
OuterClassRoomNameLabel.setVisible(true); 
OuterClassBoardNameLabel.setVisible(true); 
cJsp.setVisible(true);
rJsp.setVisible(false);
bJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setAddComponentMode()
{
if(cModel.getRowCount()==0)
{
cModel.dPopulate();
}
mode=ADD_COMPONENT_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
cTable.clearSelection();
cTable.setEnabled(false);
cJsp.setVisible(true);
rJsp.setVisible(false);
bJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setEditComponentMode()
{
mode=EDIT_COMPONENT_MODE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
cTable.setEnabled(false);
cJsp.setVisible(true);
rJsp.setVisible(false);
bJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public void setDeleteComponentMode()
{
mode=DELETE_COMPONENT_MODE;
cJsp.setVisible(true);
rJsp.setVisible(false);
bJsp.setVisible(false);
}
//---------------------------------------------------------------------------------------------------
public class HomeDetailsPanel extends JPanel implements ActionListener
{
private RoomDTOInterface room;
private BoardDTOInterface board;
private ComponentDTOInterface component;
private boolean regulatable ;
private int Imin,Imax;

private JTextField roomNameTextField;
private JTextField boardNameTextField;
private JTextField componentNameTextField;
private JTextField componentMinLevelTextField;
private JTextField componentMaxLevelTextField;
private ButtonGroup isRegulatableButtonGroup;
private JRadioButton canRegulateRadioButton;
private JRadioButton canNotRegulateRadioButton;
private JLabel roomNameLabel;
private JLabel boardNameLabel;
private JLabel componentNameLabel;
private JLabel componentRegulatableLabel;
private JLabel componentMinLevelLabel;
private JLabel componentMaxLevelLabel;

private JLabel roomNameErrorLabel;
private JLabel componentNameErrorLabel;
private JLabel boardNameErrorLabel;
private JLabel componentRegulatableErrorLabel;
private JLabel componentMinLevelErrorLabel;
private JLabel componentMaxLevelErrorLabel;

private JLabel roomNameCaptionLabel;
private JLabel boardNameCaptionLabel;
private JLabel componentNameCaptionLabel;
private JLabel componentRegulatableCaptionLabel;
private JLabel componentMinLevelCaptionLabel;
private JLabel componentMaxLevelCaptionLabel;

private JButton clearRoomNameTextFieldButton;
private JButton clearBoardNameTextFieldButton;
private JButton clearComponentNameTextFieldButton;
private JButton clearComponentMinLevelTextFieldButton;
private JButton clearComponentMaxLevelTextFieldButton;

private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton cancelButton;
private JButton exportToPDFButton;
private JButton beginButton;

int lm=0;
int tm=0;
//---------------------------------------------------------------------------------------------------
HomeDetailsPanel()
{
room=null;
board=null;
component=null;
initComponents();
setAppearance();
addListeners();
}
public void initComponents()
{
int lm=0;
int tm=0;

roomNameTextField=new JTextField();
boardNameTextField=new JTextField();
componentNameTextField=new JTextField();
componentMinLevelTextField=new JTextField();
componentMaxLevelTextField=new JTextField();

roomNameLabel=new JLabel("");
boardNameLabel=new JLabel("");
componentNameLabel=new JLabel("");
componentRegulatableLabel=new JLabel("");
componentMinLevelLabel=new JLabel("");
componentMaxLevelLabel=new JLabel("");


roomNameErrorLabel= new JLabel("");
boardNameErrorLabel=new JLabel("");
componentNameErrorLabel=new JLabel("");
componentRegulatableErrorLabel=new JLabel("");
componentMinLevelErrorLabel=new JLabel("");
componentMaxLevelErrorLabel=new JLabel("");

roomNameCaptionLabel=new JLabel("Room  :");
boardNameCaptionLabel=new JLabel("Board  :");
componentNameCaptionLabel=new JLabel("Component :");
componentRegulatableCaptionLabel=new JLabel("Regulatable :");
componentMinLevelCaptionLabel=new JLabel("Min Level  :");
componentMaxLevelCaptionLabel=new JLabel("Max Level  :");

clearRoomNameTextFieldButton = new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));
clearBoardNameTextFieldButton=new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));
clearComponentNameTextFieldButton = new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));
clearComponentMinLevelTextFieldButton = new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));
clearComponentMaxLevelTextFieldButton= new JButton(new ImageIcon(this.getClass().getResource("/images/clear.png")));

isRegulatableButtonGroup= new ButtonGroup();
canRegulateRadioButton= new JRadioButton("Yes");
canNotRegulateRadioButton= new JRadioButton("No");
isRegulatableButtonGroup.add(canRegulateRadioButton);
isRegulatableButtonGroup.add(canNotRegulateRadioButton);

beginButton = new JButton(new ImageIcon(this.getClass().getResource("/images/rev.png")));

addButton=new JButton(new ImageIcon(this.getClass().getResource("/images/add.png")));
editButton=new JButton(new ImageIcon(this.getClass().getResource("/images/edit.png")));
deleteButton=new JButton(new ImageIcon(this.getClass().getResource("/images/delete.png")));
cancelButton=new JButton(new ImageIcon(this.getClass().getResource("/images/cancel.png")));
exportToPDFButton=new JButton(new ImageIcon(this.getClass().getResource("/images/pdf.png")));

//---------------------------------------------------------------------------------------------------
JPanel p1=new JPanel();
p1.setLayout(null);
p1.setBorder(BorderFactory.createLineBorder(Color.gray));
p1.setBounds(140,180+80-60,310,70);
addButton.setBounds(5,10,50,50);
editButton.setBounds(70,10,50,50);
deleteButton.setBounds(130,10,50,50);
cancelButton.setBounds(190,10,50,50);
exportToPDFButton.setBounds(250,10,45,50);
p1.add(addButton);
p1.add(editButton);
p1.add(deleteButton);
p1.add(cancelButton);
p1.add(exportToPDFButton);
beginButton.setBounds(460,180+80-50,50,50);

//---------------------------------------------------------------------------------------------------
roomNameTextField.setBounds(lm+75+70,tm+15,300,25);
roomNameLabel.setBounds(lm+75+70,tm+15,400,20);
roomNameErrorLabel.setBounds(485,tm+15,200,20);
roomNameCaptionLabel.setBounds(lm+15+30+10,tm+15,150,20);
clearRoomNameTextFieldButton.setBounds(lm+450,tm+15,30,25);
//---------------------------------------------------------------------------------------------------
boardNameTextField.setBounds(lm+70+75,tm+10+35,300,25);
boardNameLabel.setBounds(lm+70+75,tm+10+35,400,20);
boardNameErrorLabel.setBounds(485,tm+10+35,200,20);
boardNameCaptionLabel.setBounds(lm+5+40+10,tm+10+35,200,20);
clearBoardNameTextFieldButton.setBounds(lm+450,tm+10+35,30,25);
//---------------------------------------------------------------------------------------------------
componentNameTextField.setBounds(lm+70+75,tm+10+50+10+10-5,300,25);
componentNameLabel.setBounds(lm+70+75,tm+10+50+10+10-5,150,20);
componentNameErrorLabel.setBounds(485,tm+10+50+10+10-5,200,20);
componentNameCaptionLabel.setBounds(lm+7,tm+10+50+10+10-5,200,20);
clearComponentNameTextFieldButton.setBounds(lm+450,tm+10+50+10+10-5,30,25);
//---------------------------------------------------------------------------------------------------
canRegulateRadioButton.setBounds(lm+70+100,tm+10+75+10+10,70,20);
canNotRegulateRadioButton.setBounds(lm+70+200,tm+10+75+10+10,70,20);
componentRegulatableErrorLabel.setBounds(485,tm+10+75+10+10,100+50,20);
componentRegulatableCaptionLabel.setBounds(lm+3,tm+10+75+10+10,200,20);
componentRegulatableLabel.setBounds(lm+70+75,tm+10+75+10+10,400,20);
//---------------------------------------------------------------------------------------------------
componentMinLevelErrorLabel.setBounds(485,tm+10+100+10+10+5,100,20);
componentMinLevelCaptionLabel.setBounds(lm+5+15,tm+10+100+10+10+5,200,20);
componentMinLevelLabel.setBounds(lm+70+75,tm+10+100+10+10+5,400,20);
componentMinLevelTextField.setBounds(lm+70+75,tm+10+100+10+10+5,300,25);
clearComponentMinLevelTextFieldButton.setBounds(lm+450,tm+10+100+10+10+5,30,25);
//---------------------------------------------------------------------------------------------------
componentMaxLevelErrorLabel.setBounds(485,tm+10+100+25+10+10+10,100,20);
componentMaxLevelCaptionLabel.setBounds(lm+15,tm+10+100+25+10+10+10,200,20);
componentMaxLevelLabel.setBounds(lm+70+75,tm+10+100+25+10+10+10,400,20);
componentMaxLevelTextField.setBounds(lm+70+75,tm+10+100+25+10+10+10,300,25);
clearComponentMaxLevelTextFieldButton.setBounds(lm+450,tm+10+100+25+10+10+10,30,25);
//---------------------------------------------------------------------------------------------------
setLayout(null);
add(roomNameCaptionLabel);
add(roomNameLabel);
add(roomNameTextField);
add(roomNameErrorLabel);
add(clearRoomNameTextFieldButton);

add(boardNameCaptionLabel);
add(boardNameLabel);
add(boardNameTextField);
add(boardNameErrorLabel);
add(clearBoardNameTextFieldButton);

add(componentNameCaptionLabel);
add(componentNameLabel);
add(componentNameTextField);
add(componentNameErrorLabel);
add(clearComponentNameTextFieldButton);

add(componentRegulatableErrorLabel);
add(componentRegulatableCaptionLabel);
add(componentRegulatableLabel);
add(canRegulateRadioButton);
add(canNotRegulateRadioButton);
add(componentMinLevelErrorLabel);
add(componentMinLevelCaptionLabel);
add(componentMinLevelLabel);
add(componentMinLevelTextField);
add(clearComponentMinLevelTextFieldButton);
add(componentMaxLevelErrorLabel);
add(componentMaxLevelCaptionLabel);
add(componentMaxLevelLabel);
add(componentMaxLevelTextField);
add(clearComponentMaxLevelTextFieldButton);
add(beginButton);
add(p1);

}
//---------------------------------------------------------------------------------------------------
public void setAppearance()
{

setBorder(BorderFactory.createLineBorder(Color.gray));
java.awt.Font font=new java.awt.Font("Verdana",java.awt.Font.PLAIN,20);
java.awt.Font errorFont=new java.awt.Font("Verdana",java.awt.Font.BOLD,15);
java.awt.Font Tfont=new java.awt.Font("Verdana",java.awt.Font.PLAIN,17);

roomNameLabel.setFont(font);
boardNameLabel.setFont(font);
componentNameLabel.setFont(font);
componentRegulatableLabel.setFont(font);
componentMinLevelLabel.setFont(font);
componentMaxLevelLabel.setFont(font);
roomNameCaptionLabel.setFont(font);
boardNameCaptionLabel.setFont(font);
componentNameCaptionLabel.setFont(font);
componentRegulatableCaptionLabel.setFont(font);
componentMinLevelCaptionLabel.setFont(font);
componentMaxLevelCaptionLabel.setFont(font);
roomNameErrorLabel.setFont(errorFont);
boardNameErrorLabel.setFont(errorFont);
componentNameErrorLabel.setFont(errorFont);
componentRegulatableErrorLabel.setFont(errorFont);
componentMinLevelErrorLabel.setFont(errorFont);
componentMaxLevelErrorLabel.setFont(errorFont);
roomNameErrorLabel.setForeground(new Color(111,0,0));
componentNameErrorLabel.setForeground(new Color(111,0,0));
componentRegulatableErrorLabel.setForeground(new Color(111,0,0));
componentMinLevelErrorLabel.setForeground(new Color(111,0,0));
componentMaxLevelErrorLabel.setForeground(new Color(111,0,0));
boardNameErrorLabel.setForeground(new Color(111,0,0));

roomNameTextField.setFont(Tfont);;
boardNameTextField.setFont(Tfont);;
componentNameTextField.setFont(Tfont);;
componentMinLevelTextField.setFont(Tfont);;
componentMaxLevelTextField.setFont(Tfont);;

}
//---------------------------------------------------------------------------------------------------
public void addListeners()
{
addButton.addActionListener(this);
editButton.addActionListener(this);
deleteButton.addActionListener(this);
cancelButton.addActionListener(this);
beginButton.addActionListener(this);
canNotRegulateRadioButton.addActionListener(this);
canRegulateRadioButton.addActionListener(this);
clearRoomNameTextFieldButton.addActionListener(this);
clearBoardNameTextFieldButton.addActionListener(this);
clearComponentNameTextFieldButton.addActionListener(this);
clearComponentMinLevelTextFieldButton.addActionListener(this);
clearComponentMaxLevelTextFieldButton.addActionListener(this);

roomNameTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
roomNameErrorLabel.setText("");
}
public void removeUpdate(DocumentEvent ev)
{
roomNameErrorLabel.setText("");
}
public void changedUpdate(DocumentEvent ev)
{
roomNameErrorLabel.setText("");
}
});
//---------------------------------------------------------------------------------------------------
boardNameTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
boardNameErrorLabel.setText("");
}
public void removeUpdate(DocumentEvent ev)
{
boardNameErrorLabel.setText("");
}
public void changedUpdate(DocumentEvent ev)
{
boardNameErrorLabel.setText("");
}
});
//---------------------------------------------------------------------------------------------------
componentNameTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
componentNameErrorLabel.setText("");
}
public void removeUpdate(DocumentEvent ev)
{
componentNameErrorLabel.setText("");
}
public void changedUpdate(DocumentEvent ev)
{
componentNameErrorLabel.setText("");
}
});
//---------------------------------------------------------------------------------------------------
componentMinLevelTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
componentMinLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMinLevelTextField.getText());
}catch(Exception e)
{
componentMinLevelErrorLabel.setText("*InValid");
}
}

public void removeUpdate(DocumentEvent ev)
{
componentMinLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMinLevelTextField.getText());
}catch(Exception e)
{
componentMinLevelErrorLabel.setText("*InValid");
}
}

public void changedUpdate(DocumentEvent ev)
{
componentMinLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMinLevelTextField.getText());
}catch(Exception e)
{
componentMinLevelErrorLabel.setText("*InValid");
}
}
});
//---------------------------------------------------------------------------------------------------
componentMaxLevelTextField.getDocument().addDocumentListener(new DocumentListener(){
public void insertUpdate(DocumentEvent ev)
{
componentMaxLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMaxLevelTextField.getText());
}catch(Exception e)
{
componentMaxLevelErrorLabel.setText("*InValid");
}
}

public void removeUpdate(DocumentEvent ev)
{
componentMaxLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMaxLevelTextField.getText());
}catch(Exception e)
{
componentMaxLevelErrorLabel.setText("*InValid");
}
}

public void changedUpdate(DocumentEvent ev)
{
componentMaxLevelErrorLabel.setText("");
try
{
Integer.parseInt(componentMaxLevelTextField.getText());
}catch(Exception e)
{
componentMaxLevelErrorLabel.setText("*InValid");
}
}
});

}
//---------------------------------------------------------------------------------------------------
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==clearRoomNameTextFieldButton)
{
roomNameTextField.setText("");
roomNameErrorLabel.setText("");
}
//---------------------------------------------------------------------------------------------------
if(ev.getSource()==clearBoardNameTextFieldButton)
{
boardNameTextField.setText("");
boardNameErrorLabel.setText("");
}
//---------------------------------------------------------------------------------------------------
if(ev.getSource()==clearComponentNameTextFieldButton)
{
componentNameTextField.setText("");
componentNameErrorLabel.setText("");
}
//---------------------------------------------------------------------------------------------------
if(ev.getSource()==clearComponentMinLevelTextFieldButton)
{
componentMinLevelTextField.setText("");
componentMinLevelErrorLabel.setText("");
}
//---------------------------------------------------------------------------------------------------
if(ev.getSource()==clearComponentMaxLevelTextFieldButton)
{
componentMaxLevelTextField.setText("");
componentMaxLevelErrorLabel.setText("");
}
if(mode == VIEW_ROOM_MODE || mode == ADD_ROOM_MODE  || mode == EDIT_ROOM_MODE || mode == DELETE_ROOM_MODE)
{
if(ev.getSource() == addButton)
{
if(mode==VIEW_ROOM_MODE)
{
HomePanel.this.setAddRoomMode();
this.setAddRoomMode();
}
else
{
String roomName=roomNameTextField.getText().trim();
if(roomName.length()==0)
{
roomNameErrorLabel.setText("*Required");
roomNameTextField.requestFocus();
return;
}
RoomDTOInterface roomDTOInterface = new RoomDTO();
roomDTOInterface.setName(roomName);
try
{
rModel.add(roomDTOInterface);
}catch(DAOException e)
{
JOptionPane.showMessageDialog(this,e.getMessage());
return;
}
int index=rModel.searchRoom(roomName);
rTable.setRowSelectionInterval(index,index);
rTable.scrollRectToVisible(new java.awt.Rectangle(rTable.getCellRect(index,0,false)));
this.setViewBoardMode();
HomePanel.this.setViewBoardMode();
HomePanel.this.setOuterRoom(roomDTOInterface);
this.setRoom(roomDTOInterface);
}
}
if(ev.getSource() == editButton)
{

if(mode==VIEW_ROOM_MODE)
{
int selectedRowIndex=rTable.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an Board to Edit");
return;
}
this.setEditRoomMode();
HomePanel.this.setEditRoomMode();
}
else
{
int selectedRowIndex=rTable.getSelectedRow();
String name=roomNameTextField.getText().trim();
if(name.length()==0)
{
roomNameErrorLabel.setText("*Required");
roomNameTextField.requestFocus();
return;
}
this.room.setName(name);
this.setRoom(room);
try
{
rModel.update(selectedRowIndex,room);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage());
return;
}
rTable.setRowSelectionInterval(selectedRowIndex,selectedRowIndex);
rTable.scrollRectToVisible(new java.awt.Rectangle(rTable.getCellRect(selectedRowIndex,0,false)));
this.setViewRoomMode();
HomePanel.this.setViewRoomMode();
}
}
if(ev.getSource() == deleteButton)
{
int selectedRowIndex=rTable.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an Room to Delete");
return;
}
this.setDeleteRoomMode();
HomePanel.this.setDeleteRoomMode();
int selectedIndex=rTable.getSelectedRow();
RoomDTOInterface roomDTOInterface=rModel.getRoomAt(selectedIndex);
int code=roomDTOInterface.getCode();
String name=roomDTOInterface.getName();
int selectedOption;
selectedOption=JOptionPane.showConfirmDialog(this,"Delete : "+name, "Delete Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
try
{
rModel.remove(code);
bModel.dPopulate();
cModel.dPopulate();
this.setRoom(null);
this.setBoard(null);
this.setComponent(null);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage());
}
this.setViewRoomMode();
HomePanel.this.setViewRoomMode();
}
}
if(ev.getSource() == cancelButton)
{
this.setViewRoomMode();
HomePanel.this.setViewRoomMode();
}
}

if(mode == VIEW_BOARD_MODE || mode == ADD_BOARD_MODE  || mode == EDIT_BOARD_MODE || mode == DELETE_BOARD_MODE)
{
if(ev.getSource() == addButton)
{
if(mode==VIEW_BOARD_MODE)
{
HomePanel.this.setAddBoardMode();
this.setAddBoardMode();
}
else
{
String boardName=boardNameTextField.getText().trim();
if(boardName.length()==0)
{
boardNameErrorLabel.setText("*Required");
boardNameTextField.requestFocus();
return;
}
BoardDTOInterface boardDTOInterface = new BoardDTO();
boardDTOInterface.setName(boardName);
try
{
bModel.add(boardDTOInterface,this.room);
}catch(Exception exception)
{
JOptionPane.showMessageDialog(this,exception.getMessage());
return;
}
rModel.populate(); 
int index=bModel.searchBoard(boardName);
bTable.setRowSelectionInterval(index,index);
bTable.scrollRectToVisible(new java.awt.Rectangle(bTable.getCellRect(index,0,false)));
this.setViewComponentMode();
HomePanel.this.setViewComponentMode();
this.setBoard(boardDTOInterface);
HomePanel.this.setOuterBoard(boardDTOInterface);

}
}

if(ev.getSource() == editButton)
{
if(mode==VIEW_BOARD_MODE)
{
int selectedRowIndex=bTable.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an Board to Edit");
return;
}
this.setEditBoardMode();
HomePanel.this.setEditBoardMode();
}
else
{
int selectedRowIndex=bTable.getSelectedRow();
String name=boardNameTextField.getText().trim();
if(name.length()==0)
{
boardNameErrorLabel.setText("*Required");
boardNameTextField.requestFocus();
return;
}
String oldName=this.board.getName();
this.board.setName(name);
this.setBoard(board);
try
{
bModel.update(selectedRowIndex,board,this.room,oldName);
}catch(Exception e)
{
e.printStackTrace();
}
bTable.setRowSelectionInterval(selectedRowIndex,selectedRowIndex);
bTable.scrollRectToVisible(new java.awt.Rectangle(bTable.getCellRect(selectedRowIndex,0,false)));
this.setViewBoardMode();
HomePanel.this.setViewBoardMode();
}
}

if(ev.getSource() == deleteButton)
{
int selectedRowIndex=bTable.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an Board to Delete");
return;
}
this.setDeleteBoardMode();
HomePanel.this.setDeleteBoardMode();
int selectedIndex=bTable.getSelectedRow();
BoardDTOInterface boardDTOInterface=bModel.getBoardAt(selectedIndex);
int code=boardDTOInterface.getCode();
String name=boardDTOInterface.getName();
int selectedOption;
selectedOption=JOptionPane.showConfirmDialog(this,"Delete : "+name, "Delete Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
try
{
bModel.remove(this.room,boardDTOInterface);
cModel.dPopulate();
this.setBoard(null);
this.setComponent(null);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage());
}
this.setViewBoardMode();
HomePanel.this.setViewBoardMode();
}
}
if(ev.getSource() == cancelButton)
{
this.setViewBoardMode();
HomePanel.this.setViewBoardMode();
}
}

if(mode == VIEW_COMPONENT_MODE || mode == ADD_COMPONENT_MODE  || mode == EDIT_COMPONENT_MODE || mode == DELETE_COMPONENT_MODE)
{
if(ev.getSource() == addButton)
{
if(mode==VIEW_COMPONENT_MODE)
{
this.setAddComponentMode();
HomePanel.this.setAddComponentMode();
}
else
{
Object firstInvalidComponent=null;
String componentName=componentNameTextField.getText().trim();
String min =componentMinLevelTextField.getText().trim();
String max =componentMaxLevelTextField.getText().trim();
if(componentName.length()==0)
{
componentNameErrorLabel.setText("*Required");
firstInvalidComponent=(Object)componentNameTextField;
}
if(canRegulateRadioButton.isSelected()==false && canNotRegulateRadioButton.isSelected()==false)
{
componentRegulatableErrorLabel.setText("*Required");
if(firstInvalidComponent==null) firstInvalidComponent=(Object)isRegulatableButtonGroup;
}
if(min.length()==0)
{
componentMinLevelErrorLabel.setText("*Required");
if(firstInvalidComponent==null) firstInvalidComponent=(Object)componentMinLevelTextField;
}
if(max.length()==0)
{
componentMaxLevelErrorLabel.setText("*Required");
if(firstInvalidComponent==null) firstInvalidComponent=(Object)componentMaxLevelTextField;
}
if(firstInvalidComponent != null)
{
if(!(firstInvalidComponent instanceof JTextField))
{
return;
}
else
{
JTextField jTextField=(JTextField)firstInvalidComponent;
jTextField.requestFocus();
return;
}
}
JTextField invalid=null;
try
{
Imin=Integer.parseInt(min);
}catch(Exception e)
{
componentMinLevelErrorLabel.setText("*InValid");
invalid=componentMinLevelTextField;
}
try
{
Imax=Integer.parseInt(max);
}catch(Exception e)
{
componentMaxLevelErrorLabel.setText("*InValid");
if(invalid==null) invalid=componentMaxLevelTextField;
}
if(invalid != null)
{
invalid.requestFocus();
return;
}
if(Imin != 0 && Imax !=0)
{
if(Imin > Imax)
{
componentMinLevelTextField.requestFocus();
componentMinLevelErrorLabel.setText("*InValid");
return;
}
if(Imax <= Imin)
{
componentMaxLevelTextField.requestFocus();
componentMaxLevelErrorLabel.setText("*Invalid");
return;
}
}
ComponentDTOInterface componentDTOInterface = new ComponentDTO();
componentDTOInterface.setName(componentName);
componentDTOInterface.setRegulatable(regulatable);
componentDTOInterface.setMin(Imin);
componentDTOInterface.setMax(Imax);
try
{
cModel.add(componentDTOInterface,this.board,this.room);
}catch(Exception exception)
{
JOptionPane.showMessageDialog(this,exception.getMessage());
return;
}
int index=cModel.searchComponent(componentName);
rModel.populate();
cTable.setRowSelectionInterval(index,index);
cTable.scrollRectToVisible(new java.awt.Rectangle(cTable.getCellRect(index,0,false)));
this.setViewComponentMode();
HomePanel.this.setViewComponentMode();
this.setComponent(componentDTOInterface);
}
}

if(ev.getSource() == editButton)
{
if(mode==VIEW_COMPONENT_MODE)
{
int selectedRowIndex=cTable.getSelectedRow();
if(selectedRowIndex<0)
{
JOptionPane.showMessageDialog(this,"Select an Component to Edit");
return;
}
this.setEditComponentMode();
HomePanel.this.setEditComponentMode();
}
else
{
int selectedRowIndex=cTable.getSelectedRow();
Object firstInvalidComponent=null;
String componentName=componentNameTextField.getText().trim();
String min =componentMinLevelTextField.getText().trim();
String max =componentMaxLevelTextField.getText().trim();
if(componentName.length()==0)
{
componentNameErrorLabel.setText("*Required");
firstInvalidComponent=(Object)componentNameTextField;
}
if(min.length()==0)
{
componentMinLevelErrorLabel.setText("*Required");
if(firstInvalidComponent==null) firstInvalidComponent=(Object)componentMinLevelTextField;
}
if(max.length()==0)
{
componentMaxLevelErrorLabel.setText("*Required");
if(firstInvalidComponent==null) firstInvalidComponent=(Object)componentMaxLevelTextField;
}
if(firstInvalidComponent != null)
{
JTextField jTextField=(JTextField)firstInvalidComponent;
jTextField.requestFocus();
return;
}
if(Imin != 0 && Imax !=0)
{
if(Imin > Imax)
{
componentMinLevelTextField.requestFocus();
componentMinLevelErrorLabel.setText("*InValid");
return;
}
if(Imax <= Imin)
{
componentMaxLevelTextField.requestFocus();
componentMaxLevelErrorLabel.setText("*Invalid");
return;
}
}
String oldName=this.component.getName();
this.component.setName(componentName);
this.component.setRegulatable(regulatable);
this.component.setMin(Imin);
this.component.setMax(Imax);
this.setComponent(this.component);
try
{
cModel.update(selectedRowIndex,this.component,this.board,this.room,oldName);
}catch(Exception e){}
cTable.setRowSelectionInterval(selectedRowIndex,selectedRowIndex);
cTable.scrollRectToVisible(new java.awt.Rectangle(cTable.getCellRect(selectedRowIndex,0,false)));
this.setViewComponentMode();
HomePanel.this.setViewComponentMode();
}
}

if(ev.getSource() == deleteButton)
{
int selectedIndex=cTable.getSelectedRow();
if(selectedIndex<0)
{
JOptionPane.showMessageDialog(this,"Select a Component to Delete");
return;
}
this.setDeleteComponentMode();
HomePanel.this.setDeleteComponentMode();
ComponentDTOInterface componentDTOInterface=cModel.getComponent(selectedIndex);
int code=componentDTOInterface.getCode();
String name=componentDTOInterface.getName();
int selectedOption;
selectedOption=JOptionPane.showConfirmDialog(this,"Delete : "+name, "Delete Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
try
{
cModel.remove(this.room,this.board,componentDTOInterface);
this.setComponent(null);
}catch(Exception e)
{
JOptionPane.showMessageDialog(this,e.getMessage());
}
this.setViewComponentMode();
HomePanel.this.setViewComponentMode();
}
}
if(ev.getSource() == cancelButton)
{
this.setViewComponentMode();
HomePanel.this.setViewComponentMode();
}
}

if(ev.getSource() == beginButton)
{
if(mode==VIEW_BOARD_MODE)
{
this.setViewRoomMode();
HomePanel.this.setViewRoomMode();
}
if(mode==VIEW_COMPONENT_MODE)
{
this.setViewBoardMode();
HomePanel.this.setViewBoardMode();
}
}

if(ev.getSource() == exportToPDFButton)
{
this.setPDFMode();
HomePanel.this.setPDFMode();
}

if(ev.getSource()==canRegulateRadioButton)
{
regulatable=true;
componentRegulatableErrorLabel.setText("");
componentMinLevelTextField.setText("");
componentMaxLevelTextField.setText("");
componentMaxLevelErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMinLevelTextField.setEditable(true);
componentMaxLevelTextField.setEditable(true);
clearComponentMinLevelTextFieldButton.setEnabled(true);
clearComponentMaxLevelTextFieldButton.setEnabled(true);
}
//---------------------------------------------------------------------------------------------------
if(ev.getSource()==canNotRegulateRadioButton)
{
regulatable=false;
componentRegulatableErrorLabel.setText("");
componentMaxLevelErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMinLevelTextField.setText("0");
componentMaxLevelTextField.setText("0");
componentMinLevelTextField.setEditable(false);
componentMaxLevelTextField.setEditable(false);
clearComponentMinLevelTextFieldButton.setEnabled(false);
clearComponentMaxLevelTextFieldButton.setEnabled(false);
}

}
//---------------------------------------------------------------------------------------------------
public void setRoom(RoomDTOInterface room)
{
this.room=room;
if(this.room==null)
{
roomNameLabel.setText("");
boardNameLabel.setText("");
componentNameLabel.setText("");
componentRegulatableLabel.setText("");
componentMinLevelLabel.setText("");
componentMaxLevelLabel.setText("");
}
else
{
roomNameLabel.setText(room.getName());
boardNameLabel.setText("");
componentNameLabel.setText("");
componentRegulatableLabel.setText("");
componentMinLevelLabel.setText("");
componentMaxLevelLabel.setText("");
}
}

public void setBoard(BoardDTOInterface board)
{
this.board=board;
if(this.board==null)
{
boardNameLabel.setText("");
componentNameLabel.setText("");
componentRegulatableLabel.setText("");
componentMinLevelLabel.setText("");
componentMaxLevelLabel.setText("");
}
else
{
boardNameLabel.setText(board.getName());
componentNameLabel.setText("");
componentRegulatableLabel.setText("");
componentMinLevelLabel.setText("");
componentMaxLevelLabel.setText("");
}
}
public void setComponent(ComponentDTOInterface  component)
{
this.component=component;
if(this.component==null)
{
componentNameLabel.setText("");
componentRegulatableLabel.setText("");
componentMinLevelLabel.setText("");
componentMaxLevelLabel.setText("");
}
else
{
componentNameLabel.setText(component.getName());
componentRegulatableLabel.setText(component.getRegulatable()+"");
componentMinLevelLabel.setText(component.getMin()+"");
componentMaxLevelLabel.setText(component.getMax()+"");
}
}
//---------------------------------------------------------------------------------------------------
public void setViewRoomMode()
{
mode=VIEW_ROOM_MODE;
clearRoomNameTextFieldButton.setVisible(false);
clearBoardNameTextFieldButton.setVisible(false);
clearComponentNameTextFieldButton.setVisible(false);
clearComponentMinLevelTextFieldButton.setVisible(false);
clearComponentMaxLevelTextFieldButton.setVisible(false);

/*
roomNameCaptionLabel.setVisible(true);
boardNameCaptionLabel.setVisible(false);
componentNameCaptionLabel.setVisible(false);
componentRegulatableCaptionLabel.setVisible(false);
componentMinLevelCaptionLabel.setVisible(false);
componentMaxLevelCaptionLabel.setVisible(false);
*/


roomNameTextField.setVisible(false);
boardNameTextField.setVisible(false);
componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

roomNameErrorLabel.setText("");
boardNameErrorLabel.setText("");
componentNameErrorLabel.setText("");
componentRegulatableErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMaxLevelErrorLabel.setText("");

roomNameLabel.setVisible(true);
boardNameLabel.setVisible(false);
componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);


addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/add.png")));
editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/edit.png")));

beginButton.setVisible(false);

addButton.setEnabled(true);
cancelButton.setEnabled(false);

if(rModel.getRowCount()==0)
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
else
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}	
}
//---------------------------------------------------------------------------------------------------
public void setAddRoomMode()
{
mode=ADD_ROOM_MODE;
roomNameTextField.setVisible(true);
roomNameTextField.setText("");
clearRoomNameTextFieldButton.setVisible(true);
roomNameLabel.setVisible(false);


addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));
addButton.setEnabled(true);
cancelButton.setEnabled(true);

editButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
deleteButton.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setEditRoomMode()
{
mode=EDIT_ROOM_MODE;
roomNameTextField.setText(this.room.getName());

roomNameLabel.setVisible(false);
boardNameLabel.setVisible(false);
componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

clearRoomNameTextFieldButton.setVisible(true);
clearBoardNameTextFieldButton.setVisible(false);
clearComponentNameTextFieldButton.setVisible(false);
clearComponentMinLevelTextFieldButton.setVisible(false);
clearComponentMaxLevelTextFieldButton.setVisible(false);

roomNameTextField.setVisible(true);
boardNameTextField.setVisible(false);
componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));

cancelButton.setEnabled(true);
editButton.setEnabled(true);
addButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
deleteButton.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setDeleteRoomMode()
{
mode=DELETE_ROOM_MODE;
}
//---------------------------------------------------------------------------------------------------
public void setViewBoardMode()
{
mode=VIEW_BOARD_MODE;
clearRoomNameTextFieldButton.setVisible(false);
clearBoardNameTextFieldButton.setVisible(false);
clearComponentNameTextFieldButton.setVisible(false);
clearComponentMinLevelTextFieldButton.setVisible(false);
clearComponentMaxLevelTextFieldButton.setVisible(false);
boardNameLabel.setVisible(true);
roomNameTextField.setVisible(false);
boardNameTextField.setVisible(false);
componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

/*
boardNameCaptionLabel.setVisible(true);
componentNameCaptionLabel.setVisible(false);
componentRegulatableCaptionLabel.setVisible(false);
componentMinLevelCaptionLabel.setVisible(false);
componentMaxLevelCaptionLabel.setVisible(false);
*/

componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/add.png")));
editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/edit.png")));

if(bModel.getRowCount()==0)
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
}
else
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
}	
addButton.setEnabled(true);
cancelButton.setEnabled(false);
beginButton.setVisible(true);
exportToPDFButton.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setAddBoardMode()
{
mode=ADD_BOARD_MODE;
if(this.room.getBoards().size()==0)
{
HomePanel.this.bModel.dPopulate();
}

roomNameTextField.setVisible(false);
clearRoomNameTextFieldButton.setVisible(false);
roomNameLabel.setVisible(true);

boardNameTextField.setText("");
boardNameTextField.setVisible(true);
clearBoardNameTextFieldButton.setVisible(true);

componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

roomNameErrorLabel.setText("");
boardNameErrorLabel.setText("");
componentNameErrorLabel.setText("");
componentRegulatableErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMaxLevelErrorLabel.setText("");

roomNameLabel.setVisible(true);
boardNameLabel.setVisible(false);
componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

beginButton.setVisible(false);

addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));
addButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
cancelButton.setEnabled(true);
}
//---------------------------------------------------------------------------------------------------
public void setEditBoardMode()
{
mode=EDIT_BOARD_MODE;
boardNameTextField.setText(this.board.getName());

roomNameLabel.setVisible(true);
boardNameLabel.setVisible(false);
componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

clearRoomNameTextFieldButton.setVisible(false);
clearBoardNameTextFieldButton.setVisible(true);
clearComponentNameTextFieldButton.setVisible(false);
clearComponentMinLevelTextFieldButton.setVisible(false);
clearComponentMaxLevelTextFieldButton.setVisible(false);

boardNameTextField.setVisible(true);
roomNameTextField.setVisible(false);
componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

beginButton.setVisible(false);
editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));
addButton.setEnabled(false);
cancelButton.setEnabled(true);
editButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
deleteButton.setEnabled(false);
}
public void setDeleteBoardMode()
{
mode=DELETE_BOARD_MODE;
}
//---------------------------------------------------------------------------------------------------
public void setViewComponentMode()
{
mode=VIEW_COMPONENT_MODE;
clearRoomNameTextFieldButton.setVisible(false);
clearBoardNameTextFieldButton.setVisible(false);
clearComponentNameTextFieldButton.setVisible(false);
clearComponentMinLevelTextFieldButton.setVisible(false);
clearComponentMaxLevelTextFieldButton.setVisible(false);

roomNameTextField.setVisible(false);
boardNameTextField.setVisible(false);
componentNameTextField.setVisible(false);
componentMinLevelTextField.setVisible(false);
componentMaxLevelTextField.setVisible(false);
canRegulateRadioButton.setVisible(false);
canNotRegulateRadioButton.setVisible(false);

/*
componentNameCaptionLabel.setVisible(true);
componentRegulatableCaptionLabel.setVisible(true);
componentMinLevelCaptionLabel.setVisible(true);
componentMaxLevelCaptionLabel.setVisible(true);
*/

addButton.setEnabled(true);
cancelButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
beginButton.setVisible(true);

roomNameErrorLabel.setText("");
boardNameErrorLabel.setText("");
componentNameErrorLabel.setText("");
componentRegulatableErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMaxLevelErrorLabel.setText("");

roomNameLabel.setVisible(true);
boardNameLabel.setVisible(true);
componentNameLabel.setVisible(true);
componentRegulatableLabel.setVisible(true);
componentMinLevelLabel.setVisible(true);
componentMaxLevelLabel.setVisible(true);

addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/add.png")));
editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/clear.png")));

if(cModel.getRowCount()==0)
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
}
else
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
}

}
//---------------------------------------------------------------------------------------------------
public void setAddComponentMode()
{
mode=ADD_COMPONENT_MODE;
if(this.board.getComponents().size()==0)
{
HomePanel.this.cModel.dPopulate();
}

roomNameLabel.setText(this.room.getName());
boardNameLabel.setText(this.board.getName());

clearComponentMinLevelTextFieldButton.setEnabled(true);
clearComponentMaxLevelTextFieldButton.setEnabled(true);
componentMinLevelTextField.setEnabled(true);
componentMaxLevelTextField.setEnabled(true);

componentNameTextField.setText("");
componentMinLevelTextField.setText("");
componentMaxLevelTextField.setText("");
isRegulatableButtonGroup.clearSelection();

componentNameErrorLabel.setText("");
componentRegulatableErrorLabel.setText("");
componentMinLevelErrorLabel.setText("");
componentMaxLevelErrorLabel.setText("");

componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

clearComponentNameTextFieldButton.setVisible(true);
clearComponentMinLevelTextFieldButton.setVisible(true);
clearComponentMaxLevelTextFieldButton.setVisible(true);

componentNameTextField.setVisible(true);
componentMinLevelTextField.setVisible(true);
componentMaxLevelTextField.setVisible(true);
canRegulateRadioButton.setVisible(true);
canNotRegulateRadioButton.setVisible(true);

beginButton.setVisible(false);

addButton.setIcon(new ImageIcon(this.getClass().getResource("/images/save.png")));
addButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
cancelButton.setEnabled(true);
}
//---------------------------------------------------------------------------------------------------
public void setEditComponentMode()
{
mode=EDIT_COMPONENT_MODE;
componentNameTextField.setText(this.component.getName());
if(this.component.getRegulatable() == true)
{
canRegulateRadioButton.setSelected(true);
}
else
{
canNotRegulateRadioButton.setSelected(true);
}
roomNameLabel.setVisible(true);
boardNameLabel.setVisible(true);
componentNameLabel.setVisible(false);
componentRegulatableLabel.setVisible(false);
componentMinLevelLabel.setVisible(false);
componentMaxLevelLabel.setVisible(false);

boardNameTextField.setVisible(false);
roomNameTextField.setVisible(false);
componentNameTextField.setVisible(true);
componentMinLevelTextField.setVisible(true);
componentMaxLevelTextField.setVisible(true);
canRegulateRadioButton.setVisible(true);
canNotRegulateRadioButton.setVisible(true);

clearComponentNameTextFieldButton.setVisible(true);
clearComponentMinLevelTextFieldButton.setVisible(true);
clearComponentMaxLevelTextFieldButton.setVisible(true);
if(this.component.getRegulatable() == true)
{
componentMinLevelTextField.setText(component.getMin()+"");
componentMaxLevelTextField.setText(component.getMax()+"");
componentMinLevelTextField.setEnabled(true);
componentMaxLevelTextField.setEnabled(true);
}
else
{
componentMinLevelTextField.setText("0");
componentMaxLevelTextField.setText("0");
componentMinLevelTextField.setEditable(false);
componentMaxLevelTextField.setEditable(false);
}
beginButton.setVisible(false);
editButton.setIcon(new ImageIcon(this.getClass().getResource("/images/clear.png")));
addButton.setEnabled(false);
cancelButton.setEnabled(true);
editButton.setEnabled(true);
exportToPDFButton.setEnabled(false);
deleteButton.setEnabled(false);
}
//---------------------------------------------------------------------------------------------------
public void setDeleteComponentMode()
{
mode=DELETE_COMPONENT_MODE;
}
//---------------------------------------------------------------------------------------------------
public void setPDFMode()
{
mode=EXPORT_TO_PDF_MODE;
}
}
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 