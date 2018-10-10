package com.techprimers.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperExample {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Peter", "Sam", "Greg", "Ryan");

		System.out.println("\nImperative style:");
		for (String name : names) {
			if (!name.equals("Sam")) {
				User user = new User(name);
				System.out.println(user);
			}
		}

		System.out.println("\nFunctional style using function:");
		names.stream().filter(x -> !x.equals("Sam")).map(new Function<String, MapperExample.User>() {
			@Override
			public User apply(String name) {
				return new User(name);
			}
		}).forEach(System.out::println); // foreach is a terminal operation

		System.out.println("\nFunctional style using lambda expression:");
		names.stream().filter(x -> !x.equals("Sam")).map(name -> new User(name)).forEach(System.out::println);

		System.out.println("\nFunctional style using constructor reference:");
		names.stream().filter(MapperExample::isNotSam).map(User::new).forEach(System.out::println);

		System.out.println("\nFunctional style using constructor reference and method reference:");
		List<User> users = names.stream().filter(MapperExample::isNotSam).map(User::new).collect(Collectors.toList()); // collect is a terminal operation
		System.out.println("Users: " + users);
	}

	private static boolean isNotSam(String name) {
		return !name.equals("Sam");
	}

	static class User {

		private String name;
		private Integer age = (int) (Math.random() * 100);

		public User(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public Integer getAge() {
			return age;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + "]";
		}
	}
}
