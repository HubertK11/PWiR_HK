// autor: Cay Horstmann

import java.rmi.*;
import java.util.*;
import javax.naming.*;


public class WarehouseClient
{
  public static void main(String[] args) throws NamingException, RemoteException
  {
    Context namingContext = new InitialContext();
    String hostName = args[0];
    System.out.println("host Name: " + hostName);
    
    System.out.print("RMI registry bindings: ");
//    Enumeration<NameClassPair> e = namingContext.list("rmi://192.168.100.106/");
    Enumeration<NameClassPair> e = namingContext.list("rmi://" + hostName + "/");
    while ( e.hasMoreElements() )
      System.out.println(e.nextElement().getName() );
      
//    String url = "rmi://192.168.100.106/central_warehouse";
    String url = "rmi://" + hostName + "/central_warehouse";
    Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
    
    String descr = "Blackwell Toaster";
    double price = centralWarehouse.getPrice(descr);
    System.out.println(descr + ": " + price);

    String descr1 = "ZapXpress microwave Oven";
    double price1 = centralWarehouse.getPrice(descr1);
    System.out.println(descr1 + ": " + price1);


    Scanner s = new Scanner( System.in );
    String product = null;
    while (true)
    {
       System.out.print("Name: ");
       descr = s.nextLine();     
       price = centralWarehouse.getPrice(descr);
       System.out.println(descr + ": " + price);
    }
    
  }
}