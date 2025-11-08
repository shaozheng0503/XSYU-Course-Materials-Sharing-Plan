package com.example.cinema.vo;

/**
 * @author hxw
 * @date 2019-5-22
 */
public class BuyCardHistoryVO extends BriefConsumeHisVO{
    /**
     * 会员卡类型
     */
    private String cardType;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
