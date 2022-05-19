package hello;

import org.joda.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {
      LocalTime currentTime = new LocalTime();
		  System.out.println("La hora es: " + currentTime " en UTC+0");

        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}
