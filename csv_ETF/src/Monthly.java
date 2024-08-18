import java.math.BigDecimal;

public class Monthly {
    private String name;
    private BigDecimal[] data;

    public Monthly(String name, BigDecimal[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal[] getData() {
        return data;
    }

    public void setData(BigDecimal[] data) {
        this.data = data;
    }
}


