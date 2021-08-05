package com.bike.dto;

public class BikeDto {
	private String name;
	private String addr;
	private double latitude;
	private double longitude;
	private int bike_count;

	public BikeDto() {

	}

	public BikeDto(String name, String addr, double latitude, double longitude, int bike_count) {
		this.name = name;
		this.addr = addr;
		this.latitude = latitude;
		this.longitude = longitude;
		this.bike_count = bike_count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getBike_count() {
		return bike_count;
	}

	public void setBike_count(int bike_count) {
		this.bike_count = bike_count;
	}

}
