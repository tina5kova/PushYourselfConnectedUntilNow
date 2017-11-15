package com.example.valentinvaleanu.pushyourself;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Valentin Valeanu on 11/11/2017.
 */

public class AchievementFragment extends Fragment
{
    public AchievementFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.empty_activity2, container, false);
    }
}
