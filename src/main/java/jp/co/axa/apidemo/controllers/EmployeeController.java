package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.models.AuthenticationRequest;
import jp.co.axa.apidemo.models.AuthenticationResponse;
import jp.co.axa.apidemo.services.AxaUserDetailService;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private AxaUserDetailService userDetailService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private JwtService jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MessageSource messageSource;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * Get employees.
	 * 
	 * @return Employee list
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return ResponseEntity.ok(employeeService.retrieveEmployees());
	}

	/**
	 * Get an employ from DB.
	 * 
	 * @param employeeId
	 * @return An employ with the ID.
	 */
	@GetMapping("/employees/{employeeId}")
//    @Cacheable(key = "#id", value="employee")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		// Employee employee = employeeService.getEmployee(employeeId);
		return Optional.ofNullable(employeeService.getEmployee(employeeId)).map(employee -> ResponseEntity.ok(employee))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Save employee into DB.
	 * 
	 * @param employee
	 * @return Message for success
	 */
	@PostMapping("/employees")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return new ResponseEntity<String>(messageSource.getMessage("saved_employee", null, Locale.getDefault()),
				HttpStatus.CREATED);
	}

	/**
	 * Delete employee
	 * 
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok(messageSource.getMessage("deleted_employee", null, Locale.getDefault()));
	}

	/**
	 * Update employee
	 * 
	 * @param employee
	 * @param employeeId
	 */
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee,
			@PathVariable(name = "employeeId") Long employeeId) {
		Employee emp = employeeService.getEmployee(employeeId);
		if (emp != null) {
			employee.setId(employeeId);
			employeeService.updateEmployee(employee);
			return new ResponseEntity<String>(messageSource.getMessage("employee_updated", null, Locale.getDefault()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(
					messageSource.getMessage("updated_employee_not_found", null, Locale.getDefault()), HttpStatus.OK);
		}
	}

	/**
	 * Create JWT token
	 * 
	 * @param authenticationRequest
	 * @return JWT token
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect username or password", ex);
		}

		UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
