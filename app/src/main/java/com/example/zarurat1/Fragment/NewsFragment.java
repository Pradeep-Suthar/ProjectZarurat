package com.example.zarurat1.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.zarurat1.R;

public class NewsFragment extends Fragment{
        FrameLayout newsRecent, newsPersonal;
        View view1, view2;
        TextView tvRecent, tvPersonal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_news, container, false);

        //INIT VIEWS
        init(view);

        //SET TABS ONCLICK
        newsRecent.setOnClickListener(clik);
        newsPersonal.setOnClickListener(clik);

        //LOAD PAGE FOR FIRST
        loadPage(new RecentNewsFragment());
        tvRecent.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        newsRecent.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));

        return view;
    }

    public void init(View v){
        newsRecent = v.findViewById(R.id.fragRecentNews);
        newsPersonal = v.findViewById(R.id.fragPersonalNews);
        view1 = v.findViewById(R.id.view_1);
        view2 = v.findViewById(R.id.view_2);
        tvRecent = v.findViewById(R.id.tvrecent);
        tvPersonal= v.findViewById(R.id.tvPersonal);
    }

    //ONCLICK LISTENER
    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fragRecentNews:
                    //ONSELLER CLICK
                    //LOAD SELLER FRAGMENT CLASS
                    loadPage(new RecentNewsFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvRecent.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    tvPersonal.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    newsRecent.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    newsPersonal.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                    break;
                case R.id.fragPersonalNews:
                    //ONBUYER CLICK
                    //LOAD BUYER FRAGMENT CLASS
                    loadPage(new PersonlizedNewsFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvRecent.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    tvPersonal.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    newsPersonal.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    newsRecent.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    //LOAD PAGE FRAGMENT METHOD
    private boolean loadPage(Fragment fragment) {

        if (fragment != null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerPage, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
