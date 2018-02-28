package java8updates;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressions {

	public static void sort() {
		
		List<String> names=Arrays.asList("Zeeshan","Naveed","Arsalan","Mustafa");
		
		Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
			
		});
		
		//Lambda Syntax
		
		Collections.sort(names,(a,b)->a.compareTo(b));
	}
}
