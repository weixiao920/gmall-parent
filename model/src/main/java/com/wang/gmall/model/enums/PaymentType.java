package com.wang.gmall.model.enums;

/**
 * @author 叶瑟
 */

public enum PaymentType {
    ALIPAY("支付宝"),
    WEIXIN("微信" );

    private String comment ;


    PaymentType(String comment ){
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
