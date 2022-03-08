package matthewbeaulieu.cs.lewisu.edu.soccerplayerratingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;


public class RatingFragment extends Fragment {
    private SoccerPlayerRating soccerPlayerRating;
    private EditText editPlayerName;
    private EditText editTeamName;
    private Spinner nationalitySpinner;
    private RatingBar ratingBar;
    private Button submitButton;
    private RadioGroup positionRadioGroup;

    private static final String ARG_RATING_ID = "ratingId";

    SoccerPlayerRating soccerPlayerRatingOne;

    public RatingFragment() {
        // Required empty public constructor
    }


    public static RatingFragment newInstance(int ratingId) {
        RatingFragment fragment = new RatingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RATING_ID, ratingId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int ratingId = getArguments().getInt(ARG_RATING_ID);
            soccerPlayerRatingOne = SoccerPlayerRatingDatabase.getInstance(getContext()).getSoccerPlayerRating(ratingId);
        } else {
            //don't have preferences... not really sure what to do about this.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        editPlayerName = view.findViewById(R.id.editPlayerName);
        editTeamName = view.findViewById(R.id.editTeamName);
        nationalitySpinner = view.findViewById(R.id.nationalitySpinner);
        ratingBar = view.findViewById(R.id.ratingBar);
        positionRadioGroup = view.findViewById(R.id.positionRadioGroup);
        nationalitySpinner.setOnItemSelectedListener(new NationalitySelectedListener());
        ratingBar.setOnRatingBarChangeListener(new RatingChangedListener());
        editPlayerName.addTextChangedListener(new NameTextListener(editPlayerName));
        editTeamName.addTextChangedListener(new NameTextListener(editTeamName));

        editPlayerName.setText(soccerPlayerRating.getPlayerName());
        editTeamName.setText(soccerPlayerRating.getTeamName());

        List<String> nationalities = Arrays.asList(getResources().getStringArray(R.array.nationalities));
        int index = nationalities.indexOf(soccerPlayerRating.getNationality());
        nationalitySpinner.setSelection(index);

        ratingBar.setRating(soccerPlayerRating.getRating());

        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sets the position using radio buttons when submit button is clicked
                String position = "undetermined";
                int checkedID = positionRadioGroup.getCheckedRadioButtonId();
                if (checkedID == R.id.strikerRadio) {
                    position = getString(R.string.striker);
                }
                else if (checkedID == R.id.wingerRadio) {
                    position = getString(R.string.winger);
                }
                else if (checkedID == R.id.attackingMidfielderRadio) {
                    position = getString(R.string.attackingMidfielder);
                }
                else if (checkedID == R.id.centralMidfielderRadio) {
                    position = getString(R.string.centralMidfielder);
                }
                else if (checkedID == R.id.holdingMidfielderRadio) {
                    position = getString(R.string.holdingMidfielder);
                }
                else if (checkedID == R.id.fullBackRadio) {
                    position = getString(R.string.fullBack);
                }
                else if (checkedID == R.id.centerBackRadio) {
                    position = getString(R.string.centerBack);
                }
                else if (checkedID == R.id.goalkeeperRadio) {
                    position = getString(R.string.goalkeeper);
                }

                soccerPlayerRating.setPosition(position);

                //Toast.makeText(getApplicationContext(), soccerPlayerRating.toString(), Toast.LENGTH_SHORT).show();
                String playerName = soccerPlayerRating.getPlayerName();
                int rating = soccerPlayerRating.getRating();

            }
        });

        return view;

    }

    private class NationalitySelectedListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String nationality = (String)adapterView.getItemAtPosition(i);
            if (i!=0){
                soccerPlayerRating.setNationality(nationality);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            soccerPlayerRating.setRating((int)v);
        }
    }

    private class NameTextListener implements TextWatcher {
        private View editText;

        public NameTextListener(View editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (editText == editPlayerName){
                soccerPlayerRating.setPlayerName(charSequence.toString());
            }
            else if(editText == editTeamName){
                soccerPlayerRating.setTeamName(charSequence.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }
}