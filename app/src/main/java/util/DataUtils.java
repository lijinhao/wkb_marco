package util;

import android.content.Context;
import android.util.Log;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import entity.HomeCategory;

/**
 * Created by lijinhao on 2014/11/27.
 */
public class DataUtils {

    private final static String URL_HOME="http://c.open.163.com/mobile/recommend/v1.do?mt=aphone";

    private final static String URL_IMAGE = "http://oimagec3.ydstatic.com/image?url=%s&w=290&h=162&limit=0&gif=0";

    public final static String URL_DETAIL="http://mobile.open.163.com/movie/%s/getMoviesForAndroid.htm";

    public static List<HomeCategory> loadDate(Context context){
        String jsonString = "";
        try{
            //InputStream inputStream = context.getAssets().open("home_json");
            //jsonString = inputStream2String(inputStream);
            jsonString = HttpRequest.get(URL_HOME).accept("application/json").body();
        }catch (Exception ex){
            Log.e("DataUtils", "loadDate", ex);
        }
        Gson gson = new Gson();
        List<HomeCategory> data = gson.fromJson(jsonString, new TypeToken<List<HomeCategory>>(){}.getType());
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
        }catch (Exception ex){

        }
        return data;
    }

    public static List<HomeCategory> loadDateFromRemote(Context context){
        String jsonString = "";
        try{
            jsonString  =  HttpRequest.get(DataUtils.URL_HOME).accept("application/json").body();
        }catch (Exception ex){
            Log.e("DataUtils","loadDate",ex);
        }
        Gson gson = new Gson();
        List<HomeCategory> data = gson.fromJson(jsonString, new TypeToken<List<HomeCategory>>(){}.getType());
        return data;
    }

    public static String loadDetailDate(Context context,String contentId){
        String jsonString = "";
        try{
            jsonString  =  HttpRequest.get(String.format(DataUtils.URL_DETAIL,contentId)).accept("application/json").body();
        }catch (Exception ex){
            Log.e("DataUtils","loadDate",ex);
        }
        Gson gson = new Gson();
        List<HomeCategory> data = gson.fromJson(jsonString, new TypeToken<List<HomeCategory>>(){}.getType());
        return jsonString;
    }

    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return baos.toString();
    }
}
