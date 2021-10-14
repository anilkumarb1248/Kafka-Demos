package app.kafka.model;

import java.io.Serializable;

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
public class Address implements Serializable {
	private static final long serialVersionUID = 8833607942638719091L;

	private int addressId;
	private String houseNumber;
	private String street;
	private String city;
	private String state;
	private String pincode;

}
