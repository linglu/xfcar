package com.xfcar.driver.model.bean;

public class CashEntity {

    public String userId;   // ":"28",
    public String username;     // ":"abner",
    public String type;         // ":"10", 10：积分提现； 20：余额提现）
    public String amount;       // ":"100.1"

    public CashEntity(String userId, String username, String type, String amount) {
        this.userId = userId;
        this.username = username;
        this.type = type;
        this.amount = amount;
    }
}
