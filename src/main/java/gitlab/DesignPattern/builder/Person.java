package gitlab.DesignPattern.builder;

public class Person {

    private final String name;

    private final String gender;

    private final int age;

    private Person(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public static final class Builder {

        private String name;

        private String gender;

        private int age;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

        public static void main(String[] args){
            Person woman = new Person.Builder().setAge(30).setGender("woman").setName("Natalie").build();
        }
    }

}
