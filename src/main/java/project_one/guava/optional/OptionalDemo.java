package project_one.guava.optional;


import com.google.common.base.Optional;

public class OptionalDemo {


    public static void main(String[] args){

        String name = "Janice";
        String nullName=null;

        Integer value=Integer.valueOf(1);
        Integer nullValue = null;

        Optional<String> op1 = Optional.of(name);
//        Optional<String> op2 = Optional.of(nullName);


        Optional<String> op3 = Optional.absent();

        Optional<String> op4 = Optional.fromNullable(name);
        Optional<String> op5 = Optional.fromNullable(nullName);
    }
}
