package drainageApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileConfig {
    // List to hold multiple files
    private static final List<File> files = new ArrayList<>();

    static {
        // Predefined files
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_YTD.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2017.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2018.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2019.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2020.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2021.csv"));
        files.add(new File("C:\\Users\\szq20\\OneDrive\\Desktop\\COMP3220-team10\\COMP3220-Team10\\drainageCsvFIles\\Drainage_2022.csv"));
    }

    public static List<File> getFiles() {
        return new ArrayList<>(files);  
    }

    // Get the name of the first file in the list
    public static String getFileName(int index) {
        if (index >= 0 && index < files.size()) {
            return files.get(index).getName();
        }
        return "Unknown";
    }

    // Get the size of the first file in the list
    public static long getFileSize(int index) {
        if (index >= 0 && index < files.size()) {
            return files.get(index).length();  // Size in bytes
        }
        return 0;
    }

    // Get the file type of the first file in the list
    public static String getFileType(int index) {
        if (index >= 0 && index < files.size()) {
            String fileName = getFileName(index);
            int dotIndex = fileName.lastIndexOf('.');
            return (dotIndex > 0) ? fileName.substring(dotIndex + 1) : "Unknown";
        }
        return "Unknown";
    }

    // Add a new file to the list
    public static void addFile(File file) {
        if (file != null && file.exists()) {
            files.add(file);
        }
    }

    // Remove a file from the list by index
    public static void removeFile(int index) {
        if (index >= 0 && index < files.size()) {
            files.remove(index);
        }
    }

    // Get all the file names in the list and return as a String array
    public static String[] getAllFileNames() {
        String[] fileNames = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            fileNames[i] = files.get(i).getName();
        }
        return fileNames;
    }
}
