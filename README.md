# SDT Project: SkillSphere - Freelance Service Marketplace

## Team Members
* *Istudor Victor-Mihai - 1241EA*
* *Țigăeru Septimiu-Mihai - 1241EA*

---

## Project Description

**SkillSphere** is an online marketplace designed to connect freelance professionals (Sellers) with customers (Buyers) seeking specialized services. This platform will facilitate the entire lifecycle of a freelance transaction, from a seller creating a service listing to a buyer purchasing, customizing, and reviewing the service. Our goal is to create a robust, scalable, and user-friendly environment for the gig economy.

### Core Functionalities:
* **User Management:** A secure registration and authentication system with distinct roles for **Buyers** and **Sellers**. Sellers will have extended profiles featuring portfolios, skill descriptions, and ratings.
* **Service ("Skill") Management:** Sellers can create, read, update, and delete their service listings. These listings will be complex, including descriptive text, image galleries, multiple pricing tiers, and optional add-ons for customization.
* **Order and Payment System:** Buyers can browse and purchase services, add optional extras to their orders, and complete transactions through a secure, integrated payment gateway. The system will manage the state of each order from "active" to "completed" or "cancelled."
* **Review and Rating System:** Upon order completion, buyers will be able to leave public feedback and a star rating for the service they received, building a reputation system for sellers.

---

## Design Patterns

### 1. Factory Method Pattern 
* **Justification:** The platform needs to instantiate different types of `User` objects, primarily `Buyer` and `Seller`. While these users share some common data like an email and password, their behaviors and associated data are quite different. A **Factory Method** will be used to manage the creation of these distinct user types.
* **Problem Solved:** This pattern solves the problem of decoupling the user registration logic from the concrete user classes. A `UserFactory` will have a method like `createUser(userType)` which returns the appropriate user object.
* **Advantage Over Simpler Alternatives:** A simpler alternative would be to use a large `if/else` statement within the registration service. The Factory Method is superior because it adheres to the Open/Closed Principle; if we later add a new user role like `EnterpriseClient`, we only need to create a new class and update the factory, without modifying the existing, stable registration code.

### 2. Builder Pattern 
* **Justification:** A seller's service listing (a `Service` object) is a complex entity with many attributes: a required title and category, but also optional pricing tiers, delivery times, a gallery of images, FAQs, and a list of extra add-ons. The **Builder** pattern is perfectly suited for the step-by-step construction of such a complex object.
* **Problem Solved:** This pattern solves the problem of having a constructor with an unmanageable number of parameters (a "telescoping constructor"), many of which could be optional. The `ServiceBuilder` will provide a fluent API for constructing the `Service` object.
* **Advantage Over Simpler Alternatives:** Compared to creating an object and then calling multiple setter methods, the Builder ensures the `Service` object is created in a single, atomic operation and can enforce that it is in a valid state before being returned. This makes the object immutable upon creation and the client code far more readable.

### 3. Decorator Pattern
* **Justification:** A seller's service is often offered in different tiers (e.g., **Basic, Standard, Premium**), where each subsequent tier adds more features and value. The **Decorator** pattern is perfect for building these tiers compositionally.
* **Problem Solved:** This pattern allows you to start with a base `ServiceOffering` object (the Basic tier) and then wrap it with decorators to create the higher tiers. For example, a `StandardTierDecorator` could wrap the base service to add "High-Resolution Files," and a `PremiumTierDecorator` could then wrap that to add "Priority Support." The final price and feature list are aggregated from the entire chain of decorators.
* **Advantage Over Simpler Alternatives:** The alternative would be to create separate, monolithic classes for each tier (`BasicPlan`, `StandardPlan`, etc.). This approach is rigid; if you wanted to offer a custom plan with features from different tiers, it would be difficult. The Decorator pattern is far more flexible, allowing features to be combined dynamically, which makes it easy for sellers to create custom or varied service packages.

### 4. Proxy Pattern
* **Justification:** The **payment system** is a critical and sensitive part of the platform that interacts with an external payment microservice. The **Proxy** pattern can serve as a secure and intelligent intermediary for all payment transactions.
* **Problem Solved:** Instead of the application talking directly to the `RealPaymentGateway`, it would interact with a `PaymentGatewayProxy`. This proxy would handle several important tasks before forwarding the request to the real gateway:
    1.  **Validation:** Check if the order and payment details are valid.
    2.  **Logging:** Securely log all payment attempts for auditing.
    3.  **Caching:** Prevent accidental duplicate payment submissions by caching recent transaction requests.
* **Advantage Over Simpler Alternatives:** Placing validation and logging logic directly in the client code that initiates a payment leads to code duplication and mixes business logic with security concerns. The Proxy pattern provides a clean separation of concerns, centralizing all the pre-processing and security checks in one place, which makes the payment process more robust and easier to maintain.
