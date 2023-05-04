package org.springframework.test.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-05-01
 **/
@Getter
@Setter
@ToString
@Component
public class Car {

	@Value("${brand}")
	private String brand;

}
