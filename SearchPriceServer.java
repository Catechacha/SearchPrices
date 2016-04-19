import java.util.concurrent.*;
import java.io.*;
import java.net.*;

public class SearchPriceServer{
	Socket client;
	public static CopyOnWriteArrayList<ConcurrentHashMap<String, Product>> p;

	public static void main(String[] args){
		p=new CopyOnWriteArrayList<ConcurrentHashMap<String,Product>>();
		
		//start thread for update product-list
		UpdateThread u= new UpdateThread(p);
		Thread t=new Thread(u);
		t.start();
		
		//Client handler:
		ExecutorService es = null;
		try(ServerSocket server=new ServerSocket()){
			server.bind(new InetSocketAddress(InetAddress.getLocalHost(),1500));
			es=Executors.newFixedThreadPool(20);
			
			while(true){
				try{
					Socket client=server.accept();
					ClientHandler handler=new ClientHandler(client);
					es.submit(handler);
				}catch(IOException e){
					System.out.println("Some error appeared");
				}
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (es!=null)
				es.shutdown();
		}
	}
}