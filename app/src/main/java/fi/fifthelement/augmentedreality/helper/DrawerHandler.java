package fi.fifthelement.augmentedreality.helper;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import fi.fifthelement.augmentedreality.R;
import fi.fifthelement.augmentedreality.SettingsFragment;
import fi.fifthelement.augmentedreality.domain.AugmentedWorld;

public class DrawerHandler {

    private AugmentedWorld world;
    private Drawer drawer;
    private FragmentActivity activity;
    private String appName = "Augmented Reality";
    private String companyName = "Fifth Element Oy";
    private Drawable icon;
    private Fragment activeFragment = null;

    public DrawerHandler(FragmentActivity activity, AugmentedWorld world) {
        this.activity = activity;
        this.world = world;
        icon = activity.getResources().getDrawable(R.drawable.logo);
    }

    public Drawer buildDrawer() {
        PrimaryDrawerItem cameraView = new PrimaryDrawerItem().withName("Kameranäkymä").withIdentifier(1);
        PrimaryDrawerItem settings = new PrimaryDrawerItem().withName("Asetukset").withIdentifier(2);
        drawer = new com.mikepenz.materialdrawer.DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(buildHeader())
                .addDrawerItems(
                        cameraView,
                        settings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        selectedItemChanged(drawerItem.getIdentifier());
                        return true;
                    }
                })
                .build();
        return drawer;
    }

    public boolean closeDrawer() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
            return true;
        }
        return false;
    }

    public void removeActiveFragment(){
        activity.getSupportFragmentManager().beginTransaction().remove(activeFragment).commit();
    }

    private void selectedItemChanged(int item) {

        if (activeFragment != null)
            removeActiveFragment();

        Fragment fragment = null;

        try {
            switch (item) {
                case 1:
                    break;
                case 2:
                    fragment = SettingsFragment.newInstance(world, activity);
                    activeFragment = fragment;
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.augmentedRealityFragment, fragment).commit();
            }
            closeDrawer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AccountHeader buildHeader() {
        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header1).withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        new ProfileDrawerItem().withName(appName).withEmail(companyName).withIcon(icon)
                )
                .build();
    }
}
