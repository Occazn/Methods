package com.example.app;

import org.json.JSONObject;

public class ReturnExpense {

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
            getUser = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(getUser == "error")
                return "error";

            return getUser;
        }
        catch(Exception ex) {
            return "error in ex";
        }
    }


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
            returnAll = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(returnAll == "error")
                return "error";

            return returnAll;
        }
        catch(Exception ex) {
            return "error";
        }
    }





    // Route to return all expenses for a specific month
    // Will return "error" on error
    //
    // trackdatcash.herokuapp.com/month/:newMonth

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


    // Route to return all expenses for a specific category
    // Will return "error" on error
    // trackdatcash.herokuapp.com/category/:newCategory
    public static String getCategory(String url, String userId){
        JSONObject payload = new JSONObject();
        String getCategory;
        try{
            payload.put("userId", userId);
        }
        catch(Exception ex){
            return "error";
        }

        try{
            getCategory = JsonIo.doJsonIo(url, payload.toString()).toString();
            if(getCategory == "error")
                return "error";

            return getCategory;
        }
        catch(Exception ex) {
            return "error";
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
}
