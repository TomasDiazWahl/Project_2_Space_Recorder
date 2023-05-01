package Utils;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToStringConverter {

    @TypeConverter
    @NonNull
    public String ArrayToString(ArrayList<String> aList){
        if (aList.size() == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aList.size() - 1; i++){
            sb.append(aList.get(i)).append(",");
        }
        sb.append(aList.get(aList.size() - 1));
        return sb.toString();
    }

    @TypeConverter
    @NonNull
    public ArrayList<String> StringToArray (String s){
        ArrayList<String> aList = new ArrayList<>(Arrays.asList(s.split("[,]")));
        return aList;
    }
}
