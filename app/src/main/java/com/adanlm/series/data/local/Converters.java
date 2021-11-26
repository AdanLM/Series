package com.adanlm.series.data.local;

import androidx.room.TypeConverter;

import com.adanlm.series.data.model.Image;
import com.adanlm.series.data.model.Rating;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<String> fromString(String value) {
        return new ArrayList<String>(Arrays.asList(value.split(",")));
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        return list.toString();
    }

    @TypeConverter
    public static Rating fromFloat(float value) {
        return new Rating(value);
    }

    @TypeConverter
    public static float fromRating(Rating rating) {
        return rating.getAverage();
    }

    @TypeConverter
    public static Image fromImage(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, Image.class);
    }

    @TypeConverter
    public static String fromString(Image image) {
        Gson gson = new Gson();
        return gson.toJson(image);
    }
}
