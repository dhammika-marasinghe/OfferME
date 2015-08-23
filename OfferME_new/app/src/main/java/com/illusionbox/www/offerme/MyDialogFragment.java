package com.illusionbox.www.offerme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Janitha on 8/24/2015.
 */
public class MyDialogFragment extends DialogFragment implements View.OnClickListener {

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("Take offer?");

        theDialog.setMessage("Are you sure you want to take this offer?");

        theDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Thank you!", Toast.LENGTH_SHORT).show();
            }
        });

        theDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        return super.onCreateDialog(savedInstanceState);
    }*/
    Button yes,no;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dialog, null);
        yes = (Button) view.findViewById(R.id.buttonPositive);
        no = (Button) view.findViewById(R.id.buttonNegative);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonPositive){
            dismiss();
            Toast.makeText(getActivity(), "Thank you!", Toast.LENGTH_SHORT).show();
        }
        else{
            dismiss();
            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
