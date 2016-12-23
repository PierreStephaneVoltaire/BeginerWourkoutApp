package com.example.steph.beginerwourkoutapp;

/**
 * Created by steph on 11/20/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LstFragment extends ListFragment implements View.OnClickListener {
    public LstFragment() {
    }

    Button btn;
    OnworkoutlineSelectedListener mCallback;

    public interface OnworkoutlineSelectedListener {
        public void onRoutineSelected(int position, String routinename);

        public void addOrReplaceRoutine();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragmentlayout, container, false);
        btn = (Button) rootView.findViewById(R.id.addbtn);
        btn.setOnClickListener(this);
        // Create an array of string to be data source of the ListFragment
        dbhandler db = new dbhandler(getContext());
        ArrayList<String> routineArrayList = db.selectAllRoutines();
        List<String> list = new ArrayList<String>();

        int size = routineArrayList.size();
        for (int i = 0; i < size; i++) {
            list.add(routineArrayList.get(i));

        }

        String[] datasource = list.toArray(new String[list.size()]);
        for (int p = 0; p < size; p++) {

            datasource[p] = routineArrayList.get(p);
        }
        // Create ArrayAdapter object to wrap the data source
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.rowlayout, R.id.txtitem, datasource);
        // Bind adapter to the ListFragment
        setListAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);


        return rootView;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnworkoutlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }


    }

    // Handle Item click event

    @Override
    public void onResume() {
        super.onResume();
        //  Intent intent=new Intent(getContext(),RoutineActivity.class);
        // startActivity(intent);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Send the event to the host activity
        ViewGroup viewGroup = (ViewGroup) v;
        Bundle bundle = new Bundle();
        TextView RoutineName = (TextView) viewGroup.findViewById(R.id.txtitem);
        bundle.putString("routine", RoutineName.getText().toString());
        String routineN = RoutineName.getText().toString();
        mCallback.onRoutineSelected(position, routineN);
    }


    @Override
    public void onClick(View view) {
        mCallback.addOrReplaceRoutine();


    }


}