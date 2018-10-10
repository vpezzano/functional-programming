package com.techprimers.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FilterExample {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Peter", "Sam", "Greg", "Ryan");

		System.out.println("Imperative style:");
		for (String name : names) {
			if (!name.equals("Sam")) {
				System.out.println(name);
			}
		}

		System.out.println("\nFunctional style using consumer:");
		// Using a consumer
		names.stream().filter(name -> !name.equals("Sam")).forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);

			}
		});

		System.out.println("\nFunctional style using lambda expression:");
		// Using a lambda expression
		names.stream().filter(name -> !name.equals("Sam")).forEach(name -> System.out.println(name));

		System.out.println("\nFunctional style using method reference:");
		// Using a method reference
		names.stream().filter(name -> !name.equals("Sam")).forEach(System.out::println);

		System.out.println("\nFunctional style using method reference for private class method:");
		// Using a method reference
		names.stream().filter(FilterExample::isNotSam).forEach(System.out::println);
	}

	private static boolean isNotSam(String name) {
		return name == null || !name.equals("Sam");
	}
}
