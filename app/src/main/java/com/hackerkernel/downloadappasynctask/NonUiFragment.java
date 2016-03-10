package com.hackerkernel.downloadappasynctask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NonUiFragment extends Fragment {
    public MyTask myTask;
    private Activity activity;

    public NonUiFragment(){

    }

    public void beginTask(String... ars){
        myTask = new MyTask(activity);
        myTask.execute(ars);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        if (myTask != null){
            myTask.onAttach(activity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (myTask != null){
            myTask.onDetach();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        Log.d("HUS","HUS: onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("HUS", "HUS: onCreateView");
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("HUS", "HUS: onActivityCreated");
        //Make this fragment indestructible
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("HUS", "HUS: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("HUS", "HUS: onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("HUS", "HUS: onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("HUS", "HUS: onSaveInstanceState");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("HUS", "HUS: onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("HUS", "HUS: onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("HUS", "HUS: onDestroy");
    }
}
