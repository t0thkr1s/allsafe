package infosecadventures.allsafe.challenges;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class RecorderService extends Service implements MediaRecorder.OnInfoListener {

    private final long MAX_FILE_SIZE = 1000000; // 1 MB
    private final int[] amplitudes = new int[100];
    private final int i = 0;
    private MediaRecorder mediaRecorder;
    private File outputFile;
    private long startTime = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        startRecording();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startRecording() {
        Toast.makeText(this, "Audio recording started!", Toast.LENGTH_SHORT).show();
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setOnInfoListener(this);
            try {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            } catch (Exception e) {
                Log.d("ALLSAFE", e.getMessage());
            }
            mediaRecorder.setMaxFileSize(MAX_FILE_SIZE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
            mediaRecorder.setAudioEncodingBitRate(48000);
            mediaRecorder.setAudioSamplingRate(16000);
            outputFile = getOutputFile();
            Objects.requireNonNull(outputFile.getParentFile()).mkdirs();
            mediaRecorder.setOutputFile(outputFile.getAbsolutePath());

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
                startTime = SystemClock.elapsedRealtime();
            } catch (IOException e) {
                Log.d("ALLSAFE", e.getMessage());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
        startTime = 0;
        if (outputFile != null) {
            outputFile.delete();
        }
        Toast.makeText(this, "Audio recording stopped!", Toast.LENGTH_SHORT).show();
        stopSelf();
    }

    private File getOutputFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.US);
        String fullPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/recording_"
                + dateFormat.format(new Date())
                + ".mp4";
        Toast.makeText(this, "File: " + fullPath, Toast.LENGTH_SHORT).show();
        return new File(fullPath);
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED) {
            stopRecording();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRecording();
    }
}
