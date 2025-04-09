package Entity;

public class Clothing extends Product{
	
	private String size;
	private String color;
	public Clothing() {
		super();
		
	}
	public Clothing(int productid, String productname, String description, double price, int quantityinstock,
			String type,String size,String color) {
		super(productid, productname, description, price, quantityinstock, type);
		this.color=color;
		this.size=size;
		
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Clothing [size=" + size + ", color=" + color + ", getProductId()=" + getProductId()
				+ ", getProductName()=" + getProductName() + ", getDescription()=" + getDescription() + ", getPrice()="
				+ getPrice() + ", getQuantityInStock()=" + getQuantityInStock() + ", getType()=" + getType()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
	
	

}
