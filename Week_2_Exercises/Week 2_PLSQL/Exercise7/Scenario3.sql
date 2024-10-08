CREATE OR REPLACE PACKAGE ACCOUNTOPERATIONS AS

    PROCEDURE OPENNEWACCOUNT(
        P_ACCOUNT_ID IN NUMBER,
        P_CUSTOMER_ID IN NUMBER,
        P_ACCOUNT_TYPE IN VARCHAR2,
        P_BALANCE IN NUMBER
    );

    PROCEDURE CLOSEACCOUNT(
        P_ACCOUNT_ID IN NUMBER
    );

    FUNCTION GETTOTALBALANCE(
        P_CUSTOMER_ID IN NUMBER
    ) RETURN NUMBER;
END ACCOUNTOPERATIONS;
/

CREATE OR REPLACE PACKAGE BODY ACCOUNTOPERATIONS AS

    PROCEDURE OPENNEWACCOUNT(
        P_ACCOUNT_ID IN NUMBER,
        P_CUSTOMER_ID IN NUMBER,
        P_ACCOUNT_TYPE IN VARCHAR2,
        P_BALANCE IN NUMBER
    ) IS
    BEGIN
        INSERT INTO ACCOUNTS (
            ACCOUNTID,
            CUSTOMERID,
            ACCOUNTTYPE,
            BALANCE,
            LASTMODIFIED
        ) VALUES (
            P_ACCOUNT_ID,
            P_CUSTOMER_ID,
            P_ACCOUNT_TYPE,
            P_BALANCE,
            SYSDATE
        );
        COMMIT;
    END OPENNEWACCOUNT;

    PROCEDURE CLOSEACCOUNT(
        P_ACCOUNT_ID IN NUMBER
    ) IS
    BEGIN
        DELETE FROM ACCOUNTS
        WHERE
            ACCOUNTID = P_ACCOUNT_ID;
        COMMIT;
    END CLOSEACCOUNT;

    FUNCTION GETTOTALBALANCE(
        P_CUSTOMER_ID IN NUMBER
    ) RETURN NUMBER IS
        V_TOTAL_BALANCE NUMBER := 0;
    BEGIN
        SELECT
            SUM(BALANCE) INTO V_TOTAL_BALANCE
        FROM
            ACCOUNTS
        WHERE
            CUSTOMERID = P_CUSTOMER_ID;
        RETURN V_TOTAL_BALANCE;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END GETTOTALBALANCE;
END ACCOUNTOPERATIONS;
/