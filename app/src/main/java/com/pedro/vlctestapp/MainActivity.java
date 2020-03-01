package com.pedro.vlctestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;
import com.pedro.vlc.VlcListener;
import com.pedro.vlc.VlcVideoLibrary;
import org.videolan.libvlc.MediaPlayer;
import java.util.Arrays;

/**
 * Created by pedro on 25/06/17.
 */
public class MainActivity extends AppCompatActivity implements VlcListener {

  private VlcVideoLibrary vlcVideoLibrary;

  private String[] options = new String[]{":fullscreen"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_main);
    SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
    vlcVideoLibrary = new VlcVideoLibrary(this, this, surfaceView);
    vlcVideoLibrary.setOptions(Arrays.asList(options));
  }

  @Override
  public void onComplete() {
    Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onResume() {
    vlcVideoLibrary.play("rtsp://ipc:554/stream1");;
    super.onResume();
  }

  @Override
  public void onPause() {
    Log.i("app", "onPause");
    if (vlcVideoLibrary.isPlaying()) {
      vlcVideoLibrary.stop();
    }
    super.onPause();
  }

  @Override
  public void onError() {
    Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show();
    vlcVideoLibrary.stop();
  }

  @Override
  public void onBuffering(MediaPlayer.Event event) {

  }
}
