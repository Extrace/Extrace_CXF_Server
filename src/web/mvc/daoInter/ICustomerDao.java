package web.mvc.daoInter;

import java.util.List;

import ts.model.Customer;

public interface ICustomerDao {
	public void addCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public boolean delCustomerById(int id);

	public Customer getCustomerById(int id);

	public List<Customer> getCustomers();
}
