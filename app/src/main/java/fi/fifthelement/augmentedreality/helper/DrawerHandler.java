package fi.fifthelement.augmentedreality.helper;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import fi.fifthelement.augmentedreality.R;

public class DrawerHandler {

    private Drawer drawer;
    private FragmentActivity activity;
    private String appName = "Augmented Reality";
    private String companyName = "Fifth Element Oy";
    private Drawable icon;

    public DrawerHandler(FragmentActivity activity) {
        this.activity = activity;
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
                        System.out.println(drawerItem.getIdentifier());
                        return true;
                    }
                })
                .build();

        return drawer;
    }

    public boolean close(){
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
            return true;
        }
        return false;
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
