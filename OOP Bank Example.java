// Abstraction: BankAccount নামে একটি abstract ক্লাস বানানো হলো
abstract class BankAccount {
    // Encapsulation: accountNumber ও balance ফিল্ডগুলো private করে রাখা হলো
    private String accountNumber;
    private double balance;

    // Default Constructor: একাউন্ট না জানলে ডিফল্ট মান সেট করা হবে
    public BankAccount() {
        this("N/A", 0.0); // constructor chaining
    }

    // Parameterized Constructor: নির্দিষ্ট তথ্য দিয়ে একাউন্ট তৈরি
    public BankAccount(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        deposit(balance); // শুরুতে কিছু টাকা জমা রাখা যায়
    }

    // Getter & Setter
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (!accountNumber.isEmpty()) {
            this.accountNumber = accountNumber;
        }
    }

    public double getBalance() {
        return balance;
    }

    // Deposit Method: টাকার পরিমাণ বাড়ানো হয়
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw Method: টাকা তোলা যায়, কিন্তু ব্যালেন্স চেক করা হয়
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("❌ পর্যাপ্ত ব্যালেন্স নেই!");
        }
    }

    // Abstract Methods: child class গুলো এই method গুলো override করবে
    public abstract void accountType();
    public abstract void showDetails();
}

// Inheritance: SavingsAccount -> BankAccount
class SavingsAccount extends BankAccount {
    private double interestRate;

    // Default Constructor
    public SavingsAccount() {
        this("SA000", 0.0, 0.0);
    }

    // Constructor: সেভিংস একাউন্টে ইন্টারেস্ট থাকে
    public SavingsAccount(String accNumber, double balance, double interestRate) {
        super(accNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void accountType() {
        System.out.println("🏦 এটি একটি সেভিংস একাউন্ট (Savings Account)");
    }

    @Override
    public void showDetails() {
        System.out.println("🔢 একাউন্ট নম্বর: " + getAccountNumber());
        System.out.println("💰 ব্যালেন্স: " + getBalance() + " টাকা");
        System.out.println("📈 ইন্টারেস্ট রেট: " + interestRate + "%");
    }

    // ইন্টারেস্ট হিসাব করে ব্যালেন্সে যোগ করা হয়
    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("💹 ইন্টারেস্ট যোগ করা হলো: " + interest + " টাকা");
    }
}

// Inheritance: CurrentAccount -> BankAccount
class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    // Default Constructor
    public CurrentAccount() {
        this("CA000", 0.0, 0.0);
    }

    // Constructor: কারেন্ট একাউন্টে ওভারড্রাফট সুবিধা থাকে
    public CurrentAccount(String accNumber, double balance, double overdraftLimit) {
        super(accNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void accountType() {
        System.out.println("🏦 এটি একটি কারেন্ট একাউন্ট (Current Account)");
    }

    @Override
    public void showDetails() {
        System.out.println("🔢 একাউন্ট নম্বর: " + getAccountNumber());
        System.out.println("💰 ব্যালেন্স: " + getBalance() + " টাকা");
        System.out.println("📉 ওভারড্রাফট সীমা: " + overdraftLimit + " টাকা");
    }

    // ওভারড্রাফট লিমিট সহ টাকা তোলা যায়
    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            super.withdraw(amount);
        } else {
            System.out.println("❌ ওভারড্রাফট সীমা অতিক্রম করা যাচ্ছে না!");
        }
    }
}

// Interface: ব্যাংকের অডিটের কাজ যারা করে তারা Maintainable ইন্টারফেস অনুসরণ করে
interface Maintainable {
    void auditReport();
}

// BankEmployee: ব্যাংক কর্মী অডিট রিপোর্ট তৈরি করে
class BankEmployee implements Maintainable {
    private String name;

    // Default Constructor
    public BankEmployee() {
        this("অজানা");
    }

    // Constructor: নামসহ কর্মী তৈরি
    public BankEmployee(String name) {
        this.name = name;
    }

    @Override
    public void auditReport() {
        System.out.println("📋 কর্মী " + name + " এর দ্বারা মাসিক অডিট রিপোর্ট প্রস্তুত।");
    }
}

// Main class: প্রোগ্রাম চালানোর জন্য entry point
public class Main {
    public static void main(String[] args) {
        // 🔹 সেভিংস একাউন্ট তৈরি
        SavingsAccount savings = new SavingsAccount("SA123", 5000, 5);
        savings.accountType();
        savings.showDetails();
        savings.addInterest(); // ইন্টারেস্ট যোগ করা হচ্ছে

        System.out.println();

        // 🔹 কারেন্ট একাউন্ট তৈরি
        CurrentAccount current = new CurrentAccount("CA456", 3000, 1000);
        current.accountType();
        current.showDetails();
        current.withdraw(3500); // ওভারড্রাফট ব্যবহার করে টাকা তোলা হচ্ছে

        System.out.println();

        // 🔹 ডিফল্ট সেভিংস একাউন্ট তৈরি (শুধু দেখানোর জন্য)
        SavingsAccount defaultSaving = new SavingsAccount();
        defaultSaving.accountType();
        defaultSaving.showDetails();

        System.out.println();

        // 🔹 ব্যাংক কর্মী অডিট রিপোর্ট তৈরি করছে
        BankEmployee emp = new BankEmployee("রহিম");
        emp.auditReport();

        // 🔹 ডিফল্ট কর্মী
        BankEmployee emp2 = new BankEmployee();
        emp2.auditReport();
    }
}



// ✅ প্রোগ্রামের আউটপুট:
/*
🏦 এটি একটি সেভিংস একাউন্ট (Savings Account)
🔢 একাউন্ট নম্বর: SA123
💰 ব্যালেন্স: 5000.0 টাকা
📈 ইন্টারেস্ট রেট: 5.0%
💹 ইন্টারেস্ট যোগ করা হলো: 250.0 টাকা

🏦 এটি একটি কারেন্ট একাউন্ট (Current Account)
🔢 একাউন্ট নম্বর: CA456
💰 ব্যালেন্স: 3000.0 টাকা
📉 ওভারড্রাফট সীমা: 1000.0 টাকা
❌ পর্যাপ্ত ব্যালেন্স নেই!

🏦 এটি একটি সেভিংস একাউন্ট (Savings Account)
🔢 একাউন্ট নম্বর: SA000
💰 ব্যালেন্স: 0.0 টাকা
📈 ইন্টারেস্ট রেট: 0.0%

📋 কর্মী রহিম এর দ্বারা মাসিক অডিট রিপোর্ট প্রস্তুত।
📋 কর্মী অজানা এর দ্বারা মাসিক অডিট রিপোর্ট প্রস্তুত।
*/



