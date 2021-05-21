package com.example.phisicscatalog.ui.mehanica;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.phisicscatalog.R;
import com.example.phisicscatalog.dialogs.DialogFragmentForShowInfo;
import com.example.phisicscatalog.parserXml.InfoModel;
import com.example.phisicscatalog.parserXml.XMLParser;
import com.example.phisicscatalog.ui.optica.TestingFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class MehanicaFragment extends Fragment {

    private MehanicaViewModel mehanicaViewModel;
    private TestingFragment testingFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbarMechanica;

    private FragmentActivity myContext;

    @Override
    public void onAttach(@NonNull Context context) {
        myContext = (FragmentActivity) getActivity();
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mehanicaViewModel = new ViewModelProvider(this).get(MehanicaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mehanica, container, false);

        mehanicaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                /**
                 * Текстовый код для нужного отображения
                 *
                 **/

                testingFragment = new TestingFragment();

//                toolbarOptica = root.findViewById(R.id.toolbar_optica);
//                getActivity().setActionBar(toolbarOptica);



                tabLayout = root.findViewById(R.id.tab_layout);
                viewPager = root.findViewById(R.id.view_pager_mechanica);
                tabLayout.setupWithViewPager(viewPager);

                viewPager.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(), 0));


                tabLayout.setContentDescription(root.getContentDescription());
                TabLayout.Tab tab1 = tabLayout.getTabAt(0);
                tab1.setContentDescription(root.getContentDescription());
                TabLayout.Tab tab2 = tabLayout.getTabAt(1);
                tab2.setContentDescription(root.getContentDescription());
                //для отображения уведомлений у вкладки
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(1).getOrCreateBadge();
                badgeDrawable.setVisible(true);
                badgeDrawable.setNumber(5);

                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        // тут можно выполнять действия при нажатии на кнопки
                        Toast.makeText(root.getContext(), tab.getText(), Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        Toast.makeText(getContext(), tab.getText()+"reselected", Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });
        return root;
    }
    //адаптер для кладок (хранит название вкладки и фрагмент который нужно отображать
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        //фрагменты рандомные для теста
        private Fragment[] fragments = {new MehanicaFragment(), new TestingFragment()};
        private String[] fragmentsTitle = {"Теория", "Тесты"};

        public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        public String getPageTitle(int position){
            return fragmentsTitle[position];
        }
    }

}
