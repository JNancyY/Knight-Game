package scores;

import java.io.*;
import java.util.*;

// Part of the following code may be referenced from:
// http://forum.codecall.net/topic/50071-making-a-simple-high-score-system/

public class HighScoreManager {
    private Map<String, ArrayList<Integer>> scores;
    public static final String highScoresFile = "./data/highScores";

    ObjectOutputStream outPutStream = null;
    ObjectInputStream inPutStream = null;

    public HighScoreManager() {
        scores = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new Score to the saved file
    public void addScore(String userName, int scoreVal, String file) {
        loadScoreFile(file);

        ArrayList<Integer> scoreList;
        if (!scores.containsKey(userName)) {
            scoreList = new ArrayList<>();
            scoreList.add(scoreVal);
            Collections.sort(scoreList);
            Collections.reverse(scoreList);
            scores.put(userName, scoreList);
        } else {
            scoreList = scores.get(userName);
            scoreList.add(scoreVal);
            Collections.sort(scoreList);
            Collections.reverse(scoreList);
        }

        updateScoreFile(file);

    }

    // EFFECTS: returns scores of the given username
    public ArrayList<Integer> getScores(String userName, String file) {
        loadScoreFile(file);
        return scores.get(userName);
    }

    //EFFECTS: loads the list scores in the highScoresFile and puts it in the scores list
    public void loadScoreFile(String file) {
        try {
            inPutStream = new ObjectInputStream(new FileInputStream(file));
            scores = (Map<String, ArrayList<Integer>>) inPutStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Load] IO Error:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error:" + e.getMessage());
        } finally {
            closeStream();
        }
    }

    //EFFECTS: finishes the loadScoreFile function
    public void closeStream() {
        try {
            if (outPutStream != null) {
                outPutStream.flush();
                outPutStream.close();
            }
        } catch (IOException e) {
            System.out.println("[Load] IO Error:" + e.getMessage());
        }
    }

    //EFFECTS: saves the list of scores to the highScoresFile
    public void updateScoreFile(String file) {
        try {
            outPutStream = new ObjectOutputStream(new FileOutputStream(file));
            outPutStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outPutStream != null) {
                    outPutStream.flush();
                    outPutStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    // EFFECTS: prints a list of high scores
    public void print() {
        for (Map.Entry<String, ArrayList<Integer>> e : scores.entrySet()) {
            ArrayList<Integer> scoreValues = e.getValue();
            for (int s : scoreValues) {
                System.out.println(e.getKey() + " : " + s + " points");
            }
        }
    }
}
