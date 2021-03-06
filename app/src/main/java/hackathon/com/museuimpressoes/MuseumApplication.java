package hackathon.com.museuimpressoes;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseFacebookUtils;

/**
 * Created by hallpaz on 11/10/2015.
 */
public class MuseumApplication extends Application {
    private final String TAG = "APP";
    private BeaconManager beaconManager;
    private final String mainBeaconUUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private final int MAJOR_main = 20;
    private final int MINOR_main = 3000;
    //private final String secondaryBeaconUUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private final int MAJOR_secondary = 5000;
    private final int MINOR_secondary = 7000;

    private boolean shouldChangeBeacon = false;
    private int reliabilityCounter = 0;

    private activeBeacons attachedBeacon = activeBeacons.INVALID;

    enum activeBeacons{ MainBeacon, SecondaryBeacon, INVALID }

    private Region expositionRegion;

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setRangingListener(new MuseumBeaconRangingListener());
        expositionRegion = new Region("Exposition", UUID.fromString(mainBeaconUUID), null, null);


        /*Parse.initialize(this, getResources().getString(R.string.Parse_Application_ID), getResources().getString(R.string.Parse_Client_Key));
        ParseFacebookUtils.initialize(getApplicationContext());
        ParseInstallation.getCurrentInstallation().saveInBackground();*/
    }



    public void startBeacons(){
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(expositionRegion);
            }
        });
    }

    private class MuseumBeaconRangingListener implements BeaconManager.RangingListener {

        @Override
        public void onBeaconsDiscovered(Region region, List<Beacon> list) {

            if (!list.isEmpty()) {

                Beacon nearestBeacon = list.get(0);
                Utils.Proximity nearestProximity = Utils.computeProximity(nearestBeacon);

                switch (nearestProximity){
                    case UNKNOWN:
                        break;
                    case IMMEDIATE:
                    case NEAR:
                        reliabilityCounter = 0;
                        String beaconKey = String.format("%d:%d", nearestBeacon.getMajor(), nearestBeacon.getMinor());
                        if(beaconKey.contains(String.format("%d:%d", MAJOR_main, MINOR_main))){
                            if(!attachedBeacon.equals(activeBeacons.MainBeacon) ){
                                Intent galleryIntent = new Intent(getApplicationContext(), PieceGalleryActivity.class);
                                galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                galleryIntent.putExtra(PieceGalleryActivity.PANELExtra, "Varejão");
                                startActivity(galleryIntent);
                            }
                            attachedBeacon = activeBeacons.MainBeacon;
                        }

                        if(beaconKey.contains(String.format("%d:%d", MAJOR_secondary, MINOR_secondary))){
                            if(!attachedBeacon.equals(activeBeacons.SecondaryBeacon)){
                                Intent galleryIntent = new Intent(getApplicationContext(), PieceGalleryActivity.class);
                                galleryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY) ;
                                galleryIntent.putExtra(PieceGalleryActivity.PANELExtra, "São Sebastião");
                                startActivity(galleryIntent);
                            }

                            attachedBeacon = activeBeacons.SecondaryBeacon;
                        }
                        break;
                    case FAR:
                        reliabilityCounter++;
                        if(reliabilityCounter >= 5){
                            if(!attachedBeacon.equals(activeBeacons.INVALID)){
                                Intent idleIntent = new Intent(getApplicationContext(), MainActivity.class);
                                idleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(idleIntent);
                            }

                            attachedBeacon = activeBeacons.INVALID;
                        }

                        break;
                }
            }else{
                if(!attachedBeacon.equals(activeBeacons.INVALID)){
                    Intent idleIntent = new Intent(getApplicationContext(), MainActivity.class);
                    idleIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(idleIntent);
                }

                attachedBeacon = activeBeacons.INVALID;
            }
        }

    }

    /*public void askConfirmation(int beaconID){
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("Are you sure you want to change to beacon ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
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
        }*/

}
