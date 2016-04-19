import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Seller{
	public static int port;
	public static ConcurrentHashMap<String,Product> list=new ConcurrentHashMap<String,Product>();
	public static String name;
	
	public static void setList(ConcurrentHashMap<String,Product> list){//to fill product list
		switch(Seller.port){
			case 1502:
				list.put("GalaxyGrandNeo",new Product("Samsung","GalaxyGrandNeo","120.50",name));
				list.put("Iphone6",new Product("Apple","Iphone6","500",name));
				list.put("P9",new Product("Huawei","P9","450.50",name));
				list.put("Lumia550",new Product("Nokia","Lumia550","139",name));
				break;
			case 1503:
				list.put("GalaxyGrandNeo",new Product("Samsung","GalaxyGrandNeo","133",name));
				list.put("Iphone6",new Product("Apple","Iphone6","505",name));
				list.put("Lumia550",new Product("Nokia","Lumia550","145",name));
				break;
			case 1504:
				list.put("GalaxyGrandNeo",new Product("Samsung","GalaxyGrandNeo","156",name));
				list.put("Lumia550",new Product("Nokia","Lumia550","190",name));
				break;
		}
	}

	private static void setListChanged1502() { //changes to make first seller-list
		list.clear();
		list.put("GalaxyGrandNeo",new Product("Samsung","GalaxyGrandNeo","125.50",name));
		list.put("Iphone6",new Product("Apple","Iphone6","554.33",name));
	}
	
	private static void setListChanged1503() {//changes to make second seller-list
		list.clear();
		list.put("ZenPhone",new Product("Asus","ZenPhone","300",name));
		list.put("Iphone6",new Product("Apple","Iphone6","553",name));
	}
	
	private static void setListChanged1504() {//changes to make third seller-list
		list.clear();
		list.put("ZenPhone",new Product("Asus","ZenPhone","125",name));
	}

	public static void main(String[] args){
		/*receive from input name of the seller and port*/
		BufferedReader localIn= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give me a port (1502,1503,1504)");
		try {
			Seller.port=Integer.parseInt(localIn.readLine());
			System.out.println("Give me a name");
			Seller.name=localIn.readLine();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		setList(list);/*set the product list*/
		
		Socket client=null;
		int k=0;//int used only to change a list (to make an example)
		try(ServerSocket server=new ServerSocket()){
			server.bind(new InetSocketAddress(InetAddress.getLocalHost(),port));
			while(true){
				/*this is only to make an example of changes in the lists of product*/
				k++;
				if(k==5 && port==1502)
					setListChanged1502();
				if(k==3 && port==1503)
					setListChanged1503();
				if(k==2 && port==1504)
					setListChanged1504();
				
				client=server.accept();//accept a connection
				try(ObjectOutputStream out= new ObjectOutputStream(client.getOutputStream());
						BufferedReader in= new BufferedReader(new InputStreamReader(client.getInputStream()));){
							String request=in.readLine();//read a string
							if(request.equals("UpdateList")){
								out.writeObject(list);//send the list
								out.flush();
							}
				}catch(IOException e){System.err.println("Error: "+e.getMessage());}
				finally{try{client.close();}catch(IOException e){e.printStackTrace();}}
			}
		}catch(IOException e){System.err.println("Error: "+e.getMessage());}
	}
}