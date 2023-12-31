package com.example.medievalrebuild;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;




public class MyAlertDialog {

    public interface DialogCallBack {
        void onTextEnteredForPlayerName(String enteredText);
        void onTextEnteredForOtherPurpose(String enteredText);
    }



    private DialogCallBack callBack;

    private String enteredText ="";

    public MyAlertDialog(Context context, DialogCallBack callBack){

        this.callBack = callBack;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter Text");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", ((dialog, which) -> {

           enteredText = input.getText().toString();

           System.out.println ("Testing "+ enteredText);
            callBack.onTextEnteredForPlayerName(enteredText);
            callBack.onTextEnteredForOtherPurpose(enteredText);
            dialog.dismiss();

        }));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public String getEnteredText(){
        return enteredText;
    }

}
