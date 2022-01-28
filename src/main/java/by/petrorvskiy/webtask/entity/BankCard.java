package by.petrorvskiy.webtask.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class BankCard {
    private long cardNumber;
    private int cvvNumber;
    private LocalDate replenishmentDate;
    private long userId;
    private BigDecimal paymentAmount;

    public BankCard(){}


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

    public LocalDate getReplenishmentDate() {
        return replenishmentDate;
    }

    public void setReplenishmentDate(LocalDate replenishmentDate) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        if (cardNumber != bankCard.cardNumber)
            return false;
        if (cvvNumber != bankCard.cvvNumber)
            return false;
        if (userId != bankCard.userId)
            return false;
        if (replenishmentDate != null ? !replenishmentDate.equals(bankCard.replenishmentDate) : bankCard.replenishmentDate != null) {
            return false;
        }
        return paymentAmount != null ? paymentAmount.equals(bankCard.paymentAmount) : bankCard.paymentAmount == null;

    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + (int) (cvvNumber - (cardNumber >>> 32));
        result = result * 31 + cvvNumber;
        result = result * 31 + replenishmentDate.hashCode();
        result = result * 31 + Long.hashCode(cvvNumber);
        result = result * 31 + paymentAmount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BankCards{");
        sb.append("cardNumber=").append(cardNumber);
        sb.append(", cvvNumber=").append(cvvNumber);
        sb.append(", replenishmentDate='").append(replenishmentDate);
        sb.append("', userId=").append(cvvNumber);
        sb.append(", paymentAmount=").append(paymentAmount).append("}");
        return sb.toString();
    }
}
