package challenge;

import com.techelevator.SafeReflection;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.*;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * THESE TESTS ARE NOT PART OF THE STUDENT CODE.
 * They exist only to verify the solution challenge code.
 * They must not be in the com.techelevator package to prevent running in the LMS to score student submissions.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChallengeBankAccountTest {

    private static Class bankAccountClass;
    private static Constructor twoArgConstructor;
    private static Constructor threeArgConstructor;

    private static boolean isWellFormed = false;
    private static boolean hasPassedHappyPathTests = false;

    @Test
    public void test01_BankAccountClassWellFormed() {
        try {

            // Assert BankAccount class exists
            bankAccountClass = Class.forName("com.techelevator.challenge.BankAccount");

            assertFalse("BankAccount class must not be abstract. Remove the 'abstract' modifier on BankAccount.", Modifier.isAbstract(bankAccountClass.getModifiers()));

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(bankAccountClass, String.class, String.class);
            assertNotNull("BankAccount does not have the required two argument constructor BankAccount(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("BankAccount constructor BankAccount(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            threeArgConstructor = SafeReflection.getConstructor(bankAccountClass, String.class, String.class, BigDecimal.class);
            assertNotNull("BankAccount does not have the required three argument constructor BankAccount(String, String, BigDecimal). Make sure access for the constructor is public.", threeArgConstructor);
            assertTrue("BankAccount constructor BankAccount(String, String, int) must be public.", Modifier.isPublic(threeArgConstructor.getModifiers()));

            // Assert fields exist, are of correct type and access
            Field accountHolderNameField = SafeReflection.getDeclaredField(bankAccountClass, "accountHolderName");
            assertNotNull("BankAccount does not have the required field accountHolderName.", accountHolderNameField);
            assertEquals("BankAccount field accountHolderName must be type String.", "java.lang.String", accountHolderNameField.getType().getName());
            assertTrue("BankAccount field accountHolderName must be private.", Modifier.isPrivate(accountHolderNameField.getModifiers()));

            Field accountNumberField = SafeReflection.getDeclaredField(bankAccountClass, "accountNumber");
            assertNotNull("BankAccount does not have the required field accountNumber.", accountNumberField);
            assertEquals("BankAccount field accountNumber must be type String.", "java.lang.String", accountNumberField.getType().getName());
            assertTrue("BankAccount field accountNumber must be private.", Modifier.isPrivate(accountNumberField.getModifiers()));

            Field balanceField = SafeReflection.getDeclaredField(bankAccountClass, "balance");
            assertNotNull("BankAccount does not have the required field balance.", balanceField);
            assertEquals("BankAccount field balance must be type BigDecimal.", "java.math.BigDecimal", balanceField.getType().getName());
            assertTrue("BankAccount field balance must be private.", Modifier.isPrivate(balanceField.getModifiers()));

            // Assert getters and setters for fields
            Method accountHolderNameGetter = SafeReflection.getMethod(bankAccountClass, "getAccountHolderName");
            assertTrue("BankAccount must have getter getAccountHolderName(). Make sure access for the getter is public.", accountHolderNameGetter != null);
            assertTrue("BankAccount getter getAccountHolderName() must return type String", accountHolderNameGetter.getReturnType() == String.class);
            assertTrue("BankAccount getter getAccountHolderName() must be public.", Modifier.isPublic(accountHolderNameGetter.getModifiers()));
            Method accountHolderNameSetter = SafeReflection.getMethod(bankAccountClass, "setAccountHolderName", String.class);
            assertTrue("BankAccount must not have a setter setAccountHolderName(String)", accountHolderNameSetter == null);

            Method accountNumberGetter = SafeReflection.getMethod(bankAccountClass, "getAccountNumber");
            assertTrue("BankAccount must have getter getAccountNumber(). Make sure access for the getter is public.", accountNumberGetter != null);
            assertTrue("BankAccount getter getAccountNumber() must return type String", accountNumberGetter.getReturnType() == String.class);
            assertTrue("BankAccount getter getAccountNumber() must be public.", Modifier.isPublic(accountNumberGetter.getModifiers()));
            Method accountNumberSetter = SafeReflection.getMethod(bankAccountClass, "setAccountNumber", String.class);
            assertTrue("BankAccount must not have a setter setAccountNumber(String)", accountNumberSetter == null);

            Method balanceGetter = SafeReflection.getMethod(bankAccountClass, "getBalance");
            assertTrue("BankAccount must have getter getBalance(). Make sure access for the getter is public.", balanceGetter != null);
            assertTrue("BankAccount getter getBalance() must return type BigDecimal", balanceGetter.getReturnType() == BigDecimal.class);
            assertTrue("BankAccount getter getBalance() must be public.", Modifier.isPublic(balanceGetter.getModifiers()));
            Method balanceSetter = SafeReflection.getMethod(bankAccountClass, "setBalance", BigDecimal.class);
            assertTrue("BankAccount must not have a setter setBalance(BigDecimal)", balanceSetter == null);

            // Assert methods are present -- whether they work is confirmed in other test methods
            Method depositMethod = SafeReflection.getMethod(bankAccountClass, "deposit", BigDecimal.class);
            assertTrue("BankAccount must have method deposit(BigDecimal). Make sure access for the method is public.", depositMethod != null);
            assertTrue("BankAccount method deposit(BigDecimal) must return type BigDecimal", depositMethod.getReturnType() == BigDecimal.class);
            assertTrue("BankAccount method deposit(BigDecimal) must be public.", Modifier.isPublic(depositMethod.getModifiers()));

            Method withdrawMethod = SafeReflection.getMethod(bankAccountClass, "withdraw", BigDecimal.class);
            assertTrue("BankAccount must have method withdraw(BigDecimal). Make sure access for the method is public.", withdrawMethod != null);
            assertTrue("BankAccount method withdraw(BigDecimal) must return type BigDecimal", withdrawMethod.getReturnType() == BigDecimal.class);
            assertTrue("BankAccount method withdraw(BigDecimal) must be public.", Modifier.isPublic(withdrawMethod.getModifiers()));

            isWellFormed = true;
        } catch (Exception e) {
            fail("com.techelevator.BankAccount class does not exist.");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        assumeTrue("BankAccount is not well formed. The test01_BankAccountClassWellFormed tests must pass first. You need to run all tests in this class at the same time.", isWellFormed);

        // Assert constructors set fields
        // Two arg constructor
        Object bankAccount = twoArgConstructor.newInstance("John Smith", "CHK:1234");
        Method getAccountHolderName = bankAccount.getClass().getMethod("getAccountHolderName");
        assertEquals("BankAccount two argument constructor BankAccount(String, String) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(bankAccount));
        Method getAccountNumber = bankAccount.getClass().getMethod("getAccountNumber");
        assertEquals("BankAccount two argument constructor BankAccount(String, String) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(bankAccount));
        // Three arg constructor
        bankAccount = threeArgConstructor.newInstance("John Smith", "CHK:1234", new BigDecimal("100.00"));
        getAccountHolderName = bankAccount.getClass().getMethod("getAccountHolderName");
        assertEquals("BankAccount three argument constructor BankAccount(String, String, int) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(bankAccount));
        getAccountNumber = bankAccount.getClass().getMethod("getAccountNumber");
        assertEquals("BankAccount three argument constructor BankAccount(String, String, int) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(bankAccount));
        Method getBalance = bankAccount.getClass().getMethod("getBalance");
        assertEquals("BankAccount three argument constructor BankAccount(String, String, int) does not correctly set the field Balance.", new BigDecimal("100.00"), getBalance.invoke(bankAccount));

        // Assert deposit increases balance
        Method deposit = bankAccount.getClass().getMethod("deposit", BigDecimal.class);
        deposit.invoke(bankAccount, new BigDecimal("23.00"));
        assertEquals("BankAccount deposit method fails to increase balance by correct amount. Starting balance: 100, deposit: 23, new balance should be 123.", new BigDecimal("123.00"), getBalance.invoke(bankAccount));

        // Assert withdraw decreases balance
        Method withdraw = bankAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(bankAccount, new BigDecimal("22.00"));
        assertEquals("BankAccount withdraw method fails to decrease balance by correct amount. Starting balance: 123, withdraw: 22, new balance should be 101.", new BigDecimal("101.00"), getBalance.invoke(bankAccount));

        hasPassedHappyPathTests = true;
    }

    @Test
    public void test03_EdgeCase_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("BankAccount happy path tests have not passed. The test02_HappyPath_Tests tests must pass first. You need to run all tests in this class at the same time.", hasPassedHappyPathTests);

        // Assert two argument constructor defaults balance to 0
        Object bankAccount = twoArgConstructor.newInstance("", "");
        Method getBalance = bankAccount.getClass().getMethod("getBalance");
        assertEquals("BankAccount two argument constructor BankAccount(String, String) does not default balance to 0.", BigDecimal.ZERO, getBalance.invoke(bankAccount));

        // Assert withdraw method can handle 0 balance
        bankAccount = threeArgConstructor.newInstance("", "", BigDecimal.ONE);
        Method withdraw = bankAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(bankAccount, BigDecimal.ONE);
        assertEquals("BankAccount withdraw method fails to decrease balance by correct amount. Starting balance: 1, withdraw: 1, new balance should be 0.", BigDecimal.ZERO, getBalance.invoke(bankAccount));

        // Assert deposit method can handle 0 balance
        bankAccount = threeArgConstructor.newInstance("", "", BigDecimal.ZERO);
        Method deposit = bankAccount.getClass().getMethod("deposit", BigDecimal.class);
        deposit.invoke(bankAccount, BigDecimal.ONE);
        assertEquals("BankAccount deposit method fails to increase balance by correct amount. Starting balance: 0, deposit: 1, new balance should be 1.", BigDecimal.ONE, getBalance.invoke(bankAccount));

        // Assert can't deposit a negative amount
        bankAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        deposit = bankAccount.getClass().getMethod("deposit", BigDecimal.class);
        deposit.invoke(bankAccount, new BigDecimal("-10"));
        assertEquals("BankAccount deposit method fails to prevent negative deposit amount. Starting balance: 100, deposit: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(bankAccount));

        // Assert can't withdraw a negative amount
        bankAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        withdraw = bankAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(bankAccount, new BigDecimal("-10"));
        assertEquals("BankAccount withdraw method fails to prevent negative withdraw amount. Starting balance: 100, withdraw: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(bankAccount));
    }

}
