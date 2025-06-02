# ব্যাংক সম্পর্কিত OOP উদাহরণ (Bank Related OOP Example)

এই প্রোজেক্টে Java দিয়ে ব্যাংকিং সিস্টেমের একটি মৌলিক উদাহরণ তৈরি করা হয়েছে, যা Object-Oriented Programming (OOP) এর মূল ধারণাগুলো ব্যাখ্যা করে।  
এখানে ব্যবহৃত OOP কনসেপ্টগুলো:  
**Abstraction, Encapsulation, Inheritance, Polymorphism, Interface**

---

## ক্লাস ও ইন্টারফেস সমূহ

### ১. `BankAccount` (Abstract Class)
- ব্যাংক একাউন্টের বেসিক কাঠামো নির্ধারণ করে।  
- `accountNumber` ও `balance` প্রাইভেট ফিল্ড (Encapsulation)।  
- ডিপোজিট ও উইথড্র মেথড।  
- অ্যাবস্ট্রাক্ট মেথড: `accountType()` ও `showDetails()`।

### ২. `SavingsAccount` (Subclass)
- `BankAccount` থেকে ইনহেরিট করেছে।  
- ইন্টারেস্ট রেট যুক্ত।  
- `addInterest()` মেথড।  

### ৩. `CurrentAccount` (Subclass)
- `BankAccount` থেকে ইনহেরিট করেছে।  
- ওভারড্রাফট লিমিট।  
- উইথড্র মেথড ওভাররাইড।  

### ৪. `Maintainable` (Interface)
- ব্যাংক কর্মীদের অডিট রিপোর্ট তৈরির নিয়ম।

### ৫. `BankEmployee` (Class)
- `Maintainable` ইমপ্লিমেন্ট করে।

---

## OOP কনসেপ্ট ব্যাখ্যা

| কনসেপ্ট       | ব্যাখ্যা                                                         |
|---------------|-----------------------------------------------------------------|
| **Abstraction** | `BankAccount` ক্লাসটি অ্যাবস্ট্রাক্ট, বিস্তারিত সাবক্লাসে ইমপ্লিমেন্ট। |
| **Encapsulation** | প্রাইভেট ফিল্ড এবং গেটার/সেটার মেথড।                           |
| **Inheritance** | `SavingsAccount` ও `CurrentAccount` ইনহেরিট করে।                 |
| **Polymorphism** | `withdraw()` মেথড ওভাররাইড করা হয়েছে।                          |
| **Interface**    | `Maintainable` ইন্টারফেস ব্যবহার।                               |

---

## রান করার উদাহরণ

```java
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
        current.withdraw(3500);

        System.out.println();

        Maintainable emp = new BankEmployee("রহিম");
        emp.auditReport();
    }
}



OUTPUT:
🏦 এটি একটি সেভিংস একাউন্ট
🔢 একাউন্ট নম্বর: SA123
💰 ব্যালেন্স: 5000.0 টাকা
📈 ইন্টারেস্ট রেট: 5.0%
💹 ইন্টারেস্ট যোগ করা হলো: 250.0 টাকা

🏦 এটি একটি কারেন্ট একাউন্ট
🔢 একাউন্ট নম্বর: CA456
💰 ব্যালেন্স: 3000.0 টাকা
📉 ওভারড্রাফট সীমা: 1000.0 টাকা
❌ পর্যাপ্ত ব্যালেন্স নেই!

📋 কর্মী রহিম এর দ্বারা মাসিক অডিট রিপোর্ট প্রস্তুত।

## Output Link:
https://www.programiz.com/online-compiler/1EGS9ZR9Sk5Y4


