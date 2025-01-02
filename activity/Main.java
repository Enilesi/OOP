import java.util.ArrayList;
import java.util.List;



class Product {
    private String color;
    private double weight;
    private double price;
    private String description;
    private String producer;

    public Product(String color, double weight, double price, String description, String producer) {
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.description = description;
        this.producer = producer;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getProducer() {
        return producer;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Product &&
                color.equals(((Product)o).color) &&
                weight == ((Product)o).weight &&
                price == ((Product)o).price && 
                description.equals(((Product)o).description) && 
                producer.equals(((Product)o).producer);
    }

    @Override
    public String toString() {
        return "Product{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}

abstract class Spec {
    public abstract String getDescription();
    public abstract boolean isSatisfiedBy(Product product);
}

class ColorSpec extends Spec {
    private String color;

    public ColorSpec(String color) {
        this.color = color;
    }

    @Override
    public String getDescription() {
        return "color '" + color + "'";
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getColor().equals(color);
    }
}

class WeightSpec extends Spec {
    private double weight;

    public WeightSpec(double weight) {
        this.weight = weight;
    }

    @Override
    public String getDescription() {
        return "weight " + weight;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getWeight() == weight;
    }
}

class PriceSpec extends Spec {
    private double price;

    public PriceSpec(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "Price " + price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() == price;
    }
}
class BelowPriceSpec extends Spec {
    private double price;

    public BelowPriceSpec(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "below price " + price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() < price;
    }
}

class OverPriceSpec extends Spec {
    private double price;

    public OverPriceSpec(double price) {
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "over price " + price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() > price;
    }
}

class ProducerSpec extends Spec {
    private String producer;

    public ProducerSpec(String producer) {
        this.producer = producer;
    }

    @Override
    public String getDescription() {
        return "producer '" + producer + "'";
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getProducer().equals(producer);
    }
}

class DescriptionSpec extends Spec {
    private String description;

    public DescriptionSpec(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return "description '" + description + "'";
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getDescription().equals(description);
    }
}

class DescriptionContainsSpec extends Spec {
    private String word;

    public DescriptionContainsSpec(String word) {
        this.word = word;
    }

    @Override
    public String getDescription() {
        return "description containing '" + word + "'";
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getDescription().contains(word);
    }
}

class BelowWeightSpec extends Spec {
    private double weight;

    public BelowWeightSpec(double weight) {
            this.weight = weight;
    }

    @Override
    public String getDescription() {
        return "below weight " + weight;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getWeight() < weight;
    }
}

class OverWeightSpec extends Spec {
    private double weight;

    public OverWeightSpec(double weight) {
        this.weight = weight;
    }

    @Override
    public String getDescription() {
        return "over weight " + weight;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getWeight() > weight;
    }
}

class AndSpec implements Spec {
    private Spec first;
    private Spec second;

    public AndSpec(Spec first, Spec second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return first.isSatisfiedBy(product) && second.isSatisfiedBy(product);
    }
}



class Warehouse {
    private static Warehouse instance;
    private List<Product> products;

    private Warehouse() {
        products = new ArrayList<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void addProduct(Product product) {
        if (products.contains(product)) {
            System.out.println("Product already in the warehouse.");
        } else {
            products.add(product);
        }
    }

    public void printProducts() {
        if(products.isEmpty()){
            System.out.println("No products are stored in the warehouse");
        }else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public List<Product> selectByMultiple(Spec... specs) {
        List<Product> found = new ArrayList<>();
        for (Product product : products) {
            boolean satisfiesAll = true;
            for (Spec spec : specs) {
                if (!spec.isSatisfiedBy(product)) {
                    satisfiesAll = false;
                    break;
                }
            }
            if (satisfiesAll) {
                found.add(product);
            }
        }
        return found;
    }
}


class ProductHandler {

    public void printSelectedProducts(Warehouse warehouse, Spec... specs) {
        StringBuilder message = new StringBuilder("\nProducts filtered by ");

        for (int i = 0; i < specs.length; i++) {
            if (i > 0) {
                message.append(" and ");
            }
            message.append(specs[i].getDescription());
        }

        System.out.println(message);
        List<Product> selectedProducts = warehouse.selectByMultiple(specs);
        if (selectedProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : selectedProducts) {
                System.out.println(product);
            }
        }
    }
}



public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = Warehouse.getInstance();
        ProductHandler productHandler = new ProductHandler();

        warehouse.addProduct(new Product("Red", 2.5, 10.0, "Red ball", "ABC"));
        warehouse.addProduct(new Product("Green", 1.5, 8.0, "Green ball", "DEF"));
        warehouse.addProduct(new Product("Blue", 3.0, 15.0, "Blue toy", "GHI"));

        productHandler.printSelectedProducts(warehouse, new PriceSpec(10));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(10));
        productHandler.printSelectedProducts(warehouse, new BelowPriceSpec(10));
        productHandler.printSelectedProducts(warehouse, new ColorSpec("Red"));
        productHandler.printSelectedProducts(warehouse, new WeightSpec(2.5));
        productHandler.printSelectedProducts(warehouse, new DescriptionSpec("Red ball"));
        productHandler.printSelectedProducts(warehouse, new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new BelowWeightSpec(2.5));
        productHandler.printSelectedProducts(warehouse, new OverWeightSpec(2.5));
        productHandler.printSelectedProducts(warehouse, new DescriptionContainsSpec("toy"));

        productHandler.printSelectedProducts(warehouse, new PriceSpec(10), new ColorSpec("Red"));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(10), new DescriptionSpec("Blue toy"));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(10), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new BelowPriceSpec(10), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new ColorSpec("Red"), new WeightSpec(2.5));

        productHandler.printSelectedProducts(warehouse, new PriceSpec(10), new ColorSpec("Red"), new WeightSpec(2.5));
        productHandler.printSelectedProducts(warehouse, new PriceSpec(10), new ColorSpec("Red"), new DescriptionSpec("Red ball"));
        productHandler.printSelectedProducts(warehouse, new BelowPriceSpec(15), new ColorSpec("Red"), new DescriptionSpec("Red ball"));
        productHandler.printSelectedProducts(warehouse, new BelowPriceSpec(15), new ColorSpec("Red"), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(9), new ColorSpec("Red"), new WeightSpec(2.5), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(9), new WeightSpec(2.5), new DescriptionSpec("Red ball"), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new OverPriceSpec(9), new ColorSpec("Red"), new WeightSpec(2.5), new DescriptionSpec("Red ball"), new ProducerSpec("ABC"));
        productHandler.printSelectedProducts(warehouse, new BelowPriceSpec(100), new ColorSpec("Red"), new WeightSpec(2.5), new DescriptionSpec("Red ball"), new ProducerSpec("ABC"));

    }
}
