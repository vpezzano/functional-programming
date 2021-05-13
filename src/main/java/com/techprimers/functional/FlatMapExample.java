package com.techprimers.functional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class FlatMapExample {

	public static void main(String[] args) {
		List<User> users = Arrays.asList(new User("Peter", 20, Arrays.asList("0511383762", "0611272952")),
				new User("Sam", 30, Arrays.asList("0711383762", "0711272952", "0711272953")),
				new User("Greg", 35, Arrays.asList("0811383762", "0811272952")),
				new User("Ryan", 22, Arrays.asList("0911383762", "0911272952")));

		// As map returns a stream, hereafter result will be a Stream of Streams
		Stream<Stream<String>> result = users.stream().map(user -> user.getPhoneNumbers().stream());
		System.out.println("Result: " + result);

		Optional<String> found = users.stream().flatMap(user -> user.getPhoneNumbers().stream())
				.filter(number -> number.equals("0711272953")).findAny();

		if (found.isPresent()) {
			System.out.println("Found: " + found.get());
		}

		// We can filter also while flattening
		found = users.stream()
				.flatMap(user -> user.getPhoneNumbers().stream().filter(number -> number.equals("0711272953")))
				.findAny();

		if (found.isPresent()) {
			System.out.println("Found: " + found.get());
		}

		found = users.stream().map(user -> user.getPhoneNumbers().stream())
				.flatMap(stringStream -> stringStream.filter(number -> number.equals("0711272953"))).findAny();

		if (found.isPresent()) {
			System.out.println("Found: " + found.get());
		}
		
		found = users.stream().map(user -> user.getPhoneNumbers().stream()).flatMap(Function.identity())
				.filter(number -> number.equals("0711272953")).findAny();

		if (found.isPresent()) {
			System.out.println("Found: " + found.get());
		}
	}

	private static class User {

		private String name;
		private Integer age;
		private List<String> phoneNumbers;

		public User(String name, Integer age, List<String> phoneNumbers) {
			this.name = name;
			this.age = age;
			this.phoneNumbers = phoneNumbers;
		}

		public List<String> getPhoneNumbers() {
			return phoneNumbers;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", age=" + age + ", phoneNumbers=" + phoneNumbers + "]";
		}
	}
}
