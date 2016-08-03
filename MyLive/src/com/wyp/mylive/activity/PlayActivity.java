package com.wyp.mylive.activity;

import com.wyp.mylive.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * 播放界面
 * @author Administrator
 *
 */
public class PlayActivity extends AppCompatActivity {
	//如果有问题可以用香港卫视的rtmp地址测试rtmp://live.hkstv.hk.lxdns.com/live/hks
	//rtmp://120.24.47.95/mytv/100  后面的100可以随便给  但是必须与com.jutong.live.MainActivity包的一致才能实现 一个推流 一个看
    private static final String DEFAULT_TEST_URL = "rtmp://120.24.47.95/mytv/100";
    
    private Spinner mActivitySpinner;
    private EditText mEditText;
    private int mIsHwCodecEnabled = 0;

    public static final String[] TEST_ACTIVITY_ARRAY = {
            "PLMediaPlayerActivity"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText)findViewById(R.id.VideoPathEdit);
        mEditText.setText(DEFAULT_TEST_URL);

        mActivitySpinner = (Spinner) findViewById(R.id.TestSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TEST_ACTIVITY_ARRAY);
        mActivitySpinner.setAdapter(adapter);
    }

    public void onClickPlaySetting(View v) {
        showPlaySettingDialog();
    }

    public void onClickLocalFile(View v) {
        Intent intent = new Intent(this, VideoFileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onClickPlay(View v) {
        String videopath = mEditText.getText().toString();
        if (!"".equals(videopath)) {
            jumpToPlayerActivity(videopath);
        }
    }

    public void jumpToPlayerActivity(String videopath) {
        Class<?> cls = null;
        switch (mActivitySpinner.getSelectedItemPosition()) {
            case 0: cls = PLMediaPlayerActivity.class;
                break;
            default:
                return;
        }
        Intent intent = new Intent(this, cls);
        intent.putExtra("videoPath", videopath);
        intent.putExtra("mediaCodec", mIsHwCodecEnabled);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        String videoPath = data.getStringExtra("videoPath");
        mEditText.setText(videoPath, TextView.BufferType.EDITABLE);
    }

    protected void showPlaySettingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View root = inflater.inflate(R.layout.dialog_setting,null);
        final Spinner codecSpinner = (Spinner) root.findViewById(R.id.CodecSpinner);
        codecSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[] {
                getString(R.string.sw_decode), getString(R.string.hw_decode)
        }));
        codecSpinner.setSelection(mIsHwCodecEnabled);
        builder.setTitle(getString(R.string.play_setting));
        builder.setView(root);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.dlg_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mIsHwCodecEnabled = codecSpinner.getSelectedItemPosition();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dlg_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
