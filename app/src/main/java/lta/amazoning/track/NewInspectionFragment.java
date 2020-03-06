package lta.amazoning.track;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class NewInspectionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private FragmentActivity mFrgAct;
    private Intent mIntent;

    private FloatingActionButton fab;
    private Button left;
    private Button middle;
    private Button right;
    private NiceSpinner stationstartspinner, stationendspinner, boundspinner, sectorcodespinner, accompaniedbyspinner;
    private InspectionOverview instance;

    public NewInspectionFragment(FloatingActionButton fab, Button left, Button middle, Button right) {
        this.fab = fab;
        this.left = left;
        this.middle = middle;
        this.right = right;

        left.setVisibility(View.INVISIBLE);
        right.setVisibility(View.INVISIBLE);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFrgAct = getActivity();
        mIntent = mFrgAct.getIntent(); //  Intent intent = new Intent(getActivity().getIntent());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Start", stationstartspinner.getSelectedItem().toString());
                    jsonObject.put("End", stationendspinner.getSelectedItem().toString());
                    jsonObject.put("Bound", boundspinner.getSelectedItem().toString());
                    jsonObject.put("Sector", sectorcodespinner.getSelectedItem().toString());
                    jsonObject.put("Accompanied", accompaniedbyspinner.getSelectedItem().toString());

                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                    instance = new InspectionOverview(fab, left, middle, right);
                    FragmentTransaction ft = mFrgAct.getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.homepage, instance);
                    Log.i("NewInspectionFragment", "Back stack count " + Integer.toString(mFrgAct.getSupportFragmentManager().getBackStackEntryCount()));
                    ft.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_inspection, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        stationstartspinner = view.findViewById(R.id.stationstartspinner);
        ArrayAdapter<CharSequence> stationstartadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.stations_arrays, android.R.layout.simple_spinner_item);
        stationstartadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stationstartspinner.setAdapter(stationstartadapter);

        stationendspinner = view.findViewById(R.id.stationendspinner);
        ArrayAdapter<CharSequence> stationendadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.stations_arrays, android.R.layout.simple_spinner_item);
        stationendadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stationendspinner.setAdapter(stationendadapter);

        boundspinner = view.findViewById(R.id.boundspinner);
        ArrayAdapter<CharSequence> boundadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.bound_arrays, android.R.layout.simple_spinner_item);
        boundadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boundspinner.setAdapter(boundadapter);

        sectorcodespinner = view.findViewById(R.id.sectorcodespinner);
        ArrayAdapter<CharSequence> sectorcodeadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.sector_arrays, android.R.layout.simple_spinner_item);
        sectorcodeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectorcodespinner.setAdapter(sectorcodeadapter);

        accompaniedbyspinner = view.findViewById(R.id.accompaniedbyspinner);
        ArrayAdapter<CharSequence> accompaniedbyadapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.accompany_array, android.R.layout.simple_spinner_item);
        accompaniedbyadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accompaniedbyspinner.setAdapter(accompaniedbyadapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
