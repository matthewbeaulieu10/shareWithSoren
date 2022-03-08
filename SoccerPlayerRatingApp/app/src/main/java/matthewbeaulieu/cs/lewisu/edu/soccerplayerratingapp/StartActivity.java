package matthewbeaulieu.cs.lewisu.edu.soccerplayerratingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements SoccerPlayerRatingListAdapter.ListAdapterOnClickHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        RecyclerView recyclerView = findViewById(R.id.ratingRecyclerView);
        SoccerPlayerRatingListAdapter soccerPlayerRatingListAdapter = new SoccerPlayerRatingListAdapter(getApplicationContext(), this);
        recyclerView.setAdapter(soccerPlayerRatingListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        soccerPlayerRatingListAdapter.setSoccerPlayerRatings(SoccerPlayerRatingDatabase.getInstance(this).getSoccerPlayerRatings());

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            //Toast.makeText(getApplicationContext(), "I'm Back", Toast.LENGTH_SHORT).show();
                            Intent data = result.getData();
                            int rating = data.getIntExtra("rating", 0);
                            String playerName = data.getStringExtra("playerName");
                            String toastString = getResources().getString(R.string.ratingEntered);
                            String displayCourse = getResources().getString(R.string.displayPlayerName);
                            toastString += displayCourse;
                            String ratingString = getResources().getQuantityString(R.plurals.starRating, rating, rating);
                            toastString+=ratingString;
                            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        /*
        Button launchRatingButton = findViewById(R.id.start_button);
        launchRatingButton.setOnClickListener(v -> {
                Intent ratingIntent = new Intent(getApplicationContext(), MainActivity.class);
                launcher.launch(ratingIntent);
            });

         */
    }

    @Override
    public void onClick(SoccerPlayerRating soccerPlayerRating) {
        Intent ratingIntent = new Intent(this, MainActivity.class);
        ratingIntent.putExtra("id", soccerPlayerRating.getRatingId());
        startActivity(ratingIntent);
    }
}