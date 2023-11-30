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
public class ChallengeCheckingAccountTest {

    private static Class checkingAccountClass;
    private static Constructor twoArgConstructor;
    private static Constructor threeArgConstructor;

    private static boolean isWellFormed = false;
    private static boolean hasPassedHappyPathTests = false;

    @Test
    public void test01_CheckingAccountClassWellFormed() {
        try {

            // Assert CheckingAccount class exists
            checkingAccountClass = Class.forName("com.techelevator.challenge.CheckingAccount");

            assertFalse("CheckingAccount class must not be abstract. Remove the 'abstract' modifier on CheckingAccount.", Modifier.isAbstract(checkingAccountClass.getModifiers()));

            // Assert CheckingAccount extends BankAccount
            Class superclass = checkingAccountClass.getSuperclass();
            assertEquals("CheckingAccount must extend BankAccount.", superclass.getName(), "com.techelevator.challenge.BankAccount");

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(checkingAccountClass, String.class, String.class);
            assertNotNull("CheckingAccount does not have the required two argument constructor CheckingAccount(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("CheckingAccount constructor CheckingAccount(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            threeArgConstructor = SafeReflection.getConstructor(checkingAccountClass, String.class, String.class, BigDecimal.class);
            assertNotNull("CheckingAccount does not have the required three argument constructor CheckingAccount(String, String, BigDecimal). Make sure access for the constructor is public.", threeArgConstructor);
            assertTrue("CheckingAccount constructor CheckingAccount(String, String, BigDecimal) must be public.", Modifier.isPublic(threeArgConstructor.getModifiers()));

            // Assert override methods are present -- whether they work is confirmed in other test methods
            Method withdrawMethod = SafeReflection.getMethod(checkingAccountClass, "withdraw", BigDecimal.class);
            assertEquals("CheckingAccount must override BankAccount withdraw(BigDecimal) method. Make sure access for the method is public.", "com.techelevator.challenge.CheckingAccount", withdrawMethod.getDeclaringClass().getName());

            // Assert BankAccount fields are NOT redefined in CheckingAccount
            Field accountHolderNameField = SafeReflection.getDeclaredField(checkingAccountClass, "accountHolderName");
            assertEquals("CheckingAccount must not redefine the field accountHolderName.", null, accountHolderNameField);
            Field accountNumberField = SafeReflection.getDeclaredField(checkingAccountClass, "accountNumber");
            assertEquals("CheckingAccount must not redefine the field accountNumber.", null, accountNumberField);
            Field balanceField = SafeReflection.getDeclaredField(checkingAccountClass, "balance");
            assertEquals("CheckingAccount must not redefine the field balance.", null, balanceField);

            // Assert BankAccount getters/setters are NOT redefined in CheckingAccount
            Method accountHolderNameGetter = SafeReflection.getMethod(checkingAccountClass, "getAccountHolderName");
            assertEquals("CheckingAccount must not redefine getter getAccountHolderName().", "com.techelevator.challenge.BankAccount", accountHolderNameGetter.getDeclaringClass().getName());
            Method accountHolderNameSetter = SafeReflection.getMethod(checkingAccountClass, "setAccountHolderName", String.class);
            assertEquals("CheckingAccount must not redefine setter setAccountHolderName(String).", null, accountHolderNameSetter);
            Method accountNumberGetter = SafeReflection.getMethod(checkingAccountClass, "getAccountNumber");
            assertEquals("CheckingAccount must not redefine getAccountNumber().", "com.techelevator.challenge.BankAccount", accountNumberGetter.getDeclaringClass().getName());
            Method accountNumberSetter = SafeReflection.getMethod(checkingAccountClass, "setAccountNumber", String.class);
            assertEquals("CheckingAccount must not redefine setter setAccountNumber(String).", null, accountNumberSetter);
            Method balanceGetter = SafeReflection.getMethod(checkingAccountClass, "getBalance");
            assertEquals("CheckingAccount must not redefine getter getBalance().", "com.techelevator.challenge.BankAccount", balanceGetter.getDeclaringClass().getName());
            Method balanceSetter = SafeReflection.getMethod(checkingAccountClass, "setBalance", BigDecimal.class);
            assertEquals("CheckingAccount must not redefine setter setBalance(BigDecimal).", null, balanceSetter);

            // Assert BankAccount methods are NOT redefined in CheckingAccount
            Method depositMethod = SafeReflection.getMethod(checkingAccountClass, "deposit", BigDecimal.class);
            assertEquals("CheckingAccount must not redefine deposit(BigDecimal).", "com.techelevator.challenge.BankAccount", depositMethod.getDeclaringClass().getName());

            isWellFormed = true;
        } catch (Exception e) {
            fail("com.techelevator.CheckingAccount class does not exist.");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("CheckingAccount is not well formed. The test01_CheckingAccountClassWellFormed tests must pass first. You need to run all tests in this class at the same time.", isWellFormed);

        // Assert constructors set fields
        // Two arg constructor
        Object checkingAccount = twoArgConstructor.newInstance("John Smith", "CHK:1234");
        Method getAccountHolderName = checkingAccount.getClass().getSuperclass().getMethod("getAccountHolderName");
        assertEquals("CheckingAccount two argument constructor CheckingAccount(String, String) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(checkingAccount));
        Method getAccountNumber = checkingAccount.getClass().getSuperclass().getMethod("getAccountNumber");
        assertEquals("CheckingAccount two argument constructor CheckingAccount(String, String) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(checkingAccount));
        // Three arg constructor
        checkingAccount = threeArgConstructor.newInstance("John Smith", "CHK:1234", new BigDecimal("100.00"));
        getAccountHolderName = checkingAccount.getClass().getSuperclass().getMethod("getAccountHolderName");
        assertEquals("CheckingAccount three argument constructor CheckingAccount(String, String, BigDecimal) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(checkingAccount));
        getAccountNumber = checkingAccount.getClass().getSuperclass().getMethod("getAccountNumber");
        assertEquals("CheckingAccount three argument constructor CheckingAccount(String, String, BigDecimal) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(checkingAccount));
        Method getBalance = checkingAccount.getClass().getSuperclass().getMethod("getBalance");
        assertEquals("CheckingAccount three argument constructor CheckingAccount(String, String, BigDecimal) does not correctly set the field Balance.", new BigDecimal("100.00"), getBalance.invoke(checkingAccount));

        // Assert withdraw decreases balance
        Method withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("23.00"));
        assertEquals("CheckingAccount withdraw fails to decrease balance. Current balance: 100, withdraw: 23, new balance should be 77.", new BigDecimal("77.00"), getBalance.invoke(checkingAccount));

        hasPassedHappyPathTests = true;
    }

    @Test
    public void test03_EdgeCase_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("CheckingAccount happy path tests have not passed. The test02_HappyPath_Tests tests must pass first. You need to run all tests in this class at the same time.", hasPassedHappyPathTests);

        // Assert 1 dollar under 0 balance allows overdraft and assesses fee
        Object checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        Method getBalance = checkingAccount.getClass().getMethod("getBalance");
        Method withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("101.00"));
        assertEquals("CheckingAccount withdraw method fails to decrease balance by correct amount. Starting balance: 100, withdraw: 101, new balance should be -11 (-1 is greater than -100, 10 fee incurred).", new BigDecimal("-11.00"), getBalance.invoke(checkingAccount));

        // Assert 10 dollar above -100 balance allows overdraft and assesses fee
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        getBalance = checkingAccount.getClass().getMethod("getBalance");
        withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("190.00"));
        assertEquals("CheckingAccount withdraw method fails to decrease balance by correct amount. Starting balance: 100, withdraw: 190, new balance should be -100 (-90 is greater than -100, 10 fee incurred).", new BigDecimal("-100.00"), getBalance.invoke(checkingAccount));

        // Assert 1 dollar above -100 balance allows overdraft and assesses fee
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        getBalance = checkingAccount.getClass().getMethod("getBalance");
        withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("199.00"));
        assertEquals("CheckingAccount withdraw method fails to decrease balance by correct amount. Starting balance: 100, withdraw: 199, new balance should be -109 (-99 is greater than -100, 10 fee incurred).", new BigDecimal("-109.00"), getBalance.invoke(checkingAccount));

        // Assert withdraw that would make -100 balance denies overdraft and doesn't assess fee
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        getBalance = checkingAccount.getClass().getMethod("getBalance");
        withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("200.00"));
        assertEquals("CheckingAccount withdraw method fails to decrease balance by correct amount. Starting balance: 100, withdraw: 200, overdraft should be denied (100 - 200 >= -100) and balance remains at 100.", new BigDecimal("100.00"), getBalance.invoke(checkingAccount));

        // Assert withdraw that would make 0 balance does not incur fee
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        getBalance = checkingAccount.getClass().getMethod("getBalance");
        withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("100.00"));
        assertEquals("CheckingAccount withdraw method fails to decrease balance by correct amount. Starting balance: 100, withdraw: 100, new balance should be 0. No fee incurred because balance is not less than 0.", new BigDecimal("0.00"), getBalance.invoke(checkingAccount));

        // Assert can't deposit a negative amount
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        Method deposit = checkingAccount.getClass().getMethod("deposit", BigDecimal.class);
        deposit.invoke(checkingAccount, new BigDecimal("-10"));
        assertEquals("CheckingAccount deposit method fails to prevent negative deposit amount. Starting balance: 100, deposit: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(checkingAccount));

        // Assert can't withdraw a negative amount
        checkingAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        withdraw = checkingAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(checkingAccount, new BigDecimal("-10"));
        assertEquals("CheckingAccount withdraw method fails to prevent negative withdraw amount. Starting balance: 100, withdraw: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(checkingAccount));
    }
}
