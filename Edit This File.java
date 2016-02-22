package rz.rasel.installinbackgroundsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import rz.rasel.installinbackgroundsample.apihelper.ApplicationManager;
import rz.rasel.installinbackgroundsample.apihelper.OnInstalledPackaged;

public class InstallInBackgroundSample extends AppCompatActivity {
    //|------------------------------------|
    public static final String TAG = "InstallInBackground";
    private Context context;

    //|------------------------------------|
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_in_background_sample);
        //|------------------------------------|
        context = this;
        //|------------------------------------|
        try {
            final ApplicationManager am = new ApplicationManager(InstallInBackgroundSample.this);
            am.setOnInstalledPackaged(new OnInstalledPackaged() {

                public void packageInstalled(String packageName, int returnCode) {
                    if (returnCode == ApplicationManager.INSTALL_SUCCEEDED) {
                        Log.d(TAG, "Install succeeded");
                    } else {
                        Log.d(TAG, "Install failed: " + returnCode);
                    }
                }
            });

            final TextView txtApkFilePath = (TextView) findViewById(R.id.txtApkFilePath);

            Button btnInstall = (Button) findViewById(R.id.btnInstall);
            btnInstall.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    /*try {
                        am.installPackage(txtApkFilePath.getText().toString());
                    } catch (Exception e) {
                        logError(e);
                    }*/
                    String apkUrl = "";
                    apkUrl = "http://download.mobogarden.com/mu3/000/338/614/apk/de085576f361455ba4bc3808234ab0d1.apk";
                    apkUrl = "http://download.mobogarden.com/mu3/000/338/614/apk/de085576f361455ba4bc3808234ab0d1.apk";
                    apkUrl = "http://file.appsapk.com/wp-content/uploads/downloads/GPS%20Test.apk";
                    //installApp(apkUrl);
                    //new ClsAsyncTaskDownloadApk().execute(apkUrl);
                    //mkDirectory();
                    //Toast.makeText(getApplicationContext(), new UrlProperty().getProtocolName(apkUrl), Toast.LENGTH_LONG).show();
                    String fileNameWithoutExt = new UtilsFileName().getExtension("Testtt.txt");
                    Toast.makeText(getApplicationContext(), fileNameWithoutExt, Toast.LENGTH_LONG).show();
                    File file = new File("bc/test.txt");
                    Toast.makeText(getApplicationContext(), file.getName(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            logError(e);
        }
        //|------------------------------------|
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private void installApp(String argApkUrl) {
        try {
            Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse(argApkUrl))
                    .setType("application/vnd.android.package-archive");
            startActivity(promptInstall);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private void logError(Exception e) {
        e.printStackTrace();
        Toast.makeText(InstallInBackgroundSample.this, R.string.error, Toast.LENGTH_LONG).show();
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public class ClsAsyncTaskDownloadApk extends AsyncTask<String, Void, String> {
        List urlParameters;
        ProgressDialog progressDialog = null;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... params) {
            String retVal = null;
            String requestUrl = "";
            requestUrl = params[0];
            downloadApk(requestUrl);
            retVal = "Finished";
            Log.d("REQUEST_URL", requestUrl);
            return retVal;
        }

        @Override
        protected void onPostExecute(String results) {
            Log.d("REQUEST_URL", results);
            installApk();
            progressDialog.dismiss();
        }
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File("/sdcard/filename.apk"));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private void downloadApk(String argUrl) {
        try {
            URL url = new URL(argUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            int fileLength = urlConnection.getContentLength();

            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "filename.apk");

            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            long total = 0;
            int count;

            //while ((bufferLength = inputStream.read(buffer)) > 0) {
            while ((count = inputStream.read(buffer)) != -1) {
                /*if (isCancelled()) {
                    input.close();
                    return null;
                }*/
                total += count;
                //fileOutput.write(buffer, 0, bufferLength);
                fileOutput.write(buffer, 0, count);
            }
            fileOutput.flush();
            fileOutput.close();
            inputStream.close();
            //this.checkUnknownSourceEnability();
            //this.initiateInstallation();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private void mkDirectory() {
        /*boolean success = (new File("your/directory/name")).delete();
        if (!success) {
            System.out.println("Deletion failed!");
        }*/
        /*File file = new File("/storage/sdcard0/DCIM/Camera/1414240995236.jpg");
        String strFileName = file.getName();*/
        String rootDri = "RzApp";
        Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory() + " -", Toast.LENGTH_LONG).show();
        if (isExternalStorageWritable()) {
            File myDirectory = new File(Environment.getExternalStorageDirectory(), rootDri + "/.RzCache/.rz.rasel.installinbackgroundsample");

            if (!myDirectory.exists()) {
                myDirectory.mkdirs();
            }
            if (!myDirectory.exists()) {
                String directoryRoot = "RzApp/dirName";
                File fileRz = new File(Environment.getExternalStorageDirectory(), rootDri);
                //boolean success = (new File(directoryRoot)).delete();
                //boolean success = fileRz.delete();
                boolean success = deleteDir(fileRz);
                if (!success) {
                    System.out.println("Deletion failed!");
                    Toast.makeText(getApplicationContext(), "Deletion failed!", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Deletion!", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), fileRz.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public class UrlProperty
    {
        public String getProtocolName(String argUrl)
        {
            String retVal = "";
            try
            {
                URL url = new URL(argUrl);
                retVal = url.getProtocol();
                return retVal;
            }
            catch (MalformedURLException e)
            {
                // do something
            }
            return retVal;
        }
        public String getHostName(String argUrl)
        {
            String retVal = "";
            try
            {
                URL url = new URL(argUrl);
                retVal = url.getHost();
            }
            catch (MalformedURLException e)
            {
                // do something
            }
            return retVal;
        }
    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    public class UtilsFileName
    {
        public String removeExtension(String filename) {
            if (filename == null) {
                return null;
            }

            int index = indexOfExtension(filename);

            if (index == -1) {
                return filename;
            } else {
                return filename.substring(0, index);
            }
        }

        /**
         * Return the file extension from a filename, including the "."
         *
         * e.g. /path/to/myfile.jpg -> .jpg
         */
        public String getExtension(String filename) {
            if (filename == null) {
                return null;
            }

            int index = indexOfExtension(filename);

            if (index == -1) {
                return filename;
            } else {
                return filename.substring(index);
            }
        }

        private static final char EXTENSION_SEPARATOR = '.';
        private static final char DIRECTORY_SEPARATOR = '/';

        public int indexOfExtension(String filename) {

            if (filename == null) {
                return -1;
            }

            // Check that no directory separator appears after the
            // EXTENSION_SEPARATOR
            int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);

            int lastDirSeparator = filename.lastIndexOf(DIRECTORY_SEPARATOR);

            if (lastDirSeparator > extensionPos) {
                //LogIt.w(FileSystemUtil.class, "A directory separator appears after the file extension, assuming there is no file extension");
                return -1;
            }

            return extensionPos;
        }
    }
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    /*
    public void testRegex() {
        assertEquals("test", "test.xml".replaceFirst("[.][^.]+$", ""));
        assertEquals("test.2", "test.2.xml".replaceFirst("[.][^.]+$", ""));
    }
    String str = "filename.xml";
    if (!str.contains("."))
            System.out.println("File Name=" + str);
    else {
        str = str.substring(0, str.lastIndexOf("."));
        // Because extension is always after the last '.'
        System.out.println("File Name=" + str);
    }

    new File(absolutePath).getName();
    */
    //|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
}
