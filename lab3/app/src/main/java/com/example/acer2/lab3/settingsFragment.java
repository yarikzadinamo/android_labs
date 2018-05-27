package com.example.acer2.lab3;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class settingsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragment, container, false);


        TextView rb1 = view.findViewById(R.id.radioButton1);
        Typeface TF1 = Typeface.SANS_SERIF;
        rb1.setTypeface(TF1);

        TextView rb2 = view.findViewById(R.id.radioButton2);
        Typeface TF2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Inspiration.ttf");
        rb2.setTypeface(TF2);


        Button okButton = view.findViewById(R.id.button_ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputMessage();
            }
        });

        Button cancelButton = view.findViewById(R.id.button_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelInput();
            }
        });

        Button openButton = view.findViewById(R.id.button_open);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile();
            }
        });

        if (savedInstanceState != null) {

            String savedInputText = savedInstanceState.getString(inputTextViewKey);
            EditText editText = view.findViewById(R.id.editText);

            editText.setText(savedInputText);

            int savedSelectedFont = savedInstanceState.getInt(radioButtonKey);
            if (savedSelectedFont != -1) {

                RadioGroup radioGroup = view.findViewById(R.id.fontSelection);
                radioGroup.check(savedSelectedFont);
            }
        }

        return view;
    }

    public static final String EXTRA_MESSAGE = "com.potylchak.lab3.MESSAGE";

    final static String FILE_NAME = "content.txt";

    final static String inputTextViewKey = "INPUT_TEXTVIEW_TEXT";

    final static String radioButtonKey = "FONT_RADIOBUTTON_ID";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        View view = getView();

        EditText editText = view.findViewById(R.id.editText);
        outState.putString(inputTextViewKey, editText.getText().toString());

        RadioGroup radioGroup = view.findViewById(R.id.fontSelection);
        outState.putInt(radioButtonKey, radioGroup.getCheckedRadioButtonId());

        super.onSaveInstanceState(outState);
    }

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(String message, Typeface typeface);
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {

            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {

            throw new ClassCastException(context.toString() + " будь ласка, реалізуйте OnFragmentInteractionListener");
        }
    }

	
 void cancelInput() {

        EditText editText = getView().findViewById(R.id.editText);

        editText.setText("");

        RadioGroup radioGroup = getView().findViewById(R.id.fontSelection);

        radioGroup.clearCheck();
    }

	
 void saveText(String text) {

        FileOutputStream fos = null;

        try {

            fos = getActivity().openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write(text.getBytes());
            fos.write("\n".getBytes());
        } catch(IOException ex) {

            Toast.makeText(getActivity(), ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {

            try {
                if(fos != null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(getActivity(), ex.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

	 void outputMessage() {

        View view = getView();

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        EditText editText = view.findViewById(R.id.editText);
        RadioGroup radioGroup = view.findViewById(R.id.fontSelection);

        int checkedId = radioGroup.getCheckedRadioButtonId();

        if (checkedId != -1) {

            String enteredText = editText.getText().toString();

            if (!enteredText.isEmpty()) {

                TextView checkedButtonText = radioGroup.findViewById(checkedId);

                mListener.onFragmentInteraction(editText.getText().toString(), checkedButtonText.getTypeface());

                saveText(enteredText);

            }
        }
    }



    private void openFile() {
        FileInputStream fin = null;
        String fileContent = null;

        try {

            fin = getActivity().openFileInput(FILE_NAME);
            if (fin.available() > 0) {
                byte[] bytes = new byte[fin.available()];
                fin.read(bytes);
                fileContent = new String(bytes);
            } else {
                Toast.makeText(getActivity(), "File is empty", Toast.LENGTH_SHORT).show();
            }
        }
        catch(IOException ex) {

            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        if (fileContent != null) {

            Intent intent = new Intent(getActivity(), FileActivity.class);

            intent.putExtra(EXTRA_MESSAGE, fileContent);

            startActivity(intent);
        }
    }
}
