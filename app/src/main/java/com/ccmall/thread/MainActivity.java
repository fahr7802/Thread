package com.ccmall.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    SeekBar seek, seekbar1, seekbar2;
    Button btninc, btndec, btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pb);
        seek = findViewById(R.id.seek);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);
        btn = findViewById(R.id.btn);
        btndec = findViewById(R.id.btndec);
        btninc = findViewById(R.id.btninc);
        tv = findViewById(R.id.tv);

        btninc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.incrementProgressBy(10);
            }
        });

        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.incrementProgressBy(-10);
            }
        });

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<100; i++){
                    seekbar1.setProgress(seekbar1.getProgress()+2);
                    seekbar2.setProgress(seekbar2.getProgress()+1);
                    SystemClock.sleep(100);
                }
            }
        });*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        for (int i = seekbar1.getProgress(); i < 100; i += 2) {
                            seekbar1.setProgress(seekbar1.getProgress() + 2);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread() {
                    public void run() {
                        for (int i = seekbar2.getProgress(); i < 100; i++) {
                            seekbar2.setProgress(seekbar2.getProgress()+1);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("seekbar 진행률 : " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}