package com.rjz.lifecycledemo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MainActivity extends AppCompatActivity {


    MusicPlayerLifeCycle musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicPlayer = new MusicPlayerLifeCycle();
        getLifecycle().addObserver(musicPlayer);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(musicPlayer);
    }

    private class MusicPlayerLifeCycle implements LifecycleObserver {

        private MediaPlayer m;

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public void initMusicPlayer() {
            Log.e("!_@_", "- ON_CREATE call - ");

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED)) {
                m = new MediaPlayer();
                try {

                    AssetFileDescriptor descriptor = getAssets().openFd("kalank.mp3");
                    m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    descriptor.close();

                    m.prepare();
                    m.setVolume(1f, 1f);
                    m.setLooping(true);
//            m.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void resumed() {
            Log.e("!_@_", "-  ON_RESUME call - ");
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                m.start();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void paused() {
            Log.e("!_@_", "- ON_PAUSE call - ");
            m.pause();
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroyed() {
            Log.e("!_@_", "- on ON_DESTROY call - ");
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                if (m.isPlaying()) {
                    m.stop();
                }
                m.reset();
                m.release();
                m = null;
            }
        }
    }
}
