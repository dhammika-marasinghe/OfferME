package com.illusionbox.www.offerme;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SettingsActivity extends ActionBarActivity {

    boolean subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Intent fromActivity = getIntent();

        try {
            int radius = fromActivity.getExtras().getInt("radius");
            EditText radius_text = (EditText) this.findViewById(R.id.radius_text);
            radius_text.setText(radius+"", TextView.BufferType.EDITABLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            subscribe = true;
            return true;
        }
        subscribe = false;
        return super.onOptionsItemSelected(item);
    }

    public void goBack(View view){
        Intent Back = new Intent(SettingsActivity.this, MapsActivity.class);
        EditText radius = (EditText)this.findViewById(R.id.radius_text);
        String string = radius.getText().toString();

        Back.putExtra("radius", Integer.parseInt(string));
        Back.putExtra("subscribe", subscribe);
        startActivity(Back);
    }

    public void cancel(View view){
        finish();
    }
}
