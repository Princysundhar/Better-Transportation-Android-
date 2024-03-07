package com.example.driveinn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.driveinn.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
//        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_ViewWorker) {
            Intent i = new Intent(getApplicationContext(), ViewWorker.class);
            startActivity(i);
        }
//        else if (id==R.id.nav_SendRequest){
//            Intent i = new Intent(getApplicationContext(),SendRequest.class);
//            startActivity(i);
//        }
        else if (id == R.id.nav_RequestStatus) {
            Intent i = new Intent(getApplicationContext(), ViewRequestStatus.class);
            startActivity(i);
        }

         else if (id==R.id.nav_SendFeedback){
            Intent i = new Intent(getApplicationContext(),SendFeedback.class);
            startActivity(i);
        }
        else if (id==R.id.nav_ViewReply){
            Intent i = new Intent(getApplicationContext(),ViewReply.class);
            startActivity(i);
        }
        else if (id==R.id.history){
            Intent i = new Intent(getApplicationContext(),view_request_history.class);
            startActivity(i);
        }

        else if (id==R.id.nav_ChangePassword){
            Intent i = new Intent(getApplicationContext(),Changepasswd.class);
            startActivity(i);
        }

        else if (id==R.id.nav_Logout){
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }







        return false;
    }
}