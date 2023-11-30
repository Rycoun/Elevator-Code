package com.techelevator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Step4_BankCustomerTests {
    private final String PACKAGE_NAME = "com.techelevator";
    private final String BANK_CUSTOMER_CLASS_NAME = "BankCustomer";
    private final String BANK_ACCOUNT_CLASS_NAME = "BankAccount";
    private final String CHECKING_ACCOUNT_CLASS_NAME = "CheckingAccount";
    private final String SAVINGS_ACCOUNT_CLASS_NAME = "SavingsAccount";
    private final String CREDIT_CARD_ACCOUNT_CLASS_NAME = "CreditCardAccount";
    private final String ACCOUNTABLE_INTERFACE_NAME = "Accountable";

    private static Class<?> bankCustomerClass;
    private static Class<?> accountableInterfaceClass;
    private static Class<?> creditCardAccountClass;

    @Before
    public void Initialize() {
        try {
            bankCustomerClass = Class.forName(PACKAGE_NAME + "." + BANK_CUSTOMER_CLASS_NAME);
            accountableInterfaceClass = Class.forName(PACKAGE_NAME + "." + ACCOUNTABLE_INTERFACE_NAME);
            creditCardAccountClass = Class.forName(PACKAGE_NAME + "." + CREDIT_CARD_ACCOUNT_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            fail(e.getMessage() + " class could not be found.");
        }
    }

    @Test
    public void test01_ClassWellFormed() {
        String wellFormedCheck = classWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(wellFormedCheck);
        }
    }

    @Test
    public void test02_HappyPathTests() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String wellFormedCheck = classWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(BANK_CUSTOMER_CLASS_NAME + " is not well formed. The test01_ClassWellFormed tests must pass first.\r\n\t" + wellFormedCheck);
        }

        // Assert getters and setters work correctly
        String testName = "Tester Testerson";
        String testAddress = "123 Main Street";
        String testPhoneNumber = "555-555-1234";

        Constructor<?> constructor = SafeReflection.getConstructor(bankCustomerClass);
        Object instance = constructor.newInstance();

        SafeReflection.setFieldValue(instance, "name", testName);
        Method getName = SafeReflection.getMethod(bankCustomerClass, "getName");
        assertEquals(BANK_CUSTOMER_CLASS_NAME + " does not correctly set the field name.",
                testName, getName.invoke(instance));

        SafeReflection.setFieldValue(instance, "address", testAddress);
        Method getAddress = SafeReflection.getMethod(bankCustomerClass, "getAddress");
        assertEquals(BANK_CUSTOMER_CLASS_NAME + " does not correctly set the field address.",
                testAddress, getAddress.invoke(instance));

        SafeReflection.setFieldValue(instance, "phoneNumber", testPhoneNumber);
        Method getPhoneNumber = SafeReflection.getMethod(bankCustomerClass, "getPhoneNumber");
        assertEquals(BANK_CUSTOMER_CLASS_NAME + " does not correctly set the field phoneNumber.",
                testPhoneNumber, getPhoneNumber.invoke(instance));


        // populate the `accounts` field with a list of accounts to test with
        Field accountsField = SafeReflection.getDeclaredField(bankCustomerClass, "accounts");
        accountsField.setAccessible(true);
        Object listOfAccountables = createListOfAccountables(false);
        SafeReflection.setFieldValue(instance, "accounts", listOfAccountables);

        // Assert GetAccounts() retrieves accounts
        String methodName = "getAccounts";
        Method getAccounts = SafeReflection.getMethod(bankCustomerClass, methodName);
        Object getAccountsReturnValue = getAccounts.invoke(instance);
        // compare to another call of createListOfAccountables() so it's not the same reference
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " getAccounts method fails to return the expected list.",
                areListsEqual(createListOfAccountables(false), getAccountsReturnValue));

        // Assert AddAccount() adds an account
        methodName = "addAccount";
        Method addAccount = SafeReflection.getMethod(bankCustomerClass, methodName, accountableInterfaceClass);
        addAccount.invoke(instance, additionalAccountForTest());
        // compare to another call of createListOfAccountables() so it's not the same reference
        Object accountsFieldValue = accountsField.get(instance);
        assertTrue(BANK_CUSTOMER_CLASS_NAME + " addAccount method fails to add the expected account to the list.",
                areListsEqual(createListOfAccountables(true), accountsFieldValue));
    }

    private String classWellFormedCheck() {
        // Assert class exists
        if (bankCustomerClass == null) {
            return BANK_CUSTOMER_CLASS_NAME + " class not found. Have you declared it yet? Make sure the class name is '" + BANK_CUSTOMER_CLASS_NAME + "' and the package name is '" + PACKAGE_NAME + "'.";
        }

        if (Modifier.isAbstract(bankCustomerClass.getModifiers())) {
            return BANK_CUSTOMER_CLASS_NAME + " class must not be abstract. Remove the 'abstract' modifier on " + BANK_CUSTOMER_CLASS_NAME + ".";
        }

        boolean implementsInterface = bankCustomerClass.getInterfaces().length > 0;
        if (implementsInterface) {
            return BANK_CUSTOMER_CLASS_NAME + " must not implement any interfaces.";
        }

        // Assert fields exist, are of correct type and access
        String fieldCheck = ReflectionTestHelper.checkField(bankCustomerClass, "name", String.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }
        fieldCheck = ReflectionTestHelper.checkField(bankCustomerClass, "address", String.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }
        fieldCheck = ReflectionTestHelper.checkField(bankCustomerClass, "phoneNumber", String.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }
        fieldCheck = ReflectionTestHelper.checkField(bankCustomerClass, "accounts", java.util.List.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }

        // Assert getters and setters for fields
        String methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "getName", String.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "getAddress", String.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "getPhoneNumber", String.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "setName", void.class,true, new Class[] { String.class });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "setAddress", void.class, true, new Class[] { String.class });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "setPhoneNumber", void.class, true, new Class[] { String.class });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        // Assert methods are present -- whether they work is confirmed in other test methods
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "getAccounts", Array.newInstance(accountableInterfaceClass, 0).getClass(), true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(bankCustomerClass, "addAccount", void.class, true, new Class[] { accountableInterfaceClass });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        return "";
    }

    private Object createListOfAccountables(boolean additionalAccountForAddTest) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // test data
        List<Object> bankAccounts = new ArrayList();
        // should be safe to assume BankAccount, CheckingAccount, and SavingsAccount exist and can instantiate directly
        bankAccounts.add(new BankAccount("ABC", "BNK:3713", 100));
        bankAccounts.add(new CheckingAccount("DEF", "CHK:5912", 200));
        bankAccounts.add(new SavingsAccount("GHI", "SAV:9922", 300));

        // if credit card type exists and implements Accountable, add one of those too
        if (creditCardAccountClass != null) {
            boolean implementsInterface = Arrays.stream(creditCardAccountClass.getInterfaces())
                    .anyMatch(interfaceType -> interfaceType.getSimpleName().equals(ACCOUNTABLE_INTERFACE_NAME));
            if (implementsInterface) {
                Constructor<?> twoArgConstructor = creditCardAccountClass.getConstructor(String.class, String.class);
                Object creditCardAccount = twoArgConstructor.newInstance("JKL", "1245-5232-1231-2141");
                SafeReflection.setFieldValue(creditCardAccount, "debt", 400);
                bankAccounts.add(creditCardAccount);
            }
        }

        if (additionalAccountForAddTest)
        {
            bankAccounts.add(additionalAccountForTest());
        }

        return getGenericList(bankCustomerClass, bankAccounts.toArray());
    }

    private <Type> List<Type> getGenericList(Class<Type> type, Object... params) {
        List<Type> l = new ArrayList<Type>();
        for (Object param : params) {
            l.add((Type)param);
        }
        return l;
    }

    private BankAccount additionalAccountForTest()
    {
        return new BankAccount("MNO", "BNK:2440", 500);
    }

    private boolean areListsEqual(Object expected, Object actual) throws InvocationTargetException, IllegalAccessException {
        Object[] expectedArray;
        Object[] actualArray;
        if (expected.getClass().getSimpleName().equals("ArrayList")) {
            expectedArray = ((ArrayList<?>)expected).toArray();
        } else {
            expectedArray = (Object[])expected;
        }
        if (actual.getClass().getSimpleName().equals("ArrayList")) {
            actualArray = ((ArrayList<?>)actual).toArray();
        } else {
            actualArray = (Object[])actual;
        }

        if (expectedArray.length != actualArray.length) {
            return false;
        }

        // Assume order is the same
        for (int i = 0; i < expectedArray.length; i++) {
            // If we're not comparing the same type, then they don't match
            if (!expectedArray[i].getClass().getName().equals(actualArray[i].getClass().getName())) {
                return false;
            }

            switch (expectedArray[i].getClass().getSimpleName()) {
                case BANK_ACCOUNT_CLASS_NAME:
                case CHECKING_ACCOUNT_CLASS_NAME:
                case SAVINGS_ACCOUNT_CLASS_NAME:
                    // Can cast down to base BankAccount
                    if (!doBankAccountsMatch((BankAccount) expectedArray[i], (BankAccount) actualArray[i])) {
                        return false;
                    }
                    break;
                case CREDIT_CARD_ACCOUNT_CLASS_NAME:
                    if (!doCreditCardAccountsMatch(expectedArray[i], actualArray[i])) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return true;
    }

    private boolean doBankAccountsMatch(BankAccount expected, BankAccount actual) {
        return expected.getAccountHolderName().equals(actual.getAccountHolderName())
                && expected.getAccountNumber().equals(actual.getAccountNumber())
                && expected.getBalance() == actual.getBalance();
    }

    private boolean doCreditCardAccountsMatch(Object expected, Object actual) throws InvocationTargetException, IllegalAccessException {
        Object expectedAccountHolderName = SafeReflection.getMethod(expected.getClass(), "getAccountHolderName").invoke(expected);
        Object expectedCardNumber = SafeReflection.getMethod(expected.getClass(), "getCardNumber").invoke(expected);
        Object expectedDebt = SafeReflection.getMethod(expected.getClass(), "getDebt").invoke(expected);

        Object actualAccountHolderName = SafeReflection.getMethod(actual.getClass(), "getAccountHolderName").invoke(actual);
        Object actualCardNumber = SafeReflection.getMethod(actual.getClass(), "getCardNumber").invoke(actual);
        Object actualDebt = SafeReflection.getMethod(actual.getClass(), "getDebt").invoke(actual);

        return expectedAccountHolderName.toString().equals(actualAccountHolderName.toString())
                && expectedCardNumber.toString().equals(actualCardNumber.toString())
                && (int)expectedDebt == (int)actualDebt;
    }

}
