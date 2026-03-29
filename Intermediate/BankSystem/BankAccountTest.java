import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    void testDepositSuccess() {
        BankAccount acc = new BankAccount("Edward", 1000);
        assertTrue(acc.deposit(500));
        assertEquals(1500, acc.getBalance());
    }

    @Test
    void testDepositFailure() {
        BankAccount acc = new BankAccount("Edward", 1000);
        assertFalse(acc.deposit(-100));
        assertEquals(1000, acc.getBalance());
    }

    @Test
    void testWithdrawSuccess() {
        BankAccount acc = new BankAccount("Edward", 1000);
        assertTrue(acc.withdraw(300));
        assertEquals(700, acc.getBalance());
    }

    @Test
    void testWithdrawFailure() {
        BankAccount acc = new BankAccount("Edward", 1000);
        assertFalse(acc.withdraw(2000));
        assertEquals(1000, acc.getBalance());
    }

    @Test
    void testTransactionHistory() {
        BankAccount acc = new BankAccount("Edward", 1000);
        acc.deposit(500);
        acc.withdraw(200);

        // Not perfect test, but checks no crash and balance correct
        assertEquals(1300, acc.getBalance());
    }
}
