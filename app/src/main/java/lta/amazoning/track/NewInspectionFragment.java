package lta.amazoning.track;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewInspectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewInspectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewInspectionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewInspectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewInspectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewInspectionFragment newInstance(String param1, String param2) {
        NewInspectionFragment fragment = new NewInspectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

        return inflater.inflate(R.layout.fragment_new_inspection, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Spinner stationstartspinner = view.findViewById(R.id.stationstartspinner);
        ArrayAdapter<CharSequence> stationstartadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.stations_arrays, android.R.layout.simple_spinner_item);
        stationstartadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stationstartspinner.setAdapter(stationstartadapter);

        Spinner stationendspinner = view.findViewById(R.id.stationendspinner);
        ArrayAdapter<CharSequence> stationendadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.stations_arrays, android.R.layout.simple_spinner_item);
        stationendadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stationendspinner.setAdapter(stationendadapter);

        Spinner boundspinner = view.findViewById(R.id.boundspinner);
        ArrayAdapter<CharSequence> boundadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.bound_arrays, android.R.layout.simple_spinner_item);
        boundadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boundspinner.setAdapter(boundadapter);

        Spinner sectorcodespinner = view.findViewById(R.id.sectorcodespinner);
        ArrayAdapter<CharSequence> sectorcodeadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.sector_arrays, android.R.layout.simple_spinner_item);
        sectorcodeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectorcodespinner.setAdapter(sectorcodeadapter);

        Spinner accompaniedbyspinner = view.findViewById(R.id.accompaniedbyspinner);
        ArrayAdapter<CharSequence> accompaniedbyadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.accompany_array, android.R.layout.simple_spinner_item);
        accompaniedbyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accompaniedbyspinner.setAdapter(accompaniedbyadapter);
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
