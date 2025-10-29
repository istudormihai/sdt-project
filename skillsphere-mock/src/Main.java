import entities.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     SKILLSPHERE - Design Patterns Demonstration        ║");
        System.out.println("║            Proof of Concept Implementation             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        demonstrateSellerCreatesService();

        System.out.println("\n" + "=".repeat(60) + "\n");

        demonstrateBuyerPurchasesService();

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║           Demo Complete - All Patterns Used            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    private static void demonstrateSellerCreatesService() {
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  USE CASE 1: SELLER CREATES SERVICE LISTING            │");
        System.out.println("└────────────────────────────────────────────────────────┘\n");

        System.out.println("FACTORY METHOD PATTERN: Creating Seller Account");
        System.out.println("─".repeat(60));

        User seller = UserFactory.createUser(
                "Seller",
                "john.designer@email.com",
                "john_designs",
                "securepass123"
        );

        System.out.println("Created: " + seller);
        seller.login();

        Seller sellerAccount = (Seller) seller;
        sellerAccount.addSkill("Logo Design");
        sellerAccount.addSkill("Brand Identity");
        sellerAccount.addPortfolioItem("Nike Redesign Concept");

        System.out.println("\n🔨 BUILDER PATTERN: Creating Complex Service Object");
        System.out.println("─".repeat(60));


        Service service = sellerAccount.createService()
                .setTitle("Professional Logo Design")
                .setDescription("I will create a stunning, unique logo for your brand with unlimited revisions")
                .setCategory("Graphic Design")
                .setBasePrice(50.0)
                .setDeliveryTime(7)
                .build();

        sellerAccount.addService(service);
        service.activate();

        System.out.println("\n" + service.getDetails());
        System.out.println("\n✓ Service created successfully using Builder Pattern!");
        System.out.println("✓ Seller now has " + sellerAccount.getServices().size() + " active service(s)");
    }

    private static void demonstrateBuyerPurchasesService() {
        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.println("│  USE CASE 2: BUYER PURCHASES SERVICE                   │");
        System.out.println("└────────────────────────────────────────────────────────┘\n");

        System.out.println("FACTORY METHOD PATTERN: Creating Buyer & Seller Accounts");
        System.out.println("─".repeat(60));

        User buyer = UserFactory.createUser(
                "Buyer",
                "sarah.business@email.com",
                "sarah_startup",
                "buyerpass456"
        );

        User seller = UserFactory.createUser(
                "Seller",
                "john.designer@email.com",
                "john_designs",
                "sellerpass789"
        );

        System.out.println("✓ Created: " + buyer);
        System.out.println("✓ Created: " + seller);

        buyer.login();

        Buyer buyerAccount = (Buyer) buyer;
        Seller sellerAccount = (Seller) seller;

        System.out.println("\nCreating service for purchase...");
        Service service = sellerAccount.createService()
                .setTitle("Premium Logo Design")
                .setDescription("Professional logo design service")
                .setCategory("Graphic Design")
                .setBasePrice(50.0)
                .setDeliveryTime(7)
                .build();

        sellerAccount.addService(service);
        service.activate();

        System.out.println("\nDECORATOR PATTERN: Building Custom Service Tier");
        System.out.println("─".repeat(60));

        ServiceOffering baseOffering = new BaseServiceOffering(service, "Basic");
        System.out.println("\n1️⃣ Bronze Tier Offering:");
        System.out.println("   Price: $" + baseOffering.getPrice());
        System.out.println("   Delivery: " + baseOffering.getDeliveryTime() + " days");
        System.out.println("   Features: " + baseOffering.getFeatures());

        ServiceOffering silverOffering = new SilverTierDecorator(baseOffering);
        System.out.println("\n2️⃣ After adding Silver Tier:");
        System.out.println("   Price: $" + silverOffering.getPrice());
        System.out.println("   Delivery: " + silverOffering.getDeliveryTime() + " days");
        System.out.println("   Features: " + silverOffering.getFeatures());

        ServiceOffering goldOffering = new GoldTierDecorator(silverOffering);
        System.out.println("\n3️⃣ After adding Gold Tier:");
        System.out.println("   Price: $" + goldOffering.getPrice());
        System.out.println("   Delivery: " + goldOffering.getDeliveryTime() + " days");
        System.out.println("   Features: " + goldOffering.getFeatures());

        ServiceOffering diamondOffering = new DiamondTierDecorator(goldOffering);
        System.out.println("\n4️⃣ After adding Diamond Tier (Final):");
        System.out.println("   Price: $" + diamondOffering.getPrice());
        System.out.println("   Delivery: " + diamondOffering.getDeliveryTime() + " days");
        System.out.println("   Features: " + diamondOffering.getFeatures());

        System.out.println("\n✓ Custom tier created by composing decorators dynamically!");

        System.out.println("\nCreating Order...");
        Order order = buyerAccount.purchaseService(service, diamondOffering, sellerAccount);
        System.out.println("✓ " + order);

        System.out.println("\nPROXY PATTERN: Processing Payment with Security");
        System.out.println("─".repeat(60));

        PaymentDetails paymentDetails = new PaymentDetails(
                "4532123456789012",
                "Sarah Johnson",
                "12/25",
                "123",
                "123 Business St, New York, NY 10001"
        );

        System.out.println("Payment Details: " + paymentDetails);

        PaymentGatewayProxy paymentProxy = new PaymentGatewayProxy();
        PaymentResult result = paymentProxy.processPayment(order, paymentDetails);

        System.out.println("\n💳 Payment Result: " + result);

        if (result.isSuccess()) {
            order.updateStatus(OrderStatus.IN_PROGRESS);
            System.out.println("\n✓ Payment successful! Order is now in progress.");

            System.out.println("\n⏳ Service being delivered...");
            order.updateStatus(OrderStatus.COMPLETED);

            System.out.println("\n⭐ Buyer leaving review...");
            Review review = buyerAccount.leaveReview(order, 5);
            System.out.println("✓ " + review.getReviewSummary());

            sellerAccount.updateRating(5);
            System.out.println("✓ Seller rating updated: " + sellerAccount);

        } else {
            System.out.println("\nPayment failed: " + result.getMessage());
        }

        paymentProxy.printTransactionLog();

        System.out.println("   • Factory Method: Created users");
        System.out.println("   • Builder: Constructed service");
        System.out.println("   • Decorator: Composed custom tier");
        System.out.println("   • Proxy: Secured payment processing");
    }
}