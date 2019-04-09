package com.example.app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



// REGISTER DOES NOT WORK


public class MainActivity extends AppCompatActivity {

    String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = ReturnExpense.getUser("trackdatcash.com/expenses/codeMount", "5ca6da956d073a0017df78f6");
        TextView tv = findViewById(R.id.tvText);
        tv.setText(test);
    }


    private class helper extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            // json calls
            test = ReturnExpense.getUser("trackdatcash.com/expenses/codeMount", "5ca6da956d073a0017df78f6");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // textview calls
            TextView tv = findViewById(R.id.tvText);
           tv.setText(test);
            super.onPostExecute(aVoid);
        }
    }

    // delete route - Not a post route so we wont use it
//    public static String delete(String url){
//        JSONObject payload = new JSONObject();
//
//    }
//

    // route to return specific expense in database
    // NOT USING EXPENSESROUTES.ROUTE('/:ID').GET

}

