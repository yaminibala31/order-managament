package DAO;


import Connect.Dbutil;

import java.util.List;
import java.sql.*;
import Entity.*;
import java.util.*;

public class OrderProcessor implements IOrderManagementRepository {
	
	public static Connection con;
	public OrderProcessor() {
		try {
			con=Dbutil.getDBConn();
			System.out.println("connection established");
		}
		catch(Exception e) {
			System.out.println("--not connected--");
			
		}
	}

	@Override
	public void crateorder(User user, List<Product> products) throws Exception {
		
		//to chech user present in table
		String query="Select * from user where userid=?";
		
		PreparedStatement stat=con.prepareStatement(query);
		stat.setInt(1,user.getUserid());
		ResultSet rs=stat.executeQuery();
		
		//if not exist creating a user by calling the createuser function
		if (!rs.next()) {
            createuser(user);  
        }
		Product product = products.get(0);
		//creating order
		String orderquery="insert into orders(userid,productid,orderdate) values(?,?,CURRENT_DATE)";
		//use case generated key to get the auto incremented order id , we can also use query with max of orderid and max+1 to generate id
		PreparedStatement st=con.prepareStatement(orderquery,Statement.RETURN_GENERATED_KEYS);
		st.setInt(1, user.getUserid());
		st.setInt(2, product.getProductId());
		st.executeUpdate();
		
		ResultSet od=st.getGeneratedKeys();
		int orderid = 0;
        if (od.next()) {
            orderid = od.getInt(1);
            System.out.println("Order placed successfully with Order ID: " + orderid);
        }
		
		
		
	}

	@Override
	public void cancelorder(int userid, int orderid) throws Exception {
		//user
		String query="Select * from user where userid=?";
		PreparedStatement stat=con.prepareStatement(query);
		stat.setInt(1, userid);
		ResultSet rs=stat.executeQuery();
		
		if (!rs.next()) {
			System.out.print("User not found");
		}
		//order
		String orderquery="Select * from orders where orderid=?";
		PreparedStatement st=con.prepareStatement(orderquery);
		st.setInt(1, orderid);
		ResultSet rst=stat.executeQuery();
		
		if (!rst.next()) {
			System.out.print("order not found");
		}
		//cancelling order
		PreparedStatement deleteOrder = con.prepareStatement("delete from orders where orderid = ?");
	    deleteOrder.setInt(1, orderid);
	    deleteOrder.executeUpdate();
	    
	    System.out.println("Order cancelled successfully");
		
		
	}

	@Override
	public void createproduct(User user, Product product) throws Exception {
		
		String query="Select * from user where userid=? and role='admin'";
		PreparedStatement stat=con.prepareStatement(query);
		stat.setInt(1,user.getUserid());
		ResultSet rs=stat.executeQuery();
		
		if (!rs.next()) {
			System.out.print("only admins can create product");
		}
		
		String productquery = "insert into product(productid, productname, description, price, quantityinstock, type) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(productquery);
        st.setInt(1, product.getProductId());
        st.setString(2, product.getProductName());
        st.setString(3, product.getDescription());
        st.setDouble(4, product.getPrice());
        st.setInt(5, product.getQuantityInStock());
        st.setString(6, product.getType());
        st.executeUpdate();
        
        //the function instance will check the product is electronic to store thwm in electronic table
        if (product instanceof Electronics) {
            Electronics e = (Electronics) product;//interface ogject creation
            String equery = "insert into electronics(productid, brand, warrantyPeriod) VALUES (?, ?, ?)";
            PreparedStatement et = con.prepareStatement(equery);
            et.setInt(1, product.getProductId());
            et.setString(2, e.getBrand());
            et.setInt(3, e.getWarrantyPeriod());
            et.executeUpdate();
            
            
        }
      //the function instance will check the product is clothing to store thwm in clothing table
        else if (product instanceof Clothing) {
            Clothing c = (Clothing) product;
            String cquery = "insert into clothing(productid, size, color) VALUES (?, ?, ?)";
            PreparedStatement ct = con.prepareStatement(cquery);
            ct.setInt(1, product.getProductId());
            ct.setString(2, c.getSize());
            ct.setString(3, c.getColor());
            ct.executeUpdate();
        }

        System.out.println("Product created successfully.");
	}

	@Override
	public void createuser(User user) throws Exception {
		
		String query="insert into user(userid,username,password,role) values(?,?,?,?)";
		PreparedStatement stat=con.prepareStatement(query);
		stat.setInt(1,user.getUserid());
		stat.setString(2,user.getUsername());
		stat.setString(3, user.getPasssword());
		stat.setString(4, user.getRole());
		stat.executeUpdate();
		
		
        System.out.println("User created successfully");
		
	}

	@Override
	public List<Product> getallproduct() throws Exception {
		List<Product> products = new ArrayList<>();

        String sql = "select * from product";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	Product p = new Product(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("type"));
        	products.add(p);
                
        }

        return products;
	}

	@Override
	public List<Product> getorderbyuser(User user) throws Exception {
		List<Product> orderedProducts = new ArrayList<>();

        String query = "select p.* from product p join orders o on p.productid = o.productid where o.userid = ?";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setInt(1, user.getUserid());
        ResultSet rs = stat.executeQuery();

        while (rs.next()) {
            Product p = new Product(
                rs.getInt("productId"),
                rs.getString("productName"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getInt("quantityInStock"),
                rs.getString("type")
            );
            orderedProducts.add(p);
        }

        return orderedProducts;
	}

}
