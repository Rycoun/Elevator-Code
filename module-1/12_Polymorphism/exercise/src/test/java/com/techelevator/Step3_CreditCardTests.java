package com.techelevator;

import java.lang.reflect.*;
import java.util.Arrays;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Step3_CreditCardTests {
    private final String PACKAGE_NAME = "com.techelevator";
    private final String CREDIT_CARD_ACCOUNT_CLASS_NAME = "CreditCardAccount";
    private final String ACCOUNTABLE_INTERFACE_NAME = "Accountable";

    private static Class<?> creditCardAccountClass;

    @Before
    public void Initialize() {
        try {
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
    public void test02_HappyPathTests() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String wellFormedCheck = classWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " is not well formed. The test01_ClassWellFormed tests must pass first.\r\n\t" + wellFormedCheck);
        }

        // Assert constructors set fields
        String testAccountHolderName = "Tester Testerson";
        String testCardNumber = "1234-5678-9012-3456";

        // Two arg constructor
        Constructor<?> twoArgConstructor = SafeReflection.getConstructor(creditCardAccountClass, String.class, String.class);
        Object instance = twoArgConstructor.newInstance(testAccountHolderName, testCardNumber);

        Method getAccountHolderName = instance.getClass().getMethod("getAccountHolderName");
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " constructor " + CREDIT_CARD_ACCOUNT_CLASS_NAME + "(String, String) does not correctly set the field accountHolderName.",
                testAccountHolderName, getAccountHolderName.invoke(instance));
        Method getCardNumber = instance.getClass().getMethod("getCardNumber");
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " constructor " + CREDIT_CARD_ACCOUNT_CLASS_NAME + "(String, String) does not correctly set the field cardNumber.",
                testCardNumber, getCardNumber.invoke(instance));
        Method getDebt = instance.getClass().getMethod("getDebt");
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " constructor " + CREDIT_CARD_ACCOUNT_CLASS_NAME + "(String, String) does not correctly default debt to 0.",
                0, getDebt.invoke(instance));

        // Assert debt is balance as negative number
        int testDebt = 100;
        SafeReflection.setFieldValue(instance, "debt", testDebt);
        Method getBalance = instance.getClass().getMethod("getBalance");
        Object instanceBalance = getBalance.invoke(instance);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " field balance must equal the debt value as a negative number.",
                testDebt * -1, (int)instanceBalance);

        // Assert pay decreases debt
        Method pay = instance.getClass().getMethod("pay", int.class);
        int payParamValue = 23;
        int payExpectedReturnValue = testDebt - payParamValue;
        Object payActualReturnValue = pay.invoke(instance, payParamValue);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " pay method fails to decrease debt by correct amount. Starting debt: " + testDebt + ", pay: " + payParamValue + ", new debt should be " + payExpectedReturnValue +".",
                payExpectedReturnValue, payActualReturnValue);

        // Assert charge increases debt
        Method charge = instance.getClass().getMethod("charge", int.class);
        int chargeParamValue = 22;
        int chargeExpectedReturnValue = payExpectedReturnValue + chargeParamValue;
        Object chargeActualReturnValue = charge.invoke(instance, chargeParamValue);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " charge method fails to increase debt by correct amount. Starting debt: " + payExpectedReturnValue + ", charge: " + chargeParamValue + ", new debt should be " + chargeExpectedReturnValue + ".",
                chargeExpectedReturnValue, chargeActualReturnValue);
    }

    @Test
    public void test03_EdgeCaseTests() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String wellFormedCheck = classWellFormedCheck();
        if (!wellFormedCheck.isEmpty()) {
            fail(CREDIT_CARD_ACCOUNT_CLASS_NAME + " is not well formed. The test01_ClassWellFormed tests must pass first.\r\n\t" + wellFormedCheck);
        }

        Constructor<?> twoArgConstructor = SafeReflection.getConstructor(creditCardAccountClass, String.class, String.class);
        Object account = twoArgConstructor.newInstance("", "");
        Method pay = SafeReflection.getMethod(creditCardAccountClass, "pay", int.class);
        Method charge = SafeReflection.getMethod(creditCardAccountClass, "charge", int.class);
        Method getDebt = SafeReflection.getMethod(creditCardAccountClass, "getDebt");

        // Assert pay method can handle 0 debt
        SafeReflection.setFieldValue(account, "debt", 1); // reset debt
        pay.invoke(account, 1);
        Object debt = getDebt.invoke(account);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " pay method fails to decrease debt by correct amount. Starting debt: 1, pay: 1, new debt should be 0.",
                0, debt);

        // Assert charge method can handle 0 debt
        SafeReflection.setFieldValue(account, "debt", 0); // reset debt
        charge.invoke(account, 1);
        debt = getDebt.invoke(account);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " charge method fails to increase debt by correct amount. Starting debt: 0, charge: 1, new debt should be 1.",
                1, debt);

        // Assert can't pay a negative amount
        SafeReflection.setFieldValue(account, "debt", 0); // reset debt
        pay.invoke(account, -10);
        debt = getDebt.invoke(account);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " pay method fails to prevent negative pay amount. Starting debt: 0, pay: -10, debt should remain at 0.",
                0, debt);

        // Assert can't charge a negative amount
        SafeReflection.setFieldValue(account, "debt", 10); // reset debt
        charge.invoke(account, -10);
        debt = getDebt.invoke(account);
        assertEquals(CREDIT_CARD_ACCOUNT_CLASS_NAME + " charge method fails to prevent negative charge amount. Starting debt: 10, charge: -10, debt should remain at 10.",
                10, debt);
    }

    private String classWellFormedCheck() {
        // Assert class exists
        if (creditCardAccountClass == null) {
            return CREDIT_CARD_ACCOUNT_CLASS_NAME + " class not found. Have you declared it yet? Make sure the class name is '" + CREDIT_CARD_ACCOUNT_CLASS_NAME + "' and the package name is '" + PACKAGE_NAME + "'.";
        }

        if (Modifier.isAbstract(creditCardAccountClass.getModifiers())) {
            return CREDIT_CARD_ACCOUNT_CLASS_NAME + " class must not be abstract. Remove the 'abstract' modifier on " + CREDIT_CARD_ACCOUNT_CLASS_NAME + ".";
        }

        boolean implementsInterface = Arrays.stream(creditCardAccountClass.getInterfaces())
                .anyMatch(interfaceType -> interfaceType.getSimpleName().equals(ACCOUNTABLE_INTERFACE_NAME));
        if (!implementsInterface) {
            return CREDIT_CARD_ACCOUNT_CLASS_NAME + " must implement " + ACCOUNTABLE_INTERFACE_NAME + " interface.";
        }

        // Assert constructors exist
        try {
            Constructor<?> twoArgConstructor = creditCardAccountClass.getConstructor(String.class, String.class);
        } catch (NoSuchMethodException e) {
            return CREDIT_CARD_ACCOUNT_CLASS_NAME + " does not have the required two argument constructor "
                    + CREDIT_CARD_ACCOUNT_CLASS_NAME + "(String, String). Make sure access for the constructor is public.";
        }

        // Assert fields exist, are of correct type and access
        String fieldCheck = ReflectionTestHelper.checkField(creditCardAccountClass, "accountHolderName", String.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }
        fieldCheck = ReflectionTestHelper.checkField(creditCardAccountClass, "cardNumber", String.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }
        fieldCheck = ReflectionTestHelper.checkField(creditCardAccountClass, "debt", int.class, ReflectionTestHelper.AccessModifier.Private);
        if (!fieldCheck.isEmpty()) {
            return fieldCheck;
        }

        // Assert getters and setters for fields
        String methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "getAccountHolderName", String.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "getCardNumber", String.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "getDebt", int.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }
        methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "getBalance", int.class, true, new Class[] { });
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        // Assert methods are present -- whether they work is confirmed in other test methods
        methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "pay", int.class, true, new Class<?>[]{int.class});
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        methodCheck = ReflectionTestHelper.checkMethod(creditCardAccountClass, "charge", int.class, true, new Class<?>[]{int.class});
        if (!methodCheck.isEmpty()) {
            return methodCheck;
        }

        return "";
    }
}
