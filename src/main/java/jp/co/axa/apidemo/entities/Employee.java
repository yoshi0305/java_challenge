package jp.co.axa.apidemo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "EMPLOYEE")
@Builder
public class Employee {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "EMPLOYEE_NAME")
	@Size(min = 0, max = 14)
	@JsonProperty("name")
	private String name;

	@Getter
	@Setter
	@Column(name = "EMPLOYEE_SALARY")
	@Min(0)
	@Max(Integer.MAX_VALUE)
	@JsonProperty("salary")
	private Integer salary;

	@Getter
	@Setter
	@Column(name = "DEPARTMENT")
	@Size(min = 0, max = 255)
	@JsonProperty("department")
	private String department;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getSalary() {
		return salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Employee(Long id, String name, Integer salary, String department) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
	}

	public Employee() {
	}

}
