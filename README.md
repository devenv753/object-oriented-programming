# object-oriented-programming
// Abstraction: একটি abstract class বানানো হয়েছে, যেটা সব BankAccount-এর ভিত্তি নির্ধারণ করে
abstract class BankAccount {
    // Encapsulation: accountNumber এবং balance ফিল্ডগুলো private
    private String accountNumber;
    private double balance;

    // Constructor: নতুন একাউন্ট তৈরি হলে প্রাথমিক তথ্য সেট করা হয়
    public BankAccount(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        deposit(balance); // শুরুতে কিছু টাকা জমা রাখা যায়
    }

    // Getter
    public String getAccountNumber() {
        return accountNumber;
    }

    // Setter
    public void setAccountNumber(String accountNumber) {
        if (!accountNumber.isEmpty()) {
            this.accountNumber = accountNumber;
        }
    }

    public double getBalance() {
        return balance;
    }

    // Deposit Method (Encapsulated)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Withdraw Method (Encapsulated)
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("❌ পর্যাপ্ত ব্যালেন্স নেই!");
        }
    }

    // Abstract Methods
    public abstract void accountType();
    public abstract void showDetails();
}

// Inheritance: SavingsAccount ক্লাসটি BankAccount থেকে তৈরি হয়েছে
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accNumber, double balance, double interestRate) {
        super(accNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void accountType() {
        System.out.println("🏦 এটি একটি সেভিংস একাউন্ট");
    }

    @Override
    public void showDetails() {
        System.out.println("🔢 একাউন্ট নম্বর: " + getAccountNumber());
        System.out.println("💰 ব্যালেন্স: " + getBalance() + " টাকা");
        System.out.println("📈 ইন্টারেস্ট রেট: " + interestRate + "%");
    }

    public void addInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("💹 ইন্টারেস্ট যোগ করা হলো: " + interest + " টাকা");
    }
}

// Inheritance: CurrentAccount ক্লাসও BankAccount থেকে তৈরি হয়েছে
class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    public CurrentAccount(String accNumber, double balance, double overdraftLimit) {
        super(accNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void accountType() {
        System.out.println("🏦 এটি একটি কারেন্ট একাউন্ট");
    }

    @Override
    public void showDetails() {
        System.out.println("🔢 একাউন্ট নম্বর: " + getAccountNumber());
        System.out.println("💰 ব্যালেন্স: " + getBalance() + " টাকা");
        System.out.println("📉 ওভারড্রাফট সীমা: " + overdraftLimit + " টাকা");
    }

    // Method Override করে withdraw-এর নতুন নিয়ম যুক্ত করা হলো
    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            super.withdraw(amount);
        } else {
            System.out.println("❌ ওভারড্রাফট সীমা অতিক্রম করা যাচ্ছে না!");
        }
    }
}

// Interface: একটি রেগুলেটরি ইন্টারফেস যা Maintainable ক্লাসগুলো অনুসরণ করবে
interface Maintainable {
    void auditReport();
}

// BankEmployee: ইন্টারফেস ইমপ্লিমেন্ট করা হলো
class BankEmployee implements Maintainable {
    private String name;

    public BankEmployee(String name) {
        this.name = name;
    }

    @Override
    public void auditReport() {
        System.out.println("📋 কর্মী " + name + " এর দ্বারা মাসিক অডিট রিপোর্ট প্রস্তুত।");
    }
}

// Main ক্লাস: প্রোগ্রাম চালানোর entry point
public class Main {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount("SA123", 5000, 5);
        BankAccount current = new CurrentAccount("CA456", 3000, 1000);

        savings.accountType();
        savings.showDetails();
        ((SavingsAccount) savings).addInterest();

        System.out.println();

        current.accountType();
        current.showDetails();
        current.withdraw(3500); // ওভারড্রাফট পর্যন্ত উঠানো যাবে

        System.out.println();

        // Interface ব্যবহার
        Maintainable emp = new BankEmployee("রহিম");
        emp.auditReport();
    }
}

