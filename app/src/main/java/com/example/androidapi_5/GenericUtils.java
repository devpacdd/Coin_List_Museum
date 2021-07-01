package com.example.androidapi_5;

import com.google.gson.Gson;

// para serialziar (no se ha usado)

public class GenericUtils {

    public static <T> String serialize(T object) {
        return new Gson().toJson((Object) object);
    }

    public static <T> Object deserialize(String json, Class<T> object) {
        return new Gson().fromJson(json, object);
    }

}
