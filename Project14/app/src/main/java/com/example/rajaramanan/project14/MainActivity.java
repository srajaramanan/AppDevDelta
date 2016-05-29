package com.example.rajaramanan.project14;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;



public class MainActivity extends AppCompatActivity {


    Button rajasbutton;
    Button resetbutton;
    TextView rajastext;
    RelativeLayout rajaslayout;
    int c ;
    // private SharedPreferences preferenceSettings;
    // private SharedPreferences.Editor preferenceEditor;

    //  private static final int PREFERENCE_MODE_PRIVATE = 0;





    // @Override
    //  public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    //    super.onSaveInstanceState(outState, outPersistentState);
    //  outState.putInt("COUNT",c);
    //}

    //@Override
    //public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
    //  super.onRestoreInstanceState(savedInstanceState, persistentState);
    //int c = savedInstanceState.getInt("COUNT");

    //   }


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        rajasbutton = (Button) findViewById(R.id.rajasbutton);
        rajastext = (TextView) findViewById(R.id.rajastext);

        //  c= preferenceSettings.getInt("key",0);


        try {

            String c1= Integer.toString(c);
            rajasbutton.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {

                            count();
                            //   preferenceEditor.putInt("key",c);

                        }

                    }
            );






        } catch (Exception e) {

        }
    }



    public void change(int a)
    {
        a=a%5;
        rajaslayout= (RelativeLayout)findViewById(R.id.rajaslayout);
        switch(a)
        {
            case 0 : rajaslayout.setBackgroundColor(Color.RED);
                break;
            case 1 : rajaslayout.setBackgroundColor(Color.GREEN);
                break;
            case 2 : rajaslayout.setBackgroundColor(Color.GRAY);
                break;
            case 3 : rajaslayout.setBackgroundColor(Color.YELLOW);
                break;
            case 4 : rajaslayout.setBackgroundColor(Color.BLUE);
                break;
        }
    }


    public void count() {
        c = c + 1;
        rajastext.setText(Integer.toString(c));
        change(c);
    }

}
