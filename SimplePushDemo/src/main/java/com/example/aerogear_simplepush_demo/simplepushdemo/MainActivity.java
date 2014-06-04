package com.example.aerogear_simplepush_demo.simplepushdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.common.collect.Lists;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.impl.simplepush.SimplePushConfig;
import org.jboss.aerogear.android.impl.simplepush.SimplePushRegistrarFactory;
import org.jboss.aerogear.android.unifiedpush.Registrations;

import java.net.URI;


public class MainActivity extends ActionBarActivity {

    Registrations r = new Registrations(new SimplePushRegistrarFactory());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimplePushConfig spc = new SimplePushConfig();
        spc.setPushServerURI(URI.create("https://push-coffeeregister.rhcloud.com"));
        spc.setSimplePushEndpoint("ws://10.0.2.2:7777/simplepush/websocket");
        spc.setCategories(Lists.newArrayList("push"));
        spc.setVariantID("18260626-e56d-4d13-987f-e602a63e31a6");
        spc.setSecret("fc129882-1238-4e60-a8ee-4c57bc76e3f3");
        r.push("push", spc);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("test", "Registering!");

        r.get("push").register(this, new Callback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("test", aVoid + " Success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("test", e.getMessage(), e);
            }
        });

//        SimplePushWebsocketClient client = new SimplePushWebsocketClient(URI.create("ws://10.0.2.2:7777/simplepush/websocket"));
//        CountDownLatch latch = new CountDownLatch(1);
//        client.connect(latch);

//        new WebSocketClient(URI.create("ws://10.0.2.2:7777/simplepush/websocket")) {
//            @Override
//            public void onOpen(ServerHandshake handshakedata) {
//                Log.d("OPEN", handshakedata.toString());
//                this.send("{\"messageType\":\"hello\"}");
//
//            }
//
//            @Override
//            public void onMessage(String message) {
//                Log.d("MESSAGE", message);
//            }
//
//            @Override
//            public void onClose(int code, String reason, boolean remote) {
//                Log.d("CLOSE", String.format("Code : %d, %s, remote : %d", code, reason, remote?0:1));
//            }
//
//            @Override
//            public void onError(Exception ex) {
//                Log.e("Exception", ex.getMessage(), ex);
//            }
//        }.connect();
//
    }
}
