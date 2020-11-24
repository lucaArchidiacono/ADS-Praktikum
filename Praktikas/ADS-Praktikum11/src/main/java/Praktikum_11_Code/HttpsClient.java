package Praktikum_11_Code;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;


public class HttpsClient {
    Pattern pattern = Pattern.compile("(0[0-9]{2} [0-9]{3} [0-9]{2} [0-9]{2})", Pattern.CASE_INSENSITIVE);


    public static void main(String[] args) {
        new HttpsClient().testIt();
    }

    private void testIt() {

        String https_url = "https://tel.search.ch/?was=Meier&pages=20";
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            // dumpl all cert info
            print_https_cert(con);

            // dump all the content
            print_content(con);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void print_https_cert(HttpsURLConnection con) {

        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println(
                            "Cert Public Key Algorithm : "
                                    + cert.getPublicKey().getAlgorithm());
                    System.out.println(
                            "Cert Public Key Format : "
                                    + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void print_content(HttpsURLConnection con) {
        if (con != null) {

            try {

                System.out.println("****** Content of the URL ********");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));

                String input;

                while ((input = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(input);
                    if (matcher.find()){
                        String telnumber = matcher.group();
                        System.out.println(telnumber);
                    }
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
