import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;

public class ClientHandler implements Runnable{
	Socket client;
	CopyOnWriteArrayList<ConcurrentHashMap<String, Product>> plist;
	
	public ClientHandler(Socket client){
		this.client = client;
	}
	
	@Override
	public void run(){
		try(ObjectInputStream in= new ObjectInputStream(client.getInputStream());
			ObjectOutputStream out= new ObjectOutputStream(client.getOutputStream());){
				while(true){
					String pname=(String) in.readUnshared();
					//creation of arraylist which contains the prices of the product requested
					ArrayList<Product> reply = new ArrayList<Product>();
					for(ConcurrentHashMap<String,Product> x :UpdateThread.p){
						Product e=x.get(pname);
						if(e!=null)
							reply.add(e);
					}
					Collections.sort(reply);//order: low price --> high price
					out.writeUnshared(reply);//send the arraylist
					out.flush();
				}
		}catch(IOException e){
			System.out.println("Client closed connection or an error appeared");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}