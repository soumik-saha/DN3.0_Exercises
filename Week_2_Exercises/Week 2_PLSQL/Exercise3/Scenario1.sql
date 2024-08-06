DELIMITER /
/

CREATE PROCEDURE PROCESSMONTHLYINTEREST(
)
BEGIN
    DECLARE
        DONE      INT DEFAULT 0;
        DECLARE   ACCOUNT_ID INT;
        DECLARE   BALANCE DECIMAL(10, 2);
        DECLARE   CUR CURSOR FOR
        SELECT
            ACCOUNT_ID,
            BALANCE
        FROM
            SAVINGSACCOUNTS;
        DECLARE   CONTINUE HANDLER FOR NOT FOUND SET DONE = 1;
        OPEN      CUR;
        READ_LOOP : LOOP
            FETCH CUR INTO ACCOUNT_ID, BALANCE;
            IF DONE THEN
                LEAVE READ_LOOP;
            END IF;
 

            -- Apply 1% interest to the current balance
            SET BALANCE = BALANCE + (BALANCE * 0.01);
 
            -- Update the account with the new balance
            UPDATE SAVINGSACCOUNTS
            SET
                BALANCE = BALANCE
            WHERE
                ACCOUNT_ID = ACCOUNT_ID;
        END LOOP;

        CLOSE     CUR;
    END // DELIMITER;