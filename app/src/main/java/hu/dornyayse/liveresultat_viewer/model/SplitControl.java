package hu.dornyayse.liveresultat_viewer.model;

public class SplitControl {

    private Long id;

    private Class ownerClass;

    private Integer code;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(Class ownerClass) {
        this.ownerClass = ownerClass;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
