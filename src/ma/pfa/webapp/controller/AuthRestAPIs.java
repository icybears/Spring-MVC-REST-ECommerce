package ma.pfa.webapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.pfa.webapp.dao.IClientDao;
import ma.pfa.webapp.dao.IRoleDao;
import ma.pfa.webapp.dao.IUserDao;
import ma.pfa.webapp.message.request.LoginForm;
import ma.pfa.webapp.message.request.SignUpForm;
import ma.pfa.webapp.message.response.JwtResponse;
import ma.pfa.webapp.message.response.ResponseMessage;
import ma.pfa.webapp.model.Client;
import ma.pfa.webapp.model.Role;
import ma.pfa.webapp.model.RoleName;
import ma.pfa.webapp.model.User;
import ma.pfa.webapp.security.jwt.JwtProvider;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private IUserDao userRepository;

	@Autowired
	private IRoleDao roleRepository;
	
	@Autowired
	private IClientDao clDao;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);

				if (adminRole == null)
					throw new RuntimeException("Fail! -> Cause: User Role not found.");

				roles.add(adminRole);

				break;
			case "pm":
				Role pmRole = roleRepository.findByName(RoleName.ROLE_PM);
				if (pmRole == null)
					throw new RuntimeException("Fail! -> Cause: User Role not found.");
				roles.add(pmRole);

				break;
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
				if (userRole == null)
					throw new RuntimeException("Fail! -> Cause: User Role not found.");
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		
		//creating client object
		Client client = new Client();
		client.setAdresse(signUpRequest.getAdresse());
		client.setNom(signUpRequest.getNom());
		client.setPrenom(signUpRequest.getPrenom());
		client.setTel(signUpRequest.getTelephone());
		client.setEmail(signUpRequest.getEmail());
		
		user.setClient(client);
		userRepository.save(user);
		
		
		

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}