package infosecadventures.allsafe.challenges;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecorderService extends Service implements MediaRecorder.OnInfoListener {

    private MediaRecorder mediaRecorder;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startRecording();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startRecording() {
        Toast.makeText(this, "Audio recording started!", Toast.LENGTH_SHORT).show();
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setMaxDuration(10000);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setAudioEncodingBitRate(64000);
            mediaRecorder.setAudioSamplingRate(16000);
            File outputFile = getOutputFile();
            mediaRecorder.setOutputFile(outputFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            Log.d("ALLSAFE", "Exception: " + e.getMessage());
        }
    }

    private void stopRecording() {
        try {
            if (null != mediaRecorder) {
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
            }
            stopSelf();
        } catch (Exception e) {
            Log.d("ALLSAFE", "Exception: " + e.getMessage());
        }
        Toast.makeText(getApplicationContext(), "Audio recording stopped!", Toast.LENGTH_SHORT).show();
    }

    private File getOutputFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US);
        String fullPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                .getAbsolutePath()
                + "/allsafe_rec_"
                + dateFormat.format(new Date())
                + ".mp3";
        Toast.makeText(getApplicationContext(), "File: " + fullPath, Toast.LENGTH_SHORT).show();
        return new File(fullPath);
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        stopRecording();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRecording();
    }
}
