package edu.epu.realestate.models;

import edu.epu.realestate.MainApplication;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by DuongNArtist on 12/12/2015.
 */
public class AppPreferences {

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApplication.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApplication.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }
}
