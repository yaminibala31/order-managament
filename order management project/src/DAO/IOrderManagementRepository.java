package DAO;

import java.util.*;
import Entity.*;

public interface IOrderManagementRepository {
	
	void crateorder(User user,List<Product> products) throws Exception;
	
	void cancelorder(int userid, int orderid)throws Exception;
	
	void createproduct(User user,Product product)throws Exception;
	
	void createuser(User user)throws Exception;
	
	List<Product> getallproduct() throws Exception;
	
	List<Product> getorderbyuser(User user)throws Exception;
	
	
	
}
