DECLARE
    CURSOR C_ACCOUNTS IS
    SELECT
        ACCOUNTID,
        BALANCE
    FROM
        ACCOUNTS;
    V_ACCOUNTID  ACCOUNTS.ACCOUNTID%TYPE;
    V_BALANCE    ACCOUNTS.BALANCE%TYPE;
    V_ANNUAL_FEE NUMBER := 50; -- Example annual fee
BEGIN
    OPEN C_ACCOUNTS;
    LOOP
        FETCH C_ACCOUNTS INTO V_ACCOUNTID, V_BALANCE;
        EXIT WHEN C_ACCOUNTS%NOTFOUND;
 
        -- Deduct the annual fee
        UPDATE ACCOUNTS
        SET
            BALANCE = V_BALANCE - V_ANNUAL_FEE
        WHERE
            ACCOUNTID = V_ACCOUNTID;
 
        -- Optionally, log the update
        DBMS_OUTPUT.PUT_LINE('Applied fee to AccountID: '
                             || V_ACCOUNTID);
    END LOOP;

    CLOSE C_ACCOUNTS;
END;
/