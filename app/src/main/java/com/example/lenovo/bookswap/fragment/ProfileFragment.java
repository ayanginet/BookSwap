package com.example.lenovo.bookswap.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.bookswap.R;
import com.example.lenovo.bookswap.adapters.BooksAdapter;
import com.example.lenovo.bookswap.adapters.ProfileAdapter;
import com.example.lenovo.bookswap.interfaces.RecyclerViewClickListener;
import com.example.lenovo.bookswap.models.Book;
import com.example.lenovo.bookswap.models.MyBook;
import com.example.lenovo.bookswap.models.User;
import com.example.lenovo.bookswap.other.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements RecyclerViewClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private User user;
    RecyclerView rv;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        initializeData();
        return view;
    }
    public void initializeData(){
        List<MyBook> books = new ArrayList<>();/*
        for (int i = 0; i < 8; i++){
            Book book = new Book("Мартин Иден", "Джек Лондон", "В наличии", "2010", "https://img1.labirint.ru/books39/382061/big.jpg");
            books.add(new MyBook(book, 0, null, "2"+i+".04.2018" ));
        }
        for (int i = 0; i < 8; i++){
            Book book = new Book("Мартин Иден", "Джек Лондон", "В наличии", "2010", "https://img1.labirint.ru/books39/382061/big.jpg");
            books.add(new MyBook(book, 1, "2"+i+".03.2018", null ));
        }*/
        //user = new User("Jhon Marshal", "jhon.marshal@gmail.com", "https://s-media-cache-ak0.pinimg.com/originals/a3/2b/aa/a32baa5414296b7cef86096c8ed91aad.jpg", books);
        initializeAdapter();
    }
    private void initializeAdapter(){
        ProfileAdapter adapter = new ProfileAdapter(user, this);
        rv.setAdapter(adapter);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void recyclerViewListClicked(View v, int i) {
        switch (v.getId()){
            case R.id.returnBtn:
                Toast.makeText(getContext(), "will return book", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mybook:
                Toast.makeText(getContext(), "will open more info", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
