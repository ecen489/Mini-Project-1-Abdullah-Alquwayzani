package com.example.miniproject1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class topicsList extends Fragment {
    public ListView LV; public TextView scoreView; public TextView highView;
    public topicsList() {
        // Required empty public constructor
    }
    public topicListInterface sendChoice;
    public interface topicListInterface{
        void onItemSselected(int position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_topics_list, container, false);
        LV = (ListView) view.findViewById(R.id.listView);
        scoreView = (TextView) view.findViewById(R.id.scoreView);
        highView = (TextView) view.findViewById(R.id.highView);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendChoice.onItemSselected(position);
            }
        };
        LV.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof topicListInterface)
        sendChoice = (topicListInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendChoice=null;
    }

    public void setScore(int score){
        scoreView.setText(Integer.toString(score));
    }
    public void setHighScore(int score){
        highView.setText(Integer.toString(score));
    }
}
