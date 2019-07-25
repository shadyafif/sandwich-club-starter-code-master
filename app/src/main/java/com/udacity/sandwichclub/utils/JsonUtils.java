package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {


        JSONObject JASONSanwitch, name;
        JSONArray alsoKnownAs, ingredienst;
        String nameString = null, OriginString = null, descripionString = null, imageString = null;

        List<String> AlsoknownAsList = new ArrayList<>(),
                IngerdientsList = new ArrayList<>();
        try {
            JASONSanwitch = new JSONObject(json);
            name = JASONSanwitch.getJSONObject("name");
            nameString = name.getString("mainName");
            alsoKnownAs = name.getJSONArray("alsoKnownAs");
            OriginString = JASONSanwitch.getString("placeOfOrigin");
            descripionString = JASONSanwitch.getString("description");

            imageString = JASONSanwitch.getString("image");
            ingredienst = JASONSanwitch.getJSONArray("ingredients");

            int i;
            if (ingredienst == null) IngerdientsList.add("Not available");
            else
                for (i = 0; i < ingredienst.length(); i++) {
                    IngerdientsList.add(ingredienst.get(i).toString());

                }
            if (alsoKnownAs == null) AlsoknownAsList.add("Not available");
            else
                for (i = 0; i < alsoKnownAs.length(); i++) {
                    AlsoknownAsList.add(alsoKnownAs.getString(i));
                }


            if (nameString == null || nameString.length() == 0)
                nameString = "Not Available";
            if (imageString == null || imageString.length() == 0)
                imageString = "Not Available";
            if (OriginString == null || OriginString.length() == 0)
                OriginString = "Not Available";
            if (descripionString == null || descripionString.length() == 0)
                descripionString = "Not Available";


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {

        return new Sandwich(nameString, AlsoknownAsList, OriginString, descripionString, imageString, IngerdientsList);

    }


}



