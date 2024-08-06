DECLARE
    CURSOR C_LOANS IS
    SELECT
        LOANID,
        INTERESTRATE
    FROM
        LOANS;
    V_LOANID              LOANS.LOANID%TYPE;
    V_CURRENTINTERESTRATE LOANS.INTERESTRATE%TYPE;
    V_NEWINTERESTRATE     NUMBER;
 
    -- Example function to calculate new interest rate
    FUNCTION CALCULATE_NEW_INTEREST_RATE(
        P_CURRENT_RATE NUMBER
    ) RETURN NUMBER IS
    BEGIN
        RETURN P_CURRENT_RATE + 0.01; -- Example: increase interest rate by 1%
    END;
BEGIN
    OPEN C_LOANS;
    LOOP
        FETCH C_LOANS INTO V_LOANID, V_CURRENTINTERESTRATE;
        EXIT WHEN C_LOANS%NOTFOUND;
        V_NEWINTERESTRATE := CALCULATE_NEW_INTEREST_RATE(V_CURRENTINTERESTRATE);
 
        -- Update the interest rate
        UPDATE LOANS
        SET
            INTERESTRATE = V_NEWINTERESTRATE
        WHERE
            LOANID = V_LOANID;
 
        -- Optionally, log the update
        DBMS_OUTPUT.PUT_LINE('Updated LoanID: '
                             || V_LOANID
                             || ' to new interest rate: '
                             || V_NEWINTERESTRATE);
    END LOOP;

    CLOSE C_LOANS;
END;
/