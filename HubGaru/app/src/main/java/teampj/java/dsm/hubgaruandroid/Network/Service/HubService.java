package teampj.java.dsm.hubgaruandroid.Network.Service;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import teampj.java.dsm.hubgaruandroid.Network.APIAdapter;

/**
 * Created by user on 2017-09-22.
 */

public class HubService extends APIAdapter {

    public static HubApi getRetrofit (Context context) {
        return (HubApi) retrofit(context, HubApi.class);
    }

    public interface HubApi {
//        허브 목록가져오기
//        좋아요 +1
//        좋아요 -1
//        허브 만들고 올리기
//        허브 삭제하기
/*
        @GET("/hub")
        Call<JSONArray>

        @GET("/user/garu")
        Call<JSONArray>

        @GET("/user/info")
        Call<JSONObject>*/


    }
}