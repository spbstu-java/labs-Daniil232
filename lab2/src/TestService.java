package lab2;

public class TestService {

    @RepeatableExecute(count = 2)
    public void publicMethod() {
        System.out.println("Public method executed");
    }

    @RepeatableExecute(count = 2)
    public void publicMethodWithParams(String userName, int userAge) {
        System.out.println("Public method - Username: " + userName + ", Age: " + userAge);
    }

    @RepeatableExecute(count = 3)
    private void privateMethod() {
        System.out.println("Private method executed");
    }

    @RepeatableExecute(count = 3)
    private void privateMethodWithParams(String data, int count) {
        System.out.println("Private method - Data: " + data + ", Count: " + count);
    }

    @RepeatableExecute(count = 4)
    protected void protectedMethod() {
        System.out.println("Protected method executed");
    }

    @RepeatableExecute(count = 4)
    protected void protectedMethodWithParams(String input, double value) {
        System.out.println("Protected method - Input: " + input + ", Value: " + value);
    }
}