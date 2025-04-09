package Entity;

public class Electronics extends Product{
	
	private String brand;
	private int warrantyPeriod;
	
	public Electronics(int productid, String productname, String description, double price, int quantityinstock,
			String type,String brand,int warrantyPeriod) 
	{
		super(productid, productname, description, price, quantityinstock, type);
		this.brand=brand;
		this.warrantyPeriod=warrantyPeriod;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	
	
	
	
	

}
