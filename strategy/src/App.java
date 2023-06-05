// The strategy interface declares operations common to all
// supported versions of some algorithm. The context uses this
// interface to call the algorithm defined by the concrete
// strategies.
interface Strategy{
    int execute(int a, int b);
}
// Concrete strategies implement the algorithm while following
// the base strategy interface. The interface makes them
// interchangeable in the context.

class ConcreteStrategyAdd implements Strategy{
    public int execute(int a, int b){
        return a+b;
    }
}
class ConcreteStrategySubtract implements Strategy{
    public int execute(int a, int b){
        return a-b;
    }
}
class ConcreteStrategyMultiply implements Strategy{
    public int execute(int a, int b){
        return a*b;
    }
}
// The context defines the interface of interest to clients.
class Context{
     // The context maintains a reference to one of the strategy
    // objects. The context doesn't know the concrete class of a
    // strategy. It should work with all strategies via the
    // strategy interface.

    private Strategy strategy;

    // Usually the context accepts a strategy through the
    // constructor, and also provides a setter so that the
    // strategy can be switched at runtime.
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    // The context delegates some work to the strategy object
    // instead of implementing multiple versions of the
    // algorithm on its own.
    public int executeStrategy(int a, int b) {
        System.out.println("context executed");
        return strategy.execute(a, b);
    }

}

// The client code picks a concrete strategy and passes it to
// the context. The client should be aware of the differences
// between strategies in order to make the right choice.

public class App {
    public static void main(String[] args) throws Exception {
        Context context = new Context();
        String action = "addition";
        int a= 5;
        int b = 10;

        if (action == "addition")
            context.setStrategy(new ConcreteStrategyAdd());

        if (action == "subtraction") 
            context.setStrategy(new ConcreteStrategySubtract());

        if (action == "multiplication")
            context.setStrategy(new ConcreteStrategyMultiply());

        int result = context.executeStrategy(5 , 10);

        System.out.println(result);

    }
}
