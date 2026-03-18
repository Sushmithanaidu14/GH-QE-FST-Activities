// Functional Interface
interface FuncInterfaceExample {
    String display(String str);
}

public class DemoFuncInterface{
    public static void main(String[] args) {

        FuncInterfaceExample obj = (String str) -> {
            return str;
        };       
        System.out.println(obj.display("Hello"));
    }
}
