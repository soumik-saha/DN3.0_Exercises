CREATE OR REPLACE PROCEDURE UPDATESALARY (
    EMPLOYEE_ID IN NUMBER,
    PERCENTAGE IN NUMBER
) IS
    EMPLOYEE_NOT_FOUND EXCEPTION;
    V_COUNT            NUMBER;
BEGIN
 
    -- Check if the employee exists
    SELECT
        COUNT(*) INTO V_COUNT
    FROM
        EMPLOYEES
    WHERE
        ID = EMPLOYEE_ID FOR UPDATE;
    IF V_COUNT = 0 THEN
        RAISE EMPLOYEE_NOT_FOUND;
    ELSE
 
        -- Update the employee's salary
        UPDATE EMPLOYEES
        SET
            SALARY = SALARY + (
                SALARY * (PERCENTAGE / 100)
            )
        WHERE
            ID = EMPLOYEE_ID;
    END IF;

    COMMIT;
EXCEPTION
    WHEN EMPLOYEE_NOT_FOUND THEN
 
        -- Log the error
        INSERT INTO ERRORLOGS (
            MESSAGE,
            LOG_TIME
        ) VALUES (
            'Employee ID not found',
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
END UPDATESALARY;