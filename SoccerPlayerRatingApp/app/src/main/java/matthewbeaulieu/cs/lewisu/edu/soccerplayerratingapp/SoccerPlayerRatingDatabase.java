package matthewbeaulieu.cs.lewisu.edu.soccerplayerratingapp;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class SoccerPlayerRatingDatabase {
    private static SoccerPlayerRatingDatabase soccerPlayerRatingDatabase;
    private List<SoccerPlayerRating> soccerPlayerRatings;

    public static SoccerPlayerRatingDatabase getInstance(Context context){
        if(soccerPlayerRatingDatabase == null){
            soccerPlayerRatingDatabase = new SoccerPlayerRatingDatabase(context);
        }
        return soccerPlayerRatingDatabase;
    }

    private SoccerPlayerRatingDatabase(Context context){
        soccerPlayerRatings = new ArrayList<>();
        Resources res = context.getResources();

        String[] playerNames = res.getStringArray(R.array.playerNames);
        String[] teamNames = res.getStringArray(R.array.teamNames);
        String[] positions = res.getStringArray(R.array.position);
        String[] nationalities = res.getStringArray(R.array.nationality);
        String[] ratings = res.getStringArray(R.array.rating);

        for(int i = 0; i < ratings.length; i++){
            int id = i + 1;
            String playerName = playerNames[i];
            String teamName = teamNames[i];
            String position = positions[i];
            String nationality = nationalities[i];
            int rating = Integer.parseInt(ratings[i]);
            soccerPlayerRatings.add(new SoccerPlayerRating(id, playerName, teamName, position, nationality, rating));
        }
    }

    public List<SoccerPlayerRating> getSoccerPlayerRatings() {
        return soccerPlayerRatings;
    }

    public SoccerPlayerRating getSoccerPlayerRating(int id) {
        for (SoccerPlayerRating rating : soccerPlayerRatings) {
            if(rating.getRatingId() == id){
                return rating;
            }
        }
        return null;
    }
}
