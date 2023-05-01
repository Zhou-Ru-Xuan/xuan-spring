package org.springframework.core.io;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface ResourceLoader {

	Resource getResource(String location);
}
