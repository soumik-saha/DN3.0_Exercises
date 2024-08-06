DELIMITER /
/

CREATE PROCEDURE TRANSFERFUNDS(
    IN SOURCE_ACCOUNT_ID INT,
    IN DESTINATION_ACCOUNT_ID INT,
    IN AMOUNT DECIMAL(10, 2)
)
BEGIN
    DECLARE
        CURRENT_BALANCE DECIMAL(10, 2);
 
        -- Check the balance of the source account
        SELECT          BALANCE INTO CURRENT_BALANCE FROM ACCOUNTS WHERE ACCOUNT_ID = SOURCE_ACCOUNT_ID;
 
        -- Check if the source account has sufficient balance
        IF              CURRENT_BALANCE >= AMOUNT THEN
 
            -- Deduct the amount from the source account
            UPDATE ACCOUNTS SET BALANCE = BALANCE - AMOUNT WHERE ACCOUNT_ID = SOURCE_ACCOUNT_ID;
 
            -- Add the amount to the destination account
            UPDATE          ACCOUNTS SET BALANCE = BALANCE + AMOUNT WHERE ACCOUNT_ID = DESTINATION_ACCOUNT_ID;
            ELSE           
 -- Insufficient funds, raise an error
            SIGNAL SQLSTATE '5000' SET MESSAGE_TEXT = 'Insufficient funds in the source account';
        END IF;
    END // DELIMITER;