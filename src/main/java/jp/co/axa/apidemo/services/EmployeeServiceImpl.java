package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> retrieveEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	public Employee getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId).orElse(null);
	}

	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
		employeeRepository.flush();
	}
}