package domain.dto;

public class CarDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                '}';
    }
}
