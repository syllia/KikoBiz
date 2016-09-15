package bj.kiko.projects.kikobiz;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import bj.kiko.projects.kikobiz.Fragment.CategoriesFragment;
import bj.kiko.projects.kikobiz.Fragment.DescriptionFragment;
import bj.kiko.projects.kikobiz.Fragment.FavOffersFragment;
import bj.kiko.projects.kikobiz.Fragment.HomeFragment;
import bj.kiko.projects.kikobiz.Fragment.OffersFragment;
import bj.kiko.projects.kikobiz.Fragment.ParametresFragment;
import bj.kiko.projects.kikobiz.Model.Offer;
import bj.kiko.projects.kikobiz.Util.Util;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CategoriesFragment.OnSubCategorySelectedListener,
        FavOffersFragment.OnOfferSelected, OffersFragment.OnOfferSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if (intent.getStringExtra("name")!=null){
            Util.lancerFragment(this, intent.getStringExtra("name"));
        }else {
            Util.lancerFragment(this, getResources().getString(R.string.FragmentHomeName));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    public void onOfferSelected(Offer pOffer, String fragmentName){
        DescriptionFragment test = new DescriptionFragment();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putParcelable(getResources().getString(R.string.offer), pOffer);
        test.setArguments(args);
        ft.replace(R.id.fragmentContainer, test).addToBackStack(fragmentName).commit();

    }


    public void onItemSelected(long position, String fragmentName) {

        OffersFragment test = new OffersFragment();
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();

        Bundle args = new Bundle();
        args.putLong(getString(R.string.id), position);
        test.setArguments(args);
        ft.replace(R.id.fragmentContainer, test).addToBackStack(fragmentName).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
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
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            ParametresFragment test = new ParametresFragment();
            ft.replace(R.id.fragmentContainer, test).addToBackStack("categories").commit();;
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_post) {
            Util.lancerFragment(this,getResources().getString(R.string.FragmentPost));
        } else if (id == R.id.nav_favoris) {
            Util.lancerFragment(this, getResources().getString(R.string.FragmentFavOffresName));
        } else if (id == R.id.nav_recherche) {
            Util.lancerFragment(this, getResources().getString(R.string.FragmentCategorieName));

        } else if (id == R.id.nav_parametre) {
            Util.lancerFragment(this, getResources().getString(R.string.FragmentParametresName));

        } else if (id == R.id.nav_probleme) {

        } else if (id == R.id.nav_mail) {
        }
        else if (id == R.id.nav_home) {
            Util.lancerFragment(this, getResources().getString(R.string.FragmentHomeName));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
