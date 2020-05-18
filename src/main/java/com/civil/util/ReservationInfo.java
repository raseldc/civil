/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

import java.util.Date;

/**
 *
 * @author rasel
 */
public class ReservationInfo {

    private Integer id;
    private String firstName;
    private String lastName;
    private int noGuests;
    private String gender;
    private String address;
    private String country;
    private String nationality;
    private String city;
    private String postalCode;
    private String passportId;
    private String nationalId;
    private String creditCard;
    private String mobile;
    private String company;
    private String email;
    private String password;
    private String specialNote;
    private int verified;
    private Integer createBy;
    private Date creationDate;

    private String pRoomInfo;
    private String roomInfo;
    private String fromDate;
    private String toDate;

    private Date fromDate_Date;
    private Date toDate_Date;

    private int roomId;

    private int status;

    private String zipCode;

    private int adult;
    private int children;

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getFromDate_Date() {
        return fromDate_Date;
    }

    public void setFromDate_Date(Date fromDate_Date) {
        this.fromDate_Date = fromDate_Date;
    }

    public Date getToDate_Date() {
        return toDate_Date;
    }

    public void setToDate_Date(Date toDate_Date) {
        this.toDate_Date = toDate_Date;
    }

    private String reservationId;

    public String getpRoomInfo() {
        return pRoomInfo;
    }

    public void setpRoomInfo(String pRoomInfo) {
        this.pRoomInfo = pRoomInfo;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNoGuests() {
        return noGuests;
    }

    public void setNoGuests(int noGuests) {
        this.noGuests = noGuests;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String RoomInfo) {
        this.roomInfo = RoomInfo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String FromDate) {
        this.fromDate = FromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String ToDate) {
        this.toDate = ToDate;
    }

}
