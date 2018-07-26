package com.sld.yl.testproject.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luojianping on 2018/7/26
 * Describe 作者很懒什么都没有写
 * Package name com.sld.yl.testproject.utils
 */
public class DataBean implements Serializable {
    public List<List<String>> data;

    public DataBean(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "data=" + data +
                '}';
    }
}
