package com.univnest.nodabba;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttondrawer;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        drawerLayout=findViewById(R.id.main);
        buttondrawer=findViewById(R.id.drawerbtn);
        navigationView=findViewById(R.id.navigation_drawer);

        buttondrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        View headerview = navigationView.getHeaderView(0);
        ImageView useimage = headerview.findViewById(R.id.image);
        TextView textusername= headerview.findViewById(R.id.name_text);
        TextView textemail = headerview.findViewById(R.id.email_text);
        useimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomePageActivity.this,textusername.getText(),Toast.LENGTH_SHORT).show();
                Toast.makeText(HomePageActivity.this,textemail.getText(),Toast.LENGTH_SHORT).show();
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.dashboard){
                    Toast.makeText(HomePageActivity.this,"dashboard",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.premium){
                    Toast.makeText(HomePageActivity.this,"premimum",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.settings){
                    Toast.makeText(HomePageActivity.this,"settings",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.terms_and_conditions){
                    Toast.makeText(HomePageActivity.this,"terms and conditions",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.my_task){
                    Toast.makeText(HomePageActivity.this,"my task",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.contact_us){
                    Toast.makeText(HomePageActivity.this,"contact us",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.logout){
                    Toast.makeText(HomePageActivity.this,"logout",Toast.LENGTH_SHORT).show();
                }
                if(itemId==R.id.add_account){
                    Toast.makeText(HomePageActivity.this,"add account",Toast.LENGTH_SHORT).show();
                }
                drawerLayout.close();
                return false;
            }
        });

    }
}