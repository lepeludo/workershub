package be.klusjes.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
		String streetName;
		String houseNumber;
		Integer postalcode;
		String city;
		
		
		
		public Address() {
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		public String getHouseNumber() {
			return houseNumber;
		}
		public void setHouseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
		}
		public Integer getPostalcode() {
			return postalcode;
		}
		public void setPostalcode(Integer postalcode) {
			this.postalcode = postalcode;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		
}
