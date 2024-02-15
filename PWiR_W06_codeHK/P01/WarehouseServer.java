// autor: Cay Horstmann

import java.rmi.*;
import javax.naming.*;

public class WarehouseServer 
{
  public static void main(String[] args) throws RemoteException, NamingException 
  {
    System.out.println("Constructing server inplementation...");
    WarehouseImpl centralWarehouse = new WarehouseImpl();
    
    System.out.println("Binding server impoementation to registry...");
    Context namingContext = new InitialContext();
    namingContext.rebind("rmi:central_warehouse", centralWarehouse);
    
    System.out.println("Waiting for invoications from clients...");
  }
}