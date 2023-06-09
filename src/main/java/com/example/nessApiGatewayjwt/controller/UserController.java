package  com.example.nessApiGatewayjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nessApiGatewayjwt.model.AuthRequest;
import com.example.nessApiGatewayjwt.model.User;
import com.example.nessApiGatewayjwt.repo.UserRepository;
import com.example.nessApiGatewayjwt.service.UserService;
import com.example.nessApiGatewayjwt.util.JwtUtil;


@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String welcome() {
        return "Welcome to JWT TOKENS !!";
    }

    @GetMapping("/test")
    public String welcomeTest() {
        return "Welcome to JWT TOKENS !!";
    }

    
    @GetMapping("/nesstest")
    public String testingEndpoint() {
        return "Welcome to JWT TOKENS !!";
    }
    
    @GetMapping("na/nesstest")
    public String testingNewEndpoint() {
        return "Welcome to JWT TOKENS !!";
    }
    
    
    @PostMapping("na/signup")
    public User signup(@RequestBody User user){
    	return userService.saveCustomer(user);
          }
 
    @PostMapping("na/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPwd())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }
    
	@PostMapping("/login")
	public ResponseEntity<User> getCustomer(@RequestBody AuthRequest customer) {
		User findCustomer = userService.findByEmailAndPwd(customer.getEmail(), customer.getPwd());
		return new ResponseEntity<User>(findCustomer,HttpStatus.OK);
		
	}
	
	@PostMapping("na/findById")
	public ResponseEntity<User> findByIdCustomer(@RequestBody User customer)
	{
		User customer1=userService.getCustomerById(customer.getEmail());
		return new ResponseEntity<User>(customer1,HttpStatus.OK);
	}

    @PutMapping("na/reset")
	public ResponseEntity<User> updatePassword(@RequestBody User customer) {
    	//User user= userRepo.findByEmail(email);
    	User updatedCustomer = userService.updatePassword(customer);
		return new ResponseEntity<User>(updatedCustomer,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestBody User customer)
	{
		String status=userService.deleteCustomer(customer);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
	
    @GetMapping("na/forgot/{email}")
    public ResponseEntity<User> checkMail(@PathVariable String email){
    	
    	User u=userService.CheckEmail(email);
    	return new ResponseEntity<User>(u,HttpStatus.OK);
    }
    
}
