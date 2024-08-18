public class Bar {
    private String dateTime;
    private String[] label;
    private Monthly[] data;

    public Bar(String dateTime, String[] label, Monthly[] data) {
        this.dateTime = dateTime;
        this.label = label;
        this.data = data;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public Monthly[] getData() {
        return data;
    }

    public void setData(Monthly[] data) {
        this.data = data;
    }
}
