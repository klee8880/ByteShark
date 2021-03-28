package leek.byteShark.model.CRBDataModel;

/**Record Format 6: Data for contact information at the bottom of a 500 byte data file
 * @author klee8
 *
 */
public class CRBContactInfo extends CRBBase{
	
	protected String contactType;			//31-32
	protected String companyName;			//33-82
	protected String optionalName;			//83-117
	protected String title;					//118-152
	protected String phone;					//153-177
	protected String fax;					//178-202
	protected String email;					//203-262
	protected String address1;				//263-307
	protected String address2;				//308-352
	protected String address3;				//353-397
	protected String address4;				//398-442
	protected String city;					//443-477
	protected String state;					//478-479
	protected String countryCode;			//480-481
	protected String zipCode;				//482-491
	
	public CRBContactInfo(String input) {
		super(input);
		this.contactType = input.substring(30, 32).trim();
		this.companyName = input.substring(32, 82).trim();
		this.optionalName = input.substring(82, 117).trim();
		this.title = input.substring(117, 152).trim();
		this.phone = input.substring(152, 177).trim();
		this.fax = input.substring(177, 201).trim();
		this.email = input.substring(202, 261).trim();
		this.address1 = input.substring(262, 307).trim();
		this.address2 = input.substring(307, 352).trim();
		this.address3 = input.substring(352, 397).trim();
		this.address4 = input.substring(397, 442).trim();
		this.city = input.substring(442, 477).trim();
		this.state = input.substring(477 ,479).trim();
		this.countryCode = input.substring(479, 481).trim();
		this.zipCode = input.substring(481, 491).trim();
	}
	
	public CRBContactInfo() {
		super();
		this.recordFormat = 6;
	}
	
	//Getters and Setters
	public String getContactType() {
		return contactType;
	}



	public void setContactType(String contactType) {
		this.contactType = contactType;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getOptionalName() {
		return optionalName;
	}



	public void setOptionalName(String optionalName) {
		this.optionalName = optionalName;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getAddress3() {
		return address3;
	}



	public void setAddress3(String address3) {
		this.address3 = address3;
	}



	public String getAddress4() {
		return address4;
	}



	public void setAddress4(String address4) {
		this.address4 = address4;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getZipCode() {
		return zipCode;
	}



	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	//Methods

	@Override
	public String toString() {
		StringBuilder sb =  new StringBuilder()
				.append(super.toString())
				.append(contactType)
				.append(String.format("%-50s", companyName))
				.append(String.format("%-35s", optionalName))
				.append(String.format("%-35s", title))
				.append(String.format("%-25s", phone))
				.append(String.format("%-25s", fax))
				.append(String.format("%-60s", email))
				.append(String.format("%-45s", address1))
				.append(String.format("%-45s", address2))
				.append(String.format("%-45s", address3))
				.append(String.format("%-45s", address4))
				.append(String.format("%-35s", city))
				.append(state)
				.append(countryCode)
				.append(String.format("%-10s", zipCode));
				
		for (int i = 0; i < 9; i++) {
			sb.append(" ");
		}
		
		return sb.toString();
		
	}
}
