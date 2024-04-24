package Default;

public class Main {
    public static void main(String[] args) {
        CoffeeView view = new CoffeeView();
        CoffeeOrder model = new CoffeeOrder();
        CoffeeController controller = new CoffeeController(model, view);
    }
}