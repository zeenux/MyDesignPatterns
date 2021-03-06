package zeenux.stringfunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

public class Sample {
public static List<Person> createPeople(){
	
	return Arrays.asList(
			new Person("Sara",Gender.FEMALE,20),
			new Person("Sara",Gender.FEMALE,22),
			new Person("Bob",Gender.MALE,20),
			new Person("Paula",Gender.FEMALE,32),
			new Person("Paul",Gender.MALE,32),
			new Person("Jack",Gender.MALE,2),
			new Person("Jack",Gender.MALE,72),
			new Person("Jill",Gender.FEMALE,12)
	);
}



public static void main(String [] args){
	
	//Implementing Formula the new way
	
	Formula fr=new Formula() {
		public double calculate(int a) {
			return Math.sqrt(a);
		}
	};
	
	fr.calculate(10);
	fr.sqrt(200);
	
	//// END Formula.. 
	List<Person> people=createPeople();
	//Example Get in Uppercase a List of All Female older than 18
	//The Old Way
	
	List<String> names=new ArrayList<>();
	for(Person person: people){
		if((person.getAge() >18) && (person.getGender()==Gender.FEMALE)){
			names.add(person.getName().toUpperCase());
		}
	}
	List<String>names2=people.stream().filter(person->person.getAge()>18)
			.map(Person::getName)
			.map(String::toUpperCase)
			.collect(toList());
			
	System.out.println(names);
	
	//Print All Males
	
	List<String> names3=people.stream().filter(person->person.getGender()==Gender.MALE)
			.map(Person::getName)
			.map(String::toUpperCase)
			.collect(toList());
	
	System.out.println(names3);
	
	//Apply Foreach Directly
	
	people.stream().filter(person-> person.getGender()==Gender.MALE)
	.map(person-> new Person(person.getName().toUpperCase(),person.getGender(),person.getAge()))
	.forEach(System.out::println);
	// Get Person Age
	
	people.stream().map(Person::getAge)
	.forEach(System.out::println);
	
	//Get Total Age of aLL PEOPLE
	
	System.out.println(people.stream().mapToInt(Person::getAge)
	.sum()
	
	
	);
	
}
}
