CREATE OR REPLACE TRIGGER CHECKTRANSACTIONRULES BEFORE
    INSERT ON TRANSACTIONS FOR EACH ROW
DECLARE
    CURRENT_BALANCE NUMBER;
BEGIN
 
    -- Check if the transaction is a withdrawal
    IF :NEW.TRANSACTION_TYPE = 'WITHDRAWAL' THEN
 
        -- Retrieve the current balance of the account
        SELECT
            BALANCE INTO CURRENT_BALANCE
        FROM
            ACCOUNTS
        WHERE
            ACCOUNT_ID = :NEW.ACCOUNT_ID FOR UPDATE;
 
        -- Ensure the withdrawal amount does not exceed the current balance
        IF :NEW.AMOUNT > CURRENT_BALANCE THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds current balance.');
        END IF;
    ELSIF :NEW.TRANSACTION_TYPE = 'DEPOSIT' THEN
 
        -- Ensure the deposit amount is positive
        IF :NEW.AMOUNT <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    END IF;
END CHECKTRANSACTIONRULES;
/