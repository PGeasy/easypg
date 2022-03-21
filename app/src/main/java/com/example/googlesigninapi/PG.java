package com.example.googlesigninapi;

public class PG {
    private String pgName;
    private String ownerName;
    private String phoneNumber;
    private String pgemailID;
    private String numberOfRooms;
    private String numberOfRoomsVacant;
    private String city;

    private String pgID;

    public String getPgID() {
        return latitude+""+longitude;
    }

    public void setPgID(String pgID) {
        this.pgID = pgID;
    }

    public boolean isACAvailable() {
        return isACAvailable;
    }

    public void setACAvailable(boolean ACAvailable) {
        isACAvailable = ACAvailable;
    }


    private String address;
    private String longitude;

    private String latitude;
    private int singleSharingRent;
    private int doubleSharingRent;
    private int tripleSharingRent;

    private boolean isWifiAvailable, isGeyserAvailable, isFoodAvailable, isSecurityAvailable, isParkingAvailable, isACAvailable;




    public String getPgemailID() {
        return pgemailID;
    }

    public void setPgemailID(String pgemailID) {
        this.pgemailID = pgemailID;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfRoomsVacant() {
        return numberOfRoomsVacant;
    }

    public void setNumberOfRoomsVacant(String numberOfRoomsVacant) {
        this.numberOfRoomsVacant = numberOfRoomsVacant;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getSingleSharingRent() {
        return singleSharingRent;
    }

    public void setSingleSharingRent(int singleSharingRent) {
        this.singleSharingRent = singleSharingRent;
    }

    public int getDoubleSharingRent() {
        return doubleSharingRent;
    }

    public void setDoubleSharingRent(int doubleSharingRent) {
        this.doubleSharingRent = doubleSharingRent;
    }

    public int getTripleSharingRent() {
        return tripleSharingRent;
    }

    public void setTripleSharingRent(int tripleSharingRent) {
        this.tripleSharingRent = tripleSharingRent;
    }

    public boolean isWifiAvailable() {
        return isWifiAvailable;
    }

    public void setWifiAvailable(boolean wifiAvailable) {
        isWifiAvailable = wifiAvailable;
    }

    public boolean isGeyserAvailable() {
        return isGeyserAvailable;
    }

    public void setGeyserAvailable(boolean geyserAvailable) {
        isGeyserAvailable = geyserAvailable;
    }

    public boolean isFoodAvailable() {
        return isFoodAvailable;
    }

    public void setFoodAvailable(boolean foodAvailable) {
        isFoodAvailable = foodAvailable;
    }

    public boolean isSecurityAvailable() {
        return isSecurityAvailable;
    }

    public void setSecurityAvailable(boolean securityAvailable) {
        isSecurityAvailable = securityAvailable;
    }

    public boolean isParkingAvailable() {
        return isParkingAvailable;
    }

    public void setParkingAvailable(boolean parkingAvailable) {
        isParkingAvailable = parkingAvailable;
    }


    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}