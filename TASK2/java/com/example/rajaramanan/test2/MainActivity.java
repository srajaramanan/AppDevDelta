package com.example.rajaramanan.test2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    int statusbarheight,actionbarheight;//to store the height of statusbar and actionbar
    int width,height,density;   // to calculate the height,width and dpi of the device
    int move=0;                 //to convert 10px to dp
    int imageheight,imagewidth; // viewimage height and width

    protected static final int RESULT_SPEECH = 1;

    ImageButton btnSpeak;       //button to start speech to text
    ImageView imageView;
    int changecount=0;


    int leftmargin,topmargin;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        statusbarheight=getStatusBarHeight();
        actionbarheight=getActionBarHeight();
        getvalues();
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        imageView = (ImageView)findViewById(R.id.imageview);
        imageheight=100*density/160;
        imagewidth=100*density/160;
        change();
       leftmargin=(width-imagewidth)/2;
       topmargin=(height-imageheight)/2;

        setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);//Alligning the image to center





        btnSpeak.setOnClickListener(
                new ImageButton.OnClickListener()
                {
                    public void onClick(View v)
                    {


                        Intent intent = new Intent(
                                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                        try {
                            startActivityForResult(intent, RESULT_SPEECH);
                            //Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

                        } catch (ActivityNotFoundException a) {
                            Toast t = Toast.makeText(getApplicationContext(),
                                    "Opps! Your device doesn't support Speech to Text",
                                    Toast.LENGTH_SHORT);
                            t.show();
                        }












                    }
                }
        );
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String input=text.get(0);//speech to text
                    Toast.makeText(getApplicationContext(),input,Toast.LENGTH_LONG).show();
                    if (Objects.equals(input, "right")) {
                        moveright();
                    } else if (Objects.equals(input, "left")) {
                        moveleft();
                    } else if (Objects.equals(input, "up")) {
                                moveup();
                            }else if (Objects.equals(input, "down")) {
                        movedown();
                    }


                    else if(Objects.equals(input,"small"))
                    {
                        small();
                    }

                    else if(Objects.equals(input,"large"))
                    {
                        large();
                    }

                    else if(Objects.equals(input,"normal"))
                    {
                        normal();
                    }


                    else if(Objects.equals(input,"change"))
                    {
                        changecount++;
                        change();
                    }


                }

                break;
            }

        }

    }

    //Functions

    public void moveright()
    {
        if(leftmargin+move<=(width-imagewidth) &&(flag==0))
        {
            leftmargin=leftmargin+move;
            setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"The shape cannot be moved  RIGHT further!!",Toast.LENGTH_LONG).show();
        }
    }

    public void moveleft()
    {
        if(leftmargin-move>=0 &&(flag==0))
        {
            leftmargin=leftmargin-move;
            setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"The shape cannot be moved  LEFT further!!",Toast.LENGTH_LONG).show();
        }
    }

    public void movedown()
    {
        if(topmargin+move<=(height-imageheight) &&(flag==0))
        {
            topmargin=topmargin+move;
            setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"The shape cannot be moved  DOWN further!!",Toast.LENGTH_LONG).show();
        }
    }

    public void moveup()
    {
        if(topmargin-move>=0 &&(flag==0))
        {
            topmargin=topmargin-move;
            setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"The shape cannot be moved  UP further!!",Toast.LENGTH_LONG).show();
        }
    }

    public void large()
    {
        setlayoutparams(width,height,0,0);
        flag=1;
    }

    public void small()
    {
       imageheight=10*density/160;
        imagewidth=10*density/160;
        setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        flag=0;
    }

    public void normal()
    {
        imageheight=100*density/160;
        imagewidth=100*density/160;
        setlayoutparams(imagewidth,imageheight,leftmargin,topmargin);
        flag=0;

    }


    public void setlayoutparams(int layoutwidth, int layoutheight,int lmargin,int tmargin)
    {
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(layoutwidth,layoutheight);
        params.leftMargin=lmargin;
        params.topMargin=tmargin;
        imageView.setLayoutParams(params);
    }

    public void change()
    {
        switch (changecount%3)
        {
            case 0 :  if (imageView != null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.square));}
                break;
            case 1 :  if (imageView != null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.circle));}
                break;

            case 2:  if (imageView != null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.oval));}
                break;







        }}

    public void getvalues()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm =(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        width=displayMetrics.widthPixels;
        height=(displayMetrics.heightPixels-(statusbarheight+actionbarheight));
        density = displayMetrics.densityDpi;
        move= 10*density/160;

    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private int getActionBarHeight() {
            int actionBarHeight = getSupportActionBar().getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }


}
    