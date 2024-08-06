CREATE OR REPLACE PROCEDURE SAFETRANSFERFUNDS (
    FROM_ACCOUNT_ID IN NUMBER,
    TO_ACCOUNT_ID IN NUMBER,
    AMOUNT IN NUMBER
) IS
    INSUFFICIENT_FUNDS EXCEPTION;
    FUNDS_BALANCE      NUMBER;
BEGIN
 
    -- Check balance of the from_account
    SELECT
        BALANCE INTO FUNDS_BALANCE
    FROM
        ACCOUNTS
    WHERE
        ACCOUNT_ID = FROM_ACCOUNT_ID FOR UPDATE;
    IF FUNDS_BALANCE < AMOUNT THEN
        RAISE INSUFFICIENT_FUNDS;
    ELSE
 
        -- Deduct the amount from the from_account
        UPDATE ACCOUNTS
        SET
            BALANCE = BALANCE - AMOUNT
        WHERE
            ACCOUNT_ID = FROM_ACCOUNT_ID;
 
        -- Add the amount to the to_account
        UPDATE ACCOUNTS
        SET
            BALANCE = BALANCE + AMOUNT
        WHERE
            ACCOUNT_ID = TO_ACCOUNT_ID;
    END IF;

    COMMIT;
EXCEPTION
    WHEN INSUFFICIENT_FUNDS THEN
 
        -- Log the error
        INSERT INTO ERRORLOGS (
            MESSAGE,
            LOG_TIME
        ) VALUES (
            'Insufficient funds for transfer',
            SYSDATE
        );
        ROLLBACK;
    WHEN OTHERS THEN
 
        -- Log any other errors
        INSERT INTO ERRORLOGS (
            MESSAGE,
            LOG_TIME
        ) VALUES (
            SQLERRM,
            SYSDATE
        );
        ROLLBACK;
END SAFETRANSFERFUNDS;