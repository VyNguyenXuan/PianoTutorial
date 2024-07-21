package com.example.pianotutorial.features.components.helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pianotutorial.features.playscreen.activities.PlayScreenActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, Boolean> {

    private final WeakReference<Context> contextRef;

    // Constructor to accept context
    public DownloadTask(Context context) {
        this.contextRef = new WeakReference<>(context);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String fileURL = strings[0];
        String fileName = extractFileName(fileURL);
        Context context = contextRef.get();

        if (context == null) {
            return false;
        }

        try {
            if (fileExists(context, fileName)) {
                return true;
            }

            URL url = new URL(fileURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }

            InputStream input = connection.getInputStream();
            File file = new File(context.getFilesDir(), fileName);
            FileOutputStream output = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            output.close();
            input.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Context context = contextRef.get();
        if (context == null) {
            return;
        }

        if (context instanceof PlayScreenActivity) {
            ((PlayScreenActivity) context).runOnUiThread(() -> {
                if (result) {
                    Toast.makeText(context, "Music is ready to play!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Download failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String extractFileName(String fileURL) {
        String fileName;
        if (fileURL.contains("%2F")) {
            int startIndex = fileURL.lastIndexOf("%2F") + 3;
            int endIndex = fileURL.indexOf("?");
            fileName = fileURL.substring(startIndex, endIndex);
        } else {
            fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1, fileURL.indexOf('?'));
        }
        return fileName;
    }

    private boolean fileExists(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file.exists();
    }
}
