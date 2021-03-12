package model.CRBDataModel;

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
