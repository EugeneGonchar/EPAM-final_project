package domain.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Accident implements Entity {
    private int id;
    private String description;
    private BigDecimal materialDamage;
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Accident accident = (Accident) o;

        if (getId() != accident.getId()){
            return false;
        }
        if (getOrderId() != accident.getOrderId()){
            return false;
        }
        if (getDescription() != null ? !getDescription().equals(accident.getDescription()) : accident.getDescription() != null){
            return false;
        }

        if (getMaterialDamage() != null ? !getMaterialDamage().equals(accident.getMaterialDamage()) : accident.getMaterialDamage() != null){
            return false;
        }
        return getDate() != null ? getDate().equals(accident.getDate()) : accident.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 37 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 37 * result + (getMaterialDamage() != null ? getMaterialDamage().hashCode() : 0);
        result = 37 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 37 * result + getOrderId();
        return result;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", materialDamage=" + materialDamage +
                ", date=" + date +
                ", orderId=" + orderId +
                '}';
    }
}
