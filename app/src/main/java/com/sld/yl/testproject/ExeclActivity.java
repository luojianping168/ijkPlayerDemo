package com.sld.yl.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.sld.yl.testproject.base.LoanInfo;
import com.sld.yl.testproject.utils.DataBean;
import com.sld.yl.testproject.utils.ExcelListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExeclActivity extends AppCompatActivity {

    @BindView(R.id.holle)
    TextView holle;
    private List<List<String>> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execl);
        ButterKnife.bind(this);
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
            mDatas = listener.getDatas();
            Log.e("lallalalal", "testExcel2003NoModel: " + mDatas.toString());


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

    @OnClick(R.id.holle)
    public void onViewClicked() {
        Intent intent = new Intent(this,ShowExcelActivity.class);
        intent.putExtra("DATA", new DataBean(mDatas));
        startActivity(intent);
    }
}
