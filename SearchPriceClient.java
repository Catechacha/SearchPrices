import java.io.*;
import java.net.*;
import java.util.*;

public class SearchPriceClient {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try(Socket socket=new Socket(InetAddress.getLocalHost(),1500);
				ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
				BufferedReader localIn= new BufferedReader(new InputStreamReader(System.in));)
		{			
			ArrayList<Product> p=null;
			String option=null;
			System.out.println("WELCOME TO SEARCH-PRICES: give me a product");
			while(!(option=localIn.readLine()).equals("exit")){
				out.writeUnshared(option);//send a request for a product
				out.flush();
				p=(ArrayList<Product>) in.readUnshared();//receive a reply
				//print result:
				if(p.size()==0)
					System.out.println("Product not found");
				else
					printProductList(p);
				System.out.println("Give me another product");
			}
		}catch(UnknownHostException e){System.err.println("Unknown host");}
		catch(IOException e){System.err.println("Error: "+e.getMessage());}
		catch(ClassNotFoundException e){System.err.println("Class not found "+ e.getMessage());}
	}

	private static void printProductList(ArrayList<Product> p){
		for(Product pp:p)
			System.out.println(pp.getSeller()+" "+pp.getProducer()+" "+pp.getName()+" "+pp.getPrice());
	}
}