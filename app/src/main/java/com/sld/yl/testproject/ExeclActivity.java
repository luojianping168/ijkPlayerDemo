package com.sld.yl.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sld.yl.testproject.utils.ExcelListener;
import com.sld.yl.testproject.base.LoanInfo;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExeclActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execl);
        testExcel2003NoModel();
        //testExcel2003WithReflectModel();
    }

    public void testExcel2003NoModel() {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("申请安全检查车辆汇总列表.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
            List<Object> datas = listener.getDatas();
            Log.e("lallalalal", "testExcel2003NoModel: "+datas.toString() );
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void testExcel2003WithReflectModel() {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("申请安全检查车辆汇总列表.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);

            excelReader.read(new Sheet(1, 2, LoanInfo.class));
            List<Sheet> sheets = excelReader.getSheets();
            for (Sheet sheet : sheets) {
                sheet.toString();
            }
        } catch (Exception e) {

        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
