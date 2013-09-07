package biz.lungo.xo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View v){
        v.getId();
        Intent myIntent = new Intent(this, GameActivity.class);
        String value = "";
        myIntent.putExtra("key", value);
        this.finish();
        this.startActivity(myIntent);
    }

    public void onButtonClickAbout(View v){
        v.getId();
        AlertDialog.Builder about = new AlertDialog.Builder(this);
        about.setTitle("О программе");
        about.setMessage("\"Крестики-нолики\" \nВерсия: 0.0.1 \nРазработчик: Серебряков Илья (Lungo)");
        about.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        about.show();
    }
}
