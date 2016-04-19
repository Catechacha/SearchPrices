import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class UpdateList implements Callable<ConcurrentHashMap<String,Product>>{
	public ConcurrentHashMap<String, Product> p;
	public int port;
	
	Socket s=new Socket();
		
	public UpdateList(int p) {
		this.port=p;
	}

	@SuppressWarnings("unchecked")
	public ConcurrentHashMap<String, Product> call(){
		try{
			s.connect(new InetSocketAddress(InetAddress.getLocalHost(),this.port));//connect
			s.setSoTimeout(27000);
			s.setTcpNoDelay(true);
		}catch(IOException e){e.printStackTrace();}
		try(BufferedWriter out=new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream()));
				ObjectInputStream in=new ObjectInputStream(this.s.getInputStream());){
			out.write("UpdateList\r\n");//send request to update
			out.flush();
			p=(ConcurrentHashMap<String, Product>) in.readObject();//receive the list of a seller
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{try {s.close();} catch (IOException e) {e.printStackTrace();}
		}
		return p; //return the list received
	}
}