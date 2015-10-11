package hackathon.com.museuimpressoes;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseFacebookUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by hallpaz on 11/10/2015.
 */
public class MuseumApplication extends Application {

    private final String TAG = "APP";
    private BeaconManager beaconManager;
    private final String mainBeaconUUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private final int MAJOR_main = 2000;
    private final int MINOR_main = 3000;
    //private final String secondaryBeaconUUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private final int MAJOR_secondary = 5000;
    private final int MINOR_secondary = 7000;

    private boolean shouldChangeBeacon = false;

    private activeBeacons attachedBeacon = activeBeacons.INVALID;

    enum activeBeacons{ MainBeacon, SecondaryBeacon, INVALID }

    /*private final int MAJOR_range = 22504;
    private final int MINOR_range = 48827;*/
    private Region expositionRegion;

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = new BeaconManager(getApplicationContext());


        beaconManager = new BeaconManager(getApplicationContext());
        //beaconManager.setMonitoringListener(new MuseumBeaconMonitoringListener());
        beaconManager.setRangingListener(new MuseumBeaconRangingListener());
        expositionRegion = new Region("Exposition", UUID.fromString(mainBeaconUUID), null, null);

       /* beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",
                        UUID.fromString(mainBeaconUUID),
                        MAJOR_range, MINOR_range));
            }
        });*/
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(expositionRegion);
            }
        });

        Parse.initialize(this, getResources().getString(R.string.Parse_Application_ID), getResources().getString(R.string.Parse_Client_Key));
        ParseFacebookUtils.initialize(getApplicationContext());
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }

    private class MuseumBeaconRangingListener implements BeaconManager.RangingListener {

        @Override
        public void onBeaconsDiscovered(Region region, List<Beacon> list) {

            if (!list.isEmpty()) {

                Beacon nearestBeacon = list.get(0);
                if(Utils.computeProximity(nearestBeacon) == Utils.Proximity.IMMEDIATE){
                    Log.d(TAG, "discovered IMMEDIATE " + nearestBeacon.getMajor());
                    String beaconKey = String.format("%d:%d", nearestBeacon.getMajor(), nearestBeacon.getMinor());
                    if(beaconKey.contains(String.format("%d:%d", MAJOR_main, MINOR_main))){

                        if(!attachedBeacon.equals(activeBeacons.MainBeacon) ){
                            Intent galleryIntent = new Intent(getApplicationContext(), PieceGalleryActivity.class);
                            galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(galleryIntent);
                        }
                        attachedBeacon = activeBeacons.MainBeacon;
                    }

                    if(beaconKey.contains(String.format("%d:%d", MAJOR_secondary, MINOR_secondary))){
                        if(attachedBeacon != activeBeacons.SecondaryBeacon){
                            Intent galleryIntent = new Intent(getApplicationContext(), PieceGalleryActivity.class);
                            galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(galleryIntent);
                        }

                        attachedBeacon = activeBeacons.SecondaryBeacon;
                    }
                }
                else{
                    attachedBeacon = activeBeacons.INVALID;
                    Intent idleIntent = new Intent(getApplicationContext(), MainActivity.class);
                    idleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(idleIntent);
                }

            }
        }

        public void askConfirmation(int beaconID){
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Are you sure you want to change to beacon ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //finish();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    private class MuseumBeaconMonitoringListener implements BeaconManager.MonitoringListener{

        @Override
        public void onEnteredRegion(Region region, List<Beacon> list) {
            Intent galleryIntent = new Intent(getApplicationContext(), PieceGalleryActivity.class);
            galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(galleryIntent);
        }

        @Override
        public void onExitedRegion(Region region) {
            Intent idleIntent = new Intent(getApplicationContext(), MainActivity.class);
            idleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(idleIntent);
        }
    }
}
