import java.io.Serializable;

public class Product implements Serializable,Comparable<Product>{
	
	private static final long serialVersionUID = 1L;
	public String producer;
	public String model;
	public double price;
	public String seller;

	public Product(String a,String b, String c,String name){
		this.producer=a;
		this.model=b;
		this.price=Double.parseDouble(c);
		this.seller=name;
	}
	
	public Product(){}
	
	public String getName() {
		return this.model;
	}
	public String getProducer() {
		return this.producer;
	}
	public String getSeller() {
		return this.seller;
	}
	public Double getPrice() {
		return this.price;
	}
	public void setName(String name) {
		this.model=name;
	}
	public void setProducer(String p) {
		this.producer=p;
	}
	public void setSeller(String s){
		this.seller=s;
	}
	public void setPrice(Double p) {
		this.price=p;
	}
	
	@Override
	public int compareTo(Product x) {//to sort
		Double p=this.price;
		return p.compareTo(new Double(x.getPrice()));
	}

}