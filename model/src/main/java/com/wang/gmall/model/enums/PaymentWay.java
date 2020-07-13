package com.wang.gmall.model.enums;

/**
 * @author 叶瑟
 */

public enum PaymentWay {
    ONLINE("在线支付"),
    OUTLINE("货到付款" );

    private String comment ;


    PaymentWay(String comment ){
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
