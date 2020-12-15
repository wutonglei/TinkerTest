package com.example.tinkertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

/**
 * 增加 View  增加新资源文件图片 增加类并用其计算返回到 Text   一次性搞定
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String patchDir;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.btn_update1);
        Button button2 = findViewById(R.id.btn_update2);
        Button button3 = findViewById(R.id.btn_update3);
        ImageView iv = findViewById(R.id.iv);

        text = findViewById(R.id.tv);


        askForRequiredPermissions();
        patchDir = getExternalCacheDir().getAbsolutePath() + "/tpatch/";
        Log.i(TAG, "onCreate: " + patchDir);
        text.setText(patchDir);
        File file = new File(patchDir);
        Log.i(TAG, "loadApk: " + Environment.getExternalStorageDirectory().getAbsolutePath());
        if (file != null || !file.exists()) {
            file.mkdir();
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadApk("1");
            }
        });
    }

    public void loadApk(String version) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tinker1.0." + version + ".apk";
        text.append(path+"二次更新了");
        File file = new File(path);
        if (file.exists()) {
            Toast.makeText(this, "补丁已经存在111更新后二次更新了", Toast.LENGTH_SHORT).show();
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
            Log.d("swy", path);
        } else {
            Toast.makeText(this, "补丁已经不存在111更新后第二次更新了", Toast.LENGTH_SHORT).show();
            Log.d("swy", path);
        }
    }


    private void askForRequiredPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }


    private boolean hasRequiredPermissions() {
        if (Build.VERSION.SDK_INT >= 16) {
            final int res = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            return res == PackageManager.PERMISSION_GRANTED;
        } else {
            // When SDK_INT is below 16, READ_EXTERNAL_STORAGE will also be granted if WRITE_EXTERNAL_STORAGE is granted.
            final int res = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return res == PackageManager.PERMISSION_GRANTED;
        }
    }
}
