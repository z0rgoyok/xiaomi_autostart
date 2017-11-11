package com.zabozhanov.xiaomiautostarttesting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testIt();
            }
        });
    }

    private void testIt() {
        try {
            @SuppressLint("PrivateApi") Class<?> aClass = Class.forName("android.miui.AppOpsUtils");
            if (aClass != null) {
                Method getApplicationAutoStart = aClass.getDeclaredMethod("getApplicationAutoStart", new Class[]{Context.class, String.class});
                Context applicationContext = getApplicationContext();
                Object result = getApplicationAutoStart.invoke(aClass, new Object[]{applicationContext, applicationContext.getPackageName()});
                if (result instanceof Integer) {
                    Log.d("XIAOMI", "Result: " + result.toString());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
