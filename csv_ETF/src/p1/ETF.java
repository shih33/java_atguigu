package p1;

public class ETF {
    private String date;
    private String symbol;
    private String unitNetValue;
    private String issuedUnits;
    private String totalNetValue;

    public ETF(String date, String symbol, String unitNetValue, String issuedUnits, String totalNetValue) {
        this.date = date;
        this.symbol = symbol;
        this.unitNetValue = unitNetValue;
        this.issuedUnits = issuedUnits;
        this.totalNetValue = totalNetValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUnitNetValue() {
        return unitNetValue;
    }

    public void setUnitNetValue(String unitNetValue) {
        this.unitNetValue = unitNetValue;
    }

    public String getIssuedUnits() {
        return issuedUnits;
    }

    public void setIssuedUnits(String issuedUnits) {
        this.issuedUnits = issuedUnits;
    }

    public String getTotalNetValue() {
        return totalNetValue;
    }

    public void setTotalNetValue(String totalNetValue) {
        this.totalNetValue = totalNetValue;
    }
}
