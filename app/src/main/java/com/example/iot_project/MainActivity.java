package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private MqttAndroidClient client;
    private static final String SERVER_URI = "tcp://test.mosquitto.org:1883";
    private static final String TAG = "MainActivity";

    private DBHandler dbHandler;
    ColorUtils colorFinder = new ColorUtils();

    Home homeFragment = new Home();
    Library libraryFragment = new Library();
    Profile profileFragment = new Profile();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        connect();
        client.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {
                    System.out.println("Reconnected to : " + serverURI);
                    // Re-subscribe as we lost it due to new session
                    subscribe("rgb");

                } else {
                    System.out.println("Connected to: " + serverURI);
                    subscribe("rgb");
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("The Connection was lost.");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String newMessage = new String(message.getPayload());
                System.out.println("Incoming message: " + newMessage);
                String[] spStg = newMessage.split(",");
                int r, g, b;
                r = Integer.parseInt(spStg[0]) * 4;
                g = Integer.parseInt(spStg[1]) * 4;
                b = Integer.parseInt(spStg[2]) * 4;
                System.out.println("R : " + r + " G : " + g + " B :" + b);

                //avoid errors if the colors increase above 255 due to the x4 multiplication
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }

                String colorName = colorFinder.getNameWithSpaces(colorFinder.getColorNameFromRgb(r, g, b));

                String hexColor = colorFinder.colorToHex(r, g, b);

                String rgbColor = colorFinder.colorToRGB(hexColor);

                String hsvColor = colorFinder.colorToHsb(hexColor);

                String cmykColor = colorFinder.rgbToCmyk(hexColor);

                String timeStamp = colorFinder.getTime();

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewColor(colorName, hexColor, rgbColor, hsvColor, cmykColor, timeStamp);
                dbHandler.addNewPalette(hexColor, colorFinder.generateRandomColor(Color.parseColor(hexColor)), colorFinder.generateRandomColor(Color.parseColor(hexColor)),colorFinder.generateRandomColor(Color.parseColor(hexColor)),colorFinder.generateRandomColor(Color.parseColor(hexColor)));
                dbHandler.addNewPalette(hexColor, colorFinder.generateBrightColorPaletteR(r, g, b, 0), colorFinder.generateBrightColorPaletteR(r, g, b, 1), colorFinder.generateBrightColorPaletteR(r, g, b,2), colorFinder.generateBrightColorPaletteR(r, g, b,3));

                getSupportFragmentManager().executePendingTransactions();
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("homeFrag");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(currentFragment).commitNowAllowingStateLoss();
                fragmentTransaction.attach(currentFragment).commitAllowingStateLoss();
                fragmentTransaction.commit();

                int i = Integer.parseInt(newMessage);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
    }

    private void connect() {
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), SERVER_URI, clientId);
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAG, "onSuccess");
                    System.out.println(TAG + " Success. Connected to " + SERVER_URI);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, "onFailure");
                    System.out.println(TAG + " Oh no! Failed to connect to " + SERVER_URI);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe(String topicToSubscribe) {
        final String topic = topicToSubscribe;
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    System.out.println("Subscription successful to topic: " + topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    System.out.println("Failed to subscribe to topic: " + topic);
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, "homeFrag").commit();
                return true;

            case R.id.library:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, libraryFragment, "libraryFrag").commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment, "profileFrag").commit();
                return true;

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        try {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            Log.d("class", "items in backstack " + backStackEntryCount);
            if (backStackEntryCount > 0) {
                super.onBackPressed();
            } else if (bottomNavigationView.getSelectedItemId() == R.id.home && backStackEntryCount == 0) {
                super.onBackPressed();
            } else {
                bottomNavigationView.setSelectedItemId(R.id.home);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

