CREATE OR REPLACE PROCEDURE ADDNEWCUSTOMER (
    CUSTOMER_ID IN NUMBER,
    CUSTOMER_NAME IN VARCHAR2,
    CUSTOMER_EMAIL IN VARCHAR2
) IS
    CUSTOMER_EXISTS EXCEPTION;
    V_COUNT         NUMBER;
BEGIN
 
    -- Check if the customer already exists
    SELECT
        COUNT(*) INTO V_COUNT
    FROM
        CUSTOMERS
    WHERE
        ID = CUSTOMER_ID;
    IF V_COUNT > 0 THEN
        RAISE CUSTOMER_EXISTS;
    ELSE
 
        -- Insert the new customer
        INSERT INTO CUSTOMERS (
            ID,
            NAME,
            EMAIL
        ) VALUES (
            CUSTOMER_ID,
            CUSTOMER_NAME,
            CUSTOMER_EMAIL
        );
    END IF;

    COMMIT;
EXCEPTION
    WHEN CUSTOMER_EXISTS THEN
 
        -- Log the error
        INSERT INTO ERRORLOGS (
            MESSAGE,
            LOG_TIME
        ) VALUES (
            'Customer ID already exists',
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
END ADDNEWCUSTOMER;