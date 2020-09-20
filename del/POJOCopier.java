//package tools;
import java.util.*;
import java.lang.reflect.*;
public class POJOCopier
{
public static void copy(Object target,Object source) throws Throwable
{
Class sourceClass=source.getClass();
Method methods[];
methods=sourceClass.getMethods();
ArrayList<Method> sourceGetters=new ArrayList<>();
for(Method m:methods)
{
if(isGetter(m))
{
sourceGetters.add(m);
}
}
Class targetClass=target.getClass();
methods=targetClass.getMethods();
ArrayList<Method> targetSetters=new ArrayList<>();
int i=0;
Method setterMethod,getterMethod;
while(i<sourceGetters.size())
{
getterMethod=sourceGetters.get(i);
setterMethod=getSetter(getterMethod,targetClass);
if(setterMethod!=null)
{
targetSetters.add(setterMethod);
i++;
}
else
{
sourceGetters.remove(i);
}
}
Method gm,sm;
i=0;
while(i<sourceGetters.size())
{
gm=sourceGetters.get(i);
sm=targetSetters.get(i);
try
{
sm.invoke(target,gm.invoke(source));
}catch(IllegalAccessException illegalAccessException)
{
throw illegalAccessException;
}
catch(InvocationTargetException invocationTargetException)
{
throw invocationTargetException.getCause();
}
i++;
}
}
private static boolean isGetter(Method m)
{
String name=m.getName();
if(!name.startsWith("get")) return false;
if(m.getParameterCount()>0) return false;
if(m.getReturnType().getName().equals("void")) return false;
char next=name.charAt(3);
if(next>=65 && next<=90) return true;
if(next>=97 && next<=122) return false;
return true;
}
private static Method getSetter(Method getterMethod,Class c)
{
String setterName="set"+getterMethod.getName().substring(3);
Class returnType=getterMethod.getReturnType();
try
{
Method setterMethod=c.getMethod(setterName,returnType);
return setterMethod;
}catch(NoSuchMethodException nsme)
{
return null;
}
}
}