package com.hackaton.projetofuncional.actionbar;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hackaton.projetofuncional.R;
import com.hackaton.projetofuncional.activities.MainActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * @author Paulo Mateus <paulomatew@gmail.com> on 29/05/17.
 */

public class ActionBarManager {
    private Activity _act;
    private Toolbar toolbar;

    public Drawer mActionBar;

    public ActionBarManager(Activity _act, Toolbar toolbar) {
        this._act = _act;
        this.toolbar = toolbar;

        initActionBar();
    }

    private void initActionBar() {
        new DrawerBuilder().withActivity(_act).build();


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(_act)
                .withHeaderBackground(R.drawable.header)
                .withTranslucentStatusBar(false)
                .addProfiles(
                        new ProfileDrawerItem().withName("João da Silva").withEmail("jsilva@email.com")
                        //.withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        mActionBar = new DrawerBuilder()
                .withActivity(_act)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new SecondaryDrawerItem().withIdentifier(0).withName("Início").withIcon(FontAwesome.Icon.faw_home),
                        /*separator*/new SectionDrawerItem().withName("Filtros"),
                        new SecondaryDrawerItem().withIdentifier(1).withSelectable(false).withName("Todos").withIcon(FontAwesome.Icon.faw_globe),
                        new SecondaryDrawerItem().withIdentifier(2).withSelectable(false).withName("Denúncia").withIcon(FontAwesome.Icon.faw_exclamation_triangle),
                        new SecondaryDrawerItem().withIdentifier(3).withSelectable(false).withName("Projetos Futuros").withIcon(FontAwesome.Icon.faw_gavel),
                        new SecondaryDrawerItem().withIdentifier(4).withSelectable(false).withName("Projetos Ativos").withIcon(FontAwesome.Icon.faw_list),
                        /*separator*/new SectionDrawerItem().withName("Histórico"),
                        new SecondaryDrawerItem().withIdentifier(5).withName("Suas denúncias").withIcon(FontAwesome.Icon.faw_bar_chart),
                        /*separator*/ new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(8).withSelectable(false).withName("Compartilhe").withIcon(FontAwesome.Icon.faw_share_alt),
                        new SecondaryDrawerItem().withIdentifier(10).withName("Sobre").withIcon(FontAwesome.Icon.faw_info_circle)

                )
                .addStickyDrawerItems(new SecondaryDrawerItem().withSelectable(false).withIdentifier(11).withName("Configurações").withIcon(FontAwesome.Icon.faw_cog))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 1) {//todos
                            ((MainActivity) _act).repopulate(1);
                        } else if (drawerItem.getIdentifier() == 2) {//denuncias
                            ((MainActivity) _act).repopulate(2);
                        } else if (drawerItem.getIdentifier() == 3) {//projetos futuros
                            ((MainActivity) _act).repopulate(3);
                        } else if (drawerItem.getIdentifier() == 4) {//projetos ativos
                            ((MainActivity) _act).repopulate(4);
                        }

                        return false;
                    }
                })
                .build();
    }
}