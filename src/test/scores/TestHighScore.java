package scores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHighScore {

    String testFile = "./data/testFiles";
    String testFile1 = "./data/testFiles1";

    HighScoreManager hs;
    HighScoreManager test1;

    @BeforeEach
    public void runBefore() {
        hs = new HighScoreManager();
        test1 = new HighScoreManager();
    }

    @Test
    void testSaveHighScore() {
        hs.addScore("Player1", 200, testFile);
        test1.addScore("Player1", 200, testFile1);
        assertEquals(test1.getScores("Player1", testFile), hs.getScores("Player1", testFile1));
    }

    @Test
    public void testLoadHighScore() {
        assertEquals(test1.getScores("Player1", testFile), hs.getScores("Player1", testFile1));
    }

}
