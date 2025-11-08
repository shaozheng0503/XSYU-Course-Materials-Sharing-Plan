package com.atguigu.spring6.di;

import org.springframework.core.io.Resource;

public class ResourceBean {

    private Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public Resource getResource() {
        return resource;
    }

    public void parse() {
        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());
    }
}
