package com.tsungyi.mobilefactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheck = (Button) findViewById(R.id.button_check);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spnStartTime = (Spinner) findViewById(R.id.spinner_start_time);
                String startTime = spnStartTime.getSelectedItem().toString();
                Spinner spnEndTime = (Spinner) findViewById(R.id.spinner_end_time);
                String endTime = spnEndTime.getSelectedItem().toString();
                Spinner spnAppointedTime = (Spinner) findViewById(R.id.spinner_appoint_time);
                String appointedTime = spnAppointedTime.getSelectedItem().toString();

                TextView tvResult = (TextView) findViewById(R.id.textView_result);

                if (check(startTime, endTime, appointedTime)) {
                    tvResult.setText("Included!");
                }
                else{
                    tvResult.setText("Not Included!");
                }


            }
        });

    }



    public boolean check(String startTime, String endTime, String appointedTime ){

        Integer sTime = Integer.valueOf(startTime);
        Integer eTime = Integer.valueOf(endTime);
        Integer aTime = Integer.valueOf(appointedTime);

        if (sTime == eTime) {
            return true;
        }

        if(sTime > eTime){
            return (timeInPeriod(sTime, 24, aTime) || timeInPeriod(0, eTime, aTime));
        }

        return timeInPeriod(sTime, eTime, aTime);
    }


    private boolean timeInPeriod (Integer startTime, Integer endTime, Integer appointedTime ){
        if (startTime <= appointedTime && appointedTime < endTime){
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
