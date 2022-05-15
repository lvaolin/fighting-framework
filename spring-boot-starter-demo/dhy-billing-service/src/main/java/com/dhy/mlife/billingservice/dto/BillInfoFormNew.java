package com.dhy.mlife.billingservice.dto;

import com.dhy.mlife.common.context.AppContext;
import lombok.Data;

@Data
public class BillInfoFormNew extends AppContext {

    private static final long serialVersionUID = 1L;

    private String cardNo;
    private String billDt;//账单日期

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBillDt() {
        return billDt;
    }

    public void setBillDt(String billDt) {
        this.billDt = billDt;
    }

}