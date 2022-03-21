package com.example.googlesigninapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "Sandygoogleapi";
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount account;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    static FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            Uri personPhoto = account.getPhotoUrl();

            View header_view = navigationView.getHeaderView(0);
            TextView user_name = header_view.findViewById(R.id.user_name);
            TextView user_email = header_view.findViewById(R.id.user_email);
            CircleImageView user_pic = header_view.findViewById(R.id.user_pic);
            user_name.setText(personName);
            user_email.setText(personEmail);
            try {
                Glide.with(this)
                        .load(personPhoto.toString())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .override(200, 200)
                        .centerCrop()
                        .into(user_pic);
            }
            catch(Exception e){

            }
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.log_in:
                        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                        if(account != null){
                            Toast.makeText(getApplicationContext(), "ALREADY SIGNED-IN", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            signIn();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.log_out:
                        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                        if(account != null){
                            signOut();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "ALREADY SIGNED-OUT", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if(account != null){
                    Intent intent = new Intent(getApplicationContext(), Register_PG.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog builder1 = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("NOT SIGNED IN")
                            .setMessage("For Registration please sign in...")
                            .setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }).create();
                    builder1.show();
                }
            }
        });

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        View header_view = navigationView.getHeaderView(0);
                        TextView user_name = header_view.findViewById(R.id.user_name);
                        TextView user_email = header_view.findViewById(R.id.user_email);
                        CircleImageView user_pic = header_view.findViewById(R.id.user_pic);
                        user_name.setText("");
                        user_email.setText("");
                        user_pic.setImageBitmap(null);
                        Toast.makeText(getApplicationContext(), "SIGN-OUT SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    }
                });
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(), "SIGN-IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                Uri personPhoto = acct.getPhotoUrl();

                View header_view = navigationView.getHeaderView(0);
                TextView user_name = header_view.findViewById(R.id.user_name);
                TextView user_email = header_view.findViewById(R.id.user_email);
                CircleImageView user_pic = header_view.findViewById(R.id.user_pic);
                user_name.setText(personName);
                user_email.setText(personEmail);
                try {
                    Glide.with(this)
                            .load(personPhoto.toString())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .override(200, 200)
                            .centerCrop()
                            .into(user_pic);
                }
                catch(Exception e){

                }
            }
        } catch (ApiException e) {
            Log.d(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
