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
public class ChallengeSavingsAccountTest {

    private static Class savingsAccountClass;
    private static Constructor twoArgConstructor;
    private static Constructor threeArgConstructor;

    private static boolean isWellFormed = false;
    private static boolean hasPassedHappyPathTests = false;

    @Test
    public void test01_SavingsAccountClassWellFormed() {
        try {
            // Assert SavingsAccount class exists
            savingsAccountClass = Class.forName("com.techelevator.challenge.SavingsAccount");

            assertFalse("SavingsAccount class must not be abstract. Remove the 'abstract' modifier on SavingsAccount.", Modifier.isAbstract(savingsAccountClass.getModifiers()));

            // Assert SavingsAccount extends BankAccount
            Class superclass = savingsAccountClass.getSuperclass();
            assertEquals("SavingsAccount must extend BankAccount.", superclass.getName(), "com.techelevator.challenge.BankAccount");

            // Assert constructors exist
            twoArgConstructor = SafeReflection.getConstructor(savingsAccountClass, String.class, String.class);
            assertNotNull("SavingsAccount does not have the required two argument constructor SavingsAccount(String, String). Make sure access for the constructor is public.", twoArgConstructor);
            assertTrue("SavingsAccount constructor SavingsAccount(String, String) must be public.", Modifier.isPublic(twoArgConstructor.getModifiers()));

            threeArgConstructor = SafeReflection.getConstructor(savingsAccountClass, String.class, String.class, BigDecimal.class);
            assertNotNull("SavingsAccount does not have the required three argument constructor SavingsAccount(String, String, int). Make sure access for the constructor is public.", threeArgConstructor);
            assertTrue("SavingsAccount constructor SavingsAccount(String, String, int) must be public.", Modifier.isPublic(threeArgConstructor.getModifiers()));

            // Assert methods are present -- whether they work is confirmed in other test methods
            Method withdrawMethod = SafeReflection.getMethod(savingsAccountClass, "withdraw", BigDecimal.class);
            assertEquals("SavingsAccount must override BankAccount withdraw(BigDecimal) method. Make sure access for the method is public.", "com.techelevator.challenge.SavingsAccount", withdrawMethod.getDeclaringClass().getName());

            // Assert BankAccount fields are NOT redefined in SavingsAccount
            Field accountHolderNameField = SafeReflection.getDeclaredField(savingsAccountClass, "accountHolderName");
            assertEquals("SavingsAccount must not redefine the field accountHolderName.", null, accountHolderNameField);
            Field accountNumberField = SafeReflection.getDeclaredField(savingsAccountClass, "accountNumber");
            assertEquals("SavingsAccount must not redefine the field accountNumber.", null, accountNumberField);
            Field balanceField = SafeReflection.getDeclaredField(savingsAccountClass, "balance");
            assertEquals("SavingsAccount must not redefine the field balance.", null, balanceField);

            // Assert BankAccount getters/setters are NOT redefined in SavingsAccount
            Method accountHolderNameGetter = SafeReflection.getMethod(savingsAccountClass, "getAccountHolderName");
            assertEquals("SavingsAccount must not redefine getter getAccountHolderName().", "com.techelevator.challenge.BankAccount", accountHolderNameGetter.getDeclaringClass().getName());
            Method accountHolderNameSetter = SafeReflection.getMethod(savingsAccountClass, "setAccountHolderName", String.class);
            assertEquals("SavingsAccount must not redefine setter setAccountHolderName(String).", null, accountHolderNameSetter);
            Method accountNumberGetter = SafeReflection.getMethod(savingsAccountClass, "getAccountNumber");
            assertEquals("SavingsAccount must not redefine getter getAccountNumber().", "com.techelevator.challenge.BankAccount", accountNumberGetter.getDeclaringClass().getName());
            Method accountNumberSetter = SafeReflection.getMethod(savingsAccountClass, "setAccountNumber", String.class);
            assertEquals("SavingsAccount must not redefine setter setAccountNumber(String).", null, accountNumberSetter);
            Method balanceGetter = SafeReflection.getMethod(savingsAccountClass, "getBalance");
            assertEquals("SavingsAccount must not redefine getter getBalance().", "com.techelevator.challenge.BankAccount", balanceGetter.getDeclaringClass().getName());
            Method balanceSetter = SafeReflection.getMethod(savingsAccountClass, "setBalance", Integer.TYPE);
            assertEquals("SavingsAccount must not redefine setter setBalance(int).", null, balanceSetter);

            // Assert BankAccount methods are NOT redefined in SavingsAccount
            Method depositMethod = SafeReflection.getMethod(savingsAccountClass, "deposit", BigDecimal.class);
            assertEquals("SavingsAccount must not redefine deposit(int).", "com.techelevator.challenge.BankAccount", depositMethod.getDeclaringClass().getName());

            isWellFormed = true;
        } catch (Exception e) {
            fail("com.techelevator.SavingsAccount class does not exist");
        }
    }

    @Test
    public void test02_HappyPath_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("SavingsAccount is not well formed. The test01_SavingsAccountClassWellFormed tests must pass first. You need to run all tests in this class at the same time.", isWellFormed);

        // Assert constructors set fields
        // Two arg constructor
        Object savingsAccount = twoArgConstructor.newInstance("John Smith", "CHK:1234");
        Method getAccountHolderName = savingsAccount.getClass().getSuperclass().getMethod("getAccountHolderName");
        assertEquals("SavingsAccount two argument constructor SavingsAccount(String, String) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(savingsAccount));
        Method getAccountNumber = savingsAccount.getClass().getSuperclass().getMethod("getAccountNumber");
        assertEquals("SavingsAccount two argument constructor SavingsAccount(String, String) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(savingsAccount));
        // Three arg constructor
        savingsAccount = threeArgConstructor.newInstance("John Smith", "CHK:1234", new BigDecimal(200));
        getAccountHolderName = savingsAccount.getClass().getSuperclass().getMethod("getAccountHolderName");
        assertEquals("SavingsAccount three argument constructor SavingsAccount(String, String, int) does not correctly set the field AccountHolderName.", "John Smith", getAccountHolderName.invoke(savingsAccount));
        getAccountNumber = savingsAccount.getClass().getSuperclass().getMethod("getAccountNumber");
        assertEquals("SavingsAccount three argument constructor SavingsAccount(String, String, int) does not correctly set the field AccountNumber.", "CHK:1234", getAccountNumber.invoke(savingsAccount));
        Method getBalance = savingsAccount.getClass().getSuperclass().getMethod("getBalance");
        assertEquals("SavingsAccount three argument constructor SavingsAccount(String, String, int) does not correctly set the field Balance.", new BigDecimal(200), getBalance.invoke(savingsAccount));

        // Assert withdraw decreases balance
        Method withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal(25));
        assertEquals("SavingsAccount withdraw fails to decrease balance. Current balance: 200, withdraw: 25, new balance should be 175.", new BigDecimal(175), getBalance.invoke(savingsAccount));

        hasPassedHappyPathTests = true;
    }

    @Test
    public void test03_EdgeCase_Tests() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        assumeTrue("SavingsAccount happy path tests have not passed. The test02_HappyPath_Tests tests must pass first. You need to run all tests in this class at the same time.", hasPassedHappyPathTests);

        // Assert withdrawal of 50 dollars from 200 allows withdrawal but no fee
        Object savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("200.00"));
        Method getBalance = savingsAccount.getClass().getMethod("getBalance");
        Method withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal("50.00"));
        assertEquals("SavingsAccount withdraw method fails to decrease balance by correct amount. Starting balance: 200, withdraw: 50, new balance should be 150 (200 - 50 = 150).", new BigDecimal("150.00"), getBalance.invoke(savingsAccount));

        // Assert withdrawal of 51 dollars from 200 allows withdrawal and assesses fee
        savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("200.00"));
        getBalance = savingsAccount.getClass().getMethod("getBalance");
        withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal("51.00"));
        assertEquals("SavingsAccount withdraw method fails to decrease balance by correct amount. Starting balance: 200, withdraw: 51, new balance should be 147 (200 - (51 + 2 fee) = 147).", new BigDecimal("147.00"), getBalance.invoke(savingsAccount));

        // Assert withdrawal of 198 dollars from 200 allows withdrawal and assesses fee
        savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("200.00"));
        getBalance = savingsAccount.getClass().getMethod("getBalance");
        withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal("198.00"));
        assertEquals("SavingsAccount withdraw method fails to decrease balance by correct amount. Starting balance: 200, withdraw: 198, new balance should be 0 (200 - (198 + 2 fee) = 0).", new BigDecimal("0.00"), getBalance.invoke(savingsAccount));

        // Assert withdrawal of 199 dollars from 200 denies withdrawal but no fee
        savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("200.00"));
        getBalance = savingsAccount.getClass().getMethod("getBalance");
        withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal("199.00"));
        assertEquals("SavingsAccount withdraw method fails to decrease balance by correct amount. Starting balance: 200, withdraw: 199, overdraft should be denied (200 - (199 + 2 fee) < 0) and balance remains 200.", new BigDecimal("200.00"), getBalance.invoke(savingsAccount));

        // Assert can't deposit a negative amount
        savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        Method deposit = savingsAccount.getClass().getMethod("deposit", BigDecimal.class);
        deposit.invoke(savingsAccount, new BigDecimal("-10"));
        assertEquals("SavingsAccount deposit method fails to prevent negative deposit amount. Starting balance: 100, deposit: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(savingsAccount));

        // Assert can't withdraw a negative amount
        savingsAccount = threeArgConstructor.newInstance("", "", new BigDecimal("100.00"));
        withdraw = savingsAccount.getClass().getMethod("withdraw", BigDecimal.class);
        withdraw.invoke(savingsAccount, new BigDecimal("-10"));
        assertEquals("SavingsAccount withdraw method fails to prevent negative withdraw amount. Starting balance: 100, withdraw: -10, balance should remain at 100.", new BigDecimal("100.00"), getBalance.invoke(savingsAccount));
    }
}
