package epam.task.web.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BankCards {
    private long cardNumber;
    private int cvvNumber;
    private Date replenishmentDate;
    private long userId;
    private BigDecimal paymentAmount;

    public BankCards(long cardNumber, int cvvNumber,
                     Date replenishmentDate, long userId,
                     BigDecimal paymentAmount) {
        this.cardNumber = cardNumber;
        this.cvvNumber = cvvNumber;
        this.replenishmentDate = replenishmentDate;
        this.userId = userId;
        this.paymentAmount = paymentAmount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public Date getReplenishmentDate() {
        return replenishmentDate;
    }

    public void setReplenishmentDate(Date replenishmentDate) {
        this.replenishmentDate = replenishmentDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
