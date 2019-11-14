package com.example.ryan.mineseeker.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.ryan.mineseeker.R;

/**
 * Created by ryan on 16/02/18.
 */

public class MessageFragment extends AppCompatDialogFragment {

    @SuppressLint("StringFormatInvalid")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //create the view to show
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.winmessage_layout, null);
        // Create a button Listener
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TAG", "you clicked dialog button!!!");
                getActivity().finish();
            }
        };
        //Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle(getResources().getString(R.string.congrats_msg, null))
                .setView(v).setPositiveButton(android.R.string.ok, listener)
                .create();
    }

}
