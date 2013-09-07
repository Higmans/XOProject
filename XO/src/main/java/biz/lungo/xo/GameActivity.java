package biz.lungo.xo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends Activity implements View.OnTouchListener {
    Activity activity;
    public int counter = 1;
    public final String[] X_OR_O = {"O", "X"};
    TextView topLeft, topCenter, topRight, centerLeft, centerCenter, centerRight, bottomLeft, bottomCenter, bottomRight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        activity = this;
        topLeft = (TextView) findViewById(R.id.topLeft);
        topCenter = (TextView) findViewById(R.id.topCenter);
        topRight = (TextView) findViewById(R.id.topRight);
        centerLeft = (TextView) findViewById(R.id.centerLeft);
        centerCenter = (TextView) findViewById(R.id.centerCenter);
        centerRight = (TextView) findViewById(R.id.centerRight);
        bottomLeft = (TextView) findViewById(R.id.bottomLeft);
        bottomCenter = (TextView) findViewById(R.id.bottomCenter);
        bottomRight = (TextView) findViewById(R.id.bottomRight);
        topLeft.setOnTouchListener(this);
        topCenter.setOnTouchListener(this);
        topRight.setOnTouchListener(this);
        centerLeft.setOnTouchListener(this);
        centerCenter.setOnTouchListener(this);
        centerRight.setOnTouchListener(this);
        bottomLeft.setOnTouchListener(this);
        bottomCenter.setOnTouchListener(this);
        bottomRight.setOnTouchListener(this);
    }

    public void endGame(View v){
        v.getId();
        Intent myIntent = new Intent(this, MainActivity.class);
        String value = "";
        myIntent.putExtra("key", value);
        this.finish();
        this.startActivity(myIntent);
    }
    public void restartGame(View v){
        v.getId();
        Intent myIntent = new Intent(this, GameActivity.class);
        String value = "";
        myIntent.putExtra("key", value);
        this.finish();
        this.startActivity(myIntent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView tv = (TextView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && "".equals(tv.getText())){
            tv.setText(X_OR_O[counter % 2]);
            if (checkWinner(tv, X_OR_O[counter % 2])){
                showWinnerDialog(X_OR_O[counter % 2]);
                return true;
            }
            counter++;
            if (counter > 9){
                showWinnerDialog("Ничья");
                return true;
            }
        }
        return true;
    }

    private void showWinnerDialog(String s) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Игра закончена");
        if (s.equals(X_OR_O[counter % 2])){
            alert.setMessage("Победил " + X_OR_O[counter % 2]);
        }
        else{
            alert.setMessage("Ничья!");
        }
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deactivateField();
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    private void deactivateField() {
        topLeft.setEnabled(false);
        topCenter.setEnabled(false);
        topRight.setEnabled(false);
        centerLeft.setEnabled(false);
        centerCenter.setEnabled(false);
        centerRight.setEnabled(false);
        bottomLeft.setEnabled(false);
        bottomCenter.setEnabled(false);
        bottomRight.setEnabled(false);
    }


    @SuppressWarnings("ConstantConditions")
    private boolean checkWinner(TextView tv, String currentXorO) {
        int x = Integer.parseInt(tv.getTag().toString().charAt(0) + "");
        int y = Integer.parseInt(tv.getTag().toString().charAt(1) + "");
        String checkArray[][] = {{topLeft.getText().toString(), topCenter.getText().toString(), topRight.getText().toString()},
                {centerLeft.getText().toString(), centerCenter.getText().toString(), centerRight.getText().toString()},
                {bottomLeft.getText().toString(), bottomCenter.getText().toString(), bottomRight.getText().toString()}};
        boolean output = false;
        switch (x){
            case 0:
                switch (y){
                    case 0:
                        if (checkArray[0][1].contains(currentXorO) && checkArray[0][2].contains(currentXorO) ||
                                checkArray[1][1].contains(currentXorO) && checkArray[2][2].contains(currentXorO) ||
                                checkArray[1][0].contains(currentXorO) && checkArray[2][0].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 1:
                        if (checkArray[0][0].contains(currentXorO) && checkArray[0][2].contains(currentXorO) ||
                                checkArray[1][1].contains(currentXorO) && checkArray[2][1].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 2:
                        if (checkArray[0][0].contains(currentXorO) && checkArray[0][1].contains(currentXorO) ||
                                checkArray[1][1].contains(currentXorO) && checkArray[2][0].contains(currentXorO) ||
                                checkArray[1][2].contains(currentXorO) && checkArray[2][2].contains(currentXorO)){
                            output = true;
                        }
                        break;
                }
                break;
            case 1:
                switch (y){
                    case 0:
                        if (checkArray[1][1].contains(currentXorO) && checkArray[1][2].contains(currentXorO) ||
                                checkArray[0][0].contains(currentXorO) && checkArray[2][0].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 1:
                        if (checkArray[0][0].contains(currentXorO) && checkArray[2][2].contains(currentXorO) ||
                                checkArray[0][1].contains(currentXorO) && checkArray[2][1].contains(currentXorO) ||
                                checkArray[0][2].contains(currentXorO) && checkArray[2][0].contains(currentXorO) ||
                                checkArray[1][0].contains(currentXorO) && checkArray[1][2].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 2:
                        if (checkArray[0][2].contains(currentXorO) && checkArray[2][2].contains(currentXorO) ||
                                checkArray[1][0].contains(currentXorO) && checkArray[1][1].contains(currentXorO)){
                            output = true;
                        }
                        break;
                }
                break;
            case 2:
                switch (y){
                    case 0:
                        if (checkArray[0][0].contains(currentXorO) && checkArray[1][0].contains(currentXorO) ||
                                checkArray[2][1].contains(currentXorO) && checkArray[2][2].contains(currentXorO) ||
                                checkArray[1][1].contains(currentXorO) && checkArray[0][2].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 1:
                        if (checkArray[2][0].contains(currentXorO) && checkArray[2][2].contains(currentXorO) ||
                                checkArray[0][1].contains(currentXorO) && checkArray[1][1].contains(currentXorO)){
                            output = true;
                        }
                        break;
                    case 2:
                        if (checkArray[0][0].contains(currentXorO) && checkArray[1][1].contains(currentXorO) ||
                                checkArray[0][2].contains(currentXorO) && checkArray[1][2].contains(currentXorO) ||
                                checkArray[2][0].contains(currentXorO) && checkArray[2][1].contains(currentXorO)){
                            output = true;
                        }
                        break;
                }
                break;
        }
        return output;
    }
}
