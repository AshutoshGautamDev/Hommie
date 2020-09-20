package simulator.interfaces;
import simulator.event.*;
public interface StatusListener extends java.io.Serializable
{
public void onStatusChanged(StatusEvent se);
}
