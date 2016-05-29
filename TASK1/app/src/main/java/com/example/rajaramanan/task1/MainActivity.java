package com.example.rajaramanan.task1;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int c ;
    TextView displaytext;
    Button clickbutton;
    Button resetbutton;
    RelativeLayout layout;
    String c2;


    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        c2=displaytext.getText().toString();
        outState.putString("key1",c2);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        displaytext.setText(savedInstanceState.getString("key1"));
        c=Integer.parseInt(displaytext.getText().toString());
        change(c);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        clickbutton = (Button)findViewById(R.id.clickbutton);
        resetbutton = (Button)findViewById(R.id.resetbutton);
        displaytext = (TextView)findViewById(R.id.displaytext);
        SharedPreferences backup = getSharedPreferences("key",MODE_PRIVATE);
        c= backup.getInt("count", 0);
        displaytext.setText(Integer.toString(c));
        change(c);



        clickbutton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v)
                    {

                        c++;
                        SharedPreferences backup =getSharedPreferences("key",MODE_PRIVATE);
                        SharedPreferences.Editor backup_edit= backup.edit();
                        backup_edit.putInt("count",c);
                        backup_edit.commit();

                        displaytext.setText(Integer.toString(c));

                        change(c);
                    }
                }
        );
        resetbutton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public  void onClick(View s)
                    {
                        c=0;
                        SharedPreferences backup =getSharedPreferences("key",MODE_PRIVATE);
                        SharedPreferences.Editor backup_edit= backup.edit();
                        backup_edit.putInt("count",0);
                        backup_edit.commit();
                        displaytext.setText(Integer.toString(0));
                        change(0);

                    }
                }
        );


    }

    public void change(int a)
    {

        layout=(RelativeLayout)findViewById(R.id.layout);
        if(a==0)
        {
            layout.setBackgroundColor(Color.GREEN);

        }
        else
        {
            a=a%6;
            switch (a)
            {
                case 0:
                    layout.setBackgroundColor(Color.RED);

                    break;
                case 1:
                    layout.setBackgroundColor(Color.YELLOW);

                    break;
                case 2:
                    layout.setBackgroundColor(Color.BLUE);

                    break;
                case 3:
                    layout.setBackgroundColor(Color.GRAY);

                    break;
                case 4:
                    layout.setBackgroundColor(Color.MAGENTA);

                    break;
                case 5:
                    layout.setBackgroundColor(Color.CYAN);

                    break;
            }
        }
    }


}


