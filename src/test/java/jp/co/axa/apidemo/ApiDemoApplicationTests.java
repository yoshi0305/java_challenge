package jp.co.axa.apidemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiDemoApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void setup() {
		employeeService.deleteAllEmployees();
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee1 = new Employee(1L, "emp1", 1000, "IT");
		employeeService.saveEmployee(employee1);
		employeeService.deleteEmployee(employee1.getId());
		assertThat(employeeService.retrieveEmployees()).size().isEqualTo(0);
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteNonExistingEmployeeTest() {
		employeeService.deleteEmployee(100L);
	}

	@Test
	public void retrieveEmployeesTest() {
		Employee employee2 = new Employee(2L, "empTest1", 10000, "ITTech");
		Employee employee3 = new Employee(3L, "empTest2", 20000, "Marketing");
		Employee employee4 = new Employee(4L, "empTest3", 30000, "Finance");
		employeeService.saveEmployee(employee2);
		employeeService.saveEmployee(employee3);
		employeeService.saveEmployee(employee4);

		List<Employee> employees = employeeService.retrieveEmployees();
		assertThat(employees).anyMatch(e -> e.getName().equals(employee2.getName()));
		assertThat(employees).anyMatch(e -> e.getName().equals(employee3.getName()));
		assertThat(employees).anyMatch(e -> e.getName().equals(employee4.getName()));
		assertThat(employees).size().isEqualTo(3);
	}

	@Test(expected = ConstraintViolationException.class)
	public void saveEmployeeTestWithLongString() {
		Employee employee = new Employee();
		employee.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		employeeService.saveEmployee(employee);
	}

	@Test
	public void updateEmployeeTest() {
		Employee employee5 = new Employee(5L, "emp5", 7000, "IT");
		employeeService.saveEmployee(employee5);
		employee5.setName("emp5_updated");
		employee5.setSalary(8000);
		employeeService.updateEmployee(employee5);
		assertThat(employeeService.getEmployee(5L))
				.matches(e -> e.getName().equals("emp5_updated") && e.getSalary() == 8000);
	}

	@Test(expected = ConstraintViolationException.class)
	public void updateEmployeeTestWithInvalidSalary() {
		Employee employee6 = new Employee(8L, "emp8", 8000, "IT");
		employeeService.saveEmployee(employee6);
		employee6.setSalary(-1);
		employeeService.updateEmployee(employee6);
	}

}
