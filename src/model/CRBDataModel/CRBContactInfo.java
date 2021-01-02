package model.CRBDataModel;

/**Record Format 6: Data for contact information at the bottom of a 500 byte data file
 * @author klee8
 *
 */
public class CRBContactInfo extends CRBLine{
	
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
	}
}
