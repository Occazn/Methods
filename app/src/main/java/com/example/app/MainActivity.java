package com.example.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.util.Arrays;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;

import java.io.UnsupportedEncodingException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // pass in url, token (from login method), secret and returns userId
    // No url because this is the jjwt. MUST USE LOGIN METHOD FIRST TO GET JWT
    public static String getUserId(String token, String secret){
        String encodedSecret = Base64.encodeToString(secret.getBytes(), Base64.DEFAULT);

        // return the UserID using the jwt
        final String userId;
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()         // (1)
                    .setSigningKey(encodedSecret)         // (2)
                    .parseClaimsJws(token); // (3)
            userId = jws.toString();
            return userId;
            // we can safely trust the JWT
        }
        catch (JwtException ex) {       // (4)
            // we *cannot* use the JWT as intended by its creator
            return "error";
        }
    }

    // login returns jwt, use jwt in getUserId to get user id
    // returns "error" on error, and jwt on
    // trackdatcash.herokuapp.com/login
    public static String login(String url, String email, String password){
        JSONObject payload = new JSONObject();
        String token;

        JSONArray temp = new JSONArray();

        // create payload
        try{
            payload.put("email", email);
            payload.put("password", password);

        }
        catch(Exception ex){
            return "error";
        }

        // grab jwt from login, otherwise return error
        try{
            token = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(token == "error")
                return "error";

            return token;
        }
        catch(Exception ex){
            return "error";
        }
    }

    // pass in url, and name/email/password as EditText.getText().toString()
    // on success, will return string of true and string of false on error
    public static String register(String url, String name, String email, String password){
        JSONObject payload = new JSONObject();

        String register;

        try{
            payload.put("name", name);
            payload.put("email", email);
            payload.put("password", password);

            register = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(register == "error")
                return "error";

            return "true";
        }
        catch(Exception ex){
            return "false";
        }
    }

    // returns allExpenses for a user
    // if there was an error, returns "error"
    // otherwise returns allExpenses
    // trackdatcash.herokuapp.com/getAllExpenses
    public static String getAllExpenses(String url, String userId){
        String allExpenses;
        JSONObject payload = new JSONObject();

        // create payload
        try{
            payload.put("id", userId);
        }
        catch(Exception ex){
            return "error";
        }

        // return all expenses as string
        try{
            allExpenses = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(allExpenses == "error")
                return "error";
            return allExpenses;
        }
        catch(Exception ex){
            return "error";
        }
    }

    // delete route - Not a post route so we wont use it
//    public static String delete(String url){
//        JSONObject payload = new JSONObject();
//
//    }
//

    // Update id with URL and editText.getText().toString()
    // if update route returns "error", there was an error
    // if returns "true", successful
    // trackdatcash.herokuapp.com/update/:id
    // IF THERE IS NO GROUPCODE, USE STRING OF "none"
    public static String update(String url, String description, String amount, String category,
                                String month, String day, String year, String groupCode){

        JSONObject payload = new JSONObject();
        String update = "";
        try{
            payload.put("description", description);
            payload.put("amount", amount);
            payload.put("category", category);
            payload.put("month", month);
            payload.put("day", day);
            payload.put("year", year);
            if(groupCode == "none"){

            }
            else
               payload.put("groupCode", groupCode);
        }
        catch(Exception ex){
            return "error";
        }

        try{
            update = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(update == "error"){
                return "error";
            }
            else{
                return "true";
            }
        }
        catch(Exception ex){
            return "error";
        }
    }

    // will return 'expense': 'expense added successfully' on success
    // returns "error" on error
    // trackdatcash.herokuapp.com/add
    // IF THERE IS NO GROUPCODE, JUST USE STRING OF "none"
    public static String add(String url, String userid, String description, String amount, String category,
                             String month, String day, String year, String groupCode){
        JSONObject payload = new JSONObject();
        String add;

        try{
            payload.put("userid", userid);
            payload.put("description", description);
            payload.put("amount", amount);
            payload.put("category", category);
            payload.put("month", month);
            payload.put("day", day);
            payload.put("year", year);
            if(groupCode == "none"){

            }
            else
                payload.put("groupCode", groupCode);

            add = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(add == "error")
                return "error";

            return add;
        }
        catch(Exception ex){
            return "error";
        }
    }


    // route to return specific expense in database
    // NOT USING EXPENSESROUTES.ROUTE('/:ID').GET



    // route to return all expenses with a specific group code
    // returns "error" on error
    // returns all expenses with groupcode
    // trackdatcash.herokuapp.com/code/:thisCode
    // Although thisCode will be in the parameters (Url), we will need to pass it as a String as groupCode
    public static String returnAll(String url, String groupCode){
        JSONObject payload = new JSONObject();
        String returnAll;
        try{
            payload.put("groupCode", groupCode);
        }
        catch(Exception ex){
            return "error";
        }

        try{
            returnAll = JsonIo.doJsonIo(url, groupCode.toString()).toString();
            if(returnAll == "error")
                return "error";

            return returnAll;
        }
        catch(Exception ex) {
            return "error";
        }
    }


    // This returns our User model
    // Will contain every field, but we will mainly use this to get the GROUP CODE
    // trackdatcash.herokuapp.com/codeMount
    public static String getUser(String url, String userId){
        JSONObject payload = new JSONObject();
        String getUser;
        try{
            payload.put("userId", userId);
        }
        catch(Exception ex){
            return "error";
        }

        try{
            getUser = JsonIo.doJsonIo(url, userId.toString()).toString();
            if(getUser == "error")
                return "error";

            return getUser;
        }
        catch(Exception ex) {
            return "error";
        }
    }


    // Route to return all expenses for a specific month

    // trackdatcash.herokuapp.com/code/:thisCode
    public static String getMonth(String url, String userId){
        JSONObject payload = new JSONObject();
        String getMonth;
        try{
            payload.put("userId", userId);
        }
        catch(Exception ex){
            return "error";
        }

        try{
            getMonth = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(getMonth == "error")
                return "error";

            return getMonth;
        }
        catch(Exception ex) {
            return "error";
        }
    }







}

