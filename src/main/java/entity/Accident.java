package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Accident implements Entity {
    private static final String SECONDS = ":00";

    private int id;
    private String description;
    private BigDecimal materialDamage;
    private Timestamp date;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMaterialDamage() {
        return materialDamage;
    }

    public void setMaterialDamage(BigDecimal materialDamage) {
        this.materialDamage = materialDamage;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDate(String dateReceived) {
        this.date = Timestamp.valueOf(dateReceived+SECONDS);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
