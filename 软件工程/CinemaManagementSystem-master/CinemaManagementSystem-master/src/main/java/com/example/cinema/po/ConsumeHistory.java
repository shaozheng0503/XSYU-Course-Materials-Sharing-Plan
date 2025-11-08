package com.example.cinema.po;


/**
 * @author hxw
 * @date 2019-5-22
 */
public class ConsumeHistory {
    public final static Integer BUY_TICKET = 101;
    public final static Integer BUY_VIP_CARD = 102;
    public final static Integer BUY_LOTTERY = 103;
    public final static String BUY_TICKET_STR = "购买电影票";
    public final static String BUY_VIP_CARD_STR = "购买会员卡";
    public final static String BUY_LOTTERY_STR = "抽奖";
    /**
     * id
     */
    private Integer id;
    /**
     * 账户id
     */
    private Integer userId;
    /**
     * 消费金额
     */
    private Double money;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 消费方式:银行卡/会员卡
     */
    private String consumeType;
    /**
     * 消费类型:101-购买电影票,102-购买会员卡,103-抽奖
     */
    private Integer type;
    /**
     * 消费内容id:购买电影票-ticket.Id,购买会员卡-vip_card.id,抽奖-无用
     */
    private Integer contentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
