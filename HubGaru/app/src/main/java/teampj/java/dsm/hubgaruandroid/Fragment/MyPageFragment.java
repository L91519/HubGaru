package teampj.java.dsm.hubgaruandroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import teampj.java.dsm.hubgaruandroid.R;

/**
 * Created by user on 2017-08-22.
 */

public class MyPageFragment extends Fragment{

    ImageView profilePic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_page, container, false);

        profilePic = (ImageView) view.findViewById(R.id.profilePic);

        Glide.with(getActivity())
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQb5Vi5Fqf2PHGdMK-3ddX2s9lQ-NoyejJET1NPcFvPktS0KUDX")
                .apply(RequestOptions.bitmapTransform(new CircleCrop(getActivity())))
                .into(profilePic);

        return view;
    }
}