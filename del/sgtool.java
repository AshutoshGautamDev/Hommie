//package tools;
import java.lang.reflect.*;
import java.awt.datatransfer.*;
import java.awt.*;
class sg extends Thread
{
private String className;
private static Clipboard c;
private static StringBuffer sb;
private static Class target;
private static StringSelection content;
public sg(String className)
{
this.className=className;
start();
}
public void run()
{
try
{
target=Class.forName(this.className);
Field [] fields=target.getDeclaredFields();
sb=new StringBuffer();
String name;
String dataType;
for(Field f:fields)
{
name=f.getName();
dataType=f.getType().getSimpleName();
sb.append("public void set"+name.substring(0,1).toUpperCase()+name.substring(1)+"("+dataType+" "+name+")\n");
sb.append("{\n");
sb.append("this."+name+"="+name+";\n");
sb.append("}\n");
sb.append("public "+dataType+" get"+name.substring(0,1).toUpperCase()+name.substring(1)+"()\n");
sb.append("{\n");
sb.append("return this."+name+";\n");
sb.append("}\n");
}
content=new StringSelection(sb.toString());
c=Toolkit.getDefaultToolkit().getSystemClipboard();
c.setContents(content,content);
this.sleep(10000000);
}catch(Exception cnfe)
{
System.out.println(cnfe.getMessage());
}
}
public static void main(String gg[])
{
sg ab=new sg(gg[0]);
}
}
