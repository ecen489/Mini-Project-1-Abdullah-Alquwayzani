package com.example.miniproject1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class question extends Fragment {
    public TextView TV; public EditText ET;public Button b;

    public question() {
        // Required empty public constructor
    }
    public questionInterface sendAnswer;
    public interface questionInterface{
        void onAnswer(String answer);
    }
    public void setTextView(String sss){
        TV.setText(sss);
    }
    public String getEditText(){
        return ET.getText().toString();
    }
    public void setEditHint(String sss){
        ET.setHint(sss);
    }
    public String getEditHint(){
        return ET.getHint().toString();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        TV = (TextView) view.findViewById(R.id.textView);
        ET = (EditText) view.findViewById(R.id.editText);
        b = (Button) view.findViewById(R.id.button);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //TV.setText(">"+ET.getText().toString()+"<>"+ET.getHint().toString()+"<");
                sendAnswer.onAnswer(ET.getText().toString());
                ET.setText("");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof questionInterface)
            sendAnswer = (questionInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendAnswer=null;
    }
}
