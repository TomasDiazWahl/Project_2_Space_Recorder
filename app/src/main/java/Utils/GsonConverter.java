package Utils;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Universe_Structure.Planet;

public class GsonConverter {
    Gson gson = new Gson();
    @TypeConverter
    public String convertPlanetToJson (Planet planet){
        String json = gson.toJson(planet);
        System.out.println(json);
        return json;
    }

    @TypeConverter
    public Planet convertJsonToPlanet (String json){
        Planet planet = gson.fromJson(json, Planet.class);
        return planet;
    }

}
