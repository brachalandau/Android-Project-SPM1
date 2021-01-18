package com.example.spm1.Utils;

import android.content.Context;
import androidx.room.TypeConverter;

import java.util.HashMap;


public abstract class Convert extends Context {

    @TypeConverter
    public static ParcelStatus stringToStatus(String status) {
        if (status == "SENT") {
            return ParcelStatus.SENT;
        } else if (status == "OFFERED_TO_PICK_UP") {
            return ParcelStatus.OFFERED_TO_PICK_UP;
        } else if (status == "ON_ITS_WAY") {
            return ParcelStatus.ON_ITS_WAY;
        } else if (status == "RECEIVED") {
            return ParcelStatus.RECEIVED;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String statusToString(ParcelStatus status) {
        if (status == null)
            return null;
        return status.toString();
    }

    @TypeConverter
    public static ParcelType stringToType(String type) {
        if (type == "ENVELOPE") {
            return ParcelType.ENVELOPE;
        } else if (type == "SMALL_PACKAGE") {
            return ParcelType.SMALL_PACKAGE;
        } else if (type == "BIG_PACKAGE") {
            return ParcelType.BIG_PACKAGE;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String typeToString(ParcelType type) {
        if (type == null)
            return null;
        return type.toString();
    }

    @TypeConverter
    public static ParcelWeight stringToWeight(String weight) {
        if (weight == "UP_TO_500_GRAM") {
            return ParcelWeight.UP_TO_500_GRAM;
        } else if (weight == "UP_TO_1_KGRAM") {
            return ParcelWeight.UP_TO_1_KGRAM;
        } else if (weight == "UP_TO_5_KGRAM") {
            return ParcelWeight.UP_TO_5_KGRAM;
        } else if (weight == "UP_TO_20_KGRAM") {
            return ParcelWeight.UP_TO_20_KGRAM;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String weightToString(ParcelWeight weight) {
        if (weight == null)
            return null;
        return weight.toString();
    }


    @TypeConverter
    public static String hashMapToString(HashMap hashMap) {
        return hashMap.keySet().toString();
    }

    @TypeConverter
    public static HashMap stringToHashMap(String key) {
        return (HashMap) new HashMap<>().put(key,true);
    }
}