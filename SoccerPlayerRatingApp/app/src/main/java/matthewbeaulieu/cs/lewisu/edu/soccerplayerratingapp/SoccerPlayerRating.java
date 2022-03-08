package matthewbeaulieu.cs.lewisu.edu.soccerplayerratingapp;

public class SoccerPlayerRating {
    private String playerName;
    private String teamName;
    private String nationality;
    private String position;
    private int rating;
    private int ratingId;

    public SoccerPlayerRating() {

        playerName = "";
        teamName = "";
        nationality = "";
        position = "";
        rating = 0;
        ratingId = 1;
    }

    public SoccerPlayerRating(int ratingId, String playerName, String teamName, String nationality, String position, int rating) {
        this.ratingId = ratingId;
        this.playerName = playerName;
        this.teamName = teamName;
        this.nationality = nationality;
        this.position = position;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "ratingID=" + ratingId + "\n" +
                "playerName=" + playerName + "\n" +
                "teamName=" + teamName + '\n' +
                "nationality=" + nationality + '\n' +
                "position=" + position + '\n' +
                "rating=" + rating;
    }
}
