package com.example.nessApiGatewayjwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nessApiGatewayjwt.model.User;

@Service
public interface UserService {

	
	public User saveCustomer(User customer);
	public User findByEmailAndPwd(String email, String pwd);
	public User getCustomerById(String email);
	public List<User> getAllCustomers();
	public String deleteCustomer(User customer);
	public User updatePassword(User customer);
	
	public User CheckEmail(String email);
	
}
