package org.springframework.test.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldServiceImpl implements WorldService {
    private String name;

    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
        System.out.println("name: " + getName());
    }
}
