BEGIN
    FOR REC IN (
        SELECT
            CUSTOMERID,
            BALANCE
        FROM
            CUSTOMERS
    ) LOOP
        IF REC.BALANCE > 10000 THEN
            UPDATE CUSTOMERS
            SET
                ISVIP = 1
            WHERE
                CUSTOMERID = REC.CUSTOMERID;
        ELSE
            UPDATE CUSTOMERS
            SET
                ISVIP = 0
            WHERE
                CUSTOMERID = REC.CUSTOMERID;
        END IF;
    END LOOP;

    COMMIT;
END;