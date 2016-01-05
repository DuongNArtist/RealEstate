package edu.epu.realestate.models;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by DuongNArtist on 16/12/2015.
 */
public class Utilities {

    public static boolean isValidUrl(String pUrl) {
        URL url = null;
        try {
            url = new URL(pUrl);
        } catch (MalformedURLException e) {
            return false;
        }
        try {
            url.toURI();
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }
}
