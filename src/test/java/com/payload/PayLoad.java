package com.payload;

import com.pojo.AddAddress_Input_pojo;
import com.pojo.DeleteAddress_Input_pojo;
import com.pojo.UpdateAddress_Input_pojo;

/**
 * 
 * @author siren
 * @Description Used To send PayLoad
 * @createDate 22/6/2022  
 */

public class PayLoad {
	
	/**
	 * 
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return
	 * @Description Used To send PayLoad for Add Address
	 * @createDate 22/6/2022  
	 */

	public static AddAddress_Input_pojo AddAddress_Input_pojo(String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {
		AddAddress_Input_pojo addAddress_Input_pojo = new AddAddress_Input_pojo(first_name, last_name, mobile,
				apartment, state, city, country, zipcode, address, address_type);
		return addAddress_Input_pojo;

	}
	
	/**
	 * 
	 * @param address_id
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return
	 * @Description Used To send PayLoad for Update Address
     * @createDate 22/6/2022  
	 */

	public static UpdateAddress_Input_pojo UpdateAddress_Input_pojo(String address_id, String first_name,
			String last_name, String mobile, String apartment, int state, int city, int country, String zipcode,
			String address, String address_type) {
		UpdateAddress_Input_pojo updateAddress_Input_pojo = new UpdateAddress_Input_pojo(address_id, first_name,
				last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		return updateAddress_Input_pojo;

	}
	
	/**
	 * 
	 * @param address_id
	 * @return
	 * @Description Used To send PayLoad for Delete Address
     * @createDate 22/6/2022  
	 */

	public static DeleteAddress_Input_pojo Delete_Input_pojo(String address_id) {
		DeleteAddress_Input_pojo delete_Input_pojo = new DeleteAddress_Input_pojo(address_id);
		return delete_Input_pojo;

}

}
