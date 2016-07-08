package com.kr.permissions;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    Permissions permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Permissions.Build build=  new Permissions.Build();
        Permissions.permissionsCallback callback=new Permissions.permissionsCallback() {
            @Override
            public void onRequestPermissionsResult_GRANTED() {
                goMain();
            }

            @Override
            public void onRequestPermissionsResult_DENIED() {
             /*
              force default nothing   you use setForceFlag(false)
             */
            }
        };
        permission=build.setContext(Splash.this).setAllFlag(true).setCallback(callback).setForceFlag(true).build();
        permission.checkPermissions();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        permission.onRequestPermissionsResult(requestCode,permissions,grantResults);


    }

    public void goMain(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}