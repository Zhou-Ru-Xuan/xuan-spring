package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;

}
