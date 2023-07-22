package in.NoobCoders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "suppliers")
public class Supplier {
	@Id
	@Column(name = "supplier_id")
	private Integer supplierId;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "contact_name")
	private String contactname;
	@Column(name = "contact_title")
	private String contactTitle;
	private String address;
	private String city;
	private String region;
	@Column(name = "postal_code")
	private String postalCode;
	private String country;
	private String phone;
	private String fax;
	@Column(name = "home_page")
	private String homePage;
	
	
	
	

}


