package com.example.nessApiGatewayjwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nessApiGatewayjwt.model.User;
import com.example.nessApiGatewayjwt.repo.NewRepo;
import com.example.nessApiGatewayjwt.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NewRepo customerDao;


	@Override
	public User saveCustomer(User customer) {
		customerDao.save(customer);
		return customer;
	}

	@Override
	public User findByEmailAndPwd(String email, String pwd) {
		User customer= customerDao.findByEmailAndPwd(email, pwd);
		return customer;
	}
	

	@Override
	public List<User> getAllCustomers() {
		List<User> allCustomers = customerDao.findAll();
		return allCustomers;
	}
	
	public User getCustomerById(String email) {
		Optional<User> optCustomer = customerDao.findById(email);
		 if(optCustomer.isPresent())
		 {
			return  optCustomer.get();		 
		 } 
		 return null;
		
	}

	@Override
	public User updatePassword(User customer) {
		//userRepository.findByEmail(null);
		User oldCustomer = getCustomerById(customer.getEmail());
		oldCustomer.setPwd(customer.getPwd());

		return customerDao.save(oldCustomer);
		
		
	
	}

	@Override
	public String deleteCustomer(User customer) {
		User found = getCustomerById(customer.getEmail());
//		customerDao.deleteById(customer.getEmail());
		if(found!=null) {
			customerDao.deleteById(customer.getEmail());
			return "1";
		
	}
		return "0";
}

	@Override
	public User CheckEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}



	
	
}
