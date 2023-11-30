package com.techelevator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Step5_IsVipTests {
    private final String PACKAGE_NAME = "com.techelevator";
    private final String BANK_CUSTOMER_CLASS_NAME = "BankCustomer";
    private final String CREDIT_CARD_ACCOUNT_CLASS_NAME = "CreditCardAccount";
    private final String ACCOUNTABLE_INTERFACE_NAME = "Accountable";
    private final String METHOD_NAME = "isVip";

    private static Class<?> bankCustomerClass;
    private static Class<?> creditCardAccountClass;
    private static Class<?> accountableInterfaceClass;

    private final int VIP_AMOUNT = 25000;

    @Before
    public void Initialize() {
        try {
            bankCustomerClass = Class.forName(PACKAGE_NAME + "." + BANK_CUSTOMER_CLASS_NAME);
            creditCardAccountClass = Class.forName(PACKAGE_NAME + "." + CREDIT_CARD_ACCOUNT_CLASS_NAME);
            accountableInterfaceClass = Class.forName(PACKAGE_NAME + "." + ACCOUNTABLE_INTERFACE_NAME);
        } catch (ClassNotFoundException e) {
            fail(e.getMessage() + " class could not be found.");
        }
    }

    @Test
    public void test01_MethodWellFormed() {
        String wellFormedCheck = methodWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(wellFormedCheck);
        }
    }

    @Test
    public void test02_HappyPathTests() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String wellFormedCheck = methodWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(METHOD_NAME + " is not well formed. The test01_MethodWellFormed tests must pass first.\r\n\t" + wellFormedCheck);
        }

        Constructor<?> constructor = SafeReflection.getConstructor(bankCustomerClass);
        Object bankCustomerInstance = constructor.newInstance();
        Method isVip = SafeReflection.getMethod(bankCustomerClass, METHOD_NAME);

        // one bank account, less than VIP amount
        BankAccount bankAccount = new BankAccount("", "", VIP_AMOUNT / 2);
        Object listOfAccountables = createListOfAccountables(bankAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        Object isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for one account with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance(),
                (Boolean)isVipReturnValue);

        // one bank account, greater than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT * 2);
        listOfAccountables = createListOfAccountables(bankAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for one account with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance(),
                (Boolean)isVipReturnValue);


        // two bank accounts, less than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT / 3);
        CheckingAccount checkingAccount = new CheckingAccount("", "",VIP_AMOUNT / 3);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for two accounts with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance()),
                (Boolean)isVipReturnValue);

        // two bank accounts, greater than VIP amount
        bankAccount = new BankAccount("", "", (int)(VIP_AMOUNT / 1.5));
        checkingAccount = new CheckingAccount("", "", (int)(VIP_AMOUNT / 1.5));

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for two accounts with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance()),
                (Boolean)isVipReturnValue);


        // three bank accounts, less than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT / 4);
        checkingAccount = new CheckingAccount("", "", VIP_AMOUNT / 4);
        SavingsAccount savingsAccount = new SavingsAccount("", "", VIP_AMOUNT / 4);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, savingsAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for three accounts with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", SavingsAccount balance: " + savingsAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() + savingsAccount.getBalance()),
                (Boolean)isVipReturnValue);

        // three bank accounts, greater than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT / 2);
        checkingAccount = new CheckingAccount("", "", VIP_AMOUNT / 2);
        savingsAccount = new SavingsAccount("", "", VIP_AMOUNT / 2);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, savingsAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for three accounts with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", SavingsAccount balance: " + savingsAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() + savingsAccount.getBalance()),
                (Boolean)isVipReturnValue);


        // ensure credit card class exists before continuing
        if (creditCardAccountClass == null) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " must exist to continue isVip tests.");
        }
        boolean implementsInterface = Arrays.stream(creditCardAccountClass.getInterfaces())
                .anyMatch(interfaceType -> interfaceType.getSimpleName().equals(ACCOUNTABLE_INTERFACE_NAME));
        if (!implementsInterface) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " must implement Accountable to continue isVip tests.");
        }
        Constructor<?> creditCardConstructor = SafeReflection.getConstructor(creditCardAccountClass, String.class, String.class);

        // one credit card, no debt, less than VIP amount
        Object creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 0);

        listOfAccountables = createListOfAccountables(creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for one credit card account with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "CreditCardAccount balance: 0",
                (Boolean)isVipReturnValue);


        // two accounts (one credit card), less than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 500);
        creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 1000);

        listOfAccountables = createListOfAccountables(bankAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for one bank account and one credit card account with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CreditCardAccount balance: -1000, Total: " + (bankAccount.getBalance() - 1000),
                (Boolean)isVipReturnValue);

        // two accounts (one credit card), greater than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 500);
        creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 100);

        listOfAccountables = createListOfAccountables(bankAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for one bank account and one credit card account with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CreditCardAccount balance: -100, Total: " + (bankAccount.getBalance() - 100),
                (Boolean)isVipReturnValue);


        // three accounts (one credit card), less than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 1000);
        checkingAccount = new CheckingAccount("", "", (int)(VIP_AMOUNT / 1.5));
        creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", VIP_AMOUNT);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for two bank accounts and one credit card account with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", CreditCardAccount balance: " + (VIP_AMOUNT * -1) + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() - VIP_AMOUNT),
                (Boolean)isVipReturnValue);

        // three accounts (one credit card), greater than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 1000);
        checkingAccount = new CheckingAccount("", "", (int)(VIP_AMOUNT / 1.5));
        creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 1500);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for two bank accounts and one credit card account with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", CreditCardAccount balance: -1500, Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() - 1500),
                (Boolean)isVipReturnValue);


        // three accounts (two credit cards), less than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 1000);
        creditCardAccount = creditCardConstructor.newInstance("", "");
        Object creditCardAccount2 = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 500);
        SafeReflection.setFieldValue(creditCardAccount2, "debt", 600);

        listOfAccountables = createListOfAccountables(bankAccount, creditCardAccount, creditCardAccount2);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for one bank accounts and two credit card accounts with total balance under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CreditCardAccount 1 balance: -500, CreditCardAccount 2 balance: -600, Total: " + (bankAccount.getBalance() - 500 - 600),
                (Boolean)isVipReturnValue);

        // three accounts (two credit cards), greater than VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT + 1000);
        creditCardAccount = creditCardConstructor.newInstance("", "");
        creditCardAccount2 = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 100);
        SafeReflection.setFieldValue(creditCardAccount2, "debt", 200);

        listOfAccountables = createListOfAccountables(bankAccount, creditCardAccount, creditCardAccount2);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for one bank accounts and two credit card accounts with total balance over VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CreditCardAccount 1 balance: -100, CreditCardAccount 2 balance: -200, Total: " + (bankAccount.getBalance() - 100 - 200),
                (Boolean)isVipReturnValue);
    }

    @Test
    public void test03_EdgeCaseTests() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String wellFormedCheck = methodWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(METHOD_NAME + " is not well formed. The test01_MethodWellFormed tests must pass first.\r\n\t" + wellFormedCheck);
        }

        Constructor<?> constructor = SafeReflection.getConstructor(bankCustomerClass);
        Object bankCustomerInstance = constructor.newInstance();
        Method isVip = SafeReflection.getMethod(bankCustomerClass, METHOD_NAME);

        // one account with balance one dollar under VIP amount
        BankAccount bankAccount = new BankAccount("", "", VIP_AMOUNT - 1);

        Object listOfAccountables = createListOfAccountables(bankAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        Object isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for one account with total balance one dollar under VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + "", (boolean)isVipReturnValue);

        // one account with balance exactly VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT);

        listOfAccountables = createListOfAccountables(bankAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for one account with total balance exactly the VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + "",
                (Boolean)isVipReturnValue);


        // two accounts with balance one dollar under VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT - 2);
        CheckingAccount checkingAccount = new CheckingAccount("", "", 1);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for two accounts with total balance one dollar under the VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance()),
                (Boolean)isVipReturnValue);

        // two accounts with balance exactly VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT - 1);
        checkingAccount = new CheckingAccount("", "", 1);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for two accounts with total balance exactly the VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", Total: " + (bankAccount.getBalance() + checkingAccount.getBalance()),
                (Boolean)isVipReturnValue);


        // ensure credit card class exists before continuing
        if (creditCardAccountClass == null) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " must exist to continue isVip tests.");
        }
        boolean implementsInterface = Arrays.stream(creditCardAccountClass.getInterfaces())
                .anyMatch(interfaceType -> interfaceType.getSimpleName().equals(ACCOUNTABLE_INTERFACE_NAME));
        if (!implementsInterface) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " must implement Accountable to continue isVip tests.");
        }
        Constructor<?> creditCardConstructor = SafeReflection.getConstructor(creditCardAccountClass, String.class, String.class);

        // three accounts (one credit card) with balance one dollar under VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT - 1);
        checkingAccount = new CheckingAccount("", "", 1);
        Object creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 1);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertFalse(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return false for two bank accounts and one credit card account with total balance one dollar under the VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", CreditCardAccount balance: -1, Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() - 1),
                (Boolean)isVipReturnValue);

        // three accounts (one credit card) with balance exactly VIP amount
        bankAccount = new BankAccount("", "", VIP_AMOUNT - 1);
        checkingAccount = new CheckingAccount("", "", 2);
        creditCardAccount = creditCardConstructor.newInstance("", "");
        SafeReflection.setFieldValue(creditCardAccount, "debt", 1);

        listOfAccountables = createListOfAccountables(bankAccount, checkingAccount, creditCardAccount);
        SafeReflection.setFieldValue(bankCustomerInstance, "accounts", listOfAccountables);
        isVipReturnValue = isVip.invoke(bankCustomerInstance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " method " + METHOD_NAME + " should return true for two bank accounts and one credit card account with total balance exactly the VIP amount of " + VIP_AMOUNT + ". " +
                "BankAccount balance: " + bankAccount.getBalance() + ", CheckingAccount balance: " + checkingAccount.getBalance() + ", CreditCardAccount balance: -1, Total: " + (bankAccount.getBalance() + checkingAccount.getBalance() - 1),
                (Boolean)isVipReturnValue);
    }

    private String methodWellFormedCheck() {
        // Assert class exists
        if (bankCustomerClass == null) {
            return BANK_CUSTOMER_CLASS_NAME + " class not found. Have you declared it yet? Make sure the class name is '" + BANK_CUSTOMER_CLASS_NAME + "' and the package name is '" + PACKAGE_NAME + "'.";
        }

        Method isVip = SafeReflection.getMethod(bankCustomerClass, METHOD_NAME);

        // Assert method exists
        if (isVip == null) {
            return METHOD_NAME + " not found in " + BANK_CUSTOMER_CLASS_NAME + ". Have you declared it yet? Make sure the method name is '" + METHOD_NAME + "', and that it is public.";
        }

        // Assert parameters and return type
        String methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, METHOD_NAME, boolean.class, true, new Class[] {});
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        return "";
    }

    private Object createListOfAccountables(Object... accounts) {

        return getGenericList(bankCustomerClass, accounts);
    }

    private <Type> List<Type> getGenericList(Class<Type> type, Object... params) {
        List<Type> l = new ArrayList<Type>();
        for (Object param : params) {
            l.add((Type)param);
        }
        return l;
    }
}
