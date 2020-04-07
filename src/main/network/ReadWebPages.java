package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//Referenced from : http://zetcode.com/articles/javareadwebpage/

public class ReadWebPages {

    public static void webReader() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}
