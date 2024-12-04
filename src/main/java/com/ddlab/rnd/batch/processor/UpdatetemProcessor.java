package com.ddlab.rnd.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Person;

@Component("updateItemProcessor")
public class UpdatetemProcessor implements ItemProcessor<Object, Object> {

	@Override
	public Person process(Object obj) throws Exception {
		System.out.println("------------ Inside Update Item Processor in Update Step ----------");
		Person item = (Person) obj;
		System.out.println("Processing Item: "+item);
		System.out.println("------------ Inside Update Item Processor in Update Step ----------");
		return item;
	}
}
