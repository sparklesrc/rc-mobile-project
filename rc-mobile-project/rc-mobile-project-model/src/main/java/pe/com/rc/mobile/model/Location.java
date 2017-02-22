package pe.com.rc.mobile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Location {

	@JsonIgnore
	private String locationId;
	private String locationName;
	@JsonIgnore
	private String storeId;
	private String address;
	private String addressFind;
	private String district;
	private String districtFind;
	private String province;
	private String department;
	@JsonIgnore
	private String channelId;
	private String scheduleRef;
	private String latitude;
	private String longitude;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressFind() {
		return addressFind;
	}

	public void setAddressFind(String addressFind) {
		this.addressFind = addressFind;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictFind() {
		return districtFind;
	}

	public void setDistrictFind(String districtFind) {
		this.districtFind = districtFind;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getScheduleRef() {
		return scheduleRef;
	}

	public void setScheduleRef(String scheduleRef) {
		this.scheduleRef = scheduleRef;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
