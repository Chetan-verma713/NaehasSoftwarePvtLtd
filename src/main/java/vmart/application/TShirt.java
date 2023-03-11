package vmart.application;

public class TShirt {
    private String id;
    private Info info;

    public TShirt(String id, Info info) {
        setId(id);
        setInfo(info);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
