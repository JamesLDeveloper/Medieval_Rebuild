package com.example.medievalrebuild;

import com.example.medievalrebuild.MainActivity;
import com.example.medievalrebuild.Game.Player;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

import java.io.Serializable;

public class MyAlertDialog implements Serializable {


    private static final long serialVersionUID = 1L;


    public interface DialogCallBack {
        void onTextEnteredForPlayerName(String enteredText);
        void onTextEnteredForOtherPurpose(String enteredText);
    }

private  Context context;
private MainActivity mainActivity;
    private DialogCallBack callBack;

    private String enteredText;

    private String setTitleString;

    private boolean save;

    private boolean playerCreationPending;


    public MyAlertDialog(Context context, DialogCallBack callBack, String setTitleString, boolean save/*, boolean playerCreationPending*/) {

        this.callBack = callBack;
        this.context = context;
        this.setTitleString = setTitleString;
        this.save = save;
 //       this.playerCreationPending = playerCreationPending;

showAlertDialog();
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);


        builder.setTitle(setTitleString);

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", ((dialog, which) -> {


                enteredText = input.getText().toString();
               System.out.println ("Testing "+ enteredText);


               if (!save){
                   //mainActivity.onTextEnteredForPlayerName(enteredText);
               callBack.onTextEnteredForPlayerName(enteredText);
    }

               if(save) {
                  // mainActivity.onTextEnteredForOtherPurpose(enteredText);
                callBack.onTextEnteredForOtherPurpose(enteredText);
            }

               dialog.dismiss();


        }));

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public String getEnteredText(){
        return enteredText;
    }

}
