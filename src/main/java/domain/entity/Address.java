package domain.entity;

public class Address implements Entity {
    private int id;
    private String street;
    private String building;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Address address = (Address) o;

        if (getId() != address.getId()){
            return false;
        }
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null){
            return false;
        }
        return getBuilding() != null ? getBuilding().equals(address.getBuilding()) : address.getBuilding() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 37 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 37 * result + (getBuilding() != null ? getBuilding().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}
