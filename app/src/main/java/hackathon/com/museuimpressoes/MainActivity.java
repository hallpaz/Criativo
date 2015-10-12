package hackathon.com.museuimpressoes;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        MuseumApplication myApp = (MuseumApplication) getApplication();
        myApp.startBeacons();

    }

    public void goToGallery(View view){
        Intent galleryIntent = new Intent(this, PieceGalleryActivity.class);
        galleryIntent.putExtra("panelName", "São Sebastião");
        startActivity(galleryIntent);
    }

    public void goToDetails(View view){
        Intent galleryIntent = new Intent(this, DetailsActivity.class);
        startActivity(galleryIntent);
    }

    public void goToChooseGallery(View view){
        Intent chooseGalleryIntent = new Intent(this, ChooseGalleryActivity.class);
        startActivity(chooseGalleryIntent);
    }

    public void goToLogin(View view){
        Log.d(TAG, "socorro");

        Intent loginIntent = new Intent(this, CommentsActivity.class);
        loginIntent.putExtra(CommentsActivity.COMMENTWork, "test_work");
        startActivity(loginIntent);
    }

    public void askConfirmation(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
