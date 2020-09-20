//package dataEntry.util;
import java.io.*;
public class Sequence
{
private Sequence()
{
}
public static long next(String entity) throws SequenceException
{
try
{
File file = new File("sequence.seq");
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file,"rw");
String vEntity="";
long vCode=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vEntity=randomAccessFile.readLine();
vCode=Long.parseLong(randomAccessFile.readLine());
if(vEntity.equalsIgnoreCase(entity))
{
found=true;
break;
}
}
if(found==false)
{
vCode=1;
randomAccessFile.writeBytes(entity);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(vCode));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
}
else
{
vCode++;
randomAccessFile.seek(0);
File tmpFile = new File("tmp.data");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile,"rw");
String tEntity;
long tCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tEntity=randomAccessFile.readLine();
tCode=Long.parseLong(randomAccessFile.readLine());
if(tEntity.equalsIgnoreCase(entity))
{
tmpRandomAccessFile.writeBytes(entity);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(vCode));
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(tEntity);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(String.valueOf(tCode));
tmpRandomAccessFile.writeBytes("\n");
}
}
tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}
return vCode;
}catch(IOException ioException)
{
System.out.println(ioException);
throw new SequenceException("Cannot generate next code for :"+entity);
}
}
}
