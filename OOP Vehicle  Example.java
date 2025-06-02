// Abstraction: একটি abstract class বানানো হয়েছে, যেটা সব Vehicle-এর ভিত্তি নির্ধারণ করে
abstract class Vehicle {
    // Encapsulation: model এবং capacity ফিল্ডগুলো private, বাইরের ক্লাস থেকে সরাসরি access করা যাবে না
    private String model;
    private int capacity;

    // Constructor: নতুন অবজেক্ট তৈরি হলে প্রাথমিক ভ্যালু সেট করা হয়
    public Vehicle(String model, int capacity) {
        setModel(model);         // Encapsulated setter call
        setCapacity(capacity);   // Encapsulated setter call
    }

    // Getter method: encapsulated ডেটা রিড করার জন্য
    public String getModel() {
        return model;
    }

    // Setter method: encapsulated ডেটা সেট করার জন্য
    public void setModel(String model) {
        if (!model.isEmpty()) {
            this.model = model;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    // Abstract Method: এগুলোর বাস্তবায়ন subclass গুলোতে বাধ্যতামূলকভাবে করতে হবে
    public abstract void startEngine();
    public abstract void showDetails();
}

// Inheritance: Truck ক্লাসটি Vehicle ক্লাসকে extend করছে
class Truck extends Vehicle {
    private double load; // Truck-specific ফিল্ড

    // Constructor: Truck অবজেক্ট তৈরি করার সময় parent constructor ও নিজস্ব ফিল্ড initialize হয়
    public Truck(String model, int capacity, double load) {
        super(model, capacity);  // Parent class constructor call
        this.load = load;
    }

    // Polymorphism: Vehicle class-এর method override করা হয়েছে
    @Override
    public void startEngine() {
        System.out.println("Truck engine started with loud diesel sound!");
    }

    @Override
    public void showDetails() {
        System.out.println("🚛 Truck Model: " + getModel());
        System.out.println("📦 Capacity: " + getCapacity() + " tons");
        System.out.println("📊 Current Load: " + load + " tons");
    }

    // Truck-specific Method: Truck-এ মালামাল লোড করার ফিচার
    public void loadGoods(double weight) {
        if (weight + load <= getCapacity()) {
            load += weight;
            System.out.println("✅ " + weight + " tons loaded. Total Load: " + load + " tons.");
        } else {
            System.out.println("❌ Overload! Cannot load " + weight + " tons.");
        }
    }
}

// Interface: একটি আলাদা abstraction, যেটা multiple class implement করতে পারে
interface Maintainable {
    void performMaintenance(); // Method signature only
}

// Bus class: অন্য ধরনের Vehicle, Maintainable interface implement করছে
class Bus extends Vehicle implements Maintainable {
    private int passengers;

    public Bus(String model, int capacity, int passengers) {
        super(model, capacity);
        this.passengers = passengers;
    }

    @Override
    public void startEngine() {
        System.out.println("Bus engine started smoothly.");
    }

    @Override
    public void showDetails() {
        System.out.println("🚌 Bus Model: " + getModel());
        System.out.println("🧍 Passenger Capacity: " + getCapacity());
        System.out.println("👥 Current Passengers: " + passengers);
    }

    // Interface এর method বাস্তবায়ন করা হলো
    @Override
    public void performMaintenance() {
        System.out.println("Bus maintenance scheduled monthly.");
    }
}

// Main ক্লাস: প্রোগ্রাম চালানোর entry point
public class Main {
    public static void main(String[] args) {
        // Polymorphism: Parent টাইপ Vehicle, কিন্তু আসলে Truck অবজেক্ট
        Vehicle truck = new Truck("Volvo FMX", 20, 5);
        Vehicle bus = new Bus("Hyundai Super", 40, 25);

        truck.startEngine();    // Polymorphic behavior
        truck.showDetails();

        System.out.println();

        bus.startEngine();      // Polymorphic behavior
        bus.showDetails();

        System.out.println();

        // Type casting: Truck-specific method call করার জন্য object cast করা হয়েছে
        if (truck instanceof Truck) {
            Truck t = (Truck) truck;
            t.loadGoods(10);   // লোড হচ্ছে
            t.loadGoods(7);    // অতিরিক্ত হলে মেসেজ দেখাবে
        }

        System.out.println();

        // Interface ব্যবহার করে maintenance মেথড কল করা হচ্ছে
        if (bus instanceof Maintainable) {
            Maintainable m = (Maintainable) bus;
            m.performMaintenance();
        }
    }
}
