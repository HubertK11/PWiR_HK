// autor: Cay Horstmann

import java.rmi.*;

public interface Warehouse extends Remote 
{
  double getPrice(String description) throws RemoteException;
}