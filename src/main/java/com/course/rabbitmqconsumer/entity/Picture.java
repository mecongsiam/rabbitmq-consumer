package com.course.rabbitmqconsumer.entity;

public class Picture {

    private String name;

    private String type;

    private String source;

    private Long size;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", size=" + size +
                '}';
    }
}
