package tk.solutions_apex.wifipassworddatabase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import tk.solutions_apex.wifipassworddatabase.helper.Network;
import tk.solutions_apex.wifipassworddatabase.helper.NetworksDataSource;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NetworksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NetworksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworksFragment extends Fragment {
    private NetworksDataSource datasource;
    public static ArrayAdapter<String> listAdapter;

    OnFragmentInteractionListener mListener;

    public NetworksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NetworksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NetworksFragment newInstance(String param1, String param2) {
        NetworksFragment fragment = new NetworksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_networks,
                container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NewNetworkActivity.class));
            }
        });

        datasource = new NetworksDataSource(getContext().getApplicationContext());
        datasource.open();

        if (datasource.getAllNetworkNames().size() != 0) {
            List<String> ssids = datasource.getAllNetworkNames();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            listAdapter = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_list_item_1, ssids);
            ((ListView) view.findViewById(R.id.list_networks)).setAdapter(listAdapter);
            view.findViewById(R.id.list_networks).setVisibility(View.VISIBLE);
            view.findViewById(R.id.no_network).setVisibility(View.GONE);
        }

        return view;
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
