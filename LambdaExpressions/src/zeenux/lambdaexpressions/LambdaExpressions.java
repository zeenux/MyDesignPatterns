package zeenux.lambdaexpressions;
import java.util.*;
public class LambdaExpressions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			IterateExample();
	}
	
	
	public static void IterateExample() {
		List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7);
		
		for(int e:numbers) {
			//System.out.println(e);
		}
		
		numbers.forEach(n->System.out.println(n*20));
		// Another Way by using Method Reference
		
		numbers.forEach(System.out::println);
		System.out.println(
				numbers.stream()
				.map(e->e*2)
				);
	}
	
	static int Num(int n) {
		return n++;
	}

}
