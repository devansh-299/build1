package com.piworks.build1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class today_fragment extends Fragment {

    private TextView today_amnt;
    public today_fragment(){}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        today_amnt = (TextView)getActivity().findViewById(R.id.today_amount);
        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getMyData();
        // Displaying the user details on the screen
       today_amnt.setText(myDataFromActivity);
    }
}

