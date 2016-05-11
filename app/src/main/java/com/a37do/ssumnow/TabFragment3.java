package com.a37do.ssumnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

/**
 * Created by hhylu on 2016-05-05.
 */
public class TabFragment3 extends Fragment {
    private ImageView profileImage;
    private TextView name;
    private TextView age;
    private TextView gender;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tab_fragment_3, container, false);
        profileImage = (ImageView) inflate.findViewById(R.id.profileImage);
        name = (TextView) inflate.findViewById(R.id.name);
        age = (TextView) inflate.findViewById(R.id.age);
        gender = (TextView) inflate.findViewById(R.id.gender);
        setInitialProfile();

        Button button = (Button) inflate.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testOnClickLogout();
            }
        });

        return inflate;
    }

    public void setInitialProfile() {
        //get extra from intent passed on from SignUpActivity.class
        Intent intent = (getActivity()).getIntent();
        String profileImageValue = intent.getStringExtra(SignUpActivity.PROFILEIMAGE_KEY);
        String nameValue = intent.getStringExtra(SignUpActivity.NAME_KEY);
        String ageValue = intent.getStringExtra(SignUpActivity.AGE_KEY);
        String genderValue = intent.getStringExtra(SignUpActivity.GENDER_KEY);

        new DownloadImageTask(profileImage).execute(profileImageValue);

        name.setText(nameValue);
        age.setText(ageValue);
        gender.setText(genderValue);
    }


    private void testOnClickLogout() {
        UserManagement.requestUnlink(new UnLinkResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {

            }

            @Override
            public void onNotSignedUp() {

            }

            @Override
            public void onSuccess(Long result) {
                final Intent intent = new Intent((getContext()), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                (getActivity()).finish();
            }
        });
    }
}
