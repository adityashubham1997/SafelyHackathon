package com.example.admin.myapplication;


import android.Manifest;
import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int  uniqueID = 45612;
    private static final int SELECT_PHOTO = 100;
    long last_click = 0;
    public final static int REQUEST_CODE = 10101;
    String DEFAULT = "N/A";
    String cronic;
    String diab;
    String b_p;
    String name1;
    String sex1;
    String bloodgroup1;
    String email1;
    String contactNumber1;
    String number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView startEmergency = (ImageView) (findViewById(R.id.start_emergency));

        startEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNotification();
                Intent emergencyIntent = new Intent(MainActivity.this, EmergencyActActivity.class);
emergencyIntent.putExtra("number", getIntent().getStringExtra("number"));
                startActivity(emergencyIntent);
            }
        });

        TextView myHealth = (TextView) (findViewById(R.id.myHealth));

        myHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent healthIntent = new Intent(MainActivity.this, HealthActivity.class);
                healthIntent.putExtra("name", getIntent().getStringExtra("name"));
                healthIntent.putExtra("email", getIntent().getStringExtra("email"));
                healthIntent.putExtra("blood", getIntent().getStringExtra("blood"));
                healthIntent.putExtra("sex", getIntent().getStringExtra("sex"));
                healthIntent.putExtra("number", getIntent().getStringExtra("number"));
                healthIntent.putExtra("diab",diab);
                healthIntent.putExtra("cronic",cronic);
                startActivity(healthIntent);
            }
        });

        /* TextView browse=(TextView) (findViewById(R.id.browse));

         browse.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent browseIntent=new Intent(MainActivity.this, BrowseActivity.class);

                 startActivity(browseIntent);
             }
         });*/

        TextView myAccount = (TextView) (findViewById(R.id.myAccount));

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountIntent = new Intent(MainActivity.this, AccountActivity.class);
                accountIntent.putExtra("name", getIntent().getStringExtra("name"));
                accountIntent.putExtra("email", getIntent().getStringExtra("email"));
                accountIntent.putExtra("blood", getIntent().getStringExtra("blood"));
                accountIntent.putExtra("sex", getIntent().getStringExtra("sex"));
                accountIntent.putExtra("number", getIntent().getStringExtra("number"));



                startActivity(accountIntent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());

            }
        });

        if (checkDrawOverlayPermission()) {
            startService(new Intent(this, PowerButtonService.class));
        }
    }

    private void getNotification()
    {
        getData2();
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setSummaryText("In Case Of Emergency");
        bigTextStyle.setBigContentTitle("Medical Report");

        if (cronic==""){
            bigTextStyle.bigText("Name :- " + name1+"\n" +
                    "Emergency contact:- "+ number1 +"\n"+
                    "Blood Group :-  "+bloodgroup1 +"\n"+
                    "BP Status:-  " + b_p +"\n"+
                    "Diabetes:-  " + diab +"\n"+
                    "Chornic Diseases:- "+"None");
        }
        else {
            bigTextStyle.bigText("Safely :- " + name1+"\n" +
                    "Emergency contact number:- "+ number1 +"\n"+
                    "Blood Group :-  "+bloodgroup1 +"\n"+
                    "BP Status:-  " + b_p +"\n"+
                    "Diabetes:-  " + diab +"\n"+
                    "Chornic Diseases:- "+cronic);
        }

        NotificationCompat.Builder notification;
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(false);
        //build notification
        notification.setSmallIcon(R.drawable.ic_letter);
        notification.setColor(ContextCompat.getColor(this, R.color.nColor));
        notification.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.safely));
        notification.setTicker("Emergency medical report");
        notification.setContentTitle("User Medical ID");
        notification.setStyle(bigTextStyle);
        notification.setPriority(NotificationCompat.PRIORITY_MAX);
        Intent report = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this ,
                0,report,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //issue notification
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());

    }

    private void getData2()
    {

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        cronic = sharedPreferences.getString("chronic",DEFAULT);
        diab = sharedPreferences.getString("diab",DEFAULT);
        b_p= sharedPreferences.getString("bp",DEFAULT);
        name1 = sharedPreferences.getString("name",DEFAULT);
        email1 = sharedPreferences.getString("email",DEFAULT);
        sex1 = sharedPreferences.getString("sex",DEFAULT);
        bloodgroup1 = sharedPreferences.getString("blood",DEFAULT);
        contactNumber1 = sharedPreferences.getString("contactnumber",DEFAULT);
        number1 = sharedPreferences.getString("emergencycontactnumber",DEFAULT);
    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }


    public void ChooseImage(View v) {
        openGallery();
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                startService(new Intent(this, PowerButtonService.class));
            }
        }

/**        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageButton);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }**/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                EditText edit = ((EditText) v);
                Rect outR = new Rect();
                edit.getGlobalVisibleRect(outR);
                Boolean isKeyboardOpen = !outR.contains((int) ev.getRawX(), (int) ev.getRawY());
                System.out.print("Is Keyboard? " + isKeyboardOpen);
                if (isKeyboardOpen) {
                    System.out.print("Entro al IF");
                    edit.clearFocus();
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
                }
                edit.setCursorVisible(!isKeyboardOpen);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        if (searchMenuItem == null) {
            return true;
        }

        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);

                return true;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //This is main menu
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, com.example.admin.myapplication.StepsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        } else if (id==R.id.nav_about) {
            //TODO:add about
        }else if (id==R.id.nav_account) {
            Intent intent= new Intent(MainActivity.this,AccountActivity.class);
            startActivity(intent);
        }else if (id==R.id.nav_health){
            Intent intent=new Intent(MainActivity.this,HealthActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


    public void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + 108));

        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(callIntent);
    }
}
