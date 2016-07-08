package com.kr.permissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by an-yeong-gug on 16. 7. 7..
 */
public class Permissions {


    public static class Build{
        private  Boolean forceflag = false;
        private Boolean allflag = false;
        permissionsCallback callback;

        private ArrayList<String> mypermission = null;
        private AppCompatActivity context = null;
        public Build setForceFlag(Boolean flag) {
            forceflag = flag;
        /*
         Callback change
         */

            return this;
        }

        public Build setCallback(permissionsCallback _callback) {
            callback = _callback;
            return this;
        }

        public Build setContext(AppCompatActivity _context) {

            context = _context;
            return this;
        }

        public Permissions build() {

            Permissions newinst = new Permissions();
            newinst.setContext(context);
            newinst.setAllFlag(allflag);
            newinst.setForceFlag(forceflag);
            if(allflag) {
                newinst.setMyPermission();
            }

            else {
                String[] test = new String[mypermission.size()];
                test = mypermission.toArray(test);
                newinst.setMypermission(test);
            }
            newinst.setCallback(callback);


            return newinst;
        }

        public Build setAllFlag(Boolean flag) {
            allflag = flag;

            return this;
        }


    }
    private Boolean forceflag = false;
    private Boolean allflag = false;
    permissionsCallback callback;

    private ArrayList<String> mypermission = null;
    private AppCompatActivity context = null;
    private static Permissions instance = null;

    private static final String[] DANGERPERMISSION = {
            Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG, Manifest.permission.ADD_VOICEMAIL, Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.BODY_SENSORS, Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /*
     forceflag callback
     */

    Permissions.permissionsCallback forceCallback = new Permissions.permissionsCallback() {

        @Override
        public void onRequestPermissionsResult_GRANTED() {

        }

        @Override
        public void onRequestPermissionsResult_DENIED() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("알림");
            builder.setMessage("모든 권한을 허용한후에 앱이용이 가능합니다.");
            builder.setPositiveButton("설정",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            startInstalledAppDetailsActivity(context);
                            context.finish();


                        }
                    });

            builder.setNeutralButton("취소",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                            context.finish();


                        }
                    });


            builder.show();
        }
    };


    public static Permissions getInstance() {
        if (instance == null) {
            instance = new Permissions();

        }
        return instance;

    }

    public Permissions setForceFlag(Boolean flag) {
        forceflag = flag;
        /*
         Callback change
         */

        return this;
    }

    public Permissions setCallback(permissionsCallback _callback) {
        callback = _callback;
        return this;
    }

    public Permissions setContext(AppCompatActivity _context) {

        context = _context;
        return this;
    }


    public Permissions setAllFlag(Boolean flag) {
        allflag = flag;
        if(allflag) {
            setMyPermission();
        }
        return this;
    }

    public Permissions setMypermission(String[] permission) {



            mypermission = new ArrayList<String>();
            for (int i = 0; i < permission.length; i++) {
                mypermission.add(permission[i]);



        }

        return this;
    }

    public void setMyPermission() {
        try {
            mypermission = new ArrayList<String>();
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {

                int i = 0;
                for (String p : info.requestedPermissions) {

                    Boolean dangerflag = false;
                    for (String dang : DANGERPERMISSION) {
                        if (p.equals(dang)) {


                            if (ContextCompat.checkSelfPermission(context,
                                    dang)
                                    != PackageManager.PERMISSION_GRANTED) {
                                dangerflag = true;
                            }
                        }


                    }
                    if (dangerflag) {
                        mypermission.add(i, p);

                        i += 1;
                    }
                    //   }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (PackageManager.NameNotFoundException e) {
            System.out.println("NameNotFoundException");
        }

    }


    public static void startInstalledAppDetailsActivity(final AppCompatActivity context) {
        if (context == null) {
            return;
        }
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }


    public void checkPermissions() {

        // Here, thisActivity is the current activity
        Boolean boolallcheckpermission = true;

        for (int i = 0; i < mypermission.size(); i++) {

            String permission = mypermission.get(i);

            if (ContextCompat.checkSelfPermission(context,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {

                boolallcheckpermission = false;
                break;
            }


        }


        if (boolallcheckpermission == false) {

            requestPermissions();


        } else {

            callback.onRequestPermissionsResult_GRANTED();


        }

    }

    public void requestPermissions() {

        String[] permissions = new String[mypermission.size()];
        mypermission.toArray(permissions);
        ActivityCompat.requestPermissions(context,
                permissions,
                MY_PERMISSIONS_REQUEST);
    }

    final int MY_PERMISSIONS_REQUEST = 100;


    public interface permissionsCallback {


        void onRequestPermissionsResult_GRANTED();

        void onRequestPermissionsResult_DENIED();


    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                Boolean allbool = true;

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        allbool = false;
                        break;
                    }
                }


                if (allbool) {

                    callback.onRequestPermissionsResult_GRANTED();

                    // Toast.makeText(getApplicationContext(),"모든 권한이 설정되었습니다",Toast.LENGTH_SHORT).show();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    if (forceflag) {
                        forceCallback.onRequestPermissionsResult_DENIED();
                    } else {
                        callback.onRequestPermissionsResult_DENIED();
                    }

                }
            }
            return;
        }


        // other 'case' lines to check for other
        // permissions this app might request
    }
}

