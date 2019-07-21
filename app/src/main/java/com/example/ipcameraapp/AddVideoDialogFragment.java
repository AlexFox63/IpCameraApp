package com.example.ipcameraapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddVideoDialogFragment extends DialogFragment {

    protected EditText ip_address;
    protected EditText login;
    protected EditText password;
    SharedPreferences sharedPreferences;


    public interface AddVideoDialogListener {

        void onDialogPositiveClick(final Bundle bundle);
        void onDialogNegativeClick(DialogFragment dialog);

    }

    AddVideoDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;
        a = (Activity) context;
        try {
            mListener = (AddVideoDialogListener) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement AddVideoDialogListener");
        }
    }

    public Dialog onCreateDialog(final Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = (View) layoutInflater.inflate(R.layout.dialog_custom, null);
        try {
            ip_address = view.findViewById(R.id.ip_address);
            login = view.findViewById(R.id.login);
            password = view.findViewById(R.id.password);
        }catch (Exception e){
            Toast.makeText(getContext(),"Error reading data", Toast.LENGTH_SHORT).show();
        }

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //sendResult();
                Bundle bundle = new Bundle();
                bundle.putString("IP", ip_address.getText().toString());
                bundle.putString("LOGIN", login.getText().toString());
                bundle.putString("PWD", password.getText().toString());
                mListener.onDialogPositiveClick(bundle);
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogNegativeClick(AddVideoDialogFragment.this);
                    }
                });
        builder.setView(view);

        return builder.create();
    }
}
