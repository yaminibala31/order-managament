package Client;

import java.util.Scanner;
import DAO.*;
import Entity.*;
import java.util.*;
public class main {
	
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        OrderProcessor op = new OrderProcessor();
		
        while (true) {
            System.out.println("\n mrder management Menu ");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders by User");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            
            int ch = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (ch) 
                {
                
                    case 1:
                        System.out.print("Enter User ID: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Username: ");
                        
                        String uname = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String pwd = sc.nextLine();
                        System.out.print("Enter Role (admin/user): ");
                        String role = sc.nextLine();
                        
                        
                        op.createuser(new User(uid, uname, pwd, role));
                        break;

                    case 2:
                        System.out.print("Enter Admin User ID: ");
                        int auid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product ID: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String pname = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Enter Type (Electronics/Clothing): ");
                        String type = sc.nextLine();

                        Product p = null;//to add data to electronics table
                        
                        if (type.equalsIgnoreCase("Electronics")) 
                        {
                            
                        	System.out.print("Enter Brand: ");
                            String brand = sc.nextLine();
                            
                            System.out.print("Enter Warranty Period: ");
                            int warranty = sc.nextInt();
                            p = new Electronics(pid, pname, desc, price, qty, type, brand, warranty);
                            
                            
                        } 
                        
                        
                        //to add data if it is clothing
                        else if (type.equalsIgnoreCase("Clothing")) 
                        {
                            
                        	System.out.print("Enter Size: ");
                            String size = sc.nextLine();
                            System.out.print("Enter Color: ");
                            String color = sc.nextLine();
                            p = new Clothing(pid, pname, desc, price, qty, type, size, color);
                            
                            
                        } 
                        
                        
                        else 
                        {
                            p = new Product(pid, pname, desc, price, qty, type);
                        }

                        op.createproduct(new User(auid, "", "", "admin"), p);
                        break;

                    case 3:
                        System.out.print("Enter User ID: ");
                        int orderUid = sc.nextInt();
                        sc.nextLine();
                        
                        System.out.print("Enter Product ID to Order: ");
                        int orderPid = sc.nextInt();
                        sc.nextLine();
                        
                        Product orderProduct = new Product(orderPid, "", "", 0, 0, "");
                        op.crateorder(new User(orderUid, "", "", ""), Arrays.asList(orderProduct));
                        break;

                    case 4:
                    	
                        System.out.print("Enter User ID: ");
                        int cancelUid = sc.nextInt();
                        System.out.print("Enter Order ID to cancel: ");
                        int oid = sc.nextInt();
                        
                        op.cancelorder(cancelUid, oid);
                        
                        break;

                    case 5:
                        List<Product> all = op.getallproduct();
                        for (Product prodc : all) 
                        {
                        	
                            System.out.println(prodc);
                        }
                        break;

                    case 6:
                        System.out.print("Enter User ID: ");
                        int getUid = sc.nextInt();
                        List<Product> orders = op.getorderbyuser(new User(getUid, "", "", ""));
                        
                        
                        for (Product prodc: orders)
                        	
                        {
                            System.out.println(prodc);
                        }
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
	}

}
