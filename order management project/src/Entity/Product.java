package Entity;

public class Product {
	
	//here i'm using protected to give acces to clothing and electronics
	protected int productid;
    protected String productname;
    protected String description;
    protected double price;
    protected int quantityinstock;
    protected String type;
    
	public Product() {
		
	}
    
    
    public Product(int productid, String productname, String description, double price, int quantityinstock,String type)
			
    {
		super();
		this.productid = productid;
		this.productname = productname;
		this.description = description;
		this.price = price;
		this.quantityinstock = quantityinstock;
		this.type = type;
	}


	public int getProductId() {
		return productid;
	}


	public void setProductId(int productid) {
		this.productid = productid;
	}


	public String getProductName() {
		return productname;
	}


	public void setProductName(String productname) {
		this.productname = productname;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantityInStock() {
		return quantityinstock;
	}


	public void setQuantityInStock(int quantityinstock) {
		this.quantityinstock = quantityinstock;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productid + ", productName=" + productname + ", description=" + description
				+ ", price=" + price + ", quantityInStock=" + quantityinstock + ", type=" + type + "]";
	}
	
	

}
