package com.sld.yl.testproject.utils;

import android.util.Log;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luojianping on 2018/7/26
 * Describe 作者很懒什么都没有写
 * Package name com.sld.yl.testproject.utils
 */
public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<List<String>> datas = new ArrayList<List<String>>();

    public void invoke(Object object, AnalysisContext context) {
        System.out.println("当前行：" + context.getCurrentRowNum());
        System.out.println(object);
        datas.add((List<String>)object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(object);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口
        if (object instanceof List) {
            Log.e("lllll", "doSomething: " + object.toString());
        }
    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }

    public List<List<String>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }
}
