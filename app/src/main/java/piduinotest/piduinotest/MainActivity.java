package piduinotest.piduinotest;


import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.piduinotest.piduinotest.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********************************/
         /*    Define all the buttons    */
        /********************************/

        Button sup = (Button) findViewById(R.id.Up);
        Button sdown = (Button) findViewById(R.id.Down);
        Button sleft = (Button) findViewById(R.id.Left);
        Button sright = (Button) findViewById(R.id.Right);

        /*******************************************************/
         /*  Set an onclick/onchange listener for every button  */
        /*******************************************************/

        sup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* button is led 1 */
                    new Background_get().execute("led1=1");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    new Background_get().execute("led1=0");
                }
                return true;
            }
        });

        sdown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* button is led 2 */
                    new Background_get().execute("led2=1");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    new Background_get().execute("led2=0");
                }
                return true;
            }
        });

        sleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* button is led 3 */
                    new Background_get().execute("led3=1");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    new Background_get().execute("led3=0");
                }
                return true;
            }
        });
        sright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    /* button is led 4 */
                    new Background_get().execute("led4=1");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    new Background_get().execute("led4=0");
                }
                return true;
            }
        });
    }



        /*****************************************************/
       /*  This is a background process for connecting      */
      /*   to the arduino server and sending               */
     /*    the GET request withe the added data           */
    /*****************************************************/

    private class Background_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                /* Change the IP to the IP you set in the arduino sketch */
                URL url = new URL("http://10.238.203.202/?" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
