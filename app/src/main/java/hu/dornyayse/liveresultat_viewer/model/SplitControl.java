package hu.dornyayse.liveresultat_viewer.model;

class SplitControl {

    private Long id;

    private Class ownerClass;

    private Integer code;

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
}
