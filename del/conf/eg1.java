import java.io.*;
import com.google.gson.*;
import java.net.*;
class Wifi
{
public String ssid;
public String password;
}

class psp
{
public static void main(String args[])
{
Wifi wifi = new Wifi();
String wifiString=takeInput(wifi);
sendToServer(wifiString);
}

public static String takeInput(Wifi wifi)
{
try
{
InputStreamReader isr = new InputStreamReader(System.in);
BufferedReader br=new BufferedReader(isr);
String ssid,password;
System.out.print("Enter SSID:");
wifi.ssid=(String)br.readLine();
System.out.print("Enter Password:");
wifi.password=(String)br.readLine();
String wifiString=new Gson().toJson(wifi);
return wifiString+"!";
}catch(Exception e)
{
e.printStackTrace();
return "";
}
}
public static void sendToServer(String wifiJson)
{
try
{
System.out.println(wifiJson);
byte bytes[]=wifiJson.getBytes();
System.out.println(bytes.length);
Socket socket = new Socket("192.168.4.1",1527);
OutputStream  os= socket.getOutputStream();
os.write(bytes);
os.flush();
socket.close();
}catch(Exception e)
{
e.printStackTrace();
}
}
}