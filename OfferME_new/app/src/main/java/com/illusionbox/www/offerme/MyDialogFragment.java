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

    Button yes,no;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Take Offer?");
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
            DialogFragment d = new MyOfferDialog();
            d.show(getFragmentManager(), "Something");
            Toast.makeText(getActivity(), "Thank you!", Toast.LENGTH_SHORT).show();
        }
        else{
            dismiss();
            Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
