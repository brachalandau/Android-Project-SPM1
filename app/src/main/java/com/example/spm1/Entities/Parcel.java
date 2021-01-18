package com.example.spm1.Entities;


import android.location.Location;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.spm1.Utils.ParcelStatus;
import com.example.spm1.Utils.ParcelType;
import com.example.spm1.Utils.ParcelWeight;

@Entity(tableName ="parcels")
public class Parcel {
    @PrimaryKey
    @NonNull
    String id;
    ParcelType parcelType;
    boolean fragile;
    ParcelWeight parcelWeight;
    String location;
    String firstName;
    String lastName;
    String address;
    String sentDate;
    String sentTime;
    String receivedDate;
    String receivedTime;
    String PhoneNumber;
    String email;
    ParcelStatus parcelStatus=ParcelStatus.SENT;
    String deliveryManName="NO";
    //HashMap<String, Boolean> myHashMap;


    ///////////////////////////////////// constructor////////////////////////////

    public Parcel(String parcel_id, ParcelType package_type, boolean breakable, ParcelWeight package_weight,String location1, String recipient_Fname,String recipient_Lname, String recipient_address, String package_deliver_date,String deliver_date, String recipient_phone, String recipient_mail, ParcelStatus package_status, String delivery_name) {
        this.id = parcel_id;
        this.parcelType = package_type;
        this.fragile = breakable;
        this.location=location1;
        this.parcelWeight = package_weight;
        this.firstName = recipient_Fname;
        this.lastName = recipient_Lname;
        this.address = recipient_address;
        this.sentDate = package_deliver_date;
        this.receivedDate = deliver_date;
        this.PhoneNumber = recipient_phone;
        this.email = recipient_mail;
        this.parcelStatus = package_status;
        this.deliveryManName = delivery_name;
       // myHashMap=new HashMap<>();


    }

    public Parcel() {
        this.id=id;
        this.parcelType = ParcelType.ENVELOPE;
        this.fragile = true;
        this.parcelWeight = ParcelWeight.UP_TO_1_KGRAM;
        this.location = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.sentDate = "";
        this.sentTime = "";
        this.receivedDate = "";
        this.receivedTime = "";
        PhoneNumber = "";
        this.email = "";
        this.parcelStatus = ParcelStatus.SENT;
        this.deliveryManName = "";
      //  myHashMap=new HashMap<>();


    }

    ///////////////////////////////////// get/set ////////////////////////////


    public ParcelType getParcelType() {
        return parcelType;
    }

    public void setParcelType(ParcelType parcelType) {
        this.parcelType = parcelType;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public ParcelWeight getParcelWeight() {
        return parcelWeight;
    }

    public void setParcelWeight(ParcelWeight parcelWeight) {
        this.parcelWeight = parcelWeight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSendDate() {
        return sentDate;
    }

    public void setSendDate(String sendDate) {
        this.sentDate = sendDate;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId( @NonNull String id) {
        this.id = id;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public String getDeliveryManName() {
        return deliveryManName;
    }

    public void setDeliveryManName(String deliveryManName) {
        this.deliveryManName = deliveryManName;
    }

   /* public HashMap<String, Boolean> getMyHashMap() {
        return (HashMap<String, Boolean>) myHashMap.clone();
    }

    public void setMyHashMap(HashMap<String, Boolean> myHashMap1) {
        myHashMap.clear();
        for (Map.Entry<String, Boolean> entry:myHashMap.entrySet())
        {
            this.myHashMap.put(entry.getKey(),entry.getValue());
        }
    }*/

   /* public HashMap<String, Boolean> getMyHashMap() {
        return myHashMap;
    }

    public void setMyHashMap(HashMap<String, Boolean> myHashMap1) {
        myHashMap = myHashMap1;
    }
    public Boolean getMyHashMap(String S)
    {
        Boolean ret = myHashMap.get(S);
        return ret != null ? ret : null;
    }

    public void setMyHashMap(String S, Boolean B)
    {
        myHashMap.put(S,B);
    }
*/
}
