package com.example.webviewdemo;


import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ToolFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tools.log("ToolFragment onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Tools.log("ToolFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Tools.log("ToolFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Tools.log("ToolFragment onResume");
    }

    @Override
    public void onPause() {
        Tools.log("ToolFragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Tools.log("ToolFragment onStop");
    }

    @Override
    public void onDestroy() {
        Tools.log("ToolFragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Tools.log("ToolFragment onDestroyView");
    }

}
