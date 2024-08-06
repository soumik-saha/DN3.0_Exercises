CREATE OR REPLACE FUNCTION HASSUFFICIENTBALANCE (
    ACCOUNT_ID IN NUMBER,
    AMOUNT IN NUMBER
) RETURN BOOLEAN IS
    CURRENT_BALANCE NUMBER;
BEGIN
 
    -- Retrieve the current balance of the account
    SELECT
        BALANCE INTO CURRENT_BALANCE
    FROM
        ACCOUNTS
    WHERE
        ACCOUNT_ID = ACCOUNT_ID;
 
    -- Check if the balance is sufficient
    IF CURRENT_BALANCE >= AMOUNT THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
 
        -- Handle the case where the account ID does not exist
        RETURN FALSE;
    WHEN OTHERS THEN
 
        -- Handle any other exceptions
        RETURN FALSE;
END HASSUFFICIENTBALANCE;