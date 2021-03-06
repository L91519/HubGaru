package teampj.java.dsm.hubgaruandroid.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teampj.java.dsm.hubgaruandroid.Network.Service.HubService;
import teampj.java.dsm.hubgaruandroid.R;

/**
 * Created by user on 2017-10-03.
 */

public class LogInActivity extends AppCompatActivity {
    ImageView backgrountImg, loginIcon;
    Button loginBtn;
    TextInputEditText idText, pwText;
    View createBtn;
    String sId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        idText = (TextInputEditText) findViewById(R.id.idText);
        pwText = (TextInputEditText) findViewById(R.id.pwText);
        loginIcon = (ImageView) findViewById(R.id.loginIcon);
        backgrountImg = (ImageView) findViewById(R.id.backgroundImage);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        createBtn = (LinearLayout) findViewById(R.id.createBtn);

        backgrountImg.setAlpha(127);

        Glide.with(this).load(R.drawable.splash_icon)
                .apply(RequestOptions.bitmapTransform(new CircleCrop(this)))
                .into(loginIcon);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sId = idText.getText().toString();
                String sPw = pwText.getText().toString();

                LogIn(sId, sPw);
            }
        });
    }

    public void LogIn(final String id, String pw) {
        HubService.getRetrofit(getApplicationContext()).singIn(id, pw).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try{
                if(response.code() == 201) {
                    sId = id;
                    getInfo(id);
                }
                else if(response.code() == 204) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
                } catch (Resources.NotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Account doesn't exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                Log.d(t.toString(), "errorMsg");
            }
        });
    }

    public void getInfo(String id) {
        HubService.getRetrofit(getApplicationContext()).getInfo(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject element = response.body().getAsJsonObject("user");

                    String part = element.getAsJsonPrimitive("part").getAsString();
                    String intro = element.getAsJsonPrimitive("intro").getAsString();
                    String picture = "http://www.freeiconspng.com/uploads/person-icon--icon-search-engine-3.png";
                    String name = element.getAsJsonPrimitive("name").getAsString();
                    String phone = element.getAsJsonPrimitive("phone").getAsString();

                    Intent intent = new Intent(LogInActivity.this, TabLayoutActivity.class);

                    intent.putExtra("part", part);
                    intent.putExtra("intro", intro);
                    intent.putExtra("picture", picture);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("id", sId);

                    startActivity(intent);
                    finish();
                }
                else if (response.code() == 400) {
                    Toast.makeText(getApplicationContext(), "Fail!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

}