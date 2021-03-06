package app.kafka.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee implements Serializable {
	private static final long serialVersionUID = 6851435108401746384L;

	private int empId;
	private String empName;
	private String role;
	private double salary;
	private Date dateOfBirth;
	private String mobileNumber;
	private String email;
	private Long departmentId;
	private List<Address> addressList;

}
