package com.sld.yl.testproject.base;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by luojianping on 2018/7/26
 * Describe 作者很懒什么都没有写
 * Package name com.sld.yl.testproject.utils
 */
public class LoanInfo extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String bankLoanId;

    @ExcelProperty(index = 1)
    private String customerId;

    @ExcelProperty(index = 2)
    private String loanDate;

    @ExcelProperty(index = 3)
    private String quota;

    @ExcelProperty(index = 4)
    private String bankInterestRate;

    @ExcelProperty(index = 5)
    private String loanTerm;



}
