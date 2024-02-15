javac *.java

move WarehouseClient.class client/
copy Warehouse.class client

copy Warehouse.class myNanoHttpd

move WarehouseImpl.class server/
copy Warehouse.class server
move WarehouseServer.class server/
