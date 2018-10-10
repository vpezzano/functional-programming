package com.techprimers.functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapToIntExample {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Peter", "Sam", "Greg", "Ryan");

		List<User> users = names.stream().filter(MapToIntExample::isNotSam).map(User::new).collect(Collectors.toList());
		System.out.println("Users: " + users);

		System.out.println("\nFunctional style using lambda expression:");
		int sum1 = users.stream().mapToInt(user -> user.getAge()).sum();
		System.out.println("Sum: " + sum1);
		
		System.out.println("\nFunctional style using method reference:");
		int sum2 = users.stream().mapToInt(User::getAge).sum();
		System.out.println("Sum: " + sum2);
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
