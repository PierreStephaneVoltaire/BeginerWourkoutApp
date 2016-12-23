package com.example.steph.beginerwourkoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AddRoutineFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "detailFragment";

    public AddRoutineFragment() {
    }

    Activity parentactivity;
    String routine;
    Button btnedit, btnview, btndelete;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        parentactivity = (Activity) context;

        Bundle bundle = getArguments();

        routine = bundle.getString("routine");
    }

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_routine_fragment, container, false);
        TextView txtDisplay = (TextView) view.findViewById(R.id.textView2);
        btnview = (Button) view.findViewById(R.id.view);
        btnedit = (Button) view.findViewById(R.id.edit);
        btndelete = (Button) view.findViewById(R.id.delete);

        txtDisplay.setText(routine);

        btnedit.setOnClickListener(this);
        btnview.setOnClickListener(this);
        btndelete.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == view.findViewById(R.id.delete)) {
            dbhandler DBhandler = new dbhandler(getContext());
            DBhandler.deleteRoutine(routine);
            Toast.makeText(getContext(), routine + " deleted", Toast.LENGTH_SHORT).show();
            FragmentManager transact = getFragmentManager();
            transact.popBackStack();

        } else if (view == view.findViewById(R.id.edit)) {

            Intent intent = new Intent(getContext(), AddRoutineActivity.class);
            dbhandler DBhandler = new dbhandler(getContext());
            Routine routineObj = DBhandler.selectRoutine(routine);
            intent.putExtra("RoutineObj", routineObj);
            startActivity(intent);

        } else if (view == view.findViewById(R.id.view)) {
            Intent intent = new Intent(getContext(), ViewRoutineActivity.class);
            dbhandler DBhandler = new dbhandler(getContext());
            Routine routineObj = DBhandler.selectRoutine(routine);
            intent.putExtra("RoutineObj", routineObj);
            startActivity(intent);
        }


    }

}
