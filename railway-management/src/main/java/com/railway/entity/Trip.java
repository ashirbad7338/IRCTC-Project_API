package com.railway.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Trip {
	private int id;
	private String start;
	private String dest;
	private LocalTime departure;
	private LocalTime arrival;
	private LocalDate date;
	private int bookedSeats;
	private double price;
	private Employee driver;
	private Train train;
	private ArrayList<User> passenger;
	private DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter timeFormat=DateTimeFormatter.ofPattern("HH:mm");
	public Trip() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public LocalTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}
	public LocalTime getArrival() {
		return arrival;
	}
	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Employee getDriver() {
		return driver;
	}
	public void setDriver(Employee driver) {
		this.driver = driver;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public ArrayList<User> getPassenger() {
		return passenger;
	}
	public void setPassenger(ArrayList<User> passenger) {
		this.passenger = passenger;
	}
	public DateTimeFormatter getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateTimeFormatter dateFormat) {
		this.dateFormat = dateFormat;
	}
	public DateTimeFormatter getTimeFormat() {
		return timeFormat;
	}
	public void setTimeFormat(DateTimeFormatter timeFormat) {
		this.timeFormat = timeFormat;
	}
	

	
	
}
