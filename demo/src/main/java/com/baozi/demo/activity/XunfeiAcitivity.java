package com.baozi.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.baozi.demo.R;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by baozi on 2017/5/8.
 * 讯飞语音识别
 */

public class XunfeiAcitivity extends Activity {

    private RecognizerDialog mDialog;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xunfei);
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=591050ed");
        findViewById(R.id.bt_say).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4.显示dialog，接收语音输入
                mDialog.show();
            }
        });
        mEditText = (EditText) findViewById(R.id.bt_result);

        //1.创建RecognizerDialog对象
        mDialog = new RecognizerDialog(this, null);
//
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mDialog.setParameter(SpeechConstant.VAD_ENABLE, "1");
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mDialog.setParameter(SpeechConstant.VAD_BOS, "4000");
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mDialog.setParameter(SpeechConstant.VAD_EOS, "1000");
        mDialog.setParameter(SpeechConstant.ASR_PTT, "0");
        //3.设置回调接口
        mDialog.setListener(mRecoListener);
    }

    //听写监听器
    private RecognizerDialogListener mRecoListener = new RecognizerDialogListener() {
        //听写结果回调接口(返回Json格式结果，用户可参见附录13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见Demo中JsonParser类；
        //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            String afterText = mEditText.getText().toString();
            String before = afterText + JsonParser.parseIatResult(results.getResultString());
            mEditText.setText(before);
            mEditText.setSelection(before.length());
        }

        //会话发生错误回调接口
        public void onError(SpeechError error) {
            //打印错误码描述
            Log.d("log", "error:" + error.getPlainDescription(true));
        }
    };

    /**
     * Json结果解析类
     */
    private static class JsonParser {
        static String parseIatResult(String json) {
            StringBuffer ret = new StringBuffer();
            try {
                JSONTokener tokener = new JSONTokener(json);
                JSONObject joResult = new JSONObject(tokener);

                JSONArray words = joResult.getJSONArray("ws");
                for (int i = 0; i < words.length(); i++) {
                    // 转写结果词，默认使用第一个结果
                    JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                    JSONObject obj = items.getJSONObject(0);
                    ret.append(obj.getString("w"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret.toString();
        }
    }
}
