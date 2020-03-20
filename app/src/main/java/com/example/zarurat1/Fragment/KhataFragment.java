package com.example.zarurat1.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.zarurat1.R;

public class KhataFragment extends Fragment {
    FrameLayout AddKhata, len_den,RemoveKhata;
    View view1, view2,view3;
    TextView tvAddKhata, tvLen_den,tvRemoveKhata;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_khata, container, false);


        init(view);

        //SET TABS ONCLICK
        AddKhata.setOnClickListener(clik);
        RemoveKhata.setOnClickListener(clik);
        len_den.setOnClickListener(clik);

        //LOAD PAGE FOR FIRST
        loadPage(new Len_DenFragment());
        tvLen_den.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        len_den.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));


        return view;
    }

    public void init(View v){
        AddKhata = v.findViewById(R.id.fragAddKhata);
        len_den = v.findViewById(R.id.fraglen_den);
        RemoveKhata = v.findViewById(R.id.fragKhataRemove);
        view1 = v.findViewById(R.id.khataview_1);
        view2 = v.findViewById(R.id.khataview_2);
        view3= v.findViewById(R.id.khataview_3);
        tvRemoveKhata = v.findViewById(R.id.tvKhataRemove);
        tvLen_den= v.findViewById(R.id.tvlen_den);
        tvAddKhata= v.findViewById(R.id.tvAddKhata);
    }

    //ONCLICK LISTENER
    public View.OnClickListener clik = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fragAddKhata:
                    //ONSELLER CLICK
                    //LOAD SELLER FRAGMENT CLASS
                    loadPage(new AddKharaFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvAddKhata.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    tvLen_den.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    tvRemoveKhata.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    AddKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    len_den.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    RemoveKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                    view3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.fraglen_den:
                    //ONBUYER CLICK
                    //LOAD BUYER FRAGMENT CLASS
                    loadPage(new Len_DenFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvLen_den.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    tvAddKhata.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    tvRemoveKhata.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    len_den.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    AddKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    RemoveKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.fragKhataRemove:
                    //ONBUYER CLICK
                    //LOAD BUYER FRAGMENT CLASS
                    loadPage(new RemoveKhataFragment());

                    //WHEN CLICK TEXT COLOR CHANGED
                    tvRemoveKhata.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    tvAddKhata.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    tvLen_den.setTextColor(getActivity().getResources().getColor(R.color.colorWhite));
                    RemoveKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorWhite));
                    AddKhata.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    len_den.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));

                    //VIEW VISIBILITY WHEN CLICKED
                    view1.setVisibility(View.INVISIBLE);
                    view3.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
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
