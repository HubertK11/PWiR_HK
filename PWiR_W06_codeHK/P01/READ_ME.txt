1. Pobranie i uruchomienie serwera nanoHTTP (wymagane jest uprzednie zainstalowanie aplikacji git)

git clone https://github.com/NanoHttpd/nanohttpd.git myNanoHttpd

repozytorium utworzone jest w katalogu myNanoHttpd.
Wchodzimy do katalogu myNanoHttpd i wydajemy polecena:

    mvn compile
    mvn exec:java -pl webserver -Dexec.mainClass="org.nanohttpd.webserver.SimpleWebServer"

keytool -genkey -keyalg RSA -alias selfsigned -keystore keystore.jks -storepass password -validity 360 -keysize 2048 -ext SAN=DNS:pwir,IP:109.95.27.19  -validity 9999
    
UWAGA! Wymagane jest wcześniejsze zainstalowanie aplikacji maven

Umieszczamy w katalogu myNanoHttpd pliki class, które chcemy udostępnić. W tym przykładzie plik Worehouse.class
Serwer jest dostępny pod adresem: http://localhost:8080/


2. Kompilujemy źródła

compile.bat


3. Uruchomienie rmiregister
UWAGA. Proszę sprawdzić wersję Javy. W przykładzie jest użyta JDK-19.

run_rmiregistry.bat

opcja -J umożliwia użycie jako parametru opcji JVM java, w naszym przypadku wskazania sposobu dostępu (poprzez serwer http) oraz położenia plików klas:
-Djava.rmi.server.codebase=http://localhost:8080/

4. Uruchomienie serwera w katalogu servera

java WarehouseServer


5. Uruchomienie klienta w katalogu client

java WarehouseClient  localhost



Zawartość katalogów:

server:
* Warehouse.class  
* WarehouseImpl.class  
* WarehouseServer.class

client:
* Warehouse.class  
* WarehouseClient.class

myNanoHttpd:
* Warehouse.class  