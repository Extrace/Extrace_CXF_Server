package web.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ts.model.Customer;
import web.mvc.daoImpl.CustomerDao;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	CustomerDao customerDao = new CustomerDao();

	@RequestMapping(value = "/list")
	public ModelAndView getCustomers() {
		ModelAndView model = new ModelAndView("listofcustomers");
		List<Customer> cust = customerDao.getCustomers();
		model.addObject("customers", cust);
		return model;
	}

	@RequestMapping(value = "/customerinfo")
	public ModelAndView getCustomerInfo() {
		ModelAndView model = new ModelAndView("listofcustomers");
		// String customers = (String) miscService.getCustomerInfo(id);
		// model.addObject("customers", customers);

		return model;
	}
	// @RequestMapping(value = "/json", method = RequestMethod.GET, produces =
	// "application/json")
	// @ResponseBody
	// public List<Customer> getCustomersJson() {
	// List<Customer> lcustomer = customerService.getCustomers();
	// return creatListjson(lcustomer);
	// }
	//
	// private List<Customer> creatListjson(List<Customer> lcustomer) {
	// // TODO Auto-generated method stub
	// List<Customer> customers = new ArrayList<Customer>();
	// for (Customer c : lcustomer) {
	// customers.add(c);
	// }
	// return customers;
	// }

}
